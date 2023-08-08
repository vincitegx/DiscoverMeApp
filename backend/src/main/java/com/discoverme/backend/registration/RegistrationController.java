package com.discoverme.backend.registration;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final Logger log = LoggerFactory.getLogger(RegistrationController.class);
    private final RegistrationService registrationService;

    @PostMapping("register")
    public ResponseEntity<RegistrationResponse> registerUser(@Valid @RequestBody RegistrationRequest registerRequest) {
        RegistrationResponse response = registrationService.registerUser(registerRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
