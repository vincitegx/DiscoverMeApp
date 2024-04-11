package com.discoverme.backend.user.social;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

@ContextConfiguration(classes = {FacebookService.class})
@ExtendWith(SpringExtension.class)
class FacebookServiceDiffblueTest {
    @Autowired
    private FacebookService facebookService;

    @MockBean
    private HttpServletRequest httpServletRequest;

    @MockBean
    private WebClient webClient;

    /**
     * Method under test: {@link FacebookService#postVideo(String)}
     */
    @Test
    void testPostVideo() throws FileNotFoundException, MalformedURLException {
        // Arrange
        when(httpServletRequest.getCookies()).thenReturn(new Cookie[]{new Cookie("Name", "https://example.org/example")});

        // Act
        facebookService.postVideo("https://example.org/example");

        // Assert
        verify(httpServletRequest).getCookies();
    }

    /**
     * Method under test: {@link FacebookService#postVideo(String)}
     */
    @Test
    void testPostVideo2() throws FileNotFoundException, MalformedURLException {
        // Arrange
        when(httpServletRequest.getCookies()).thenReturn(new Cookie[]{});

        // Act
        facebookService.postVideo("https://example.org/example");

        // Assert
        verify(httpServletRequest).getCookies();
    }

    /**
     * Method under test: {@link FacebookService#publishVideoToStory()}
     */
    @Test
    void testPublishVideoToStory() {
        // Arrange
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
        Mono<Object> justResult = Mono.just("Data");
        when(responseSpec.bodyToMono(Mockito.<Class<Object>>any())).thenReturn(justResult);
        WebClient.RequestBodySpec requestBodySpec = mock(WebClient.RequestBodySpec.class);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        WebClient.RequestBodyUriSpec requestBodyUriSpec = mock(WebClient.RequestBodyUriSpec.class);
        when(requestBodyUriSpec.uri(Mockito.<String>any(), Mockito.<Function<UriBuilder, URI>>any()))
                .thenReturn(requestBodySpec);
        when(webClient.post()).thenReturn(requestBodyUriSpec);

        // Act
        facebookService.publishVideoToStory();

        // Assert
        verify(webClient, atLeast(1)).post();
        verify(requestBodySpec, atLeast(1)).retrieve();
        verify(responseSpec, atLeast(1)).bodyToMono(isA(Class.class));
        verify(requestBodyUriSpec, atLeast(1)).uri(Mockito.<String>any(), Mockito.<Function<UriBuilder, URI>>any());
    }

    /**
     * Method under test: {@link FacebookService#getStories()}
     */
    @Test
    void testGetStories() {
        // Arrange
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
        Mono<Object> justResult = Mono.just("Data");
        when(responseSpec.bodyToMono(Mockito.<Class<Object>>any())).thenReturn(justResult);
        WebClient.RequestBodySpec requestBodySpec = mock(WebClient.RequestBodySpec.class);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        WebClient.RequestHeadersUriSpec<WebClient.RequestBodySpec> requestHeadersUriSpec = mock(
                WebClient.RequestHeadersUriSpec.class);
        when(requestHeadersUriSpec.uri(Mockito.<String>any(), isA(Object[].class))).thenReturn(requestBodySpec);
        Mockito.<WebClient.RequestHeadersUriSpec<?>>when(webClient.get()).thenReturn(requestHeadersUriSpec);

        // Act
        facebookService.getStories();

        // Assert
        verify(webClient).get();
        verify(requestBodySpec).retrieve();
        verify(responseSpec).bodyToMono(isA(Class.class));
        verify(requestHeadersUriSpec).uri(eq("https://graph.facebook.com/v18.0/page_id/stories"), isA(Object[].class));
    }
}
