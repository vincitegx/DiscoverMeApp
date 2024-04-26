package com.discoverme.backend.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.discoverme.backend.project.calender.Calender;
import com.discoverme.backend.social.Socials;
import com.discoverme.backend.user.Users;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {Project.class})
@ExtendWith(SpringExtension.class)
class ProjectDiffblueTest {
    @Autowired
    private Project project;

    /**
     * Method under test: {@link Project#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new Project()).canEqual("Other"));
    }

    /**
     * Method under test: {@link Project#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        Project project2 = new Project();

        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user.setUserName("User Name");

        Project project3 = new Project();
        project3.setCalender(calender);
        project3.setContentUri("Not all who wander are lost");
        project3.setId(1L);
        project3.setSocial(social);
        project3.setSongTitle("Dr");
        project3.setSongUri("Song Uri");
        project3.setSupportCount(3);
        project3.setUrl("https://example.org/example");
        project3.setUser(user);

        // Act and Assert
        assertTrue(project2.canEqual(project3));
    }

    /**
     * Method under test: {@link Project#canEqual(Object)}
     */
    @Test
    void testCanEqual3() {
        // Arrange
        Project project2 = new Project();
        Calender calender = mock(Calender.class);
        doNothing().when(calender).setEndDate(Mockito.<Date>any());
        doNothing().when(calender).setId(Mockito.<Long>any());
        doNothing().when(calender).setStartDate(Mockito.<Date>any());
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user.setUserName("User Name");

        Project project3 = new Project();
        project3.setCalender(calender);
        project3.setContentUri("Not all who wander are lost");
        project3.setId(1L);
        project3.setSocial(social);
        project3.setSongTitle("Dr");
        project3.setSongUri("Song Uri");
        project3.setSupportCount(3);
        project3.setUrl("https://example.org/example");
        project3.setUser(user);

        // Act
        boolean actualCanEqualResult = project2.canEqual(project3);

        // Assert
        verify(calender).setEndDate(isA(Date.class));
        verify(calender).setId(isA(Long.class));
        verify(calender).setStartDate(isA(Date.class));
        assertTrue(actualCanEqualResult);
    }

