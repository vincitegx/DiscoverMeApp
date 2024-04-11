package com.discoverme.backend.security;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discoverme.backend.user.UserRepository;
import com.discoverme.backend.user.Users;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomOAuth2UserService.class})
@ExtendWith(SpringExtension.class)
class CustomOAuth2UserServiceDiffblueTest {
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test:
     * {@link CustomOAuth2UserService#upsertUser(GoogleIdToken.Payload)}
     */
    @Test
    void testUpsertUser() {
        // Arrange
        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setStageName("Stage Name");
        Optional<Users> ofResult = Optional.of(users);

        Users users2 = new Users();
        users2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users2.setEmail("jane.doe@example.org");
        users2.setEnabled(true);
        users2.setId(1L);
        users2.setNonLocked(true);
        users2.setPassword("iloveyou");
        users2.setRole("Role");
        users2.setStageName("Stage Name");
        when(userRepository.saveAndFlush(Mockito.<Users>any())).thenReturn(users2);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Users actualUpsertUserResult = customOAuth2UserService.upsertUser(new GoogleIdToken.Payload());

        // Assert
        verify(userRepository).findByEmail(isNull());
        verify(userRepository).saveAndFlush(isA(Users.class));
        assertSame(users2, actualUpsertUserResult);
    }

    /**
     * Method under test:
     * {@link CustomOAuth2UserService#upsertUser(GoogleIdToken.Payload)}
     */
    @Test
    void testUpsertUser2() {
        // Arrange
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setStageName("Stage Name");
        when(userRepository.saveAndFlush(Mockito.<Users>any())).thenReturn(users);
        Optional<Users> emptyResult = Optional.empty();
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);

        GoogleIdToken.Payload payload = new GoogleIdToken.Payload();
        payload.put("name", "Value");

        // Act
        Users actualUpsertUserResult = customOAuth2UserService.upsertUser(payload);

        // Assert
        verify(userRepository).findByEmail(isNull());
        verify(userRepository).saveAndFlush(isA(Users.class));
        verify(passwordEncoder).encode(isA(CharSequence.class));
        assertSame(users, actualUpsertUserResult);
    }
}
