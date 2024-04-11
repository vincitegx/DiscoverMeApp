package com.discoverme.backend.mail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EventDto.class})
@ExtendWith(SpringExtension.class)
class EventDtoDiffblueTest {
    @Autowired
    private EventDto eventDto;

    /**
     * Method under test: {@link EventDto#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse(eventDto.canEqual("Other"));
        assertTrue(eventDto.canEqual(eventDto));
        assertTrue(eventDto.canEqual(new EventDto(mock(EventDto.EventDtoBuilder.class))));
    }

    /**
     * Method under test: {@link EventDto#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange, Act and Assert
        assertNotEquals(new EventDto(), null);
        assertNotEquals(new EventDto(), "Different type to EventDto");
    }

    /**
     * Method under test: {@link EventDto#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        EventDto eventDto = new EventDto("alice.liddell@example.org", "jane.doe@example.org", new HashMap<>());

        // Act and Assert
        assertNotEquals(eventDto, new EventDto());
    }

    /**
     * Method under test: {@link EventDto#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        EventDto eventDto = new EventDto();

        // Act and Assert
        assertNotEquals(eventDto, new EventDto("alice.liddell@example.org", "jane.doe@example.org", new HashMap<>()));
    }

    /**
     * Method under test: {@link EventDto#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        EventDto eventDto = new EventDto();
        eventDto.setFrom("jane.doe@example.org");

        // Act and Assert
        assertNotEquals(eventDto, new EventDto());
    }

    /**
     * Method under test: {@link EventDto#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        EventDto eventDto = new EventDto();
        eventDto.setData(new HashMap<>());

        // Act and Assert
        assertNotEquals(eventDto, new EventDto());
    }

    /**
     * Method under test: {@link EventDto#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        EventDto eventDto = new EventDto();

        EventDto eventDto2 = new EventDto();
        eventDto2.setFrom("jane.doe@example.org");

        // Act and Assert
        assertNotEquals(eventDto, eventDto2);
    }

    /**
     * Method under test: {@link EventDto#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        EventDto eventDto = new EventDto();

        EventDto eventDto2 = new EventDto();
        eventDto2.setData(new HashMap<>());

        // Act and Assert
        assertNotEquals(eventDto, eventDto2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EventDto#equals(Object)}
     *   <li>{@link EventDto#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        EventDto eventDto = new EventDto();

        // Act and Assert
        assertEquals(eventDto, eventDto);
        int expectedHashCodeResult = eventDto.hashCode();
        assertEquals(expectedHashCodeResult, eventDto.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EventDto#equals(Object)}
     *   <li>{@link EventDto#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        EventDto eventDto = new EventDto();
        EventDto eventDto2 = new EventDto();

        // Act and Assert
        assertEquals(eventDto, eventDto2);
        int expectedHashCodeResult = eventDto.hashCode();
        assertEquals(expectedHashCodeResult, eventDto2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EventDto#equals(Object)}
     *   <li>{@link EventDto#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode3() {
        // Arrange
        EventDto eventDto = new EventDto("alice.liddell@example.org", "jane.doe@example.org", new HashMap<>());
        EventDto eventDto2 = new EventDto("alice.liddell@example.org", "jane.doe@example.org", new HashMap<>());

        // Act and Assert
        assertEquals(eventDto, eventDto2);
        int expectedHashCodeResult = eventDto.hashCode();
        assertEquals(expectedHashCodeResult, eventDto2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EventDto#equals(Object)}
     *   <li>{@link EventDto#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode4() {
        // Arrange
        EventDto eventDto = new EventDto(mock(EventDto.EventDtoBuilder.class));
        EventDto eventDto2 = new EventDto();

        // Act and Assert
        assertEquals(eventDto, eventDto2);
        int expectedHashCodeResult = eventDto.hashCode();
        assertEquals(expectedHashCodeResult, eventDto2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EventDto#EventDto()}
     *   <li>{@link EventDto#setData(Map)}
     *   <li>{@link EventDto#setFrom(String)}
     *   <li>{@link EventDto#setTo(String)}
     *   <li>{@link EventDto#toString()}
     *   <li>{@link EventDto#getData()}
     *   <li>{@link EventDto#getFrom()}
     *   <li>{@link EventDto#getTo()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        EventDto actualEventDto = new EventDto();
        HashMap<String, String> data = new HashMap<>();
        actualEventDto.setData(data);
        actualEventDto.setFrom("jane.doe@example.org");
        actualEventDto.setTo("alice.liddell@example.org");
        String actualToStringResult = actualEventDto.toString();
        Map<String, String> actualData = actualEventDto.getData();
        String actualFrom = actualEventDto.getFrom();

        // Assert that nothing has changed
        assertEquals("EventDto(to=alice.liddell@example.org, from=jane.doe@example.org, data={})", actualToStringResult);
        assertEquals("alice.liddell@example.org", actualEventDto.getTo());
        assertEquals("jane.doe@example.org", actualFrom);
        assertSame(data, actualData);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EventDto#EventDto(String, String, Map)}
     *   <li>{@link EventDto#setData(Map)}
     *   <li>{@link EventDto#setFrom(String)}
     *   <li>{@link EventDto#setTo(String)}
     *   <li>{@link EventDto#toString()}
     *   <li>{@link EventDto#getData()}
     *   <li>{@link EventDto#getFrom()}
     *   <li>{@link EventDto#getTo()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        EventDto actualEventDto = new EventDto("alice.liddell@example.org", "jane.doe@example.org", new HashMap<>());
        HashMap<String, String> data = new HashMap<>();
        actualEventDto.setData(data);
        actualEventDto.setFrom("jane.doe@example.org");
        actualEventDto.setTo("alice.liddell@example.org");
        String actualToStringResult = actualEventDto.toString();
        Map<String, String> actualData = actualEventDto.getData();
        String actualFrom = actualEventDto.getFrom();

        // Assert that nothing has changed
        assertEquals("EventDto(to=alice.liddell@example.org, from=jane.doe@example.org, data={})", actualToStringResult);
        assertEquals("alice.liddell@example.org", actualEventDto.getTo());
        assertEquals("jane.doe@example.org", actualFrom);
        assertSame(data, actualData);
    }

    /**
     * Method under test: {@link EventDto#EventDto(EventDto.EventDtoBuilder)}
     */
    @Test
    void testNewEventDto() {
        // Arrange and Act
        EventDto actualEventDto = new EventDto(mock(EventDto.EventDtoBuilder.class));

        // Assert
        assertNull(actualEventDto.getFrom());
        assertNull(actualEventDto.getTo());
        assertNull(actualEventDto.getData());
    }
}
