package com.discoverme.backend.user.verification;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discoverme.backend.user.UserException;
import com.discoverme.backend.user.UserRepository;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import com.discoverme.backend.user.registration.RegistrationResponse;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class VerificationServiceDiffblueTest {
    /**
     * Method under test:
     * {@link VerificationService#registerVerificationTokenToDb(RegistrationResponse)}
     */
    @Test
    void testRegisterVerificationTokenToDb() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        UserRepository userRepository = mock(UserRepository.class);
        Optional<Users> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        VerificationService verificationService = new VerificationService(mock(EmailVerificationTokenRepository.class),
                new UserService(userRepository));

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> verificationService
                .registerVerificationTokenToDb(new RegistrationResponse(1L, "Stage Name", "jane.doe@example.org")));
        verify(userRepository).findById(isA(Long.class));
    }

    /**
     * Method under test:
     * {@link VerificationService#requestNewVerificationToken(String)}
     */
    @Test
    void testRequestNewVerificationToken() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setStageName("Stage Name");
        Optional<Users> ofResult = Optional.of(users);
        UserRepository userRepository = mock(UserRepository.class);
        Optional<Users> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UsernameNotFoundException.class,
                () -> (new VerificationService(mock(EmailVerificationTokenRepository.class), new UserService(userRepository)))
                        .requestNewVerificationToken("jane.doe@example.org"));
        verify(userRepository).findByEmail(eq("jane.doe@example.org"));
        verify(userRepository).findById(isA(Long.class));
    }

    /**
     * Method under test:
     * {@link VerificationService#requestNewVerificationToken(String)}
     */
    @Test
    void testRequestNewVerificationToken2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        UserRepository userRepository = mock(UserRepository.class);
        Optional<Users> emptyResult = Optional.empty();
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(UsernameNotFoundException.class,
                () -> (new VerificationService(mock(EmailVerificationTokenRepository.class), new UserService(userRepository)))
                        .requestNewVerificationToken("jane.doe@example.org"));
        verify(userRepository).findByEmail(eq("jane.doe@example.org"));
    }

    /**
     * Method under test:
     * {@link VerificationService#requestNewVerificationToken(String)}
     */
    @Test
    void testRequestNewVerificationToken3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setStageName("Stage Name");
        Optional<Users> ofResult = Optional.of(users);
        UserService userService = mock(UserService.class);
        when(userService.findById(Mockito.<Long>any())).thenThrow(new UserException("An error occurred"));
        when(userService.findUserByEmail(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UserException.class,
                () -> (new VerificationService(mock(EmailVerificationTokenRepository.class), userService))
                        .requestNewVerificationToken("jane.doe@example.org"));
        verify(userService).findById(isA(Long.class));
        verify(userService).findUserByEmail(eq("jane.doe@example.org"));
    }

    /**
     * Method under test: {@link VerificationService#verifyEmail(String)}
     */
    @Test
    void testVerifyEmail() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

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

        EmailVerificationToken emailVerificationToken = new EmailVerificationToken();
        emailVerificationToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        emailVerificationToken.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        emailVerificationToken.setId(1L);
        emailVerificationToken.setToken("ABC123");
        emailVerificationToken.setUser(user);
        Optional<EmailVerificationToken> ofResult = Optional.of(emailVerificationToken);
        EmailVerificationTokenRepository emailVerificationTokenRepository = mock(EmailVerificationTokenRepository.class);
        when(emailVerificationTokenRepository.findByToken(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UserException.class,
                () -> (new VerificationService(emailVerificationTokenRepository, new UserService(mock(UserRepository.class))))
                        .verifyEmail("ABC123"));
        verify(emailVerificationTokenRepository).findByToken(eq("ABC123"));
    }

    /**
     * Method under test: {@link VerificationService#verifyEmail(String)}
     */
    @Test
    void testVerifyEmail2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        EmailVerificationTokenRepository emailVerificationTokenRepository = mock(EmailVerificationTokenRepository.class);
        Optional<EmailVerificationToken> emptyResult = Optional.empty();
        when(emailVerificationTokenRepository.findByToken(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(UserException.class,
                () -> (new VerificationService(emailVerificationTokenRepository, new UserService(mock(UserRepository.class))))
                        .verifyEmail("ABC123"));
        verify(emailVerificationTokenRepository).findByToken(eq("ABC123"));
    }
}
