package com.discoverme.backend.user.verification;

import com.discoverme.backend.user.UserException;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;
import com.discoverme.backend.mail.EventDto;
import com.discoverme.backend.user.registration.RegistrationResponse;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author TEGA
 */
@Service
@Transactional
@RequiredArgsConstructor
public class VerificationService {
    
    @Value("${activation.token.expiration.time.hours}")
    private Long activationTokenExpirationTimeInHours;

    @Value("${organization.properties.mail}")
    private String organizationEmail;
    private final EmailVerificationTokenRepository emailVerificationTokenRepository;
    private final UserService userService;
    private final Clock clock = Clock.systemUTC();
    
    public EventDto registerVerificationTokenToDb(RegistrationResponse registrationResponse) {
        String generatedToken = UUID.randomUUID().toString();
        Users user = userService.findById(registrationResponse.getUserId()).orElseThrow(()-> new UsernameNotFoundException("No such user found"));
        emailVerificationTokenRepository.findByUser(user).ifPresent(emailVerificationTokenRepository::delete);
        EmailVerificationToken verificationToken = new EmailVerificationToken(generatedToken, user, LocalDateTime.now().plusHours(activationTokenExpirationTimeInHours));
        emailVerificationTokenRepository.save(verificationToken);
        Map<String, String> data = new HashMap<>();
        data.put("subject", "Email verification");
        data.put("name", user.getUserName());
        data.put("token", generatedToken);
        data.put("expiresAt", activationTokenExpirationTimeInHours.toString());
        return EventDto.builder().from(organizationEmail).to(registrationResponse.getEmail()).data(data).build();
    }

    public void requestNewVerificationToken(String email) {
        Users user = userService.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("This email has not been registered. Visit the registration page to register an account..."));
        RegistrationResponse response = new RegistrationResponse(user.getId(), user.getUserName(), user.getEmail());
        registerVerificationTokenToDb(response);
    }

    public Users verifyEmail(String token) {
        EmailVerificationToken verificationToken = emailVerificationTokenRepository.findByToken(token).orElseThrow(() -> new UserException("Token does not exist !!!"));
        if (verificationToken.getExpiresAt().isBefore(LocalDateTime.now(clock))) {
            throw new UserException("Token Expired !!!");
        } else {
            Users user = verificationToken.getUser();
            user.setEnabled(true);
            user.setNonLocked(true);
            user = userService.saveUser(user);
            emailVerificationTokenRepository.delete(verificationToken);
            return user;
        }
    }
}
