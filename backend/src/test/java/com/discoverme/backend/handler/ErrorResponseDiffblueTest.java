package com.discoverme.backend.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ErrorResponse.class})
@ExtendWith(SpringExtension.class)
class ErrorResponseDiffblueTest {
    @Autowired
    private ErrorResponse errorResponse;

    /**
     * Method under test: {@link ErrorResponse#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse(errorResponse.canEqual("Other"));
        assertTrue(errorResponse.canEqual(errorResponse));
        assertTrue(errorResponse.canEqual(new ErrorResponse(mock(ErrorResponse.ErrorResponseBuilder.class))));
    }

    /**
     * Method under test: {@link ErrorResponse#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange, Act and Assert
        assertNotEquals(new ErrorResponse(), null);
        assertNotEquals(new ErrorResponse(), "Different type to ErrorResponse");
    }

    /**
     * Method under test: {@link ErrorResponse#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        LocalDateTime timeStamp = LocalDate.of(1970, 1, 1).atStartOfDay();
        ErrorResponse errorResponse = new ErrorResponse(timeStamp, HttpStatus.CONTINUE, "Not all who wander are lost",
                "Developer Message", new HashMap<>());

        // Act and Assert
        assertNotEquals(errorResponse, new ErrorResponse());
    }

    /**
     * Method under test: {@link ErrorResponse#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        ErrorResponse errorResponse = new ErrorResponse();
        LocalDateTime timeStamp = LocalDate.of(1970, 1, 1).atStartOfDay();

        // Act and Assert
        assertNotEquals(errorResponse, new ErrorResponse(timeStamp, HttpStatus.CONTINUE, "Not all who wander are lost",
                "Developer Message", new HashMap<>()));
    }

    /**
     * Method under test: {@link ErrorResponse#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.CONTINUE);

        // Act and Assert
        assertNotEquals(errorResponse, new ErrorResponse());
    }

    /**
     * Method under test: {@link ErrorResponse#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Not all who wander are lost");

        // Act and Assert
        assertNotEquals(errorResponse, new ErrorResponse());
    }

    /**
     * Method under test: {@link ErrorResponse#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setDeveloperMessage("Developer Message");

        // Act and Assert
        assertNotEquals(errorResponse, new ErrorResponse());
    }

    /**
     * Method under test: {@link ErrorResponse#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setData(new HashMap<>());

        // Act and Assert
        assertNotEquals(errorResponse, new ErrorResponse());
    }

    /**
     * Method under test: {@link ErrorResponse#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        ErrorResponse errorResponse = new ErrorResponse();

        ErrorResponse errorResponse2 = new ErrorResponse();
        errorResponse2.setStatus(HttpStatus.CONTINUE);

        // Act and Assert
        assertNotEquals(errorResponse, errorResponse2);
    }

    /**
     * Method under test: {@link ErrorResponse#equals(Object)}
     */
    @Test
    void testEquals9() {
        // Arrange
        ErrorResponse errorResponse = new ErrorResponse();

        ErrorResponse errorResponse2 = new ErrorResponse();
        errorResponse2.setMessage("Not all who wander are lost");

        // Act and Assert
        assertNotEquals(errorResponse, errorResponse2);
    }

    /**
     * Method under test: {@link ErrorResponse#equals(Object)}
     */
    @Test
    void testEquals10() {
        // Arrange
        ErrorResponse errorResponse = new ErrorResponse();

        ErrorResponse errorResponse2 = new ErrorResponse();
        errorResponse2.setDeveloperMessage("Developer Message");

        // Act and Assert
        assertNotEquals(errorResponse, errorResponse2);
    }