    /**
     * Method under test: {@link Project#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user.setUserName("User Name");

        Project project = new Project();
        project.setCalender(calender);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        // Act and Assert
        assertNotEquals(project, null);
    }

    /**
     * Method under test: {@link Project#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user.setUserName("User Name");

        Project project = new Project();
        project.setCalender(calender);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        // Act and Assert
        assertNotEquals(project, "Different type to Project");
    }

    /**
     * Method under test: {@link Project#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        Calender calender = mock(Calender.class);
        doNothing().when(calender).setEndDate(Mockito.<Date>any());
        doNothing().when(calender).setId(Mockito.<Long>any());
        doNothing().when(calender).setStartDate(Mockito.<Date>any());
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user.setUserName("User Name");

        Project project = new Project();
        project.setCalender(calender);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user2.setUserName("User Name");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Not all who wander are lost");
        project2.setId(1L);
        project2.setSocial(social2);
        project2.setSongTitle("Dr");
        project2.setSongUri("Song Uri");
        project2.setSupportCount(3);
        project2.setUrl("https://example.org/example");
        project2.setUser(user2);

        // Act and Assert
        assertNotEquals(project, project2);
    }

    /**
     * Method under test: {@link Project#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        Calender calender = mock(Calender.class);
        doNothing().when(calender).setEndDate(Mockito.<Date>any());
        doNothing().when(calender).setId(Mockito.<Long>any());
        doNothing().when(calender).setStartDate(Mockito.<Date>any());
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user.setUserName("User Name");

        Project project = new Project();
        project.setCalender(calender);
        project.setContentUri("Not all who wander are lost");
        project.setId(2L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user2.setUserName("User Name");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Not all who wander are lost");
        project2.setId(1L);
        project2.setSocial(social2);
        project2.setSongTitle("Dr");
        project2.setSongUri("Song Uri");
        project2.setSupportCount(3);
        project2.setUrl("https://example.org/example");
        project2.setUser(user2);

        // Act and Assert
        assertNotEquals(project, project2);
    }

    /**
     * Method under test: {@link Project#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        Calender calender = mock(Calender.class);
        doNothing().when(calender).setEndDate(Mockito.<Date>any());
        doNothing().when(calender).setId(Mockito.<Long>any());
        doNothing().when(calender).setStartDate(Mockito.<Date>any());
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user.setUserName("User Name");

        Project project = new Project();
        project.setCalender(calender);
        project.setContentUri("Not all who wander are lost");
        project.setId(null);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user2.setUserName("User Name");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Not all who wander are lost");
        project2.setId(1L);
        project2.setSocial(social2);
        project2.setSongTitle("Dr");
        project2.setSongUri("Song Uri");
        project2.setSupportCount(3);
        project2.setUrl("https://example.org/example");
        project2.setUser(user2);

        // Act and Assert
        assertNotEquals(project, project2);
    }

    /**
     * Method under test: {@link Project#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        Calender calender = mock(Calender.class);
        doNothing().when(calender).setEndDate(Mockito.<Date>any());
        doNothing().when(calender).setId(Mockito.<Long>any());
        doNothing().when(calender).setStartDate(Mockito.<Date>any());
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user.setUserName("User Name");

        Project project = new Project();
        project.setCalender(calender);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(1);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user2.setUserName("User Name");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Not all who wander are lost");
        project2.setId(1L);
        project2.setSocial(social2);
        project2.setSongTitle("Dr");
        project2.setSongUri("Song Uri");
        project2.setSupportCount(3);
        project2.setUrl("https://example.org/example");
        project2.setUser(user2);

        // Act and Assert
        assertNotEquals(project, project2);
    }

    /**
     * Method under test: {@link Project#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        Calender calender = mock(Calender.class);
        doNothing().when(calender).setEndDate(Mockito.<Date>any());
        doNothing().when(calender).setId(Mockito.<Long>any());
        doNothing().when(calender).setStartDate(Mockito.<Date>any());
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user.setUserName("User Name");

        Project project = new Project();
        project.setCalender(calender);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(null);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user2.setUserName("User Name");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Not all who wander are lost");
        project2.setId(1L);
        project2.setSocial(social2);
        project2.setSongTitle("Dr");
        project2.setSongUri("Song Uri");
        project2.setSupportCount(3);
        project2.setUrl("https://example.org/example");
        project2.setUser(user2);

        // Act and Assert
        assertNotEquals(project, project2);
    }

    /**
     * Method under test: {@link Project#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        Calender calender = mock(Calender.class);
        doNothing().when(calender).setEndDate(Mockito.<Date>any());
        doNothing().when(calender).setId(Mockito.<Long>any());
        doNothing().when(calender).setStartDate(Mockito.<Date>any());
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user.setUserName("User Name");

        Project project = new Project();
        project.setCalender(calender);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("Url");
        project.setUser(user);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user2.setUserName("User Name");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Not all who wander are lost");
        project2.setId(1L);
        project2.setSocial(social2);
        project2.setSongTitle("Dr");
        project2.setSongUri("Song Uri");
        project2.setSupportCount(3);
        project2.setUrl("https://example.org/example");
        project2.setUser(user2);

        // Act and Assert
        assertNotEquals(project, project2);
    }

    /**
     * Method under test: {@link Project#equals(Object)}
     */
    @Test
    void testEquals9() {
        // Arrange
        Calender calender = mock(Calender.class);
        doNothing().when(calender).setEndDate(Mockito.<Date>any());
        doNothing().when(calender).setId(Mockito.<Long>any());
        doNothing().when(calender).setStartDate(Mockito.<Date>any());
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user.setUserName("User Name");

        Project project = new Project();
        project.setCalender(calender);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl(null);
        project.setUser(user);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user2.setUserName("User Name");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Not all who wander are lost");
        project2.setId(1L);
        project2.setSocial(social2);
        project2.setSongTitle("Dr");
        project2.setSongUri("Song Uri");
        project2.setSupportCount(3);
        project2.setUrl("https://example.org/example");
        project2.setUser(user2);

        // Act and Assert
        assertNotEquals(project, project2);
    }

