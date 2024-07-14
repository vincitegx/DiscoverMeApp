package com.discoverme.backend.project.reaction;

import com.discoverme.backend.project.Project;
import com.discoverme.backend.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionRespository extends JpaRepository<Reaction, Long> {
    List<Reaction> findByProject(Project project);

    Optional<Reaction> findTopByProjectAndUserOrderByIdDesc(Project project, Users currentUser);
}
