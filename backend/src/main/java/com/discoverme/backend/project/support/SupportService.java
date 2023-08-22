package com.discoverme.backend.project.support;

import com.discoverme.backend.project.*;
import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.UserMapper;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public List<UserDto> getProjectSupporters(String projectId) {
        Project project = Project.builder()
                .id(Long.parseLong(projectId))
                .build();
        List<Support> support = supportRepository.findByProject(project);
        List<UserDto> users = new ArrayList<>();
        support.forEach(s->{
            users.add(userMapper.apply(s.getUser()));
        });
        return users;
    }

    public void addSupporter(String projectId){
        Project project = projectService.findById(Long.parseLong(projectId)).orElseThrow(()-> new ProjectException("No such project found with that ID"));
        if(!project.getCalender().getStatus().equals(PeriodStatus.SUPPORT)){
            throw new ProjectException("You cannot support a project currently");
        }
        Optional<Support> supportByPostAndUser = supportRepository.findTopByProjectAndUserOrderByIdDesc(project, userService.getCurrentUser());
        if(supportByPostAndUser.isPresent()){
            throw new ProjectException("Already added to supported");
        }
        Support support= Support.builder()
                    .project(project)
                    .user(userService.getCurrentUser())
                    .build();
        supportRepository.save(support);
        project.setSupportCount(project.getSupportCount()+ 1);
        projectService.saveProject(project);
    }

    void unSupportProject(String projectId) {
        Project project = projectService.findById(Long.parseLong(projectId)).orElseThrow(()-> new ProjectException("No such project found with that ID"));
        Optional<Support> supportByPostAndUser = supportRepository.findTopByProjectAndUserOrderByIdDesc(project, userService.getCurrentUser());
        if(supportByPostAndUser.isEmpty()){
            throw new ProjectException("Project was not initially supported");
        }else{
            supportRepository.delete(supportByPostAndUser.get());
            project.setSupportCount(project.getSupportCount()- 1);
            projectService.saveProject(project);
        }
    }

    List<ProjectResponse> getProjectsSupported(Long userId) {
        Users user = userService.findById(userId).orElseThrow(()-> new ProjectException("No Such User"));
        List<ProjectResponse> projectResponses = new ArrayList<>();
        List<Support> supports =supportRepository.findByUser(user);
        supports.forEach(support->{
            ProjectResponse projectResponse = projectService.mapProjectToResponse(support.getProject());
            projectResponses.add(projectResponse);
        });
        return projectResponses;
    }
}
