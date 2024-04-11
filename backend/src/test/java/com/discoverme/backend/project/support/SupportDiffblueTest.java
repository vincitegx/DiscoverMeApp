package com.discoverme.backend.project.support;

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

import com.discoverme.backend.project.Project;
import com.discoverme.backend.project.calender.Calender;
import com.discoverme.backend.social.Socials;
import com.discoverme.backend.user.Users;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {Support.class})
@ExtendWith(SpringExtension.class)
class SupportDiffblueTest {
    @Autowired
    private Support support;

    /**
     * Method under test: {@link Support#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new Support()).canEqual("Other"));
    }

    /**
     * Method under test: {@link Support#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        Support support2 = new Support();

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
        user.setStageName("Stage Name");

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

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");

        Support support3 = new Support();
        support3.setId(1L);
        support3.setProject(project);
        support3.setUser(user2);

        // Act and Assert
        assertTrue(support2.canEqual(support3));
    }

    /**
     * Method under test: {@link Support#canEqual(Object)}
     */
    @Test
    void testCanEqual3() {
        // Arrange
        Support support2 = new Support();

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
        user.setStageName("Stage Name");
        Project project = mock(Project.class);
        doNothing().when(project).setCalender(Mockito.<Calender>any());
        doNothing().when(project).setContentUri(Mockito.<String>any());
        doNothing().when(project).setId(Mockito.<Long>any());
        doNothing().when(project).setSocial(Mockito.<Socials>any());
        doNothing().when(project).setSongTitle(Mockito.<String>any());
        doNothing().when(project).setSongUri(Mockito.<String>any());
        doNothing().when(project).setSupportCount(Mockito.<Integer>any());
        doNothing().when(project).setUrl(Mockito.<String>any());
        doNothing().when(project).setUser(Mockito.<Users>any());
        project.setCalender(calender);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");

        Support support3 = new Support();
        support3.setId(1L);
        support3.setProject(project);
        support3.setUser(user2);

        // Act
        boolean actualCanEqualResult = support2.canEqual(support3);

        // Assert
        verify(project).setCalender(isA(Calender.class));
        verify(project).setContentUri(eq("Not all who wander are lost"));
        verify(project).setId(isA(Long.class));
        verify(project).setSocial(isA(Socials.class));
        verify(project).setSongTitle(eq("Dr"));
        verify(project).setSongUri(eq("Song Uri"));
        verify(project).setSupportCount(isA(Integer.class));
        verify(project).setUrl(eq("https://example.org/example"));
        verify(project).setUser(isA(Users.class));
        assertTrue(actualCanEqualResult);
    }

    /**
     * Method under test: {@link Support#equals(Object)}
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
        user.setStageName("Stage Name");

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

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");

        Support support = new Support();
        support.setId(1L);
        support.setProject(project);
        support.setUser(user2);

        // Act and Assert
        assertNotEquals(support, null);
    }

    /**
     * Method under test: {@link Support#equals(Object)}
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
        user.setStageName("Stage Name");

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

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");

        Support support = new Support();
        support.setId(1L);
        support.setProject(project);
        support.setUser(user2);

        // Act and Assert
        assertNotEquals(support, "Different type to Support");
    }

    /**
     * Method under test: {@link Support#equals(Object)}
     */
    @Test
    void testEquals3() {
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
        user.setStageName("Stage Name");

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

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");

        Support support = new Support();
        support.setId(2L);
        support.setProject(project);
        support.setUser(user2);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        Users user3 = new Users();
        user3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user3.setEmail("jane.doe@example.org");
        user3.setEnabled(true);
        user3.setId(1L);
        user3.setNonLocked(true);
        user3.setPassword("iloveyou");
        user3.setRole("Role");
        user3.setStageName("Stage Name");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Not all who wander are lost");
        project2.setId(1L);
        project2.setSocial(social2);
        project2.setSongTitle("Dr");
        project2.setSongUri("Song Uri");
        project2.setSupportCount(3);
        project2.setUrl("https://example.org/example");
        project2.setUser(user3);

        Users user4 = new Users();
        user4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user4.setEmail("jane.doe@example.org");
        user4.setEnabled(true);
        user4.setId(1L);
        user4.setNonLocked(true);
        user4.setPassword("iloveyou");
        user4.setRole("Role");
        user4.setStageName("Stage Name");

        Support support2 = new Support();
        support2.setId(1L);
        support2.setProject(project2);
        support2.setUser(user4);

        // Act and Assert
        assertNotEquals(support, support2);
    }

