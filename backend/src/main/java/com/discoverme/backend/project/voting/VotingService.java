package com.discoverme.backend.project.voting;

import com.discoverme.backend.project.Project;
import com.discoverme.backend.project.ProjectException;
import com.discoverme.backend.project.ProjectService;
import com.discoverme.backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VotingService {
    private final ProjectService projectService;
    private final UserService userService;
    private final VoteRepository voteRepository;
    public void vote(VoteDto voteDto) {
        Project project = projectService.findById(voteDto.getProjectId())
                .orElseThrow(() -> new ProjectException("Project Not Found with ID - " + voteDto.getProjectId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByProjectAndUserOrderByIdDesc(project, userService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new ProjectException("You have already "
                    + voteDto.getVoteType() + "'d for this project");
        }
        if (VoteType.UPVOTE.equals(voteDto.getVoteType())) {
            project.setVoteCount(project.getVoteCount() + 1);
        } else {
            if(project.getVoteCount() >= 0){
                project.setVoteCount(project.getVoteCount() - 1);
            }
        }
        voteRepository.save(mapToVote(voteDto, project));
        projectService.saveProject(project);
    }

    private Vote mapToVote(VoteDto voteDto, Project project) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .project(project)
                .user(userService.getCurrentUser())
                .build();
    }
}
