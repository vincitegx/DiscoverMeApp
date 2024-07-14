package com.discoverme.backend.project;

import com.discoverme.backend.config.ApplicationProperties;
import com.discoverme.backend.project.calender.Calender;
import com.discoverme.backend.project.calender.CalenderService;
import com.discoverme.backend.project.file.FileService;
import com.discoverme.backend.social.Socials;
import com.discoverme.backend.social.SocialsService;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
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
    private final SocialsService socialService;

    public Optional<Project> getProject(Long projectId){
        return projectRepository.findById(projectId);
    }

    public ProjectResponse submitProject(ProjectRequest projectRequest){
        Calender calender = calenderService.getProjectCalender();
        Users user = userService.getCurrentUser();
//        Optional<Project> project = projectRepository.findByUserAndCalenderAndCategory(user, calender, ProjectCategory.FREE.name());
//        if(project.isPresent()){
//            throw new IllegalArgumentException("You can add only one project per week");
//        }
        Project project1 = Project.builder()
                .code(secureRandomStringGenerator.apply(10))
                .calender(calender)
                .title(projectRequest.getTitle())
                .status(ProjectStatus.PENDING.name())
                .category(ProjectCategory.FREE.name())
                .user(user)
                .social(projectRequest.getSocial())
                .build();
        project1 = saveProject(project1);
        return mapProjectToResponse(project1);
    }



    public void processAndUploadVideo(Long projectId, MultipartFile content, Long socialId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        Socials social = socialService.getSocialById(socialId);
        String contentUri = fileService.uploadFile(content, social.getName());

        project.setContentUri(contentUri);
        projectRepository.save(project);
    }

    public void publishToSocialMedia(Long projectId) {
        // Code to publish the project to social media
        // Update the project status accordingly
    }

    public void updateProjectStatus(Project project) {
//        List<Project> pendingProjects = projectRepository.findByStatus("PENDING");

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
    public Page<ProjectResponse> getCurrentProjects(String search, int page, String sort) {
        Sort sortCriteria = switch (sort.toLowerCase()) {
            case "alphabetical" -> Sort.by(Sort.Direction.ASC, "title");
            case "mostrecent" -> Sort.by(Sort.Direction.DESC, "publish_date");
            case "mostliked" -> Sort.by(Sort.Direction.DESC, "reaction_count");
            default -> Sort.by(Sort.Direction.DESC, "support_count")
                    .and(Sort.by(Sort.Direction.DESC, "reaction_count"));
        };

        Pageable pageable = PageRequest.of(page, properties.getPageSize(), sortCriteria);
        Calender calender = calenderService.getProjectCalender();
        Page<Project> projects = projectRepository.findByCalenderAndStatusAndTitleContainingOrUserNameContaining(calender.getId(), ProjectStatus.APPROVED.name(), search, pageable);

        return projects.map(this::mapProjectToResponse);
    }

    public Page<ProjectResponse> getUserProjects(String search, PageRequest request) {
        Calender calender = calenderService.getProjectCalender();
        Users user = userService.getCurrentUser();
        Page<Project> projects = projectRepository.findByCalenderAndUserAndStatusAndTitleContainingOrUserNameContaining(calender.getId(),user.getId(),ProjectStatus.APPROVED.name(), search, request);
        return projects.map(this::mapProjectToResponse);
    }

    private Integer getTotalProjectsCount() {
        Calender calender = calenderService.getProjectCalender();
        return projectRepository.findByCalender(calender).size();
    }
    
    public ProjectResponse mapProjectToResponse(Project project){
        return ProjectResponse.builder()
                .id(project.getId())
                .code(project.getCode())
                .title(project.getTitle())
                .status(project.getStatus())
                .contentUri(project.getContentUri())
                .isSupported(loggedInUserService.checkSupportStateForLoggedInUser(project.getId()))
                .noOfSupport(project.getSupportCount())
                .social(project.getSocial())
                .userName(project.getUser().getUserName())
                .isReacted(loggedInUserService.checkReactionStateForLoggedInUser(project.getId()))
                .noOfReaction(project.getReactionCount())
                .publishDate(project.getPublishDate() != null ? TimeAgo.using(project.getPublishDate().toInstant().toEpochMilli()): "Not Published")
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
