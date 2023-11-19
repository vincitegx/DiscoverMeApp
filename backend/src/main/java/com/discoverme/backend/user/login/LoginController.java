package com.discoverme.backend.user.login;

import com.discoverme.backend.security.JWTAuthenticationFilter;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import com.discoverme.backend.user.login.refresh.RefreshTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

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
        System.out.println(user);
        String refreshToken = refreshTokenService.generateRefreshToken(user);
        Cookie refreshTokenCookie = new Cookie(JWTAuthenticationFilter.COOKIE_NAME, refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setAttribute("SameSite", "strict");
        refreshTokenCookie.setMaxAge((int) Duration.of(1, ChronoUnit.DAYS).toSeconds());
        refreshTokenCookie.setPath("/");
        servletResponse.addCookie(refreshTokenCookie);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, response.getAuthToken())
                .body(response);
    }
}
