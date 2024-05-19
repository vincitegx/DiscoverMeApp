package com.discoverme.backend.project.file.googledrive;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import com.google.auth.http.HttpCredentialsAdapter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.google.api.services.drive.DriveScopes.DRIVE_FILE;

public class UploadService {
    private static final String APPLICATION_NAME = "[your project name]";
//    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_FILE);
    private static final String CREDENTIALS_FILE_PATH = "/client_secret.json";

    private Drive getDrive() throws GeneralSecurityException, IOException, EntityNotFoundException {
        Credential credential = authorize();
//        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credential
        ).setApplicationName(APPLICATION_NAME).build();
    }

    public String uploadFile(MultipartFile file) throws IOException, GeneralSecurityException, EntityNotFoundException {

        if (file == null) {
            throw new EntityNotFoundException("File cannot be null!");
        } else {
            File fileMetaData = new File();
            fileMetaData.setName(file.getOriginalFilename());
            fileMetaData.setMimeType(file.getContentType());

            java.io.File tempFile = convertMultipartFileToFile(file);
            FileContent mediaContent = new FileContent(file.getContentType(), tempFile);

            File uploadedFile = getDrive()
                    .files()
                    .create(fileMetaData, mediaContent)
                    .setFields("id")
                    .execute();

            String fileId = uploadedFile.getId();

            //set file permissions and make it publicly accessible
            getDrive()
                    .permissions()
                    .create(fileId, setPermission("anyone", "writer"))
                    .execute();

            // Delete the temporary file
            tempFile.delete();

            //return the file url
            return fileId;
        }
    }

    //delete file
    public void deleteFile(String fileId) throws GeneralSecurityException, IOException, EntityNotFoundException {
        getDrive()
                .files()
                .delete(fileId)
                .execute();
    }

    // helper method to set permission drive file
    private Permission setPermission(String type,
                                     String role) {
        Permission permission = new Permission();
        permission.setType(type);
        permission.setRole(role);
        return permission;
    }

    //helper method to authorize the Google Drive credentials
    private Credential authorize() throws GeneralSecurityException, IOException, EntityNotFoundException {
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                getClientSecrets(),
                SCOPES
        ).setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8088).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    //helper method to get clientSecret from the Google Drive credentials
    private GoogleClientSecrets getClientSecrets() throws IOException, EntityNotFoundException {
        InputStream inputStream = UploadService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (inputStream == null) {
            throw new EntityNotFoundException("Resource not found!");
        } else {
            return GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(inputStream));
        }
    }

    //helper method to convert the multipart file to file
    private java.io.File convertMultipartFileToFile(MultipartFile file) throws IOException {
        java.io.File convertedFile = new java.io.File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    //helper method to get the file url
    public String getFileUrl(String fileId) throws IOException, GeneralSecurityException, EntityNotFoundException {

        //get the public web view url
        String webViewUrl = getDrive()
                .files()
                .get(fileId)
                .setFields("webViewLink")
                .execute()
                .getWebViewLink();
        return webViewUrl;
    }

    public static String uploadBasic() throws IOException, GeneralSecurityException {
        // Load pre-authorized user credentials from the environment.
        // TODO(developer) - See https://developers.google.com/identity for
        // guides on implementing OAuth2 for your application.
//        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
//                .createScoped(Arrays.asList(DriveScopes.DRIVE_FILE));
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//        GoogleCredentials credentials = getCredentials(new NetHttpTransport(), GsonFactory.getDefaultInstance());
//        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(
//                credentials);
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(
                null);

        // Build a new authorized API client service.
        Drive service = new Drive.Builder(new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                requestInitializer)
                .setApplicationName("Drive samples")
                .build();
        // Upload file photo.jpg on drive.
        File fileMetadata = new File();
        fileMetadata.setName("photo.jpg");
        // File's content.
        java.io.File filePath = new java.io.File("files/photo.jpg");
        // Specify media type and file-path for file.
        FileContent mediaContent = new FileContent("image/jpeg", filePath);
        try {
            File file = service.files().create(fileMetadata, mediaContent)
                    .setFields("id")
                    .execute();
            System.out.println("File ID: " + file.getId());
            return file.getId();
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            System.err.println("Unable to upload file: " + e.getDetails());
            throw e;
        }
    }

    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory)
            throws IOException {
        InputStream in = UploadService.class.getResourceAsStream("/client_secret_1042749728847-3l89hs3p2o32qm973hico0vj2lg0rafg.apps.googleusercontent.com.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, Set.of(DRIVE_FILE))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
}
