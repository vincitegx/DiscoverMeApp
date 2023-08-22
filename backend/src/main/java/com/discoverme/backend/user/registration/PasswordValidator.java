package com.discoverme.backend.user.registration;

import org.springframework.stereotype.Service;

@Service
public class PasswordValidator {
    public boolean test(String psw) {
        return !psw.isEmpty() && psw.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }
}
