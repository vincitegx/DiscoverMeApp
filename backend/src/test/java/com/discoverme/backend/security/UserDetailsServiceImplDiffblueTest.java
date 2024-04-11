package com.discoverme.backend.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discoverme.backend.user.UserRepository;
import com.discoverme.backend.user.Users;
import com.discoverme.backend.user.login.LoginAttemptService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserDetailsServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserDetailsServiceImplDiffblueTest {
    @MockBean
    private LoginAttemptService loginAttemptService;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link UserDetailsServiceImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws ExecutionException, UsernameNotFoundException {
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
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        when(loginAttemptService.hasExceededMaxAttempts(Mockito.<String>any())).thenReturn(true);

        // Act
        UserDetails actualLoadUserByUsernameResult = userDetailsServiceImpl.loadUserByUsername("jane.doe@example.org");

        // Assert
        verify(userRepository).findByEmail(eq("jane.doe@example.org"));
        verify(loginAttemptService).hasExceededMaxAttempts(eq("jane.doe@example.org"));
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        assertEquals("jane.doe@example.org", actualLoadUserByUsernameResult.getUsername());
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertFalse(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
    }

    /**
     * Method under test: {@link UserDetailsServiceImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws ExecutionException, UsernameNotFoundException {
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
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        when(loginAttemptService.hasExceededMaxAttempts(Mockito.<String>any()))
                .thenThrow(new ExecutionException("foo", new Throwable()));

        // Act
        UserDetails actualLoadUserByUsernameResult = userDetailsServiceImpl.loadUserByUsername("jane.doe@example.org");

        // Assert
        verify(userRepository).findByEmail(eq("jane.doe@example.org"));
        verify(loginAttemptService).hasExceededMaxAttempts(eq("jane.doe@example.org"));
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        assertEquals("jane.doe@example.org", actualLoadUserByUsernameResult.getUsername());
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
    }

    /**
     * Method under test: {@link UserDetailsServiceImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername3() throws ExecutionException, UsernameNotFoundException {
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
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        when(loginAttemptService.hasExceededMaxAttempts(Mockito.<String>any()))
                .thenThrow(new UsernameNotFoundException("Msg"));

        // Act and Assert
        assertThrows(UsernameNotFoundException.class,
                () -> userDetailsServiceImpl.loadUserByUsername("jane.doe@example.org"));
        verify(userRepository).findByEmail(eq("jane.doe@example.org"));
        verify(loginAttemptService).hasExceededMaxAttempts(eq("jane.doe@example.org"));
    }

    /**
     * Method under test: {@link UserDetailsServiceImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername4() throws UsernameNotFoundException {
        // Arrange
        Users users = mock(Users.class);
        when(users.getEnabled()).thenReturn(true);
        when(users.getNonLocked()).thenReturn(false);
        when(users.getEmail()).thenReturn("jane.doe@example.org");
        when(users.getPassword()).thenReturn("iloveyou");
        when(users.getRole()).thenReturn("Role");
        doNothing().when(users).setCreatedAt(Mockito.<ZonedDateTime>any());
        doNothing().when(users).setEmail(Mockito.<String>any());
        doNothing().when(users).setEnabled(Mockito.<Boolean>any());
        doNothing().when(users).setId(Mockito.<Long>any());
        doNothing().when(users).setNonLocked(Mockito.<Boolean>any());
        doNothing().when(users).setPassword(Mockito.<String>any());
        doNothing().when(users).setRole(Mockito.<String>any());
        doNothing().when(users).setStageName(Mockito.<String>any());
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setStageName("Stage Name");
        Optional<Users> ofResult = Optional.of(users);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        doNothing().when(loginAttemptService).evictUserFromLoginAttemptCache(Mockito.<String>any());

        // Act
        UserDetails actualLoadUserByUsernameResult = userDetailsServiceImpl.loadUserByUsername("jane.doe@example.org");

        // Assert
        verify(userRepository).findByEmail(eq("jane.doe@example.org"));
        verify(users, atLeast(1)).getEmail();
        verify(users).getEnabled();
        verify(users, atLeast(1)).getNonLocked();
        verify(users).getPassword();
        verify(users).getRole();
        verify(users).setCreatedAt(isA(ZonedDateTime.class));
        verify(users).setEmail(eq("jane.doe@example.org"));
        verify(users).setEnabled(isA(Boolean.class));
        verify(users).setId(isA(Long.class));
        verify(users).setNonLocked(isA(Boolean.class));
        verify(users).setPassword(eq("iloveyou"));
        verify(users).setRole(eq("Role"));
        verify(users).setStageName(eq("Stage Name"));
        verify(loginAttemptService).evictUserFromLoginAttemptCache(eq("jane.doe@example.org"));
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        assertEquals("jane.doe@example.org", actualLoadUserByUsernameResult.getUsername());
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertFalse(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
    }

    /**
     * Method under test: {@link UserDetailsServiceImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername5() throws UsernameNotFoundException {
        // Arrange
        Optional<Users> emptyResult = Optional.empty();
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(UsernameNotFoundException.class,
                () -> userDetailsServiceImpl.loadUserByUsername("jane.doe@example.org"));
        verify(userRepository).findByEmail(eq("jane.doe@example.org"));
    }

    /**
     * Method under test: {@link UserDetailsServiceImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername6() throws UsernameNotFoundException {
        // Arrange
        Users users = mock(Users.class);
        when(users.getNonLocked()).thenReturn(false);
        when(users.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(users).setCreatedAt(Mockito.<ZonedDateTime>any());
        doNothing().when(users).setEmail(Mockito.<String>any());
        doNothing().when(users).setEnabled(Mockito.<Boolean>any());
        doNothing().when(users).setId(Mockito.<Long>any());
        doNothing().when(users).setNonLocked(Mockito.<Boolean>any());
        doNothing().when(users).setPassword(Mockito.<String>any());
        doNothing().when(users).setRole(Mockito.<String>any());
        doNothing().when(users).setStageName(Mockito.<String>any());
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setStageName("Stage Name");
        Optional<Users> ofResult = Optional.of(users);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        doThrow(new UsernameNotFoundException("Msg")).when(loginAttemptService)
                .evictUserFromLoginAttemptCache(Mockito.<String>any());

        // Act and Assert
        assertThrows(UsernameNotFoundException.class,
                () -> userDetailsServiceImpl.loadUserByUsername("jane.doe@example.org"));
        verify(userRepository).findByEmail(eq("jane.doe@example.org"));
        verify(users).getEmail();
        verify(users).getNonLocked();
        verify(users).setCreatedAt(isA(ZonedDateTime.class));
        verify(users).setEmail(eq("jane.doe@example.org"));
        verify(users).setEnabled(isA(Boolean.class));
        verify(users).setId(isA(Long.class));
        verify(users).setNonLocked(isA(Boolean.class));
        verify(users).setPassword(eq("iloveyou"));
        verify(users).setRole(eq("Role"));
        verify(users).setStageName(eq("Stage Name"));
        verify(loginAttemptService).evictUserFromLoginAttemptCache(eq("jane.doe@example.org"));
    }
}
