package com.discoverme.backend.user.social;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.discoverme.backend.social.Socials;
import com.discoverme.backend.user.Users;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {UserSocials.class})
@ExtendWith(SpringExtension.class)
class UserSocialsDiffblueTest {
    @Autowired
    private UserSocials userSocials;

    /**
     * Method under test: {@link UserSocials#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new UserSocials()).canEqual("Other"));
    }

    /**
     * Method under test: {@link UserSocials#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        UserSocials userSocials2 = new UserSocials();

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setStageName("Stage Name");

        UserSocials userSocials3 = new UserSocials();
        userSocials3.setAccessToken("ABC123");
        userSocials3.setId(1L);
        userSocials3.setSocial(social);
        userSocials3.setUser(user);
        userSocials3.setUserName("janedoe");

        // Act and Assert
        assertTrue(userSocials2.canEqual(userSocials3));
    }

    /**
     * Method under test: {@link UserSocials#canEqual(Object)}
     */
    @Test
    void testCanEqual3() {
        // Arrange
        UserSocials userSocials2 = new UserSocials();
        Socials social = mock(Socials.class);
        doNothing().when(social).setId(Mockito.<Long>any());
        doNothing().when(social).setName(Mockito.<String>any());
        social.setId(1L);
        social.setName("Name");

        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setStageName("Stage Name");

        UserSocials userSocials3 = new UserSocials();
        userSocials3.setAccessToken("ABC123");
        userSocials3.setId(1L);
        userSocials3.setSocial(social);
        userSocials3.setUser(user);
        userSocials3.setUserName("janedoe");

        // Act
        boolean actualCanEqualResult = userSocials2.canEqual(userSocials3);

        // Assert
        verify(social).setId(isA(Long.class));
        verify(social).setName(eq("Name"));
        assertTrue(actualCanEqualResult);
    }

