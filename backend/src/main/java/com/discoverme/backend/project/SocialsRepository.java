package com.discoverme.backend.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialsRepository extends JpaRepository<Socials, Long> {

    Socials findByName(String name);
}
