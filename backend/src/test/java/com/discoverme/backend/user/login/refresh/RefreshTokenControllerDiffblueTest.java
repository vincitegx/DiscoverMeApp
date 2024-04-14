package com.discoverme.backend.user.login.refresh;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.discoverme.backend.security.JWTUtil;
import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.UserMapper;

import javax.security.auth.RefreshFailedException;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.reactive.function.client.WebClient;

class RefreshTokenControllerDiffblueTest {
    /**
     * Method under test: {@link RefreshTokenController#refreshToken(UserDto)}
     */
    @Test
    void testRefreshToken() throws RefreshFailedException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        RefreshTokenRepository refreshTokenRepository = mock(RefreshTokenRepository.class);
        JWTUtil jwtUtil = new JWTUtil();
        RefreshTokenService refreshTokenService = new RefreshTokenService(refreshTokenRepository, jwtUtil,
                new UserMapper());

        // Act and Assert
        assertThrows(RefreshFailedException.class,
//                () -> (new RefreshTokenController(refreshTokenService,  mock(WebClient.class)))
                () -> (new RefreshTokenController(refreshTokenService, new MockHttpServletRequest(), mock(WebClient.class)))
                        .refreshToken(null));
    }
}
