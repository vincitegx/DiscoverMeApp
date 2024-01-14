package com.discoverme.backend.config;

import com.discoverme.backend.project.calender.CalendarJob;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;

@Configuration
public class QuartzConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(CalendarJob.class);
        factory.setDurability(true);
        return factory;
    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetail jobDetail) {
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        factory.setJobDetail(jobDetail);
        factory.setCronExpression("0 0 0 * * ?");
        factory.setStartDelay(0);
        return factory;
    }
//    @Bean
//    @Primary
//    public Trigger jobTrigger(JobDetail job) {
//        return TriggerBuilder.newTrigger()
//                .forJob(job)
//                .withIdentity("calendarTrigger")
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24).repeatForever())
//                .startNow()
//                .build();
//    }


    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(Trigger cronTrigger, JobDetail jobDetail, DataSource dataSource) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setConfigLocation(applicationContext.getResource("classpath:application.properties"));
        factory.setJobFactory(springBeanJobFactory());
        factory.setJobDetails(jobDetail);
        factory.setTriggers(cronTrigger);
        factory.setDataSource(dataSource);
        factory.setOverwriteExistingJobs(true);
        factory.setAutoStartup(true);
        return factory;
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        return new SpringBeanJobFactory();
    }
}