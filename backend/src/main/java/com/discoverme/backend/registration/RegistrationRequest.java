package com.discoverme.backend.registration;

import lombok.Data;
import lombok.NonNull;
@Data
public class RegistrationRequest {
    @NonNull
    private String stageName;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String password;
    @NonNull
    private String facebookUri;
    @NonNull
    private String twitterUri;
    @NonNull
    private String instagramUri;
    @NonNull
    private String tiktokUri;
    @NonNull
    private String youtubeUri;
}
