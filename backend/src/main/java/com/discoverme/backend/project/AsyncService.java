package com.discoverme.backend.project;

import com.discoverme.backend.project.file.FileService;
import com.discoverme.backend.project.support.SupportService;
import com.discoverme.backend.social.Socials;
import com.discoverme.backend.social.SocialsService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AsyncService {

//    private final ProjectService projectService;
//    private final FileService fileService;
//    private final SupportService supportService;
//
//    public AsyncService(ProjectService projectService, FileService fileService, SupportService supportService) {
//        this.projectService = projectService;
//        this.fileService = fileService;
//        this.supportService = supportService;
//    }
//
//    @Async
//    public void processProjectAsync(Long projectId, MultipartFile content, Long socialId) {
//        try {
//            Project project = projectService.getProject(projectId).orElseThrow(()->new ProjectException("Project not found"));
//            String contentUri = fileService.uploadFile(content, project.getSocial().getName());
//            projectService.processAndUploadVideo(projectId, content, socialId);
//            projectService.publishToSocialMedia(projectId);
//        } catch (Exception e) {
//            // Handle exception
//        }
//    }

    private final ProjectService projectService;

    public AsyncService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Async
    public void processProjectAsync(Long projectId, MultipartFile content, Long socialId) {
        try {
            projectService.processAndUploadVideo(projectId, content, socialId);
            projectService.publishToSocialMedia(projectId);
        } catch (Exception e) {
            // Handle exception
        }
    }
}
