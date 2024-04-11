package com.discoverme.backend.security;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.discoverme.backend.user.UserRepository;
import com.discoverme.backend.user.login.LoginAttemptService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

@ContextConfiguration(classes = {SecurityConfig.class, AuthenticationConfiguration.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class SecurityConfigDiffblueTest {
    @Autowired
    private SecurityConfig securityConfig;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private WebClient webClient;

    /**
     * Method under test: {@link SecurityConfig#passwordEncoder()}
     */
    @Test
    void testPasswordEncoder() {
        // Arrange, Act and Assert
        assertTrue(securityConfig.passwordEncoder() instanceof BCryptPasswordEncoder);
    }

    /**
     * Method under test:
     * {@link SecurityConfig#authenticationManager(AuthenticationConfiguration)}
     */
    @Test
    void testAuthenticationManager() throws Exception {
        // Arrange, Act and Assert
        assertTrue(
                ((ProviderManager) securityConfig.authenticationManager(new AuthenticationConfiguration())).getProviders()
                        .get(0) instanceof DaoAuthenticationProvider);
    }

    /**
     * Method under test:
     * {@link SecurityConfig#authenticationManager(AuthenticationConfiguration)}
     */
    @Test
    void testAuthenticationManager2() throws Exception {
        // Arrange
        AuthenticationConfiguration configuration = new AuthenticationConfiguration();
        configuration.setApplicationContext(mock(AnnotationConfigApplicationContext.class));

        // Act and Assert
        assertTrue(((ProviderManager) securityConfig.authenticationManager(configuration)).getProviders()
                .get(0) instanceof DaoAuthenticationProvider);
    }

    /**
     * Method under test:
     * {@link SecurityConfig#authenticationProvider(UserDetailsService, PasswordEncoder)}
     */
    @Test
    void testAuthenticationProvider() {
        // Arrange
        UserRepository userRepository = mock(UserRepository.class);
        UserDetailsServiceImpl userDetailsService2 = new UserDetailsServiceImpl(userRepository, new LoginAttemptService());

        // Act and Assert
        assertTrue(((DaoAuthenticationProvider) securityConfig.authenticationProvider(userDetailsService2,
                new BCryptPasswordEncoder())).getUserCache() instanceof NullUserCache);
    }

    /**
     * Method under test: {@link SecurityConfig#userInfoClient()}
     */
    @Test
    void testUserInfoClient() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        securityConfig.userInfoClient();
    }

    /**
     * Method under test: {@link SecurityConfig#corsConfigurer()}
     */
    @Test
    void testCorsConfigurer() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        SecurityConfig securityConfig = new SecurityConfig();

        // Act
        securityConfig.corsConfigurer();

        // Assert
        assertTrue(securityConfig.passwordEncoder() instanceof BCryptPasswordEncoder);
    }

    /**
     * Method under test: {@link SecurityConfig#corsConfigurer()}
     */
    @Test
    void testCorsConfigurer2() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        securityConfig.corsConfigurer();
    }
}
