package com.discoverme.backend.user.registration;

import org.springframework.stereotype.Service;

@Service
public class PhoneNumberValidator {
    public boolean test(String phoneNumber) {
        return !phoneNumber.isEmpty();
    }
}
