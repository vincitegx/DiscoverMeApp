package com.discoverme.backend.user;

import com.discoverme.backend.project.SocialPlatform;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
public class UserSocialsDto {
    private Long id;
    private SocialPlatform socialPlatform;
    private String uri;
}
