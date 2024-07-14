package com.discoverme.backend.social.facebook;

import com.discoverme.backend.project.Project;
import com.discoverme.backend.project.VideoCheckJob;
import com.discoverme.backend.social.SocialPlatform;
import com.discoverme.backend.social.Socials;
import com.discoverme.backend.social.SocialsService;
import com.discoverme.backend.user.*;
import com.discoverme.backend.user.social.UserSocials;
import com.discoverme.backend.user.social.UserSocialsService;
import com.restfb.*;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.*;
import lombok.AllArgsConstructor;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.lang.Thread;
import java.net.MalformedURLException;
import java.nio.file.Files;

@Service
@AllArgsConstructor
public class FacebookService {
    private final UserSocialsService userSocialsService;
    private final WebClient webClient;
    private final UserService userService;
    private final SocialsService socialsService;
    private final UserMapper userMapper;
    private final Scheduler scheduler;
    private static final Logger logger = LoggerFactory.getLogger(FacebookService.class);

    // Constants for video status checks
    private static final int MAX_RETRIES = 10;
    private static final int SLEEP_DURATION_MS = 60000; // 60 seconds

//    public GraphResponse postVideo(Project project, String contentFilePath) throws MalformedURLException, FileNotFoundException, FacebookOAuthException {
//        System.out.println("Entered post Video method");
//        System.out.println(project);
////        Users loggedInUser = userService.getCurrentUser();
////        System.out.println(loggedInUser);
//        Socials social = socialsService.getSocialByPlatform(SocialPlatform.FACEBOOK);
//        System.out.println(social);
//        UserSocials userSocial = userSocialsService.findUserSocial(project.getUser(), social)
//                .orElseThrow(() -> new UserException("User social not found"));
//        System.out.println(userSocial);
//        try (FileInputStream fileInputStream = new FileInputStream(contentFilePath)) {
//            FacebookClient defaultFacebookClient = new DefaultFacebookClient(userSocial.getAccessToken(), Version.LATEST);
//            File videoFile = new File(contentFilePath);
//            if (!videoFile.exists()) {
//                throw new FileNotFoundException("Video file not found at specified path.");
//            }
//
//            ResumableUploadStartResponse startResponse = defaultFacebookClient.publish(userSocial.getSocialUserId() + "/video_stories",
//                    ResumableUploadStartResponse.class, Parameter.with("upload_phase", "start"));
//
//            if (startResponse != null) {
//                String videoUploadID = startResponse.getVideoId();
//                byte[] videoBytes = Files.readAllBytes(videoFile.toPath());
//                FacebookReelAttachment reelAttachment = FacebookReelAttachment.withByteContent(videoBytes);
//                GraphResponse response = defaultFacebookClient.publish(videoUploadID, GraphResponse.class, reelAttachment);
//
//                if (response.isSuccess()) {
//                    GraphResponse publishResponse = defaultFacebookClient.publish(userSocial.getSocialUserId() + "/video_stories", GraphResponse.class,
//                            Parameter.with("video_id", videoUploadID),
//                            Parameter.with("upload_phase", "finish"),
//                            Parameter.with("video_state", "PUBLISHED"),
//                            Parameter.with("description", "test video"),
//                            Parameter.with("title", "test video")
//                    );
//                    if (publishResponse != null) {
//                        return publishResponse;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("failed to load file"+ e.getMessage());
//            e.printStackTrace();
//            // Log the exception or handle it as needed
//        }
//        return null;
//    }

    public GraphResponse postVideo(Project project, String contentFilePath) throws MalformedURLException, FileNotFoundException, FacebookOAuthException {
        System.out.println("Entered post Video method");
        System.out.println(project);

        Socials social = socialsService.getSocialByPlatform(SocialPlatform.FACEBOOK);
        System.out.println(social);
        UserSocials userSocial = userSocialsService.findUserSocial(project.getUser(), social)
                .orElseThrow(() -> new UserException("User social not found"));
        System.out.println(userSocial);

        try (FileInputStream fileInputStream = new FileInputStream(contentFilePath)) {
            FacebookClient defaultFacebookClient = new DefaultFacebookClient(userSocial.getAccessToken(), Version.LATEST);
            File videoFile = new File(contentFilePath);
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
                    if (publishResponse != null && checkVideoStatus(defaultFacebookClient, videoUploadID)) {
                        scheduleVideoCheckJob(project.getId(), userSocial.getSocialUserId(), videoUploadID);
                        return publishResponse;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to load file: " + e.getMessage());
            e.printStackTrace();
            // Log the exception or handle it as needed
        }
        return null;
    }

    private boolean checkVideoStatus(FacebookClient client, String videoUploadID) {
        int retryCount = 0;

        while (retryCount < MAX_RETRIES) {
            try {
                Video videoStatus = client.fetchObject(videoUploadID, Video.class, Parameter.withFields("status"));
                System.out.println("VideoStatus: " + videoStatus.getStatus());

                String videoProcessingStatus = videoStatus.getStatus().getVideoStatus();
                if ("ready".equals(videoProcessingStatus)) {
                    return true;
                } else if ("error".equals(videoProcessingStatus)) {
                    logger.error("Video processing failed: {}", videoStatus.getStatus());
                    return false;
                }

                // Sleep before checking the status again
                Thread.sleep(SLEEP_DURATION_MS);
                retryCount++;
            } catch (InterruptedException e) {
                logger.error("Error while waiting to check video status", e);
                Thread.currentThread().interrupt();
                return false;
            }
        }

        logger.error("Video processing did not complete within the allowed retries");
        return false;
    }

    private void scheduleVideoCheckJob(Long projectId, String userId, String videoUploadID) {
        JobDetail jobDetail = JobBuilder.newJob(VideoCheckJob.class)
                .withIdentity("videoCheckJob_" + projectId, "videoCheck")
                .usingJobData("projectId", projectId)
                .usingJobData("userId", userId)
                .usingJobData("videoUploadID", videoUploadID)
                .storeDurably(true)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("videoCheckTrigger_" + projectId, "videoCheck")
                .startAt(DateBuilder.futureDate(23 * 60 + 50, DateBuilder.IntervalUnit.MINUTE))
                .build();

        try {
            scheduler.addJob(jobDetail, true);
            scheduler.scheduleJob(trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
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

    public UserDto disconnectAccount() {
        Socials social = socialsService.getSocialByPlatform(SocialPlatform.FACEBOOK);
        Users loggedInUser = userService.getCurrentUser();
        userSocialsService.findUserSocial(loggedInUser, social).ifPresent(userSocialsService::deleteUserSocial);
        loggedInUser = userService.getCurrentUser();
        return userMapper.apply(loggedInUser);
    }

}
