package com.discoverme.backend.project.reaction;

import com.discoverme.backend.project.Project;
import com.discoverme.backend.project.ProjectException;
import com.discoverme.backend.project.ProjectResponse;
import com.discoverme.backend.project.ProjectService;
import com.discoverme.backend.project.support.Support;
import com.discoverme.backend.social.SocialPlatform;
import com.discoverme.backend.social.facebook.FacebookService;
import com.discoverme.backend.social.instagram.InstagramService;
import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.UserMapper;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import com.restfb.exception.FacebookOAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReactionService {
    private final UserMapper userMapper;
    private final UserService userService;
    private final ProjectService projectService;
    private final FacebookService facebookService;
    private final InstagramService instagramService;
    private final ReactionRespository reactionRespository;


    public ProjectResponse toggleReaction(Long projectId) {
        Project project = projectService.findById(projectId).orElseThrow(()-> new ProjectException("No such project found with that ID"));
        Optional<Reaction> reactionByProjectAndUser = reactionRespository.findTopByProjectAndUserOrderByIdDesc(project, userService.getCurrentUser());
        reactionByProjectAndUser.ifPresentOrElse(v -> {
            reactionRespository.delete(v);
            if (project.getReactionCount() > 0) {
                project.setReactionCount(project.getReactionCount() - 1);
                projectService.saveProject(project);
            }
        }, () -> {
            Reaction reaction = Reaction.builder()
                    .project(project)
                    .user(userService.getCurrentUser())
                    .build();
            reactionRespository.save(reaction);
            project.setReactionCount(project.getReactionCount() + 1);
            projectService.saveProject(project);
        });
        return projectService.mapProjectToResponse(project);
    }

    public List<UserDto> getProjectReactions(Long projectId) {
        Project project = projectService.findById(projectId).orElseThrow(()-> new ProjectException("Project not found with ID"));
        List<Reaction> reactions = reactionRespository.findByProject(project);
        List<UserDto> users = new ArrayList<>();
        reactions.forEach(reaction-> users.add(userMapper.apply(reaction.getUser())));
        return users;
    }
}
