package com.discoverme.backend.config;

import com.discoverme.backend.project.ProjectProcessingJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobSchedulerService {

    @Autowired
    private Scheduler scheduler;

    public void scheduleProjectProcessingJob(Long projectId, String contentFilePath) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(ProjectProcessingJob.class)
                .withIdentity("projectProcessingJob_" + projectId, "projectProcessing")
                .usingJobData("projectId", projectId.toString())
                .usingJobData("contentFilePath", contentFilePath)
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("projectProcessingTrigger_" + projectId, "projectProcessing")
                .startNow()
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
