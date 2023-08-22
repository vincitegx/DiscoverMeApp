package com.discoverme.backend.user.login;

import com.discoverme.backend.user.registration.RegistrationController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(RegistrationController.class);
    private final LoginService loginService;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> userLogin(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse response = loginService.login(loginRequest);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, response.getAuthToken())
                .body(response);
    }
}