    /**
     * Method under test: {@link Support#equals(Object)}
     */
    @Test
    void testEquals4() {
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
        user.setStageName("Stage Name");

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

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");

        Support support = new Support();
        support.setId(null);
        support.setProject(project);
        support.setUser(user2);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        Users user3 = new Users();
        user3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user3.setEmail("jane.doe@example.org");
        user3.setEnabled(true);
        user3.setId(1L);
        user3.setNonLocked(true);
        user3.setPassword("iloveyou");
        user3.setRole("Role");
        user3.setStageName("Stage Name");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Not all who wander are lost");
        project2.setId(1L);
        project2.setSocial(social2);
        project2.setSongTitle("Dr");
        project2.setSongUri("Song Uri");
        project2.setSupportCount(3);
        project2.setUrl("https://example.org/example");
        project2.setUser(user3);

        Users user4 = new Users();
        user4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user4.setEmail("jane.doe@example.org");
        user4.setEnabled(true);
        user4.setId(1L);
        user4.setNonLocked(true);
        user4.setPassword("iloveyou");
        user4.setRole("Role");
        user4.setStageName("Stage Name");

        Support support2 = new Support();
        support2.setId(1L);
        support2.setProject(project2);
        support2.setUser(user4);

        // Act and Assert
        assertNotEquals(support, support2);
    }

