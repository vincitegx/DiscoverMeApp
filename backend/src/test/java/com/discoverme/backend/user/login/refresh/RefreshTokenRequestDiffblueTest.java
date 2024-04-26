package com.discoverme.backend.user.login.refresh;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discoverme.backend.user.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RefreshTokenRequest.class})
@ExtendWith(SpringExtension.class)
class RefreshTokenRequestDiffblueTest {
    @Autowired
    private RefreshTokenRequest refreshTokenRequest;

    /**
     * Method under test: {@link RefreshTokenRequest#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse(refreshTokenRequest.canEqual("Other"));
        assertTrue(refreshTokenRequest.canEqual(refreshTokenRequest));
    }

    /**
     * Method under test: {@link RefreshTokenRequest#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        UserDto.UserDtoBuilder userDtoBuilder = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder.email(Mockito.<String>any())).thenReturn(UserDto.builder());
        UserDto user = userDtoBuilder.email("jane.doe@example.org").id(1L).role("Role").UserName("User Name").build();

        // Act
        boolean actualCanEqualResult = refreshTokenRequest.canEqual(new RefreshTokenRequest("ABC123", user));

        // Assert
        verify(userDtoBuilder).email(eq("jane.doe@example.org"));
        assertTrue(actualCanEqualResult);
    }

    /**
     * Method under test: {@link RefreshTokenRequest#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange, Act and Assert
        assertNotEquals(new RefreshTokenRequest(), null);
        assertNotEquals(new RefreshTokenRequest(), "Different type to RefreshTokenRequest");
    }

    /**
     * Method under test: {@link RefreshTokenRequest#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").UserName("User Name").build();
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest("ABC123", user);

        // Act and Assert
        assertNotEquals(refreshTokenRequest, new RefreshTokenRequest());
    }

    /**
     * Method under test: {@link RefreshTokenRequest#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").UserName("User Name").build();

        // Act and Assert
        assertNotEquals(refreshTokenRequest, new RefreshTokenRequest("ABC123", user));
    }

    /**
     * Method under test: {@link RefreshTokenRequest#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").UserName("User Name").build();
        refreshTokenRequest.setUser(user);

        // Act and Assert
        assertNotEquals(refreshTokenRequest, new RefreshTokenRequest());
    }

    /**
     * Method under test: {@link RefreshTokenRequest#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        UserDto.UserDtoBuilder userDtoBuilder = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder.email(Mockito.<String>any())).thenReturn(UserDto.builder());
        UserDto user = userDtoBuilder.email("jane.doe@example.org").id(1L).role("Role").UserName("User Name").build();
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest("ABC123", user);

        // Act and Assert
        assertNotEquals(refreshTokenRequest, new RefreshTokenRequest());
    }

    /**
     * Method under test: {@link RefreshTokenRequest#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        UserDto.UserDtoBuilder userDtoBuilder = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder.email(Mockito.<String>any())).thenReturn(UserDto.builder());
        UserDto user = userDtoBuilder.email("jane.doe@example.org").id(1L).role("Role").UserName("User Name").build();
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest("ABC123", user);
        UserDto user2 = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").UserName("User Name").build();

        // Act and Assert
        assertNotEquals(refreshTokenRequest, new RefreshTokenRequest("ABC123", user2));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link RefreshTokenRequest#equals(Object)}
     *   <li>{@link RefreshTokenRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();

        // Act and Assert
        assertEquals(refreshTokenRequest, refreshTokenRequest);
        int expectedHashCodeResult = refreshTokenRequest.hashCode();
        assertEquals(expectedHashCodeResult, refreshTokenRequest.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link RefreshTokenRequest#equals(Object)}
     *   <li>{@link RefreshTokenRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        RefreshTokenRequest refreshTokenRequest2 = new RefreshTokenRequest();

        // Act and Assert
        assertEquals(refreshTokenRequest, refreshTokenRequest2);
        int expectedHashCodeResult = refreshTokenRequest.hashCode();
        assertEquals(expectedHashCodeResult, refreshTokenRequest2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link RefreshTokenRequest#equals(Object)}
     *   <li>{@link RefreshTokenRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode3() {
        // Arrange
        UserDto.UserDtoBuilder userDtoBuilder = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder.email(Mockito.<String>any())).thenReturn(UserDto.builder());
        UserDto user = userDtoBuilder.email("jane.doe@example.org").id(1L).role("Role").UserName("User Name").build();
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest("ABC123", user);
        UserDto.UserDtoBuilder userDtoBuilder2 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder2.email(Mockito.<String>any())).thenReturn(UserDto.builder());
        UserDto user2 = userDtoBuilder2.email("jane.doe@example.org").id(1L).role("Role").UserName("User Name").build();
        RefreshTokenRequest refreshTokenRequest2 = new RefreshTokenRequest("ABC123", user2);

        // Act and Assert
        assertEquals(refreshTokenRequest, refreshTokenRequest2);
        int expectedHashCodeResult = refreshTokenRequest.hashCode();
        assertEquals(expectedHashCodeResult, refreshTokenRequest2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link RefreshTokenRequest#RefreshTokenRequest()}
     *   <li>{@link RefreshTokenRequest#setRefreshToken(String)}
     *   <li>{@link RefreshTokenRequest#setUser(UserDto)}
     *   <li>{@link RefreshTokenRequest#toString()}
     *   <li>{@link RefreshTokenRequest#getRefreshToken()}
     *   <li>{@link RefreshTokenRequest#getUser()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        RefreshTokenRequest actualRefreshTokenRequest = new RefreshTokenRequest();
        actualRefreshTokenRequest.setRefreshToken("ABC123");
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").UserName("User Name").build();
        actualRefreshTokenRequest.setUser(user);
        String actualToStringResult = actualRefreshTokenRequest.toString();
        String actualRefreshToken = actualRefreshTokenRequest.getRefreshToken();

        // Assert that nothing has changed
        assertEquals("ABC123", actualRefreshToken);
        assertEquals(
                "RefreshTokenRequest(refreshToken=ABC123, user=UserDto(id=1, UserName=User Name, email=jane.doe@example.org,"
                        + " role=Role))",
                actualToStringResult);
        assertSame(user, actualRefreshTokenRequest.getUser());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link RefreshTokenRequest#RefreshTokenRequest(String, UserDto)}
     *   <li>{@link RefreshTokenRequest#setRefreshToken(String)}
     *   <li>{@link RefreshTokenRequest#setUser(UserDto)}
     *   <li>{@link RefreshTokenRequest#toString()}
     *   <li>{@link RefreshTokenRequest#getRefreshToken()}
     *   <li>{@link RefreshTokenRequest#getUser()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").UserName("User Name").build();

        // Act
        RefreshTokenRequest actualRefreshTokenRequest = new RefreshTokenRequest("ABC123", user);
        actualRefreshTokenRequest.setRefreshToken("ABC123");
        UserDto user2 = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").UserName("User Name").build();
        actualRefreshTokenRequest.setUser(user2);
        String actualToStringResult = actualRefreshTokenRequest.toString();
        String actualRefreshToken = actualRefreshTokenRequest.getRefreshToken();
        UserDto actualUser = actualRefreshTokenRequest.getUser();

        // Assert that nothing has changed
        assertEquals("ABC123", actualRefreshToken);
        assertEquals(
                "RefreshTokenRequest(refreshToken=ABC123, user=UserDto(id=1, UserName=User Name, email=jane.doe@example.org,"
                        + " role=Role))",
                actualToStringResult);
        assertEquals(user, actualUser);
        assertSame(user2, actualUser);
    }
}
