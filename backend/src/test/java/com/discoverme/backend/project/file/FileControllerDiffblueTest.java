package com.discoverme.backend.project.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {FileController.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class FileControllerDiffblueTest {
    @Autowired
    private FileController fileController;

    @MockBean
    private FileService fileService;

    @MockBean
    private HttpServletRequest httpServletRequest;

    /**
     * Method under test: {@link FileController#displayFile(String)}
     */
    @Test
    void testDisplayFile() throws Exception {
        // Arrange
        when(fileService.downloadFile(Mockito.<String>any()))
                .thenReturn(new ByteArrayResource("AXAXAXAX".getBytes("UTF-8")));
        when(httpServletRequest.getServletContext()).thenReturn(new MockServletContext());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/projects/contents/{fileName:.+}",
                "U");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(fileController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/octet-stream"))
                .andExpect(MockMvcResultMatchers.content().string("AXAXAXAX"));
    }

    /**
     * Method under test: {@link FileController#displayFile(String)}
     */
    @Test
    void testDisplayFile2() throws Exception {
        // Arrange
        when(fileService.downloadFile(Mockito.<String>any()))
                .thenReturn(new ByteArrayResource("AXAXAXAX".getBytes("UTF-8")));
        when(httpServletRequest.getServletContext()).thenReturn(new MockServletContext());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/projects/contents/{fileName:.+}",
                "Uri Variables", "Uri Variables");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(fileController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/octet-stream"))
                .andExpect(MockMvcResultMatchers.content().string("AXAXAXAX"));
    }

    /**
     * Method under test:
     * {@link FileController.MyFileNotFoundException#MyFileNotFoundException(String)}
     */
    @Test
    void testMyFileNotFoundExceptionNewMyFileNotFoundException() {
        // Arrange and Act
        FileController.MyFileNotFoundException actualMyFileNotFoundException = new FileController.MyFileNotFoundException(
                "An error occurred");

        // Assert
        assertEquals("An error occurred", actualMyFileNotFoundException.getLocalizedMessage());
        assertEquals("An error occurred", actualMyFileNotFoundException.getMessage());
        assertNull(actualMyFileNotFoundException.getCause());
        assertEquals(0, actualMyFileNotFoundException.getSuppressed().length);
    }
}
