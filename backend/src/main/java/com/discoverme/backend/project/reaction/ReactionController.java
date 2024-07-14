package com.discoverme.backend.project.reaction;

import com.discoverme.backend.project.ProjectResponse;
import com.discoverme.backend.project.support.SupportService;
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
public class ReactionController {

    private final ReactionService reactionService;
    @PutMapping("{projectId}/react")
    public ResponseEntity<ProjectResponse> toggleReaction(@PathVariable Long projectId) {
        ProjectResponse response = reactionService.toggleReaction(projectId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("react")
    public ResponseEntity<List<UserDto>> getProjectReactors(@RequestParam @NonNull Long projectId){
        List<UserDto> users =  reactionService.getProjectReactions(projectId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
