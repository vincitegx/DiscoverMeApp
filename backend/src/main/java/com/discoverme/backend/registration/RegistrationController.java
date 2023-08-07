package com.discoverme.backend.registration;

import io.swagger.v3.oas.annotations.Hidden;
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
public class RegistrationController {

    private final Logger log = LoggerFactory.getLogger(RegistrationController.class);
    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<RegistrationResponse> registerUser(@Valid @RequestBody RegistrationRequest registerRequest) {
        RegistrationResponse response = registrationService.registerUser(registerRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("token/validate")
    @Hidden
    @ResponseStatus(HttpStatus.OK)
    private UserDto validateToken(@RequestParam String token) {
        log.info("Trying to validate token {}", token);
        return authService.validateToken(token);
    }
    @PostMapping("forget-password/generate-token")
    @ResponseStatus(HttpStatus.CREATED)
    public void generateToken(@Valid @RequestBody EmailRequest emailRequest) {
        passwordService.generatePasswordResetToken(emailRequest);
    }

    @PostMapping("forget-password/reset-password")
    public void resetPassword(@Valid @RequestBody PasswordResetRequest passwordResetRequest) {
        passwordService.resetAccountPassword(passwordResetRequest);
    }
}
