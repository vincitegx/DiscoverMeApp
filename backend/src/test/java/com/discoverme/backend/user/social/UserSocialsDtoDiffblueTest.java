package com.discoverme.backend.user.social;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discoverme.backend.social.SocialPlatform;
import com.discoverme.backend.user.UserDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserSocialsDto.class, SocialPlatform.class, String.class})
@ExtendWith(SpringExtension.class)
class UserSocialsDtoDiffblueTest {
    @MockBean
    private UserDto userDto;

    @Autowired
    private UserSocialsDto userSocialsDto;

    /**
     * Method under test: {@link UserSocialsDto#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();

        // Act and Assert
        assertFalse((new UserSocialsDto(1L, user, SocialPlatform.FACEBOOK, "janedoe")).canEqual("Other"));
    }

    /**
     * Method under test: {@link UserSocialsDto#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        UserSocialsDto.UserSocialsDtoBuilder socialResult = UserSocialsDto.builder().id(1L).social(SocialPlatform.FACEBOOK);
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        UserSocialsDto buildResult = socialResult.user(user).userName("janedoe").build();
        UserSocialsDto.UserSocialsDtoBuilder socialResult2 = UserSocialsDto.builder()
                .id(1L)
                .social(SocialPlatform.FACEBOOK);
        UserDto user2 = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        UserSocialsDto buildResult2 = socialResult2.user(user2).userName("janedoe").build();

        // Act and Assert
        assertTrue(buildResult.canEqual(buildResult2));
    }

    /**
     * Method under test: {@link UserSocialsDto#canEqual(Object)}
     */
    @Test
    void testCanEqual3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        UserDto.UserDtoBuilder userDtoBuilder = mock(UserDto.UserDtoBuilder.class);
        when(userDtoBuilder.email(Mockito.<String>any())).thenReturn(UserDto.builder());
        UserDto user = userDtoBuilder.email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();

        // Act
        boolean actualCanEqualResult = (new UserSocialsDto(1L, user, SocialPlatform.FACEBOOK, "janedoe")).canEqual("Other");

