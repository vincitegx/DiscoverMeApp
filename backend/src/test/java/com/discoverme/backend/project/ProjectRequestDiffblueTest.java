package com.discoverme.backend.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.discoverme.backend.social.Socials;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {ProjectRequest.class})
@ExtendWith(SpringExtension.class)
class ProjectRequestDiffblueTest {
    @Autowired
    private ProjectRequest projectRequest;

    /**
     * Method under test: {@link ProjectRequest#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new ProjectRequest()).canEqual("Other"));
    }

    /**
     * Method under test: {@link ProjectRequest#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        ProjectRequest projectRequest2 = new ProjectRequest();

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        ProjectRequest projectRequest3 = new ProjectRequest();
        projectRequest3.setSocial(social);
        projectRequest3.setSongTitle("Dr");
        projectRequest3.setSongUri("Song Uri");

        // Act and Assert
        assertTrue(projectRequest2.canEqual(projectRequest3));
    }

    /**
     * Method under test: {@link ProjectRequest#canEqual(Object)}
     */
    @Test
    void testCanEqual3() {
        // Arrange
        ProjectRequest projectRequest2 = new ProjectRequest();
        Socials social = mock(Socials.class);
        doNothing().when(social).setId(Mockito.<Long>any());
        doNothing().when(social).setName(Mockito.<String>any());
        social.setId(1L);
        social.setName("Name");

        ProjectRequest projectRequest3 = new ProjectRequest();
        projectRequest3.setSocial(social);
        projectRequest3.setSongTitle("Dr");
        projectRequest3.setSongUri("Song Uri");

        // Act
        boolean actualCanEqualResult = projectRequest2.canEqual(projectRequest3);

        // Assert
        verify(social).setId(isA(Long.class));
        verify(social).setName(eq("Name"));
        assertTrue(actualCanEqualResult);
    }

