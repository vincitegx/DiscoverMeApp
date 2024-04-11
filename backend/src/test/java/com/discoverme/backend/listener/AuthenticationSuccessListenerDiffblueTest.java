package com.discoverme.backend.listener;

import com.discoverme.backend.user.login.LoginAttemptService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthenticationSuccessListener.class})
@ExtendWith(SpringExtension.class)
class AuthenticationSuccessListenerDiffblueTest {
    @Autowired
    private AuthenticationSuccessListener authenticationSuccessListener;

    @MockBean
    private LoginAttemptService loginAttemptService;

    /**
     * Method under test:
     * {@link AuthenticationSuccessListener#onAuthenticationSuccess(AuthenticationSuccessEvent)}
     */
    @Test
    void testOnAuthenticationSuccess() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     AuthenticationSuccessListener.loginAttemptService

        // Arrange and Act
        authenticationSuccessListener
                .onAuthenticationSuccess(new AuthenticationSuccessEvent(new BearerTokenAuthenticationToken("ABC123")));
    }

    /**
     * Method under test:
     * {@link AuthenticationSuccessListener#onAuthenticationSuccess(AuthenticationSuccessEvent)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testOnAuthenticationSuccess2() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.authentication.event.AuthenticationSuccessEvent.getAuthentication()" because "event" is null
        //       at com.discoverme.backend.listener.AuthenticationSuccessListener.onAuthenticationSuccess(AuthenticationSuccessListener.java:17)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        authenticationSuccessListener.onAuthenticationSuccess(null);
    }
}
