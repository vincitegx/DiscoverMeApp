package com.discoverme.backend.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectCalenderRepository extends JpaRepository<ProjectCalender, Long> {
    Optional<ProjectCalender> findByName(String name);
    Optional<ProjectCalender> findFirstByOrderByIdDesc();
}
