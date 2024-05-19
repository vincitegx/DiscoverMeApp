package com.discoverme.backend.user.login.refresh;

import com.discoverme.backend.config.ApplicationProperties;
import com.discoverme.backend.security.GoogleRefreshTokenResponse;
import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.login.JwtResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;

import javax.security.auth.RefreshFailedException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;
    private final WebClient userInfoClient;
    private final ApplicationProperties applicationProperties;

    @PostMapping("refresh/token")
    public JwtResponse refreshToken(@Valid @RequestBody UserDto user) throws RefreshFailedException {
        Cookie refreshTokenCookie = getRefreshTokenCookie();
//        validateRefreshTokenCookie(refreshTokenCookie);
//        System.out.println("Validated refresh token cookie: " + refreshTokenCookie);
        String refreshToken = refreshTokenCookie.getValue();
        if (refreshTokenService.existByToken(refreshToken)) {
            System.out.println("This is a local refresh token");
            return refreshTokenService.refreshToken(user, refreshToken);
        } else {
            System.out.println("This is a Google refresh token");
            return refreshTokenWithGoogle(refreshToken, user);
        }
    }

    private Cookie getRefreshTokenCookie() throws RefreshFailedException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .filter(cookie -> applicationProperties.getRefreshTokenCookie().equals(cookie.getName()))
                    .findFirst()
                    .orElseThrow(() -> new RefreshFailedException("No refresh token found"));
        }
        throw new RefreshFailedException("No cookies found in the request");
    }

    private void validateRefreshTokenCookie(Cookie refreshTokenCookie) throws RefreshFailedException {
        System.out.println("entered validateRefreshTokenCookie method");
        long currentTimeMillis = System.currentTimeMillis();
        int maxAge = refreshTokenCookie.getMaxAge();
        System.out.println("max age: "+maxAge);
        long expirationTimeMillis = currentTimeMillis + (refreshTokenCookie.getMaxAge() * 1000L);
        Date cookieExpiryTime = new Date(expirationTimeMillis);
        LocalDateTime expiryTime = LocalDateTime.ofInstant(cookieExpiryTime.toInstant(), ZoneOffset.UTC);
        LocalDateTime now = LocalDateTime.now();
        if (expiryTime.isBefore(now)) {
            throw new RefreshFailedException("Refresh token cookie has expired");
        }
        else {
            System.out.println("Refresh token cookie is valid");
        }
    }

    private JwtResponse refreshTokenWithGoogle(String refreshToken, UserDto user) throws RefreshFailedException {
        try {
            GoogleRefreshTokenResponse refreshTokenResponse = userInfoClient.post()
                    .uri("https://oauth2.googleapis.com/token?client_id={client_id}&client_secret={client_secret}&refresh_token={refresh_token}&grant_type=refresh_token",
                            applicationProperties.getGoogleClientId(),
                            applicationProperties.getGoogleClientSecret(),
                            refreshToken)
                    .retrieve()
                    .bodyToMono(GoogleRefreshTokenResponse.class)
                    .block();
            System.out.println(refreshTokenResponse);
            return new JwtResponse(refreshTokenResponse.id_token(), user);
        } catch (Exception ex) {
            System.out.println("Couldn't refresh Google JWT: " + ex.getMessage());
            throw new RefreshFailedException("Couldn't refresh Google JWT");
        }
    }
}
