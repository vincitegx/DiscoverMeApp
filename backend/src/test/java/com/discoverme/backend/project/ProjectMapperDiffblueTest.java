package com.discoverme.backend.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.discoverme.backend.social.Socials;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProjectMapper.class})
@ExtendWith(SpringExtension.class)
class ProjectMapperDiffblueTest {
    @Autowired
    private ProjectMapper projectMapper;

    /**
     * Method under test: {@link ProjectMapper#getProjectRequestJson(String)}
     */
    @Test
    void testGetProjectRequestJson() throws JsonProcessingException {
        // Arrange
        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setSocial(social);
        projectRequest.setSongTitle("Dr");
        projectRequest.setSongUri("Song Uri");

        // Act
        ProjectRequest actualProjectRequestJson = projectMapper
                .getProjectRequestJson((new ObjectMapper()).writeValueAsString(projectRequest));

        // Assert
        assertEquals("Dr", actualProjectRequestJson.getSongTitle());
        Socials social2 = actualProjectRequestJson.getSocial();
        assertEquals("Name", social2.getName());
        assertEquals("Song Uri", actualProjectRequestJson.getSongUri());
        assertEquals(1L, social2.getId().longValue());
        assertEquals(social, social2);
    }
}
