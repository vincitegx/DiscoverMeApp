package com.discoverme.backend.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectTagRepository extends JpaRepository<ProjectTag, Long> {
    Optional<ProjectTag> findByName(String name);
    Optional<ProjectTag> findFirstByOrderByIdDesc();
}
