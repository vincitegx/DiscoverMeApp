package com.discoverme.backend.verification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationService verificationService;
    @PutMapping("users/verify")
    public ResponseEntity<String> verifyUserAccount(@RequestParam String id){
        Long userId = Long.parseLong(id);
        String response = verificationService.fetchAndEnableUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
