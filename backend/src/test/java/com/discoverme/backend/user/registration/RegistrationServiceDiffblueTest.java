package com.discoverme.backend.user.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RegistrationService.class})
@ExtendWith(SpringExtension.class)
class RegistrationServiceDiffblueTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private PasswordValidator passwordValidator;

    @MockBean
    private RegistrationMapper registrationMapper;

    @Autowired
    private RegistrationService registrationService;

    @MockBean
    private UserService userService;

    /**
     * Method under test:
     * {@link RegistrationService#registerUser(RegistrationRequest)}
     */
    @Test
    void testRegisterUser() {
        // Arrange
        when(passwordValidator.test(Mockito.<String>any())).thenReturn(true);

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        Optional<Users> ofResult = Optional.of(users);
        when(userService.findUserByEmail(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RegistrationException.class, () -> registrationService
                .registerUser(new RegistrationRequest("User Name", "jane.doe@example.org", "iloveyou")));
        verify(userService).findUserByEmail(eq("jane.doe@example.org"));
        verify(passwordValidator).test(eq("iloveyou"));
    }

    /**
     * Method under test:
     * {@link RegistrationService#registerUser(RegistrationRequest)}
     */
    @Test
    void testRegisterUser2() {
        // Arrange
        when(passwordValidator.test(Mockito.<String>any())).thenReturn(true);
        when(userService.findUserByEmail(Mockito.<String>any())).thenThrow(new RegistrationException("An error occurred"));

        // Act and Assert
        assertThrows(RegistrationException.class, () -> registrationService
                .registerUser(new RegistrationRequest("User Name", "jane.doe@example.org", "iloveyou")));
        verify(userService).findUserByEmail(eq("jane.doe@example.org"));
        verify(passwordValidator).test(eq("iloveyou"));
    }

    /**
     * Method under test:
     * {@link RegistrationService#registerUser(RegistrationRequest)}
     */
    @Test
    void testRegisterUser3() {
        // Arrange
        when(passwordValidator.test(Mockito.<String>any())).thenReturn(false);

        // Act and Assert
        assertThrows(RegistrationException.class, () -> registrationService
                .registerUser(new RegistrationRequest("User Name", "jane.doe@example.org", "iloveyou")));
        verify(passwordValidator).test(eq("iloveyou"));
    }

    /**
     * Method under test:
     * {@link RegistrationService#registerUser(RegistrationRequest)}
     */
    @Test
    void testRegisterUser4() {
        // Arrange
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        when(passwordValidator.test(Mockito.<String>any())).thenReturn(true);

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        when(userService.saveUser(Mockito.<Users>any())).thenReturn(users);
        Optional<Users> emptyResult = Optional.empty();
        when(userService.findUserByEmail(Mockito.<String>any())).thenReturn(emptyResult);

        Users users2 = new Users();
        users2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users2.setEmail("jane.doe@example.org");
        users2.setEnabled(true);
        users2.setId(1L);
        users2.setNonLocked(true);
        users2.setPassword("iloveyou");
        users2.setRole("Role");
        users2.setUserName("User Name");
        when(registrationMapper.mapRegistrationRequestToUser(Mockito.<RegistrationRequest>any())).thenReturn(users2);
        RegistrationResponse buildResult = RegistrationResponse.builder()
                .email("jane.doe@example.org")
                .UserName("User Name")
                .userId(1L)
                .build();
        when(registrationMapper.mapUserToRegistrationResponse(Mockito.<Users>any())).thenReturn(buildResult);
        RegistrationRequest registerRequest = new RegistrationRequest("User Name", "jane.doe@example.org", "iloveyou");

        // Act
        registrationService.registerUser(registerRequest);

        // Assert
        verify(userService).findUserByEmail(eq("jane.doe@example.org"));
        verify(userService).saveUser(isA(Users.class));
        verify(passwordValidator).test(eq("iloveyou"));
        verify(registrationMapper).mapRegistrationRequestToUser(isA(RegistrationRequest.class));
        verify(registrationMapper).mapUserToRegistrationResponse(isA(Users.class));
        verify(passwordEncoder).encode(isA(CharSequence.class));
        assertEquals("secret", registerRequest.getPassword());
    }

    /**
     * Method under test:
     * {@link RegistrationService#registerAdmin(AdminRegistrationRequest)}
     */
    @Test
    void testRegisterAdmin() {
        // Arrange
        when(passwordValidator.test(Mockito.<String>any())).thenReturn(true);

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        Optional<Users> ofResult = Optional.of(users);
        when(userService.findUserByEmail(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RegistrationException.class,
                () -> registrationService.registerAdmin(new AdminRegistrationRequest("jane.doe@example.org", "iloveyou")));
        verify(userService).findUserByEmail(eq("jane.doe@example.org"));
        verify(passwordValidator).test(eq("iloveyou"));
    }

    /**
     * Method under test:
     * {@link RegistrationService#registerAdmin(AdminRegistrationRequest)}
     */
    @Test
    void testRegisterAdmin2() {
        // Arrange
        when(passwordValidator.test(Mockito.<String>any())).thenReturn(true);
        when(userService.findUserByEmail(Mockito.<String>any())).thenThrow(new RegistrationException("An error occurred"));

        // Act and Assert
        assertThrows(RegistrationException.class,
                () -> registrationService.registerAdmin(new AdminRegistrationRequest("jane.doe@example.org", "iloveyou")));
        verify(userService).findUserByEmail(eq("jane.doe@example.org"));
        verify(passwordValidator).test(eq("iloveyou"));
    }

    /**
     * Method under test:
     * {@link RegistrationService#registerAdmin(AdminRegistrationRequest)}
     */
    @Test
    void testRegisterAdmin3() {
        // Arrange
        when(passwordValidator.test(Mockito.<String>any())).thenReturn(false);

        // Act and Assert
        assertThrows(RegistrationException.class,
                () -> registrationService.registerAdmin(new AdminRegistrationRequest("jane.doe@example.org", "iloveyou")));
        verify(passwordValidator).test(eq("iloveyou"));
    }

    /**
     * Method under test:
     * {@link RegistrationService#registerAdmin(AdminRegistrationRequest)}
     */
    @Test
    void testRegisterAdmin4() {
        // Arrange
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        when(passwordValidator.test(Mockito.<String>any())).thenReturn(true);

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        when(userService.saveUser(Mockito.<Users>any())).thenReturn(users);
        Optional<Users> emptyResult = Optional.empty();
        when(userService.findUserByEmail(Mockito.<String>any())).thenReturn(emptyResult);

        Users users2 = new Users();
        users2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users2.setEmail("jane.doe@example.org");
        users2.setEnabled(true);
        users2.setId(1L);
        users2.setNonLocked(true);
        users2.setPassword("iloveyou");
        users2.setRole("Role");
        users2.setUserName("User Name");
        when(registrationMapper.mapRegistrationRequestToAdmin(Mockito.<AdminRegistrationRequest>any())).thenReturn(users2);
        RegistrationResponse buildResult = RegistrationResponse.builder()
                .email("jane.doe@example.org")
                .UserName("User Name")
                .userId(1L)
                .build();
        when(registrationMapper.mapUserToRegistrationResponse(Mockito.<Users>any())).thenReturn(buildResult);
        AdminRegistrationRequest registerRequest = new AdminRegistrationRequest("jane.doe@example.org", "iloveyou");

        // Act
        registrationService.registerAdmin(registerRequest);

        // Assert
        verify(userService).findUserByEmail(eq("jane.doe@example.org"));
        verify(userService).saveUser(isA(Users.class));
        verify(passwordValidator).test(eq("iloveyou"));
        verify(registrationMapper).mapRegistrationRequestToAdmin(isA(AdminRegistrationRequest.class));
        verify(registrationMapper).mapUserToRegistrationResponse(isA(Users.class));
        verify(passwordEncoder).encode(isA(CharSequence.class));
        assertEquals("secret", registerRequest.getPassword());
    }
}
