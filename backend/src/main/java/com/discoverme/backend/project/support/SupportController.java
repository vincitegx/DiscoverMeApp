package com.discoverme.backend.project.support;

import com.discoverme.backend.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/project")
@RequiredArgsConstructor
public class SupportController {
    private final SupportService supportService;
    @PostMapping("support")
    public ResponseEntity<Void> supportProject(@RequestParam String projectId) {
        supportService.addSupporter(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("support")
    public ResponseEntity<List<UserDto>> getProjectSupporters(@RequestParam String projectId){
        List<UserDto> users =  supportService.getProjectSupporters(projectId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
