package com.discoverme.backend.project.calender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalenderRepository extends JpaRepository<Calender, Long> {
    Optional<Calender> findByName(String name);
    Optional<Calender> findFirstByOrderByIdDesc();
}
