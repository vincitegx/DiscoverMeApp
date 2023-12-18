package com.discoverme.backend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public final class ApplicationProperties {
    @Value("${jwt.expiry.minutes}")
    private Long jwtValidity;
    private final String refreshTokenCookie="refresh-token";
}
