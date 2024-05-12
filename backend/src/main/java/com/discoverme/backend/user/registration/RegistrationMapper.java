package com.discoverme.backend.user.registration;

import com.discoverme.backend.user.Roles;
import com.discoverme.backend.user.Users;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class RegistrationMapper {
    public Users mapRegistrationRequestToUser(RegistrationRequest registrationRequest) {
        ZonedDateTime createdAt = ZonedDateTime.now();
        return Users.builder()
                .email(registrationRequest.getEmail())
                .userName(getUsernameFromEmail(registrationRequest.getEmail()))
                .password(registrationRequest.getPassword())
                .enabled(false)
                .nonLocked(false)
                .role(Roles.USER.name())
                .createdAt(createdAt)
                .build();
    }

    public RegistrationResponse mapUserToRegistrationResponse(Users users) {
        return RegistrationResponse.builder()
                .userId(users.getId())
                .userName(users.getUserName())
                .email(users.getEmail())
                .build();
    }

    public Users mapRegistrationRequestToAdmin(AdminRegistrationRequest registrationRequest) {
        ZonedDateTime createdAt = ZonedDateTime.now();
        return Users.builder()
                .email(registrationRequest.getEmail())
                .userName("Administrator")
                .password(registrationRequest.getPassword())
                .enabled(true)
                .nonLocked(true)
                .role(Roles.ADMIN.name())
                .createdAt(createdAt)
                .build();
    }

    public String getUsernameFromEmail(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex != -1) {
            return email.substring(0, atIndex);
        } else {
            return email;
        }
    }
}
