package com.discoverme.backend.registration;

import com.discoverme.backend.user.Users;

import java.util.function.Function;

public class RegistrationMapper {
    public Users mapRegistrationRequestToUser(RegistrationRequest registrationRequest) {
        return Users.builder()
                .phoneNumber(registrationRequest.getPhoneNumber())
                .stageName(registrationRequest.getStageName())
                .enabled(false)
                .nonLocked(false)
                .password(registrationRequest.getPassword())
                .facebookUri(registrationRequest.getFacebookUri())
                .instagramUri(registrationRequest.getInstagramUri())
                .tiktokUri(registrationRequest.getTiktokUri())
                .twitterUri(registrationRequest.getTwitterUri())
                .youtubeUri(registrationRequest.getYoutubeUri())
                .build();
    }

    public RegistrationResponse mapUserToRegistrationRequest(Users users) {
        return RegistrationResponse.builder()
                .userId(users.getId())
                .stageName(users.getStageName())
                .build();
    }
}
