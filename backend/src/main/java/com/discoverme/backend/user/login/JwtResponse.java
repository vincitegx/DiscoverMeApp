package com.discoverme.backend.user.login;

import com.discoverme.backend.user.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class JwtResponse {

    private String authToken;
    private UserDto user;

    public JwtResponse(String authToken, UserDto user){
        this.authToken = authToken;
        this.user = user;
    }
}
