package com.discoverme.backend.user.verification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.discoverme.backend.user.Users;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmailVerificationToken.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class EmailVerificationTokenDiffblueTest {
    @Autowired
    private EmailVerificationToken emailVerificationToken;

    /**
     * Method under test:
     * {@link EmailVerificationToken#EmailVerificationToken(String, Users, LocalDateTime)}
     */
    @Test
    void testNewEmailVerificationToken() {
        // Arrange
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setStageName("Stage Name");

        // Act
        EmailVerificationToken actualEmailVerificationToken = new EmailVerificationToken("ABC123", user,
                LocalDate.of(1970, 1, 1).atStartOfDay());

        // Assert
        assertEquals("1970-01-01", actualEmailVerificationToken.getExpiresAt().toLocalDate().toString());
        assertEquals("ABC123", actualEmailVerificationToken.getToken());
        assertEquals("Z", actualEmailVerificationToken.getUser().getCreatedAt().getZone().toString());
    }

    /**
     * Method under test:
     * {@link EmailVerificationToken#EmailVerificationToken(String, Users, LocalDateTime)}
     */
    @Test
    void testNewEmailVerificationToken2() {
        // Arrange
        Users user = mock(Users.class);
        doNothing().when(user).setCreatedAt(Mockito.<ZonedDateTime>any());
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setEnabled(Mockito.<Boolean>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNonLocked(Mockito.<Boolean>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setStageName(Mockito.<String>any());
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setStageName("Stage Name");

        // Act
        EmailVerificationToken actualEmailVerificationToken = new EmailVerificationToken("ABC123", user,
                LocalDate.of(1970, 1, 1).atStartOfDay());

        // Assert
        verify(user).setCreatedAt(isA(ZonedDateTime.class));
        verify(user).setEmail(eq("jane.doe@example.org"));
        verify(user).setEnabled(isA(Boolean.class));
        verify(user).setId(isA(Long.class));
        verify(user).setNonLocked(isA(Boolean.class));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(eq("Role"));
        verify(user).setStageName(eq("Stage Name"));
        assertEquals("1970-01-01", actualEmailVerificationToken.getExpiresAt().toLocalDate().toString());
        assertEquals("ABC123", actualEmailVerificationToken.getToken());
        assertSame(user, actualEmailVerificationToken.getUser());
    }
}
