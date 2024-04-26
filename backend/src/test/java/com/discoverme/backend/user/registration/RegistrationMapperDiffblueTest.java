package com.discoverme.backend.user.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discoverme.backend.user.Users;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RegistrationMapper.class})
@ExtendWith(SpringExtension.class)
class RegistrationMapperDiffblueTest {
    @Autowired
    private RegistrationMapper registrationMapper;

    /**
     * Method under test:
     * {@link RegistrationMapper#mapRegistrationRequestToUser(RegistrationRequest)}
     */
    @Test
    void testMapRegistrationRequestToUser() {
        // Arrange and Act
        Users actualMapRegistrationRequestToUserResult = registrationMapper
                .mapRegistrationRequestToUser(new RegistrationRequest("User Name", "jane.doe@example.org", "iloveyou"));

        // Assert
        assertEquals("User Name", actualMapRegistrationRequestToUserResult.getUserName());
        assertEquals("USER", actualMapRegistrationRequestToUserResult.getRole());
        assertEquals("iloveyou", actualMapRegistrationRequestToUserResult.getPassword());
        assertEquals("jane.doe@example.org", actualMapRegistrationRequestToUserResult.getEmail());
        assertNull(actualMapRegistrationRequestToUserResult.getId());
        assertFalse(actualMapRegistrationRequestToUserResult.getEnabled());
        assertFalse(actualMapRegistrationRequestToUserResult.getNonLocked());
    }

    /**
     * Method under test:
     * {@link RegistrationMapper#mapRegistrationRequestToUser(RegistrationRequest)}
     */
    @Test
    void testMapRegistrationRequestToUser2() {
        // Arrange
        RegistrationRequest registrationRequest = mock(RegistrationRequest.class);
        when(registrationRequest.getEmail()).thenReturn("jane.doe@example.org");
        when(registrationRequest.getPassword()).thenReturn("iloveyou");
        when(registrationRequest.getUserName()).thenReturn("User Name");

        // Act
        Users actualMapRegistrationRequestToUserResult = registrationMapper
                .mapRegistrationRequestToUser(registrationRequest);

        // Assert
        verify(registrationRequest).getEmail();
        verify(registrationRequest).getPassword();
        verify(registrationRequest).getUserName();
        assertEquals("User Name", actualMapRegistrationRequestToUserResult.getUserName());
        assertEquals("USER", actualMapRegistrationRequestToUserResult.getRole());
        assertEquals("iloveyou", actualMapRegistrationRequestToUserResult.getPassword());
        assertEquals("jane.doe@example.org", actualMapRegistrationRequestToUserResult.getEmail());
        assertNull(actualMapRegistrationRequestToUserResult.getId());
        assertFalse(actualMapRegistrationRequestToUserResult.getEnabled());
        assertFalse(actualMapRegistrationRequestToUserResult.getNonLocked());
    }

    /**
     * Method under test:
     * {@link RegistrationMapper#mapUserToRegistrationResponse(Users)}
     */
    @Test
    void testMapUserToRegistrationResponse() {
        // Arrange
        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");

        // Act
        RegistrationResponse actualMapUserToRegistrationResponseResult = registrationMapper
                .mapUserToRegistrationResponse(users);

        // Assert
        assertEquals("User Name", actualMapUserToRegistrationResponseResult.getUserName());
        assertEquals("jane.doe@example.org", actualMapUserToRegistrationResponseResult.getEmail());
        assertEquals(1L, actualMapUserToRegistrationResponseResult.getUserId().longValue());
    }

    /**
     * Method under test:
     * {@link RegistrationMapper#mapUserToRegistrationResponse(Users)}
     */
    @Test
    void testMapUserToRegistrationResponse2() {
        // Arrange
        Users users = mock(Users.class);
        when(users.getId()).thenReturn(1L);
        when(users.getEmail()).thenReturn("jane.doe@example.org");
        when(users.getUserName()).thenReturn("User Name");
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

        // Act
        RegistrationResponse actualMapUserToRegistrationResponseResult = registrationMapper
                .mapUserToRegistrationResponse(users);

        // Assert
        verify(users).getEmail();
        verify(users).getId();
        verify(users).getUserName();
        verify(users).setCreatedAt(isA(ZonedDateTime.class));
        verify(users).setEmail(eq("jane.doe@example.org"));
        verify(users).setEnabled(isA(Boolean.class));
        verify(users).setId(isA(Long.class));
        verify(users).setNonLocked(isA(Boolean.class));
        verify(users).setPassword(eq("iloveyou"));
        verify(users).setRole(eq("Role"));
        verify(users).setUserName(eq("User Name"));
        assertEquals("User Name", actualMapUserToRegistrationResponseResult.getUserName());
        assertEquals("jane.doe@example.org", actualMapUserToRegistrationResponseResult.getEmail());
        assertEquals(1L, actualMapUserToRegistrationResponseResult.getUserId().longValue());
    }

    /**
     * Method under test:
     * {@link RegistrationMapper#mapRegistrationRequestToAdmin(AdminRegistrationRequest)}
     */
    @Test
    void testMapRegistrationRequestToAdmin() {
        // Arrange and Act
        Users actualMapRegistrationRequestToAdminResult = registrationMapper
                .mapRegistrationRequestToAdmin(new AdminRegistrationRequest("jane.doe@example.org", "iloveyou"));

        // Assert
        assertEquals("ADMIN", actualMapRegistrationRequestToAdminResult.getRole());
        assertEquals("Administrator", actualMapRegistrationRequestToAdminResult.getUserName());
        assertEquals("iloveyou", actualMapRegistrationRequestToAdminResult.getPassword());
        assertEquals("jane.doe@example.org", actualMapRegistrationRequestToAdminResult.getEmail());
        assertNull(actualMapRegistrationRequestToAdminResult.getId());
        assertTrue(actualMapRegistrationRequestToAdminResult.getEnabled());
        assertTrue(actualMapRegistrationRequestToAdminResult.getNonLocked());
    }

    /**
     * Method under test:
     * {@link RegistrationMapper#mapRegistrationRequestToAdmin(AdminRegistrationRequest)}
     */
    @Test
    void testMapRegistrationRequestToAdmin2() {
        // Arrange
        AdminRegistrationRequest registrationRequest = mock(AdminRegistrationRequest.class);
        when(registrationRequest.getEmail()).thenReturn("jane.doe@example.org");
        when(registrationRequest.getPassword()).thenReturn("iloveyou");

        // Act
        Users actualMapRegistrationRequestToAdminResult = registrationMapper
                .mapRegistrationRequestToAdmin(registrationRequest);

        // Assert
        verify(registrationRequest).getEmail();
        verify(registrationRequest).getPassword();
        assertEquals("ADMIN", actualMapRegistrationRequestToAdminResult.getRole());
        assertEquals("Administrator", actualMapRegistrationRequestToAdminResult.getUserName());
        assertEquals("iloveyou", actualMapRegistrationRequestToAdminResult.getPassword());
        assertEquals("jane.doe@example.org", actualMapRegistrationRequestToAdminResult.getEmail());
        assertNull(actualMapRegistrationRequestToAdminResult.getId());
        assertTrue(actualMapRegistrationRequestToAdminResult.getEnabled());
        assertTrue(actualMapRegistrationRequestToAdminResult.getNonLocked());
    }
}
