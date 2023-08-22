package com.discoverme.backend.project;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProjectResponse> submitProject(@RequestParam("request") String projectRequest,
                                                         @RequestParam("artwork") MultipartFile artwork,
                                                         @RequestParam("song") MultipartFile song,
                                                         @RequestParam("contents") List<MultipartFile> contents) {
        ProjectRequest projectRequest1 = projectMapper.getProjectRequestJson(projectRequest);
        ProjectResponse projectResponse = projectService.submitProject(projectRequest1, artwork, song, contents);
        return new ResponseEntity<>(projectResponse, HttpStatus.OK);
    }

    @PutMapping("approve")
    public ResponseEntity<ProjectResponse> approveProject(@RequestParam String id) {
        ProjectResponse projectResponse = projectService.approveProject(id);
        return new ResponseEntity<>(projectResponse, HttpStatus.OK);
    }

    @PutMapping("disapprove")
    public ResponseEntity<ProjectResponse> disApproveProject(@RequestParam String id) {
        ProjectResponse projectResponse = projectService.disApproveProject(id);
        return new ResponseEntity<>(projectResponse, HttpStatus.OK);
    }

    @GetMapping("approved")
    public ResponseEntity<List<ProjectResponse>> getApprovedProjects(){
        List<ProjectResponse> projectResponseList = projectService.getApprovedProjects();
        return new ResponseEntity<>(projectResponseList, HttpStatus.OK);
    }
    @GetMapping("disapproved")
    public ResponseEntity<ProjectResponse> getDisApprovedProjects(){
        return null;
    }

    @GetMapping
    public ResponseEntity<ProjectResponse> getAllProjects(String projects){
        return null;
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProject(@RequestParam Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllProject() {
        projectService.deleteAllProject();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("voters")
    public ResponseEntity<List<ProjectResponse>> getTop5ProjectsWithTheHighestVoters(@RequestParam String id) {
        List<ProjectResponse> projectResponse = projectService.getTop5ProjectsWithTheHighestVoters(id);
        return new ResponseEntity<>(projectResponse, HttpStatus.OK);
    }
}
