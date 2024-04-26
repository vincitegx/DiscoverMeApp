package com.discoverme.backend.user.login.refresh;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discoverme.backend.security.JWTUtil;
import com.discoverme.backend.user.UserDto;
import com.discoverme.backend.user.UserException;
import com.discoverme.backend.user.UserMapper;
import com.discoverme.backend.user.Users;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import javax.security.auth.RefreshFailedException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RefreshTokenService.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class RefreshTokenServiceDiffblueTest {
    @MockBean
    private JWTUtil jWTUtil;

    @MockBean
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @MockBean
    private UserMapper userMapper;

    /**
     * Method under test: {@link RefreshTokenService#generateRefreshToken(Users)}
     */
    @Test
    void testGenerateRefreshToken() {
        // Arrange
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setId(1L);
        refreshToken.setToken("ABC123");
        refreshToken.setUser(user);
        when(refreshTokenRepository.save(Mockito.<RefreshToken>any())).thenReturn(refreshToken);

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserName("User Name");

        // Act
        String actualGenerateRefreshTokenResult = refreshTokenService.generateRefreshToken(user2);

        // Assert
        verify(refreshTokenRepository).save(isA(RefreshToken.class));
        assertEquals("ABC123", actualGenerateRefreshTokenResult);
    }

    /**
     * Method under test: {@link RefreshTokenService#generateRefreshToken(Users)}
     */
    @Test
    void testGenerateRefreshToken2() {
        // Arrange
        when(refreshTokenRepository.save(Mockito.<RefreshToken>any())).thenThrow(new UserException("An error occurred"));

        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");

        // Act and Assert
        assertThrows(UserException.class, () -> refreshTokenService.generateRefreshToken(user));
        verify(refreshTokenRepository).save(isA(RefreshToken.class));
    }

    /**
     * Method under test: {@link RefreshTokenService#refreshToken(UserDto, String)}
     */
    @Test
    void testRefreshToken() throws RefreshFailedException {
        // Arrange
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setId(1L);
        refreshToken.setToken("ABC123");
        refreshToken.setUser(user);
        Optional<RefreshToken> ofResult = Optional.of(refreshToken);
        doNothing().when(refreshTokenRepository).deleteByToken(Mockito.<String>any());
        when(refreshTokenRepository.findByToken(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RefreshFailedException.class, () -> refreshTokenService.refreshToken(null, "ABC123"));
        verify(refreshTokenRepository).deleteByToken(eq("ABC123"));
        verify(refreshTokenRepository).findByToken(eq("ABC123"));
    }

    /**
     * Method under test: {@link RefreshTokenService#refreshToken(UserDto, String)}
     */
    @Test
    void testRefreshToken2() throws RefreshFailedException {
        // Arrange
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setId(1L);
        refreshToken.setToken("ABC123");
        refreshToken.setUser(user);
        Optional<RefreshToken> ofResult = Optional.of(refreshToken);
        doThrow(new UserException("An error occurred")).when(refreshTokenRepository).deleteByToken(Mockito.<String>any());
        when(refreshTokenRepository.findByToken(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UserException.class, () -> refreshTokenService.refreshToken(null, "ABC123"));
        verify(refreshTokenRepository).deleteByToken(eq("ABC123"));
        verify(refreshTokenRepository).findByToken(eq("ABC123"));
    }

    /**
     * Method under test: {@link RefreshTokenService#refreshToken(UserDto, String)}
     */
    @Test
    void testRefreshToken3() throws RefreshFailedException {
        // Arrange
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");
        RefreshToken refreshToken = mock(RefreshToken.class);
        when(refreshToken.getToken()).thenThrow(new UserException("An error occurred"));
        when(refreshToken.getExpiresAt()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        doNothing().when(refreshToken).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(refreshToken).setExpiresAt(Mockito.<LocalDateTime>any());
        doNothing().when(refreshToken).setId(Mockito.<Long>any());
        doNothing().when(refreshToken).setToken(Mockito.<String>any());
        doNothing().when(refreshToken).setUser(Mockito.<Users>any());
        refreshToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setId(1L);
        refreshToken.setToken("ABC123");
        refreshToken.setUser(user);
        Optional<RefreshToken> ofResult = Optional.of(refreshToken);
        when(refreshTokenRepository.findByToken(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UserException.class, () -> refreshTokenService.refreshToken(null, "ABC123"));
        verify(refreshToken).getExpiresAt();
        verify(refreshToken).getToken();
        verify(refreshToken).setCreatedAt(isA(LocalDateTime.class));
        verify(refreshToken).setExpiresAt(isA(LocalDateTime.class));
        verify(refreshToken).setId(isA(Long.class));
        verify(refreshToken).setToken(eq("ABC123"));
        verify(refreshToken).setUser(isA(Users.class));
        verify(refreshTokenRepository).findByToken(eq("ABC123"));
    }

    /**
     * Method under test: {@link RefreshTokenService#refreshToken(UserDto, String)}
     */
    @Test
    void testRefreshToken4() throws RefreshFailedException {
        // Arrange
        Optional<RefreshToken> emptyResult = Optional.empty();
        when(refreshTokenRepository.findByToken(Mockito.<String>any())).thenReturn(emptyResult);
        new UserException("An error occurred");

        // Act and Assert
        assertThrows(UserException.class, () -> refreshTokenService.refreshToken(null, "ABC123"));
        verify(refreshTokenRepository).findByToken(eq("ABC123"));
    }

    /**
     * Method under test:
     * {@link RefreshTokenService#validateRefreshToken(UserDto, String)}
     */
    @Test
    void testValidateRefreshToken() throws RefreshFailedException {
        // Arrange
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setId(1L);
        refreshToken.setToken("ABC123");
        refreshToken.setUser(user);
        Optional<RefreshToken> ofResult = Optional.of(refreshToken);
        doNothing().when(refreshTokenRepository).deleteByToken(Mockito.<String>any());
        when(refreshTokenRepository.findByToken(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RefreshFailedException.class, () -> refreshTokenService.validateRefreshToken(null, "ABC123"));
        verify(refreshTokenRepository).deleteByToken(eq("ABC123"));
        verify(refreshTokenRepository).findByToken(eq("ABC123"));
    }

    /**
     * Method under test:
     * {@link RefreshTokenService#validateRefreshToken(UserDto, String)}
     */
    @Test
    void testValidateRefreshToken2() throws RefreshFailedException {
        // Arrange
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setId(1L);
        refreshToken.setToken("ABC123");
        refreshToken.setUser(user);
        Optional<RefreshToken> ofResult = Optional.of(refreshToken);
        doThrow(new UserException("An error occurred")).when(refreshTokenRepository).deleteByToken(Mockito.<String>any());
        when(refreshTokenRepository.findByToken(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UserException.class, () -> refreshTokenService.validateRefreshToken(null, "ABC123"));
        verify(refreshTokenRepository).deleteByToken(eq("ABC123"));
        verify(refreshTokenRepository).findByToken(eq("ABC123"));
    }

    /**
     * Method under test:
     * {@link RefreshTokenService#validateRefreshToken(UserDto, String)}
     */
    @Test
    void testValidateRefreshToken3() throws RefreshFailedException {
        // Arrange
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");
        RefreshToken refreshToken = mock(RefreshToken.class);
        when(refreshToken.getToken()).thenThrow(new UserException("An error occurred"));
        when(refreshToken.getExpiresAt()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        doNothing().when(refreshToken).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(refreshToken).setExpiresAt(Mockito.<LocalDateTime>any());
        doNothing().when(refreshToken).setId(Mockito.<Long>any());
        doNothing().when(refreshToken).setToken(Mockito.<String>any());
        doNothing().when(refreshToken).setUser(Mockito.<Users>any());
        refreshToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setId(1L);
        refreshToken.setToken("ABC123");
        refreshToken.setUser(user);
        Optional<RefreshToken> ofResult = Optional.of(refreshToken);
        when(refreshTokenRepository.findByToken(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UserException.class, () -> refreshTokenService.validateRefreshToken(null, "ABC123"));
        verify(refreshToken).getExpiresAt();
        verify(refreshToken).getToken();
        verify(refreshToken).setCreatedAt(isA(LocalDateTime.class));
        verify(refreshToken).setExpiresAt(isA(LocalDateTime.class));
        verify(refreshToken).setId(isA(Long.class));
        verify(refreshToken).setToken(eq("ABC123"));
        verify(refreshToken).setUser(isA(Users.class));
        verify(refreshTokenRepository).findByToken(eq("ABC123"));
    }

    /**
     * Method under test:
     * {@link RefreshTokenService#validateRefreshToken(UserDto, String)}
     */
    @Test
    void testValidateRefreshToken4() throws RefreshFailedException {
        // Arrange
        Optional<RefreshToken> emptyResult = Optional.empty();
        when(refreshTokenRepository.findByToken(Mockito.<String>any())).thenReturn(emptyResult);
        new UserException("An error occurred");

        // Act and Assert
        assertThrows(UserException.class, () -> refreshTokenService.validateRefreshToken(null, "ABC123"));
        verify(refreshTokenRepository).findByToken(eq("ABC123"));
    }

    /**
     * Method under test: {@link RefreshTokenService#existByToken(String)}
     */
    @Test
    void testExistByToken() {
        // Arrange
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setId(1L);
        refreshToken.setToken("ABC123");
        refreshToken.setUser(user);
        Optional<RefreshToken> ofResult = Optional.of(refreshToken);
        when(refreshTokenRepository.findByToken(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        boolean actualExistByTokenResult = refreshTokenService.existByToken("ABC123");

        // Assert
        verify(refreshTokenRepository).findByToken(eq("ABC123"));
        assertTrue(actualExistByTokenResult);
    }

    /**
     * Method under test: {@link RefreshTokenService#existByToken(String)}
     */
    @Test
    void testExistByToken2() {
        // Arrange
        Optional<RefreshToken> emptyResult = Optional.empty();
        when(refreshTokenRepository.findByToken(Mockito.<String>any())).thenReturn(emptyResult);

        // Act
        boolean actualExistByTokenResult = refreshTokenService.existByToken("ABC123");

        // Assert
        verify(refreshTokenRepository).findByToken(eq("ABC123"));
        assertFalse(actualExistByTokenResult);
    }

    /**
     * Method under test: {@link RefreshTokenService#existByToken(String)}
     */
    @Test
    void testExistByToken3() {
        // Arrange
        when(refreshTokenRepository.findByToken(Mockito.<String>any())).thenThrow(new UserException("An error occurred"));

        // Act and Assert
        assertThrows(UserException.class, () -> refreshTokenService.existByToken("ABC123"));
        verify(refreshTokenRepository).findByToken(eq("ABC123"));
    }

    /**
     * Method under test: {@link RefreshTokenService#deleteRefreshToken(String)}
     */
    @Test
    void testDeleteRefreshToken() {
        // Arrange
        doNothing().when(refreshTokenRepository).deleteByToken(Mockito.<String>any());

        // Act
        refreshTokenService.deleteRefreshToken("ABC123");

        // Assert that nothing has changed
        verify(refreshTokenRepository).deleteByToken(eq("ABC123"));
    }

    /**
     * Method under test: {@link RefreshTokenService#deleteRefreshToken(String)}
     */
    @Test
    void testDeleteRefreshToken2() {
        // Arrange
        doThrow(new UserException("An error occurred")).when(refreshTokenRepository).deleteByToken(Mockito.<String>any());

        // Act and Assert
        assertThrows(UserException.class, () -> refreshTokenService.deleteRefreshToken("ABC123"));
        verify(refreshTokenRepository).deleteByToken(eq("ABC123"));
    }
}
