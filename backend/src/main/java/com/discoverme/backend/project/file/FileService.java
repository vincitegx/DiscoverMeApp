package com.discoverme.backend.project.file;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Primary
public class FileService {

    private final String fileStorageLocation;

    public FileService(@Value("${file.upload-dir:temp}") String fileStorageLocation) throws IOException {
        this.fileStorageLocation = fileStorageLocation;
        Path fileStoragePath = Paths.get(fileStorageLocation)
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException ex) {
            throw new IOException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @PostConstruct
    public void init() {
        Path outputPath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        try {
            Files.createDirectories(outputPath);
        } catch (IOException ex) {
            throw new RuntimeException("Could not create the directory for output files.", ex);
        }
    }

    public String uploadFile(MultipartFile file) {
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        var key = UUID.randomUUID() + "." + extension;
        String directory = this.fileStorageLocation;
        Path newPath = Paths.get(directory).toAbsolutePath().normalize();
        try {
            Files.createDirectories(newPath);
            Files.copy(file.getInputStream(), newPath.resolve(StringUtils.cleanPath(key)), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
    }

//    public String uploadFile(MultipartFile file) {
//        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
//        var key = UUID.randomUUID() + "." + extension;
//        String directory = this.fileStorageLocation;
//        Path newPath = Paths.get(directory).toAbsolutePath().normalize();
//        try {
//            Files.createDirectories(newPath);
//            Path inputFilePath = newPath.resolve(StringUtils.cleanPath(key));
//            Path outputFilePath = newPath.resolve("output.mp4");
//            Files.copy(file.getInputStream(), inputFilePath, StandardCopyOption.REPLACE_EXISTING);
//            executeFFmpegCommand(inputFilePath.toString(), outputFilePath.toString());
//
//        } catch (IOException ex) {
//            Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return key;
//    }

    private void executeFFmpegCommand(String inputFilePath, String outputFilePath) throws IOException {
        String ffmpegPath = "C:\\ffmpeg\\bin\\ffmpeg.exe"; // Example path, replace with actual path
        String[] ffmpegCommand = {
                ffmpegPath,
                "-i", inputFilePath,
                "-c:v", "libx264",
                "-aspect", "16:9",
                "-crf", "18",
                "-vf", "scale=iw*min(1280/iw\\,720/ih):ih*min(1280/iw\\,720/ih),pad=1280:720:(1280-iw)/2:(720-ih)/2",
                "-fpsmax", "60",
                "-preset", "ultrafast",
                "-c:a", "aac",
                "-b:a", "128k",
                "-ac", "1",
                "-pix_fmt", "yuv420p",
                "-movflags", "+faststart",
                "-t", "59",
                "-y", outputFilePath
        };

        ProcessBuilder processBuilder = new ProcessBuilder(ffmpegCommand);
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        try {
            process.waitFor();
            System.out.println("FFmpeg command executed successfully.");
        } catch (InterruptedException e) {
            throw new IOException("Error executing FFmpeg command: " + e.getMessage(), e);
        }
    }

    public List<String> uploadFiles(List<MultipartFile> files) {
        List<String> filenames = new ArrayList<>();
        files.forEach(file -> filenames.add(uploadFile(file)));
        return filenames;
    }

    public Resource downloadFile(String fileName) {
        String directory = this.fileStorageLocation;
        Path path = Paths.get(directory).toAbsolutePath().resolve(fileName);
        Resource resource;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException ex) {
            throw new FileServiceException("Issue Reading this file", ex);
        }
        if (resource.exists()) {
            return resource;
        } else {
            throw new FileServiceException("This file does not exist or is not readable");
        }
    }

    public boolean deleteFile(String name) {
        String directory = this.fileStorageLocation;
        try {
            Path newPath = Paths.get(directory).toAbsolutePath().normalize().resolve(name);
            return Files.deleteIfExists(newPath);
        } catch (IOException ex) {
            Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void deleteFiles(List<String> files) {
        files.forEach(this::deleteFile);
    }
}
