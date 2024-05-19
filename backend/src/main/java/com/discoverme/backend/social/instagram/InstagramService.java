package com.discoverme.backend.social.instagram;

import com.discoverme.backend.social.SocialPlatform;
import com.discoverme.backend.social.Socials;
import com.discoverme.backend.social.SocialsService;
import com.discoverme.backend.user.*;
import com.discoverme.backend.user.social.UserSocials;
import com.discoverme.backend.user.social.UserSocialsService;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.FacebookReelAttachment;
import com.restfb.types.GraphResponse;
import com.restfb.types.ReelsUploadStartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class InstagramService {
    private final WebClient webClient;
    private final UserSocialsService userSocialsService;
    private final UserService userService;
    private final SocialsService socialsService;
    private final UserMapper userMapper;

    public void publishVideoToStory(){
        Users loggedInUser = userService.getCurrentUser();
        Socials social = socialsService.getSocialByPlatform(SocialPlatform.INSTAGRAM);
        UserSocials userSocial= userSocialsService.findUserSocial(loggedInUser, social).orElseThrow(()-> new UserException("User social not found"));
        try{
            InstagramUploadID instagramUploadID = webClient.post()
                    .uri("https://graph.facebook.com/v19.0/"+userSocial.getSocialUserId()+"/media", uriBuilder -> uriBuilder
                            .queryParam("video_url", "https://drive.google.com/file/d/1ObfqbqcgwDrNmVMjZvgZ4n2haH33P-NK/view?usp=sharing")
                            .queryParam("media_type", "STORIES")
                            .queryParam("caption", "Hello World")
                            .queryParam("access_token", userSocial.getAccessToken())
                            .build())
                    .retrieve()
                    .bodyToMono(InstagramUploadID.class)
                    .block();
            System.out.println("Upload ID: "+ instagramUploadID.id());
            InstagramPublishResponse instagramPublishResponse = webClient.post()
                    .uri("https://graph.facebook.com/v18.0/"+userSocial.getSocialUserId()+"/media_publish", uriBuilder -> uriBuilder
                            .queryParam("creation_id", instagramUploadID.id())
                            .queryParam("access_token", userSocial.getAccessToken())
                            .build())
                    .retrieve()
                    .bodyToMono(InstagramPublishResponse.class)
                    .block();
            System.out.println(instagramPublishResponse.id());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void postVideo(String contentUrl) throws MalformedURLException, FileNotFoundException, FacebookOAuthException {
        Users loggedInUser = userService.getCurrentUser();
        Socials social = socialsService.getSocialByPlatform(SocialPlatform.INSTAGRAM);
        UserSocials userSocial= userSocialsService.findUserSocial(loggedInUser, social).orElseThrow(()-> new UserException("User social not found"));
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

    public UserDto disconnectAccount() {
        Socials social = socialsService.getSocialByPlatform(SocialPlatform.INSTAGRAM);
        Users loggedInUser = userService.getCurrentUser();
        userSocialsService.findUserSocial(loggedInUser, social).ifPresent(userSocialsService::deleteUserSocial);
        loggedInUser = userService.getCurrentUser();
        return userMapper.apply(loggedInUser);
    }
}
