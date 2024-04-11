package com.discoverme.backend.project.calender;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {Calender.class})
@ExtendWith(SpringExtension.class)
class CalenderDiffblueTest {
    @Autowired
    private Calender calender;

    /**
     * Method under test: {@link Calender#add()}
     */
    @Test
    void testAdd() {
        // Arrange
        Calender.CalenderBuilder calenderBuilder = mock(Calender.CalenderBuilder.class);
        when(calenderBuilder.endDate(Mockito.<Date>any())).thenReturn(Calender.builder());
        Calender.CalenderBuilder idResult = calenderBuilder
                .endDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
                .id(1L);
        Calender buildResult = idResult
                .startDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
                .build();

        // Act
        buildResult.add();

        // Assert
        verify(calenderBuilder).endDate(isA(Date.class));
    }
}
