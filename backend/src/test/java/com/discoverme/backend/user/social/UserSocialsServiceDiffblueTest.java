package com.discoverme.backend.user.social;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discoverme.backend.user.UserMapper;
import com.discoverme.backend.user.UserService;
import com.discoverme.backend.user.Users;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserSocialsService.class})
@ExtendWith(SpringExtension.class)
class UserSocialsServiceDiffblueTest {
    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private UserSocialsRepository userSocialsRepository;

    @Autowired
    private UserSocialsService userSocialsService;

    /**
     * Method under test: {@link UserSocialsService#findAllUserSocials()}
     */
    @Test
    void testFindAllUserSocials() {
        // Arrange
        when(userSocialsRepository.findByUser(Mockito.<Users>any())).thenReturn(new ArrayList<>());

        Users users = new Users();
        users.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        users.setEmail("jane.doe@example.org");
        users.setEnabled(true);
        users.setId(1L);
        users.setNonLocked(true);
        users.setPassword("iloveyou");
        users.setRole("Role");
        users.setStageName("Stage Name");
        when(userService.getCurrentUser()).thenReturn(users);

        // Act
        List<UserSocialsDto> actualFindAllUserSocialsResult = userSocialsService.findAllUserSocials();

        // Assert
        verify(userService).getCurrentUser();
        verify(userSocialsRepository).findByUser(isA(Users.class));
        assertTrue(actualFindAllUserSocialsResult.isEmpty());
    }
}
