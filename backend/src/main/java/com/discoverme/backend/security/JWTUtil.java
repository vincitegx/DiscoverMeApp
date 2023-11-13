package com.discoverme.backend.security;

import com.discoverme.backend.user.UserException;
import com.discoverme.backend.user.Users;
import com.discoverme.backend.user.login.refresh.RefreshToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.util.Date.from;

@Service
public class JWTUtil {
    private static final String SECRET_KEY =
            "foobar_123456789_foobar_123456789_foobar_123456789_foobar_123456789";

    private Collection<? extends GrantedAuthority> getAuthorities(Users user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    public String issueToken(String subject) {
        return issueToken(subject, Map.of());
    }

    public String issueTokenWithRefreshToken(RefreshToken refreshToken) {
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
            Map<String, List<String>> claims = new HashMap<>();
            claims.put("roles", principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
            return Jwts.builder()
                    .setSubject(refreshToken.getUser().getEmail())
                    .setExpiration(Date.from(
                            Instant.now().plus(3, ChronoUnit.MINUTES)
                    ))
                    .setIssuer("https://discoverme.com")
                    .setIssuedAt(from(Instant.now()))
                    .setClaims(claims)
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                    .compact();
        } catch (IllegalArgumentException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public String issueToken(String subject, String ...scopes) {
        return issueToken(subject, Map.of("scopes", scopes));
    }

    public String issueToken(String subject, List<String> scopes) {
        return issueToken(subject, Map.of("scopes", scopes));
    }


    public String issueToken(
            String subject,
            Map<String, Object> claims) {
        String token = Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuer("https://discoverme.com")
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(
                        Date.from(
                                Instant.now().plus(3, ChronoUnit.MINUTES)
                        )
                )
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public boolean isTokenValid(String jwt, String username) {
        String subject = getSubject(jwt);
        return subject.equals(username) && !isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String jwt) {
        Date today = Date.from(Instant.now());
        return getClaims(jwt).getExpiration().before(today);
    }
}
