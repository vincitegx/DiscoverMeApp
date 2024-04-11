package com.discoverme.backend.social;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SocialsController.class})
@ExtendWith(SpringExtension.class)
class SocialsControllerDiffblueTest {
    @Autowired
    private SocialsController socialsController;

    @MockBean
    private SocialsService socialsService;

    /**
     * Method under test: {@link SocialsController#getAllSocials()}
     */
    @Test
    void testGetAllSocials() throws Exception {
        // Arrange
        when(socialsService.getAllSocials()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/socials");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(socialsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
