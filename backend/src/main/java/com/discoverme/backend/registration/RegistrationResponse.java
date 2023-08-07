package com.discoverme.backend.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RegistrationResponse {
    private Long userId;
    private String stageName;
    private List<String> roles;
}
