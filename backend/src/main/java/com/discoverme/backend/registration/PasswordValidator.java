package com.discoverme.backend.registration;

import org.springframework.stereotype.Service;

@Service
public class PasswordValidator {
    public boolean test(String psw) {
        return !psw.isEmpty() && psw.length() == 13;
    }
}
