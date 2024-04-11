package com.discoverme.backend.project.calender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discoverme.backend.project.ProjectException;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CalenderService.class})
@ExtendWith(SpringExtension.class)
class CalenderServiceDiffblueTest {
    @MockBean
    private CalenderRepository calenderRepository;

    @Autowired
    private CalenderService calenderService;

    /**
     * Method under test: {@link CalenderService#getProjectCalender()}
     */
    @Test
    void testGetProjectCalender() {
        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Optional<Calender> ofResult = Optional.of(calender);
        when(calenderRepository.findFirstByOrderByIdDesc()).thenReturn(ofResult);

        // Act
        Calender actualProjectCalender = calenderService.getProjectCalender();

        // Assert
        verify(calenderRepository).findFirstByOrderByIdDesc();
        assertSame(calender, actualProjectCalender);
    }

    /**
     * Method under test: {@link CalenderService#getProjectCalender()}
     */
    @Test
    void testGetProjectCalender2() {
        // Arrange
        Optional<Calender> emptyResult = Optional.empty();
        when(calenderRepository.findFirstByOrderByIdDesc()).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ProjectException.class, () -> calenderService.getProjectCalender());
        verify(calenderRepository).findFirstByOrderByIdDesc();
    }

    /**
     * Method under test: {@link CalenderService#getProjectCalender()}
     */
    @Test
    void testGetProjectCalender3() {
        // Arrange
        when(calenderRepository.findFirstByOrderByIdDesc()).thenThrow(new ProjectException("An error occurred"));

        // Act and Assert
        assertThrows(ProjectException.class, () -> calenderService.getProjectCalender());
        verify(calenderRepository).findFirstByOrderByIdDesc();
    }

    /**
     * Method under test: {@link CalenderService#findById(Long)}
     */
    @Test
    void testFindById() {
        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Optional<Calender> ofResult = Optional.of(calender);
        when(calenderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Calender actualFindByIdResult = calenderService.findById(1L);

        // Assert
        verify(calenderRepository).findById(isA(Long.class));
        assertSame(calender, actualFindByIdResult);
    }

    /**
     * Method under test: {@link CalenderService#findById(Long)}
     */
    @Test
    void testFindById2() {
        // Arrange
        Optional<Calender> emptyResult = Optional.empty();
        when(calenderRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ProjectException.class, () -> calenderService.findById(1L));
        verify(calenderRepository).findById(isA(Long.class));
    }

    /**
     * Method under test: {@link CalenderService#findById(Long)}
     */
    @Test
    void testFindById3() {
        // Arrange
        when(calenderRepository.findById(Mockito.<Long>any())).thenThrow(new ProjectException("An error occurred"));

        // Act and Assert
        assertThrows(ProjectException.class, () -> calenderService.findById(1L));
        verify(calenderRepository).findById(isA(Long.class));
    }

    /**
     * Method under test: {@link CalenderService#getProjectCalenders(Pageable)}
     */
    @Test
    void testGetProjectCalenders() {
        // Arrange
        when(calenderRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act
        Page<CalenderResponse> actualProjectCalenders = calenderService.getProjectCalenders(null);

        // Assert
        verify(calenderRepository).findAll((Pageable) isNull());
        assertTrue(actualProjectCalenders.toList().isEmpty());
    }

    /**
     * Method under test: {@link CalenderService#getProjectCalenders(Pageable)}
     */
    @Test
    void testGetProjectCalenders2() {
        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        ArrayList<Calender> content = new ArrayList<>();
        content.add(calender);
        PageImpl<Calender> pageImpl = new PageImpl<>(content);
        when(calenderRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);

        // Act
        Page<CalenderResponse> actualProjectCalenders = calenderService.getProjectCalenders(null);

        // Assert
        verify(calenderRepository).findAll((Pageable) isNull());
        assertEquals(1, actualProjectCalenders.toList().size());
    }

    /**
     * Method under test: {@link CalenderService#getProjectCalenders(Pageable)}
     */
    @Test
    void testGetProjectCalenders3() {
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
        PageImpl<Calender> pageImpl = new PageImpl<>(content);
        when(calenderRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);

        // Act
        Page<CalenderResponse> actualProjectCalenders = calenderService.getProjectCalenders(null);

        // Assert
        verify(calenderRepository).findAll((Pageable) isNull());
        assertEquals(2, actualProjectCalenders.toList().size());
    }

    /**
     * Method under test: {@link CalenderService#getProjectCalenders(Pageable)}
     */
    @Test
    void testGetProjectCalenders4() {
        // Arrange
        when(calenderRepository.findAll(Mockito.<Pageable>any())).thenThrow(new ProjectException("An error occurred"));

        // Act and Assert
        assertThrows(ProjectException.class, () -> calenderService.getProjectCalenders(null));
        verify(calenderRepository).findAll((Pageable) isNull());
    }
}
