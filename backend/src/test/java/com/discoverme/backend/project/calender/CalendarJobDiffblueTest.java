package com.discoverme.backend.project.calender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.quartz.ee.jmx.jboss.JBoss4RMIRemoteMBeanScheduler;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.JobExecutionContextImpl;
import org.quartz.impl.calendar.AnnualCalendar;
import org.quartz.impl.triggers.CalendarIntervalTriggerImpl;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CalendarJob.class})
@ExtendWith(SpringExtension.class)
class CalendarJobDiffblueTest {
    @Autowired
    private CalendarJob calendarJob;

    @MockBean
    private CalenderRepository calenderRepository;

    /**
     * Method under test: {@link CalendarJob#executeInternal(JobExecutionContext)}
     */
    @Test
    void testExecuteInternal() throws SchedulerException {
        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(calenderRepository.save(Mockito.<Calender>any())).thenReturn(calender);
        JBoss4RMIRemoteMBeanScheduler scheduler = new JBoss4RMIRemoteMBeanScheduler();
        JobDetailImpl job = new JobDetailImpl();
        CalendarIntervalTriggerImpl trigger = new CalendarIntervalTriggerImpl();
        AnnualCalendar cal = new AnnualCalendar();
        Date fireTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date scheduledFireTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date prevFireTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date nextFireTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        JobExecutionContextImpl jobExecutionContext = new JobExecutionContextImpl(scheduler,
                new TriggerFiredBundle(job, trigger, cal, true, fireTime, scheduledFireTime, prevFireTime, nextFireTime),
                mock(Job.class));

        // Act
        calendarJob.executeInternal(jobExecutionContext);

        // Assert that nothing has changed
        verify(calenderRepository).save(isA(Calender.class));
        assertEquals(-1L, jobExecutionContext.getJobRunTime());
        assertEquals(0, jobExecutionContext.getRefireCount());
        assertTrue(jobExecutionContext.isRecovering());
        assertTrue(jobExecutionContext.getMergedJobDataMap().isEmpty());
        assertSame(scheduler, jobExecutionContext.getScheduler());
        assertSame(job, jobExecutionContext.getJobDetail());
        assertSame(cal, jobExecutionContext.getCalendar());
        assertSame(fireTime, jobExecutionContext.getFireTime());
        assertSame(nextFireTime, jobExecutionContext.getNextFireTime());
        assertSame(prevFireTime, jobExecutionContext.getPreviousFireTime());
        assertSame(scheduledFireTime, jobExecutionContext.getScheduledFireTime());
    }
}
