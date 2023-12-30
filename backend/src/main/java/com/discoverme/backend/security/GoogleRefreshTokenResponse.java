package com.discoverme.backend.security;

public record GoogleRefreshTokenResponse(String access_token, Integer expires_in, Object scope, String token_type, String id_token) {
}
