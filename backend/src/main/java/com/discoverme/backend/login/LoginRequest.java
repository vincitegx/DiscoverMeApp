package com.discoverme.backend.login;

import lombok.Data;

@Data
public class LoginRequest {
    private String phoneNumber;
    private String password;
}
