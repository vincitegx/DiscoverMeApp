package com.discoverme.backend.user.registration;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NonNull;
@Data
public class RegistrationRequest {
    @NonNull
    @NotEmpty
    private String userName;
    @NonNull
    @NotEmpty
    private String email;
    @NonNull
    @NotEmpty
    private String password;
}
