package com.discoverme.backend.user;

import com.discoverme.backend.config.ApplicationProperties;
import com.discoverme.backend.security.CustomOAuth2UserService;
import com.discoverme.backend.security.JWTAuthenticationFilter;
import com.discoverme.backend.user.login.JwtResponse;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class GoogleController {

    @Value("${spring.security.oauth2.resourceserver.opaque-token.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.resourceserver.opaque-token.client-secret}")
    private String clientSecret;
    private final ApplicationProperties applicationProperties;
    private final HttpServletResponse servletResponse;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final UserMapper userMapper;
    @GetMapping("url")
    public ResponseEntity<UrlDto> auth() {
        String url = new GoogleAuthorizationCodeRequestUrl(applicationProperties.getGoogleClientId(),
                "https://localhost:4200/auth/redirect",
                Arrays.asList(
                        "email",
                        "profile",
                        "openid")).setAccessType("offline").build();
        return ResponseEntity.ok(new UrlDto(url));
    }

    @PostMapping("callback")
    public ResponseEntity<JwtResponse> callback(@RequestParam("code") String code) {
        JwtResponse response;
        long expirationTimeMillis = Instant.now().plusSeconds(applicationProperties.getRefreshTokenValidity()).toEpochMilli();
        try {
            NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                    .setAudience(Collections.singletonList(applicationProperties.getGoogleClientId()))
                    .build();
            GoogleTokenResponse googleTokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(), new GsonFactory(),
                    applicationProperties.getGoogleClientId(),
                    applicationProperties.getGoogleClientSecret(),
                    code,
                    applicationProperties.getFrontendDomain()+"/auth/redirect"
            ).execute();
            GoogleIdToken idToken = verifier.verify(googleTokenResponse.getIdToken());
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                UserDto user =  userMapper.apply(customOAuth2UserService.upsertUser(payload));
                response = new JwtResponse(googleTokenResponse.getIdToken(), user);
                Cookie refreshTokenCookie = new Cookie(applicationProperties.getRefreshTokenCookie(), googleTokenResponse.getRefreshToken());
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
            } else {
                System.out.println("Invalid ID token.");
                throw new GeneralSecurityException("Invalid ID token.");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (GeneralSecurityException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
