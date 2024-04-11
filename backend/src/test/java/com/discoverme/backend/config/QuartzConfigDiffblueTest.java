package com.discoverme.backend.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CalendarIntervalTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean$$SpringCGLIB$$0;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean$$SpringCGLIB$$0;
import org.springframework.scheduling.quartz.SchedulerFactoryBean$$SpringCGLIB$$0;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {QuartzConfig.class})
@ExtendWith(SpringExtension.class)
class QuartzConfigDiffblueTest {
    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private DataSource dataSource;

    @Autowired
    private QuartzConfig quartzConfig;

    /**
     * Method under test: {@link QuartzConfig#jobDetailFactoryBean()}
     */
    @Test
    void testJobDetailFactoryBean() {
        // Arrange, Act and Assert
        assertTrue(quartzConfig.jobDetailFactoryBean() instanceof JobDetailFactoryBean$$SpringCGLIB$$0);
    }

    /**
     * Method under test: {@link QuartzConfig#cronTriggerFactoryBean(JobDetail)}
     */
    @Test
    void testCronTriggerFactoryBean() {
        // Arrange, Act and Assert
        assertTrue(
                quartzConfig.cronTriggerFactoryBean(new JobDetailImpl()) instanceof CronTriggerFactoryBean$$SpringCGLIB$$0);
        assertTrue(
                quartzConfig.cronTriggerFactoryBean(mock(JobDetail.class)) instanceof CronTriggerFactoryBean$$SpringCGLIB$$0);
    }

    /**
     * Method under test:
     * {@link QuartzConfig#schedulerFactoryBean(Trigger, JobDetail, DataSource)}
     */
    @Test
    void testSchedulerFactoryBean() {
        // Arrange
        CalendarIntervalTriggerImpl cronTrigger = new CalendarIntervalTriggerImpl();

        // Act and Assert
        assertTrue(quartzConfig.schedulerFactoryBean(cronTrigger, new JobDetailImpl(),
                mock(DataSource.class)) instanceof SchedulerFactoryBean$$SpringCGLIB$$0);
    }

    /**
     * Method under test: {@link QuartzConfig#springBeanJobFactory()}
     */
    @Test
    void testSpringBeanJobFactory() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        QuartzConfig quartzConfig = new QuartzConfig();

        // Act
        quartzConfig.springBeanJobFactory();

        // Assert
        JobDetailFactoryBean jobDetailFactoryBeanResult = quartzConfig.jobDetailFactoryBean();
        assertTrue(jobDetailFactoryBeanResult.getJobDataMap().isEmpty());
        assertTrue(jobDetailFactoryBeanResult.isSingleton());
        Class<JobDetail> expectedObjectType = JobDetail.class;
        assertEquals(expectedObjectType, jobDetailFactoryBeanResult.getObjectType());
    }

    /**
     * Method under test: {@link QuartzConfig#springBeanJobFactory()}
     */
    @Test
    void testSpringBeanJobFactory2() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     SpringBeanJobFactory.applicationContext
        //     SpringBeanJobFactory.ignoredUnknownProperties
        //     SpringBeanJobFactory.schedulerContext

        // Arrange and Act
        quartzConfig.springBeanJobFactory();
    }
}
