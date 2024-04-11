package com.discoverme.backend.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceDiffblueTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#findUserByEmail(String)}
     */
    @Test
    void testFindUserByEmail() {
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

        // Act
        Optional<Users> actualFindUserByEmailResult = userService.findUserByEmail("jane.doe@example.org");

        // Assert
        verify(userRepository).findByEmail(eq("jane.doe@example.org"));
        assertSame(ofResult, actualFindUserByEmailResult);
    }

    /**
     * Method under test: {@link UserService#findUserByEmail(String)}
     */
    @Test
    void testFindUserByEmail2() {
        // Arrange
        when(userRepository.findByEmail(Mockito.<String>any())).thenThrow(new UserException("An error occurred"));

        // Act and Assert
        assertThrows(UserException.class, () -> userService.findUserByEmail("jane.doe@example.org"));
        verify(userRepository).findByEmail(eq("jane.doe@example.org"));
    }

    /**
     * Method under test: {@link UserService#saveUser(Users)}
     */
    @Test
    void testSaveUser() {
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
        when(userRepository.save(Mockito.<Users>any())).thenReturn(users);

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
        Users actualSaveUserResult = userService.saveUser(user);

        // Assert
        verify(userRepository).save(isA(Users.class));
        assertSame(users, actualSaveUserResult);
    }

    /**
     * Method under test: {@link UserService#saveUser(Users)}
     */
    @Test
    void testSaveUser2() {
        // Arrange
        when(userRepository.save(Mockito.<Users>any())).thenThrow(new UserException("An error occurred"));

        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setStageName("Stage Name");

        // Act and Assert
        assertThrows(UserException.class, () -> userService.saveUser(user));
        verify(userRepository).save(isA(Users.class));
    }

    /**
     * Method under test: {@link UserService#findByStageName(String)}
     */
    @Test
    void testFindByStageName() {
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
        when(userRepository.findByStageName(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Optional<Users> actualFindByStageNameResult = userService.findByStageName("Stage Name");

        // Assert
        verify(userRepository).findByStageName(eq("Stage Name"));
        assertSame(ofResult, actualFindByStageNameResult);
    }

    /**
     * Method under test: {@link UserService#findByStageName(String)}
     */
    @Test
    void testFindByStageName2() {
        // Arrange
        when(userRepository.findByStageName(Mockito.<String>any())).thenThrow(new UserException("An error occurred"));

        // Act and Assert
        assertThrows(UserException.class, () -> userService.findByStageName("Stage Name"));
        verify(userRepository).findByStageName(eq("Stage Name"));
    }

    /**
     * Method under test: {@link UserService#findById(Long)}
     */
    @Test
    void testFindById() {
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
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Optional<Users> actualFindByIdResult = userService.findById(1L);

        // Assert
        verify(userRepository).findById(isA(Long.class));
        assertSame(ofResult, actualFindByIdResult);
    }

    /**
     * Method under test: {@link UserService#findById(Long)}
     */
    @Test
    void testFindById2() {
        // Arrange
        when(userRepository.findById(Mockito.<Long>any())).thenThrow(new UserException("An error occurred"));

        // Act and Assert
        assertThrows(UserException.class, () -> userService.findById(1L));
        verify(userRepository).findById(isA(Long.class));
    }

    /**
     * Method under test: {@link UserService#findAll(Pageable)}
     */
    @Test
    void testFindAll() {
        // Arrange
        PageImpl<Users> pageImpl = new PageImpl<>(new ArrayList<>());
        when(userRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);

        // Act
        Page<Users> actualFindAllResult = userService.findAll(null);

        // Assert
        verify(userRepository).findAll((Pageable) isNull());
        assertTrue(actualFindAllResult.toList().isEmpty());
        assertSame(pageImpl, actualFindAllResult);
    }

    /**
     * Method under test: {@link UserService#findAll(Pageable)}
     */
    @Test
    void testFindAll2() {
        // Arrange
        when(userRepository.findAll(Mockito.<Pageable>any())).thenThrow(new UserException("An error occurred"));

        // Act and Assert
        assertThrows(UserException.class, () -> userService.findAll(null));
        verify(userRepository).findAll((Pageable) isNull());
    }

    /**
     * Method under test: {@link UserService#getCurrentUser()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCurrentUser() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getPrincipal()" because the return value of "org.springframework.security.core.context.SecurityContext.getAuthentication()" is null
        //       at com.discoverme.backend.user.UserService.getCurrentUser(UserService.java:41)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        userService.getCurrentUser();
    }

    /**
     * Method under test: {@link UserService#findAllByRoleUser(Pageable)}
     */
    @Test
    void testFindAllByRoleUser() {
        // Arrange
        PageImpl<Users> pageImpl = new PageImpl<>(new ArrayList<>());
        when(userRepository.findByRole(Mockito.<String>any(), Mockito.<Pageable>any())).thenReturn(pageImpl);

        // Act
        Page<Users> actualFindAllByRoleUserResult = userService.findAllByRoleUser(null);

        // Assert
        verify(userRepository).findByRole(eq("USER"), isNull());
        assertTrue(actualFindAllByRoleUserResult.toList().isEmpty());
        assertSame(pageImpl, actualFindAllByRoleUserResult);
    }

    /**
     * Method under test: {@link UserService#findAllByRoleUser(Pageable)}
     */
    @Test
    void testFindAllByRoleUser2() {
        // Arrange
        when(userRepository.findByRole(Mockito.<String>any(), Mockito.<Pageable>any()))
                .thenThrow(new UserException("An error occurred"));

        // Act and Assert
        assertThrows(UserException.class, () -> userService.findAllByRoleUser(null));
        verify(userRepository).findByRole(eq("USER"), isNull());
    }

    /**
     * Method under test: {@link UserService#fetchAndEnableUser(Long)}
     */
    @Test
    void testFetchAndEnableUser() {
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

        Users users2 = new Users();
        users2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users2.setEmail("jane.doe@example.org");
        users2.setEnabled(true);
        users2.setId(1L);
        users2.setNonLocked(true);
        users2.setPassword("iloveyou");
        users2.setRole("Role");
        users2.setStageName("Stage Name");
        when(userRepository.save(Mockito.<Users>any())).thenReturn(users2);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        String actualFetchAndEnableUserResult = userService.fetchAndEnableUser(1L);

        // Assert
        verify(userRepository).findById(isA(Long.class));
        verify(userRepository).save(isA(Users.class));
        assertEquals("User account has been activated !!!", actualFetchAndEnableUserResult);
    }

    /**
     * Method under test: {@link UserService#fetchAndEnableUser(Long)}
     */
    @Test
    void testFetchAndEnableUser2() {
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
        when(userRepository.save(Mockito.<Users>any())).thenThrow(new UserException("An error occurred"));
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UserException.class, () -> userService.fetchAndEnableUser(1L));
        verify(userRepository).findById(isA(Long.class));
        verify(userRepository).save(isA(Users.class));
    }

    /**
     * Method under test: {@link UserService#fetchAndEnableUser(Long)}
     */
    @Test
    void testFetchAndEnableUser3() {
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
        Users users2 = mock(Users.class);
        when(users2.getNonLocked()).thenThrow(new UserException("An error occurred"));
        when(users2.getEnabled()).thenReturn(true);
        doNothing().when(users2).setCreatedAt(Mockito.<ZonedDateTime>any());
        doNothing().when(users2).setEmail(Mockito.<String>any());
        doNothing().when(users2).setEnabled(Mockito.<Boolean>any());
        doNothing().when(users2).setId(Mockito.<Long>any());
        doNothing().when(users2).setNonLocked(Mockito.<Boolean>any());
        doNothing().when(users2).setPassword(Mockito.<String>any());
        doNothing().when(users2).setRole(Mockito.<String>any());
        doNothing().when(users2).setStageName(Mockito.<String>any());
        users2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users2.setEmail("jane.doe@example.org");
        users2.setEnabled(true);
        users2.setId(1L);
        users2.setNonLocked(true);
        users2.setPassword("iloveyou");
        users2.setRole("Role");
        users2.setStageName("Stage Name");
        when(userRepository.save(Mockito.<Users>any())).thenReturn(users2);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UserException.class, () -> userService.fetchAndEnableUser(1L));
        verify(users2).getEnabled();
        verify(users2).getNonLocked();
        verify(users2).setCreatedAt(isA(ZonedDateTime.class));
        verify(users2).setEmail(eq("jane.doe@example.org"));
        verify(users2).setEnabled(isA(Boolean.class));
        verify(users2).setId(isA(Long.class));
        verify(users2).setNonLocked(isA(Boolean.class));
        verify(users2).setPassword(eq("iloveyou"));
        verify(users2).setRole(eq("Role"));
        verify(users2).setStageName(eq("Stage Name"));
        verify(userRepository).findById(isA(Long.class));
        verify(userRepository).save(isA(Users.class));
    }

    /**
     * Method under test: {@link UserService#fetchAndEnableUser(Long)}
     */
    @Test
    void testFetchAndEnableUser4() {
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
        Users users2 = mock(Users.class);
        when(users2.getNonLocked()).thenReturn(false);
        when(users2.getEnabled()).thenReturn(true);
        doNothing().when(users2).setCreatedAt(Mockito.<ZonedDateTime>any());
        doNothing().when(users2).setEmail(Mockito.<String>any());
        doNothing().when(users2).setEnabled(Mockito.<Boolean>any());
        doNothing().when(users2).setId(Mockito.<Long>any());
        doNothing().when(users2).setNonLocked(Mockito.<Boolean>any());
        doNothing().when(users2).setPassword(Mockito.<String>any());
        doNothing().when(users2).setRole(Mockito.<String>any());
        doNothing().when(users2).setStageName(Mockito.<String>any());
        users2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users2.setEmail("jane.doe@example.org");
        users2.setEnabled(true);
        users2.setId(1L);
        users2.setNonLocked(true);
        users2.setPassword("iloveyou");
        users2.setRole("Role");
        users2.setStageName("Stage Name");
        when(userRepository.save(Mockito.<Users>any())).thenReturn(users2);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        String actualFetchAndEnableUserResult = userService.fetchAndEnableUser(1L);

        // Assert
        verify(users2).getEnabled();
        verify(users2).getNonLocked();
        verify(users2).setCreatedAt(isA(ZonedDateTime.class));
        verify(users2).setEmail(eq("jane.doe@example.org"));
        verify(users2).setEnabled(isA(Boolean.class));
        verify(users2).setId(isA(Long.class));
        verify(users2).setNonLocked(isA(Boolean.class));
        verify(users2).setPassword(eq("iloveyou"));
        verify(users2).setRole(eq("Role"));
        verify(users2).setStageName(eq("Stage Name"));
        verify(userRepository).findById(isA(Long.class));
        verify(userRepository).save(isA(Users.class));
        assertEquals("Sorry user account could not be activated, Please try again later :(",
                actualFetchAndEnableUserResult);
    }

    /**
     * Method under test: {@link UserService#fetchAndEnableUser(Long)}
     */
    @Test
    void testFetchAndEnableUser5() {
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
        Users users2 = mock(Users.class);
        when(users2.getEnabled()).thenReturn(false);
        doNothing().when(users2).setCreatedAt(Mockito.<ZonedDateTime>any());
        doNothing().when(users2).setEmail(Mockito.<String>any());
        doNothing().when(users2).setEnabled(Mockito.<Boolean>any());
        doNothing().when(users2).setId(Mockito.<Long>any());
        doNothing().when(users2).setNonLocked(Mockito.<Boolean>any());
        doNothing().when(users2).setPassword(Mockito.<String>any());
        doNothing().when(users2).setRole(Mockito.<String>any());
        doNothing().when(users2).setStageName(Mockito.<String>any());
        users2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users2.setEmail("jane.doe@example.org");
        users2.setEnabled(true);
        users2.setId(1L);
        users2.setNonLocked(true);
        users2.setPassword("iloveyou");
        users2.setRole("Role");
        users2.setStageName("Stage Name");
        when(userRepository.save(Mockito.<Users>any())).thenReturn(users2);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        String actualFetchAndEnableUserResult = userService.fetchAndEnableUser(1L);

        // Assert
        verify(users2).getEnabled();
        verify(users2).setCreatedAt(isA(ZonedDateTime.class));
        verify(users2).setEmail(eq("jane.doe@example.org"));
        verify(users2).setEnabled(isA(Boolean.class));
        verify(users2).setId(isA(Long.class));
        verify(users2).setNonLocked(isA(Boolean.class));
        verify(users2).setPassword(eq("iloveyou"));
        verify(users2).setRole(eq("Role"));
        verify(users2).setStageName(eq("Stage Name"));
        verify(userRepository).findById(isA(Long.class));
        verify(userRepository).save(isA(Users.class));
        assertEquals("Sorry user account could not be activated, Please try again later :(",
                actualFetchAndEnableUserResult);
    }

    /**
     * Method under test: {@link UserService#fetchAndEnableUser(Long)}
     */
    @Test
    void testFetchAndEnableUser6() {
        // Arrange
        Optional<Users> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        Users users = mock(Users.class);
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

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.fetchAndEnableUser(1L));
        verify(users).setCreatedAt(isA(ZonedDateTime.class));
        verify(users).setEmail(eq("jane.doe@example.org"));
        verify(users).setEnabled(isA(Boolean.class));
        verify(users).setId(isA(Long.class));
        verify(users).setNonLocked(isA(Boolean.class));
        verify(users).setPassword(eq("iloveyou"));
        verify(users).setRole(eq("Role"));
        verify(users).setStageName(eq("Stage Name"));
        verify(userRepository).findById(isA(Long.class));
    }

    /**
     * Method under test: {@link UserService#fetchAndDisableUser(Long)}
     */
    @Test
    void testFetchAndDisableUser() {
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

        Users users2 = new Users();
        users2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users2.setEmail("jane.doe@example.org");
        users2.setEnabled(true);
        users2.setId(1L);
        users2.setNonLocked(true);
        users2.setPassword("iloveyou");
        users2.setRole("Role");
        users2.setStageName("Stage Name");
        when(userRepository.save(Mockito.<Users>any())).thenReturn(users2);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        String actualFetchAndDisableUserResult = userService.fetchAndDisableUser(1L);

        // Assert
        verify(userRepository).findById(isA(Long.class));
        verify(userRepository).save(isA(Users.class));
        assertEquals("User account has been disabled !!!", actualFetchAndDisableUserResult);
    }

    /**
     * Method under test: {@link UserService#fetchAndDisableUser(Long)}
     */
    @Test
    void testFetchAndDisableUser2() {
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
        when(userRepository.save(Mockito.<Users>any())).thenThrow(new UserException("An error occurred"));
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UserException.class, () -> userService.fetchAndDisableUser(1L));
        verify(userRepository).findById(isA(Long.class));
        verify(userRepository).save(isA(Users.class));
    }

    /**
     * Method under test: {@link UserService#fetchAndDisableUser(Long)}
     */
    @Test
    void testFetchAndDisableUser3() {
        // Arrange
        Optional<Users> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.fetchAndDisableUser(1L));
        verify(userRepository).findById(isA(Long.class));
    }

    /**
     * Method under test: {@link UserService#removeAdmin(Long)}
     */
    @Test
    void testRemoveAdmin() {
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
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UserException.class, () -> userService.removeAdmin(1L));
        verify(userRepository).findById(isA(Long.class));
    }

    /**
     * Method under test: {@link UserService#removeAdmin(Long)}
     */
    @Test
    void testRemoveAdmin2() {
        // Arrange
        Optional<Users> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.removeAdmin(1L));
        verify(userRepository).findById(isA(Long.class));
    }
}
