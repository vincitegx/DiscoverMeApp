package com.discoverme.backend.user.login;

import com.discoverme.backend.security.JWTAuthenticationFilter;
import com.discoverme.backend.user.UserDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final LoginService loginService;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final HttpServletRequest request;

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


    @PostMapping("logout")
    public ResponseEntity<Boolean> logout(@Valid @RequestBody UserDto user) {
        boolean isLoggedOut = false;
        Optional<Cookie> cookies = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .filter(cookie -> JWTAuthenticationFilter.COOKIE_NAME.equals(cookie.getName()))
                .findFirst();
        if(cookies.isPresent()){
            RefreshToken refreshToken = refreshTokenService.validateRefreshToken(user, cookies.get().getValue());
            if(refreshToken != null){
                SecurityContextHolder.clearContext();
                refreshTokenService.deleteRefreshToken(refreshToken.getToken());
                isLoggedOut =true;
            }else{
                SecurityContextHolder.clearContext();
            }
        }
        return new ResponseEntity<>(isLoggedOut, HttpStatus.OK);
    }
}
