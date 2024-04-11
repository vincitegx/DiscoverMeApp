package com.discoverme.backend.project.calender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CalenderResponse.class, Date.class})
@ExtendWith(SpringExtension.class)
class CalenderResponseDiffblueTest {
    @Autowired
    private CalenderResponse calenderResponse;

    /**
     * Method under test: {@link CalenderResponse#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act and Assert
        assertFalse((new CalenderResponse(1L, startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))).canEqual("Other"));
    }

    /**
     * Method under test: {@link CalenderResponse#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        CalenderResponse calenderResponse = new CalenderResponse(1L, startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Date startDate2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act and Assert
        assertTrue(calenderResponse.canEqual(new CalenderResponse(1L, startDate2,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))));
    }

    /**
     * Method under test: {@link CalenderResponse#canEqual(Object)}
     */
    @Test
    void testCanEqual3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        java.sql.Date startDate = mock(java.sql.Date.class);

        // Act and Assert
        assertFalse((new CalenderResponse(2L, startDate,
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())))
                .canEqual("Other"));
    }

    /**
     * Method under test: {@link CalenderResponse#canEqual(Object)}
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
        calenderResponse.canEqual("Other");
    }

    /**
     * Method under test: {@link CalenderResponse#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act and Assert
        assertNotEquals(new CalenderResponse(1L, startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())), null);
    }

    /**
     * Method under test: {@link CalenderResponse#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act and Assert
        assertNotEquals(
                new CalenderResponse(1L, startDate,
                        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())),
                "Different type to CalenderResponse");
    }

    /**
     * Method under test: {@link CalenderResponse#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        CalenderResponse calenderResponse = new CalenderResponse(2L, startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Date startDate2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act and Assert
        assertNotEquals(calenderResponse, new CalenderResponse(1L, startDate2,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
    }

    /**
     * Method under test: {@link CalenderResponse#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        CalenderResponse calenderResponse = new CalenderResponse(null, startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Date startDate2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act and Assert
        assertNotEquals(calenderResponse, new CalenderResponse(1L, startDate2,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
    }

    /**
     * Method under test: {@link CalenderResponse#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        Date startDate = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        CalenderResponse calenderResponse = new CalenderResponse(1L, startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Date startDate2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act and Assert
        assertNotEquals(calenderResponse, new CalenderResponse(1L, startDate2,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
    }

    /**
     * Method under test: {@link CalenderResponse#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        CalenderResponse calenderResponse = new CalenderResponse(1L, null,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act and Assert
        assertNotEquals(calenderResponse, new CalenderResponse(1L, startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
    }

    /**
     * Method under test: {@link CalenderResponse#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        CalenderResponse calenderResponse = new CalenderResponse(1L, startDate,
                Date.from(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Date startDate2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act and Assert
        assertNotEquals(calenderResponse, new CalenderResponse(1L, startDate2,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
    }

    /**
     * Method under test: {@link CalenderResponse#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        CalenderResponse calenderResponse = new CalenderResponse(1L,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), null);
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act and Assert
        assertNotEquals(calenderResponse, new CalenderResponse(1L, startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
    }

    /**
     * Method under test: {@link CalenderResponse#equals(Object)}
     */
    @Test
    void testEquals9() {
        // Arrange
        java.sql.Date startDate = mock(java.sql.Date.class);
        CalenderResponse calenderResponse = new CalenderResponse(3L, startDate,
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        java.util.Date startDate2 = java.util.Date
                .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act and Assert
        assertNotEquals(calenderResponse, new CalenderResponse(1L, startDate2,
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CalenderResponse#equals(Object)}
     *   <li>{@link CalenderResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        CalenderResponse calenderResponse = new CalenderResponse(1L, startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        // Act and Assert
        assertEquals(calenderResponse, calenderResponse);
        int expectedHashCodeResult = calenderResponse.hashCode();
        assertEquals(expectedHashCodeResult, calenderResponse.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CalenderResponse#equals(Object)}
     *   <li>{@link CalenderResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        CalenderResponse calenderResponse = new CalenderResponse(1L, startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Date startDate2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        CalenderResponse calenderResponse2 = new CalenderResponse(1L, startDate2,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        // Act and Assert
        assertEquals(calenderResponse, calenderResponse2);
        int expectedHashCodeResult = calenderResponse.hashCode();
        assertEquals(expectedHashCodeResult, calenderResponse2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CalenderResponse#equals(Object)}
     *   <li>{@link CalenderResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode3() {
        // Arrange
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        CalenderResponse calenderResponse = new CalenderResponse(null, startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Date startDate2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        CalenderResponse calenderResponse2 = new CalenderResponse(null, startDate2,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        // Act and Assert
        assertEquals(calenderResponse, calenderResponse2);
        int expectedHashCodeResult = calenderResponse.hashCode();
        assertEquals(expectedHashCodeResult, calenderResponse2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CalenderResponse#equals(Object)}
     *   <li>{@link CalenderResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode4() {
        // Arrange
        CalenderResponse calenderResponse = new CalenderResponse(1L, null,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        CalenderResponse calenderResponse2 = new CalenderResponse(1L, null,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        // Act and Assert
        assertEquals(calenderResponse, calenderResponse2);
        int expectedHashCodeResult = calenderResponse.hashCode();
        assertEquals(expectedHashCodeResult, calenderResponse2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CalenderResponse#equals(Object)}
     *   <li>{@link CalenderResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode5() {
        // Arrange
        CalenderResponse calenderResponse = new CalenderResponse(1L,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), null);
        CalenderResponse calenderResponse2 = new CalenderResponse(1L,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), null);

        // Act and Assert
        assertEquals(calenderResponse, calenderResponse2);
        int expectedHashCodeResult = calenderResponse.hashCode();
        assertEquals(expectedHashCodeResult, calenderResponse2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CalenderResponse#CalenderResponse(Long, Date, Date)}
     *   <li>{@link CalenderResponse#setEndDate(Date)}
     *   <li>{@link CalenderResponse#setId(Long)}
     *   <li>{@link CalenderResponse#setStartDate(Date)}
     *   <li>{@link CalenderResponse#toString()}
     *   <li>{@link CalenderResponse#getEndDate()}
     *   <li>{@link CalenderResponse#getId()}
     *   <li>{@link CalenderResponse#getStartDate()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act
        CalenderResponse actualCalenderResponse = new CalenderResponse(1L, startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Date endDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCalenderResponse.setEndDate(endDate);
        actualCalenderResponse.setId(1L);
        Date startDate2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCalenderResponse.setStartDate(startDate2);
        actualCalenderResponse.toString();
        Date actualEndDate = actualCalenderResponse.getEndDate();
        Long actualId = actualCalenderResponse.getId();
        Date actualStartDate = actualCalenderResponse.getStartDate();

        // Assert that nothing has changed
        assertEquals(1L, actualId.longValue());
        assertSame(endDate, actualEndDate);
        assertSame(startDate2, actualStartDate);
    }
}