    /**
     * Method under test: {@link Project#equals(Object)}
     */
    @Test
    void testEquals10() {
        // Arrange
        Calender calender = mock(Calender.class);
        doNothing().when(calender).setEndDate(Mockito.<Date>any());
        doNothing().when(calender).setId(Mockito.<Long>any());
        doNothing().when(calender).setStartDate(Mockito.<Date>any());
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social = new Socials();
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
        doNothing().when(user).setUserName(Mockito.<String>any());
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");

        Project project = new Project();
        project.setCalender(calender);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user2.setUserName("User Name");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Not all who wander are lost");
        project2.setId(1L);
        project2.setSocial(social2);
        project2.setSongTitle("Dr");
        project2.setSongUri("Song Uri");
        project2.setSupportCount(3);
        project2.setUrl("https://example.org/example");
        project2.setUser(user2);

        // Act and Assert
        assertNotEquals(project, project2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Project#equals(Object)}
     *   <li>{@link Project#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user.setUserName("User Name");

        Project project = new Project();
        project.setCalender(calender);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        // Act and Assert
        assertEquals(project, project);
        int expectedHashCodeResult = project.hashCode();
        assertEquals(expectedHashCodeResult, project.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Project#equals(Object)}
     *   <li>{@link Project#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user.setUserName("User Name");

        Project project = new Project();
        project.setCalender(calender);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user2.setUserName("User Name");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Not all who wander are lost");
        project2.setId(1L);
        project2.setSocial(social2);
        project2.setSongTitle("Dr");
        project2.setSongUri("Song Uri");
        project2.setSupportCount(3);
        project2.setUrl("https://example.org/example");
        project2.setUser(user2);

        // Act and Assert
        assertEquals(project, project2);
        int expectedHashCodeResult = project.hashCode();
        assertEquals(expectedHashCodeResult, project2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link Project#Project(Long, String, Users, Calender, String, String, Socials, String, Integer)}
     *   <li>{@link Project#setCalender(Calender)}
     *   <li>{@link Project#setContentUri(String)}
     *   <li>{@link Project#setId(Long)}
     *   <li>{@link Project#setSocial(Socials)}
     *   <li>{@link Project#setSongTitle(String)}
     *   <li>{@link Project#setSongUri(String)}
     *   <li>{@link Project#setSupportCount(Integer)}
     *   <li>{@link Project#setUrl(String)}
     *   <li>{@link Project#setUser(Users)}
     *   <li>{@link Project#toString()}
     *   <li>{@link Project#getCalender()}
     *   <li>{@link Project#getContentUri()}
     *   <li>{@link Project#getId()}
     *   <li>{@link Project#getSocial()}
     *   <li>{@link Project#getSongTitle()}
     *   <li>{@link Project#getSongUri()}
     *   <li>{@link Project#getSupportCount()}
     *   <li>{@link Project#getUrl()}
     *   <li>{@link Project#getUser()}
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

        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        // Act
        Project actualProject = new Project(1L, "https://example.org/example", user, calender, "Dr", "Song Uri", social,
                "Not all who wander are lost", 3);
        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        actualProject.setCalender(calender2);
        actualProject.setContentUri("Not all who wander are lost");
        actualProject.setId(1L);
        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        actualProject.setSocial(social2);
        actualProject.setSongTitle("Dr");
        actualProject.setSongUri("Song Uri");
        actualProject.setSupportCount(3);
        actualProject.setUrl("https://example.org/example");
        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserName("User Name");
        actualProject.setUser(user2);
        actualProject.toString();
        Calender actualCalender = actualProject.getCalender();
        String actualContentUri = actualProject.getContentUri();
        Long actualId = actualProject.getId();
        Socials actualSocial = actualProject.getSocial();
        String actualSongTitle = actualProject.getSongTitle();
        String actualSongUri = actualProject.getSongUri();
        Integer actualSupportCount = actualProject.getSupportCount();
        String actualUrl = actualProject.getUrl();
        Users actualUser = actualProject.getUser();

        // Assert that nothing has changed
        assertEquals("Dr", actualSongTitle);
        assertEquals("Not all who wander are lost", actualContentUri);
        assertEquals("Song Uri", actualSongUri);
        assertEquals("https://example.org/example", actualUrl);
        assertEquals(1L, actualId.longValue());
        assertEquals(3, actualSupportCount.intValue());
        assertEquals(calender, actualCalender);
        assertEquals(social, actualSocial);
        assertEquals(user, actualUser);
        assertSame(calender2, actualCalender);
        assertSame(social2, actualSocial);
        assertSame(user2, actualUser);
    }

    /**
     * Method under test: {@link Project#Project()}
     */
    @Test
    void testNewProject() {
        // Arrange, Act and Assert
        assertEquals(0, (new Project()).getSupportCount().intValue());
    }
}
