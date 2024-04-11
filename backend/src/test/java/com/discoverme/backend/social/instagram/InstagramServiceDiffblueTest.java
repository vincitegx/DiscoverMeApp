package com.discoverme.backend.social.instagram;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

@ContextConfiguration(classes = {InstagramService.class})
@ExtendWith(SpringExtension.class)
class InstagramServiceDiffblueTest {
    @Autowired
    private InstagramService instagramService;

    @MockBean
    private WebClient webClient;

    /**
     * Method under test: {@link InstagramService#publishVideoToStory()}
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
        instagramService.publishVideoToStory();

        // Assert
        verify(webClient, atLeast(1)).post();
        verify(requestBodySpec, atLeast(1)).retrieve();
        verify(responseSpec, atLeast(1)).bodyToMono(isA(Class.class));
        verify(requestBodyUriSpec, atLeast(1)).uri(Mockito.<String>any(), Mockito.<Function<UriBuilder, URI>>any());
    }
}
