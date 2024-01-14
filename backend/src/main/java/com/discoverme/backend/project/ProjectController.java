package com.discoverme.backend.project;

import com.discoverme.backend.config.ApplicationProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    private final ApplicationProperties properties;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProjectResponse> submitProject(@RequestParam("request") String projectRequest,
                                                         @RequestParam("content") MultipartFile content) {
        ProjectRequest projectRequest1 = projectMapper.getProjectRequestJson(projectRequest);
        ProjectResponse projectResponse = projectService.submitProject(projectRequest1, content);
        return new ResponseEntity<>(projectResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProjectResponse>> getCurrentProjects(@RequestParam(name = "search", defaultValue = "") String search,
                                                                    @RequestParam(name = "page", defaultValue = "0") int page){
        Page<ProjectResponse> projectResponseList = projectService.getCurrentProjects(search,
                PageRequest.of(page, properties.getPageSize()));
        return new ResponseEntity<>(projectResponseList, HttpStatus.OK);
    }

    @GetMapping("limit")
    public ResponseEntity<Boolean> isProjectLimitExceeded(){
        return new ResponseEntity<>(projectService.isProjectLimitExceeded(), HttpStatus.OK);
    }

    @GetMapping("calender")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Page<ProjectResponse>> getAllProjectsForACalender(@RequestParam @NonNull Long calenderId, Pageable pageable){
        return new ResponseEntity<>(projectService.getAllProjectsForACalender(calenderId, pageable), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProject(@RequestParam Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