        // Assert
        verify(userDtoBuilder).email(eq("jane.doe@example.org"));
        assertFalse(actualCanEqualResult);
    }

    /**
     * Method under test: {@link UserSocialsDto#canEqual(Object)}
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
        userSocialsDto.canEqual("Other");
    }

    /**
     * Method under test: {@link UserSocialsDto#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        UserSocialsDto.UserSocialsDtoBuilder socialResult = UserSocialsDto.builder().id(1L).social(SocialPlatform.FACEBOOK);
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        UserSocialsDto buildResult = socialResult.user(user).userName("janedoe").build();

        // Act and Assert
        assertNotEquals(buildResult, null);
    }

    /**
     * Method under test: {@link UserSocialsDto#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        UserSocialsDto.UserSocialsDtoBuilder socialResult = UserSocialsDto.builder().id(1L).social(SocialPlatform.FACEBOOK);
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        UserSocialsDto buildResult = socialResult.user(user).userName("janedoe").build();

        // Act and Assert
        assertNotEquals(buildResult, "Different type to UserSocialsDto");
    }

    /**
     * Method under test: {@link UserSocialsDto#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        UserSocialsDto.UserSocialsDtoBuilder userSocialsDtoBuilder = mock(UserSocialsDto.UserSocialsDtoBuilder.class);
        when(userSocialsDtoBuilder.id(Mockito.<Long>any())).thenReturn(UserSocialsDto.builder());
        UserSocialsDto.UserSocialsDtoBuilder socialResult = userSocialsDtoBuilder.id(1L).social(SocialPlatform.FACEBOOK);
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        UserSocialsDto buildResult = socialResult.user(user).userName("janedoe").build();
        UserSocialsDto.UserSocialsDtoBuilder socialResult2 = UserSocialsDto.builder()
                .id(1L)
                .social(SocialPlatform.FACEBOOK);
        UserDto user2 = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        UserSocialsDto buildResult2 = socialResult2.user(user2).userName("janedoe").build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link UserSocialsDto#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        UserSocialsDto.UserSocialsDtoBuilder userSocialsDtoBuilder = mock(UserSocialsDto.UserSocialsDtoBuilder.class);
        when(userSocialsDtoBuilder.social(Mockito.<SocialPlatform>any())).thenReturn(UserSocialsDto.builder());
        UserSocialsDto.UserSocialsDtoBuilder userSocialsDtoBuilder2 = mock(UserSocialsDto.UserSocialsDtoBuilder.class);
        when(userSocialsDtoBuilder2.id(Mockito.<Long>any())).thenReturn(userSocialsDtoBuilder);
        UserSocialsDto.UserSocialsDtoBuilder socialResult = userSocialsDtoBuilder2.id(1L).social(SocialPlatform.FACEBOOK);
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        UserSocialsDto buildResult = socialResult.user(user).userName("janedoe").build();
        UserSocialsDto.UserSocialsDtoBuilder userSocialsDtoBuilder3 = mock(UserSocialsDto.UserSocialsDtoBuilder.class);
        when(userSocialsDtoBuilder3.id(Mockito.<Long>any())).thenReturn(UserSocialsDto.builder());
        UserSocialsDto.UserSocialsDtoBuilder socialResult2 = userSocialsDtoBuilder3.id(1L).social(SocialPlatform.FACEBOOK);
        UserDto user2 = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        UserSocialsDto buildResult2 = socialResult2.user(user2).userName("janedoe").build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link UserSocialsDto#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        UserSocialsDto.UserSocialsDtoBuilder userSocialsDtoBuilder = mock(UserSocialsDto.UserSocialsDtoBuilder.class);
        when(userSocialsDtoBuilder.user(Mockito.<UserDto>any())).thenReturn(UserSocialsDto.builder());
        UserSocialsDto.UserSocialsDtoBuilder userSocialsDtoBuilder2 = mock(UserSocialsDto.UserSocialsDtoBuilder.class);
        when(userSocialsDtoBuilder2.social(Mockito.<SocialPlatform>any())).thenReturn(userSocialsDtoBuilder);
        UserSocialsDto.UserSocialsDtoBuilder userSocialsDtoBuilder3 = mock(UserSocialsDto.UserSocialsDtoBuilder.class);
        when(userSocialsDtoBuilder3.id(Mockito.<Long>any())).thenReturn(userSocialsDtoBuilder2);
        UserSocialsDto.UserSocialsDtoBuilder socialResult = userSocialsDtoBuilder3.id(1L).social(SocialPlatform.FACEBOOK);
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        UserSocialsDto buildResult = socialResult.user(user).userName("janedoe").build();
        UserSocialsDto.UserSocialsDtoBuilder userSocialsDtoBuilder4 = mock(UserSocialsDto.UserSocialsDtoBuilder.class);
        when(userSocialsDtoBuilder4.id(Mockito.<Long>any())).thenReturn(UserSocialsDto.builder());
        UserSocialsDto.UserSocialsDtoBuilder socialResult2 = userSocialsDtoBuilder4.id(1L).social(SocialPlatform.FACEBOOK);
        UserDto user2 = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        UserSocialsDto buildResult2 = socialResult2.user(user2).userName("janedoe").build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link UserSocialsDto#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        UserSocialsDto.UserSocialsDtoBuilder builderResult = UserSocialsDto.builder();
        builderResult.id(1L);
        UserSocialsDto.UserSocialsDtoBuilder userSocialsDtoBuilder = mock(UserSocialsDto.UserSocialsDtoBuilder.class);
        when(userSocialsDtoBuilder.user(Mockito.<UserDto>any())).thenReturn(builderResult);
        UserSocialsDto.UserSocialsDtoBuilder userSocialsDtoBuilder2 = mock(UserSocialsDto.UserSocialsDtoBuilder.class);
        when(userSocialsDtoBuilder2.social(Mockito.<SocialPlatform>any())).thenReturn(userSocialsDtoBuilder);
        UserSocialsDto.UserSocialsDtoBuilder userSocialsDtoBuilder3 = mock(UserSocialsDto.UserSocialsDtoBuilder.class);
        when(userSocialsDtoBuilder3.id(Mockito.<Long>any())).thenReturn(userSocialsDtoBuilder2);
        UserSocialsDto.UserSocialsDtoBuilder socialResult = userSocialsDtoBuilder3.id(1L).social(SocialPlatform.FACEBOOK);
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        UserSocialsDto buildResult = socialResult.user(user).userName("janedoe").build();
        UserSocialsDto.UserSocialsDtoBuilder userSocialsDtoBuilder4 = mock(UserSocialsDto.UserSocialsDtoBuilder.class);
        when(userSocialsDtoBuilder4.id(Mockito.<Long>any())).thenReturn(UserSocialsDto.builder());
        UserSocialsDto.UserSocialsDtoBuilder socialResult2 = userSocialsDtoBuilder4.id(1L).social(SocialPlatform.FACEBOOK);
        UserDto user2 = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        UserSocialsDto buildResult2 = socialResult2.user(user2).userName("janedoe").build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserSocialsDto#equals(Object)}
     *   <li>{@link UserSocialsDto#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        UserSocialsDto.UserSocialsDtoBuilder socialResult = UserSocialsDto.builder().id(1L).social(SocialPlatform.FACEBOOK);
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        UserSocialsDto buildResult = socialResult.user(user).userName("janedoe").build();

        // Act and Assert
        assertEquals(buildResult, buildResult);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserSocialsDto#equals(Object)}
     *   <li>{@link UserSocialsDto#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        UserSocialsDto.UserSocialsDtoBuilder socialResult = UserSocialsDto.builder().id(1L).social(SocialPlatform.FACEBOOK);
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        UserSocialsDto buildResult = socialResult.user(user).userName("janedoe").build();
        UserSocialsDto.UserSocialsDtoBuilder socialResult2 = UserSocialsDto.builder()
                .id(1L)
                .social(SocialPlatform.FACEBOOK);
        UserDto user2 = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        UserSocialsDto buildResult2 = socialResult2.user(user2).userName("janedoe").build();

        // Act and Assert
        assertEquals(buildResult, buildResult2);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserSocialsDto#equals(Object)}
     *   <li>{@link UserSocialsDto#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode3() {
        // Arrange
        UserSocialsDto.UserSocialsDtoBuilder userSocialsDtoBuilder = mock(UserSocialsDto.UserSocialsDtoBuilder.class);
        when(userSocialsDtoBuilder.id(Mockito.<Long>any())).thenReturn(UserSocialsDto.builder());
        UserSocialsDto.UserSocialsDtoBuilder socialResult = userSocialsDtoBuilder.id(1L).social(SocialPlatform.FACEBOOK);
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        UserSocialsDto buildResult = socialResult.user(user).userName("janedoe").build();
        UserSocialsDto.UserSocialsDtoBuilder userSocialsDtoBuilder2 = mock(UserSocialsDto.UserSocialsDtoBuilder.class);
        when(userSocialsDtoBuilder2.id(Mockito.<Long>any())).thenReturn(UserSocialsDto.builder());
        UserSocialsDto.UserSocialsDtoBuilder socialResult2 = userSocialsDtoBuilder2.id(1L).social(SocialPlatform.FACEBOOK);
        UserDto user2 = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        UserSocialsDto buildResult2 = socialResult2.user(user2).userName("janedoe").build();

        // Act and Assert
        assertEquals(buildResult, buildResult2);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link UserSocialsDto#UserSocialsDto(Long, UserDto, SocialPlatform, String)}
     *   <li>{@link UserSocialsDto#setId(Long)}
     *   <li>{@link UserSocialsDto#setSocial(SocialPlatform)}
     *   <li>{@link UserSocialsDto#setUser(UserDto)}
     *   <li>{@link UserSocialsDto#setUserName(String)}
     *   <li>{@link UserSocialsDto#toString()}
     *   <li>{@link UserSocialsDto#getId()}
     *   <li>{@link UserSocialsDto#getSocial()}
     *   <li>{@link UserSocialsDto#getUser()}
     *   <li>{@link UserSocialsDto#getUserName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        UserDto user = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();

        // Act
        UserSocialsDto actualUserSocialsDto = new UserSocialsDto(1L, user, SocialPlatform.FACEBOOK, "janedoe");
        actualUserSocialsDto.setId(1L);
        actualUserSocialsDto.setSocial(SocialPlatform.FACEBOOK);
        UserDto user2 = UserDto.builder().email("jane.doe@example.org").id(1L).role("Role").stageName("Stage Name").build();
        actualUserSocialsDto.setUser(user2);
        actualUserSocialsDto.setUserName("janedoe");
        String actualToStringResult = actualUserSocialsDto.toString();
        Long actualId = actualUserSocialsDto.getId();
        SocialPlatform actualSocial = actualUserSocialsDto.getSocial();
        UserDto actualUser = actualUserSocialsDto.getUser();

        // Assert that nothing has changed
        assertEquals("UserSocialsDto(id=1, user=UserDto(id=1, stageName=Stage Name, email=jane.doe@example.org, role=Role),"
                + " social=FACEBOOK, userName=janedoe)", actualToStringResult);
        assertEquals("janedoe", actualUserSocialsDto.getUserName());
        assertEquals(1L, actualId.longValue());
        assertEquals(SocialPlatform.FACEBOOK, actualSocial);
        assertEquals(user, actualUser);
        assertSame(user2, actualUser);
    }
}
