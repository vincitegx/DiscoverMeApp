package com.discoverme.backend.project.calender;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class CalendarJob implements StatefulJob  {
    private CalenderService calendarService;
    public CalendarJob() {}
    @Autowired
    public void setCalendarService(CalenderService calendarService) {
        this.calendarService = calendarService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        if (calendarService == null) {
            throw new JobExecutionException("CalendarService not properly injected.");
        }
        calendarService.addProjectCalender();
    }
}
