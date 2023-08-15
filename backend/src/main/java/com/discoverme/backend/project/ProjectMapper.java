package com.discoverme.backend.project;

import com.discoverme.backend.user.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProjectMapper {
    private final UserMapper userMapper;
    public ProjectRequest getProjectRequestJson(String request){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(request, ProjectRequest.class);
        } catch (IOException ex) {
            throw new ProjectException(ex.getMessage());
        }
    }

    public ProjectResponse mapProjectToResponse(Project project){
        return ProjectResponse.builder()
                .user(userMapper.apply(project.getUser()))
                .songTitle(project.getSongTitle())
                .build();
    }
}
