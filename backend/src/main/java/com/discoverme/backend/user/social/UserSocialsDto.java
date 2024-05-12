package com.discoverme.backend.user.social;

import com.discoverme.backend.social.SocialPlatform;
import com.discoverme.backend.user.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
public class UserSocialsDto {
    private Long id;
//    private UserDto user;
    private SocialPlatform social;
    private String socialUserName;
}
