package com.discoverme.backend.project.voting;

import com.discoverme.backend.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteResponse {
    private Long projectId;
    private UserDto user;
}
