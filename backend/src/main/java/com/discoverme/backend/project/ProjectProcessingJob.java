package com.discoverme.backend.project;

import com.discoverme.backend.config.ApplicationProperties;
import com.discoverme.backend.mail.EventDto;
import com.discoverme.backend.mail.MailService;
import com.discoverme.backend.project.file.FileService;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProjectProcessingJob implements Job {

    @Autowired
    private ProjectProcessingService projectProcessingService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private FileService fileService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String projectIdStr = jobDataMap.getString("projectId");
        if (projectIdStr == null) {
            throw new JobExecutionException("Project ID is null.");
        }

        long projectId;
        try {
            projectId = Long.parseLong(projectIdStr);
        } catch (NumberFormatException e) {
            throw new JobExecutionException("Invalid Project ID format: " + projectIdStr, e);
        }
        String contentFilePath = jobDataMap.getString("contentFilePath");
        if (contentFilePath == null) {
            throw new JobExecutionException("Content file path is null.");
        }

        System.out.println("Executing ProjectProcessingJob for project ID: " + projectId + " with content file path: " + contentFilePath);
        Project project = projectService.findById(projectId)
                .orElseThrow(() -> new JobExecutionException("Project not found with ID: " + projectId));

        try {
            projectProcessingService.processProjectAsync(project, contentFilePath).join();
        } catch (Exception e) {
            // Handle the exception
            fileService.deleteFile(contentFilePath);
            projectService.deleteProject(projectId);
            sendFailureEmail();
            throw new JobExecutionException("Failed to process project async", e);
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
