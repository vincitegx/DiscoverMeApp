package com.discoverme.backend.social;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialsRepository extends JpaRepository<Socials, Long> {

    Socials findByNameIgnoreCase(String name);
}
