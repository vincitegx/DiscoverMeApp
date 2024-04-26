package com.discoverme.backend.user.social;

import com.discoverme.backend.social.Socials;
import com.discoverme.backend.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSocialsRepository extends JpaRepository<UserSocials, Long> {
    List<UserSocials> findByUser(Users user);
    Optional<UserSocials> findByUserAndSocial(Users user, Socials social);
}
