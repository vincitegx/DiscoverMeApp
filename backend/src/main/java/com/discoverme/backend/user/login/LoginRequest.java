package com.discoverme.backend.user.login;

import lombok.Data;

@Data
public class LoginRequest {
    private String phoneNumber;
    private String password;
}
