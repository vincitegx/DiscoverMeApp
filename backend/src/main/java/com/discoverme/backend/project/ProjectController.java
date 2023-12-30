package com.discoverme.backend.project;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProjectResponse> submitProject(@RequestParam("request") String projectRequest,
                                                         @RequestParam("content") MultipartFile content) {
        ProjectRequest projectRequest1 = projectMapper.getProjectRequestJson(projectRequest);
        ProjectResponse projectResponse = projectService.submitProject(projectRequest1, content);
        return new ResponseEntity<>(projectResponse, HttpStatus.OK);
    }

    @PutMapping("approve")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ProjectResponse> approveProject(@RequestParam String id) {
        ProjectResponse projectResponse = projectService.approveProject(id);
        return new ResponseEntity<>(projectResponse, HttpStatus.OK);
    }

    @PutMapping("disapprove")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ProjectResponse> disApproveProject(@RequestParam String id) {
        ProjectResponse projectResponse = projectService.disApproveProject(id);
        return new ResponseEntity<>(projectResponse, HttpStatus.OK);
    }

    @GetMapping("approved")
    public ResponseEntity<Page<ProjectResponse>> getApprovedProjects(@RequestParam Optional<String> search,
                                                                     @RequestParam Optional<Integer> page){
        Page<ProjectResponse> projectResponseList = projectService.getApprovedProjects(search.orElse(""),
                PageRequest.of(page.orElse(0), 3));
        return new ResponseEntity<>(projectResponseList, HttpStatus.OK);
    }
    @GetMapping("disapproved")
    public ResponseEntity<Page<ProjectResponse>> getDisApprovedProjects(Pageable pageable){
        Page<ProjectResponse> projectResponseList = projectService.getDisApprovedProjects(pageable);
        return new ResponseEntity<>(projectResponseList, HttpStatus.OK);
    }

    @GetMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Page<ProjectResponse>> getAllProjectsForACalender(@RequestParam @NonNull String calenderId, Pageable pageable){
        Long id = Long.parseLong(calenderId);
        return new ResponseEntity<>(projectService.getAllProjectsForACalender(id, pageable), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProject(@RequestParam Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("socials")
    public ResponseEntity<List<Socials>> getAllSocials(){
        return new ResponseEntity<>(projectService.getAllSocials(), HttpStatus.OK);
    }

    @DeleteMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteDisApprovedProjects(Pageable pageable) {
        projectService.deleteDisApprovedProjects(pageable);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("voters")
    public ResponseEntity<List<ProjectResponse>> getTop5ProjectsWithTheHighestVoters(@RequestParam String id) {
        List<ProjectResponse> projectResponse = projectService.getTop5ProjectsWithTheHighestVoters(id);
        return new ResponseEntity<>(projectResponse, HttpStatus.OK);
    }
}
