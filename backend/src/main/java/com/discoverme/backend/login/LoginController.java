package com.discoverme.backend.login;

import com.discoverme.backend.registration.RegistrationController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(RegistrationController.class);
    private final LoginService loginService;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> userLogin(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse response = loginService.login(loginRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PostMapping("refresh-token")
//    public ResponseEntity<JwtResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
//        return refreshTokenService.refreshToken(refreshTokenRequest);
//    }
}
