package com.discoverme.backend.project;

import com.discoverme.backend.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByUserAndProjectTag(Users user, ProjectTag projectTag);
    List<Project> findByStatusAndProjectTag(ProjectApprovalStatus status, ProjectTag projectTag);
}
