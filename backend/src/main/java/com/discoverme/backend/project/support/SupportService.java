package com.discoverme.backend.project.support;

import com.discoverme.backend.project.PeriodStatus;
import com.discoverme.backend.project.Project;
import com.discoverme.backend.project.ProjectException;
import com.discoverme.backend.project.ProjectService;
import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.UserMapper;
import com.discoverme.backend.user.UserService;
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
        support.forEach(s->users.add(userMapper.apply(s.getUser())));
        return users;
    }

    public void addSupporter(String projectId){
        Project project = projectService.findById(Long.parseLong(projectId)).orElseThrow(()-> new ProjectException("No such project found with that ID"));
        if(!project.getCalender().getStatus().equals(PeriodStatus.SUPPORT)){
            throw new ProjectException("You cannot support a project currently");
        }
        Optional<Support> supportByProjectAndUser = supportRepository.findTopByProjectAndUserOrderByIdDesc(project, userService.getCurrentUser());
        if(supportByProjectAndUser.isPresent()){
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

    public void unSupportProject(String projectId) {
        Project project = projectService.findById(Long.parseLong(projectId)).orElseThrow(()-> new ProjectException("No such project found with that ID"));
        Optional<Support> supportByProjectAndUser = supportRepository.findTopByProjectAndUserOrderByIdDesc(project, userService.getCurrentUser());
        if(supportByProjectAndUser.isEmpty()){
            throw new ProjectException("Project was not initially supported");
        }else{
            supportRepository.delete(supportByProjectAndUser.get());
            if(project.getSupportCount() >= 0){
                project.setSupportCount(project.getSupportCount()- 1);
                projectService.saveProject(project);
            }
        }
    }
}
