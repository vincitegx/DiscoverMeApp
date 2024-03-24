package com.discoverme.backend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public final class ApplicationProperties {
    @Value("${jwt.expiry.minutes}")
    private Long jwtValidity;

    @Value("${refresh.token.cookie}")
    private String refreshTokenCookie;

    @Value("${frontend.domain}")
    private String frontendDomain;

    @Value("${organization.properties.mail}")
    private String mailAddress;

    @Value("${spring.security.oauth2.resourceserver.opaque-token.introspection-uri}")
    private String introspectionUri;

    @Value("${spring.security.oauth2.resourceserver.opaque-token.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.resourceserver.opaque-token.client-secret}")
    private String googleClientSecret;

    @Value("${file.upload-dir}")
    private String fileUploadDir;

    @Value("${pagination.content.max-size}")
    private Integer pageSize;

    @Value("${project.max-size}")
    private Integer projectMaxSize;
}
