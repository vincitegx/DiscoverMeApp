package com.discoverme.backend.project.support;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discoverme.backend.project.Project;
import com.discoverme.backend.project.ProjectService;
import com.discoverme.backend.project.calender.Calender;
import com.discoverme.backend.social.Socials;
import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.UserMapper;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import com.discoverme.backend.social.facebook.FacebookService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SupportService.class})
@ExtendWith(SpringExtension.class)
class SupportServiceDiffblueTest {
    @MockBean
    private FacebookService facebookService;

    @MockBean
    private ProjectService projectService;

    @MockBean
    private SupportRepository supportRepository;

    @Autowired
    private SupportService supportService;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link SupportService#getProjectSupporters(Long)}
     */
    @Test
    void testGetProjectSupporters() {
        // Arrange
        when(supportRepository.findByProject(Mockito.<Project>any())).thenReturn(new ArrayList<>());

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
        when(projectService.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        List<UserDto> actualProjectSupporters = supportService.getProjectSupporters(1L);

        // Assert
        verify(projectService).findById(isA(Long.class));
        verify(supportRepository).findByProject(isA(Project.class));
        assertTrue(actualProjectSupporters.isEmpty());
    }

    /**
     * Method under test: {@link SupportService#getProjectSupporters(Long)}
     */
    @Test
    void testGetProjectSupporters2() {
        // Arrange
        when(supportRepository.findByProject(Mockito.<Project>any())).thenThrow(new RuntimeException("foo"));

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
        when(projectService.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> supportService.getProjectSupporters(1L));
        verify(projectService).findById(isA(Long.class));
        verify(supportRepository).findByProject(isA(Project.class));
    }

    /**
     * Method under test: {@link SupportService#getProjectSupporters(Long)}
     */
    @Test
    void testGetProjectSupporters3() {
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

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserName("User Name");

        Support support = new Support();
        support.setId(1L);
        support.setProject(project);
        support.setUser(user2);

        ArrayList<Support> supportList = new ArrayList<>();
        supportList.add(support);
        when(supportRepository.findByProject(Mockito.<Project>any())).thenReturn(supportList);
        UserDto buildResult = UserDto.builder()
                .email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();
        when(userMapper.apply(Mockito.<Users>any())).thenReturn(buildResult);

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
        user3.setUserName("User Name");

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
        Optional<Project> ofResult = Optional.of(project2);
        when(projectService.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        List<UserDto> actualProjectSupporters = supportService.getProjectSupporters(1L);

        // Assert
        verify(projectService).findById(isA(Long.class));
        verify(supportRepository).findByProject(isA(Project.class));
        verify(userMapper).apply(isA(Users.class));
        assertEquals(1, actualProjectSupporters.size());
    }

    /**
     * Method under test: {@link SupportService#getProjectSupporters(Long)}
     */
    @Test
    void testGetProjectSupporters4() {
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

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserName("User Name");

        Support support = new Support();
        support.setId(1L);
        support.setProject(project);
        support.setUser(user2);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(2L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social2 = new Socials();
        social2.setId(2L);
        social2.setName("com.discoverme.backend.social.Socials");

        Users user3 = new Users();
        user3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user3.setEmail("john.smith@example.org");
        user3.setEnabled(false);
        user3.setId(2L);
        user3.setNonLocked(false);
        user3.setPassword("Password");
        user3.setRole("com.discoverme.backend.user.Users");
        user3.setUserName("com.discoverme.backend.user.Users");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Content Uri");
        project2.setId(2L);
        project2.setSocial(social2);
        project2.setSongTitle("Mr");
        project2.setSongUri("com.discoverme.backend.project.Project");
        project2.setSupportCount(1);
        project2.setUrl("Url");
        project2.setUser(user3);

        Users user4 = new Users();
        user4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user4.setEmail("john.smith@example.org");
        user4.setEnabled(false);
        user4.setId(2L);
        user4.setNonLocked(false);
        user4.setPassword("Password");
        user4.setRole("com.discoverme.backend.user.Users");
        user4.setUserName("com.discoverme.backend.user.Users");

        Support support2 = new Support();
        support2.setId(2L);
        support2.setProject(project2);
        support2.setUser(user4);

        ArrayList<Support> supportList = new ArrayList<>();
        supportList.add(support2);
        supportList.add(support);
        when(supportRepository.findByProject(Mockito.<Project>any())).thenReturn(supportList);
        UserDto buildResult = UserDto.builder()
                .email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();
        when(userMapper.apply(Mockito.<Users>any())).thenReturn(buildResult);

        Calender calender3 = new Calender();
        calender3.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender3.setId(1L);
        calender3.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social3 = new Socials();
        social3.setId(1L);
        social3.setName("Name");

        Users user5 = new Users();
        user5.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user5.setEmail("jane.doe@example.org");
        user5.setEnabled(true);
        user5.setId(1L);
        user5.setNonLocked(true);
        user5.setPassword("iloveyou");
        user5.setRole("Role");
        user5.setUserName("User Name");

        Project project3 = new Project();
        project3.setCalender(calender3);
        project3.setContentUri("Not all who wander are lost");
        project3.setId(1L);
        project3.setSocial(social3);
        project3.setSongTitle("Dr");
        project3.setSongUri("Song Uri");
        project3.setSupportCount(3);
        project3.setUrl("https://example.org/example");
        project3.setUser(user5);
        Optional<Project> ofResult = Optional.of(project3);
        when(projectService.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        List<UserDto> actualProjectSupporters = supportService.getProjectSupporters(1L);

        // Assert
        verify(projectService).findById(isA(Long.class));
        verify(supportRepository).findByProject(isA(Project.class));
        verify(userMapper, atLeast(1)).apply(Mockito.<Users>any());
        assertEquals(2, actualProjectSupporters.size());
    }

    /**
     * Method under test: {@link SupportService#getProjectSupporters(Long)}
     */
    @Test
    void testGetProjectSupporters5() {
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

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserName("User Name");

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        Support support = mock(Support.class);
        when(support.getUser()).thenReturn(users);
        doNothing().when(support).setId(Mockito.<Long>any());
        doNothing().when(support).setProject(Mockito.<Project>any());
        doNothing().when(support).setUser(Mockito.<Users>any());
        support.setId(1L);
        support.setProject(project);
        support.setUser(user2);

        ArrayList<Support> supportList = new ArrayList<>();
        supportList.add(support);
        when(supportRepository.findByProject(Mockito.<Project>any())).thenReturn(supportList);
        UserDto buildResult = UserDto.builder()
                .email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();
        when(userMapper.apply(Mockito.<Users>any())).thenReturn(buildResult);

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
        user3.setUserName("User Name");

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
        Optional<Project> ofResult = Optional.of(project2);
        when(projectService.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        List<UserDto> actualProjectSupporters = supportService.getProjectSupporters(1L);

        // Assert
        verify(projectService).findById(isA(Long.class));
        verify(support).getUser();
        verify(support).setId(isA(Long.class));
        verify(support).setProject(isA(Project.class));
        verify(support).setUser(isA(Users.class));
        verify(supportRepository).findByProject(isA(Project.class));
        verify(userMapper).apply(isA(Users.class));
        assertEquals(1, actualProjectSupporters.size());
    }

    /**
     * Method under test: {@link SupportService#toggleSupport(Long)}
     */
    @Test
    void testToggleSupport() {
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

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserName("User Name");

        Support support = new Support();
        support.setId(1L);
        support.setProject(project);
        support.setUser(user2);
        Optional<Support> ofResult = Optional.of(support);
        doNothing().when(supportRepository).delete(Mockito.<Support>any());
        when(supportRepository.findTopByProjectAndUserOrderByIdDesc(Mockito.<Project>any(), Mockito.<Users>any()))
                .thenReturn(ofResult);

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        when(userService.getCurrentUser()).thenReturn(users);

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
        user3.setUserName("User Name");

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
        Optional<Project> ofResult2 = Optional.of(project2);

        Calender calender3 = new Calender();
        calender3.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender3.setId(1L);
        calender3.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social3 = new Socials();
        social3.setId(1L);
        social3.setName("Name");

        Users user4 = new Users();
        user4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user4.setEmail("jane.doe@example.org");
        user4.setEnabled(true);
        user4.setId(1L);
        user4.setNonLocked(true);
        user4.setPassword("iloveyou");
        user4.setRole("Role");
        user4.setUserName("User Name");

        Project project3 = new Project();
        project3.setCalender(calender3);
        project3.setContentUri("Not all who wander are lost");
        project3.setId(1L);
        project3.setSocial(social3);
        project3.setSongTitle("Dr");
        project3.setSongUri("Song Uri");
        project3.setSupportCount(3);
        project3.setUrl("https://example.org/example");
        project3.setUser(user4);
        when(projectService.saveProject(Mockito.<Project>any())).thenReturn(project3);
        when(projectService.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        // Act
        supportService.toggleSupport(1L);

        // Assert
        verify(projectService).findById(isA(Long.class));
        verify(projectService).saveProject(isA(Project.class));
        verify(supportRepository).findTopByProjectAndUserOrderByIdDesc(isA(Project.class), isA(Users.class));
        verify(userService).getCurrentUser();
        verify(supportRepository).delete(isA(Support.class));
    }

    /**
     * Method under test: {@link SupportService#toggleSupport(Long)}
     */
    @Test
    void testToggleSupport2() {
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

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserName("User Name");

        Support support = new Support();
        support.setId(1L);
        support.setProject(project);
        support.setUser(user2);
        Optional<Support> ofResult = Optional.of(support);
        doNothing().when(supportRepository).delete(Mockito.<Support>any());
        when(supportRepository.findTopByProjectAndUserOrderByIdDesc(Mockito.<Project>any(), Mockito.<Users>any()))
                .thenReturn(ofResult);

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setUserName("User Name");
        when(userService.getCurrentUser()).thenReturn(users);

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
        user3.setUserName("User Name");

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
        Optional<Project> ofResult2 = Optional.of(project2);
        when(projectService.saveProject(Mockito.<Project>any())).thenThrow(new RuntimeException("foo"));
        when(projectService.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> supportService.toggleSupport(1L));
        verify(projectService).findById(isA(Long.class));
        verify(projectService).saveProject(isA(Project.class));
        verify(supportRepository).findTopByProjectAndUserOrderByIdDesc(isA(Project.class), isA(Users.class));
        verify(userService).getCurrentUser();
        verify(supportRepository).delete(isA(Support.class));
    }
}
