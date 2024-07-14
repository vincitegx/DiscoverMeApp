package com.discoverme.backend.config;

import com.discoverme.backend.project.ProjectProcessingJob;
import com.discoverme.backend.project.calender.CalendarJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    @Bean
    public JobDetail projectProcessingJobDetail() {
        return JobBuilder.newJob(ProjectProcessingJob.class)
                .withIdentity("projectProcessingJob")
                .storeDurably()
                .build();
    }
}

