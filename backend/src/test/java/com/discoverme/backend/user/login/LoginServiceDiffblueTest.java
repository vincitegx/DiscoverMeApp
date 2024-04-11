package com.discoverme.backend.user.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discoverme.backend.security.JWTUtil;
import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.UserMapper;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;

import java.time.LocalDate;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LoginService.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class LoginServiceDiffblueTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JWTUtil jWTUtil;

    @Autowired
    private LoginService loginService;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link LoginService#login(LoginRequest)}
     */
    @Test
    void testLogin() throws AuthenticationException {
        // Arrange
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new BearerTokenAuthenticationToken("ABC123"));
        UserDto buildResult = UserDto.builder()
                .email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .stageName("Stage Name")
                .build();
        when(userMapper.apply(Mockito.<Users>any())).thenReturn(buildResult);
        when(jWTUtil.generateJwtToken(Mockito.<Authentication>any())).thenReturn("ABC123");

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

        LoginRequest request = new LoginRequest();
        request.setEmail("jane.doe@example.org");
        request.setPassword("iloveyou");

        // Act
        JwtResponse actualLoginResult = loginService.login(request);

        // Assert
        verify(jWTUtil).generateJwtToken(isA(Authentication.class));
        verify(userMapper).apply(isA(Users.class));
        verify(userService).getCurrentUser();
        verify(authenticationManager).authenticate(isA(Authentication.class));
        assertEquals("ABC123", actualLoginResult.getAuthToken());
        assertEquals("jane.doe@example.org", actualLoginResult.getUser().getEmail());
    }

    /**
     * Method under test: {@link LoginService#login(LoginRequest)}
     */
    @Test
    void testLogin2() throws AuthenticationException {
        // Arrange
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new BearerTokenAuthenticationToken("ABC123"));
        when(jWTUtil.generateJwtToken(Mockito.<Authentication>any())).thenReturn("ABC123");
        when(userService.getCurrentUser()).thenThrow(new AccountExpiredException("Msg"));

        LoginRequest request = new LoginRequest();
        request.setEmail("jane.doe@example.org");
        request.setPassword("iloveyou");

        // Act and Assert
        assertThrows(AccountExpiredException.class, () -> loginService.login(request));
        verify(jWTUtil).generateJwtToken(isA(Authentication.class));
        verify(userService).getCurrentUser();
        verify(authenticationManager).authenticate(isA(Authentication.class));
    }
}
