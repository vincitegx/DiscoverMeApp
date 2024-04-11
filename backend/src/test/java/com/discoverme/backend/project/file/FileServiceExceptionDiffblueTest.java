package com.discoverme.backend.project.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class FileServiceExceptionDiffblueTest {
    /**
     * Method under test: {@link FileServiceException#FileServiceException(String)}
     */
    @Test
    void testNewFileServiceException() {
        // Arrange and Act
        FileServiceException actualFileServiceException = new FileServiceException("An error occurred");

        // Assert
        assertEquals("An error occurred", actualFileServiceException.getLocalizedMessage());
        assertEquals("An error occurred", actualFileServiceException.getMessage());
        assertNull(actualFileServiceException.getCause());
        assertEquals(0, actualFileServiceException.getSuppressed().length);
    }

    /**
     * Method under test:
     * {@link FileServiceException#FileServiceException(String, Throwable)}
     */
    @Test
    void testNewFileServiceException2() {
        // Arrange
        Throwable cause = new Throwable();

        // Act
        FileServiceException actualFileServiceException = new FileServiceException("An error occurred", cause);

        // Assert
        assertEquals("An error occurred", actualFileServiceException.getLocalizedMessage());
        assertEquals("An error occurred", actualFileServiceException.getMessage());
        Throwable cause2 = actualFileServiceException.getCause();
        assertNull(cause2.getLocalizedMessage());
        assertNull(cause2.getMessage());
        assertNull(cause2.getCause());
        Throwable[] suppressed = actualFileServiceException.getSuppressed();
        assertEquals(0, suppressed.length);
        assertSame(cause, cause2);
        assertSame(suppressed, cause2.getSuppressed());
    }
}
