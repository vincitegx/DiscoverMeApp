package com.discoverme.backend.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.discoverme.backend.social.Socials;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProjectResponse.class, Long.class, String.class, Double.class, int.class})
@ExtendWith(SpringExtension.class)
class ProjectResponseDiffblueTest {
    @Autowired
    private ProjectResponse projectResponse;

    @MockBean
    private Socials socials;

    /**
     * Method under test: {@link ProjectResponse#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange, Act and Assert
        assertFalse((new ProjectResponse(1L, "https://example.org/example", "Song Uri", "Dr", "Stage Name", new Socials(),
                "Not all who wander are lost", 10.0d, true)).canEqual("Other"));
        assertFalse((new ProjectResponse(1L, "https://example.org/example", "Song Uri", "Dr", "Stage Name",
                mock(Socials.class), "Not all who wander are lost", 10.0d, true)).canEqual("Other"));
    }

    /**
     * Method under test: {@link ProjectResponse#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d)
                .social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        ProjectResponse buildResult2 = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d)
                .social(social2)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        // Act and Assert
        assertTrue(buildResult.canEqual(buildResult2));
    }

    /**
     * Method under test: {@link ProjectResponse#canEqual(Object)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCanEqual3() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       com.discoverme.backend.project.ProjectResponse
        //   See https://diff.blue/R027 to resolve this issue.

        // Arrange and Act
        projectResponse.canEqual("Other");
    }

    /**
     * Method under test: {@link ProjectResponse#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d)
                .social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, null);
    }

    /**
     * Method under test: {@link ProjectResponse#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d)
                .social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, "Different type to ProjectResponse");
    }

    /**
     * Method under test: {@link ProjectResponse#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder.contentUri(Mockito.<String>any())).thenReturn(ProjectResponse.builder());
        ProjectResponse.ProjectResponseBuilder percentOfSupportResult = projectResponseBuilder
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d);

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = percentOfSupportResult.social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        ProjectResponse buildResult2 = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d)
                .social(social2)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link ProjectResponse#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder.id(Mockito.<Long>any())).thenReturn(ProjectResponse.builder());
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder2 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder2.contentUri(Mockito.<String>any())).thenReturn(projectResponseBuilder);
        ProjectResponse.ProjectResponseBuilder percentOfSupportResult = projectResponseBuilder2
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d);

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = percentOfSupportResult.social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        ProjectResponse buildResult2 = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d)
                .social(social2)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link ProjectResponse#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder.id(Mockito.<Long>any())).thenReturn(ProjectResponse.builder());
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder2 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder2.contentUri(Mockito.<String>any())).thenReturn(projectResponseBuilder);
        ProjectResponse.ProjectResponseBuilder percentOfSupportResult = projectResponseBuilder2
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d);

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = percentOfSupportResult.social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        ProjectResponse buildResult2 = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(null)
                .percentOfSupport(10.0d)
                .social(social2)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link ProjectResponse#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder.percentOfSupport(Mockito.<Double>any())).thenReturn(ProjectResponse.builder());
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder2 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder2.id(Mockito.<Long>any())).thenReturn(projectResponseBuilder);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder3 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder3.contentUri(Mockito.<String>any())).thenReturn(projectResponseBuilder2);
        ProjectResponse.ProjectResponseBuilder percentOfSupportResult = projectResponseBuilder3
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d);

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = percentOfSupportResult.social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        ProjectResponse buildResult2 = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(null)
                .percentOfSupport(10.0d)
                .social(social2)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link ProjectResponse#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder.percentOfSupport(Mockito.<Double>any())).thenReturn(ProjectResponse.builder());
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder2 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder2.id(Mockito.<Long>any())).thenReturn(projectResponseBuilder);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder3 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder3.contentUri(Mockito.<String>any())).thenReturn(projectResponseBuilder2);
        ProjectResponse.ProjectResponseBuilder percentOfSupportResult = projectResponseBuilder3
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d);

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = percentOfSupportResult.social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        ProjectResponse buildResult2 = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(null)
                .percentOfSupport(null)
                .social(social2)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link ProjectResponse#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        ProjectResponse.ProjectResponseBuilder builderResult = ProjectResponse.builder();
        builderResult.id(1L);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder.percentOfSupport(Mockito.<Double>any())).thenReturn(builderResult);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder2 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder2.id(Mockito.<Long>any())).thenReturn(projectResponseBuilder);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder3 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder3.contentUri(Mockito.<String>any())).thenReturn(projectResponseBuilder2);
        ProjectResponse.ProjectResponseBuilder percentOfSupportResult = projectResponseBuilder3
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d);

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = percentOfSupportResult.social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        ProjectResponse buildResult2 = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(null)
                .percentOfSupport(10.0d)
                .social(social2)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link ProjectResponse#equals(Object)}
     */
    @Test
    void testEquals9() {
        // Arrange
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder.social(Mockito.<Socials>any())).thenReturn(ProjectResponse.builder());
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder2 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder2.percentOfSupport(Mockito.<Double>any())).thenReturn(projectResponseBuilder);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder3 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder3.id(Mockito.<Long>any())).thenReturn(projectResponseBuilder2);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder4 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder4.contentUri(Mockito.<String>any())).thenReturn(projectResponseBuilder3);
        ProjectResponse.ProjectResponseBuilder percentOfSupportResult = projectResponseBuilder4
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d);

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = percentOfSupportResult.social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        ProjectResponse buildResult2 = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(null)
                .percentOfSupport(null)
                .social(social2)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link ProjectResponse#equals(Object)}
     */
    @Test
    void testEquals10() {
        // Arrange
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder.songTitle(Mockito.<String>any())).thenReturn(ProjectResponse.builder());
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder2 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder2.social(Mockito.<Socials>any())).thenReturn(projectResponseBuilder);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder3 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder3.percentOfSupport(Mockito.<Double>any())).thenReturn(projectResponseBuilder2);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder4 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder4.id(Mockito.<Long>any())).thenReturn(projectResponseBuilder3);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder5 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder5.contentUri(Mockito.<String>any())).thenReturn(projectResponseBuilder4);
        ProjectResponse.ProjectResponseBuilder percentOfSupportResult = projectResponseBuilder5
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d);

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = percentOfSupportResult.social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        ProjectResponse buildResult2 = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(null)
                .percentOfSupport(null)
                .social(social2)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link ProjectResponse#equals(Object)}
     */
    @Test
    void testEquals11() {
        // Arrange
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder.songUri(Mockito.<String>any())).thenReturn(ProjectResponse.builder());
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder2 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder2.songTitle(Mockito.<String>any())).thenReturn(projectResponseBuilder);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder3 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder3.social(Mockito.<Socials>any())).thenReturn(projectResponseBuilder2);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder4 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder4.percentOfSupport(Mockito.<Double>any())).thenReturn(projectResponseBuilder3);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder5 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder5.id(Mockito.<Long>any())).thenReturn(projectResponseBuilder4);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder6 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder6.contentUri(Mockito.<String>any())).thenReturn(projectResponseBuilder5);
        ProjectResponse.ProjectResponseBuilder percentOfSupportResult = projectResponseBuilder6
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d);

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = percentOfSupportResult.social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        ProjectResponse buildResult2 = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(null)
                .percentOfSupport(null)
                .social(social2)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link ProjectResponse#equals(Object)}
     */
    @Test
    void testEquals12() {
        // Arrange
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder.songUri(Mockito.<String>any())).thenReturn(ProjectResponse.builder());
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder2 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder2.songTitle(Mockito.<String>any())).thenReturn(projectResponseBuilder);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder3 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder3.social(Mockito.<Socials>any())).thenReturn(projectResponseBuilder2);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder4 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder4.percentOfSupport(Mockito.<Double>any())).thenReturn(projectResponseBuilder3);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder5 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder5.id(Mockito.<Long>any())).thenReturn(projectResponseBuilder4);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder6 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder6.contentUri(Mockito.<String>any())).thenReturn(projectResponseBuilder5);
        ProjectResponse.ProjectResponseBuilder percentOfSupportResult = projectResponseBuilder6
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d);

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = percentOfSupportResult.social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("Url")
                .build();

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        ProjectResponse buildResult2 = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(null)
                .percentOfSupport(null)
                .social(social2)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link ProjectResponse#equals(Object)}
     */
    @Test
    void testEquals13() {
        // Arrange
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder.songUri(Mockito.<String>any())).thenReturn(ProjectResponse.builder());
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder2 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder2.songTitle(Mockito.<String>any())).thenReturn(projectResponseBuilder);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder3 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder3.social(Mockito.<Socials>any())).thenReturn(projectResponseBuilder2);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder4 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder4.percentOfSupport(Mockito.<Double>any())).thenReturn(projectResponseBuilder3);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder5 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder5.id(Mockito.<Long>any())).thenReturn(projectResponseBuilder4);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder6 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder6.contentUri(Mockito.<String>any())).thenReturn(projectResponseBuilder5);
        ProjectResponse.ProjectResponseBuilder percentOfSupportResult = projectResponseBuilder6
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d);

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = percentOfSupportResult.social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url(null)
                .build();

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        ProjectResponse buildResult2 = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(null)
                .percentOfSupport(null)
                .social(social2)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link ProjectResponse#equals(Object)}
     */
    @Test
    void testEquals14() {
        // Arrange
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder.songUri(Mockito.<String>any())).thenReturn(ProjectResponse.builder());
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder2 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder2.songTitle(Mockito.<String>any())).thenReturn(projectResponseBuilder);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder3 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder3.social(Mockito.<Socials>any())).thenReturn(projectResponseBuilder2);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder4 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder4.percentOfSupport(Mockito.<Double>any())).thenReturn(projectResponseBuilder3);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder5 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder5.id(Mockito.<Long>any())).thenReturn(projectResponseBuilder4);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder6 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder6.contentUri(Mockito.<String>any())).thenReturn(projectResponseBuilder5);
        ProjectResponse.ProjectResponseBuilder percentOfSupportResult = projectResponseBuilder6
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d);

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = percentOfSupportResult.social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        ProjectResponse buildResult2 = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(null)
                .percentOfSupport(null)
                .social(social2)
                .songTitle("Dr")
                .songUri(null)
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link ProjectResponse#equals(Object)}
     */
    @Test
    void testEquals15() {
        // Arrange
        ProjectResponse.ProjectResponseBuilder builderResult = ProjectResponse.builder();
        builderResult.songUri("https://example.org/example");
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder.songUri(Mockito.<String>any())).thenReturn(builderResult);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder2 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder2.songTitle(Mockito.<String>any())).thenReturn(projectResponseBuilder);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder3 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder3.social(Mockito.<Socials>any())).thenReturn(projectResponseBuilder2);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder4 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder4.percentOfSupport(Mockito.<Double>any())).thenReturn(projectResponseBuilder3);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder5 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder5.id(Mockito.<Long>any())).thenReturn(projectResponseBuilder4);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder6 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder6.contentUri(Mockito.<String>any())).thenReturn(projectResponseBuilder5);
        ProjectResponse.ProjectResponseBuilder percentOfSupportResult = projectResponseBuilder6
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d);

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = percentOfSupportResult.social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        ProjectResponse buildResult2 = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(null)
                .percentOfSupport(null)
                .social(social2)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link ProjectResponse#equals(Object)}
     */
    @Test
    void testEquals16() {
        // Arrange
        ProjectResponse.ProjectResponseBuilder builderResult = ProjectResponse.builder();
        builderResult.percentOfSupport(10.0d);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder.songUri(Mockito.<String>any())).thenReturn(builderResult);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder2 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder2.songTitle(Mockito.<String>any())).thenReturn(projectResponseBuilder);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder3 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder3.social(Mockito.<Socials>any())).thenReturn(projectResponseBuilder2);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder4 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder4.percentOfSupport(Mockito.<Double>any())).thenReturn(projectResponseBuilder3);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder5 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder5.id(Mockito.<Long>any())).thenReturn(projectResponseBuilder4);
        ProjectResponse.ProjectResponseBuilder projectResponseBuilder6 = mock(ProjectResponse.ProjectResponseBuilder.class);
        when(projectResponseBuilder6.contentUri(Mockito.<String>any())).thenReturn(projectResponseBuilder5);
        ProjectResponse.ProjectResponseBuilder percentOfSupportResult = projectResponseBuilder6
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d);

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = percentOfSupportResult.social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        ProjectResponse buildResult2 = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(null)
                .percentOfSupport(null)
                .social(social2)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ProjectResponse#equals(Object)}
     *   <li>{@link ProjectResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d)
                .social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
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
     *   <li>{@link ProjectResponse#equals(Object)}
     *   <li>{@link ProjectResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");
        ProjectResponse buildResult = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d)
                .social(social)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
                .build();

        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        ProjectResponse buildResult2 = ProjectResponse.builder()
                .contentUri("Not all who wander are lost")
                .id(1L)
                .percentOfSupport(10.0d)
                .social(social2)
                .songTitle("Dr")
                .songUri("Song Uri")
                .stageName("Stage Name")
                .url("https://example.org/example")
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
     *   <li>
     * {@link ProjectResponse#ProjectResponse(Long, String, String, String, String, Socials, String, Double, boolean)}
     *   <li>{@link ProjectResponse#setContentUri(String)}
     *   <li>{@link ProjectResponse#setId(Long)}
     *   <li>{@link ProjectResponse#setPercentOfSupport(Double)}
     *   <li>{@link ProjectResponse#setSocial(Socials)}
     *   <li>{@link ProjectResponse#setSongTitle(String)}
     *   <li>{@link ProjectResponse#setSongUri(String)}
     *   <li>{@link ProjectResponse#setStageName(String)}
     *   <li>{@link ProjectResponse#setSupported(boolean)}
     *   <li>{@link ProjectResponse#setUrl(String)}
     *   <li>{@link ProjectResponse#toString()}
     *   <li>{@link ProjectResponse#getContentUri()}
     *   <li>{@link ProjectResponse#getId()}
     *   <li>{@link ProjectResponse#getPercentOfSupport()}
     *   <li>{@link ProjectResponse#getSocial()}
     *   <li>{@link ProjectResponse#getSongTitle()}
     *   <li>{@link ProjectResponse#getSongUri()}
     *   <li>{@link ProjectResponse#getStageName()}
     *   <li>{@link ProjectResponse#getUrl()}
     *   <li>{@link ProjectResponse#isSupported()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        // Act
        ProjectResponse actualProjectResponse = new ProjectResponse(1L, "https://example.org/example", "Song Uri", "Dr",
                "Stage Name", social, "Not all who wander are lost", 10.0d, true);
        actualProjectResponse.setContentUri("Not all who wander are lost");
        actualProjectResponse.setId(1L);
        actualProjectResponse.setPercentOfSupport(10.0d);
        Socials social2 = new Socials();
        social2.setId(1L);
        social2.setName("Name");
        actualProjectResponse.setSocial(social2);
        actualProjectResponse.setSongTitle("Dr");
        actualProjectResponse.setSongUri("Song Uri");
        actualProjectResponse.setStageName("Stage Name");
        actualProjectResponse.setSupported(true);
        actualProjectResponse.setUrl("https://example.org/example");
        String actualToStringResult = actualProjectResponse.toString();
        String actualContentUri = actualProjectResponse.getContentUri();
        Long actualId = actualProjectResponse.getId();
        Double actualPercentOfSupport = actualProjectResponse.getPercentOfSupport();
        Socials actualSocial = actualProjectResponse.getSocial();
        String actualSongTitle = actualProjectResponse.getSongTitle();
        String actualSongUri = actualProjectResponse.getSongUri();
        String actualStageName = actualProjectResponse.getStageName();
        String actualUrl = actualProjectResponse.getUrl();
        boolean actualIsSupportedResult = actualProjectResponse.isSupported();

        // Assert that nothing has changed
        assertEquals("Dr", actualSongTitle);
        assertEquals("Not all who wander are lost", actualContentUri);
        assertEquals(
                "ProjectResponse(id=1, url=https://example.org/example, songUri=Song Uri, songTitle=Dr, stageName=Stage"
                        + " Name, social=Socials(id=1, name=Name), contentUri=Not all who wander are lost, percentOfSupport=10.0,"
                        + " isSupported=true)",
                actualToStringResult);
        assertEquals("Song Uri", actualSongUri);
        assertEquals("Stage Name", actualStageName);
        assertEquals("https://example.org/example", actualUrl);
        assertEquals(10.0d, actualPercentOfSupport.doubleValue());
        assertEquals(1L, actualId.longValue());
        assertTrue(actualIsSupportedResult);
        assertEquals(social, actualSocial);
        assertSame(social2, actualSocial);
    }
}
