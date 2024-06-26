package com.discoverme.backend.user.login;

import com.discoverme.backend.config.ApplicationProperties;
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

import java.time.Instant;
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
    private final ApplicationProperties applicationProperties;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> userLogin(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse servletResponse) {
        JwtResponse response = loginService.login(loginRequest);
        Users user = userService.getCurrentUser();
        String refreshToken = refreshTokenService.generateRefreshToken(user);
        long expirationTimeMillis = Instant.now().plusSeconds(applicationProperties.getRefreshTokenValidity()).toEpochMilli();
        Cookie refreshTokenCookie = new Cookie(applicationProperties.getRefreshTokenCookie(), refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setAttribute("SameSite", "None");
        refreshTokenCookie.setMaxAge((int) expirationTimeMillis);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setAttribute("Priority", "High");
        servletResponse.addCookie(refreshTokenCookie);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, response.getAuthToken())
                .body(response);
    }


    @PostMapping("logout")
    public ResponseEntity<Boolean> logout(@Valid @RequestBody UserDto user, HttpServletResponse servletResponse) {
        try{
            Optional<Cookie> cookies = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                    .filter(cookie -> applicationProperties.getRefreshTokenCookie().equals(cookie.getName()))
                    .findFirst();
            if(cookies.isPresent()){
                RefreshToken refreshToken = refreshTokenService.validateRefreshToken(user, cookies.get().getValue());
                if(refreshToken != null){
                    refreshTokenService.deleteRefreshToken(refreshToken.getToken());
                }
                Cookie refreshTokenCookie = new Cookie(applicationProperties.getRefreshTokenCookie(), cookies.get().getValue());
                refreshTokenCookie.setHttpOnly(true);
                refreshTokenCookie.setSecure(true);
                refreshTokenCookie.setAttribute("SameSite", "None");
                refreshTokenCookie.setMaxAge(0);
                refreshTokenCookie.setPath("/");
                refreshTokenCookie.setAttribute("Priority", "High");
                servletResponse.addCookie(refreshTokenCookie);
            }else {
                SecurityContextHolder.clearContext();
            }
        }catch (Exception ex){
            SecurityContextHolder.clearContext();
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
