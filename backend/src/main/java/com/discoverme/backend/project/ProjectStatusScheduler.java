package com.discoverme.backend.project;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProjectStatusScheduler {

    private final ProjectService projectService;

    public ProjectStatusScheduler(ProjectService projectService) {
        this.projectService = projectService;
    }

//    @Scheduled(fixedRate = 60000) // Run every 60 seconds
//    public void checkProjectStatus(Project project) {
//        projectService.updateProjectStatus(project);
//    }
}