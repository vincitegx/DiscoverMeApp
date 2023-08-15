package com.discoverme.backend.project;

import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.UserMapper;
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
    private final UserMapper userMapper;
    private final ProjectMapper projectMapper;

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
                .calender(getProjectTag())
                .songTitle(projectRequest.getSongTitle())
                .songUri(songUri)
                .user(userService.getCurrentUser())
                .content(contentList)
                .socials(projectRequest.getPlatform())
                .status(ProjectApprovalStatus.PENDING)
                .build();
        project1 = saveProject(project1);
        ProjectResponse projectResponse = new ProjectResponse(project1.getId(), project1.getSongTitle(),userMapper.apply(user) );
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

    public ProjectTagResponse addProjectTag(ProjectTagRequest projectTagRequest) {
        ProjectCalender projectTag = new ProjectCalender();
        projectTag.setName(projectTag.getName());
        projectTag = projectCalenderRepository.save(projectTag);
        return new ProjectTagResponse(projectTag.getId(), projectTag.getName());
    }

    public void deleteProjectTag(Long projectTagId) {
        projectCalenderRepository.findById(projectTagId).ifPresent(tag->{ projectCalenderRepository.delete(tag);});
    }

    public ProjectTagResponse editProjectTag(ProjectTagRequest projectTagRequest) {
        ProjectCalender projectTag = projectCalenderRepository.findByName(projectTagRequest.getName()).orElseThrow(()-> new ProjectException("No such tag found"));
        projectTag.setName(projectTag.getName());
        projectTag = projectCalenderRepository.save(projectTag);
        return new ProjectTagResponse(projectTag.getId(), projectTag.getName());
    }

    public ProjectCalender getProjectTag() {
        return projectCalenderRepository.findFirstByOrderByIdDesc().orElseThrow(()-> new ProjectException("No tags found"));
    }

    public ProjectResponse approveProject(String id) {
        Long projectId = Long.parseLong(id);
        Project project =projectRepository.findById(projectId).orElseThrow(()-> new ProjectException("No such Project Found"));
        project.setStatus(ProjectApprovalStatus.APPROVED);
        project = projectRepository.save(project);
        UserDto user = userMapper.apply(project.getUser());
        ProjectResponse projectResponse = new ProjectResponse(project.getId(), project.getSongTitle(),user );
        return projectResponse;
    }

    public ProjectResponse disApproveProject(String id) {
        Long projectId = Long.parseLong(id);
        Project project =projectRepository.findById(projectId).orElseThrow(()-> new ProjectException("No such Project Found"));
        project.setStatus(ProjectApprovalStatus.REJECTED);
        project = projectRepository.save(project);
        UserDto user = userMapper.apply(project.getUser());
        ProjectResponse projectResponse = new ProjectResponse(project.getId(), project.getSongTitle(),user );
        return projectResponse;
    }

    public ProjectCalender updateProjectStatus(PeriodStatus status) {
        return null;
    }

    public List<ProjectResponse> getApprovedProjects() {
        ProjectCalender projectCalender = projectCalenderRepository.findFirstByOrderByIdDesc().orElseThrow(()-> new ProjectException("No tags found"));
        List<Project> projects = projectRepository.findByStatusAndCalender(ProjectApprovalStatus.APPROVED, projectCalender);
        List<ProjectResponse> projectResponseList = new ArrayList<>();
        projects.forEach(project -> {
            ProjectResponse projectResponse = projectMapper.mapProjectToResponse(project);
            projectResponseList.add(projectResponse);
        });
        return projectResponseList;
    }
}
