package com.discoverme.backend.user.registration;

import com.discoverme.backend.mail.EventDto;
import com.discoverme.backend.mail.MailService;
import com.discoverme.backend.user.verification.VerificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final VerificationService verificationService;
    private final MailService mailService;

    @PostMapping("register/user")
    public ResponseEntity<RegistrationResponse> registerUser(@Valid @RequestBody RegistrationRequest registerRequest) {
        RegistrationResponse response = registrationService.registerUser(registerRequest);
        EventDto eventDto = verificationService.registerVerificationTokenToDb(response);
        mailService.sendHtmlEmail(eventDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("register/admin")
    @Secured({"ROLE_SUPER-ADMIN"})
    public ResponseEntity<RegistrationResponse> registerAdmin(@Valid @RequestBody AdminRegistrationRequest registerRequest) {
        RegistrationResponse response = registrationService.registerAdmin(registerRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
