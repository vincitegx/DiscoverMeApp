package com.discoverme.backend.user.registration;

import lombok.Data;
import lombok.NonNull;

@Data
public class AdminRegistrationRequest {
    @NonNull
    private String phoneNumber;
    @NonNull
    private String password;
}
