package com.discoverme.backend.project;

import com.discoverme.backend.config.ApplicationProperties;
import com.discoverme.backend.mail.EventDto;
import com.discoverme.backend.mail.MailService;
import com.discoverme.backend.user.UserException;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import com.discoverme.backend.user.social.UserSocials;
import com.discoverme.backend.user.social.UserSocialsService;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Video;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@PersistJobDataAfterExecution
public class VideoCheckJob implements Job {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSocialsService userSocialsService;

    @Autowired
    private MailService mailService;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private ProjectService projectService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        Long projectId = jobDataMap.getLong("projectId");
        String userId = jobDataMap.getString("userId");
        String videoUploadID = jobDataMap.getString("videoUploadID");

        Project project = projectService.findById(projectId)
                .orElseThrow(() -> new JobExecutionException("Project not found with ID: " + projectId));
        UserSocials userSocial = userSocialsService.findUserSocial(project.getUser(), project.getSocial())
                .orElseThrow(() -> new UserException("User social not found"));
        try {
            FacebookClient defaultFacebookClient = new DefaultFacebookClient(userSocial.getAccessToken(), Version.LATEST);
            Video videoStatus = defaultFacebookClient.fetchObject(videoUploadID, Video.class, Parameter.withFields("status"));
            if (videoStatus == null || !"ready".equals(videoStatus.getStatus().getVideoStatus())) {
                System.out.println("video was deleted");
                userService.fetchAndDisableUser(Long.valueOf(userId));
                sendVideoDeletedEmail(userId);
            }else{
//                System.out.println("video still exists");
            }
        } catch (Exception e) {
            throw new JobExecutionException("Error checking video status for project ID: " + projectId, e);
        }
    }

    private void sendVideoDeletedEmail(String userId) {
        EventDto eventDto = new EventDto();
        Users user = userService.findById(Long.valueOf(userId)).orElseThrow(()-> new UserException("No such user exist with such ID"));
        eventDto.setTo(user.getEmail());
        eventDto.setFrom(applicationProperties.getMailAddress());
        Map<String, String> data = new HashMap<>();
        data.put("subject", "Story Deleted Notification");
        data.put("name", user.getUserName());
        eventDto.setData(data);
        mailService.sendHtmlEmail(eventDto);
    }
}
