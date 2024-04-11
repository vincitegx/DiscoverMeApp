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
@ContextConfiguration(classes = {AdminRegistrationRequest.class, String.class})
@ExtendWith(SpringExtension.class)
class AdminRegistrationRequestDiffblueTest {
    @Autowired
    private AdminRegistrationRequest adminRegistrationRequest;

    /**
     * Method under test: {@link AdminRegistrationRequest#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse(adminRegistrationRequest.canEqual("Other"));
        assertTrue(adminRegistrationRequest.canEqual(adminRegistrationRequest));
    }

    /**
     * Method under test: {@link AdminRegistrationRequest#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange, Act and Assert
        assertNotEquals(new AdminRegistrationRequest("jane.doe@example.org", "iloveyou"), null);
        assertNotEquals(new AdminRegistrationRequest("jane.doe@example.org", "iloveyou"),
                "Different type to AdminRegistrationRequest");
    }

    /**
     * Method under test: {@link AdminRegistrationRequest#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        AdminRegistrationRequest adminRegistrationRequest = new AdminRegistrationRequest("john.smith@example.org",
                "iloveyou");

        // Act and Assert
        assertNotEquals(adminRegistrationRequest, new AdminRegistrationRequest("jane.doe@example.org", "iloveyou"));
    }

    /**
     * Method under test: {@link AdminRegistrationRequest#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        AdminRegistrationRequest adminRegistrationRequest = new AdminRegistrationRequest("jane.doe@example.org",
                "jane.doe@example.org");

        // Act and Assert
        assertNotEquals(adminRegistrationRequest, new AdminRegistrationRequest("jane.doe@example.org", "iloveyou"));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link AdminRegistrationRequest#equals(Object)}
     *   <li>{@link AdminRegistrationRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        AdminRegistrationRequest adminRegistrationRequest = new AdminRegistrationRequest("jane.doe@example.org",
                "iloveyou");

        // Act and Assert
        assertEquals(adminRegistrationRequest, adminRegistrationRequest);
        int expectedHashCodeResult = adminRegistrationRequest.hashCode();
        assertEquals(expectedHashCodeResult, adminRegistrationRequest.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link AdminRegistrationRequest#equals(Object)}
     *   <li>{@link AdminRegistrationRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        AdminRegistrationRequest adminRegistrationRequest = new AdminRegistrationRequest("jane.doe@example.org",
                "iloveyou");
        AdminRegistrationRequest adminRegistrationRequest2 = new AdminRegistrationRequest("jane.doe@example.org",
                "iloveyou");

        // Act and Assert
        assertEquals(adminRegistrationRequest, adminRegistrationRequest2);
        int expectedHashCodeResult = adminRegistrationRequest.hashCode();
        assertEquals(expectedHashCodeResult, adminRegistrationRequest2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link AdminRegistrationRequest#toString()}
     *   <li>{@link AdminRegistrationRequest#getEmail()}
     *   <li>{@link AdminRegistrationRequest#getPassword()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        AdminRegistrationRequest adminRegistrationRequest = new AdminRegistrationRequest("jane.doe@example.org",
                "iloveyou");

        // Act
        String actualToStringResult = adminRegistrationRequest.toString();
        String actualEmail = adminRegistrationRequest.getEmail();

        // Assert
        assertEquals("AdminRegistrationRequest(email=jane.doe@example.org, password=iloveyou)", actualToStringResult);
        assertEquals("iloveyou", adminRegistrationRequest.getPassword());
        assertEquals("jane.doe@example.org", actualEmail);
    }

    /**
     * Method under test:
     * {@link AdminRegistrationRequest#AdminRegistrationRequest(String, String)}
     */
    @Test
    void testNewAdminRegistrationRequest() {
        // Arrange and Act
        AdminRegistrationRequest actualAdminRegistrationRequest = new AdminRegistrationRequest("jane.doe@example.org",
                "iloveyou");

        // Assert
        assertEquals("iloveyou", actualAdminRegistrationRequest.getPassword());
        assertEquals("jane.doe@example.org", actualAdminRegistrationRequest.getEmail());
    }

    /**
     * Method under test: {@link AdminRegistrationRequest#setEmail(String)}
     */
    @Test
    void testSetEmail() {
        // Arrange and Act
        adminRegistrationRequest.setEmail("jane.doe@example.org");

        // Assert
        assertEquals("jane.doe@example.org", adminRegistrationRequest.getEmail());
    }

    /**
     * Method under test: {@link AdminRegistrationRequest#setPassword(String)}
     */
    @Test
    void testSetPassword() {
        // Arrange and Act
        adminRegistrationRequest.setPassword("iloveyou");

        // Assert
        assertEquals("iloveyou", adminRegistrationRequest.getPassword());
    }
}
