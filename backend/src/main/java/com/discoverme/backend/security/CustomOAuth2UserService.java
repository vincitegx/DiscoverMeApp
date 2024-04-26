package com.discoverme.backend.security;

import com.discoverme.backend.user.Roles;
import com.discoverme.backend.user.UserRepository;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class CustomOAuth2UserService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public Users upsertUser(GoogleIdToken.Payload payload) {
        ZonedDateTime createdAt = ZonedDateTime.now();
        Optional<Users> userOptional = userRepository.findByEmail(payload.getEmail());
        String password = UUID.randomUUID().toString();
        Users user;
        if (userOptional.isEmpty()) {
            user = new Users();
            user.setEmail(payload.getEmail());
            user.setUserName(payload.get("name").toString());
            user.setCreatedAt(createdAt);
            user.setEnabled(true);
            user.setNonLocked(true);
            user.setRole(Roles.USER.name());
        } else {
            user = userOptional.get();
        }
        user.setPassword(passwordEncoder.encode(password));
        user = userRepository.saveAndFlush(user);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        password
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return user;
    }
}

