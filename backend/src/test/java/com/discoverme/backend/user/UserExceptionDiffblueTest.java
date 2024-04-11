package com.discoverme.backend.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class UserExceptionDiffblueTest {
    /**
     * Method under test: {@link UserException#UserException(String)}
     */
    @Test
    void testNewUserException() {
        // Arrange and Act
        UserException actualUserException = new UserException("An error occurred");

        // Assert
        assertEquals("An error occurred", actualUserException.getLocalizedMessage());
        assertEquals("An error occurred", actualUserException.getMessage());
        assertNull(actualUserException.getCause());
        assertEquals(0, actualUserException.getSuppressed().length);
    }
}
