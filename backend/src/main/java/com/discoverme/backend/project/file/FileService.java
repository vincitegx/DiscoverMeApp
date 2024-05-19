package com.discoverme.backend.project.file;

import com.discoverme.backend.social.SocialPlatform;
import com.discoverme.backend.social.Socials;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

//    public String uploadFile(MultipartFile file) {
//        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
//        var key = UUID.randomUUID() + "." + extension;
//        String directory = this.fileStorageLocation;
//        Path newPath = Paths.get(directory).toAbsolutePath().normalize();
//        try {
//            Files.createDirectories(newPath);
//            Files.copy(file.getInputStream(), newPath.resolve(StringUtils.cleanPath(key)), StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException ex) {
//            Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return key;
//    }

    public String uploadFile(MultipartFile file, String social) {
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        var key = UUID.randomUUID() + "." + extension;
        var key2 = UUID.randomUUID() + "." + extension;
        String directory = this.fileStorageLocation;
        Path newPath = Paths.get(directory).toAbsolutePath().normalize();
        try {
            Files.createDirectories(newPath);
            Path inputFilePath = newPath.resolve(StringUtils.cleanPath(key));
            Path outputFilePath = newPath.resolve(StringUtils.cleanPath(key2));
            Files.copy(file.getInputStream(), inputFilePath, StandardCopyOption.REPLACE_EXISTING);

            // Execute FFmpeg command
            executeFFmpegCommand(inputFilePath.toString(), outputFilePath.toString());
            Files.deleteIfExists(inputFilePath);
        } catch (IOException ex) {
            Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key2;
    }

    private void executeFFmpegCommand(String inputFilePath, String outputFilePath) throws IOException {
        String ffmpegPath = "C:\\ffmpeg\\bin\\ffmpeg.exe";
        String[] ffmpegCommand = {
                ffmpegPath,
                "-i", inputFilePath,
                "-c:v", "libx264",
                "-aspect", "9:16", // Aspect ratio: 9:16
                "-crf", "18", // Quality (CRF): 18 (adjust as needed)
                "-vf", "scale=1080:1920,crop=1080:1920:0:0", // Frame size: 1080x1920, crop to fit aspect ratio
                "-r", "50", // Frame rate: 50 frames/second
                "-c:a", "aac",
                "-b:a", "128k",
                "-ar", "44100", // Audio sample rate: 44.1kHz
                "-ac", "2", // Audio channels: 2 (stereo)
                "-movflags", "+faststart",
                "-t", "60", // Length: 60 seconds or less
                "-y", outputFilePath
        };

        System.out.println("Executing FFmpeg command: ");
        for (String arg : ffmpegCommand) {
            System.out.print(arg + " ");
        }
        System.out.println();

        ProcessBuilder processBuilder = new ProcessBuilder(ffmpegCommand);
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        try {
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IOException("Error executing FFmpeg command. Exit code: " + exitCode);
            }
            System.out.println("FFmpeg command executed successfully.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Error executing FFmpeg command: " + e.getMessage(), e);
        }
    }

