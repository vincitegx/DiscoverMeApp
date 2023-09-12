package com.discoverme.backend.user.login;

import com.discoverme.backend.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private String authToken;
    private UserDto user;
}