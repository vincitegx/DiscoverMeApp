package com.discoverme.backend.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserMapper.class})
@ExtendWith(SpringExtension.class)
class UserMapperDiffblueTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * Method under test: {@link UserMapper#apply(Users)}
     */
    @Test
    void testApply() {
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

        // Act
        UserDto actualApplyResult = userMapper.apply(user);

        // Assert
        assertEquals("Role", actualApplyResult.getRole());
        assertEquals("User Name", actualApplyResult.getUserName());
        assertEquals("jane.doe@example.org", actualApplyResult.getEmail());
        assertEquals(1L, actualApplyResult.getId().longValue());
    }

    /**
     * Method under test: {@link UserMapper#apply(Users)}
     */
    @Test
    void testApply2() {
        // Arrange
        Users user = mock(Users.class);
        when(user.getId()).thenReturn(1L);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getRole()).thenReturn("Role");
        when(user.getUserName()).thenReturn("User Name");
        doNothing().when(user).setCreatedAt(Mockito.<ZonedDateTime>any());
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setEnabled(Mockito.<Boolean>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNonLocked(Mockito.<Boolean>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setUserName(Mockito.<String>any());
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");

        // Act
        UserDto actualApplyResult = userMapper.apply(user);

        // Assert
        verify(user).getEmail();
        verify(user).getId();
        verify(user).getRole();
        verify(user).getUserName();
        verify(user).setCreatedAt(isA(ZonedDateTime.class));
        verify(user).setEmail(eq("jane.doe@example.org"));
        verify(user).setEnabled(isA(Boolean.class));
        verify(user).setId(isA(Long.class));
        verify(user).setNonLocked(isA(Boolean.class));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(eq("Role"));
        verify(user).setUserName(eq("User Name"));
        assertEquals("Role", actualApplyResult.getRole());
        assertEquals("User Name", actualApplyResult.getUserName());
        assertEquals("jane.doe@example.org", actualApplyResult.getEmail());
        assertEquals(1L, actualApplyResult.getId().longValue());
    }
}
