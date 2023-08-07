package com.discoverme.backend.registration;

import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final PhoneNumberValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final UserService userService;
    private final RegistrationMapper registrationMapper;
    private final PasswordEncoder passwordEncoder;
    public RegistrationResponse registerUser(RegistrationRequest registerRequest) {
        if (!emailValidator.test(registerRequest.getPhoneNumber())) {
            throw new RegistrationException(registerRequest.getPhoneNumber() + " is not valid");
        } else if(!passwordValidator.test(registerRequest.getPassword())){
            throw new RegistrationException("Password must be a minimum of eight characters contain at least one uppercase letter, one lowercase letter, one number and one special character");
        } else if (userService.findUserByPhoneNumber(registerRequest.getPhoneNumber()) != null) {
            throw new RegistrationException("Phone number is already registered");
        } else {
            registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            Users user = registrationMapper.mapRegistrationRequestToUser(registerRequest);
            user = userService.saveUser(user);
            return registrationMapper.mapUserToRegistrationRequest(user);
        }
    }
}
