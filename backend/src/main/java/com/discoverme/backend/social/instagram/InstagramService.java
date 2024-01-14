package com.discoverme.backend.social.instagram;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class InstagramService {

    private final WebClient webClient;

    public void publishVideoToStory(){
        Object b = webClient.post()
                .uri("https://graph.facebook.com/v18.0/17841400008460056/media", uriBuilder -> uriBuilder
                        .queryParam("video_url", "")
                        .queryParam("media_type", "STORIES")
                        .queryParam("caption", "")
                        .build())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
        Object c = webClient.post()
                .uri("https://graph.facebook.com/v18.0/17841400008460056/media_publish", uriBuilder -> uriBuilder
                        .queryParam("creation_id", "")
                        .build())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
