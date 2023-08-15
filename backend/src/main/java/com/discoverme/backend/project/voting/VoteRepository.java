package com.discoverme.backend.project.voting;


import com.discoverme.backend.project.Project;
import com.discoverme.backend.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByProjectAndUserOrderByIdDesc(Project project, Users currentUser);
}
