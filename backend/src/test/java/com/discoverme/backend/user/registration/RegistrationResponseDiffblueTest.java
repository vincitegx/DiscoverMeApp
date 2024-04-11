package com.discoverme.backend.user.registration;

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

@ContextConfiguration(classes = {RegistrationResponse.class, String.class})
@ExtendWith(SpringExtension.class)
class RegistrationResponseDiffblueTest {
    @Autowired
    private RegistrationResponse registrationResponse;

    /**
     * Method under test: {@link RegistrationResponse#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange, Act and Assert
        assertFalse((new RegistrationResponse(1L, "Stage Name", "jane.doe@example.org")).canEqual("Other"));
    }

    /**
     * Method under test: {@link RegistrationResponse#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        RegistrationResponse buildResult = RegistrationResponse.builder()
                .email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
                .build();
        RegistrationResponse buildResult2 = RegistrationResponse.builder()
                .email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
                .build();

        // Act and Assert
        assertTrue(buildResult.canEqual(buildResult2));
    }

    /**
     * Method under test: {@link RegistrationResponse#canEqual(Object)}
     */
    @Test
    void testCanEqual3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        RegistrationResponse.RegistrationResponseBuilder registrationResponseBuilder = mock(
                RegistrationResponse.RegistrationResponseBuilder.class);
        when(registrationResponseBuilder.email(Mockito.<String>any())).thenReturn(RegistrationResponse.builder());
        RegistrationResponse buildResult = registrationResponseBuilder.email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
                .build();
        RegistrationResponse buildResult2 = RegistrationResponse.builder()
                .email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
                .build();

        // Act
        boolean actualCanEqualResult = buildResult.canEqual(buildResult2);

