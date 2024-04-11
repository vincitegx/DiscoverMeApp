package com.discoverme.backend.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class GlobalExceptionDiffblueTest {
    /**
     * Method under test: {@link GlobalException#GlobalException(String)}
     */
    @Test
    void testNewGlobalException() {
        // Arrange and Act
        GlobalException actualGlobalException = new GlobalException("An error occurred");

        // Assert
        assertEquals("An error occurred", actualGlobalException.getLocalizedMessage());
        assertEquals("An error occurred", actualGlobalException.getMessage());
        assertNull(actualGlobalException.getCause());
        assertEquals(0, actualGlobalException.getSuppressed().length);
    }
}
