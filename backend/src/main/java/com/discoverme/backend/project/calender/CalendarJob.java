package com.discoverme.backend.project.calender;

import org.jetbrains.annotations.NotNull;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;


public class CalendarJob implements Job {
//        extends QuartzJobBean {
    @Autowired
    private CalenderRepository calendarRepository;

//    @Override
//    protected void executeInternal(@NotNull JobExecutionContext jobExecutionContext) {
//        Calender calendarEntity = new Calender();
//        calendarRepository.save(calendarEntity);
//    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Calender calendarEntity = new Calender();
        calendarRepository.save(calendarEntity);
    }
}
