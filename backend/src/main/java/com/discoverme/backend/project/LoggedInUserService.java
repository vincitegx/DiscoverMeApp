package com.discoverme.backend.project;

import com.discoverme.backend.project.support.Support;
import com.discoverme.backend.project.support.SupportRepository;
import com.discoverme.backend.project.voting.Vote;
import com.discoverme.backend.project.voting.VoteRepository;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<ProjectResponse> getProjectsSupported(Long userId) {
        Users user = userService.findById(userId).orElseThrow(()-> new ProjectException("No Such User"));
        List<ProjectResponse> projectResponses = new ArrayList<>();
        List<Support> supports =supportRepository.findByUser(user);
        supports.forEach(support->{
            ProjectResponse projectResponse = mapProjectToResponse(support.getProject());
            projectResponses.add(projectResponse);
        });
        return projectResponses;
    }
    public ProjectResponse mapProjectToResponse(Project project){
        return ProjectResponse.builder()
                .songTitle(project.getSongTitle())
                .artworkUri(project.getArtworkUri())
                .id(project.getId())
                .isSupported(checkSupportStateForLoggedInUser(project.getId().toString()))
                .isVoted(checkVoteStateForLoggedInUser(project.getId().toString()))
                .noOfVoters(project.getVoteCount())
                .songUri(project.getSongUri())
                .socials(project.getSocials())
                .stageName(project.getUser().getStageName())
                .build();
    }

}
