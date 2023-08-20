package com.discoverme.backend.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(NON_NULL)
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
