package com.discoverme.backend.security;

import com.discoverme.backend.user.Roles;
import com.discoverme.backend.user.UserRepository;
import com.discoverme.backend.user.Users;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class CustomOAuth2UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public Users upsertUser(GoogleIdToken.Payload payload) {
        ZonedDateTime createdAt = ZonedDateTime.now();
        Optional<Users> userOptional = userRepository.findByEmail(payload.getEmail());
        Users user;
        if (userOptional.isEmpty()) {
            System.out.println("user not found");
            String password = UUID.randomUUID().toString();
            user = new Users();
            user.setEmail(payload.getEmail());
            user.setStageName(payload.get("name").toString());
            user.setCreatedAt(createdAt);
            user.setEnabled(true);
            user.setNonLocked(true);
            user.setRole(Roles.USER.name());
            user.setPassword(passwordEncoder.encode(password));
        } else {
            System.out.println("user found");
            user = userOptional.get();
        }
        return userRepository.saveAndFlush(user);
    }
}

