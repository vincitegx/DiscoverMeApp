package com.discoverme.backend.config;

import com.discoverme.backend.project.calender.CalendarJob;
import jakarta.annotation.PostConstruct;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.discoverme.backend.project.calender.CalenderRepository;

@Component
public class QuartzJobInitializer {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private CalenderRepository calendarRepository;

    @PostConstruct
    public void initialize() throws SchedulerException {
        if (calendarRepository.count() == 0) {
            JobDetail jobDetail = JobBuilder.newJob(CalendarJob.class)
                    .withIdentity("calendarJob", "DEFAULT")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(TriggerKey.triggerKey("calendarTrigger", "DEFAULT"))
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInHours(24)
                            .repeatForever())
                    .build();

            if (!scheduler.checkExists(new JobKey("calendarJob", "DEFAULT"))) {
                scheduler.scheduleJob(jobDetail, trigger);
            }

            // Ensure there is at least one calendar entry in the database on startup
            if (calendarRepository.count() == 0) {
                scheduler.triggerJob(new JobKey("calendarJob", "DEFAULT"));
            }
        }
    }
}

