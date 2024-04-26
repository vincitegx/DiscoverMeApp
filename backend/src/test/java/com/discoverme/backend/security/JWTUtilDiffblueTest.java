package com.discoverme.backend.security;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.discoverme.backend.user.UserException;
import com.discoverme.backend.user.Users;
import com.discoverme.backend.user.login.refresh.RefreshToken;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JWTUtil.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class JWTUtilDiffblueTest {
    @Autowired
    private JWTUtil jWTUtil;

    /**
     * Method under test: {@link JWTUtil#generateJwtToken(RefreshToken)}
     */
    @Test
    void testGenerateJwtToken() {
        // Arrange
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        RefreshToken refreshToken = mock(RefreshToken.class);
        when(refreshToken.getUser()).thenReturn(users);
        doNothing().when(refreshToken).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(refreshToken).setExpiresAt(Mockito.<LocalDateTime>any());
        doNothing().when(refreshToken).setId(Mockito.<Long>any());
        doNothing().when(refreshToken).setToken(Mockito.<String>any());
        doNothing().when(refreshToken).setUser(Mockito.<Users>any());
        refreshToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setId(1L);
        refreshToken.setToken("ABC123");
        refreshToken.setUser(user);

        // Act
        jWTUtil.generateJwtToken(refreshToken);

        // Assert
        verify(refreshToken, atLeast(1)).getUser();
        verify(refreshToken).setCreatedAt(isA(LocalDateTime.class));
        verify(refreshToken).setExpiresAt(isA(LocalDateTime.class));
        verify(refreshToken).setId(isA(Long.class));
        verify(refreshToken).setToken(eq("ABC123"));
        verify(refreshToken).setUser(isA(Users.class));
    }

    /**
     * Method under test: {@link JWTUtil#generateJwtToken(RefreshToken)}
     */
    @Test
    void testGenerateJwtToken2() {
        // Arrange
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");
        Users users = mock(Users.class);
        when(users.getEnabled()).thenThrow(new TokenExpiredException("An error occurred",
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(users.getPassword()).thenReturn("iloveyou");
        when(users.getRole()).thenReturn("Role");
        when(users.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(users).setCreatedAt(Mockito.<ZonedDateTime>any());
        doNothing().when(users).setEmail(Mockito.<String>any());
        doNothing().when(users).setEnabled(Mockito.<Boolean>any());
        doNothing().when(users).setId(Mockito.<Long>any());
        doNothing().when(users).setNonLocked(Mockito.<Boolean>any());
        doNothing().when(users).setPassword(Mockito.<String>any());
        doNothing().when(users).setRole(Mockito.<String>any());
        doNothing().when(users).setUserName(Mockito.<String>any());
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        RefreshToken refreshToken = mock(RefreshToken.class);
        when(refreshToken.getUser()).thenReturn(users);
        doNothing().when(refreshToken).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(refreshToken).setExpiresAt(Mockito.<LocalDateTime>any());
        doNothing().when(refreshToken).setId(Mockito.<Long>any());
        doNothing().when(refreshToken).setToken(Mockito.<String>any());
        doNothing().when(refreshToken).setUser(Mockito.<Users>any());
        refreshToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setId(1L);
        refreshToken.setToken("ABC123");
        refreshToken.setUser(user);

        // Act and Assert
        assertThrows(TokenExpiredException.class, () -> jWTUtil.generateJwtToken(refreshToken));
        verify(users).getEmail();
        verify(users).getEnabled();
        verify(users).getPassword();
        verify(users).getRole();
        verify(users).setCreatedAt(isA(ZonedDateTime.class));
        verify(users).setEmail(eq("jane.doe@example.org"));
        verify(users).setEnabled(isA(Boolean.class));
        verify(users).setId(isA(Long.class));
        verify(users).setNonLocked(isA(Boolean.class));
        verify(users).setPassword(eq("iloveyou"));
        verify(users).setRole(eq("Role"));
        verify(users).setUserName(eq("User Name"));
        verify(refreshToken, atLeast(1)).getUser();
        verify(refreshToken).setCreatedAt(isA(LocalDateTime.class));
        verify(refreshToken).setExpiresAt(isA(LocalDateTime.class));
        verify(refreshToken).setId(isA(Long.class));
        verify(refreshToken).setToken(eq("ABC123"));
        verify(refreshToken).setUser(isA(Users.class));
    }

    /**
     * Method under test: {@link JWTUtil#generateJwtToken(RefreshToken)}
     */
    @Test
    void testGenerateJwtToken3() {
        // Arrange
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");
        Users users = mock(Users.class);
        when(users.getEnabled()).thenReturn(false);
        when(users.getNonLocked()).thenReturn(true);
        when(users.getPassword()).thenReturn("iloveyou");
        when(users.getRole()).thenReturn("Role");
        when(users.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(users).setCreatedAt(Mockito.<ZonedDateTime>any());
        doNothing().when(users).setEmail(Mockito.<String>any());
        doNothing().when(users).setEnabled(Mockito.<Boolean>any());
        doNothing().when(users).setId(Mockito.<Long>any());
        doNothing().when(users).setNonLocked(Mockito.<Boolean>any());
        doNothing().when(users).setPassword(Mockito.<String>any());
        doNothing().when(users).setRole(Mockito.<String>any());
        doNothing().when(users).setUserName(Mockito.<String>any());
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        RefreshToken refreshToken = mock(RefreshToken.class);
        when(refreshToken.getUser()).thenReturn(users);
        doNothing().when(refreshToken).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(refreshToken).setExpiresAt(Mockito.<LocalDateTime>any());
        doNothing().when(refreshToken).setId(Mockito.<Long>any());
        doNothing().when(refreshToken).setToken(Mockito.<String>any());
        doNothing().when(refreshToken).setUser(Mockito.<Users>any());
        refreshToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setId(1L);
        refreshToken.setToken("ABC123");
        refreshToken.setUser(user);

        // Act
        jWTUtil.generateJwtToken(refreshToken);

        // Assert
        verify(users, atLeast(1)).getEmail();
        verify(users).getEnabled();
        verify(users).getNonLocked();
        verify(users).getPassword();
        verify(users).getRole();
        verify(users).setCreatedAt(isA(ZonedDateTime.class));
        verify(users).setEmail(eq("jane.doe@example.org"));
        verify(users).setEnabled(isA(Boolean.class));
        verify(users).setId(isA(Long.class));
        verify(users).setNonLocked(isA(Boolean.class));
        verify(users).setPassword(eq("iloveyou"));
        verify(users).setRole(eq("Role"));
        verify(users).setUserName(eq("User Name"));
        verify(refreshToken, atLeast(1)).getUser();
        verify(refreshToken).setCreatedAt(isA(LocalDateTime.class));
        verify(refreshToken).setExpiresAt(isA(LocalDateTime.class));
        verify(refreshToken).setId(isA(Long.class));
        verify(refreshToken).setToken(eq("ABC123"));
        verify(refreshToken).setUser(isA(Users.class));
    }

    /**
     * Method under test: {@link JWTUtil#generateJwtToken(RefreshToken)}
     */
    @Test
    void testGenerateJwtToken4() {
        // Arrange
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");
        Users users = mock(Users.class);
        when(users.getEnabled()).thenReturn(true);
        when(users.getNonLocked()).thenReturn(false);
        when(users.getPassword()).thenReturn("iloveyou");
        when(users.getRole()).thenReturn("Role");
        when(users.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(users).setCreatedAt(Mockito.<ZonedDateTime>any());
        doNothing().when(users).setEmail(Mockito.<String>any());
        doNothing().when(users).setEnabled(Mockito.<Boolean>any());
        doNothing().when(users).setId(Mockito.<Long>any());
        doNothing().when(users).setNonLocked(Mockito.<Boolean>any());
        doNothing().when(users).setPassword(Mockito.<String>any());
        doNothing().when(users).setRole(Mockito.<String>any());
        doNothing().when(users).setUserName(Mockito.<String>any());
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        RefreshToken refreshToken = mock(RefreshToken.class);
        when(refreshToken.getUser()).thenReturn(users);
        doNothing().when(refreshToken).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(refreshToken).setExpiresAt(Mockito.<LocalDateTime>any());
        doNothing().when(refreshToken).setId(Mockito.<Long>any());
        doNothing().when(refreshToken).setToken(Mockito.<String>any());
        doNothing().when(refreshToken).setUser(Mockito.<Users>any());
        refreshToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setId(1L);
        refreshToken.setToken("ABC123");
        refreshToken.setUser(user);

        // Act
        jWTUtil.generateJwtToken(refreshToken);

        // Assert
        verify(users, atLeast(1)).getEmail();
        verify(users).getEnabled();
        verify(users).getNonLocked();
        verify(users).getPassword();
        verify(users).getRole();
        verify(users).setCreatedAt(isA(ZonedDateTime.class));
        verify(users).setEmail(eq("jane.doe@example.org"));
        verify(users).setEnabled(isA(Boolean.class));
        verify(users).setId(isA(Long.class));
        verify(users).setNonLocked(isA(Boolean.class));
        verify(users).setPassword(eq("iloveyou"));
        verify(users).setRole(eq("Role"));
        verify(users).setUserName(eq("User Name"));
        verify(refreshToken, atLeast(1)).getUser();
        verify(refreshToken).setCreatedAt(isA(LocalDateTime.class));
        verify(refreshToken).setExpiresAt(isA(LocalDateTime.class));
        verify(refreshToken).setId(isA(Long.class));
        verify(refreshToken).setToken(eq("ABC123"));
        verify(refreshToken).setUser(isA(Users.class));
    }

    /**
     * Method under test: {@link JWTUtil#generateJwtToken(RefreshToken)}
     */
    @Test
    void testGenerateJwtToken5() {
        // Arrange
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");
        Users users = mock(Users.class);
        when(users.getRole()).thenReturn("");
        when(users.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(users).setCreatedAt(Mockito.<ZonedDateTime>any());
        doNothing().when(users).setEmail(Mockito.<String>any());
        doNothing().when(users).setEnabled(Mockito.<Boolean>any());
        doNothing().when(users).setId(Mockito.<Long>any());
        doNothing().when(users).setNonLocked(Mockito.<Boolean>any());
        doNothing().when(users).setPassword(Mockito.<String>any());
        doNothing().when(users).setRole(Mockito.<String>any());
        doNothing().when(users).setUserName(Mockito.<String>any());
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        RefreshToken refreshToken = mock(RefreshToken.class);
        when(refreshToken.getUser()).thenReturn(users);
        doNothing().when(refreshToken).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(refreshToken).setExpiresAt(Mockito.<LocalDateTime>any());
        doNothing().when(refreshToken).setId(Mockito.<Long>any());
        doNothing().when(refreshToken).setToken(Mockito.<String>any());
        doNothing().when(refreshToken).setUser(Mockito.<Users>any());
        refreshToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setId(1L);
        refreshToken.setToken("ABC123");
        refreshToken.setUser(user);

        // Act and Assert
        assertThrows(UserException.class, () -> jWTUtil.generateJwtToken(refreshToken));
        verify(users).getEmail();
        verify(users).getRole();
        verify(users).setCreatedAt(isA(ZonedDateTime.class));
        verify(users).setEmail(eq("jane.doe@example.org"));
        verify(users).setEnabled(isA(Boolean.class));
        verify(users).setId(isA(Long.class));
        verify(users).setNonLocked(isA(Boolean.class));
        verify(users).setPassword(eq("iloveyou"));
        verify(users).setRole(eq("Role"));
        verify(users).setUserName(eq("User Name"));
        verify(refreshToken, atLeast(1)).getUser();
        verify(refreshToken).setCreatedAt(isA(LocalDateTime.class));
        verify(refreshToken).setExpiresAt(isA(LocalDateTime.class));
        verify(refreshToken).setId(isA(Long.class));
        verify(refreshToken).setToken(eq("ABC123"));
        verify(refreshToken).setUser(isA(Users.class));
    }

    /**
     * Method under test: {@link JWTUtil#generateJwtToken(RefreshToken)}
     */
    @Test
    void testGenerateJwtToken6() {
        // Arrange
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");
        Users users = mock(Users.class);
        when(users.getEnabled()).thenReturn(true);
        when(users.getNonLocked()).thenReturn(true);
        when(users.getPassword()).thenReturn("iloveyou");
        when(users.getRole()).thenReturn("Role");
        when(users.getEmail()).thenReturn("");
        doNothing().when(users).setCreatedAt(Mockito.<ZonedDateTime>any());
        doNothing().when(users).setEmail(Mockito.<String>any());
        doNothing().when(users).setEnabled(Mockito.<Boolean>any());
        doNothing().when(users).setId(Mockito.<Long>any());
        doNothing().when(users).setNonLocked(Mockito.<Boolean>any());
        doNothing().when(users).setPassword(Mockito.<String>any());
        doNothing().when(users).setRole(Mockito.<String>any());
        doNothing().when(users).setUserName(Mockito.<String>any());
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        RefreshToken refreshToken = mock(RefreshToken.class);
        when(refreshToken.getUser()).thenReturn(users);
        doNothing().when(refreshToken).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(refreshToken).setExpiresAt(Mockito.<LocalDateTime>any());
        doNothing().when(refreshToken).setId(Mockito.<Long>any());
        doNothing().when(refreshToken).setToken(Mockito.<String>any());
        doNothing().when(refreshToken).setUser(Mockito.<Users>any());
        refreshToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setId(1L);
        refreshToken.setToken("ABC123");
        refreshToken.setUser(user);

        // Act and Assert
        assertThrows(UserException.class, () -> jWTUtil.generateJwtToken(refreshToken));
        verify(users).getEmail();
        verify(users).getEnabled();
        verify(users).getNonLocked();
        verify(users).getPassword();
        verify(users).getRole();
        verify(users).setCreatedAt(isA(ZonedDateTime.class));
        verify(users).setEmail(eq("jane.doe@example.org"));
        verify(users).setEnabled(isA(Boolean.class));
        verify(users).setId(isA(Long.class));
        verify(users).setNonLocked(isA(Boolean.class));
        verify(users).setPassword(eq("iloveyou"));
        verify(users).setRole(eq("Role"));
        verify(users).setUserName(eq("User Name"));
        verify(refreshToken, atLeast(1)).getUser();
        verify(refreshToken).setCreatedAt(isA(LocalDateTime.class));
        verify(refreshToken).setExpiresAt(isA(LocalDateTime.class));
        verify(refreshToken).setId(isA(Long.class));
        verify(refreshToken).setToken(eq("ABC123"));
        verify(refreshToken).setUser(isA(Users.class));
    }

    /**
     * Method under test: {@link JWTUtil#generateJwtToken(Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateJwtToken7() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getPrincipal()" because "authentication" is null
        //       at com.discoverme.backend.security.JWTUtil.generateJwtToken(JWTUtil.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        jWTUtil.generateJwtToken((Authentication) null);
    }

    /**
     * Method under test: {@link JWTUtil#validateToken(String)}
     */
    @Test
    void testValidateToken() {
        // Arrange, Act and Assert
        assertNull(jWTUtil.validateToken("ABC123"));
        assertNull(jWTUtil.validateToken("foobar_123456789_foobar_123456789_foobar_123456789_foobar_123456789"));
    }

    /**
     * Method under test: {@link JWTUtil#validateOAuthToken(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testValidateOAuthToken() throws TokenExpiredException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException
        //       at com.google.common.base.Preconditions.checkArgument(Preconditions.java:108)
        //       at com.google.api.client.util.Preconditions.checkArgument(Preconditions.java:35)
        //       at com.google.api.client.json.webtoken.JsonWebSignature$Parser.parse(JsonWebSignature.java:544)
        //       at com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.parse(GoogleIdToken.java:53)
        //       at com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier.verify(GoogleIdTokenVerifier.java:187)
        //       at com.discoverme.backend.security.JWTUtil.validateOAuthToken(JWTUtil.java:107)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        jWTUtil.validateOAuthToken("ABC123");
    }
}
