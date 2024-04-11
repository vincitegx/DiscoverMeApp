package com.discoverme.backend.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discoverme.backend.project.calender.Calender;
import com.discoverme.backend.project.support.Support;
import com.discoverme.backend.project.support.SupportRepository;
import com.discoverme.backend.social.Socials;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LoggedInUserService.class})
@ExtendWith(SpringExtension.class)
class LoggedInUserServiceDiffblueTest {
    @Autowired
    private LoggedInUserService loggedInUserService;

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private SupportRepository supportRepository;

    @MockBean
    private UserService userService;

    /**
     * Method under test:
     * {@link LoggedInUserService#checkSupportStateForLoggedInUser(Long)}
     */
    @Test
    void testCheckSupportStateForLoggedInUser() {
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
        Optional<Project> ofResult = Optional.of(project);
        when(projectRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setStageName("Stage Name");
        when(userService.getCurrentUser()).thenReturn(users);

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
        user2.setStageName("Stage Name");

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

        Users user3 = new Users();
        user3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user3.setEmail("jane.doe@example.org");
        user3.setEnabled(true);
        user3.setId(1L);
        user3.setNonLocked(true);
        user3.setPassword("iloveyou");
        user3.setRole("Role");
        user3.setStageName("Stage Name");

        Support support = new Support();
        support.setId(1L);
        support.setProject(project2);
        support.setUser(user3);
        Optional<Support> ofResult2 = Optional.of(support);
        when(supportRepository.findTopByProjectAndUserOrderByIdDesc(Mockito.<Project>any(), Mockito.<Users>any()))
                .thenReturn(ofResult2);

        // Act
        boolean actualCheckSupportStateForLoggedInUserResult = loggedInUserService.checkSupportStateForLoggedInUser(1L);

        // Assert
        verify(supportRepository).findTopByProjectAndUserOrderByIdDesc(isA(Project.class), isA(Users.class));
        verify(userService, atLeast(1)).getCurrentUser();
        verify(projectRepository).findById(isA(Long.class));
        assertTrue(actualCheckSupportStateForLoggedInUserResult);
    }

    /**
     * Method under test:
     * {@link LoggedInUserService#checkSupportStateForLoggedInUser(Long)}
     */
    @Test
    void testCheckSupportStateForLoggedInUser2() {
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
        Optional<Project> ofResult = Optional.of(project);
        when(projectRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setStageName("Stage Name");
        when(userService.getCurrentUser()).thenReturn(users);
        when(supportRepository.findTopByProjectAndUserOrderByIdDesc(Mockito.<Project>any(), Mockito.<Users>any()))
                .thenThrow(new ProjectException("An error occurred"));

        // Act and Assert
        assertThrows(ProjectException.class, () -> loggedInUserService.checkSupportStateForLoggedInUser(1L));
        verify(supportRepository).findTopByProjectAndUserOrderByIdDesc(isA(Project.class), isA(Users.class));
        verify(userService, atLeast(1)).getCurrentUser();
        verify(projectRepository).findById(isA(Long.class));
    }

    /**
     * Method under test:
     * {@link LoggedInUserService#checkSupportStateForLoggedInUser(Long)}
     */
    @Test
    void testCheckSupportStateForLoggedInUser3() {
        // Arrange
        Optional<Project> emptyResult = Optional.empty();
        when(projectRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ProjectException.class, () -> loggedInUserService.checkSupportStateForLoggedInUser(1L));
        verify(projectRepository).findById(isA(Long.class));
    }

    /**
     * Method under test:
     * {@link LoggedInUserService#getProjectsSupportedToLoggedInUser(Users)}
     */
    @Test
    void testGetProjectsSupportedToLoggedInUser() {
        // Arrange
        when(projectRepository.findByUser(Mockito.<Users>any())).thenReturn(new ArrayList<>());

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setStageName("Stage Name");
        when(userService.getCurrentUser()).thenReturn(users);

        Users projectUser = new Users();
        projectUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        projectUser.setEmail("jane.doe@example.org");
        projectUser.setEnabled(true);
        projectUser.setId(1L);
        projectUser.setNonLocked(true);
        projectUser.setPassword("iloveyou");
        projectUser.setRole("Role");
        projectUser.setStageName("Stage Name");

        // Act
        Double actualProjectsSupportedToLoggedInUser = loggedInUserService.getProjectsSupportedToLoggedInUser(projectUser);

        // Assert
        verify(projectRepository).findByUser(isA(Users.class));
        verify(userService).getCurrentUser();
        assertEquals(0.0d, actualProjectsSupportedToLoggedInUser.doubleValue());
    }

    /**
     * Method under test:
     * {@link LoggedInUserService#getProjectsSupportedToLoggedInUser(Users)}
     */
    @Test
    void testGetProjectsSupportedToLoggedInUser2() {
        // Arrange
        when(userService.getCurrentUser()).thenThrow(new ProjectException("An error occurred"));

        Users projectUser = new Users();
        projectUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        projectUser.setEmail("jane.doe@example.org");
        projectUser.setEnabled(true);
        projectUser.setId(1L);
        projectUser.setNonLocked(true);
        projectUser.setPassword("iloveyou");
        projectUser.setRole("Role");
        projectUser.setStageName("Stage Name");

        // Act and Assert
        assertThrows(ProjectException.class, () -> loggedInUserService.getProjectsSupportedToLoggedInUser(projectUser));
        verify(userService).getCurrentUser();
    }

    /**
     * Method under test:
     * {@link LoggedInUserService#getProjectsSupportedToLoggedInUser(Users)}
     */
    @Test
    void testGetProjectsSupportedToLoggedInUser3() {
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

        ArrayList<Project> projectList = new ArrayList<>();
        projectList.add(project);
        when(projectRepository.findByUser(Mockito.<Users>any())).thenReturn(projectList);

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setStageName("Stage Name");
        when(userService.getCurrentUser()).thenReturn(users);

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
        user2.setStageName("Stage Name");

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

        Users user3 = new Users();
        user3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user3.setEmail("jane.doe@example.org");
        user3.setEnabled(true);
        user3.setId(1L);
        user3.setNonLocked(true);
        user3.setPassword("iloveyou");
        user3.setRole("Role");
        user3.setStageName("Stage Name");

        Support support = new Support();
        support.setId(1L);
        support.setProject(project2);
        support.setUser(user3);
        Optional<Support> ofResult = Optional.of(support);
        when(supportRepository.findByProjectAndUser(Mockito.<Project>any(), Mockito.<Users>any())).thenReturn(ofResult);

        Users projectUser = new Users();
        projectUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        projectUser.setEmail("jane.doe@example.org");
        projectUser.setEnabled(true);
        projectUser.setId(1L);
        projectUser.setNonLocked(true);
        projectUser.setPassword("iloveyou");
        projectUser.setRole("Role");
        projectUser.setStageName("Stage Name");

        // Act
        Double actualProjectsSupportedToLoggedInUser = loggedInUserService.getProjectsSupportedToLoggedInUser(projectUser);

        // Assert
        verify(projectRepository).findByUser(isA(Users.class));
        verify(supportRepository).findByProjectAndUser(isA(Project.class), isA(Users.class));
        verify(userService).getCurrentUser();
        assertEquals(100.0d, actualProjectsSupportedToLoggedInUser.doubleValue());
    }

    /**
     * Method under test:
     * {@link LoggedInUserService#getProjectsSupportedToLoggedInUser(Users)}
     */
    @Test
    void testGetProjectsSupportedToLoggedInUser4() {
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

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(2L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social2 = new Socials();
        social2.setId(2L);
        social2.setName("com.discoverme.backend.social.Socials");

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("john.smith@example.org");
        user2.setEnabled(false);
        user2.setId(2L);
        user2.setNonLocked(false);
        user2.setPassword("Password");
        user2.setRole("com.discoverme.backend.user.Users");
        user2.setStageName("com.discoverme.backend.user.Users");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Content Uri");
        project2.setId(2L);
        project2.setSocial(social2);
        project2.setSongTitle("Mr");
        project2.setSongUri("com.discoverme.backend.project.Project");
        project2.setSupportCount(1);
        project2.setUrl("Url");
        project2.setUser(user2);

        ArrayList<Project> projectList = new ArrayList<>();
        projectList.add(project2);
        projectList.add(project);
        when(projectRepository.findByUser(Mockito.<Users>any())).thenReturn(projectList);

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setStageName("Stage Name");
        when(userService.getCurrentUser()).thenReturn(users);

        Calender calender3 = new Calender();
        calender3.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender3.setId(1L);
        calender3.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social3 = new Socials();
        social3.setId(1L);
        social3.setName("Name");

        Users user3 = new Users();
        user3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user3.setEmail("jane.doe@example.org");
        user3.setEnabled(true);
        user3.setId(1L);
        user3.setNonLocked(true);
        user3.setPassword("iloveyou");
        user3.setRole("Role");
        user3.setStageName("Stage Name");

        Project project3 = new Project();
        project3.setCalender(calender3);
        project3.setContentUri("Not all who wander are lost");
        project3.setId(1L);
        project3.setSocial(social3);
        project3.setSongTitle("Dr");
        project3.setSongUri("Song Uri");
        project3.setSupportCount(3);
        project3.setUrl("https://example.org/example");
        project3.setUser(user3);

        Users user4 = new Users();
        user4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user4.setEmail("jane.doe@example.org");
        user4.setEnabled(true);
        user4.setId(1L);
        user4.setNonLocked(true);
        user4.setPassword("iloveyou");
        user4.setRole("Role");
        user4.setStageName("Stage Name");

        Support support = new Support();
        support.setId(1L);
        support.setProject(project3);
        support.setUser(user4);
        Optional<Support> ofResult = Optional.of(support);
        when(supportRepository.findByProjectAndUser(Mockito.<Project>any(), Mockito.<Users>any())).thenReturn(ofResult);

        Users projectUser = new Users();
        projectUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        projectUser.setEmail("jane.doe@example.org");
        projectUser.setEnabled(true);
        projectUser.setId(1L);
        projectUser.setNonLocked(true);
        projectUser.setPassword("iloveyou");
        projectUser.setRole("Role");
        projectUser.setStageName("Stage Name");

        // Act
        Double actualProjectsSupportedToLoggedInUser = loggedInUserService.getProjectsSupportedToLoggedInUser(projectUser);

        // Assert
        verify(projectRepository).findByUser(isA(Users.class));
        verify(supportRepository, atLeast(1)).findByProjectAndUser(Mockito.<Project>any(), isA(Users.class));
        verify(userService).getCurrentUser();
        assertEquals(100.0d, actualProjectsSupportedToLoggedInUser.doubleValue());
    }
}
