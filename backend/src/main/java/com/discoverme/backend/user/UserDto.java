package com.discoverme.backend.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
public class UserDto {
    private Long id;
    private String stageName;
    private String phoneNumber;
    private String role;
    private Set<UserSocialsDto> userSocials;
}
