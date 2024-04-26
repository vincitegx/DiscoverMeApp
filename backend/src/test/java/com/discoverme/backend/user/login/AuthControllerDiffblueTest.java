package com.discoverme.backend.user.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.discoverme.backend.security.JWTUtil;
import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.UserMapper;
import com.discoverme.backend.user.UserRepository;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import com.discoverme.backend.user.login.refresh.RefreshTokenRepository;
import com.discoverme.backend.user.login.refresh.RefreshTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import javax.security.auth.RefreshFailedException;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthController.class})
@ExtendWith(SpringExtension.class)
class AuthControllerDiffblueTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private HttpServletRequest httpServletRequest;

    @MockBean
    private LoginService loginService;

    @MockBean
    private RefreshTokenService refreshTokenService;

    @MockBean
    private UserService userService;

    /**
     * Method under test:
     * {@link AuthController#logout(UserDto, HttpServletResponse)}
     */
    @Test
    void testLogout() throws RefreshFailedException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserMapper userDtoMapper = new UserMapper();
        JWTUtil jwtUtil = new JWTUtil();
        LoginService loginService = new LoginService(authenticationManager, userDtoMapper, jwtUtil,
                new UserService(mock(UserRepository.class)));

        UserService userService = new UserService(mock(UserRepository.class));
        RefreshTokenRepository refreshTokenRepository = mock(RefreshTokenRepository.class);
        JWTUtil jwtUtil2 = new JWTUtil();
        RefreshTokenService refreshTokenService = new RefreshTokenService(refreshTokenRepository, jwtUtil2,
                new UserMapper());

        AuthController authController = new AuthController(loginService, userService, refreshTokenService,
                new MockHttpServletRequest());

        // Act
        ResponseEntity<Boolean> actualLogoutResult = authController.logout(null, new Response());

        // Assert
        assertEquals(200, actualLogoutResult.getStatusCodeValue());
        assertTrue(actualLogoutResult.getBody());
        assertTrue(actualLogoutResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link AuthController#userLogin(LoginRequest, HttpServletResponse)}
     */
    @Test
    void testUserLogin() throws Exception {
        // Arrange
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").UserName("User Name").build();
        when(loginService.login(Mockito.<LoginRequest>any())).thenReturn(new JwtResponse("ABC123", user));

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
        when(refreshTokenService.generateRefreshToken(Mockito.<Users>any())).thenReturn("ABC123");

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("jane.doe@example.org");
        loginRequest.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"authToken\":\"ABC123\",\"user\":{\"id\":1,\"UserName\":\"User Name\",\"email\":\"jane.doe@example.org\",\"role"
                                        + "\":\"Role\"}}"));
        ResultActions resultActions2 = resultActions
                .andExpect(MockMvcResultMatchers.cookie().value("refresh-token", "ABC123"));
        ResultActions resultActions3 = resultActions2
                .andExpect(MockMvcResultMatchers.cookie().secure("refresh-token", true));
        ResultActions resultActions4 = resultActions3
                .andExpect(MockMvcResultMatchers.cookie().httpOnly("refresh-token", true));
        resultActions4.andExpect(MockMvcResultMatchers.cookie().maxAge("refresh-token", 86400));
    }
}