    /**
     * Method under test: {@link ProjectRequest#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setSocial(social);
        projectRequest.setSongTitle("Dr");
        projectRequest.setSongUri("Song Uri");

        // Act and Assert
        assertNotEquals(projectRequest, null);
    }

    /**
     * Method under test: {@link ProjectRequest#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setSocial(social);
        projectRequest.setSongTitle("Dr");
        projectRequest.setSongUri("Song Uri");

        // Act and Assert
        assertNotEquals(projectRequest, "Different type to ProjectRequest");
    }

    /**
     * Method under test: {@link ProjectRequest#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        Socials social = mock(Socials.class);
        doNothing().when(social).setId(Mockito.<Long>any());
        doNothing().when(social).setName(Mockito.<String>any());
        social.setId(1L);
        social.setName("Name");

        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setSocial(social);
        projectRequest.setSongTitle("Dr");
        projectRequest.setSongUri("Song Uri");

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        ProjectRequest projectRequest2 = new ProjectRequest();
        projectRequest2.setSocial(social2);
        projectRequest2.setSongTitle("Dr");
        projectRequest2.setSongUri("Song Uri");

        // Act and Assert
        assertNotEquals(projectRequest, projectRequest2);
    }

    /**
     * Method under test: {@link ProjectRequest#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        Socials social = mock(Socials.class);
        doNothing().when(social).setId(Mockito.<Long>any());
        doNothing().when(social).setName(Mockito.<String>any());
        social.setId(1L);
        social.setName("Name");

        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setSocial(social);
        projectRequest.setSongTitle("Mr");
        projectRequest.setSongUri("Song Uri");

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        ProjectRequest projectRequest2 = new ProjectRequest();
        projectRequest2.setSocial(social2);
        projectRequest2.setSongTitle("Dr");
        projectRequest2.setSongUri("Song Uri");

        // Act and Assert
        assertNotEquals(projectRequest, projectRequest2);
    }

    /**
     * Method under test: {@link ProjectRequest#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        Socials social = mock(Socials.class);
        doNothing().when(social).setId(Mockito.<Long>any());
        doNothing().when(social).setName(Mockito.<String>any());
        social.setId(1L);
        social.setName("Name");

        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setSocial(social);
        projectRequest.setSongTitle(null);
        projectRequest.setSongUri("Song Uri");

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        ProjectRequest projectRequest2 = new ProjectRequest();
        projectRequest2.setSocial(social2);
        projectRequest2.setSongTitle("Dr");
        projectRequest2.setSongUri("Song Uri");

        // Act and Assert
        assertNotEquals(projectRequest, projectRequest2);
    }

    /**
     * Method under test: {@link ProjectRequest#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        Socials social = mock(Socials.class);
        doNothing().when(social).setId(Mockito.<Long>any());
        doNothing().when(social).setName(Mockito.<String>any());
        social.setId(1L);
        social.setName("Name");

        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setSocial(social);
        projectRequest.setSongTitle("Dr");
        projectRequest.setSongUri("Dr");

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        ProjectRequest projectRequest2 = new ProjectRequest();
        projectRequest2.setSocial(social2);
        projectRequest2.setSongTitle("Dr");
        projectRequest2.setSongUri("Song Uri");

        // Act and Assert
        assertNotEquals(projectRequest, projectRequest2);
    }

    /**
     * Method under test: {@link ProjectRequest#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        Socials social = mock(Socials.class);
        doNothing().when(social).setId(Mockito.<Long>any());
        doNothing().when(social).setName(Mockito.<String>any());
        social.setId(1L);
        social.setName("Name");

        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setSocial(social);
        projectRequest.setSongTitle("Dr");
        projectRequest.setSongUri(null);

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        ProjectRequest projectRequest2 = new ProjectRequest();
        projectRequest2.setSocial(social2);
        projectRequest2.setSongTitle("Dr");
        projectRequest2.setSongUri("Song Uri");

        // Act and Assert
        assertNotEquals(projectRequest, projectRequest2);
    }

    /**
     * Method under test: {@link ProjectRequest#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        Socials social = mock(Socials.class);
        doNothing().when(social).setId(Mockito.<Long>any());
        doNothing().when(social).setName(Mockito.<String>any());
        social.setId(1L);
        social.setName("Name");

        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setSocial(social);
        projectRequest.setSongTitle(null);
        projectRequest.setSongUri("Song Uri");

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        ProjectRequest projectRequest2 = new ProjectRequest();
        projectRequest2.setSocial(social2);
        projectRequest2.setSongTitle(null);
        projectRequest2.setSongUri("Song Uri");

        // Act and Assert
        assertNotEquals(projectRequest, projectRequest2);
    }

    /**
     * Method under test: {@link ProjectRequest#equals(Object)}
     */
    @Test
    void testEquals9() {
        // Arrange
        Socials social = mock(Socials.class);
        doNothing().when(social).setId(Mockito.<Long>any());
        doNothing().when(social).setName(Mockito.<String>any());
        social.setId(1L);
        social.setName("Name");

        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setSocial(social);
        projectRequest.setSongTitle("Dr");
        projectRequest.setSongUri(null);

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        ProjectRequest projectRequest2 = new ProjectRequest();
        projectRequest2.setSocial(social2);
        projectRequest2.setSongTitle("Dr");
        projectRequest2.setSongUri(null);

        // Act and Assert
        assertNotEquals(projectRequest, projectRequest2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ProjectRequest#equals(Object)}
     *   <li>{@link ProjectRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setSocial(social);
        projectRequest.setSongTitle("Dr");
        projectRequest.setSongUri("Song Uri");

        // Act and Assert
        assertEquals(projectRequest, projectRequest);
        int expectedHashCodeResult = projectRequest.hashCode();
        assertEquals(expectedHashCodeResult, projectRequest.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ProjectRequest#equals(Object)}
     *   <li>{@link ProjectRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setSocial(social);
        projectRequest.setSongTitle("Dr");
        projectRequest.setSongUri("Song Uri");

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");

        ProjectRequest projectRequest2 = new ProjectRequest();
        projectRequest2.setSocial(social2);
        projectRequest2.setSongTitle("Dr");
        projectRequest2.setSongUri("Song Uri");

        // Act and Assert
        assertEquals(projectRequest, projectRequest2);
        int expectedHashCodeResult = projectRequest.hashCode();
        assertEquals(expectedHashCodeResult, projectRequest2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link ProjectRequest}
     *   <li>{@link ProjectRequest#setSocial(Socials)}
     *   <li>{@link ProjectRequest#setSongTitle(String)}
     *   <li>{@link ProjectRequest#setSongUri(String)}
     *   <li>{@link ProjectRequest#toString()}
     *   <li>{@link ProjectRequest#getSocial()}
     *   <li>{@link ProjectRequest#getSongTitle()}
     *   <li>{@link ProjectRequest#getSongUri()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        ProjectRequest actualProjectRequest = new ProjectRequest();
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        actualProjectRequest.setSocial(social);
        actualProjectRequest.setSongTitle("Dr");
        actualProjectRequest.setSongUri("Song Uri");
        String actualToStringResult = actualProjectRequest.toString();
        Socials actualSocial = actualProjectRequest.getSocial();
        String actualSongTitle = actualProjectRequest.getSongTitle();

        // Assert that nothing has changed
        assertEquals("Dr", actualSongTitle);
        assertEquals("ProjectRequest(songTitle=Dr, songUri=Song Uri, social=Socials(id=1, name=Name))",
                actualToStringResult);
        assertEquals("Song Uri", actualProjectRequest.getSongUri());
        assertSame(social, actualSocial);
    }
}
