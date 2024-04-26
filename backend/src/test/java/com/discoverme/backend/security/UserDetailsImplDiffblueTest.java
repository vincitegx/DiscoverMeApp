package com.discoverme.backend.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discoverme.backend.user.Users;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserDetailsImpl.class})
@ExtendWith(SpringExtension.class)
class UserDetailsImplDiffblueTest {
    @Autowired
    private UserDetailsImpl userDetailsImpl;

    @MockBean
    private Users users;

    /**
     * Method under test: {@link UserDetailsImpl#getAuthorities()}
     */
    @Test
    void testGetAuthorities() {
        // Arrange
        when(users.getRole()).thenReturn("Role");

        // Act
        Collection<? extends GrantedAuthority> actualAuthorities = userDetailsImpl.getAuthorities();

        // Assert
        verify(users).getRole();
        assertEquals(1, actualAuthorities.size());
        assertEquals("ROLE_Role", ((List<? extends GrantedAuthority>) actualAuthorities).get(0).getAuthority());
    }

    /**
     * Method under test: {@link UserDetailsImpl#getPassword()}
     */
    @Test
    void testGetPassword() {
        // Arrange
        when(users.getPassword()).thenReturn("iloveyou");

        // Act
        String actualPassword = userDetailsImpl.getPassword();

        // Assert
        verify(users).getPassword();
        assertEquals("iloveyou", actualPassword);
    }

    /**
     * Method under test: {@link UserDetailsImpl#getUsername()}
     */
    @Test
    void testGetUsername() {
        // Arrange
        when(users.getEmail()).thenReturn("jane.doe@example.org");

        // Act
        String actualUsername = userDetailsImpl.getUsername();

        // Assert
        verify(users).getEmail();
        assertEquals("jane.doe@example.org", actualUsername);
    }

    /**
     * Method under test: {@link UserDetailsImpl#isAccountNonLocked()}
     */
    @Test
    void testIsAccountNonLocked() {
        // Arrange
        when(users.getNonLocked()).thenReturn(true);

        // Act
        boolean actualIsAccountNonLockedResult = userDetailsImpl.isAccountNonLocked();

        // Assert
        verify(users).getNonLocked();
        assertTrue(actualIsAccountNonLockedResult);
    }

    /**
     * Method under test: {@link UserDetailsImpl#isAccountNonLocked()}
     */
    @Test
    void testIsAccountNonLocked2() {
        // Arrange
        when(users.getNonLocked()).thenReturn(false);

        // Act
        boolean actualIsAccountNonLockedResult = userDetailsImpl.isAccountNonLocked();

        // Assert
        verify(users).getNonLocked();
        assertFalse(actualIsAccountNonLockedResult);
    }

    /**
     * Method under test: {@link UserDetailsImpl#isEnabled()}
     */
    @Test
    void testIsEnabled() {
        // Arrange
        when(users.getEnabled()).thenReturn(true);

        // Act
        boolean actualIsEnabledResult = userDetailsImpl.isEnabled();

        // Assert
        verify(users).getEnabled();
        assertTrue(actualIsEnabledResult);
    }

    /**
     * Method under test: {@link UserDetailsImpl#isEnabled()}
     */
    @Test
    void testIsEnabled2() {
        // Arrange
        when(users.getEnabled()).thenReturn(false);

        // Act
        boolean actualIsEnabledResult = userDetailsImpl.isEnabled();

        // Assert
        verify(users).getEnabled();
        assertFalse(actualIsEnabledResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserDetailsImpl#isAccountNonExpired()}
     *   <li>{@link UserDetailsImpl#isCredentialsNonExpired()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
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
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user);

        // Act
        boolean actualIsAccountNonExpiredResult = userDetailsImpl.isAccountNonExpired();

        // Assert
        assertTrue(actualIsAccountNonExpiredResult);
        assertTrue(userDetailsImpl.isCredentialsNonExpired());
    }
}
