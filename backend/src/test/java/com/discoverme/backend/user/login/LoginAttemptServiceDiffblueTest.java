package com.discoverme.backend.user.login;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LoginAttemptService.class})
@ExtendWith(SpringExtension.class)
class LoginAttemptServiceDiffblueTest {
    @Autowired
    private LoginAttemptService loginAttemptService;

    /**
     * Method under test:
     * {@link LoginAttemptService#evictUserFromLoginAttemptCache(String)}
     */
    @Test
    void testEvictUserFromLoginAttemptCache() throws ExecutionException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        LoginAttemptService loginAttemptService = new LoginAttemptService();

        // Act
        loginAttemptService.evictUserFromLoginAttemptCache("janedoe");

        // Assert
        assertFalse(loginAttemptService.hasExceededMaxAttempts("janedoe"));
    }

    /**
     * Method under test:
     * {@link LoginAttemptService#evictUserFromLoginAttemptCache(String)}
     */
    @Test
    void testEvictUserFromLoginAttemptCache2() throws ExecutionException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        LoginAttemptService loginAttemptService = new LoginAttemptService();
        loginAttemptService.addUserToLoginAttemptCache("janedoe");

        // Act
        loginAttemptService.evictUserFromLoginAttemptCache("janedoe");

        // Assert
        assertFalse(loginAttemptService.hasExceededMaxAttempts("janedoe"));
    }

    /**
     * Method under test:
     * {@link LoginAttemptService#evictUserFromLoginAttemptCache(String)}
     */
    @Test
    void testEvictUserFromLoginAttemptCache3() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     LoginAttemptService.loginAttemptCache

        // Arrange and Act
        loginAttemptService.evictUserFromLoginAttemptCache("janedoe");
    }

    /**
     * Method under test:
     * {@link LoginAttemptService#addUserToLoginAttemptCache(String)}
     */
    @Test
    void testAddUserToLoginAttemptCache() throws ExecutionException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        LoginAttemptService loginAttemptService = new LoginAttemptService();

        // Act
        loginAttemptService.addUserToLoginAttemptCache("janedoe");

        // Assert
        assertFalse(loginAttemptService.hasExceededMaxAttempts("janedoe"));
    }

    /**
     * Method under test:
     * {@link LoginAttemptService#addUserToLoginAttemptCache(String)}
     */
    @Test
    void testAddUserToLoginAttemptCache2() throws ExecutionException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        LoginAttemptService loginAttemptService = new LoginAttemptService();
        loginAttemptService.addUserToLoginAttemptCache("janedoe");

        // Act
        loginAttemptService.addUserToLoginAttemptCache("janedoe");

        // Assert
        assertFalse(loginAttemptService.hasExceededMaxAttempts("janedoe"));
    }

    /**
     * Method under test:
     * {@link LoginAttemptService#addUserToLoginAttemptCache(String)}
     */
    @Test
    void testAddUserToLoginAttemptCache3() throws ExecutionException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     LoginAttemptService.loginAttemptCache

        // Arrange and Act
        loginAttemptService.addUserToLoginAttemptCache("janedoe");
    }

    /**
     * Method under test: {@link LoginAttemptService#hasExceededMaxAttempts(String)}
     */
    @Test
    void testHasExceededMaxAttempts() throws ExecutionException {
        // Arrange, Act and Assert
        assertFalse(loginAttemptService.hasExceededMaxAttempts("janedoe"));
        assertFalse(loginAttemptService.hasExceededMaxAttempts("424242"));
    }
}
