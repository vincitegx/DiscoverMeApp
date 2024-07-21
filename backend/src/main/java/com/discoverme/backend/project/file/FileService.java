package com.discoverme.backend.project.file;

import com.discoverme.backend.social.SocialPlatform;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
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
@Slf4j
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
        Path newPath = Paths.get(this.fileStorageLocation).toAbsolutePath().normalize();
        try {
            Files.createDirectories(newPath);
            Path filePath = newPath.resolve(StringUtils.cleanPath(key));
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch (IOException ex) {
            Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Could not store the file. Error: " + ex.getMessage());
        }
    }

    public String uploadFile(MultipartFile file, String social) {
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        var key = UUID.randomUUID() + "." + extension;
        var key2 = UUID.randomUUID() + "." + extension;
        // return the key 2 and let the ffmpeg command be handled asynchronously
        String directory = this.fileStorageLocation;
        Path newPath = Paths.get(directory).toAbsolutePath().normalize();
        try {
            Files.createDirectories(newPath);
            Path inputFilePath = newPath.resolve(StringUtils.cleanPath(key));
            Path outputFilePath = newPath.resolve(StringUtils.cleanPath(key2));
            Files.copy(file.getInputStream(), inputFilePath, StandardCopyOption.REPLACE_EXISTING);
            executeFFmpegCommand(inputFilePath.toString(), outputFilePath.toString(), social);
            Files.deleteIfExists(inputFilePath);
        } catch (IOException ex) {
            Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key2;
    }

    public String extractFileName(String filePath) {
        Path path = Paths.get(filePath);
        return path.getFileName().toString();
    }

    public String executeFFmpegCommand(String inputFilePath, String social) throws IOException {
        String outputFilePath = inputFilePath.replace(".mp4", "_converted.mp4");
        String[] ffmpegCommand = getFfmpegCommand(inputFilePath, outputFilePath, social);

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
            Files.deleteIfExists(Path.of(inputFilePath));
//            outputFilePath = inputFilePath.replace("_converted.mp4", ".mp4");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Error executing FFmpeg command: " + e.getMessage(), e);
        }
        return outputFilePath;
    }



    private void executeFFmpegCommand(String inputFilePath, String outputFilePath, String social) throws IOException {
        String[] ffmpegCommand = getFfmpegCommand(inputFilePath, outputFilePath, social);

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

    private String[] getFfmpegCommand(String inputFilePath, String outputFilePath, String social) {
        String ffmpegPath = "C:\\ffmpeg\\bin\\ffmpeg.exe";
        String[] ffmpegCommand;
        if (social.equalsIgnoreCase(SocialPlatform.FACEBOOK.name())) {
            ffmpegCommand = fbStoryVideoSpec(ffmpegPath, inputFilePath, outputFilePath);
        } else if (social.equalsIgnoreCase(SocialPlatform.INSTAGRAM.name())) {
            ffmpegCommand = igStoryVideoSpec(ffmpegPath, inputFilePath, outputFilePath);
        }else {
            throw new IllegalArgumentException("Invalid social platform: " + social);
        }
        return ffmpegCommand;
    }

    private String[] igStoryVideoSpec(String ffmpegPath, String inputFilePath, String outputFilePath) {
        return new String[]{
                ffmpegPath,
                "-i", inputFilePath,
                "-c:v", "libx265", // Using HEVC codec
                "-preset", "slow",
                "-crf", "22",
                "-pix_fmt", "yuv420p", // Chroma subsampling
                "-vf", "scale='min(1920,iw)':-2, pad=ceil(iw/2)*2:ceil(ih/2)*2, setsar=1", // Scaling and padding
                "-g", "48", // Closed GOP
                "-movflags", "faststart", // Move moov atom to the front of the file
                "-maxrate", "25M", // Maximum video bitrate
                "-bufsize", "50M", // Buffer size
                "-c:a", "aac",
                "-b:a", "128k", // Constant bitrate for audio
                "-ar", "48000", // Audio sample rate
                "-ac", "2", // Audio channels (stereo)
                "-r", "30", // Frame rate
                "-t", "60", // Limit duration to 60 seconds
                "-y", // Overwrite output file if it exists
                outputFilePath
        };
    }

//    private String[] fbStoryVideoSpec(String ffmpegPath,String inputFilePath, String outputFilePath) {
//        return new String[]{
//                ffmpegPath,
//                "-i", inputFilePath,
//                "-c:v", "libx264", // Using H.264 codec
//                "-preset", "slow",
//                "-crf", "22",
//                "-pix_fmt", "yuv420p", // Chroma subsampling
//                "-vf", "scale='min(1080,iw)':-2, pad=ceil(iw/2)*2:ceil(ih/2)*2, setsar=1", // Scaling and padding
//                "-g", "48", // Closed GOP
//                "-movflags", "faststart", // Move moov atom to the front of the file
//                "-maxrate", "25M", // Maximum video bitrate
//                "-bufsize", "50M", // Buffer size
//                "-c:a", "aac",
//                "-b:a", "128k", // Constant bitrate for audio
//                "-ar", "48000", // Audio sample rate
//                "-ac", "2", // Audio channels (stereo)
//                "-r", "30", // Frame rate
//                "-t", "60", // Limit duration to 60 seconds (as reels published as a story on a Facebook Page cannot exceed 60 seconds)
//                "-y", // Overwrite output file if it exists
//                outputFilePath
//        };
//    }

    private String[] fbStoryVideoSpec(String ffmpegPath, String inputFilePath, String outputFilePath) {
        return new String[]{
                ffmpegPath,
                "-i", inputFilePath,
                "-preset", "slow",
                "-crf", "22",
                "-pix_fmt", "yuv420p", // Chroma subsampling
                "-vf", "scale='if(gt(a,0.5625),1080,-2)':'if(gt(a,0.5625),-2,1920)',pad=1080:1920:(1080-iw)/2:(1920-ih)/2,fps=23,setsar=1", // Scaling and padding to maintain aspect ratio
                "-c:v", "libx264",
                "-g", "48", // Closed GOP
                "-movflags", "faststart", // Move moov atom to the front of the file
                "-maxrate", "25M", // Maximum video bitrate
                "-bufsize", "50M", // Buffer size
                "-c:a", "aac",
                "-b:a", "128k", // Constant bitrate for audio
                "-ar", "48000", // Audio sample rate
                "-ac", "2", // Audio channels (stereo)
                "-r", "30", // Frame rate
                "-t", "60", // Limit duration to 60 seconds (as reels published as a story on a Facebook Page cannot exceed 60 seconds)
                "-y",
                outputFilePath
        };
    }



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

    public byte[] readFileFromLocation(String fileUrl) {
        if (org.apache.commons.lang3.StringUtils.isBlank(fileUrl)) {
            return null;
        }
        try {
            Path filePath = new File(fileUrl).toPath();
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            log.warn("No file found in the path {}", fileUrl);
        }
        return null;
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
