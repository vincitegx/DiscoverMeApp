package com.discoverme.backend.user;

import com.discoverme.backend.user.social.UserSocialsDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
public class UserDto {
    private Long id;
    private String userName;
    private String email;
    private String role;
    private Set<UserSocialsDto> socials;
}
