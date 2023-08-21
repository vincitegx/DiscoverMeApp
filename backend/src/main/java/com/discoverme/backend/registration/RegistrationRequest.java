package com.discoverme.backend.registration;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NonNull;
@Data
public class RegistrationRequest {
    @NonNull
    @NotEmpty
    private String stageName;
    @NonNull
    @NotEmpty
    private String phoneNumber;
    @NonNull
    @NotEmpty
    private String password;
    @NonNull
    @NotEmpty
    private String facebookUri;
    @NonNull
    @NotEmpty
    private String xUri;
    @NonNull
    @NotEmpty
    private String instagramUri;
    @NonNull
    @NotEmpty
    private String tiktokUri;
    @NonNull
    @NotEmpty
    private String youtubeUri;
}
