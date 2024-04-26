package com.discoverme.backend.user.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.discoverme.backend.user.UserDto;
import org.junit.jupiter.api.Test;

class JwtResponseDiffblueTest {
    /**
     * Method under test: {@link JwtResponse#JwtResponse(String, UserDto)}
     */
    @Test
    void testNewJwtResponse() {
        // Arrange
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").UserName("User Name").build();

        // Act
        JwtResponse actualJwtResponse = new JwtResponse("ABC123", user);

        // Assert
        assertEquals("ABC123", actualJwtResponse.getAuthToken());
        UserDto user2 = actualJwtResponse.getUser();
        assertEquals("Role", user2.getRole());
        assertEquals("User Name", user2.getUserName());
        assertEquals("jane.doe@example.org", user2.getEmail());
        assertEquals(1L, user2.getId().longValue());
        assertSame(user, user2);
    }
}
