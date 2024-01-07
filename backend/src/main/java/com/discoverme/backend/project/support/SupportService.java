package com.discoverme.backend.project.support;

import com.discoverme.backend.project.Project;
import com.discoverme.backend.project.ProjectException;
import com.discoverme.backend.project.ProjectService;
import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.UserMapper;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.social.FacebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupportService {
    private final SupportRepository supportRepository;
    private final UserMapper userMapper;
    private final UserService userService;
    private final ProjectService projectService;
    private final FacebookService facebookService;
    public List<UserDto> getProjectSupporters(Long projectId) {
        Project project = projectService.findById(projectId).orElseThrow(()-> new ProjectException("Project not found with ID"));
        List<Support> support = supportRepository.findByProject(project);
        List<UserDto> users = new ArrayList<>();
        support.forEach(s-> users.add(userMapper.apply(s.getUser())));
        return users;
    }

    public void toggleSupport(Long projectId){
        Project project = projectService.findById(projectId).orElseThrow(()-> new ProjectException("No such project found with that ID"));
        Optional<Support> supportByProjectAndUser = supportRepository.findTopByProjectAndUserOrderByIdDesc(project, userService.getCurrentUser());
        supportByProjectAndUser.ifPresentOrElse(v -> {
            supportRepository.delete(v);
            if (project.getSupportCount() >= 0) {
                project.setSupportCount(project.getSupportCount() - 1);
                projectService.saveProject(project);
            }
        }, () -> {
            if(project.getSocial().getName() == "FACEBOOK"){
                try {
                    facebookService.postVideo(project.getContentUri());
                } catch (MalformedURLException | FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            Support support = Support.builder()
                    .project(project)
                    .user(userService.getCurrentUser())
                    .build();
            supportRepository.save(support);
            project.setSupportCount(project.getSupportCount() + 1);
            projectService.saveProject(project);
        });
    }
}
