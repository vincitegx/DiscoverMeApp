package com.discoverme.backend.project.voting;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Void> vote(@RequestBody VoteDto voteDto) {
        votingService.vote(voteDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
