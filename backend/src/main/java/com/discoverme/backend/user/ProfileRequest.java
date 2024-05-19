package com.discoverme.backend.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProfileRequest {
    @NotNull
    private String username;
}
