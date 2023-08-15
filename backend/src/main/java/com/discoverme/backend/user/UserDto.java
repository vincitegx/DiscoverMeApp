package com.discoverme.backend.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String stageName;
    private String phoneNumber;
    private String role;
    private String facebookUri;
    private String xUri;
    private String instagramUri;
    private String tiktokUri;
    private String youtubeUri;
}
