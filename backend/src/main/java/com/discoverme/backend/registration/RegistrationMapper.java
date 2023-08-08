package com.discoverme.backend.registration;

import com.discoverme.backend.user.Roles;
import com.discoverme.backend.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
@Service
@RequiredArgsConstructor
public class RegistrationMapper {
    public Users mapRegistrationRequestToUser(RegistrationRequest registrationRequest) {
        ZonedDateTime createdAt = ZonedDateTime.now();
        return Users.builder()
                .phoneNumber(registrationRequest.getPhoneNumber())
                .stageName(registrationRequest.getStageName())
                .password(registrationRequest.getPassword())
                .facebookUri(registrationRequest.getFacebookUri())
                .instagramUri(registrationRequest.getInstagramUri())
                .tiktokUri(registrationRequest.getTiktokUri())
                .twitterUri(registrationRequest.getTwitterUri())
                .youtubeUri(registrationRequest.getYoutubeUri())
                .enabled(false)
                .nonLocked(false)
                .role(Roles.USER.name())
                .createdAt(createdAt)
                .build();
    }

    public RegistrationResponse mapUserToRegistrationRequest(Users users) {
        return RegistrationResponse.builder()
                .userId(users.getId())
                .stageName(users.getStageName())
                .build();
    }
}
