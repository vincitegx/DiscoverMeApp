package com.discoverme.backend.user.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class RegistrationExceptionDiffblueTest {
    /**
     * Method under test:
     * {@link RegistrationException#RegistrationException(String)}
     */
    @Test
    void testNewRegistrationException() {
        // Arrange and Act
        RegistrationException actualRegistrationException = new RegistrationException("An error occurred");

        // Assert
        assertEquals("An error occurred", actualRegistrationException.getLocalizedMessage());
        assertEquals("An error occurred", actualRegistrationException.getMessage());
        assertNull(actualRegistrationException.getCause());
        assertEquals(0, actualRegistrationException.getSuppressed().length);
    }
}
