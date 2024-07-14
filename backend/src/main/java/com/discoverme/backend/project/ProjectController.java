package com.discoverme.backend.project;

import com.discoverme.backend.config.ApplicationProperties;
import com.discoverme.backend.config.JobSchedulerService;
import com.discoverme.backend.mail.EventDto;
import com.discoverme.backend.mail.MailService;
import com.discoverme.backend.project.file.FileService;
import com.discoverme.backend.project.file.FileServiceException;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    private final ApplicationProperties properties;
    private final FileService fileService;
    private final JobSchedulerService jobSchedulerService;
    private final UserService userService;
    private final MailService mailService;
    private final Scheduler scheduler;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProjectResponse> submitProject(@RequestParam("request") String projectRequest,
                                                         @RequestParam("content") MultipartFile content) {
        EventDto eventDto;
        ProjectRequest projectRequest1 = projectMapper.getProjectRequestJson(projectRequest);
        ProjectResponse projectResponse = projectService.submitProject(projectRequest1);
        String contentFilePath = fileService.uploadFile(content);
        try {
            jobSchedulerService.scheduleProjectProcessingJob(projectResponse.getId(), contentFilePath);
            return ResponseEntity.ok(projectResponse);
        }
        catch (FileServiceException | SchedulerException e) {
            fileService.deleteFile(contentFilePath);
            projectService.deleteProject(projectResponse.getId());
            sendFailureEmail();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
//        catch (FileServiceException e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
//        }
//        catch (SchedulerException e) {
//            fileService.deleteFile(contentFilePath);
//            projectService.deleteProject(projectResponse.getId());
//            eventDto = new EventDto();
//            Users user = userService.getCurrentUser();
//            eventDto.setTo(user.getEmail());
//            eventDto.setFrom(properties.getMailAddress());
//            Map<String, String> data = new HashMap<>();
//            data.put("subject", "Project Publishing Failed !!!");
//            data.put("name", user.getUserName());
//            eventDto.setData(data);
//            mailService.sendHtmlEmail(eventDto);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
//        }
    }

    private void sendFailureEmail() {
        EventDto eventDto = new EventDto();
        Users user = userService.getCurrentUser();
        eventDto.setTo(user.getEmail());
        eventDto.setFrom(properties.getMailAddress());
        Map<String, String> data = new HashMap<>();
        data.put("subject", "Project Publishing Failed !!!");
        data.put("name", user.getUserName());
        eventDto.setData(data);
        mailService.sendHtmlEmail(eventDto);
    }

    public void scheduleProjectProcessingJob(Long projectId, String contentFilePath) {
        if (projectId == null || contentFilePath == null) {
            throw new IllegalArgumentException("Project ID and Content File Path cannot be null");
        }

        JobDetail jobDetail = JobBuilder.newJob(ProjectProcessingJob.class)
                .withIdentity("projectProcessingJob_" + projectId)
                .usingJobData("projectId", projectId.toString())
                .usingJobData("contentFilePath", contentFilePath)
                .storeDurably()
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("projectProcessingTrigger_" + projectId)
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

//        supportService.toggleSupport(projectResponse.getId());
//        // Call the asynchronous method
//        asyncService.processProjectAsync(projectResponse.getId(), content, projectRequest1.getSocial().getId());
//
//        return new ResponseEntity<>(projectResponse, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<Page<ProjectResponse>> getCurrentProjects(@RequestParam(name = "search", defaultValue = "") String search,
                                                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                                                    @RequestParam(name = "sort") String sort) {
        System.out.println("sort: "+ sort);
        Page<ProjectResponse> projectResponseList = projectService.getCurrentProjects(search, page, sort);
        return new ResponseEntity<>(projectResponseList, HttpStatus.OK);
    }

    @GetMapping("user")
    public ResponseEntity<Page<ProjectResponse>> getUserProjects(@RequestParam(name = "search", defaultValue = "") String search,
                                                                    @RequestParam(name = "page", defaultValue = "0") int page){
        Page<ProjectResponse> projectResponseList = projectService.getUserProjects(search,
                PageRequest.of(page, properties.getPageSize()));
        return new ResponseEntity<>(projectResponseList, HttpStatus.OK);
    }

    @GetMapping("limit")
    public ResponseEntity<Boolean> isProjectLimitExceeded(){
        return new ResponseEntity<>(projectService.isProjectLimitExceeded(), HttpStatus.OK);
    }

    @GetMapping("calender")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Page<ProjectResponse>> getAllProjectsForACalender(@RequestParam @NonNull Long calenderId, Pageable pageable){
        return new ResponseEntity<>(projectService.getAllProjectsForACalender(calenderId, pageable), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProject(@RequestParam Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
