package com.discoverme.backend.user.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discoverme.backend.mail.AltGMailService;
import com.discoverme.backend.mail.EventDto;
import com.discoverme.backend.user.UserRepository;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import com.discoverme.backend.user.verification.EmailVerificationTokenRepository;
import com.discoverme.backend.user.verification.VerificationService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.TemplateEngine;

class RegistrationControllerDiffblueTest {
    /**
     * Method under test:
     * {@link RegistrationController#registerUser(RegistrationRequest)}
     */
    @Test
    void testRegisterUser() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        RegistrationService registrationService = mock(RegistrationService.class);
        RegistrationResponse buildResult = RegistrationResponse.builder()
                .email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
                .build();
        when(registrationService.registerUser(Mockito.<RegistrationRequest>any())).thenReturn(buildResult);
        VerificationService verificationService = mock(VerificationService.class);
        when(verificationService.registerVerificationTokenToDb(Mockito.<RegistrationResponse>any()))
                .thenReturn(new EventDto());
        AltGMailService mailService = mock(AltGMailService.class);
        doNothing().when(mailService).sendHtmlEmail(Mockito.<EventDto>any());
        RegistrationController registrationController = new RegistrationController(registrationService, verificationService,
                mailService);

        // Act
        ResponseEntity<RegistrationResponse> actualRegisterUserResult = registrationController
                .registerUser(new RegistrationRequest("Stage Name", "jane.doe@example.org", "iloveyou"));

        // Assert
        verify(mailService).sendHtmlEmail(isA(EventDto.class));
        verify(registrationService).registerUser(isA(RegistrationRequest.class));
        verify(verificationService).registerVerificationTokenToDb(isA(RegistrationResponse.class));
        assertEquals(201, actualRegisterUserResult.getStatusCodeValue());
        assertTrue(actualRegisterUserResult.hasBody());
        assertTrue(actualRegisterUserResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link RegistrationController#registerAdmin(AdminRegistrationRequest)}
     */
    @Test
    void testRegisterAdmin() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        PasswordValidator passwordValidator = mock(PasswordValidator.class);
        when(passwordValidator.test(Mockito.<String>any())).thenReturn(true);

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setStageName("Stage Name");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.save(Mockito.<Users>any())).thenReturn(users);
        Optional<Users> emptyResult = Optional.empty();
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);
        UserService userService = new UserService(userRepository);
        RegistrationMapper registrationMapper = new RegistrationMapper();
        RegistrationService registrationService = new RegistrationService(passwordValidator, userService,
                registrationMapper, new BCryptPasswordEncoder());

        EmailVerificationTokenRepository emailVerificationTokenRepository = mock(EmailVerificationTokenRepository.class);
        VerificationService verificationService = new VerificationService(emailVerificationTokenRepository,
                new UserService(mock(UserRepository.class)));

        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
        RegistrationController registrationController = new RegistrationController(registrationService, verificationService,
                new AltGMailService(emailSender, new TemplateEngine()));

        // Act
        ResponseEntity<RegistrationResponse> actualRegisterAdminResult = registrationController
                .registerAdmin(new AdminRegistrationRequest("jane.doe@example.org", "iloveyou"));

        // Assert
        verify(userRepository).findByEmail(eq("jane.doe@example.org"));
        verify(passwordValidator).test(eq("iloveyou"));
        verify(userRepository).save(isA(Users.class));
        RegistrationResponse body = actualRegisterAdminResult.getBody();
        assertEquals("Stage Name", body.getStageName());
        assertEquals("jane.doe@example.org", body.getEmail());
        assertEquals(1L, body.getUserId().longValue());
        assertEquals(201, actualRegisterAdminResult.getStatusCodeValue());
        assertTrue(actualRegisterAdminResult.hasBody());
        assertTrue(actualRegisterAdminResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link RegistrationController#registerAdmin(AdminRegistrationRequest)}
     */
    @Test
    void testRegisterAdmin2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        PasswordValidator passwordValidator = mock(PasswordValidator.class);
        when(passwordValidator.test(Mockito.<String>any())).thenReturn(true);

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setStageName("Stage Name");
        UserService userService = mock(UserService.class);
        when(userService.saveUser(Mockito.<Users>any())).thenReturn(users);
        Optional<Users> emptyResult = Optional.empty();
        when(userService.findUserByEmail(Mockito.<String>any())).thenReturn(emptyResult);
        RegistrationMapper registrationMapper = new RegistrationMapper();
        RegistrationService registrationService = new RegistrationService(passwordValidator, userService,
                registrationMapper, new BCryptPasswordEncoder());

        EmailVerificationTokenRepository emailVerificationTokenRepository = mock(EmailVerificationTokenRepository.class);
        VerificationService verificationService = new VerificationService(emailVerificationTokenRepository,
                new UserService(mock(UserRepository.class)));

        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
        RegistrationController registrationController = new RegistrationController(registrationService, verificationService,
                new AltGMailService(emailSender, new TemplateEngine()));

        // Act
        ResponseEntity<RegistrationResponse> actualRegisterAdminResult = registrationController
                .registerAdmin(new AdminRegistrationRequest("jane.doe@example.org", "iloveyou"));

        // Assert
        verify(userService).findUserByEmail(eq("jane.doe@example.org"));
        verify(userService).saveUser(isA(Users.class));
        verify(passwordValidator).test(eq("iloveyou"));
        RegistrationResponse body = actualRegisterAdminResult.getBody();
        assertEquals("Stage Name", body.getStageName());
        assertEquals("jane.doe@example.org", body.getEmail());
        assertEquals(1L, body.getUserId().longValue());
        assertEquals(201, actualRegisterAdminResult.getStatusCodeValue());
        assertTrue(actualRegisterAdminResult.hasBody());
        assertTrue(actualRegisterAdminResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link RegistrationController#registerAdmin(AdminRegistrationRequest)}
     */
    @Test
    void testRegisterAdmin3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        RegistrationService registrationService = mock(RegistrationService.class);
        RegistrationResponse buildResult = RegistrationResponse.builder()
                .email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
                .build();
        when(registrationService.registerAdmin(Mockito.<AdminRegistrationRequest>any())).thenReturn(buildResult);
        EmailVerificationTokenRepository emailVerificationTokenRepository = mock(EmailVerificationTokenRepository.class);
        VerificationService verificationService = new VerificationService(emailVerificationTokenRepository,
                new UserService(mock(UserRepository.class)));

        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
        RegistrationController registrationController = new RegistrationController(registrationService, verificationService,
                new AltGMailService(emailSender, new TemplateEngine()));

        // Act
        ResponseEntity<RegistrationResponse> actualRegisterAdminResult = registrationController
                .registerAdmin(new AdminRegistrationRequest("jane.doe@example.org", "iloveyou"));

        // Assert
        verify(registrationService).registerAdmin(isA(AdminRegistrationRequest.class));
        assertEquals(201, actualRegisterAdminResult.getStatusCodeValue());
        assertTrue(actualRegisterAdminResult.hasBody());
        assertTrue(actualRegisterAdminResult.getHeaders().isEmpty());
    }
}
