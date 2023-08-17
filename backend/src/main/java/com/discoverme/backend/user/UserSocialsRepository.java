package com.discoverme.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSocialsRepository extends JpaRepository<UserSocials, Long> {
}
