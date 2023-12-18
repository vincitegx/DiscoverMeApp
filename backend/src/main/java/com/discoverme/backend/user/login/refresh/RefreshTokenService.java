package com.discoverme.backend.user.login.refresh;

import com.discoverme.backend.security.JWTUtil;
import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.UserException;
import com.discoverme.backend.user.UserMapper;
import com.discoverme.backend.user.Users;
import com.discoverme.backend.user.login.JwtResponse;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JWTUtil jwtUtil;
    private final UserMapper userMapper;
    private final Clock clock = Clock.systemDefaultZone();

    public String generateRefreshToken(Users user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiresAt(LocalDateTime.now().plusDays(1));
        refreshToken.setUser(user);
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

//    @CachePut(cacheNames = "userdto")
    public JwtResponse refreshToken(UserDto user, String token) {
        RefreshToken refreshToken = validateRefreshToken(user, token);
        String authToken = jwtUtil.generateJwtToken(refreshToken);
        return new JwtResponse(authToken, user);
    }

    public RefreshToken validateRefreshToken(UserDto userDto, String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new UserException("Invalid refresh Token"));
        if (refreshToken.getExpiresAt().isBefore(LocalDateTime.now(clock))) {
            deleteRefreshToken(refreshToken.getToken());
            throw new UserException("Refresh token was expired. Please make a new signin request");
        }
        UserDto user = userMapper.apply(refreshToken.getUser());
        if (!user.getId().equals(userDto.getId())) {
            throw new UserException("You will need to login again");
        }
        return refreshToken;
    }

    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}