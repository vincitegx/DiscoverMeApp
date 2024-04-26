package com.discoverme.backend.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserDto.class, String.class})
@ExtendWith(SpringExtension.class)
class UserDtoDiffblueTest {
    @Autowired
    private UserDto userDto;

    /**
     * Method under test: {@link UserDto#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange, Act and Assert
        assertFalse((new UserDto(1L, "User Name", "jane.doe@example.org", "Role")).canEqual("Other"));
    }

    /**
     * Method under test: {@link UserDto#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        UserDto buildResult = UserDto.builder()
                .email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();
        UserDto buildResult2 = UserDto.builder()
                .email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();

        // Act and Assert
        assertTrue(buildResult.canEqual(buildResult2));
    }

    /**
     * Method under test: {@link UserDto#canEqual(Object)}
     */
    @Test
    void testCanEqual3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        UserDto.UserDtoBuilder userDtoBuilder = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder.email(Mockito.<String>any())).thenReturn(UserDto.builder());
        UserDto buildResult = userDtoBuilder.email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();
        UserDto buildResult2 = UserDto.builder()
                .email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();

        // Act
        boolean actualCanEqualResult = buildResult.canEqual(buildResult2);

        // Assert
        verify(userDtoBuilder).email(eq("jane.doe@example.org"));
        assertTrue(actualCanEqualResult);
    }

    /**
     * Method under test: {@link UserDto#canEqual(Object)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCanEqual4() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       java.lang.Long
        //   See https://diff.blue/R027 to resolve this issue.

        // Arrange and Act
        userDto.canEqual("Other");
    }

    /**
     * Method under test: {@link UserDto#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        UserDto buildResult = UserDto.builder()
                .email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, null);
    }

    /**
     * Method under test: {@link UserDto#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        UserDto buildResult = UserDto.builder()
                .email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, "Different type to UserDto");
    }

    /**
     * Method under test: {@link UserDto#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        UserDto.UserDtoBuilder userDtoBuilder = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder.email(Mockito.<String>any())).thenReturn(UserDto.builder());
        UserDto buildResult = userDtoBuilder.email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();
        UserDto buildResult2 = UserDto.builder()
                .email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link UserDto#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        UserDto.UserDtoBuilder userDtoBuilder = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder.id(Mockito.<Long>any())).thenReturn(UserDto.builder());
        UserDto.UserDtoBuilder userDtoBuilder2 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder2.email(Mockito.<String>any())).thenReturn(userDtoBuilder);
        UserDto buildResult = userDtoBuilder2.email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();
        UserDto buildResult2 = UserDto.builder()
                .email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link UserDto#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        UserDto.UserDtoBuilder userDtoBuilder = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder.id(Mockito.<Long>any())).thenReturn(UserDto.builder());
        UserDto.UserDtoBuilder userDtoBuilder2 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder2.email(Mockito.<String>any())).thenReturn(userDtoBuilder);
        UserDto buildResult = userDtoBuilder2.email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();
        UserDto buildResult2 = UserDto.builder()
                .email("jane.doe@example.org")
                .id(null)
                .role("Role")
                .UserName("User Name")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link UserDto#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        UserDto.UserDtoBuilder userDtoBuilder = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder.id(Mockito.<Long>any())).thenReturn(UserDto.builder());
        UserDto.UserDtoBuilder userDtoBuilder2 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder2.email(Mockito.<String>any())).thenReturn(userDtoBuilder);
        UserDto buildResult = userDtoBuilder2.email("jane.doe@example.org").id(1L).role("Role").UserName(null).build();
        UserDto buildResult2 = UserDto.builder()
                .email("jane.doe@example.org")
                .id(null)
                .role("Role")
                .UserName("User Name")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link UserDto#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        UserDto.UserDtoBuilder userDtoBuilder = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder.id(Mockito.<Long>any())).thenReturn(UserDto.builder());
        UserDto.UserDtoBuilder userDtoBuilder2 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder2.email(Mockito.<String>any())).thenReturn(userDtoBuilder);
        UserDto buildResult = userDtoBuilder2.email("jane.doe@example.org").id(1L).role("Role").UserName("42").build();
        UserDto buildResult2 = UserDto.builder()
                .email("jane.doe@example.org")
                .id(null)
                .role("Role")
                .UserName("User Name")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link UserDto#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        UserDto.UserDtoBuilder userDtoBuilder = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder.role(Mockito.<String>any())).thenReturn(UserDto.builder());
        UserDto.UserDtoBuilder userDtoBuilder2 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder2.id(Mockito.<Long>any())).thenReturn(userDtoBuilder);
        UserDto.UserDtoBuilder userDtoBuilder3 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder3.email(Mockito.<String>any())).thenReturn(userDtoBuilder2);
        UserDto buildResult = userDtoBuilder3.email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();
        UserDto.UserDtoBuilder userDtoBuilder4 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder4.email(Mockito.<String>any())).thenReturn(UserDto.builder());
        UserDto buildResult2 = userDtoBuilder4.email("jane.doe@example.org")
                .id(null)
                .role("Role")
                .UserName("User Name")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link UserDto#equals(Object)}
     */
    @Test
    void testEquals9() {
        // Arrange
        UserDto.UserDtoBuilder builderResult = UserDto.builder();
        builderResult.id(1L);
        UserDto.UserDtoBuilder userDtoBuilder = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder.role(Mockito.<String>any())).thenReturn(builderResult);
        UserDto.UserDtoBuilder userDtoBuilder2 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder2.id(Mockito.<Long>any())).thenReturn(userDtoBuilder);
        UserDto.UserDtoBuilder userDtoBuilder3 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder3.email(Mockito.<String>any())).thenReturn(userDtoBuilder2);
        UserDto buildResult = userDtoBuilder3.email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();
        UserDto.UserDtoBuilder userDtoBuilder4 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder4.email(Mockito.<String>any())).thenReturn(UserDto.builder());
        UserDto buildResult2 = userDtoBuilder4.email("jane.doe@example.org")
                .id(null)
                .role("Role")
                .UserName("User Name")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link UserDto#equals(Object)}
     */
    @Test
    void testEquals10() {
        // Arrange
        UserDto.UserDtoBuilder builderResult = UserDto.builder();
        builderResult.email("jane.doe@example.org");
        UserDto.UserDtoBuilder userDtoBuilder = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder.role(Mockito.<String>any())).thenReturn(builderResult);
        UserDto.UserDtoBuilder userDtoBuilder2 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder2.id(Mockito.<Long>any())).thenReturn(userDtoBuilder);
        UserDto.UserDtoBuilder userDtoBuilder3 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder3.email(Mockito.<String>any())).thenReturn(userDtoBuilder2);
        UserDto buildResult = userDtoBuilder3.email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();
        UserDto.UserDtoBuilder userDtoBuilder4 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder4.email(Mockito.<String>any())).thenReturn(UserDto.builder());
        UserDto buildResult2 = userDtoBuilder4.email("jane.doe@example.org")
                .id(null)
                .role("Role")
                .UserName("User Name")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link UserDto#equals(Object)}
     */
    @Test
    void testEquals11() {
        // Arrange
        UserDto.UserDtoBuilder builderResult = UserDto.builder();
        builderResult.role("User Name");
        UserDto.UserDtoBuilder userDtoBuilder = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder.role(Mockito.<String>any())).thenReturn(builderResult);
        UserDto.UserDtoBuilder userDtoBuilder2 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder2.id(Mockito.<Long>any())).thenReturn(userDtoBuilder);
        UserDto.UserDtoBuilder userDtoBuilder3 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder3.email(Mockito.<String>any())).thenReturn(userDtoBuilder2);
        UserDto buildResult = userDtoBuilder3.email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();
        UserDto.UserDtoBuilder userDtoBuilder4 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder4.email(Mockito.<String>any())).thenReturn(UserDto.builder());
        UserDto buildResult2 = userDtoBuilder4.email("jane.doe@example.org")
                .id(null)
                .role("Role")
                .UserName("User Name")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserDto#equals(Object)}
     *   <li>{@link UserDto#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        UserDto buildResult = UserDto.builder()
                .email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();

        // Act and Assert
        assertEquals(buildResult, buildResult);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserDto#equals(Object)}
     *   <li>{@link UserDto#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        UserDto buildResult = UserDto.builder()
                .email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();
        UserDto buildResult2 = UserDto.builder()
                .email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();

        // Act and Assert
        assertEquals(buildResult, buildResult2);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserDto#equals(Object)}
     *   <li>{@link UserDto#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode3() {
        // Arrange
        UserDto.UserDtoBuilder userDtoBuilder = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder.id(Mockito.<Long>any())).thenReturn(UserDto.builder());
        UserDto.UserDtoBuilder userDtoBuilder2 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder2.email(Mockito.<String>any())).thenReturn(userDtoBuilder);
        UserDto buildResult = userDtoBuilder2.email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();
        UserDto.UserDtoBuilder userDtoBuilder3 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder3.email(Mockito.<String>any())).thenReturn(UserDto.builder());
        UserDto buildResult2 = userDtoBuilder3.email("jane.doe@example.org")
                .id(null)
                .role("Role")
                .UserName("User Name")
                .build();

        // Act and Assert
        assertEquals(buildResult, buildResult2);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserDto#equals(Object)}
     *   <li>{@link UserDto#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode4() {
        // Arrange
        UserDto.UserDtoBuilder userDtoBuilder = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder.role(Mockito.<String>any())).thenReturn(UserDto.builder());
        UserDto.UserDtoBuilder userDtoBuilder2 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder2.id(Mockito.<Long>any())).thenReturn(userDtoBuilder);
        UserDto.UserDtoBuilder userDtoBuilder3 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder3.email(Mockito.<String>any())).thenReturn(userDtoBuilder2);
        UserDto buildResult = userDtoBuilder3.email("jane.doe@example.org")
                .id(1L)
                .role("Role")
                .UserName("User Name")
                .build();
        UserDto.UserDtoBuilder userDtoBuilder4 = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder4.email(Mockito.<String>any())).thenReturn(UserDto.builder());
        UserDto buildResult2 = userDtoBuilder4.email("jane.doe@example.org")
                .id(null)
                .role(null)
                .UserName("User Name")
                .build();

        // Act and Assert
        assertEquals(buildResult, buildResult2);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserDto#UserDto(Long, String, String, String)}
     *   <li>{@link UserDto#setEmail(String)}
     *   <li>{@link UserDto#setId(Long)}
     *   <li>{@link UserDto#setRole(String)}
     *   <li>{@link UserDto#setUserName(String)}
     *   <li>{@link UserDto#toString()}
     *   <li>{@link UserDto#getEmail()}
     *   <li>{@link UserDto#getId()}
     *   <li>{@link UserDto#getRole()}
     *   <li>{@link UserDto#getUserName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        UserDto actualUserDto = new UserDto(1L, "User Name", "jane.doe@example.org", "Role");
        actualUserDto.setEmail("jane.doe@example.org");
        actualUserDto.setId(1L);
        actualUserDto.setRole("Role");
        actualUserDto.setUserName("User Name");
        String actualToStringResult = actualUserDto.toString();
        String actualEmail = actualUserDto.getEmail();
        Long actualId = actualUserDto.getId();
        String actualRole = actualUserDto.getRole();

        // Assert that nothing has changed
        assertEquals("Role", actualRole);
        assertEquals("User Name", actualUserDto.getUserName());
        assertEquals("UserDto(id=1, UserName=User Name, email=jane.doe@example.org, role=Role)", actualToStringResult);
        assertEquals("jane.doe@example.org", actualEmail);
        assertEquals(1L, actualId.longValue());
    }
}
