package com.discoverme.backend.user.login;

import com.discoverme.backend.security.JWTAuthenticationFilter;
import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.UserMapper;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import com.discoverme.backend.user.login.refresh.RefreshToken;
import com.discoverme.backend.user.login.refresh.RefreshTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> userLogin(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse servletResponse) {
        JwtResponse response = loginService.login(loginRequest);
        Users user = userService.getCurrentUser();
        String refreshToken = refreshTokenService.generateRefreshToken(user);
        Cookie refreshTokenCookie = new Cookie(JWTAuthenticationFilter.COOKIE_NAME, refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setAttribute("SameSite", "None");
        refreshTokenCookie.setMaxAge((int) Duration.of(1, ChronoUnit.DAYS).toSeconds());
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setAttribute("Priority", "High");
        servletResponse.addCookie(refreshTokenCookie);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, response.getAuthToken())
                .body(response);
    }

    @GetMapping("is-logged-in")
    public ResponseEntity<Boolean> isLoggedIn(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        boolean isLoggedIn = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (JWTAuthenticationFilter.COOKIE_NAME.equals(cookie.getName())) {
                    System.out.println("Refresh Token exists: "+ cookie.getValue());
                    if (isValidToken(cookie.getValue())) {
                        isLoggedIn = true;
                        break;
                    }
                }
            }
        }
        return ResponseEntity.ok(isLoggedIn);
    }

    private boolean isValidToken(String token) {
        Users user = userService.getCurrentUser();
        UserMapper userMapper = new UserMapper();
        UserDto userDto = userMapper.apply(user);
        RefreshToken refreshToken = refreshTokenService.validateRefreshToken(userDto, token);
        return refreshToken != null;
    }
}
