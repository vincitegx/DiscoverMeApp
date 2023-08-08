package com.discoverme.backend.verification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationService verificationService;
    @PutMapping("user/{id}/verify")
    public ResponseEntity<String> verifyUserAccount(@RequestParam String id){
        Long userId = Long.parseLong(id);
        String response = verificationService.fetchAndEnableUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