    /**
     * Method under test: {@link Support#equals(Object)}
     */
    @Test
    void testEquals5() {
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
        user.setStageName("Stage Name");
        Project project = mock(Project.class);
        doNothing().when(project).setCalender(Mockito.<Calender>any());
        doNothing().when(project).setContentUri(Mockito.<String>any());
        doNothing().when(project).setId(Mockito.<Long>any());
        doNothing().when(project).setSocial(Mockito.<Socials>any());
        doNothing().when(project).setSongTitle(Mockito.<String>any());
        doNothing().when(project).setSongUri(Mockito.<String>any());
        doNothing().when(project).setSupportCount(Mockito.<Integer>any());
        doNothing().when(project).setUrl(Mockito.<String>any());
        doNothing().when(project).setUser(Mockito.<Users>any());
        project.setCalender(calender);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");

        Support support = new Support();
        support.setId(1L);
        support.setProject(project);
        support.setUser(user2);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        Users user3 = new Users();
        user3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user3.setEmail("jane.doe@example.org");
        user3.setEnabled(true);
        user3.setId(1L);
        user3.setNonLocked(true);
        user3.setPassword("iloveyou");
        user3.setRole("Role");
        user3.setStageName("Stage Name");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Not all who wander are lost");
        project2.setId(1L);
        project2.setSocial(social2);
        project2.setSongTitle("Dr");
        project2.setSongUri("Song Uri");
        project2.setSupportCount(3);
        project2.setUrl("https://example.org/example");
        project2.setUser(user3);

        Users user4 = new Users();
        user4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user4.setEmail("jane.doe@example.org");
        user4.setEnabled(true);
        user4.setId(1L);
        user4.setNonLocked(true);
        user4.setPassword("iloveyou");
        user4.setRole("Role");
        user4.setStageName("Stage Name");

        Support support2 = new Support();
        support2.setId(1L);
        support2.setProject(project2);
        support2.setUser(user4);

        // Act and Assert
        assertNotEquals(support, support2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Support#equals(Object)}
     *   <li>{@link Support#hashCode()}
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
        user.setStageName("Stage Name");

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

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");

        Support support = new Support();
        support.setId(1L);
        support.setProject(project);
        support.setUser(user2);

        // Act and Assert
        assertEquals(support, support);
        int expectedHashCodeResult = support.hashCode();
        assertEquals(expectedHashCodeResult, support.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Support#equals(Object)}
     *   <li>{@link Support#hashCode()}
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
        user.setStageName("Stage Name");

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

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");

        Support support = new Support();
        support.setId(1L);
        support.setProject(project);
        support.setUser(user2);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        Users user3 = new Users();
        user3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user3.setEmail("jane.doe@example.org");
        user3.setEnabled(true);
        user3.setId(1L);
        user3.setNonLocked(true);
        user3.setPassword("iloveyou");
        user3.setRole("Role");
        user3.setStageName("Stage Name");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Not all who wander are lost");
        project2.setId(1L);
        project2.setSocial(social2);
        project2.setSongTitle("Dr");
        project2.setSongUri("Song Uri");
        project2.setSupportCount(3);
        project2.setUrl("https://example.org/example");
        project2.setUser(user3);

        Users user4 = new Users();
        user4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user4.setEmail("jane.doe@example.org");
        user4.setEnabled(true);
        user4.setId(1L);
        user4.setNonLocked(true);
        user4.setPassword("iloveyou");
        user4.setRole("Role");
        user4.setStageName("Stage Name");

        Support support2 = new Support();
        support2.setId(1L);
        support2.setProject(project2);
        support2.setUser(user4);

        // Act and Assert
        assertEquals(support, support2);
        int expectedHashCodeResult = support.hashCode();
        assertEquals(expectedHashCodeResult, support2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Support#Support()}
     *   <li>{@link Support#setId(Long)}
     *   <li>{@link Support#setProject(Project)}
     *   <li>{@link Support#setUser(Users)}
     *   <li>{@link Support#toString()}
     *   <li>{@link Support#getId()}
     *   <li>{@link Support#getProject()}
     *   <li>{@link Support#getUser()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Support actualSupport = new Support();
        actualSupport.setId(1L);
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
        user.setStageName("Stage Name");
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
        actualSupport.setProject(project);
        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");
        actualSupport.setUser(user2);
        actualSupport.toString();
        Long actualId = actualSupport.getId();
        Project actualProject = actualSupport.getProject();
        Users actualUser = actualSupport.getUser();

        // Assert that nothing has changed
        assertEquals(1L, actualId.longValue());
        assertSame(project, actualProject);
        assertSame(user2, actualUser);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Support#Support(Long, Project, Users)}
     *   <li>{@link Support#setId(Long)}
     *   <li>{@link Support#setProject(Project)}
     *   <li>{@link Support#setUser(Users)}
     *   <li>{@link Support#toString()}
     *   <li>{@link Support#getId()}
     *   <li>{@link Support#getProject()}
     *   <li>{@link Support#getUser()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
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
        user.setStageName("Stage Name");

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

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setStageName("Stage Name");

        // Act
        Support actualSupport = new Support(1L, project, user2);
        actualSupport.setId(1L);
        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        Users user3 = new Users();
        user3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user3.setEmail("jane.doe@example.org");
        user3.setEnabled(true);
        user3.setId(1L);
        user3.setNonLocked(true);
        user3.setPassword("iloveyou");
        user3.setRole("Role");
        user3.setStageName("Stage Name");
        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Not all who wander are lost");
        project2.setId(1L);
        project2.setSocial(social2);
        project2.setSongTitle("Dr");
        project2.setSongUri("Song Uri");
        project2.setSupportCount(3);
        project2.setUrl("https://example.org/example");
        project2.setUser(user3);
        actualSupport.setProject(project2);
        Users user4 = new Users();
        user4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user4.setEmail("jane.doe@example.org");
        user4.setEnabled(true);
        user4.setId(1L);
        user4.setNonLocked(true);
        user4.setPassword("iloveyou");
        user4.setRole("Role");
        user4.setStageName("Stage Name");
        actualSupport.setUser(user4);
        actualSupport.toString();
        Long actualId = actualSupport.getId();
        Project actualProject = actualSupport.getProject();
        Users actualUser = actualSupport.getUser();

        // Assert that nothing has changed
        assertEquals(1L, actualId.longValue());
        assertEquals(project, actualProject);
        assertEquals(user, actualUser);
        assertSame(project2, actualProject);
        assertSame(user4, actualUser);
    }
}
