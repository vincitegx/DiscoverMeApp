package com.discoverme.backend.user.login;

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
@ContextConfiguration(classes = {LoginRequest.class})
@ExtendWith(SpringExtension.class)
class LoginRequestDiffblueTest {
    @Autowired
    private LoginRequest loginRequest;

    /**
     * Method under test: {@link LoginRequest#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new LoginRequest()).canEqual("Other"));
    }

    /**
     * Method under test: {@link LoginRequest#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        LoginRequest loginRequest2 = new LoginRequest();

        LoginRequest loginRequest3 = new LoginRequest();
        loginRequest3.setEmail("jane.doe@example.org");
        loginRequest3.setPassword("iloveyou");

        // Act and Assert
        assertTrue(loginRequest2.canEqual(loginRequest3));
    }

    /**
     * Method under test: {@link LoginRequest#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("jane.doe@example.org");
        loginRequest.setPassword("iloveyou");

        // Act and Assert
        assertNotEquals(loginRequest, null);
    }

    /**
     * Method under test: {@link LoginRequest#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("jane.doe@example.org");
        loginRequest.setPassword("iloveyou");

        // Act and Assert
        assertNotEquals(loginRequest, "Different type to LoginRequest");
    }

    /**
     * Method under test: {@link LoginRequest#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("john.smith@example.org");
        loginRequest.setPassword("iloveyou");

        LoginRequest loginRequest2 = new LoginRequest();
        loginRequest2.setEmail("jane.doe@example.org");
        loginRequest2.setPassword("iloveyou");

        // Act and Assert
        assertNotEquals(loginRequest, loginRequest2);
    }

    /**
     * Method under test: {@link LoginRequest#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(null);
        loginRequest.setPassword("iloveyou");

        LoginRequest loginRequest2 = new LoginRequest();
        loginRequest2.setEmail("jane.doe@example.org");
        loginRequest2.setPassword("iloveyou");

        // Act and Assert
        assertNotEquals(loginRequest, loginRequest2);
    }

    /**
     * Method under test: {@link LoginRequest#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("jane.doe@example.org");
        loginRequest.setPassword("jane.doe@example.org");

        LoginRequest loginRequest2 = new LoginRequest();
        loginRequest2.setEmail("jane.doe@example.org");
        loginRequest2.setPassword("iloveyou");

        // Act and Assert
        assertNotEquals(loginRequest, loginRequest2);
    }

    /**
     * Method under test: {@link LoginRequest#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("jane.doe@example.org");
        loginRequest.setPassword(null);

        LoginRequest loginRequest2 = new LoginRequest();
        loginRequest2.setEmail("jane.doe@example.org");
        loginRequest2.setPassword("iloveyou");

        // Act and Assert
        assertNotEquals(loginRequest, loginRequest2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link LoginRequest#equals(Object)}
     *   <li>{@link LoginRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("jane.doe@example.org");
        loginRequest.setPassword("iloveyou");

        // Act and Assert
        assertEquals(loginRequest, loginRequest);
        int expectedHashCodeResult = loginRequest.hashCode();
        assertEquals(expectedHashCodeResult, loginRequest.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link LoginRequest#equals(Object)}
     *   <li>{@link LoginRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("jane.doe@example.org");
        loginRequest.setPassword("iloveyou");

        LoginRequest loginRequest2 = new LoginRequest();
        loginRequest2.setEmail("jane.doe@example.org");
        loginRequest2.setPassword("iloveyou");

        // Act and Assert
        assertEquals(loginRequest, loginRequest2);
        int expectedHashCodeResult = loginRequest.hashCode();
        assertEquals(expectedHashCodeResult, loginRequest2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link LoginRequest#equals(Object)}
     *   <li>{@link LoginRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode3() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(null);
        loginRequest.setPassword("iloveyou");

        LoginRequest loginRequest2 = new LoginRequest();
        loginRequest2.setEmail(null);
        loginRequest2.setPassword("iloveyou");

        // Act and Assert
        assertEquals(loginRequest, loginRequest2);
        int expectedHashCodeResult = loginRequest.hashCode();
        assertEquals(expectedHashCodeResult, loginRequest2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link LoginRequest#equals(Object)}
     *   <li>{@link LoginRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode4() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("jane.doe@example.org");
        loginRequest.setPassword(null);

        LoginRequest loginRequest2 = new LoginRequest();
        loginRequest2.setEmail("jane.doe@example.org");
        loginRequest2.setPassword(null);

        // Act and Assert
        assertEquals(loginRequest, loginRequest2);
        int expectedHashCodeResult = loginRequest.hashCode();
        assertEquals(expectedHashCodeResult, loginRequest2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link LoginRequest}
     *   <li>{@link LoginRequest#setEmail(String)}
     *   <li>{@link LoginRequest#setPassword(String)}
     *   <li>{@link LoginRequest#toString()}
     *   <li>{@link LoginRequest#getEmail()}
     *   <li>{@link LoginRequest#getPassword()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        LoginRequest actualLoginRequest = new LoginRequest();
        actualLoginRequest.setEmail("jane.doe@example.org");
        actualLoginRequest.setPassword("iloveyou");
        String actualToStringResult = actualLoginRequest.toString();
        String actualEmail = actualLoginRequest.getEmail();

        // Assert that nothing has changed
        assertEquals("LoginRequest(email=jane.doe@example.org, password=iloveyou)", actualToStringResult);
        assertEquals("iloveyou", actualLoginRequest.getPassword());
        assertEquals("jane.doe@example.org", actualEmail);
    }
}
