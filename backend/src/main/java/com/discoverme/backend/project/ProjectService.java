package com.discoverme.backend.project;

import com.discoverme.backend.config.ApplicationProperties;
import com.discoverme.backend.project.calender.Calender;
import com.discoverme.backend.project.calender.CalenderService;
import com.discoverme.backend.project.file.FileService;
import com.discoverme.backend.social.Socials;
import com.discoverme.backend.social.SocialsRepository;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final CalenderService calenderService;
    private final UserService userService;
    private final ProjectRepository projectRepository;
    private final FileService fileService;
    private final LoggedInUserService loggedInUserService;
    private final SecureRandomStringGenerator secureRandomStringGenerator;
    private final ApplicationProperties properties;

    public ProjectResponse submitProject(ProjectRequest projectRequest, MultipartFile content){
        Calender calender = calenderService.getProjectCalender();
        Users user = userService.getCurrentUser();
        Optional<Project> project = projectRepository.findByUserAndCalender(user, calender);
        if(project.isPresent()){
            throw new IllegalArgumentException("You can add only one project per week");
        }
        String contentUri = fileService.uploadFile(content);
        Project project1 = Project.builder()
                .url(secureRandomStringGenerator.apply(properties.getRandomStringGeneratorMaxSize()))
                .calender(calender)
                .songTitle(projectRequest.getSongTitle())
                .songUri(projectRequest.getSongUri())
                .user(user)
                .contentUri(contentUri)
                .social(projectRequest.getSocial())
                .build();
        project1 = saveProject(project1);
        return mapProjectToResponse(project1);
    }

    public Optional<Project> findById(Long id){
        return projectRepository.findById(id);
    }

    public Project saveProject(Project project){
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(()-> new ProjectException("No project with such ID"));
        projectRepository.delete(project);
    }
    public Page<ProjectResponse> getCurrentProjects(String search, PageRequest request) {
        Calender calender = calenderService.getProjectCalender();
        Page<Project> projects = projectRepository.findByCalenderAndSongTitleContainingOrStageNameContaining(calender.getId(),search, request);
        return projects.map(this::mapProjectToResponse);
    }
    
    public ProjectResponse mapProjectToResponse(Project project){
        return ProjectResponse.builder()
                .id(project.getId())
                .url(project.getUrl())
                .songUri(project.getSongUri())
                .songTitle(project.getSongTitle())
                .contentUri(project.getContentUri())
//                .isSupported(loggedInUserService.checkSupportStateForLoggedInUser(project.getId()))
//                .percentOfSupport(loggedInUserService.getProjectsSupportedToLoggedInUser(project.getUser()))
                .social(project.getSocial())
                .stageName(project.getUser().getStageName())
                .build();
    }

    public Page<ProjectResponse> getAllProjectsForACalender(Long id, Pageable pageable) {
        Calender calender = calenderService.findById(id);
        Page<Project> projects = projectRepository.findByCalender(calender, pageable);
        List<Project> projectList = projects.toList();
        Page<ProjectResponse> projectResponsePage = new PageImpl(projectList);
        projectList.forEach(project -> projectResponsePage.and(mapProjectToResponse(project)));
        return projectResponsePage;
    }

    public Boolean isProjectLimitExceeded() {
        long projectCount = projectRepository.count();
        return projectCount == properties.getProjectMaxSize();
    }
}
