package com.discoverme.backend.user.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {RegistrationRequest.class, String.class})
@ExtendWith(SpringExtension.class)
class RegistrationRequestDiffblueTest {
    @Autowired
    private RegistrationRequest registrationRequest;

    /**
     * Method under test: {@link RegistrationRequest#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse(registrationRequest.canEqual("Other"));
        assertTrue(registrationRequest.canEqual(registrationRequest));
    }

    /**
     * Method under test: {@link RegistrationRequest#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange, Act and Assert
        assertNotEquals(new RegistrationRequest("Stage Name", "jane.doe@example.org", "iloveyou"), null);
        assertNotEquals(new RegistrationRequest("Stage Name", "jane.doe@example.org", "iloveyou"),
                "Different type to RegistrationRequest");
    }

    /**
     * Method under test: {@link RegistrationRequest#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        RegistrationRequest registrationRequest = new RegistrationRequest("jane.doe@example.org", "jane.doe@example.org",
                "iloveyou");

        // Act and Assert
        assertNotEquals(registrationRequest, new RegistrationRequest("Stage Name", "jane.doe@example.org", "iloveyou"));
    }

    /**
     * Method under test: {@link RegistrationRequest#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        RegistrationRequest registrationRequest = new RegistrationRequest("Stage Name", "john.smith@example.org",
                "iloveyou");

        // Act and Assert
        assertNotEquals(registrationRequest, new RegistrationRequest("Stage Name", "jane.doe@example.org", "iloveyou"));
    }

    /**
     * Method under test: {@link RegistrationRequest#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        RegistrationRequest registrationRequest = new RegistrationRequest("Stage Name", "jane.doe@example.org",
                "Stage Name");

        // Act and Assert
        assertNotEquals(registrationRequest, new RegistrationRequest("Stage Name", "jane.doe@example.org", "iloveyou"));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link RegistrationRequest#equals(Object)}
     *   <li>{@link RegistrationRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        RegistrationRequest registrationRequest = new RegistrationRequest("Stage Name", "jane.doe@example.org", "iloveyou");

        // Act and Assert
        assertEquals(registrationRequest, registrationRequest);
        int expectedHashCodeResult = registrationRequest.hashCode();
        assertEquals(expectedHashCodeResult, registrationRequest.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link RegistrationRequest#equals(Object)}
     *   <li>{@link RegistrationRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        RegistrationRequest registrationRequest = new RegistrationRequest("Stage Name", "jane.doe@example.org", "iloveyou");
        RegistrationRequest registrationRequest2 = new RegistrationRequest("Stage Name", "jane.doe@example.org",
                "iloveyou");

        // Act and Assert
        assertEquals(registrationRequest, registrationRequest2);
        int expectedHashCodeResult = registrationRequest.hashCode();
        assertEquals(expectedHashCodeResult, registrationRequest2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link RegistrationRequest#toString()}
     *   <li>{@link RegistrationRequest#getEmail()}
     *   <li>{@link RegistrationRequest#getPassword()}
     *   <li>{@link RegistrationRequest#getStageName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        RegistrationRequest registrationRequest = new RegistrationRequest("Stage Name", "jane.doe@example.org", "iloveyou");

        // Act
        String actualToStringResult = registrationRequest.toString();
        String actualEmail = registrationRequest.getEmail();
        String actualPassword = registrationRequest.getPassword();

        // Assert
        assertEquals("RegistrationRequest(stageName=Stage Name, email=jane.doe@example.org, password=iloveyou)",
                actualToStringResult);
        assertEquals("Stage Name", registrationRequest.getStageName());
        assertEquals("iloveyou", actualPassword);
        assertEquals("jane.doe@example.org", actualEmail);
    }

    /**
     * Method under test:
     * {@link RegistrationRequest#RegistrationRequest(String, String, String)}
     */
    @Test
    void testNewRegistrationRequest() {
        // Arrange and Act
        RegistrationRequest actualRegistrationRequest = new RegistrationRequest("Stage Name", "jane.doe@example.org",
                "iloveyou");

        // Assert
        assertEquals("Stage Name", actualRegistrationRequest.getStageName());
        assertEquals("iloveyou", actualRegistrationRequest.getPassword());
        assertEquals("jane.doe@example.org", actualRegistrationRequest.getEmail());
    }

    /**
     * Method under test: {@link RegistrationRequest#setEmail(String)}
     */
    @Test
    void testSetEmail() {
        // Arrange and Act
        registrationRequest.setEmail("jane.doe@example.org");

        // Assert
        assertEquals("jane.doe@example.org", registrationRequest.getEmail());
    }

    /**
     * Method under test: {@link RegistrationRequest#setPassword(String)}
     */
    @Test
    void testSetPassword() {
        // Arrange and Act
        registrationRequest.setPassword("iloveyou");

        // Assert
        assertEquals("iloveyou", registrationRequest.getPassword());
    }

    /**
     * Method under test: {@link RegistrationRequest#setStageName(String)}
     */
    @Test
    void testSetStageName() {
        // Arrange and Act
        registrationRequest.setStageName("Stage Name");

        // Assert
        assertEquals("Stage Name", registrationRequest.getStageName());
    }
}
