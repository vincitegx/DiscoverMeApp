package com.discoverme.backend.project.support;

import com.discoverme.backend.project.ProjectResponse;
import com.discoverme.backend.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import lombok.NonNull;

@RestController
@RequestMapping("api/v1/project")
@RequiredArgsConstructor
public class SupportController {
    private final SupportService supportService;
    @PostMapping("support")
    public ResponseEntity<Void> supportProject(@RequestParam @NonNull String projectId) {
        supportService.addSupporter(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("unsupport")
    public ResponseEntity<Void> unsupportProject(@RequestParam @NonNull String projectId) {
        supportService.unSupportProject(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("support")
    public ResponseEntity<List<UserDto>> getProjectSupporters(@RequestParam @NonNull String projectId){
        List<UserDto> users =  supportService.getProjectSupporters(projectId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    @GetMapping("supported")
    public ResponseEntity<List<ProjectResponse>> getProjectsSupported(@RequestParam @NonNull String id){
        Long userId = Long.parseLong(id);
        List<ProjectResponse> projects = supportService.getProjectsSupported(userId);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}
