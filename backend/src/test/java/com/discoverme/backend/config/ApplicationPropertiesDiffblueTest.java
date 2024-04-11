package com.discoverme.backend.config;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ApplicationPropertiesDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ApplicationProperties#getFileUploadDir()}
     *   <li>{@link ApplicationProperties#getFrontendDomain()}
     *   <li>{@link ApplicationProperties#getGoogleClientId()}
     *   <li>{@link ApplicationProperties#getGoogleClientSecret()}
     *   <li>{@link ApplicationProperties#getIntrospectionUri()}
     *   <li>{@link ApplicationProperties#getJwtValidity()}
     *   <li>{@link ApplicationProperties#getMailAddress()}
     *   <li>{@link ApplicationProperties#getPageSize()}
     *   <li>{@link ApplicationProperties#getProjectMaxSize()}
     *   <li>{@link ApplicationProperties#getRefreshTokenCookie()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        ApplicationProperties applicationProperties = new ApplicationProperties();

        // Act
        String actualFileUploadDir = applicationProperties.getFileUploadDir();
        String actualFrontendDomain = applicationProperties.getFrontendDomain();
        String actualGoogleClientId = applicationProperties.getGoogleClientId();
        String actualGoogleClientSecret = applicationProperties.getGoogleClientSecret();
        String actualIntrospectionUri = applicationProperties.getIntrospectionUri();
        Long actualJwtValidity = applicationProperties.getJwtValidity();
        String actualMailAddress = applicationProperties.getMailAddress();
        Integer actualPageSize = applicationProperties.getPageSize();
        Integer actualProjectMaxSize = applicationProperties.getProjectMaxSize();

        // Assert
        assertNull(actualPageSize);
        assertNull(actualProjectMaxSize);
        assertNull(actualJwtValidity);
        assertNull(actualFileUploadDir);
        assertNull(actualFrontendDomain);
        assertNull(actualGoogleClientId);
        assertNull(actualGoogleClientSecret);
        assertNull(actualIntrospectionUri);
        assertNull(actualMailAddress);
        assertNull(applicationProperties.getRefreshTokenCookie());
    }
}
