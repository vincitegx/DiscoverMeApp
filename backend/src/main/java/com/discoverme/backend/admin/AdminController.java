package com.discoverme.backend.admin;

import com.discoverme.backend.project.voting.VoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TEGA
 */
@RestController
@RequestMapping("api/v1/admins")
@RequiredArgsConstructor
public class AdminController {
    @PostMapping("remove")
    public ResponseEntity<Void> removeAdmin(@RequestBody VoteResponse voteDto) {
//        votingService.vote(voteDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }    
}
