package com.discoverme.backend.user.login;

import com.discoverme.backend.security.JWTUtil;
import com.discoverme.backend.security.UserDetailsImpl;
import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final UserMapper userDtoMapper;
    private final JWTUtil jwtUtil;

    public JwtResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        UserDto userDto = userDtoMapper.apply(principal.getUser());
        String token = jwtUtil.issueToken(userDto.getEmail(), userDto.getRole());
        return new JwtResponse(token,userDto);
    }
}