        // Assert
        verify(registrationResponseBuilder).email(eq("jane.doe@example.org"));
        assertTrue(actualCanEqualResult);
    }

    /**
     * Method under test: {@link RegistrationResponse#canEqual(Object)}
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
        registrationResponse.canEqual("Other");
    }

    /**
     * Method under test: {@link RegistrationResponse#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        RegistrationResponse buildResult = RegistrationResponse.builder()
                .email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
                .build();

        // Act and Assert
        assertNotEquals(buildResult, null);
    }

    /**
     * Method under test: {@link RegistrationResponse#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        RegistrationResponse buildResult = RegistrationResponse.builder()
                .email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
                .build();

        // Act and Assert
        assertNotEquals(buildResult, "Different type to RegistrationResponse");
    }

    /**
     * Method under test: {@link RegistrationResponse#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        RegistrationResponse.RegistrationResponseBuilder registrationResponseBuilder = mock(
                RegistrationResponse.RegistrationResponseBuilder.class);
        when(registrationResponseBuilder.email(Mockito.<String>any())).thenReturn(RegistrationResponse.builder());
        RegistrationResponse buildResult = registrationResponseBuilder.email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
                .build();
        RegistrationResponse buildResult2 = RegistrationResponse.builder()
                .email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link RegistrationResponse#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        RegistrationResponse.RegistrationResponseBuilder registrationResponseBuilder = mock(
                RegistrationResponse.RegistrationResponseBuilder.class);
        when(registrationResponseBuilder.stageName(Mockito.<String>any())).thenReturn(RegistrationResponse.builder());
        RegistrationResponse.RegistrationResponseBuilder registrationResponseBuilder2 = mock(
                RegistrationResponse.RegistrationResponseBuilder.class);
        when(registrationResponseBuilder2.email(Mockito.<String>any())).thenReturn(registrationResponseBuilder);
        RegistrationResponse buildResult = registrationResponseBuilder2.email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
                .build();
        RegistrationResponse buildResult2 = RegistrationResponse.builder()
                .email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link RegistrationResponse#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        RegistrationResponse.RegistrationResponseBuilder registrationResponseBuilder = mock(
                RegistrationResponse.RegistrationResponseBuilder.class);
        when(registrationResponseBuilder.userId(Mockito.<Long>any())).thenReturn(RegistrationResponse.builder());
        RegistrationResponse.RegistrationResponseBuilder registrationResponseBuilder2 = mock(
                RegistrationResponse.RegistrationResponseBuilder.class);
        when(registrationResponseBuilder2.stageName(Mockito.<String>any())).thenReturn(registrationResponseBuilder);
        RegistrationResponse.RegistrationResponseBuilder registrationResponseBuilder3 = mock(
                RegistrationResponse.RegistrationResponseBuilder.class);
        when(registrationResponseBuilder3.email(Mockito.<String>any())).thenReturn(registrationResponseBuilder2);
        RegistrationResponse buildResult = registrationResponseBuilder3.email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
                .build();
        RegistrationResponse buildResult2 = RegistrationResponse.builder()
                .email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link RegistrationResponse#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        RegistrationResponse.RegistrationResponseBuilder registrationResponseBuilder = mock(
                RegistrationResponse.RegistrationResponseBuilder.class);
        when(registrationResponseBuilder.userId(Mockito.<Long>any())).thenReturn(RegistrationResponse.builder());
        RegistrationResponse.RegistrationResponseBuilder registrationResponseBuilder2 = mock(
                RegistrationResponse.RegistrationResponseBuilder.class);
        when(registrationResponseBuilder2.stageName(Mockito.<String>any())).thenReturn(registrationResponseBuilder);
        RegistrationResponse.RegistrationResponseBuilder registrationResponseBuilder3 = mock(
                RegistrationResponse.RegistrationResponseBuilder.class);
        when(registrationResponseBuilder3.email(Mockito.<String>any())).thenReturn(registrationResponseBuilder2);
        RegistrationResponse buildResult = registrationResponseBuilder3.email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
                .build();
        RegistrationResponse buildResult2 = RegistrationResponse.builder()
                .email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(null)
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link RegistrationResponse#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        RegistrationResponse.RegistrationResponseBuilder registrationResponseBuilder = mock(
                RegistrationResponse.RegistrationResponseBuilder.class);
        RegistrationResponse buildResult = RegistrationResponse.builder()
                .email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
                .build();
        when(registrationResponseBuilder.build()).thenReturn(buildResult);
        RegistrationResponse.RegistrationResponseBuilder registrationResponseBuilder2 = mock(
                RegistrationResponse.RegistrationResponseBuilder.class);
        when(registrationResponseBuilder2.userId(Mockito.<Long>any())).thenReturn(registrationResponseBuilder);
        RegistrationResponse.RegistrationResponseBuilder registrationResponseBuilder3 = mock(
                RegistrationResponse.RegistrationResponseBuilder.class);
        when(registrationResponseBuilder3.stageName(Mockito.<String>any())).thenReturn(registrationResponseBuilder2);
        RegistrationResponse.RegistrationResponseBuilder registrationResponseBuilder4 = mock(
                RegistrationResponse.RegistrationResponseBuilder.class);
        when(registrationResponseBuilder4.email(Mockito.<String>any())).thenReturn(registrationResponseBuilder3);
        RegistrationResponse buildResult2 = registrationResponseBuilder4.email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
                .build();
        RegistrationResponse buildResult3 = RegistrationResponse.builder()
                .email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(null)
                .build();

        // Act and Assert
        assertNotEquals(buildResult2, buildResult3);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link RegistrationResponse#equals(Object)}
     *   <li>{@link RegistrationResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        RegistrationResponse buildResult = RegistrationResponse.builder()
                .email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
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
     *   <li>{@link RegistrationResponse#equals(Object)}
     *   <li>{@link RegistrationResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        RegistrationResponse buildResult = RegistrationResponse.builder()
                .email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
                .build();
        RegistrationResponse buildResult2 = RegistrationResponse.builder()
                .email("jane.doe@example.org")
                .stageName("Stage Name")
                .userId(1L)
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
     *   <li>{@link RegistrationResponse#RegistrationResponse(Long, String, String)}
     *   <li>{@link RegistrationResponse#setEmail(String)}
     *   <li>{@link RegistrationResponse#setStageName(String)}
     *   <li>{@link RegistrationResponse#setUserId(Long)}
     *   <li>{@link RegistrationResponse#toString()}
     *   <li>{@link RegistrationResponse#getEmail()}
     *   <li>{@link RegistrationResponse#getStageName()}
     *   <li>{@link RegistrationResponse#getUserId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        RegistrationResponse actualRegistrationResponse = new RegistrationResponse(1L, "Stage Name",
                "jane.doe@example.org");
        actualRegistrationResponse.setEmail("jane.doe@example.org");
        actualRegistrationResponse.setStageName("Stage Name");
        actualRegistrationResponse.setUserId(1L);
        String actualToStringResult = actualRegistrationResponse.toString();
        String actualEmail = actualRegistrationResponse.getEmail();
        String actualStageName = actualRegistrationResponse.getStageName();

        // Assert that nothing has changed
        assertEquals("RegistrationResponse(userId=1, stageName=Stage Name, email=jane.doe@example.org)",
                actualToStringResult);
        assertEquals("Stage Name", actualStageName);
        assertEquals("jane.doe@example.org", actualEmail);
        assertEquals(1L, actualRegistrationResponse.getUserId().longValue());
    }
}
