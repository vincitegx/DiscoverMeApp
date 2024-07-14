package com.discoverme.backend.config;

import de.chandre.quartz.spring.AutowiringSpringBeanJobFactory;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.listeners.SchedulerListenerSupport;
import org.quartz.spi.JobFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(springBeanJobFactory());
        factory.setOverwriteExistingJobs(true);
        factory.setStartupDelay(10);
        factory.setApplicationContextSchedulerContextKey("applicationContext");
        return factory;
    }

    @Bean
    public JobFactory springBeanJobFactory() {
        return new AutowiringSpringBeanJobFactory();
    }

    @Bean
    public SchedulerListener schedulerListener() {
        return new SchedulerListenerSupport() {
            @Override
            public void schedulerStarted() {
                try {
                    Scheduler scheduler = schedulerFactoryBean().getScheduler();
                    for (String groupName : scheduler.getJobGroupNames()) {
                        for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                            if (jobDetail.getJobDataMap().containsKey("projectId") &&
                                    jobDetail.getJobDataMap().getString("projectId") == null) {
                                scheduler.deleteJob(jobKey);
                            }
                        }
                    }
                } catch (SchedulerException e) {
                    e.printStackTrace();
                    System.err.println("Error in scheduler listener");
                }
            }
        };
    }
}