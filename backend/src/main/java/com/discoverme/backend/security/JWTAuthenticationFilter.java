package com.discoverme.backend.security;

import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private final JWTUtil jwtUtil;
    @Autowired
    private final UserDetailsService userDetailsService;
    public static final String COOKIE_NAME = "refresh-token";

    public JWTAuthenticationFilter(JWTUtil jwtUtil,
                                   UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException, TokenExpiredException {
        System.out.println("inside authfilter");
        final String authorizationHeader = request.getHeader("Authorization");
        String email;
        String token;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            System.out.println("there is a bearer auth");
            token = authorizationHeader.substring(7);
            System.out.println("jwt: "+ token);
            email = jwtUtil.validateToken(token);
            System.out.println("email: "+email);
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                System.out.println("passed auth");
                UserDetails userDetails
                        = this.userDetailsService.loadUserByUsername(email);
                var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
