package com.discoverme.backend.project.support;

import com.discoverme.backend.project.Project;
import com.discoverme.backend.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupportRepository extends JpaRepository<Support, Long> {
    Optional<Support> findByProjectAndUser(Project project, Users user);
    List<Support> findByProject(Project project);
    List<Support> findByUser(Users user);
    Optional<Support> findTopByProjectAndUserOrderByIdDesc(Project project, Users currentUser);
}
