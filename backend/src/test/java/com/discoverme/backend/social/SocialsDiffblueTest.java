package com.discoverme.backend.social;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {Socials.class})
@ExtendWith(SpringExtension.class)
class SocialsDiffblueTest {
    @Autowired
    private Socials socials;

    /**
     * Method under test: {@link Socials#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new Socials()).canEqual("Other"));
    }

    /**
     * Method under test: {@link Socials#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        Socials socials2 = new Socials();

        Socials socials3 = new Socials();
        socials3.setId(1L);
        socials3.setName("Name");

        // Act and Assert
        assertTrue(socials2.canEqual(socials3));
    }

    /**
     * Method under test: {@link Socials#canEqual(Object)}
     */
    @Test
    void testCanEqual3() {
        // Arrange
        Socials.SocialsBuilder socialsBuilder = mock(Socials.SocialsBuilder.class);
        when(socialsBuilder.id(Mockito.<Long>any())).thenReturn(Socials.builder());
        Socials buildResult = socialsBuilder.id(1L).name("Name").build();

        // Act
        boolean actualCanEqualResult = buildResult.canEqual("Other");

        // Assert
        verify(socialsBuilder).id(isA(Long.class));
        assertFalse(actualCanEqualResult);
    }

    /**
     * Method under test: {@link Socials#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        Socials socials = new Socials();
        socials.setId(1L);
        socials.setName("Name");

        // Act and Assert
        assertNotEquals(socials, null);
    }

    /**
     * Method under test: {@link Socials#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        Socials socials = new Socials();
        socials.setId(1L);
        socials.setName("Name");

        // Act and Assert
        assertNotEquals(socials, "Different type to Socials");
    }

    /**
     * Method under test: {@link Socials#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        Socials socials = new Socials();
        socials.setId(2L);
        socials.setName("Name");

        Socials socials2 = new Socials();
        socials2.setId(1L);
        socials2.setName("Name");

        // Act and Assert
        assertNotEquals(socials, socials2);
    }

    /**
     * Method under test: {@link Socials#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        Socials socials = new Socials();
        socials.setId(null);
        socials.setName("Name");

        Socials socials2 = new Socials();
        socials2.setId(1L);
        socials2.setName("Name");

        // Act and Assert
        assertNotEquals(socials, socials2);
    }

    /**
     * Method under test: {@link Socials#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        Socials socials = new Socials();
        socials.setId(1L);
        socials.setName(null);

        Socials socials2 = new Socials();
        socials2.setId(1L);
        socials2.setName("Name");

        // Act and Assert
        assertNotEquals(socials, socials2);
    }

    /**
     * Method under test: {@link Socials#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        Socials socials = new Socials();
        socials.setId(1L);
        socials.setName("com.discoverme.backend.social.Socials");

        Socials socials2 = new Socials();
        socials2.setId(1L);
        socials2.setName("Name");

        // Act and Assert
        assertNotEquals(socials, socials2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Socials#equals(Object)}
     *   <li>{@link Socials#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Socials socials = new Socials();
        socials.setId(1L);
        socials.setName("Name");

        // Act and Assert
        assertEquals(socials, socials);
        int expectedHashCodeResult = socials.hashCode();
        assertEquals(expectedHashCodeResult, socials.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Socials#equals(Object)}
     *   <li>{@link Socials#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        Socials socials = new Socials();
        socials.setId(1L);
        socials.setName("Name");

        Socials socials2 = new Socials();
        socials2.setId(1L);
        socials2.setName("Name");

        // Act and Assert
        assertEquals(socials, socials2);
        int expectedHashCodeResult = socials.hashCode();
        assertEquals(expectedHashCodeResult, socials2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Socials#equals(Object)}
     *   <li>{@link Socials#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode3() {
        // Arrange
        Socials socials = new Socials();
        socials.setId(null);
        socials.setName("Name");

        Socials socials2 = new Socials();
        socials2.setId(null);
        socials2.setName("Name");

        // Act and Assert
        assertEquals(socials, socials2);
        int expectedHashCodeResult = socials.hashCode();
        assertEquals(expectedHashCodeResult, socials2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Socials#equals(Object)}
     *   <li>{@link Socials#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode4() {
        // Arrange
        Socials socials = new Socials();
        socials.setId(1L);
        socials.setName(null);

        Socials socials2 = new Socials();
        socials2.setId(1L);
        socials2.setName(null);

        // Act and Assert
        assertEquals(socials, socials2);
        int expectedHashCodeResult = socials.hashCode();
        assertEquals(expectedHashCodeResult, socials2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Socials#equals(Object)}
     *   <li>{@link Socials#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode5() {
        // Arrange
        Socials.SocialsBuilder socialsBuilder = mock(Socials.SocialsBuilder.class);
        when(socialsBuilder.id(Mockito.<Long>any())).thenReturn(Socials.builder());
        Socials buildResult = socialsBuilder.id(1L).name("Name").build();
        buildResult.setId(1L);
        buildResult.setName("Name");

        Socials socials = new Socials();
        socials.setId(1L);
        socials.setName("Name");

        // Act and Assert
        assertEquals(buildResult, socials);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, socials.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Socials#Socials()}
     *   <li>{@link Socials#setId(Long)}
     *   <li>{@link Socials#setName(String)}
     *   <li>{@link Socials#toString()}
     *   <li>{@link Socials#getId()}
     *   <li>{@link Socials#getName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Socials actualSocials = new Socials();
        actualSocials.setId(1L);
        actualSocials.setName("Name");
        String actualToStringResult = actualSocials.toString();
        Long actualId = actualSocials.getId();

        // Assert that nothing has changed
        assertEquals("Name", actualSocials.getName());
        assertEquals("Socials(id=1, name=Name)", actualToStringResult);
        assertEquals(1L, actualId.longValue());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Socials#Socials(Long, String)}
     *   <li>{@link Socials#setId(Long)}
     *   <li>{@link Socials#setName(String)}
     *   <li>{@link Socials#toString()}
     *   <li>{@link Socials#getId()}
     *   <li>{@link Socials#getName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        Socials actualSocials = new Socials(1L, "Name");
        actualSocials.setId(1L);
        actualSocials.setName("Name");
        String actualToStringResult = actualSocials.toString();
        Long actualId = actualSocials.getId();

        // Assert that nothing has changed
        assertEquals("Name", actualSocials.getName());
        assertEquals("Socials(id=1, name=Name)", actualToStringResult);
        assertEquals(1L, actualId.longValue());
    }
}
