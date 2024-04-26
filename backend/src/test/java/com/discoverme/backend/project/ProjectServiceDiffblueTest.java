package com.discoverme.backend.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discoverme.backend.config.ApplicationProperties;
import com.discoverme.backend.project.calender.Calender;
import com.discoverme.backend.project.calender.CalenderRepository;
import com.discoverme.backend.project.calender.CalenderService;
import com.discoverme.backend.project.support.SupportRepository;
import com.discoverme.backend.social.Socials;
import com.discoverme.backend.user.UserRepository;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

class ProjectServiceDiffblueTest {
    /**
     * Method under test:
     * {@link ProjectService#submitProject(ProjectRequest, MultipartFile)}
     */
    @Test
    void testSubmitProject() throws IOException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        CalenderService calenderService = mock(CalenderService.class);
        when(calenderService.getProjectCalender()).thenReturn(calender);

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        UserService userService = mock(UserService.class);
        when(userService.getCurrentUser()).thenReturn(users);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        project.setCalender(calender2);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);
        Optional<Project> ofResult = Optional.of(project);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.findByUserAndCalender(Mockito.<Users>any(), Mockito.<Calender>any())).thenReturn(ofResult);
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();
        ProjectService projectService = new ProjectService(calenderService, userService, projectRepository, null,
                loggedInUserService, secureRandomStringGenerator, new ApplicationProperties());

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setSocial(social2);
        projectRequest.setSongTitle("Dr");
        projectRequest.setSongUri("Song Uri");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> projectService.submitProject(projectRequest,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
        verify(projectRepository).findByUserAndCalender(isA(Users.class), isA(Calender.class));
        verify(calenderService).getProjectCalender();
        verify(userService).getCurrentUser();
    }

    /**
     * Method under test:
     * {@link ProjectService#submitProject(ProjectRequest, MultipartFile)}
     */
    @Test
    void testSubmitProject2() throws IOException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        CalenderService calenderService = mock(CalenderService.class);
        when(calenderService.getProjectCalender()).thenReturn(calender);

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        UserService userService = mock(UserService.class);
        when(userService.getCurrentUser()).thenReturn(users);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.findByUserAndCalender(Mockito.<Users>any(), Mockito.<Calender>any()))
                .thenThrow(new IllegalArgumentException("foo"));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();
        ProjectService projectService = new ProjectService(calenderService, userService, projectRepository, null,
                loggedInUserService, secureRandomStringGenerator, new ApplicationProperties());

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setSocial(social);
        projectRequest.setSongTitle("Dr");
        projectRequest.setSongUri("Song Uri");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> projectService.submitProject(projectRequest,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
        verify(projectRepository).findByUserAndCalender(isA(Users.class), isA(Calender.class));
        verify(calenderService).getProjectCalender();
        verify(userService).getCurrentUser();
    }

    /**
     * Method under test: {@link ProjectService#findById(Long)}
     */
    @Test
    void testFindById() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

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
        Optional<Project> ofResult = Optional.of(project);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CalenderService calenderService = new CalenderService(mock(CalenderRepository.class));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();

        // Act
        Optional<Project> actualFindByIdResult = (new ProjectService(calenderService, userService, projectRepository, null,
                loggedInUserService, secureRandomStringGenerator, new ApplicationProperties())).findById(1L);

        // Assert
        verify(projectRepository).findById(isA(Long.class));
        assertSame(ofResult, actualFindByIdResult);
    }

    /**
     * Method under test: {@link ProjectService#findById(Long)}
     */
    @Test
    void testFindById2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.findById(Mockito.<Long>any())).thenThrow(new IllegalArgumentException("foo"));
        CalenderService calenderService = new CalenderService(mock(CalenderRepository.class));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new ProjectService(calenderService, userService, projectRepository, null, loggedInUserService,
                        secureRandomStringGenerator, new ApplicationProperties())).findById(1L));
        verify(projectRepository).findById(isA(Long.class));
    }

    /**
     * Method under test: {@link ProjectService#saveProject(Project)}
     */
    @Test
    void testSaveProject() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

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
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.save(Mockito.<Project>any())).thenReturn(project);
        CalenderService calenderService = new CalenderService(mock(CalenderRepository.class));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();
        ProjectService projectService = new ProjectService(calenderService, userService, projectRepository, null,
                loggedInUserService, secureRandomStringGenerator, new ApplicationProperties());

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

        // Act
        Project actualSaveProjectResult = projectService.saveProject(project2);

        // Assert
        verify(projectRepository).save(isA(Project.class));
        assertSame(project, actualSaveProjectResult);
    }

    /**
     * Method under test: {@link ProjectService#saveProject(Project)}
     */
    @Test
    void testSaveProject2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.save(Mockito.<Project>any())).thenThrow(new IllegalArgumentException("foo"));
        CalenderService calenderService = new CalenderService(mock(CalenderRepository.class));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();
        ProjectService projectService = new ProjectService(calenderService, userService, projectRepository, null,
                loggedInUserService, secureRandomStringGenerator, new ApplicationProperties());

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
        assertThrows(IllegalArgumentException.class, () -> projectService.saveProject(project));
        verify(projectRepository).save(isA(Project.class));
    }

    /**
     * Method under test: {@link ProjectService#deleteProject(Long)}
     */
    @Test
    void testDeleteProject() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

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
        Optional<Project> ofResult = Optional.of(project);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        doNothing().when(projectRepository).delete(Mockito.<Project>any());
        when(projectRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CalenderService calenderService = new CalenderService(mock(CalenderRepository.class));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();

        // Act
        (new ProjectService(calenderService, userService, projectRepository, null, loggedInUserService,
                secureRandomStringGenerator, new ApplicationProperties())).deleteProject(1L);

        // Assert
        verify(projectRepository).delete(isA(Project.class));
        verify(projectRepository).findById(isA(Long.class));
    }

    /**
     * Method under test: {@link ProjectService#deleteProject(Long)}
     */
    @Test
    void testDeleteProject2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

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
        Optional<Project> ofResult = Optional.of(project);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        doThrow(new IllegalArgumentException("foo")).when(projectRepository).delete(Mockito.<Project>any());
        when(projectRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CalenderService calenderService = new CalenderService(mock(CalenderRepository.class));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new ProjectService(calenderService, userService, projectRepository, null, loggedInUserService,
                        secureRandomStringGenerator, new ApplicationProperties())).deleteProject(1L));
        verify(projectRepository).delete(isA(Project.class));
        verify(projectRepository).findById(isA(Long.class));
    }

    /**
     * Method under test: {@link ProjectService#deleteProject(Long)}
     */
    @Test
    void testDeleteProject3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        Optional<Project> emptyResult = Optional.empty();
        when(projectRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        CalenderService calenderService = new CalenderService(mock(CalenderRepository.class));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();

        // Act and Assert
        assertThrows(ProjectException.class, () -> (new ProjectService(calenderService, userService, projectRepository,
                null, loggedInUserService, secureRandomStringGenerator, new ApplicationProperties())).deleteProject(1L));
        verify(projectRepository).findById(isA(Long.class));
    }

    /**
     * Method under test:
     * {@link ProjectService#getCurrentProjects(String, PageRequest)}
     */
    @Test
    void testGetCurrentProjects() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Optional<Calender> ofResult = Optional.of(calender);
        CalenderRepository calenderRepository = mock(CalenderRepository.class);
        when(calenderRepository.findFirstByOrderByIdDesc()).thenReturn(ofResult);
        CalenderService calenderService = new CalenderService(calenderRepository);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.findByCalenderAndSongTitleContainingOrUserNameContaining(Mockito.<Long>any(),
                Mockito.<String>any(), Mockito.<PageRequest>any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();

        // Act
        Page<ProjectResponse> actualCurrentProjects = (new ProjectService(calenderService, userService, projectRepository,
                null, loggedInUserService, secureRandomStringGenerator, new ApplicationProperties()))
                .getCurrentProjects("Search", null);

        // Assert
        verify(projectRepository).findByCalenderAndSongTitleContainingOrUserNameContaining(isA(Long.class), eq("Search"),
                isNull());
        verify(calenderRepository).findFirstByOrderByIdDesc();
        assertTrue(actualCurrentProjects.toList().isEmpty());
    }

    /**
     * Method under test:
     * {@link ProjectService#getCurrentProjects(String, PageRequest)}
     */
    @Test
    void testGetCurrentProjects2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Optional<Calender> ofResult = Optional.of(calender);
        CalenderRepository calenderRepository = mock(CalenderRepository.class);
        when(calenderRepository.findFirstByOrderByIdDesc()).thenReturn(ofResult);
        CalenderService calenderService = new CalenderService(calenderRepository);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.findByCalenderAndSongTitleContainingOrUserNameContaining(Mockito.<Long>any(),
                Mockito.<String>any(), Mockito.<PageRequest>any())).thenThrow(new IllegalArgumentException("foo"));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new ProjectService(calenderService, userService, projectRepository, null, loggedInUserService,
                        secureRandomStringGenerator, new ApplicationProperties())).getCurrentProjects("Search", null));
        verify(projectRepository).findByCalenderAndSongTitleContainingOrUserNameContaining(isA(Long.class), eq("Search"),
                isNull());
        verify(calenderRepository).findFirstByOrderByIdDesc();
    }

    /**
     * Method under test:
     * {@link ProjectService#getCurrentProjects(String, PageRequest)}
     */
    @Test
    void testGetCurrentProjects3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        CalenderService calenderService = mock(CalenderService.class);
        when(calenderService.getProjectCalender()).thenReturn(calender);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.findByCalenderAndSongTitleContainingOrUserNameContaining(Mockito.<Long>any(),
                Mockito.<String>any(), Mockito.<PageRequest>any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();

        // Act
        Page<ProjectResponse> actualCurrentProjects = (new ProjectService(calenderService, userService, projectRepository,
                null, loggedInUserService, secureRandomStringGenerator, new ApplicationProperties()))
                .getCurrentProjects("Search", null);

        // Assert
        verify(projectRepository).findByCalenderAndSongTitleContainingOrUserNameContaining(isA(Long.class), eq("Search"),
                isNull());
        verify(calenderService).getProjectCalender();
        assertTrue(actualCurrentProjects.toList().isEmpty());
    }

    /**
     * Method under test:
     * {@link ProjectService#getCurrentProjects(String, PageRequest)}
     */
    @Test
    void testGetCurrentProjects4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        CalenderService calenderService = mock(CalenderService.class);
        when(calenderService.getProjectCalender()).thenReturn(calender);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        project.setCalender(calender2);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        ArrayList<Project> content = new ArrayList<>();
        content.add(project);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.findByCalenderAndSongTitleContainingOrUserNameContaining(Mockito.<Long>any(),
                Mockito.<String>any(), Mockito.<PageRequest>any())).thenReturn(new PageImpl<>(content));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();

        // Act
        Page<ProjectResponse> actualCurrentProjects = (new ProjectService(calenderService, userService, projectRepository,
                null, loggedInUserService, secureRandomStringGenerator, new ApplicationProperties()))
                .getCurrentProjects("Search", null);

        // Assert
        verify(projectRepository).findByCalenderAndSongTitleContainingOrUserNameContaining(isA(Long.class), eq("Search"),
                isNull());
        verify(calenderService).getProjectCalender();
        assertEquals(1, actualCurrentProjects.toList().size());
    }

    /**
     * Method under test:
     * {@link ProjectService#getCurrentProjects(String, PageRequest)}
     */
    @Test
    void testGetCurrentProjects5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        CalenderService calenderService = mock(CalenderService.class);
        when(calenderService.getProjectCalender()).thenReturn(calender);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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

        Socials socials = new Socials();
        socials.setId(1L);
        socials.setName("Name");

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        Project project = mock(Project.class);
        when(project.getSocial()).thenReturn(socials);
        when(project.getUser()).thenReturn(users);
        when(project.getId()).thenReturn(1L);
        when(project.getContentUri()).thenReturn("Not all who wander are lost");
        when(project.getSongTitle()).thenReturn("Dr");
        when(project.getSongUri()).thenReturn("Song Uri");
        when(project.getUrl()).thenReturn("https://example.org/example");
        doNothing().when(project).setCalender(Mockito.<Calender>any());
        doNothing().when(project).setContentUri(Mockito.<String>any());
        doNothing().when(project).setId(Mockito.<Long>any());
        doNothing().when(project).setSocial(Mockito.<Socials>any());
        doNothing().when(project).setSongTitle(Mockito.<String>any());
        doNothing().when(project).setSongUri(Mockito.<String>any());
        doNothing().when(project).setSupportCount(Mockito.<Integer>any());
        doNothing().when(project).setUrl(Mockito.<String>any());
        doNothing().when(project).setUser(Mockito.<Users>any());
        project.setCalender(calender2);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        ArrayList<Project> content = new ArrayList<>();
        content.add(project);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.findByCalenderAndSongTitleContainingOrUserNameContaining(Mockito.<Long>any(),
                Mockito.<String>any(), Mockito.<PageRequest>any())).thenReturn(new PageImpl<>(content));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();

        // Act
        Page<ProjectResponse> actualCurrentProjects = (new ProjectService(calenderService, userService, projectRepository,
                null, loggedInUserService, secureRandomStringGenerator, new ApplicationProperties()))
                .getCurrentProjects("Search", null);

        // Assert
        verify(project).getContentUri();
        verify(project).getId();
        verify(project).getSocial();
        verify(project).getSongTitle();
        verify(project).getSongUri();
        verify(project).getUrl();
        verify(project).getUser();
        verify(project).setCalender(isA(Calender.class));
        verify(project).setContentUri(eq("Not all who wander are lost"));
        verify(project).setId(isA(Long.class));
        verify(project).setSocial(isA(Socials.class));
        verify(project).setSongTitle(eq("Dr"));
        verify(project).setSongUri(eq("Song Uri"));
        verify(project).setSupportCount(isA(Integer.class));
        verify(project).setUrl(eq("https://example.org/example"));
        verify(project).setUser(isA(Users.class));
        verify(projectRepository).findByCalenderAndSongTitleContainingOrUserNameContaining(isA(Long.class), eq("Search"),
                isNull());
        verify(calenderService).getProjectCalender();
        assertEquals(1, actualCurrentProjects.toList().size());
    }

    /**
     * Method under test: {@link ProjectService#mapProjectToResponse(Project)}
     */
    @Test
    void testMapProjectToResponse() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CalenderService calenderService = new CalenderService(mock(CalenderRepository.class));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();
        ProjectService projectService = new ProjectService(calenderService, userService, projectRepository, null,
                loggedInUserService, secureRandomStringGenerator, new ApplicationProperties());

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

        // Act
        ProjectResponse actualMapProjectToResponseResult = projectService.mapProjectToResponse(project);

        // Assert
        assertEquals("Dr", actualMapProjectToResponseResult.getSongTitle());
        assertEquals("Not all who wander are lost", actualMapProjectToResponseResult.getContentUri());
        assertEquals("Song Uri", actualMapProjectToResponseResult.getSongUri());
        assertEquals("User Name", actualMapProjectToResponseResult.getUserName());
        assertEquals("https://example.org/example", actualMapProjectToResponseResult.getUrl());
        assertNull(actualMapProjectToResponseResult.getPercentOfSupport());
        assertEquals(1L, actualMapProjectToResponseResult.getId().longValue());
        assertEquals(1L, actualMapProjectToResponseResult.getSocial().getId().longValue());
        assertFalse(actualMapProjectToResponseResult.isSupported());
    }

    /**
     * Method under test: {@link ProjectService#mapProjectToResponse(Project)}
     */
    @Test
    void testMapProjectToResponse2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CalenderService calenderService = new CalenderService(mock(CalenderRepository.class));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();
        ProjectService projectService = new ProjectService(calenderService, userService, projectRepository, null,
                loggedInUserService, secureRandomStringGenerator, new ApplicationProperties());

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

        Socials socials = new Socials();
        socials.setId(1L);
        socials.setName("Name");

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        Project project = mock(Project.class);
        when(project.getSocial()).thenReturn(socials);
        when(project.getUser()).thenReturn(users);
        when(project.getId()).thenReturn(1L);
        when(project.getContentUri()).thenReturn("Not all who wander are lost");
        when(project.getSongTitle()).thenReturn("Dr");
        when(project.getSongUri()).thenReturn("Song Uri");
        when(project.getUrl()).thenReturn("https://example.org/example");
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

        // Act
        ProjectResponse actualMapProjectToResponseResult = projectService.mapProjectToResponse(project);

        // Assert
        verify(project).getContentUri();
        verify(project).getId();
        verify(project).getSocial();
        verify(project).getSongTitle();
        verify(project).getSongUri();
        verify(project).getUrl();
        verify(project).getUser();
        verify(project).setCalender(isA(Calender.class));
        verify(project).setContentUri(eq("Not all who wander are lost"));
        verify(project).setId(isA(Long.class));
        verify(project).setSocial(isA(Socials.class));
        verify(project).setSongTitle(eq("Dr"));
        verify(project).setSongUri(eq("Song Uri"));
        verify(project).setSupportCount(isA(Integer.class));
        verify(project).setUrl(eq("https://example.org/example"));
        verify(project).setUser(isA(Users.class));
        assertEquals("Dr", actualMapProjectToResponseResult.getSongTitle());
        assertEquals("Not all who wander are lost", actualMapProjectToResponseResult.getContentUri());
        assertEquals("Song Uri", actualMapProjectToResponseResult.getSongUri());
        assertEquals("User Name", actualMapProjectToResponseResult.getUserName());
        assertEquals("https://example.org/example", actualMapProjectToResponseResult.getUrl());
        assertNull(actualMapProjectToResponseResult.getPercentOfSupport());
        assertEquals(1L, actualMapProjectToResponseResult.getId().longValue());
        assertEquals(1L, actualMapProjectToResponseResult.getSocial().getId().longValue());
        assertFalse(actualMapProjectToResponseResult.isSupported());
    }

    /**
     * Method under test:
     * {@link ProjectService#getAllProjectsForACalender(Long, Pageable)}
     */
    @Test
    void testGetAllProjectsForACalender() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Optional<Calender> ofResult = Optional.of(calender);
        CalenderRepository calenderRepository = mock(CalenderRepository.class);
        when(calenderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CalenderService calenderService = new CalenderService(calenderRepository);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.findByCalender(Mockito.<Calender>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();

        // Act
        Page<ProjectResponse> actualAllProjectsForACalender = (new ProjectService(calenderService, userService,
                projectRepository, null, loggedInUserService, secureRandomStringGenerator, new ApplicationProperties()))
                .getAllProjectsForACalender(1L, null);

        // Assert
        verify(projectRepository).findByCalender(isA(Calender.class), isNull());
        verify(calenderRepository).findById(isA(Long.class));
        assertTrue(actualAllProjectsForACalender.toList().isEmpty());
    }

    /**
     * Method under test:
     * {@link ProjectService#getAllProjectsForACalender(Long, Pageable)}
     */
    @Test
    void testGetAllProjectsForACalender2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Optional<Calender> ofResult = Optional.of(calender);
        CalenderRepository calenderRepository = mock(CalenderRepository.class);
        when(calenderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CalenderService calenderService = new CalenderService(calenderRepository);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.findByCalender(Mockito.<Calender>any(), Mockito.<Pageable>any()))
                .thenThrow(new IllegalArgumentException("foo"));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new ProjectService(calenderService, userService, projectRepository, null, loggedInUserService,
                        secureRandomStringGenerator, new ApplicationProperties())).getAllProjectsForACalender(1L, null));
        verify(projectRepository).findByCalender(isA(Calender.class), isNull());
        verify(calenderRepository).findById(isA(Long.class));
    }

    /**
     * Method under test:
     * {@link ProjectService#getAllProjectsForACalender(Long, Pageable)}
     */
    @Test
    void testGetAllProjectsForACalender3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        CalenderService calenderService = mock(CalenderService.class);
        when(calenderService.findById(Mockito.<Long>any())).thenReturn(calender);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.findByCalender(Mockito.<Calender>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();

        // Act
        Page<ProjectResponse> actualAllProjectsForACalender = (new ProjectService(calenderService, userService,
                projectRepository, null, loggedInUserService, secureRandomStringGenerator, new ApplicationProperties()))
                .getAllProjectsForACalender(1L, null);

        // Assert
        verify(projectRepository).findByCalender(isA(Calender.class), isNull());
        verify(calenderService).findById(isA(Long.class));
        assertTrue(actualAllProjectsForACalender.toList().isEmpty());
    }

    /**
     * Method under test:
     * {@link ProjectService#getAllProjectsForACalender(Long, Pageable)}
     */
    @Test
    void testGetAllProjectsForACalender4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        CalenderService calenderService = mock(CalenderService.class);
        when(calenderService.findById(Mockito.<Long>any())).thenReturn(calender);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        project.setCalender(calender2);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        ArrayList<Project> content = new ArrayList<>();
        content.add(project);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.findByCalender(Mockito.<Calender>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(content));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();

        // Act
        Page<ProjectResponse> actualAllProjectsForACalender = (new ProjectService(calenderService, userService,
                projectRepository, null, loggedInUserService, secureRandomStringGenerator, new ApplicationProperties()))
                .getAllProjectsForACalender(1L, null);

        // Assert
        verify(projectRepository).findByCalender(isA(Calender.class), isNull());
        verify(calenderService).findById(isA(Long.class));
        assertEquals(1, actualAllProjectsForACalender.toList().size());
    }

    /**
     * Method under test:
     * {@link ProjectService#getAllProjectsForACalender(Long, Pageable)}
     */
    @Test
    void testGetAllProjectsForACalender5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        CalenderService calenderService = mock(CalenderService.class);
        when(calenderService.findById(Mockito.<Long>any())).thenReturn(calender);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        project.setCalender(calender2);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        Calender calender3 = new Calender();
        calender3.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender3.setId(2L);
        calender3.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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
        user2.setUserName("com.discoverme.backend.user.Users");

        Project project2 = new Project();
        project2.setCalender(calender3);
        project2.setContentUri("Content Uri");
        project2.setId(2L);
        project2.setSocial(social2);
        project2.setSongTitle("Mr");
        project2.setSongUri("com.discoverme.backend.project.Project");
        project2.setSupportCount(2);
        project2.setUrl("Url");
        project2.setUser(user2);

        ArrayList<Project> content = new ArrayList<>();
        content.add(project2);
        content.add(project);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.findByCalender(Mockito.<Calender>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(content));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();

        // Act
        Page<ProjectResponse> actualAllProjectsForACalender = (new ProjectService(calenderService, userService,
                projectRepository, null, loggedInUserService, secureRandomStringGenerator, new ApplicationProperties()))
                .getAllProjectsForACalender(1L, null);

        // Assert
        verify(projectRepository).findByCalender(isA(Calender.class), isNull());
        verify(calenderService).findById(isA(Long.class));
        assertEquals(2, actualAllProjectsForACalender.toList().size());
    }

    /**
     * Method under test:
     * {@link ProjectService#getAllProjectsForACalender(Long, Pageable)}
     */
    @Test
    void testGetAllProjectsForACalender6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        CalenderService calenderService = mock(CalenderService.class);
        when(calenderService.findById(Mockito.<Long>any())).thenReturn(calender);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(1L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

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

        Socials socials = new Socials();
        socials.setId(1L);
        socials.setName("Name");

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        Project project = mock(Project.class);
        when(project.getSocial()).thenReturn(socials);
        when(project.getUser()).thenReturn(users);
        when(project.getId()).thenReturn(1L);
        when(project.getContentUri()).thenReturn("Not all who wander are lost");
        when(project.getSongTitle()).thenReturn("Dr");
        when(project.getSongUri()).thenReturn("Song Uri");
        when(project.getUrl()).thenReturn("https://example.org/example");
        doNothing().when(project).setCalender(Mockito.<Calender>any());
        doNothing().when(project).setContentUri(Mockito.<String>any());
        doNothing().when(project).setId(Mockito.<Long>any());
        doNothing().when(project).setSocial(Mockito.<Socials>any());
        doNothing().when(project).setSongTitle(Mockito.<String>any());
        doNothing().when(project).setSongUri(Mockito.<String>any());
        doNothing().when(project).setSupportCount(Mockito.<Integer>any());
        doNothing().when(project).setUrl(Mockito.<String>any());
        doNothing().when(project).setUser(Mockito.<Users>any());
        project.setCalender(calender2);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        ArrayList<Project> content = new ArrayList<>();
        content.add(project);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.findByCalender(Mockito.<Calender>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(content));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();

        // Act
        Page<ProjectResponse> actualAllProjectsForACalender = (new ProjectService(calenderService, userService,
                projectRepository, null, loggedInUserService, secureRandomStringGenerator, new ApplicationProperties()))
                .getAllProjectsForACalender(1L, null);

        // Assert
        verify(project).getContentUri();
        verify(project).getId();
        verify(project).getSocial();
        verify(project).getSongTitle();
        verify(project).getSongUri();
        verify(project).getUrl();
        verify(project).getUser();
        verify(project).setCalender(isA(Calender.class));
        verify(project).setContentUri(eq("Not all who wander are lost"));
        verify(project).setId(isA(Long.class));
        verify(project).setSocial(isA(Socials.class));
        verify(project).setSongTitle(eq("Dr"));
        verify(project).setSongUri(eq("Song Uri"));
        verify(project).setSupportCount(isA(Integer.class));
        verify(project).setUrl(eq("https://example.org/example"));
        verify(project).setUser(isA(Users.class));
        verify(projectRepository).findByCalender(isA(Calender.class), isNull());
        verify(calenderService).findById(isA(Long.class));
        assertEquals(1, actualAllProjectsForACalender.toList().size());
    }

    /**
     * Method under test: {@link ProjectService#isProjectLimitExceeded()}
     */
    @Test
    void testIsProjectLimitExceeded() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.count()).thenThrow(new IllegalArgumentException("foo"));
        CalenderService calenderService = new CalenderService(mock(CalenderRepository.class));
        UserService userService = new UserService(mock(UserRepository.class));
        ProjectRepository projectRepository2 = mock(ProjectRepository.class);
        LoggedInUserService loggedInUserService = new LoggedInUserService(projectRepository2,
                new UserService(mock(UserRepository.class)), mock(SupportRepository.class));

        SecureRandomStringGenerator secureRandomStringGenerator = new SecureRandomStringGenerator();

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new ProjectService(calenderService, userService, projectRepository, null, loggedInUserService,
                        secureRandomStringGenerator, new ApplicationProperties())).isProjectLimitExceeded());
        verify(projectRepository).count();
    }
}
