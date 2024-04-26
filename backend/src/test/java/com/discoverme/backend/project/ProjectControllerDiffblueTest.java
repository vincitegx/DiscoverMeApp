package com.discoverme.backend.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
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

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {ProjectController.class, ApplicationProperties.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class ProjectControllerDiffblueTest {
    @Autowired
    private ProjectController projectController;

    @MockBean
    private ProjectMapper projectMapper;

    @MockBean
    private ProjectService projectService;

    /**
     * Method under test:
     * {@link ProjectController#submitProject(String, MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSubmitProject() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Unable to load class.
        //   Class: javax.servlet.http.Part
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        ProjectController projectController = null;
        String projectRequest = "";
        MultipartFile content = null;

        // Act
        ResponseEntity<ProjectResponse> actualSubmitProjectResult = projectController.submitProject(projectRequest,
                content);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link ProjectController#isProjectLimitExceeded()}
     */
    @Test
    void testIsProjectLimitExceeded() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ProjectService projectService = mock(ProjectService.class);
        when(projectService.isProjectLimitExceeded()).thenReturn(true);
        ProjectMapper projectMapper = new ProjectMapper();

        // Act
        ResponseEntity<Boolean> actualIsProjectLimitExceededResult = (new ProjectController(projectService, projectMapper,
                new ApplicationProperties())).isProjectLimitExceeded();

        // Assert
        verify(projectService).isProjectLimitExceeded();
        assertEquals(200, actualIsProjectLimitExceededResult.getStatusCodeValue());
        assertTrue(actualIsProjectLimitExceededResult.getBody());
        assertTrue(actualIsProjectLimitExceededResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ProjectController#getAllProjectsForACalender(Long, Pageable)}
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
        ProjectService projectService = new ProjectService(calenderService, userService, projectRepository, null,
                loggedInUserService, secureRandomStringGenerator, new ApplicationProperties());

        ProjectMapper projectMapper = new ProjectMapper();

        // Act
        ResponseEntity<Page<ProjectResponse>> actualAllProjectsForACalender = (new ProjectController(projectService,
                projectMapper, new ApplicationProperties())).getAllProjectsForACalender(1L, null);

        // Assert
        verify(projectRepository).findByCalender(isA(Calender.class), isNull());
        verify(calenderRepository).findById(isA(Long.class));
        assertEquals(200, actualAllProjectsForACalender.getStatusCodeValue());
        assertTrue(actualAllProjectsForACalender.getBody().toList().isEmpty());
        assertTrue(actualAllProjectsForACalender.hasBody());
        assertTrue(actualAllProjectsForACalender.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ProjectController#getAllProjectsForACalender(Long, Pageable)}
     */
    @Test
    void testGetAllProjectsForACalender2() {
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
        ProjectService projectService = new ProjectService(calenderService, userService, projectRepository, null,
                loggedInUserService, secureRandomStringGenerator, new ApplicationProperties());

        ProjectMapper projectMapper = new ProjectMapper();

        // Act
        ResponseEntity<Page<ProjectResponse>> actualAllProjectsForACalender = (new ProjectController(projectService,
                projectMapper, new ApplicationProperties())).getAllProjectsForACalender(1L, null);

        // Assert
        verify(projectRepository).findByCalender(isA(Calender.class), isNull());
        verify(calenderService).findById(isA(Long.class));
        assertEquals(200, actualAllProjectsForACalender.getStatusCodeValue());
        assertTrue(actualAllProjectsForACalender.getBody().toList().isEmpty());
        assertTrue(actualAllProjectsForACalender.hasBody());
        assertTrue(actualAllProjectsForACalender.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ProjectController#getAllProjectsForACalender(Long, Pageable)}
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
        ProjectService projectService = new ProjectService(calenderService, userService, projectRepository, null,
                loggedInUserService, secureRandomStringGenerator, new ApplicationProperties());

        ProjectMapper projectMapper = new ProjectMapper();

        // Act
        ResponseEntity<Page<ProjectResponse>> actualAllProjectsForACalender = (new ProjectController(projectService,
                projectMapper, new ApplicationProperties())).getAllProjectsForACalender(1L, null);

        // Assert
        verify(projectRepository).findByCalender(isA(Calender.class), isNull());
        verify(calenderService).findById(isA(Long.class));
        assertEquals(1, actualAllProjectsForACalender.getBody().toList().size());
        assertEquals(200, actualAllProjectsForACalender.getStatusCodeValue());
        assertTrue(actualAllProjectsForACalender.hasBody());
        assertTrue(actualAllProjectsForACalender.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ProjectController#getAllProjectsForACalender(Long, Pageable)}
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
        ProjectService projectService = new ProjectService(calenderService, userService, projectRepository, null,
                loggedInUserService, secureRandomStringGenerator, new ApplicationProperties());

        ProjectMapper projectMapper = new ProjectMapper();

        // Act
        ResponseEntity<Page<ProjectResponse>> actualAllProjectsForACalender = (new ProjectController(projectService,
                projectMapper, new ApplicationProperties())).getAllProjectsForACalender(1L, null);

        // Assert
        verify(projectRepository).findByCalender(isA(Calender.class), isNull());
        verify(calenderService).findById(isA(Long.class));
        assertEquals(2, actualAllProjectsForACalender.getBody().toList().size());
        assertEquals(200, actualAllProjectsForACalender.getStatusCodeValue());
        assertTrue(actualAllProjectsForACalender.hasBody());
        assertTrue(actualAllProjectsForACalender.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ProjectController#getAllProjectsForACalender(Long, Pageable)}
     */
    @Test
    void testGetAllProjectsForACalender5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ProjectService projectService = mock(ProjectService.class);
        when(projectService.getAllProjectsForACalender(Mockito.<Long>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        ProjectMapper projectMapper = new ProjectMapper();

        // Act
        ResponseEntity<Page<ProjectResponse>> actualAllProjectsForACalender = (new ProjectController(projectService,
                projectMapper, new ApplicationProperties())).getAllProjectsForACalender(1L, null);

        // Assert
        verify(projectService).getAllProjectsForACalender(isA(Long.class), isNull());
        assertEquals(200, actualAllProjectsForACalender.getStatusCodeValue());
        assertTrue(actualAllProjectsForACalender.getBody().toList().isEmpty());
        assertTrue(actualAllProjectsForACalender.hasBody());
        assertTrue(actualAllProjectsForACalender.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ProjectController#deleteProject(Long)}
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
        ProjectService projectService = new ProjectService(calenderService, userService, projectRepository, null,
                loggedInUserService, secureRandomStringGenerator, new ApplicationProperties());

        ProjectMapper projectMapper = new ProjectMapper();

        // Act
        ResponseEntity<Void> actualDeleteProjectResult = (new ProjectController(projectService, projectMapper,
                new ApplicationProperties())).deleteProject(1L);

        // Assert
        verify(projectRepository).delete(isA(Project.class));
        verify(projectRepository).findById(isA(Long.class));
        assertNull(actualDeleteProjectResult.getBody());
        assertEquals(200, actualDeleteProjectResult.getStatusCodeValue());
        assertTrue(actualDeleteProjectResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ProjectController#deleteProject(Long)}
     */
    @Test
    void testDeleteProject2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ProjectService projectService = mock(ProjectService.class);
        doNothing().when(projectService).deleteProject(Mockito.<Long>any());
        ProjectMapper projectMapper = new ProjectMapper();

        // Act
        ResponseEntity<Void> actualDeleteProjectResult = (new ProjectController(projectService, projectMapper,
                new ApplicationProperties())).deleteProject(1L);

        // Assert
        verify(projectService).deleteProject(isA(Long.class));
        assertNull(actualDeleteProjectResult.getBody());
        assertEquals(200, actualDeleteProjectResult.getStatusCodeValue());
        assertTrue(actualDeleteProjectResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ProjectController#getCurrentProjects(String, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCurrentProjects() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing Spring properties.
        //   Failed to create Spring context due to unresolvable @Value
        //   properties: private java.lang.Long com.discoverme.backend.config.ApplicationProperties.jwtValidity
        //   Please check that at least one of the property files is provided
        //   and contains required variables:
        //   - application-test.properties (doesn't contain the required variables)
        //   See https://diff.blue/R033 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/projects");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("page", String.valueOf(1)).param("search", "foo");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(projectController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }
}
