package com.discoverme.backend.project;

import com.discoverme.backend.project.reaction.Reaction;
import com.discoverme.backend.project.reaction.ReactionRespository;
import com.discoverme.backend.project.support.Support;
import com.discoverme.backend.project.support.SupportRepository;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class LoggedInUserService {
    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final SupportRepository supportRepository;
    private final ReactionRespository reactionRespository;

    public boolean checkSupportStateForLoggedInUser(Long projectId) {
        boolean supportState = false;
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectException("Project Not Found with ID - " + projectId));
        if(userService.getCurrentUser() != null){
            Optional<Support> support = supportRepository.findTopByProjectAndUserOrderByIdDesc(project, userService.getCurrentUser());
            if(support.isPresent()){
                supportState = true;
            }
        }

        return supportState;
    }

    public Double getProjectsSupportedToLoggedInUser(Users projectUser) {
        Users loggedInUser = userService.getCurrentUser();
        List<Project> listOfProjectsByLoggedInUser = projectRepository.findByUser(loggedInUser);
        long totalSupports = listOfProjectsByLoggedInUser.stream()
                .mapToLong(project -> supportRepository.findByProjectAndUser(project, projectUser).isPresent() ? 1 : 0)
                .sum();
        long totalProjectsSupported = listOfProjectsByLoggedInUser.size();
        if (totalProjectsSupported > 0) {
            return (double) (totalSupports * 100) / totalProjectsSupported;
        } else {
            return 0.0; // To avoid division by zero
        }
    }

    public boolean checkReactionStateForLoggedInUser(Long id) {
        boolean reactState = false;
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectException("Project Not Found with ID - " + id));
        if(userService.getCurrentUser() != null){
            Optional<Reaction> reaction = reactionRespository.findTopByProjectAndUserOrderByIdDesc(project, userService.getCurrentUser());
            if(reaction.isPresent()){
                reactState = true;
            }
        }

        return reactState;
    }
}
