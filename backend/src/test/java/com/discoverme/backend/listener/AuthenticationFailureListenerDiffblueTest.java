package com.discoverme.backend.listener;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.discoverme.backend.user.login.LoginAttemptService;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthenticationFailureListener.class})
@ExtendWith(SpringExtension.class)
class AuthenticationFailureListenerDiffblueTest {
    @Autowired
    private AuthenticationFailureListener authenticationFailureListener;

    @MockBean
    private LoginAttemptService loginAttemptService;

    /**
     * Method under test:
     * {@link AuthenticationFailureListener#onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent)}
     */
    @Test
    void testOnAuthenticationFailure() throws ExecutionException {
        // Arrange
        doNothing().when(loginAttemptService).addUserToLoginAttemptCache(Mockito.<String>any());
        BearerTokenAuthenticationToken authentication = new BearerTokenAuthenticationToken("ABC123");

        // Act
        authenticationFailureListener.onAuthenticationFailure(
                new AuthenticationFailureBadCredentialsEvent(authentication, new AccountExpiredException("Msg")));

        // Assert that nothing has changed
        verify(loginAttemptService).addUserToLoginAttemptCache(eq("ABC123"));
    }

    /**
     * Method under test:
     * {@link AuthenticationFailureListener#onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent)}
     */
    @Test
    void testOnAuthenticationFailure2() throws ExecutionException {
        // Arrange
        doThrow(new ExecutionException("foo", new Throwable())).when(loginAttemptService)
                .addUserToLoginAttemptCache(Mockito.<String>any());
        BearerTokenAuthenticationToken authentication = new BearerTokenAuthenticationToken("ABC123");

        // Act and Assert
        assertThrows(ExecutionException.class, () -> authenticationFailureListener.onAuthenticationFailure(
                new AuthenticationFailureBadCredentialsEvent(authentication, new AccountExpiredException("Msg"))));
        verify(loginAttemptService).addUserToLoginAttemptCache(eq("ABC123"));
    }
}
