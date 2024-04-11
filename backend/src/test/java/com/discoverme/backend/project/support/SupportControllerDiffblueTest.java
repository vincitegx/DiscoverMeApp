package com.discoverme.backend.project.support;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.discoverme.backend.project.LoggedInUserService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SupportController.class})
@ExtendWith(SpringExtension.class)
class SupportControllerDiffblueTest {
    @MockBean
    private LoggedInUserService loggedInUserService;

    @Autowired
    private SupportController supportController;

    @MockBean
    private SupportService supportService;

    /**
     * Method under test: {@link SupportController#toggleSupport(Long)}
     */
    @Test
    void testToggleSupport() throws Exception {
        // Arrange
        doNothing().when(supportService).toggleSupport(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/project/{projectId}/support",
                1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(supportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link SupportController#getProjectSupporters(Long)}
     */
    @Test
    void testGetProjectSupporters() throws Exception {
        // Arrange
        when(supportService.getProjectSupporters(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/project/support");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("projectId", String.valueOf(1L));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(supportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
