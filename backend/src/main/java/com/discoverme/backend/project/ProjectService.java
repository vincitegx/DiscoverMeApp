package com.discoverme.backend.project;

import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectCalenderRepository projectCalenderRepository;
    private final UserService userService;
    private final ProjectRepository projectRepository;
    private final FileService fileService;
    private final ContentRepository contentRepository;
    private final LoggedInUserService loggedInUserService;

    public ProjectResponse submitProject(ProjectRequest projectRequest, MultipartFile artwork, MultipartFile song, List<MultipartFile> contents){
        ProjectCalender projectCalender = projectCalenderRepository.findFirstByOrderByIdDesc().orElseThrow(()-> new ProjectException("No tags found"));
        Users user = userService.getCurrentUser();
        Optional<Project> project = projectRepository.findByUserAndCalender(user, projectCalender);
        if(project.isPresent()){
            throw new ProjectException("You can only register one project");
        }
        String artworkUri = fileService.uploadFile(artwork);
        String songUri = fileService.uploadFile(song);
        List<String> contentUri = fileService.uploadFiles(contents);
        Set<Content> contentList = new HashSet<>();
        contentUri.forEach(c->{
            Content content = new Content();
            content.setUri(c);
            content = contentRepository.save(content);
            contentList.add(content);
        });
        Project project1 = Project.builder()
                .artworkUri(artworkUri)
                .calender(getProjectCalender())
                .songTitle(projectRequest.getSongTitle())
                .songUri(songUri)
                .user(userService.getCurrentUser())
                .content(contentList)
                .socials(projectRequest.getSocials())
                .status(ProjectApprovalStatus.PENDING)
                .build();
        project1 = saveProject(project1);
        ProjectResponse projectResponse = mapProjectToResponse(project1);
        return projectResponse;
    }

    public Optional<Project> findById(Long id){
        return projectRepository.findById(id);
    }

    public Project saveProject(Project project){
        return projectRepository.save(project);
    }

    public void deleteAllProject() {
    }

    public void deleteProject(Long id) {
    }

    public ProjectCalenderResponse addProjectTag(ProjectCalenderRequest projectCalenderRequest) {
        ProjectCalender projectTag = new ProjectCalender();
        projectTag.setName(projectTag.getName());
        projectTag = projectCalenderRepository.save(projectTag);
        return new ProjectCalenderResponse(projectTag.getId(), projectTag.getName());
    }

    public void deleteProjectTag(Long projectTagId) {
        projectCalenderRepository.findById(projectTagId).ifPresent(tag->{ projectCalenderRepository.delete(tag);});
    }

    public ProjectCalenderResponse editProjectTag(ProjectCalenderRequest projectCalenderRequest) {
        ProjectCalender projectTag = projectCalenderRepository.findByName(projectCalenderRequest.getName()).orElseThrow(()-> new ProjectException("No such tag found"));
        projectTag.setName(projectTag.getName());
        projectTag = projectCalenderRepository.save(projectTag);
        return new ProjectCalenderResponse(projectTag.getId(), projectTag.getName());
    }

    public ProjectCalender getProjectCalender() {
        return projectCalenderRepository.findFirstByOrderByIdDesc().orElseThrow(()-> new ProjectException("No tags found"));
    }

    public ProjectResponse approveProject(String id) {
        Long projectId = Long.parseLong(id);
        Project project =projectRepository.findById(projectId).orElseThrow(()-> new ProjectException("No such Project Found"));
        project.setStatus(ProjectApprovalStatus.APPROVED);
        project = projectRepository.save(project);
        ProjectResponse projectResponse = mapProjectToResponse(project);
        return projectResponse;
    }

    public ProjectResponse disApproveProject(String id) {
        Long projectId = Long.parseLong(id);
        Project project =projectRepository.findById(projectId).orElseThrow(()-> new ProjectException("No such Project Found"));
        project.setStatus(ProjectApprovalStatus.REJECTED);
        project = projectRepository.save(project);
        ProjectResponse projectResponse = mapProjectToResponse(project);
        return projectResponse;
    }

    public ProjectCalender updateProjectStatus(PeriodStatus status) {
        return null;
    }

    public List<ProjectResponse> getApprovedProjects() {
        ProjectCalender projectCalender = projectCalenderRepository.findFirstByOrderByIdDesc().orElseThrow(()-> new ProjectException("No Calender found"));
        List<Project> projects = projectRepository.findByStatusAndCalender(ProjectApprovalStatus.APPROVED, projectCalender);
        List<ProjectResponse> projectResponseList = new ArrayList<>();
        projects.forEach(project -> {
            ProjectResponse projectResponse = mapProjectToResponse(project);
            projectResponseList.add(projectResponse);
        });
        return projectResponseList;
    }
    
    public List<ProjectResponse> getSupportedProjects(Long userId) {
        ProjectCalender projectCalender = projectCalenderRepository.findFirstByOrderByIdDesc().orElseThrow(()-> new ProjectException("No Calender found"));
        List<Project> projects = projectRepository.findByStatusAndCalender(ProjectApprovalStatus.APPROVED, projectCalender);
        List<ProjectResponse> projectResponseList = new ArrayList<>();
        projects.forEach(project -> {
            ProjectResponse projectResponse = mapProjectToResponse(project);
            projectResponseList.add(projectResponse);
        });
        return projectResponseList;
    }
    
    public ProjectResponse mapProjectToResponse(Project project){
        return ProjectResponse.builder()
                .songTitle(project.getSongTitle())
                .artworkUri(project.getArtworkUri())
                .id(project.getId())
                .isSupported(loggedInUserService.checkSupportStateForLoggedInUser(project.getId().toString()))
                .isVoted(loggedInUserService.checkVoteStateForLoggedInUser(project.getId().toString()))
                .noOfVoters(project.getVoteCount())
                .songUri(project.getSongUri())
                .socials(project.getSocials())
                .stageName(project.getUser().getStageName())
                .build();
    }
}
