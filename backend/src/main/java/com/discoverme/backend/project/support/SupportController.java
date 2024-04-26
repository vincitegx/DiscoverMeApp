package com.discoverme.backend.project.support;

import com.discoverme.backend.project.LoggedInUserService;
import com.discoverme.backend.project.ProjectResponse;
import com.discoverme.backend.user.UserDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
@RequiredArgsConstructor
public class SupportController {
    private final SupportService supportService;
    private final LoggedInUserService loggedInUserService;
    @PutMapping("{projectId}/support")
    public ResponseEntity<Void> toggleSupport(@PathVariable Long projectId) {
        supportService.toggleSupport(projectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("support")
    public ResponseEntity<List<UserDto>> getProjectSupporters(@RequestParam @NonNull Long projectId){
        List<UserDto> users =  supportService.getProjectSupporters(projectId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
