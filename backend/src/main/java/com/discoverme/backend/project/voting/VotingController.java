package com.discoverme.backend.project.voting;

import com.discoverme.backend.project.ProjectRequest;
import com.discoverme.backend.project.ProjectResponse;
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
public class VotingController {
    private final VotingService votingService;
    @PostMapping("vote")
    public ResponseEntity<ProjectResponse> toggleVote(@Valid @RequestBody ProjectRequest projectRequest) {
//        ProjectResponse projectResponse = votingService.toggleVote(projectRequest);
//        return new ResponseEntity<>(projectResponse, HttpStatus.OK);
        return null;
    }
}
