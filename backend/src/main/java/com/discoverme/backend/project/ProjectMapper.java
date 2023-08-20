package com.discoverme.backend.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProjectMapper {
    public ProjectRequest getProjectRequestJson(String request){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(request, ProjectRequest.class);
        } catch (IOException ex) {
            throw new ProjectException(ex.getMessage());
        }
    }
}
