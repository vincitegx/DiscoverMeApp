package com.discoverme.backend.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    public ProjectResponse addProject(ProjectRequest projectRequest){
        return new ProjectResponse();
    }

}
