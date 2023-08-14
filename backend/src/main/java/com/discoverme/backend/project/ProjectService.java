package com.discoverme.backend.project;

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
    private final ProjectTagRepository projectTagRepository;
    private final UserService userService;
    private final ProjectRepository projectRepository;
    private final FileService fileService;
    private final ContentRepository contentRepository;
    private final UserMapper userMapper;

    public ProjectResponse submitProject(ProjectRequest projectRequest, MultipartFile artwork, MultipartFile song, List<MultipartFile> contents){
        ProjectTag projectTag = projectTagRepository.findFirstByOrderByIdDesc().orElseThrow(()-> new ProjectException("No tags found"));
        Users user = userService.getCurrentUser();
        Optional<Project> project = projectRepository.findByUserAndProjectTag(user, projectTag);
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
                .projectTag(getProjectTag())
                .songTitle(projectRequest.getSongTitle())
                .songUri(songUri)
                .user(userService.getCurrentUser())
                .content(contentList)
                .platform(projectRequest.getPlatform())
                .status(ProjectApprovalStatus.PENDING)
                .build();
        project1 = projectRepository.save(project1);
        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setId(project1.getId());
        projectResponse.setSongTitle(project1.getSongTitle());
        projectResponse.setUser(userMapper.apply(user));
        return projectResponse;
    }

    public void deleteAllProject() {
    }

    public void deleteProject(Long id) {
    }

    public ProjectTagResponse addProjectTag(ProjectTagRequest projectTagRequest) {
        ProjectTag projectTag = new ProjectTag();
        projectTag.setName(projectTag.getName());
        projectTag = projectTagRepository.save(projectTag);
        return new ProjectTagResponse(projectTag.getId(), projectTag.getName());
    }

    public void deleteProjectTag(Long projectTagId) {
        projectTagRepository.findById(projectTagId).ifPresent(tag->{ projectTagRepository.delete(tag);});
    }

    public ProjectTagResponse editProjectTag(ProjectTagRequest projectTagRequest) {
        ProjectTag projectTag = projectTagRepository.findByName(projectTagRequest.getName()).orElseThrow(()-> new ProjectException("No such tag found"));
        projectTag.setName(projectTag.getName());
        projectTag = projectTagRepository.save(projectTag);
        return new ProjectTagResponse(projectTag.getId(), projectTag.getName());
    }

    public ProjectTag getProjectTag() {
        return projectTagRepository.findFirstByOrderByIdDesc().orElseThrow(()-> new ProjectException("No tags found"));
    }

    public ProjectResponse approveProject(String id) {
        Long projectId = Long.parseLong(id);
        Project project =projectRepository.findById(projectId).orElseThrow(()-> new ProjectException("No such Project Found"));
        project.setStatus(ProjectApprovalStatus.APPROVED);
        project = projectRepository.save(project);
        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setId(project.getId());
        projectResponse.setSongTitle(project.getSongTitle());
        return projectResponse;
    }

    public ProjectResponse disApproveProject(String id) {
        Long projectId = Long.parseLong(id);
        Project project =projectRepository.findById(projectId).orElseThrow(()-> new ProjectException("No such Project Found"));
        project.setStatus(ProjectApprovalStatus.REJECTED);
        project = projectRepository.save(project);
        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setId(project.getId());
        projectResponse.setSongTitle(project.getSongTitle());
        return projectResponse;
    }
}
