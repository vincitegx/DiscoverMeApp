package com.discoverme.backend.user.login.refresh;

import com.discoverme.backend.security.GoogleRefreshTokenResponse;
import com.discoverme.backend.security.JWTAuthenticationFilter;
import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.login.JwtResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import javax.security.auth.RefreshFailedException;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;
    private final HttpServletRequest request;
    private final WebClient userInfoClient;
    @Value("${spring.security.oauth2.resourceserver.opaque-token.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaque-token.client-secret}")
    private String clientSecret;
    @PostMapping("refresh/token")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse refreshToken(@Valid @RequestBody UserDto user) throws RefreshFailedException {
//        Optional<Cookie> cookies = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
//                .filter(cookie -> JWTAuthenticationFilter.COOKIE_NAME.equals(cookie.getName()))
//                .findFirst();

        System.out.println("Entered Refresh Token controller");
        String refreshToken = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (JWTAuthenticationFilter.COOKIE_NAME.equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }
        if(refreshToken != null && !refreshToken.isEmpty()){
            System.out.println("Refresh Token Cookie exists");
            if(refreshTokenService.existByToken(refreshToken)){
                System.out.println("This is a local refresh token");
                return refreshTokenService.refreshToken(user, refreshToken);
            } else {
                System.out.println("This is a google refresh token");
                try{
                    String finalRefreshToken = refreshToken;
                    GoogleRefreshTokenResponse refreshTokenResponse = userInfoClient.post()
                            .uri("https://oauth2.googleapis.com/", uriBuilder -> uriBuilder
                                    .path("token")
                                    .queryParam("client_id", clientId)
                                    .queryParam("client_secret", clientSecret)
                                    .queryParam("refresh_token", finalRefreshToken)
                                    .queryParam("grant_type", "refresh_token")
                                    .build())
                            .retrieve()
                            .bodyToMono(GoogleRefreshTokenResponse.class)
                            .block();
                    System.out.println(refreshTokenResponse.id_token());
//                    assert refreshTokenResponse != null;
                    return new JwtResponse(refreshTokenResponse.id_token(), user);
                }catch (Exception ex){
                    System.out.println("Couldn't refresh google jwt");
                    throw new RefreshFailedException(ex.getMessage());
                }
            }
        }else{
            SecurityContextHolder.clearContext();
            throw new RefreshFailedException("No refresh token found");
        }
    }
}
