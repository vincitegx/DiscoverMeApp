package com.discoverme.backend.project.voting;

import com.discoverme.backend.project.PeriodStatus;
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

    public void vote(Long projectId) {
        Project project = projectService.findById(projectId)
                .orElseThrow(() -> new ProjectException("Project Not Found with ID - " + projectId));
        if(!project.getCalender().getStatus().equals(PeriodStatus.SUBMISSION)){
            throw new ProjectException("You cannot vote a project currently");
        }
        Optional<Vote> voteByProjectAndUser = voteRepository.findTopByProjectAndUserOrderByIdDesc(project, userService.getCurrentUser());
        voteByProjectAndUser.ifPresentOrElse(v -> {
            voteRepository.delete(v);
            if (project.getVoteCount() >= 0) {
                project.setVoteCount(project.getVoteCount() - 1);
                projectService.saveProject(project);
            }
        }, () -> {
            Vote vote = Vote.builder()
                    .project(project)
                    .user(userService.getCurrentUser())
                    .build();
            voteRepository.save(vote);
            project.setVoteCount(project.getVoteCount() + 1);
            projectService.saveProject(project);
        });
    }
}
