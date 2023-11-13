package com.discoverme.backend.user.registration;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NonNull;
@Data
public class RegistrationRequest {
    @NonNull
    @NotEmpty
    private String stageName;
    @NonNull
    @NotEmpty
    private String email;
    @NonNull
    @NotEmpty
    private String password;
}
