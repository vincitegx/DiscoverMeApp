package com.discoverme.backend.project.voting;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/projects")
@RequiredArgsConstructor
public class VotingController {
    private final VotingService votingService;
    @PutMapping("{projectId}/vote")
    public ResponseEntity<Void> vote(@PathVariable Long projectId) {
        votingService.vote(projectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
