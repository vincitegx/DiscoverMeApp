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
import com.restfb.types.FacebookReelAttachment;
import com.restfb.types.GraphResponse;
import com.restfb.types.ReelsUploadStartResponse;
import com.restfb.types.StoryAttachment;
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
        UserSocials userSocial= userSocialsService.findUserSocial(loggedInUser, social).orElseThrow(()-> new UserException("User social not found"));
//        FacebookClient defaultFacebookClient = new DefaultFacebookClient(userSocial.getAccessToken(), Version.LATEST);
//        File videoFile = new File("uploads/" + contentUrl);
//        if (!videoFile.exists()) {
//            throw new FileNotFoundException("Video file not found at specified path.");
//        }
//        FileInputStream fileInputStream = new FileInputStream(videoFile);
//        FBVideoStoryStartResponse fbVideoStoryStartResponse1 = defaultFacebookClient.publish(userSocial.getSocialUserId() + "/video_stories", FBVideoStoryStartResponse.class,
//                Parameter.with("upload_phase", "start"));
//        String videoUploadID = fbVideoStoryStartResponse1.getVideoId();
//        System.out.println(videoUploadID);
//        GraphResponse graphResponse = defaultFacebookClient.publish(videoUploadID, GraphResponse.class, BinaryAttachment.with(videoFile.getName(), fileInputStream));
//        System.out.println(graphResponse.getPostId());
//        GraphResponse graphResponse1 = defaultFacebookClient.publish(userSocial.getSocialUserId() + "/video_reels", GraphResponse.class,
//                Parameter.with("video_id", videoUploadID),
//                Parameter.with("upload_phase", "finish"),
//                Parameter.with("video_state", "PUBLISHED"),
//                Parameter.with("description", "A short description text"),
//                Parameter.with("message", "Your message here"),
//                Parameter.with("title", "Title of testing video"),
//                Parameter.with("description", "Description of testing video"));

        try (FileInputStream fileInputStream = new FileInputStream("uploads/" + contentUrl)) {
            FacebookClient defaultFacebookClient = new DefaultFacebookClient(userSocial.getAccessToken(), Version.LATEST);
            File videoFile = new File("uploads/" + contentUrl);
            if (!videoFile.exists()) {
                throw new FileNotFoundException("Video file not found at specified path.");
            }

            ReelsUploadStartResponse startResponse = defaultFacebookClient.publish(userSocial.getSocialUserId() + "/video_reels",
                    ReelsUploadStartResponse.class,
                    Parameter.with("upload_phase", "start"));
            String videoUploadID = startResponse.getVideoId();
            byte[] videoBytes = Files.readAllBytes(videoFile.toPath());
            FacebookReelAttachment reelAttachment = FacebookReelAttachment.withByteContent(videoBytes);
            GraphResponse response = defaultFacebookClient.publish(videoUploadID, GraphResponse.class, reelAttachment);
            GraphResponse publishResponse = defaultFacebookClient.publish(userSocial.getSocialUserId() + "/video_stories", GraphResponse.class,
                    Parameter.with("video_id", videoUploadID),
                    Parameter.with("upload_phase", "finish"),
                    Parameter.with("video_state", "PUBLISHED"),
                    Parameter.with("description", "A short description text"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void publishVideoToStory(String contentUrl){
        Users loggedInUser = userService.getCurrentUser();
        Socials social = socialsService.getSocialByPlatform(SocialPlatform.FACEBOOK);
        UserSocials userSocial= userSocialsService.findUserSocial(loggedInUser, social).orElseThrow(()-> new UserException("User social not found"));
        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("file", new FileSystemResource("uploads/" + contentUrl));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        FBVideoStoryStartResponse fbVideoStoryStartResponse = webClient.post()
                .uri("https://graph.facebook.com/v18.0/"+userSocial.getSocialUserId()+"/video_stories", uriBuilder -> uriBuilder
                        .queryParam("upload_phase", "start")
                        .build())
                .retrieve()
                .bodyToMono(FBVideoStoryStartResponse.class)
                .block();
        System.out.println(fbVideoStoryStartResponse);
        if(fbVideoStoryStartResponse != null){
            FBVideoStoryUploadStatus fbVideoStoryUploadStatus = webClient.post()
                    .uri("https://rupload.facebook.com/video-upload/v18.0/" + fbVideoStoryStartResponse.getVideoId())
                    .headers(httpHeaders -> httpHeaders.addAll(headers)) // Add the headers to the request
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(FBVideoStoryUploadStatus.class)
                    .block();
            System.out.println(fbVideoStoryUploadStatus);
            assert fbVideoStoryUploadStatus != null;
            if(fbVideoStoryUploadStatus.isSuccess()){
                FBVideoStoryFinishResponse fbVideoStoryFinishResponse = webClient.post()
                        .uri("https://graph.facebook.com/v18.0/"+userSocial.getSocialUserId()+"/video_stories", uriBuilder -> uriBuilder
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

    public void getStories(){
        Object d = webClient.get()
                .uri("https://graph.facebook.com/v18.0/page_id/stories")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
