package com.discoverme.backend.registration;

import com.discoverme.backend.project.SocialPlatform;
import com.discoverme.backend.project.SocialsRepository;
import com.discoverme.backend.project.Socials;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.UserSocials;
import com.discoverme.backend.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final PhoneNumberValidator phoneNumberValidator;
    private final PasswordValidator passwordValidator;
    private final UserService userService;
    private final RegistrationMapper registrationMapper;
    private final PasswordEncoder passwordEncoder;
    private final SocialsRepository socialRepository;
    public RegistrationResponse registerUser(RegistrationRequest registerRequest) {
        if (!phoneNumberValidator.test(registerRequest.getPhoneNumber())) {
            throw new RegistrationException(registerRequest.getPhoneNumber() + " is not valid");
        } else if(!passwordValidator.test(registerRequest.getPassword())){
            throw new RegistrationException("Password must be a minimum of eight characters contain at least one uppercase letter, one lowercase letter, one number and one special character");
        } else if (userService.findUserByPhoneNumber(registerRequest.getPhoneNumber()).isPresent()) {
            throw new RegistrationException("Phone number is already registered");
        } else {
            registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            Users user = registrationMapper.mapRegistrationRequestToUser(registerRequest);
            user = addUserSocials(user, registerRequest);
            user = userService.saveUser(user);
            return registrationMapper.mapUserToRegistrationRequest(user);
        }
    }

    private Users addUserSocials(Users user, RegistrationRequest registrationRequest) {
        for (SocialPlatform platform : SocialPlatform.values()) {
            String uri = getUriForPlatform(platform, registrationRequest);
            if (uri != null) {
                Socials social = socialRepository.findByName(platform.name());
                if (social != null) {
                    UserSocials userSocial = UserSocials.builder()
                            .user(user)
                            .social(social)
                            .uri(uri)
                            .build();
                    user.getUserSocials().add(userSocial);
                }
            }
        }
        return user;
    }

    private String getUriForPlatform(SocialPlatform platform, RegistrationRequest registrationRequest) {
        return switch (platform) {
            case FACEBOOK -> registrationRequest.getFacebookUri();
            case X -> registrationRequest.getXUri();
            case INSTAGRAM -> registrationRequest.getInstagramUri();
            case TIKTOK -> registrationRequest.getTiktokUri();
            case YOUTUBE -> registrationRequest.getYoutubeUri();
            default -> null;
        };
    }

    public RegistrationResponse registerAdmin(AdminRegistrationRequest registerRequest) {
        if (!phoneNumberValidator.test(registerRequest.getPhoneNumber())) {
            throw new RegistrationException(registerRequest.getPhoneNumber() + " is not valid");
        } else if(!passwordValidator.test(registerRequest.getPassword())){
            throw new RegistrationException("Password must be a minimum of eight characters contain at least one uppercase letter, one lowercase letter, one number and one special character");
        } else if (userService.findUserByPhoneNumber(registerRequest.getPhoneNumber()).isPresent()) {
            throw new RegistrationException("Phone number is already registered");
        } else {
            registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            Users user = registrationMapper.mapRegistrationRequestToAdmin(registerRequest);
            user = userService.saveUser(user);
            return registrationMapper.mapUserToRegistrationRequest(user);
        }
    }
}
