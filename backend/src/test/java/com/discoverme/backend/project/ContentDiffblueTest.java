package com.discoverme.backend.project;

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
@ContextConfiguration(classes = {Content.class})
@ExtendWith(SpringExtension.class)
class ContentDiffblueTest {
    @Autowired
    private Content content;

    /**
     * Method under test: {@link Content#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new Content()).canEqual("Other"));
    }

    /**
     * Method under test: {@link Content#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        Content content2 = new Content();

        Content content3 = new Content();
        content3.setId(1L);
        content3.setUri("Uri");

        // Act and Assert
        assertTrue(content2.canEqual(content3));
    }

    /**
     * Method under test: {@link Content#canEqual(Object)}
     */
    @Test
    void testCanEqual3() {
        // Arrange
        Content.ContentBuilder contentBuilder = mock(Content.ContentBuilder.class);
        when(contentBuilder.id(Mockito.<Long>any())).thenReturn(Content.builder());
        Content buildResult = contentBuilder.id(1L).uri("Uri").build();

        // Act
        boolean actualCanEqualResult = buildResult.canEqual("Other");

        // Assert
        verify(contentBuilder).id(isA(Long.class));
        assertFalse(actualCanEqualResult);
    }

    /**
     * Method under test: {@link Content#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        Content content = new Content();
        content.setId(1L);
        content.setUri("Uri");

        // Act and Assert
        assertNotEquals(content, null);
    }

    /**
     * Method under test: {@link Content#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        Content content = new Content();
        content.setId(1L);
        content.setUri("Uri");

        // Act and Assert
        assertNotEquals(content, "Different type to Content");
    }

    /**
     * Method under test: {@link Content#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        Content content = new Content();
        content.setId(2L);
        content.setUri("Uri");

        Content content2 = new Content();
        content2.setId(1L);
        content2.setUri("Uri");

        // Act and Assert
        assertNotEquals(content, content2);
    }

    /**
     * Method under test: {@link Content#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        Content content = new Content();
        content.setId(null);
        content.setUri("Uri");

        Content content2 = new Content();
        content2.setId(1L);
        content2.setUri("Uri");

        // Act and Assert
        assertNotEquals(content, content2);
    }

    /**
     * Method under test: {@link Content#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        Content content = new Content();
        content.setId(1L);
        content.setUri(null);

        Content content2 = new Content();
        content2.setId(1L);
        content2.setUri("Uri");

        // Act and Assert
        assertNotEquals(content, content2);
    }

    /**
     * Method under test: {@link Content#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        Content content = new Content();
        content.setId(1L);
        content.setUri("com.discoverme.backend.project.Content");

        Content content2 = new Content();
        content2.setId(1L);
        content2.setUri("Uri");

        // Act and Assert
        assertNotEquals(content, content2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Content#equals(Object)}
     *   <li>{@link Content#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Content content = new Content();
        content.setId(1L);
        content.setUri("Uri");

        // Act and Assert
        assertEquals(content, content);
        int expectedHashCodeResult = content.hashCode();
        assertEquals(expectedHashCodeResult, content.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Content#equals(Object)}
     *   <li>{@link Content#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        Content content = new Content();
        content.setId(1L);
        content.setUri("Uri");

        Content content2 = new Content();
        content2.setId(1L);
        content2.setUri("Uri");

        // Act and Assert
        assertEquals(content, content2);
        int expectedHashCodeResult = content.hashCode();
        assertEquals(expectedHashCodeResult, content2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Content#equals(Object)}
     *   <li>{@link Content#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode3() {
        // Arrange
        Content content = new Content();
        content.setId(null);
        content.setUri("Uri");

        Content content2 = new Content();
        content2.setId(null);
        content2.setUri("Uri");

        // Act and Assert
        assertEquals(content, content2);
        int expectedHashCodeResult = content.hashCode();
        assertEquals(expectedHashCodeResult, content2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Content#equals(Object)}
     *   <li>{@link Content#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode4() {
        // Arrange
        Content content = new Content();
        content.setId(1L);
        content.setUri(null);

        Content content2 = new Content();
        content2.setId(1L);
        content2.setUri(null);

        // Act and Assert
        assertEquals(content, content2);
        int expectedHashCodeResult = content.hashCode();
        assertEquals(expectedHashCodeResult, content2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Content#equals(Object)}
     *   <li>{@link Content#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode5() {
        // Arrange
        Content.ContentBuilder contentBuilder = mock(Content.ContentBuilder.class);
        when(contentBuilder.id(Mockito.<Long>any())).thenReturn(Content.builder());
        Content buildResult = contentBuilder.id(1L).uri("Uri").build();
        buildResult.setId(1L);
        buildResult.setUri("Uri");

        Content content = new Content();
        content.setId(1L);
        content.setUri("Uri");

        // Act and Assert
        assertEquals(buildResult, content);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, content.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Content#Content()}
     *   <li>{@link Content#setId(Long)}
     *   <li>{@link Content#setUri(String)}
     *   <li>{@link Content#toString()}
     *   <li>{@link Content#getId()}
     *   <li>{@link Content#getUri()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Content actualContent = new Content();
        actualContent.setId(1L);
        actualContent.setUri("Uri");
        String actualToStringResult = actualContent.toString();
        Long actualId = actualContent.getId();

        // Assert that nothing has changed
        assertEquals("Content(id=1, uri=Uri)", actualToStringResult);
        assertEquals("Uri", actualContent.getUri());
        assertEquals(1L, actualId.longValue());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Content#Content(Long, String)}
     *   <li>{@link Content#setId(Long)}
     *   <li>{@link Content#setUri(String)}
     *   <li>{@link Content#toString()}
     *   <li>{@link Content#getId()}
     *   <li>{@link Content#getUri()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        Content actualContent = new Content(1L, "Uri");
        actualContent.setId(1L);
        actualContent.setUri("Uri");
        String actualToStringResult = actualContent.toString();
        Long actualId = actualContent.getId();

        // Assert that nothing has changed
        assertEquals("Content(id=1, uri=Uri)", actualToStringResult);
        assertEquals("Uri", actualContent.getUri());
        assertEquals(1L, actualId.longValue());
    }
}
