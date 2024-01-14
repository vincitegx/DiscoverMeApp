package com.discoverme.backend.user.social;

import com.restfb.*;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FacebookService {

    private final HttpServletRequest request;
    private final WebClient webClient;

    public void postVideo(String contentUrl) throws MalformedURLException, FileNotFoundException {
        Optional<Cookie> cookies = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .filter(cookie -> "".equals(cookie.getName()))
                .findFirst();
        if(cookies.isPresent()){
            String accessToken = cookies.get().getValue();
            FacebookClient defaultFacebookClient = new DefaultFacebookClient(accessToken, Version.LATEST);
            User user = defaultFacebookClient.fetchObject("me", User.class);
            URL url = new URL(contentUrl);
            FileInputStream fileInputStream = new FileInputStream("");
            FacebookType response = defaultFacebookClient.publish("me/videos", FacebookType.class,
                    BinaryAttachment.with("", fileInputStream),
                    Parameter.with("message",""));
        }else{
            SecurityContextHolder.clearContext();
        }
    }

    public void publishVideoToStory(){
        Object b = webClient.post()
                .uri("https://graph.facebook.com/v18.0/page_id/video_stories", uriBuilder -> uriBuilder
                        .queryParam("upload_phase", "start")
                        .build())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
        Object c = webClient.post()
                .uri("https://rupload.facebook.com/video-upload/v18.0/video_id", uriBuilder -> uriBuilder
                        .queryParam("file_url", "")
                        .build())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
        Object d = webClient.post()
                .uri("https://graph.facebook.com/v18.0/page_id/video_stories", uriBuilder -> uriBuilder
                        .queryParam("video_id", "")
                        .queryParam("upload_phase", "")
                        .build())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    public void getStories(){
        Object d = webClient.get()
                .uri("https://graph.facebook.com/v18.0/page_id/stories")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
