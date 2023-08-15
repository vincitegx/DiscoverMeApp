package com.discoverme.backend.registration;

import com.discoverme.backend.user.Roles;
import com.discoverme.backend.user.Users;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class RegistrationMapper {
    public Users mapRegistrationRequestToUser(RegistrationRequest registrationRequest) {
        ZonedDateTime createdAt = ZonedDateTime.now();
        Users user =  Users.builder()
                .phoneNumber(registrationRequest.getPhoneNumber())
                .stageName(registrationRequest.getStageName())
                .password(registrationRequest.getPassword())
                .enabled(false)
                .nonLocked(false)
                .role(Roles.USER.name())
                .createdAt(createdAt)
                .build();
        return user;
    }

    public RegistrationResponse mapUserToRegistrationRequest(Users users) {
        return RegistrationResponse.builder()
                .userId(users.getId())
                .stageName(users.getStageName())
                .role(users.getRole())
                .build();
    }

    public Users mapRegistrationRequestToAdmin(AdminRegistrationRequest registrationRequest) {
        ZonedDateTime createdAt = ZonedDateTime.now();
        Users user =  Users.builder()
                .phoneNumber(registrationRequest.getPhoneNumber())
                .stageName("Administrator")
                .password(registrationRequest.getPassword())
                .enabled(true)
                .nonLocked(true)
                .role(Roles.ADMIN.name())
                .createdAt(createdAt)
                .build();
        return user;
    }
}
