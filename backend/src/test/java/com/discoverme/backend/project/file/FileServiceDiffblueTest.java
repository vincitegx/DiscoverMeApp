package com.discoverme.backend.project.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.discoverme.backend.social.SocialPlatform;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class FileServiceDiffblueTest {
    @MockBean
    private FileService fileService;

    /**
     * Method under test: {@link FileService#uploadFile(MultipartFile)}
     */
    @Test
    void testUploadFile() throws IOException {
        // Arrange
        when(fileService.uploadFile(Mockito.<MultipartFile>any(), SocialPlatform.FACEBOOK.name())).thenReturn("Upload File");

        // Act
        String actualUploadFileResult = fileService
                .uploadFile(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), SocialPlatform.FACEBOOK.name());

        // Assert
        verify(fileService).uploadFile(isA(MultipartFile.class), SocialPlatform.FACEBOOK.name());
        assertEquals("Upload File", actualUploadFileResult);
    }

    /**
     * Method under test: {@link FileService#uploadFiles(List)}
     */
    @Test
    void testUploadFiles() {
        // Arrange
        ArrayList<String> stringList = new ArrayList<>();
        when(fileService.uploadFiles(Mockito.<List<MultipartFile>>any(), SocialPlatform.FACEBOOK.name())).thenReturn(stringList);

        // Act
        List<String> actualUploadFilesResult = fileService.uploadFiles(new ArrayList<>(), SocialPlatform.FACEBOOK.name()));

        // Assert
        verify(fileService).uploadFiles(isA(List.class), SocialPlatform.FACEBOOK.name());
        assertTrue(actualUploadFilesResult.isEmpty());
        assertSame(stringList, actualUploadFilesResult);
    }

    /**
     * Method under test: {@link FileService#uploadFiles(List)}
     */
    @Test
    void testUploadFiles2() throws IOException {
        // Arrange
        ArrayList<String> stringList = new ArrayList<>();
        when(fileService.uploadFiles(Mockito.<List<MultipartFile>>any())).thenReturn(stringList);

        ArrayList<MultipartFile> files = new ArrayList<>();
        files.add(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Act
        List<String> actualUploadFilesResult = fileService.uploadFiles(files);

        // Assert
        verify(fileService).uploadFiles(isA(List.class));
        assertTrue(actualUploadFilesResult.isEmpty());
        assertSame(stringList, actualUploadFilesResult);
    }

    /**
     * Method under test: {@link FileService#uploadFiles(List)}
     */
    @Test
    void testUploadFiles3() throws IOException {
        // Arrange
        ArrayList<String> stringList = new ArrayList<>();
        when(fileService.uploadFiles(Mockito.<List<MultipartFile>>any())).thenReturn(stringList);

        ArrayList<MultipartFile> files = new ArrayList<>();
        files.add(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
        files.add(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Act
        List<String> actualUploadFilesResult = fileService.uploadFiles(files);

        // Assert
        verify(fileService).uploadFiles(isA(List.class));
        assertTrue(actualUploadFilesResult.isEmpty());
        assertSame(stringList, actualUploadFilesResult);
    }

    /**
     * Method under test: {@link FileService#downloadFile(String)}
     */
    @Test
    void testDownloadFile() throws UnsupportedEncodingException {
        // Arrange
        ByteArrayResource byteArrayResource = new ByteArrayResource("AXAXAXAX".getBytes("UTF-8"));
        when(fileService.downloadFile(Mockito.<String>any())).thenReturn(byteArrayResource);

        // Act
        Resource actualDownloadFileResult = fileService.downloadFile("foo.txt");

        // Assert
        verify(fileService).downloadFile(eq("foo.txt"));
        assertSame(byteArrayResource, actualDownloadFileResult);
    }

    /**
     * Method under test: {@link FileService#deleteFile(String)}
     */
    @Test
    void testDeleteFile() {
        // Arrange
        when(fileService.deleteFile(Mockito.<String>any())).thenReturn(true);

        // Act
        boolean actualDeleteFileResult = fileService.deleteFile("Name");

        // Assert
        verify(fileService).deleteFile(eq("Name"));
        assertTrue(actualDeleteFileResult);
    }

    /**
     * Method under test: {@link FileService#deleteFile(String)}
     */
    @Test
    void testDeleteFile2() {
        // Arrange
        when(fileService.deleteFile(Mockito.<String>any())).thenReturn(false);

        // Act
        boolean actualDeleteFileResult = fileService.deleteFile("Name");

        // Assert
        verify(fileService).deleteFile(eq("Name"));
        assertFalse(actualDeleteFileResult);
    }

    /**
     * Method under test: {@link FileService#deleteFiles(List)}
     */
    @Test
    void testDeleteFiles() {
        // Arrange
        doNothing().when(fileService).deleteFiles(Mockito.<List<String>>any());

        // Act
        fileService.deleteFiles(new ArrayList<>());

        // Assert that nothing has changed
        verify(fileService).deleteFiles(isA(List.class));
    }

    /**
     * Method under test: {@link FileService#deleteFiles(List)}
     */
    @Test
    void testDeleteFiles2() {
        // Arrange
        doNothing().when(fileService).deleteFiles(Mockito.<List<String>>any());

        ArrayList<String> files = new ArrayList<>();
        files.add("foo");

        // Act
        fileService.deleteFiles(files);

        // Assert that nothing has changed
        verify(fileService).deleteFiles(isA(List.class));
    }

    /**
     * Method under test: {@link FileService#deleteFiles(List)}
     */
    @Test
    void testDeleteFiles3() {
        // Arrange
        doNothing().when(fileService).deleteFiles(Mockito.<List<String>>any());

        ArrayList<String> files = new ArrayList<>();
        files.add("42");
        files.add("foo");

        // Act
        fileService.deleteFiles(files);

        // Assert that nothing has changed
        verify(fileService).deleteFiles(isA(List.class));
    }
}
