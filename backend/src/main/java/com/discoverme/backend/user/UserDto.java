package com.discoverme.backend.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String stageName;
    private String role;
}
