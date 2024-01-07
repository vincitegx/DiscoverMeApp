package com.discoverme.backend.user.social;

import com.discoverme.backend.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSocialsRepository extends JpaRepository<UserSocials, Long> {
    List<UserSocials> findByUser(Users user);
}
