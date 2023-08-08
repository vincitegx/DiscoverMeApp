package com.discoverme.backend.project;

import com.discoverme.backend.login.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/project")
@RequiredArgsConstructor
public class ProjectController {

    @PostMapping
    public ResponseEntity<?> addProject(@Valid @RequestBody LoginRequest loginRequest) {
//        JwtResponse response = loginService.login(loginRequest);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.AUTHORIZATION, response.getAuthToken())
//                .body(response);
        return ResponseEntity.ofNullable("");
    }
}
