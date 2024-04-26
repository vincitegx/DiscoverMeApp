package com.discoverme.backend.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerDiffblueTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#all(Pageable)}
     */
    @Test
    void testAll() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        UserService userService = new UserService(userRepository, new UserMapper());

        // Act
        ResponseEntity<Page<UserDto>> actualAllResult = (new UserController(userService, new UserMapper())).all(null);

        // Assert
        verify(userRepository).findAll((Pageable) isNull());
        assertEquals(200, actualAllResult.getStatusCodeValue());
        assertTrue(actualAllResult.getBody().toList().isEmpty());
        assertTrue(actualAllResult.hasBody());
        assertTrue(actualAllResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link UserController#all(Pageable)}
     */
    @Test
    void testAll2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

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

        ArrayList<Users> content = new ArrayList<>();
        content.add(users);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));
        UserService userService = new UserService(userRepository, new UserMapper());

        // Act
        ResponseEntity<Page<UserDto>> actualAllResult = (new UserController(userService, new UserMapper())).all(null);

        // Assert
        verify(userRepository).findAll((Pageable) isNull());
        List<UserDto> toListResult = actualAllResult.getBody().toList();
        assertEquals(1, toListResult.size());
        UserDto getResult = toListResult.get(0);
        assertEquals("Role", getResult.getRole());
        assertEquals("User Name", getResult.getUserName());
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals(200, actualAllResult.getStatusCodeValue());
        assertTrue(actualAllResult.hasBody());
        assertTrue(actualAllResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link UserController#all(Pageable)}
     */
    @Test
    void testAll3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

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
        users2.setEmail("john.smith@example.org");
        users2.setEnabled(false);
        users2.setId(2L);
        users2.setNonLocked(false);
        users2.setPassword("Password");
        users2.setRole("com.discoverme.backend.user.Users");
        users2.setUserName("com.discoverme.backend.user.Users");

        ArrayList<Users> content = new ArrayList<>();
        content.add(users2);
        content.add(users);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));
        UserService userService = new UserService(userRepository,new UserMapper());

        // Act
        ResponseEntity<Page<UserDto>> actualAllResult = (new UserController(userService, new UserMapper())).all(null);

        // Assert
        verify(userRepository).findAll((Pageable) isNull());
        List<UserDto> toListResult = actualAllResult.getBody().toList();
        assertEquals(2, toListResult.size());
        UserDto getResult = toListResult.get(1);
        assertEquals("Role", getResult.getRole());
        assertEquals("User Name", getResult.getUserName());
        UserDto getResult2 = toListResult.get(0);
        assertEquals("com.discoverme.backend.user.Users", getResult2.getRole());
        assertEquals("com.discoverme.backend.user.Users", getResult2.getUserName());
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertEquals("john.smith@example.org", getResult2.getEmail());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals(200, actualAllResult.getStatusCodeValue());
        assertEquals(2L, getResult2.getId().longValue());
        assertTrue(actualAllResult.hasBody());
        assertTrue(actualAllResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link UserController#all(Pageable)}
     */
    @Test
    void testAll4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        UserService userService = mock(UserService.class);
        when(userService.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act
        ResponseEntity<Page<UserDto>> actualAllResult = (new UserController(userService, new UserMapper())).all(null);

        // Assert
        verify(userService).findAll(isNull());
        assertEquals(200, actualAllResult.getStatusCodeValue());
        assertTrue(actualAllResult.getBody().toList().isEmpty());
        assertTrue(actualAllResult.hasBody());
        assertTrue(actualAllResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link UserController#all(Pageable)}
     */
    @Test
    void testAll5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

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

        ArrayList<Users> content = new ArrayList<>();
        content.add(users);
        UserService userService = mock(UserService.class);
        when(userService.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));
        UserMapper userMapper = mock(UserMapper.class);
        UserDto buildResult = UserDto.builder()
                .email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .userName("User Name")
                .build();
        when(userMapper.apply(Mockito.<Users>any())).thenReturn(buildResult);

        // Act
        ResponseEntity<Page<UserDto>> actualAllResult = (new UserController(userService, userMapper)).all(null);

        // Assert
        verify(userMapper).apply(isA(Users.class));
        verify(userService).findAll(isNull());
        assertEquals(1, actualAllResult.getBody().toList().size());
        assertEquals(200, actualAllResult.getStatusCodeValue());
        assertTrue(actualAllResult.hasBody());
        assertTrue(actualAllResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link UserController#allUsers(Pageable)}
     */
    @Test
    void testAllUsers() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByRole(Mockito.<String>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        UserService userService = new UserService(userRepository, new UserMapper());

        // Act
        ResponseEntity<Page<UserDto>> actualAllUsersResult = (new UserController(userService, new UserMapper()))
                .allUsers(null);

        // Assert
        verify(userRepository).findByRole(eq("USER"), isNull());
        assertEquals(200, actualAllUsersResult.getStatusCodeValue());
        assertTrue(actualAllUsersResult.getBody().toList().isEmpty());
        assertTrue(actualAllUsersResult.hasBody());
        assertTrue(actualAllUsersResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link UserController#allUsers(Pageable)}
     */
    @Test
    void testAllUsers2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

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

        ArrayList<Users> content = new ArrayList<>();
        content.add(users);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByRole(Mockito.<String>any(), Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));
        UserService userService = new UserService(userRepository, new UserMapper());

        // Act
        ResponseEntity<Page<UserDto>> actualAllUsersResult = (new UserController(userService, new UserMapper()))
                .allUsers(null);

        // Assert
        verify(userRepository).findByRole(eq("USER"), isNull());
        List<UserDto> toListResult = actualAllUsersResult.getBody().toList();
        assertEquals(1, toListResult.size());
        UserDto getResult = toListResult.get(0);
        assertEquals("Role", getResult.getRole());
        assertEquals("User Name", getResult.getUserName());
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals(200, actualAllUsersResult.getStatusCodeValue());
        assertTrue(actualAllUsersResult.hasBody());
        assertTrue(actualAllUsersResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link UserController#allUsers(Pageable)}
     */
    @Test
    void testAllUsers3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

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
        users2.setEmail("john.smith@example.org");
        users2.setEnabled(false);
        users2.setId(2L);
        users2.setNonLocked(false);
        users2.setPassword("Password");
        users2.setRole("com.discoverme.backend.user.Users");
        users2.setUserName("com.discoverme.backend.user.Users");

        ArrayList<Users> content = new ArrayList<>();
        content.add(users2);
        content.add(users);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByRole(Mockito.<String>any(), Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));
        UserService userService = new UserService(userRepository, new UserMapper());

        // Act
        ResponseEntity<Page<UserDto>> actualAllUsersResult = (new UserController(userService, new UserMapper()))
                .allUsers(null);

        // Assert
        verify(userRepository).findByRole(eq("USER"), isNull());
        List<UserDto> toListResult = actualAllUsersResult.getBody().toList();
        assertEquals(2, toListResult.size());
        UserDto getResult = toListResult.get(1);
        assertEquals("Role", getResult.getRole());
        assertEquals("User Name", getResult.getUserName());
        UserDto getResult2 = toListResult.get(0);
        assertEquals("com.discoverme.backend.user.Users", getResult2.getRole());
        assertEquals("com.discoverme.backend.user.Users", getResult2.getUserName());
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertEquals("john.smith@example.org", getResult2.getEmail());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals(200, actualAllUsersResult.getStatusCodeValue());
        assertEquals(2L, getResult2.getId().longValue());
        assertTrue(actualAllUsersResult.hasBody());
        assertTrue(actualAllUsersResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link UserController#allUsers(Pageable)}
     */
    @Test
    void testAllUsers4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        UserService userService = mock(UserService.class);
        when(userService.findAllByRoleUser(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act
        ResponseEntity<Page<UserDto>> actualAllUsersResult = (new UserController(userService, new UserMapper()))
                .allUsers(null);

        // Assert
        verify(userService).findAllByRoleUser(isNull());
        assertEquals(200, actualAllUsersResult.getStatusCodeValue());
        assertTrue(actualAllUsersResult.getBody().toList().isEmpty());
        assertTrue(actualAllUsersResult.hasBody());
        assertTrue(actualAllUsersResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link UserController#allUsers(Pageable)}
     */
    @Test
    void testAllUsers5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

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

        ArrayList<Users> content = new ArrayList<>();
        content.add(users);
        UserService userService = mock(UserService.class);
        when(userService.findAllByRoleUser(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));
        UserMapper userMapper = mock(UserMapper.class);
        UserDto buildResult = UserDto.builder()
                .email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .userName("User Name")
                .build();
        when(userMapper.apply(Mockito.<Users>any())).thenReturn(buildResult);

        // Act
        ResponseEntity<Page<UserDto>> actualAllUsersResult = (new UserController(userService, userMapper)).allUsers(null);

        // Assert
        verify(userMapper).apply(isA(Users.class));
        verify(userService).findAllByRoleUser(isNull());
        assertEquals(1, actualAllUsersResult.getBody().toList().size());
        assertEquals(200, actualAllUsersResult.getStatusCodeValue());
        assertTrue(actualAllUsersResult.hasBody());
        assertTrue(actualAllUsersResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link UserController#findById(String)}
     */
    @Test
    void testFindById() throws Exception {
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
        Optional<Users> ofResult = Optional.of(users);
        when(userService.findById(Mockito.<Long>any())).thenReturn(ofResult);
        UserDto buildResult = UserDto.builder()
                .email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .userName("User Name")
                .build();
        when(userMapper.apply(Mockito.<Users>any())).thenReturn(buildResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/42").param("id", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"UserName\":\"User Name\",\"email\":\"jane.doe@example.org\",\"role\":\"Role\"}"));
    }

    /**
     * Method under test: {@link UserController#removeAdmin(String)}
     */
    @Test
    void testRemoveAdmin() throws Exception {
        // Arrange
        doNothing().when(userService).removeAdmin(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/users/admin")
                .param("userId", "42");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link UserController#blockUserAccount(String)}
     */
    @Test
    void testBlockUserAccount() throws Exception {
        // Arrange
        when(userService.fetchAndDisableUser(Mockito.<Long>any())).thenReturn("Fetch And Disable User");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/users/block").param("id", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Fetch And Disable User"));
    }

    /**
     * Method under test: {@link UserController#me()}
     */
    @Test
    void testMe() throws Exception {
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
        when(userService.getCurrentUser()).thenReturn(users);
        UserDto buildResult = UserDto.builder()
                .email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .userName("User Name")
                .build();
        when(userMapper.apply(Mockito.<Users>any())).thenReturn(buildResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/me");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"UserName\":\"User Name\",\"email\":\"jane.doe@example.org\",\"role\":\"Role\"}"));
    }
}
