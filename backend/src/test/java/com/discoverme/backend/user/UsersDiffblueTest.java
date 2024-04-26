package com.discoverme.backend.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UsersDiffblueTest {
    /**
     * Method under test: {@link Users#equals(Object)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testEquals4() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Long.equals(Object)" because "<local3>.id" is null
        //       at com.discoverme.backend.user.Users.equals(Users.java:52)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(null);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");

        Users users2 = new Users();
        users2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users2.setEmail("jane.doe@example.org");
        users2.setEnabled(true);
        users2.setId(1L);
        users2.setNonLocked(true);
        users2.setPassword("iloveyou");
        users2.setRole("Role");
        users2.setUserName("User Name");

        // Act and Assert
        assertThrows(NullPointerException.class, () -> users.equals(users2));
    }

    /**
     * Method under test: {@link Users#equals(Object)}
     */
    @Test
    void testEquals() {
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

        // Act and Assert
        assertNotEquals(users, null);
    }

    /**
     * Method under test: {@link Users#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(2L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");

        Users users2 = new Users();
        users2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users2.setEmail("jane.doe@example.org");
        users2.setEnabled(true);
        users2.setId(1L);
        users2.setNonLocked(true);
        users2.setPassword("iloveyou");
        users2.setRole("Role");
        users2.setUserName("User Name");

        // Act and Assert
        assertNotEquals(users, users2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Users#equals(Object)}
     *   <li>{@link Users#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
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

        // Act and Assert
        assertEquals(users, users);
        int expectedHashCodeResult = users.hashCode();
        assertEquals(expectedHashCodeResult, users.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Users#equals(Object)}
     *   <li>{@link Users#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
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

        Users users2 = new Users();
        users2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users2.setEmail("jane.doe@example.org");
        users2.setEnabled(true);
        users2.setId(1L);
        users2.setNonLocked(true);
        users2.setPassword("iloveyou");
        users2.setRole("Role");
        users2.setUserName("User Name");

        // Act and Assert
        assertEquals(users, users2);
        int expectedHashCodeResult = users.hashCode();
        assertEquals(expectedHashCodeResult, users2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Users#equals(Object)}
     *   <li>{@link Users#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode3() {
        // Arrange
        Users.UsersBuilder usersBuilder = mock(Users.UsersBuilder.class);
        when(usersBuilder.createdAt(Mockito.<ZonedDateTime>any())).thenReturn(Users.builder());
        Users buildResult = usersBuilder.createdAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC))
                .email("jane.doe@example.org")
                .enabled(true)
                .id(1L)
                .nonLocked(true)
                .password("iloveyou")
                .role("Role")
                .UserName("User Name")
                .build();
        buildResult.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        buildResult.setEmail("jane.doe@example.org");
        buildResult.setEnabled(true);
        buildResult.setId(1L);
        buildResult.setNonLocked(true);
        buildResult.setPassword("iloveyou");
        buildResult.setRole("Role");
        buildResult.setUserName("User Name");

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");

        // Act and Assert
        assertEquals(buildResult, users);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, users.hashCode());
    }

    /**
     * Method under test: {@link Users#equals(Object)}
     */
    @Test
    void testEquals2() {
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

        // Act and Assert
        assertNotEquals(users, "Different type to Users");
    }
}
