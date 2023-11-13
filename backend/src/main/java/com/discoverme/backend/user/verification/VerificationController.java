package com.discoverme.backend.user.verification;

import com.discoverme.backend.user.Users;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TEGA
 */
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class VerificationController {
    
    private final VerificationService verificationService;
    
    @GetMapping("verify")
    public ResponseEntity<Boolean> verifyUserAccount(@RequestParam("token") @NonNull String token){
        Users user = verificationService.verifyEmail(token);
        return new ResponseEntity<>(user!=null, HttpStatus.OK);
    }
    
}
