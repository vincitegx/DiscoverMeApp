package com.discoverme.backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.discoverme.backend.user.UserException;
import com.discoverme.backend.user.Users;
import com.discoverme.backend.user.login.refresh.RefreshToken;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.util.*;

import static com.auth0.jwt.algorithms.Algorithm.HMAC256;
import static java.util.Date.from;

@Service
public class JWTUtil {
    private static final String SECRET_KEY =
            "foobar_123456789_foobar_123456789_foobar_123456789_foobar_123456789";

    @Value("${spring.security.oauth2.resourceserver.opaque-token.client-id}")
    private String clientId;

    private Collection<? extends GrantedAuthority> getAuthorities(Users user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    public String generateJwtToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(Date.from(Instant.now().plusSeconds(180)))
                .withIssuer("https://discoverme.com")
                .withIssuedAt(Date.from(Instant.now()))
                .withClaim("roles", principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .sign(HMAC256(SECRET_KEY.getBytes()));
    }

    public String generateJwtToken(RefreshToken refreshToken) {
        try {
            User principal = (User) User.builder()
                    .username(refreshToken.getUser().getEmail())
                    .authorities(getAuthorities(refreshToken.getUser()))
                    .password(refreshToken.getUser().getPassword())
                    .disabled(refreshToken.getUser().getEnabled())
                    .accountExpired(false)
                    .accountLocked(refreshToken.getUser().getNonLocked())
                    .credentialsExpired(false)
                    .build();
            return JWT.create()
                    .withSubject(refreshToken.getUser().getEmail())
                    .withExpiresAt(Date.from(Instant.now().plusSeconds(180)))
                    .withIssuer("https://discoverme.com")
                    .withIssuedAt(from(Instant.now()))
                    .withClaim("roles", principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                    .sign(HMAC256(SECRET_KEY.getBytes()));
        } catch (JWTCreationException | IllegalArgumentException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public String validateToken(String token) {
        Date now = from(Instant.now());
        Algorithm algorithm = HMAC256(SECRET_KEY.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("https://discoverme.com").build();
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            String email = decodedJWT.getSubject();
            if(email != null && decodedJWT.getExpiresAt().after(now)){
                return email;
            }else {
                return null;
            }
        }catch (AlgorithmMismatchException | IncorrectClaimException e){
         return validateOAuthToken(token);
        }catch (JWTVerificationException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String validateOAuthToken(String token) throws TokenExpiredException{
        try {
            NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                    .setAudience(Collections.singletonList(clientId))
                    .build();
            GoogleIdToken idToken = verifier.verify(token);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                return payload.getEmail();
            } else {
                return null;
            }
        } catch (IOException | GeneralSecurityException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
