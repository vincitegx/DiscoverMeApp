package com.discoverme.backend.user.login.refresh;

import com.discoverme.backend.security.JWTAuthenticationFilter;
import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.login.JwtResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.RefreshFailedException;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;
    @PostMapping("refresh/token")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse refreshToken(@Valid @RequestBody UserDto user, HttpServletRequest request) throws RefreshFailedException {
        Optional<Cookie> cookies = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .filter(cookie -> JWTAuthenticationFilter.COOKIE_NAME.equals(cookie.getName()))
                .findFirst();
        if(cookies.isPresent()){
            return refreshTokenService.refreshToken(user, cookies.get().getValue());
        }else{
            throw new RefreshFailedException("No refresh token found");
        }
    }

}
