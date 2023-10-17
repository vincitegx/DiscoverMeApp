package com.discoverme.backend.user.login;

import com.discoverme.backend.security.CookieAuthenticationFilter;
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

    @PostMapping("login")
    public ResponseEntity<JwtResponse> userLogin(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse servletResponse) {
        JwtResponse response = loginService.login(loginRequest);
        Cookie refreshTokenCookie = new Cookie(CookieAuthenticationFilter.COOKIE_NAME, response.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setAttribute("SameSite", "strict");
        refreshTokenCookie.setMaxAge((int) Duration.of(1, ChronoUnit.DAYS).toSeconds());
        refreshTokenCookie.setPath("/");
        servletResponse.addCookie(refreshTokenCookie);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, response.getAuthToken())
                .body(new JwtResponse(response.getAuthToken(), response.getUser()));
    }
}
