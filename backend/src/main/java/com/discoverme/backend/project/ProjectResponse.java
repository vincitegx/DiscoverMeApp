package com.discoverme.backend.project;

import com.discoverme.backend.user.UserDto;
import lombok.Data;

@Data
public class ProjectResponse {
    private Long id;
    private String songTitle;
    private UserDto user;
}
