package com.discoverme.backend.login;

import com.discoverme.backend.user.UserDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshTokenRequest {
    @NotBlank
    private String refreshToken;
    private UserDto user;

}
