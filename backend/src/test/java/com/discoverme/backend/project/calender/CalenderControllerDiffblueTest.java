package com.discoverme.backend.project.calender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CalenderController.class})
@ExtendWith(SpringExtension.class)
class CalenderControllerDiffblueTest {
    @Autowired
    private CalenderController calenderController;

    @MockBean
    private CalenderService calenderService;

    /**
     * Method under test: {@link CalenderController#getCalenders(Pageable)}
     */
    @Test
    void testGetCalenders() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CalenderRepository calenderRepository = mock(CalenderRepository.class);
        when(calenderRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act
        ResponseEntity<Page<CalenderResponse>> actualCalenders = (new CalenderController(
                new CalenderService(calenderRepository))).getCalenders(null);

        // Assert
        verify(calenderRepository).findAll((Pageable) isNull());
        assertEquals(200, actualCalenders.getStatusCodeValue());
        assertTrue(actualCalenders.getBody().toList().isEmpty());
        assertTrue(actualCalenders.hasBody());
        assertTrue(actualCalenders.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link CalenderController#getCalenders(Pageable)}
     */
    @Test
    void testGetCalenders2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        ArrayList<Calender> content = new ArrayList<>();
        content.add(calender);
        CalenderRepository calenderRepository = mock(CalenderRepository.class);
        when(calenderRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));

        // Act
        ResponseEntity<Page<CalenderResponse>> actualCalenders = (new CalenderController(
                new CalenderService(calenderRepository))).getCalenders(null);

        // Assert
        verify(calenderRepository).findAll((Pageable) isNull());
        assertEquals(1, actualCalenders.getBody().toList().size());
        assertEquals(200, actualCalenders.getStatusCodeValue());
        assertTrue(actualCalenders.hasBody());
        assertTrue(actualCalenders.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link CalenderController#getCalenders(Pageable)}
     */
    @Test
    void testGetCalenders3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(2L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        ArrayList<Calender> content = new ArrayList<>();
        content.add(calender2);
        content.add(calender);
        CalenderRepository calenderRepository = mock(CalenderRepository.class);
        when(calenderRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));

        // Act
        ResponseEntity<Page<CalenderResponse>> actualCalenders = (new CalenderController(
                new CalenderService(calenderRepository))).getCalenders(null);

        // Assert
        verify(calenderRepository).findAll((Pageable) isNull());
        assertEquals(2, actualCalenders.getBody().toList().size());
        assertEquals(200, actualCalenders.getStatusCodeValue());
        assertTrue(actualCalenders.hasBody());
        assertTrue(actualCalenders.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link CalenderController#getCalenders(Pageable)}
     */
    @Test
    void testGetCalenders4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CalenderService calenderService = mock(CalenderService.class);
        when(calenderService.getProjectCalenders(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act
        ResponseEntity<Page<CalenderResponse>> actualCalenders = (new CalenderController(calenderService))
                .getCalenders(null);

        // Assert
        verify(calenderService).getProjectCalenders(isNull());
        assertEquals(200, actualCalenders.getStatusCodeValue());
        assertTrue(actualCalenders.getBody().toList().isEmpty());
        assertTrue(actualCalenders.hasBody());
        assertTrue(actualCalenders.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link CalenderController#getCurrentProjectCalender()}
     */
    @Test
    void testGetCurrentProjectCalender() throws Exception {
        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(calenderService.getProjectCalender()).thenReturn(calender);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/projects/calenders/active");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(calenderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"startDate\":0,\"endDate\":0}"));
    }
}
