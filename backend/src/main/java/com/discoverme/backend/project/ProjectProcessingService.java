package com.discoverme.backend.project;

import com.discoverme.backend.config.ApplicationProperties;
import com.discoverme.backend.mail.EventDto;
import com.discoverme.backend.mail.MailService;
import com.discoverme.backend.project.file.FileService;
import com.discoverme.backend.social.SocialPlatform;
import com.discoverme.backend.social.facebook.FacebookService;
import com.discoverme.backend.social.instagram.InstagramService;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.GraphResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class ProjectProcessingService {

    @Autowired
    private FileService fileService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private FacebookService facebookService;

    @Autowired
    private InstagramService instagramService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private ApplicationProperties applicationProperties;

    public String extractFileName(String filePath) {
        Path path = Paths.get(filePath);
        return path.getFileName().toString();
    }

    @Async
    public CompletableFuture<String> processProjectAsync(Project project, String contentFilePath) {
        try {
            contentFilePath = convertVideo(contentFilePath, project.getSocial().getName());
            //TODO: Extract only filename with extension
            project.setContentUri(extractFileName(contentFilePath));
            project = projectService.saveProject(project);
            System.out.println(project);
            publishToSocialMedia(project, contentFilePath);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Failed to process project async");
            throw new ProjectProcessingException("Failed to process project async", e);
        }
        return CompletableFuture.completedFuture("Processing Complete");
    }

    private String convertVideo(String filePath, String social) throws IOException, InterruptedException {
        return fileService.executeFFmpegCommand(filePath, social);
    }

    private void publishToSocialMedia(Project project, String contentFilePath) {
        if (SocialPlatform.FACEBOOK.name().equals(project.getSocial().getName())) {
            try {
                System.out.println("Publish to Facebook");
                GraphResponse graphResponse = facebookService.postVideo(project, contentFilePath);
                if (graphResponse == null) {
                    throw new ProjectException("Facebook Content Upload Not Successful");
                } else {
                    project.setStatus(ProjectStatus.APPROVED.name());
                    project.setVideoId(graphResponse.getId());
                    project.setPostId(graphResponse.getPostId());
                    project.setPublishDate(Date.from(Instant.now()));
                    projectService.saveProject(project);
                }
            } catch (MalformedURLException | FileNotFoundException | FacebookOAuthException | ProjectException e) {
                System.out.println("failed for facebook: " + e.getMessage());
                fileService.deleteFile(contentFilePath);
                projectService.deleteProject(project.getId());
                sendFailureEmail();
            }
        } else if (SocialPlatform.INSTAGRAM.name().equals(project.getSocial().getName())) {
            try {
                instagramService.publishVideoToStory();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed for instagram");
            }
        }
    }

    private void sendFailureEmail() {
        EventDto eventDto = new EventDto();
        Users user = userService.getCurrentUser();
        eventDto.setTo(user.getEmail());
        eventDto.setFrom(applicationProperties.getMailAddress());
        Map<String, String> data = new HashMap<>();
        data.put("subject", "Project Publishing Failed !!!");
        data.put("name", user.getUserName());
        eventDto.setData(data);
        mailService.sendHtmlEmail(eventDto);
    }
}
