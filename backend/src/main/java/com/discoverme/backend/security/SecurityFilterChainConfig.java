package com.discoverme.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityFilterChainConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(antMatcher(HttpMethod.POST, "/api/v1/auth/register/user")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.POST, "/api/v1/auth/login")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/api/v1/users/**")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.PUT, "/api/v1/users/verify")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/v3/api-docs")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/api/v3/users/**")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/v2/api-docs")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/v3/api-docs/**")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/swagger-resources")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/swagger-resources/**")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/configuration/ui")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/configuration/security")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/swagger-ui/**")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/webjars/**")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/swagger-ui.html")).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
