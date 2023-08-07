package com.discoverme.backend.registration;

import org.springframework.stereotype.Service;

@Service
public class PhoneNumberValidator {
    public boolean test(String email) {
        return !email.isEmpty() && email.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");
    }
}
