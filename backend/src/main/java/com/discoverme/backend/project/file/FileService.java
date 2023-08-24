package com.discoverme.backend.project.file;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class FileService {

    private Path fileStoragePath;
    private String fileStorageLocation;

    public FileService(@Value("${file.upload-dir:temp}") String fileStorageLocation) throws IOException {
        this.fileStorageLocation = fileStorageLocation;
        this.fileStoragePath = Paths.get(fileStorageLocation)
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStoragePath);
        } catch (IOException ex) {
            throw new IOException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String uploadFile(MultipartFile file) {
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        var key = UUID.randomUUID().toString() + "." + extension;
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
        files.forEach(file -> deleteFile(file));
    }
}