    /**
     * Method under test: {@link ErrorResponse#equals(Object)}
     */
    @Test
    void testEquals11() {
        // Arrange
        ErrorResponse errorResponse = new ErrorResponse();

        ErrorResponse errorResponse2 = new ErrorResponse();
        errorResponse2.setData(new HashMap<>());

        // Act and Assert
        assertNotEquals(errorResponse, errorResponse2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ErrorResponse#equals(Object)}
     *   <li>{@link ErrorResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        ErrorResponse errorResponse = new ErrorResponse();

        // Act and Assert
        assertEquals(errorResponse, errorResponse);
        int expectedHashCodeResult = errorResponse.hashCode();
        assertEquals(expectedHashCodeResult, errorResponse.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ErrorResponse#equals(Object)}
     *   <li>{@link ErrorResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        ErrorResponse errorResponse = new ErrorResponse();
        ErrorResponse errorResponse2 = new ErrorResponse();

        // Act and Assert
        assertEquals(errorResponse, errorResponse2);
        int expectedHashCodeResult = errorResponse.hashCode();
        assertEquals(expectedHashCodeResult, errorResponse2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ErrorResponse#equals(Object)}
     *   <li>{@link ErrorResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode3() {
        // Arrange
        LocalDateTime timeStamp = LocalDate.of(1970, 1, 1).atStartOfDay();
        ErrorResponse errorResponse = new ErrorResponse(timeStamp, HttpStatus.CONTINUE, "Not all who wander are lost",
                "Developer Message", new HashMap<>());
        LocalDateTime timeStamp2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ErrorResponse errorResponse2 = new ErrorResponse(timeStamp2, HttpStatus.CONTINUE, "Not all who wander are lost",
                "Developer Message", new HashMap<>());

        // Act and Assert
        assertEquals(errorResponse, errorResponse2);
        int expectedHashCodeResult = errorResponse.hashCode();
        assertEquals(expectedHashCodeResult, errorResponse2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ErrorResponse#ErrorResponse()}
     *   <li>{@link ErrorResponse#setData(Map)}
     *   <li>{@link ErrorResponse#setDeveloperMessage(String)}
     *   <li>{@link ErrorResponse#setMessage(String)}
     *   <li>{@link ErrorResponse#setStatus(HttpStatus)}
     *   <li>{@link ErrorResponse#setTimeStamp(LocalDateTime)}
     *   <li>{@link ErrorResponse#toString()}
     *   <li>{@link ErrorResponse#getData()}
     *   <li>{@link ErrorResponse#getDeveloperMessage()}
     *   <li>{@link ErrorResponse#getMessage()}
     *   <li>{@link ErrorResponse#getStatus()}
     *   <li>{@link ErrorResponse#getTimeStamp()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        ErrorResponse actualErrorResponse = new ErrorResponse();
        HashMap<Object, Object> data = new HashMap<>();
        actualErrorResponse.setData(data);
        actualErrorResponse.setDeveloperMessage("Developer Message");
        actualErrorResponse.setMessage("Not all who wander are lost");
        actualErrorResponse.setStatus(HttpStatus.CONTINUE);
        LocalDateTime timeStamp = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualErrorResponse.setTimeStamp(timeStamp);
        String actualToStringResult = actualErrorResponse.toString();
        Map<?, ?> actualData = actualErrorResponse.getData();
        String actualDeveloperMessage = actualErrorResponse.getDeveloperMessage();
        String actualMessage = actualErrorResponse.getMessage();
        HttpStatus actualStatus = actualErrorResponse.getStatus();

        // Assert that nothing has changed
        assertEquals("Developer Message", actualDeveloperMessage);
        assertEquals("ErrorResponse(timeStamp=1970-01-01T00:00, status=100 CONTINUE, message=Not all who wander are lost,"
                + " developerMessage=Developer Message, data={})", actualToStringResult);
        assertEquals("Not all who wander are lost", actualMessage);
        assertEquals(HttpStatus.CONTINUE, actualStatus);
        assertSame(data, actualData);
        assertSame(timeStamp, actualErrorResponse.getTimeStamp());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link ErrorResponse#ErrorResponse(LocalDateTime, HttpStatus, String, String, Map)}
     *   <li>{@link ErrorResponse#setData(Map)}
     *   <li>{@link ErrorResponse#setDeveloperMessage(String)}
     *   <li>{@link ErrorResponse#setMessage(String)}
     *   <li>{@link ErrorResponse#setStatus(HttpStatus)}
     *   <li>{@link ErrorResponse#setTimeStamp(LocalDateTime)}
     *   <li>{@link ErrorResponse#toString()}
     *   <li>{@link ErrorResponse#getData()}
     *   <li>{@link ErrorResponse#getDeveloperMessage()}
     *   <li>{@link ErrorResponse#getMessage()}
     *   <li>{@link ErrorResponse#getStatus()}
     *   <li>{@link ErrorResponse#getTimeStamp()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        LocalDateTime timeStamp = LocalDate.of(1970, 1, 1).atStartOfDay();

        // Act
        ErrorResponse actualErrorResponse = new ErrorResponse(timeStamp, HttpStatus.CONTINUE, "Not all who wander are lost",
                "Developer Message", new HashMap<>());
        HashMap<Object, Object> data = new HashMap<>();
        actualErrorResponse.setData(data);
        actualErrorResponse.setDeveloperMessage("Developer Message");
        actualErrorResponse.setMessage("Not all who wander are lost");
        actualErrorResponse.setStatus(HttpStatus.CONTINUE);
        LocalDateTime timeStamp2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualErrorResponse.setTimeStamp(timeStamp2);
        String actualToStringResult = actualErrorResponse.toString();
        Map<?, ?> actualData = actualErrorResponse.getData();
        String actualDeveloperMessage = actualErrorResponse.getDeveloperMessage();
        String actualMessage = actualErrorResponse.getMessage();
        HttpStatus actualStatus = actualErrorResponse.getStatus();

        // Assert that nothing has changed
        assertEquals("Developer Message", actualDeveloperMessage);
        assertEquals("ErrorResponse(timeStamp=1970-01-01T00:00, status=100 CONTINUE, message=Not all who wander are lost,"
                + " developerMessage=Developer Message, data={})", actualToStringResult);
        assertEquals("Not all who wander are lost", actualMessage);
        assertEquals(HttpStatus.CONTINUE, actualStatus);
        assertSame(data, actualData);
        assertSame(timeStamp2, actualErrorResponse.getTimeStamp());
    }

    /**
     * Method under test:
     * {@link ErrorResponse#ErrorResponse(ErrorResponse.ErrorResponseBuilder)}
     */
    @Test
    void testNewErrorResponse() {
        // Arrange and Act
        ErrorResponse actualErrorResponse = new ErrorResponse(mock(ErrorResponse.ErrorResponseBuilder.class));

        // Assert
        assertNull(actualErrorResponse.getDeveloperMessage());
        assertNull(actualErrorResponse.getMessage());
        assertNull(actualErrorResponse.getTimeStamp());
        assertNull(actualErrorResponse.getData());
        assertNull(actualErrorResponse.getStatus());
    }
}
