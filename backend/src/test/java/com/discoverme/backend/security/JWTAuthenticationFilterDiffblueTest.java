package com.discoverme.backend.security;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.discoverme.backend.user.Users;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = {JWTAuthenticationFilter.class, UserDetailsService.class})
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class JWTAuthenticationFilterDiffblueTest {
    @Autowired
    private JWTAuthenticationFilter jWTAuthenticationFilter;

    @MockBean
    private JWTUtil jWTUtil;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private UserDetailsServiceImpl userDetailsServiceImpl;

    /**
     * Method under test:
     * {@link JWTAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal() throws TokenExpiredException, ServletException, IOException {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jWTAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
    }

    /**
     * Method under test:
     * {@link JWTAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal2() throws TokenExpiredException, ServletException, IOException {
        // Arrange
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("https://example.org/example");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jWTAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
        verify(request).getHeader(eq("Authorization"));
    }

    /**
     * Method under test:
     * {@link JWTAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal3() throws TokenExpiredException, ServletException, IOException, UsernameNotFoundException {
        // Arrange
        when(userDetailsService.loadUserByUsername(Mockito.<String>any())).thenReturn(new UserDetailsImpl(new Users()));
        when(jWTUtil.validateToken(Mockito.<String>any())).thenReturn("2020-03-01");
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getSession(anyBoolean())).thenReturn(new MockHttpSession());
        when(request.getRemoteAddr()).thenReturn("42 Main St");
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jWTAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(jWTUtil).validateToken(eq(""));
        verify(filterChain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
        verify(request).getRemoteAddr();
        verify(request).getHeader(eq("Authorization"));
        verify(request).getSession(eq(false));
        verify(userDetailsService).loadUserByUsername(eq("2020-03-01"));
    }

    /**
     * Method under test:
     * {@link JWTAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal4() throws TokenExpiredException, ServletException, IOException, UsernameNotFoundException {
        // Arrange
        when(userDetailsService.loadUserByUsername(Mockito.<String>any()))
                .thenReturn(new User("janedoe", "iloveyou", new ArrayList<>()));
        when(jWTUtil.validateToken(Mockito.<String>any())).thenReturn("2020-03-01");
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getSession(anyBoolean())).thenReturn(new MockHttpSession());
        when(request.getRemoteAddr()).thenReturn("42 Main St");
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jWTAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(jWTUtil).validateToken(eq(""));
        verify(filterChain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
        verify(request).getRemoteAddr();
        verify(request).getHeader(eq("Authorization"));
        verify(request).getSession(eq(false));
        verify(userDetailsService).loadUserByUsername(eq("2020-03-01"));
    }

    /**
     * Method under test:
     * {@link JWTAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal5() throws TokenExpiredException, ServletException, IOException, UsernameNotFoundException {
        // Arrange
        UserDetailsImpl userDetailsImpl = mock(UserDetailsImpl.class);
        Mockito.<Collection<? extends GrantedAuthority>>when(userDetailsImpl.getAuthorities())
                .thenReturn(new ArrayList<>());
        when(userDetailsService.loadUserByUsername(Mockito.<String>any())).thenReturn(userDetailsImpl);
        when(jWTUtil.validateToken(Mockito.<String>any())).thenReturn("2020-03-01");
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getSession(anyBoolean())).thenReturn(new MockHttpSession());
        when(request.getRemoteAddr()).thenReturn("42 Main St");
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jWTAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(jWTUtil).validateToken(eq(""));
        verify(userDetailsImpl).getAuthorities();
        verify(filterChain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
        verify(request).getRemoteAddr();
        verify(request).getHeader(eq("Authorization"));
        verify(request).getSession(eq(false));
        verify(userDetailsService).loadUserByUsername(eq("2020-03-01"));
    }

    /**
     * Method under test:
     * {@link JWTAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal6() throws TokenExpiredException, ServletException, IOException {
        // Arrange
        when(jWTUtil.validateToken(Mockito.<String>any())).thenReturn(null);
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jWTAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(jWTUtil).validateToken(eq(""));
        verify(filterChain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
        verify(request).getHeader(eq("Authorization"));
    }
}