    /**
     * Method under test: {@link UserSocials#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setStageName("Stage Name");

        UserSocials userSocials = new UserSocials();
        userSocials.setAccessToken("ABC123");
        userSocials.setId(1L);
        userSocials.setSocial(social);
        userSocials.setUser(user);
        userSocials.setUserName("janedoe");

        // Act and Assert
        assertNotEquals(userSocials, null);
    }

    /**
     * Method under test: {@link UserSocials#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setStageName("Stage Name");

        UserSocials userSocials = new UserSocials();
        userSocials.setAccessToken("ABC123");
        userSocials.setId(1L);
        userSocials.setSocial(social);
        userSocials.setUser(user);
        userSocials.setUserName("janedoe");

        // Act and Assert
        assertNotEquals(userSocials, "Different type to UserSocials");
    }

    /**
     * Method under test: {@link UserSocials#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setStageName("Stage Name");

        UserSocials userSocials = new UserSocials();
        userSocials.setAccessToken("ExampleToken");
        userSocials.setId(1L);
        userSocials.setSocial(social);
        userSocials.setUser(user);
        userSocials.setUserName("janedoe");

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");

        UserSocials userSocials2 = new UserSocials();
        userSocials2.setAccessToken("ABC123");
        userSocials2.setId(1L);
        userSocials2.setSocial(social2);
        userSocials2.setUser(user2);
        userSocials2.setUserName("janedoe");

        // Act and Assert
        assertNotEquals(userSocials, userSocials2);
    }

    /**
     * Method under test: {@link UserSocials#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setStageName("Stage Name");

        UserSocials userSocials = new UserSocials();
        userSocials.setAccessToken(null);
        userSocials.setId(1L);
        userSocials.setSocial(social);
        userSocials.setUser(user);
        userSocials.setUserName("janedoe");

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");

        UserSocials userSocials2 = new UserSocials();
        userSocials2.setAccessToken("ABC123");
        userSocials2.setId(1L);
        userSocials2.setSocial(social2);
        userSocials2.setUser(user2);
        userSocials2.setUserName("janedoe");

        // Act and Assert
        assertNotEquals(userSocials, userSocials2);
    }

    /**
     * Method under test: {@link UserSocials#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setStageName("Stage Name");

        UserSocials userSocials = new UserSocials();
        userSocials.setAccessToken("ABC123");
        userSocials.setId(2L);
        userSocials.setSocial(social);
        userSocials.setUser(user);
        userSocials.setUserName("janedoe");

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");

        UserSocials userSocials2 = new UserSocials();
        userSocials2.setAccessToken("ABC123");
        userSocials2.setId(1L);
        userSocials2.setSocial(social2);
        userSocials2.setUser(user2);
        userSocials2.setUserName("janedoe");

        // Act and Assert
        assertNotEquals(userSocials, userSocials2);
    }

    /**
     * Method under test: {@link UserSocials#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setStageName("Stage Name");

        UserSocials userSocials = new UserSocials();
        userSocials.setAccessToken("ABC123");
        userSocials.setId(null);
        userSocials.setSocial(social);
        userSocials.setUser(user);
        userSocials.setUserName("janedoe");

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");

        UserSocials userSocials2 = new UserSocials();
        userSocials2.setAccessToken("ABC123");
        userSocials2.setId(1L);
        userSocials2.setSocial(social2);
        userSocials2.setUser(user2);
        userSocials2.setUserName("janedoe");

        // Act and Assert
        assertNotEquals(userSocials, userSocials2);
    }

    /**
     * Method under test: {@link UserSocials#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        Socials social = mock(Socials.class);
        doNothing().when(social).setId(Mockito.<Long>any());
        doNothing().when(social).setName(Mockito.<String>any());
        social.setId(1L);
        social.setName("Name");

        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setStageName("Stage Name");

        UserSocials userSocials = new UserSocials();
        userSocials.setAccessToken("ABC123");
        userSocials.setId(1L);
        userSocials.setSocial(social);
        userSocials.setUser(user);
        userSocials.setUserName("janedoe");

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");

        UserSocials userSocials2 = new UserSocials();
        userSocials2.setAccessToken("ABC123");
        userSocials2.setId(1L);
        userSocials2.setSocial(social2);
        userSocials2.setUser(user2);
        userSocials2.setUserName("janedoe");

        // Act and Assert
        assertNotEquals(userSocials, userSocials2);
    }

    /**
     * Method under test: {@link UserSocials#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        Socials social = mock(Socials.class);
        doNothing().when(social).setId(Mockito.<Long>any());
        doNothing().when(social).setName(Mockito.<String>any());
        social.setId(1L);
        social.setName("Name");
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

        UserSocials userSocials = new UserSocials();
        userSocials.setAccessToken("ABC123");
        userSocials.setId(1L);
        userSocials.setSocial(social);
        userSocials.setUser(user);
        userSocials.setUserName("janedoe");

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");

        UserSocials userSocials2 = new UserSocials();
        userSocials2.setAccessToken("ABC123");
        userSocials2.setId(1L);
        userSocials2.setSocial(social2);
        userSocials2.setUser(user2);
        userSocials2.setUserName("janedoe");

        // Act and Assert
        assertNotEquals(userSocials, userSocials2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserSocials#equals(Object)}
     *   <li>{@link UserSocials#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setStageName("Stage Name");

        UserSocials userSocials = new UserSocials();
        userSocials.setAccessToken("ABC123");
        userSocials.setId(1L);
        userSocials.setSocial(social);
        userSocials.setUser(user);
        userSocials.setUserName("janedoe");

        // Act and Assert
        assertEquals(userSocials, userSocials);
        int expectedHashCodeResult = userSocials.hashCode();
        assertEquals(expectedHashCodeResult, userSocials.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserSocials#equals(Object)}
     *   <li>{@link UserSocials#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setStageName("Stage Name");

        UserSocials userSocials = new UserSocials();
        userSocials.setAccessToken("ABC123");
        userSocials.setId(1L);
        userSocials.setSocial(social);
        userSocials.setUser(user);
        userSocials.setUserName("janedoe");

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");

        UserSocials userSocials2 = new UserSocials();
        userSocials2.setAccessToken("ABC123");
        userSocials2.setId(1L);
        userSocials2.setSocial(social2);
        userSocials2.setUser(user2);
        userSocials2.setUserName("janedoe");

        // Act and Assert
        assertEquals(userSocials, userSocials2);
        int expectedHashCodeResult = userSocials.hashCode();
        assertEquals(expectedHashCodeResult, userSocials2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserSocials#UserSocials()}
     *   <li>{@link UserSocials#setAccessToken(String)}
     *   <li>{@link UserSocials#setId(Long)}
     *   <li>{@link UserSocials#setSocial(Socials)}
     *   <li>{@link UserSocials#setUser(Users)}
     *   <li>{@link UserSocials#setUserName(String)}
     *   <li>{@link UserSocials#toString()}
     *   <li>{@link UserSocials#getAccessToken()}
     *   <li>{@link UserSocials#getId()}
     *   <li>{@link UserSocials#getSocial()}
     *   <li>{@link UserSocials#getUser()}
     *   <li>{@link UserSocials#getUserName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        UserSocials actualUserSocials = new UserSocials();
        actualUserSocials.setAccessToken("ABC123");
        actualUserSocials.setId(1L);
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        actualUserSocials.setSocial(social);
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setStageName("Stage Name");
        actualUserSocials.setUser(user);
        actualUserSocials.setUserName("janedoe");
        String actualToStringResult = actualUserSocials.toString();
        String actualAccessToken = actualUserSocials.getAccessToken();
        Long actualId = actualUserSocials.getId();
        Socials actualSocial = actualUserSocials.getSocial();
        Users actualUser = actualUserSocials.getUser();

        // Assert that nothing has changed
        assertEquals("ABC123", actualAccessToken);
        assertEquals(
                "UserSocials(id=1, user=Users(id=1, stageName=Stage Name, email=jane.doe@example.org, password=iloveyou,"
                        + " role=Role, createdAt=1970-01-01T00:00Z, nonLocked=true, enabled=true), social=Socials(id=1, name=Name),"
                        + " userName=janedoe, accessToken=ABC123)",
                actualToStringResult);
        assertEquals("janedoe", actualUserSocials.getUserName());
        assertEquals(1L, actualId.longValue());
        assertSame(social, actualSocial);
        assertSame(user, actualUser);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserSocials#UserSocials(Long, Users, Socials, String, String)}
     *   <li>{@link UserSocials#setAccessToken(String)}
     *   <li>{@link UserSocials#setId(Long)}
     *   <li>{@link UserSocials#setSocial(Socials)}
     *   <li>{@link UserSocials#setUser(Users)}
     *   <li>{@link UserSocials#setUserName(String)}
     *   <li>{@link UserSocials#toString()}
     *   <li>{@link UserSocials#getAccessToken()}
     *   <li>{@link UserSocials#getId()}
     *   <li>{@link UserSocials#getSocial()}
     *   <li>{@link UserSocials#getUser()}
     *   <li>{@link UserSocials#getUserName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
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

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        // Act
        UserSocials actualUserSocials = new UserSocials(1L, user, social, "janedoe", "ABC123");
        actualUserSocials.setAccessToken("ABC123");
        actualUserSocials.setId(1L);
        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        actualUserSocials.setSocial(social2);
        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");
        actualUserSocials.setUser(user2);
        actualUserSocials.setUserName("janedoe");
        String actualToStringResult = actualUserSocials.toString();
        String actualAccessToken = actualUserSocials.getAccessToken();
        Long actualId = actualUserSocials.getId();
        Socials actualSocial = actualUserSocials.getSocial();
        Users actualUser = actualUserSocials.getUser();

        // Assert that nothing has changed
        assertEquals("ABC123", actualAccessToken);
        assertEquals(
                "UserSocials(id=1, user=Users(id=1, stageName=Stage Name, email=jane.doe@example.org, password=iloveyou,"
                        + " role=Role, createdAt=1970-01-01T00:00Z, nonLocked=true, enabled=true), social=Socials(id=1, name=Name),"
                        + " userName=janedoe, accessToken=ABC123)",
                actualToStringResult);
        assertEquals("janedoe", actualUserSocials.getUserName());
        assertEquals(1L, actualId.longValue());
        assertEquals(social, actualSocial);
        assertEquals(user, actualUser);
        assertSame(social2, actualSocial);
        assertSame(user2, actualUser);
    }
}
