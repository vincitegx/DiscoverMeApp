package com.discoverme.backend.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ProjectExceptionDiffblueTest {
    /**
     * Method under test: {@link ProjectException#ProjectException(String)}
     */
    @Test
    void testNewProjectException() {
        // Arrange and Act
        ProjectException actualProjectException = new ProjectException("An error occurred");

        // Assert
        assertEquals("An error occurred", actualProjectException.getLocalizedMessage());
        assertEquals("An error occurred", actualProjectException.getMessage());
        assertNull(actualProjectException.getCause());
        assertEquals(0, actualProjectException.getSuppressed().length);
    }
}
