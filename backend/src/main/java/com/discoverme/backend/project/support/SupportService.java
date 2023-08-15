package com.discoverme.backend.project.support;

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
        Support support = Support.builder()
                .project(project)
                .user(userService.getCurrentUser())
                .build();
        supportRepository.save(support);
    }
}