//    if (social.equalsIgnoreCase(SocialPlatform.FACEBOOK.name())) {
//        executeFBFFmpegCommand(file.getInputStream().toString(), inputFilePath.toString());
//    } else if (social.equalsIgnoreCase(SocialPlatform.INSTAGRAM.name())) {
//        executeIGFFmpegCommand(file.getInputStream().toString(), inputFilePath.toString());
//    }


    private boolean executeFFmpegCommand1(String inputFilePath, String outputFilePath) throws IOException {
        String ffmpegPath = "C:\\ffmpeg\\bin\\ffmpeg.exe"; // Update with your actual FFmpeg path

        // Probe the video to get its properties
        ProcessBuilder probeBuilder = new ProcessBuilder(ffmpegPath, "-i", inputFilePath, "-hide_banner");
        Process probeProcess = probeBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(probeProcess.getErrorStream()));
        String line;
        int width = 0;
        int height = 0;
        float fps = 0.0f;

        while ((line = reader.readLine()) != null) {
            if (line.contains("Video:")) {
                String[] parts = line.split(",");
                for (String part : parts) {
                    part = part.trim();
                    if (part.endsWith("fps")) {
                        fps = Float.parseFloat(part.split(" ")[0]);
                    }
                    if (part.contains("x")) {
                        String[] dimensions = part.split(" ")[0].split("x");
                        width = Integer.parseInt(dimensions[0]);
                        height = Integer.parseInt(dimensions[1]);
                    }
                }
            }
        }

        // Determine if processing is needed
        boolean needsProcessing = false;
        if (fps > 30) {
            needsProcessing = true;
        }
        if (width < 1200 || width > 4000 || (width % 16 != 0)) {
            needsProcessing = true;
        }

        // Ensure aspect ratio compliance
        double aspectRatio = (double) width / height;
        if (!(aspectRatio == 9.0 / 16.0 || aspectRatio == 4.0 / 5.0 || aspectRatio == 1.91 / 1.0)) {
            needsProcessing = true;
        }

        if (!needsProcessing) {
            return false; // No processing needed
        }

        // Prepare the FFmpeg command
        String[] ffmpegCommand = {
                ffmpegPath,
                "-i", inputFilePath,
                "-c:v", "libx264",
                "-vf", "scale='min(4000,iw)':-2:flags=lanczos", // Maintain aspect ratio, ensure width ≤ 4000 and divisible by 16
                "-r", "30", // Fixed frame rate, up to 30 fps
                "-crf", "18", // Quality (CRF): 18 (adjust as needed)
                "-c:a", "aac",
                "-b:a", "128k",
                "-ar", "44100", // Audio sample rate: 44.1kHz
                "-ac", "2", // Audio channels: 2 (stereo)
                "-movflags", "+faststart",
                "-t", "60", // Length: 60 seconds or less
                "-y", outputFilePath
        };

        ProcessBuilder processBuilder = new ProcessBuilder(ffmpegCommand);
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        try {
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IOException("Error executing FFmpeg command. Exit code: " + exitCode);
            }
            System.out.println("FFmpeg command executed successfully.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Error executing FFmpeg command: " + e.getMessage(), e);
        }
        return true; // Processing was done
    }

    private void executeFBFFmpegCommand(String inputFilePath, String outputFilePath) throws IOException {
        String ffmpegPath = "C:\\ffmpeg\\bin\\ffmpeg.exe";
        String[] ffmpegCommand = {
                ffmpegPath,
                "-i", inputFilePath,
                "-c:v", "libx264",
                "-aspect", "9:16", // Aspect ratio: 9:16
                "-crf", "18", // Quality (CRF): 18 (adjust as needed)
                "-vf", "scale=1080:1920,crop=1080:1920:0:0", // Frame size: 1080x1920, crop to fit aspect ratio
                "-r", "50", // Frame rate: 50 frames/second
                "-c:a", "aac",
                "-b:a", "128k",
                "-ar", "44100", // Audio sample rate: 44.1kHz
                "-ac", "2", // Audio channels: 2 (stereo)
                "-movflags", "+faststart",
                "-t", "60", // Length: 60 seconds or less
                "-y", outputFilePath
        };

        System.out.println("Executing FFmpeg command: ");
        for (String arg : ffmpegCommand) {
            System.out.print(arg + " ");
        }
        System.out.println();

        ProcessBuilder processBuilder = new ProcessBuilder(ffmpegCommand);
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        try {
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IOException("Error executing FFmpeg command. Exit code: " + exitCode);
            }
            System.out.println("FFmpeg command executed successfully.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Error executing FFmpeg command: " + e.getMessage(), e);
        }
    }

    private void executeIGFFmpegCommand(String inputFilePath, String outputFilePath) throws IOException {
        String ffmpegPath = "C:\\ffmpeg\\bin\\ffmpeg.exe"; // Example path, replace with actual path
        String[] ffmpegCommand = {
                ffmpegPath,
                "-i", inputFilePath,
                "-c:v", "libx264",
                "-aspect", "9:16", // Aspect ratio: 9:16
                "-crf", "18", // Quality (CRF): 18 (adjust as needed)
                "-vf", "scale=1080:1920,crop=1080:1920:0:0", // Frame size: 1080x1920, crop to fit aspect ratio
                "-r", "50", // Frame rate: 50 frames/second
                "-c:a", "aac",
                "-b:a", "128k",
                "-ar", "44100", // Audio sample rate: 44.1kHz
                "-ac", "2", // Audio channels: 2 (stereo)
                "-movflags", "+faststart",
                "-t", "60", // Length: 60 seconds or less
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

    public List<String> uploadFiles(List<MultipartFile> files, String social) {
        List<String> filenames = new ArrayList<>();
        files.forEach(file -> filenames.add(uploadFile(file, social)));
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
