package com.discoverme.backend.project.file;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/projects/contents")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    private final HttpServletRequest request;
    @GetMapping("{fileName:.+}")
    public ResponseEntity<Resource> displayFile(@PathVariable String fileName) {
        Resource resource = fileService.downloadFile(fileName);
        String mimeType;
        try {
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            mimeType = MediaType.ALL_VALUE;
        }
        if(mimeType == null) {
            mimeType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(getMediaType(fileName))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+resource.getFilename()+"\"")
                .body(resource);
    }

    private MediaType getMediaType(String fileName) {
        if (fileName.toLowerCase().endsWith(".mp4")) {
            return MediaType.valueOf("video/mp4");
        }
        // Add more media types as needed for other file types

        // Default to application/octet-stream if the file type is unknown
        return MediaType.APPLICATION_OCTET_STREAM;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class MyFileNotFoundException extends FileNotFoundException {
        public MyFileNotFoundException(String message) {
            super(message);
        }
    }
}
