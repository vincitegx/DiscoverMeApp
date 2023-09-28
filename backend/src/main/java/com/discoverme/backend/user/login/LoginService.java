package com.discoverme.backend.user.login;

import com.discoverme.backend.security.JWTUtil;
import com.discoverme.backend.security.UserDetailsImpl;
import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.UserMapper;
import com.discoverme.backend.user.login.refresh.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final UserMapper userDtoMapper;
    private final JWTUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public JwtResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPhoneNumber(),
                        request.getPassword()
                )
        );

        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        UserDto userDto = userDtoMapper.apply(principal.getUser());
        String token = jwtUtil.issueToken(userDto.getPhoneNumber(), userDto.getRole());
        String refreshToken = refreshTokenService.generateRefreshToken(principal.getUser());
        return new JwtResponse(token,refreshToken, userDto);
    }
}
