package com.discoverme.backend.user.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegistrationResponse {
    private Long userId;
    private String stageName;
    private String email;
}
