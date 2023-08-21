package com.discoverme.backend.project;

import com.discoverme.backend.project.support.Support;
import com.discoverme.backend.project.support.SupportRepository;
import com.discoverme.backend.project.voting.Vote;
import com.discoverme.backend.project.voting.VoteRepository;
import com.discoverme.backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class LoggedInUserService {
    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final VoteRepository voteRepository;
    private final SupportRepository supportRepository;

    public boolean checkVoteStateForLoggedInUser(String projectId) {
        boolean voteState = false;
        Project project = projectRepository.findById(Long.parseLong(projectId))
                .orElseThrow(() -> new ProjectException("Project Not Found with ID - " + projectId));
        Optional<Vote> voteByProjectAndUser = voteRepository.findTopByProjectAndUserOrderByIdDesc(project, userService.getCurrentUser());
        if(voteByProjectAndUser.isPresent()){
            voteState = true;
        }
        return voteState;
    }

    public boolean checkSupportStateForLoggedInUser(String projectId) {
        boolean supportState = false;
        Project project = projectRepository.findById(Long.parseLong(projectId))
                .orElseThrow(() -> new ProjectException("Project Not Found with ID - " + projectId));
        Optional<Support> support = supportRepository.findTopByProjectAndUserOrderByIdDesc(project, userService.getCurrentUser());
        if(support.isPresent()){
            supportState = true;
        }
        return supportState;
    }
}
