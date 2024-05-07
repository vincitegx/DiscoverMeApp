package com.discoverme.backend.social.facebook;

import com.discoverme.backend.social.SocialPlatform;
import com.discoverme.backend.social.Socials;
import com.discoverme.backend.social.SocialsService;
import com.discoverme.backend.user.UserException;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import com.discoverme.backend.user.social.UserSocials;
import com.discoverme.backend.user.social.UserSocialsService;
import com.restfb.*;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FacebookService {
    private final UserSocialsService userSocialsService;
    private final WebClient webClient;
    private final UserService userService;
    private final SocialsService socialsService;

    public void postVideo(String contentUrl) throws MalformedURLException, FileNotFoundException, FacebookOAuthException {
        Users loggedInUser = userService.getCurrentUser();
        Socials social = socialsService.getSocialByPlatform(SocialPlatform.FACEBOOK);
        UserSocials userSocial = userSocialsService.findUserSocial(loggedInUser, social).orElseThrow(() -> new UserException("User social not found"));
        try (FileInputStream fileInputStream = new FileInputStream("uploads/" + contentUrl)) {
            FacebookClient defaultFacebookClient = new DefaultFacebookClient(userSocial.getAccessToken(), Version.LATEST);
            File videoFile = new File("uploads/" + contentUrl);
            if (!videoFile.exists()) {
                throw new FileNotFoundException("Video file not found at specified path.");
            }
            ResumableUploadStartResponse startResponse = defaultFacebookClient.publish(userSocial.getSocialUserId() + "/video_stories",
                    ResumableUploadStartResponse.class, Parameter.with("upload_phase", "start"));
            if (startResponse != null) {
                String videoUploadID = startResponse.getVideoId();
                byte[] videoBytes = Files.readAllBytes(videoFile.toPath());
                FacebookReelAttachment reelAttachment = FacebookReelAttachment.withByteContent(videoBytes);
                GraphResponse response = defaultFacebookClient.publish(videoUploadID, GraphResponse.class, reelAttachment);
                if (response.isSuccess()) {
                    GraphResponse publishResponse = defaultFacebookClient.publish(userSocial.getSocialUserId() + "/video_stories", GraphResponse.class,
                            Parameter.with("video_id", videoUploadID),
                            Parameter.with("upload_phase", "finish"),
                            Parameter.with("video_state", "PUBLISHED"),
                            Parameter.with("description", "test video"),
                            Parameter.with("title", "test video")
                            );
                    if (publishResponse != null) {
                        System.out.println(publishResponse.getPostId());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void publishVideoToStory(String contentUrl) {
        Users loggedInUser = userService.getCurrentUser();
        Socials social = socialsService.getSocialByPlatform(SocialPlatform.FACEBOOK);
        UserSocials userSocial = userSocialsService.findUserSocial(loggedInUser, social).orElseThrow(() -> new UserException("User social not found"));
        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("file", new FileSystemResource("uploads/" + contentUrl));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        FBVideoStoryStartResponse fbVideoStoryStartResponse = webClient.post()
                .uri("https://graph.facebook.com/v19.0/" + userSocial.getSocialUserId() + "/video_stories", uriBuilder -> uriBuilder
                        .queryParam("upload_phase", "start")
                        .build())
                .retrieve()
                .bodyToMono(FBVideoStoryStartResponse.class)
                .block();
        System.out.println(fbVideoStoryStartResponse);
        if (fbVideoStoryStartResponse != null) {
            FBVideoStoryUploadStatus fbVideoStoryUploadStatus = webClient.post()
                    .uri("https://rupload.facebook.com/video-upload/v19.0/" + fbVideoStoryStartResponse.getVideoId())
                    .headers(httpHeaders -> httpHeaders.addAll(headers)) // Add the headers to the request
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(FBVideoStoryUploadStatus.class)
                    .block();
            System.out.println(fbVideoStoryUploadStatus);
            assert fbVideoStoryUploadStatus != null;
            if (fbVideoStoryUploadStatus.isSuccess()) {
                FBVideoStoryFinishResponse fbVideoStoryFinishResponse = webClient.post()
                        .uri("https://graph.facebook.com/v18.0/" + userSocial.getSocialUserId() + "/video_stories", uriBuilder -> uriBuilder
                                .queryParam("video_id", fbVideoStoryStartResponse.getVideoId())
                                .queryParam("upload_phase", "finish")
                                .build())
                        .retrieve()
                        .bodyToMono(FBVideoStoryFinishResponse.class)
                        .block();
                System.out.println(fbVideoStoryFinishResponse);
            }
        }
    }

    public void getStories() {
        Object d = webClient.get()
                .uri("https://graph.facebook.com/v18.0/page_id/stories")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
