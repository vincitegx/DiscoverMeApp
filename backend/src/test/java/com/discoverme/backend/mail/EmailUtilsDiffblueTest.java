package com.discoverme.backend.mail;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EmailUtilsDiffblueTest {
    /**
     * Method under test: {@link EmailUtils#getEmailMessage(String, String, String)}
     */
    @Test
    void testGetEmailMessage() {
        // Arrange, Act and Assert
        assertEquals(
                "Hello Name,\n" + "\n"
                        + "Your new account has been created. Please click the link below to verify your account. \n" + "\n"
                        + "https://localhost:4200/verify?token=ABC123\n" + "\n" + "The support Team",
                EmailUtils.getEmailMessage("Name", "localhost", "ABC123"));
    }

    /**
     * Method under test: {@link EmailUtils#getVerificationUrl(String, String)}
     */
    @Test
    void testGetVerificationUrl() {
        // Arrange, Act and Assert
        assertEquals("https://localhost:4200/verify?token=https://example.org/example",
                EmailUtils.getVerificationUrl("https://example.org/example", "https://example.org/example"));
    }
}
