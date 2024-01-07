package com.discoverme.backend.config;

import com.discoverme.backend.project.calender.CalendarJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

@Configuration
public class QuartzConfig {

//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Bean
//    public AutowiringSpringBeanJobFactory jobFactory() {
//        return new AutowiringSpringBeanJobFactory();
//    }
//
//    @Bean
//    public SchedulerFactoryBean schedulerFactory(AutowiringSpringBeanJobFactory jobFactory, Trigger... triggers) {
//        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//        factory.setOverwriteExistingJobs(true);
//        factory.setJobFactory(jobFactory);
//        factory.setTriggers(triggers);
//        return factory;
//    }
//
//    @Bean
//    public JobDetailFactoryBean calendarJobDetail() {
//        return createJobDetail(CalendarJob.class);
//    }
//
//    @Bean
//    public SimpleTriggerFactoryBean calendarJobTrigger(JobDetail calendarJobDetail) {
//        return createTrigger(calendarJobDetail); // Repeat every week in milliseconds
//    }
//
//    // Add methods for other jobs and triggers here
//
//    private <T extends Job> JobDetailFactoryBean createJobDetail(Class<T> jobClass) {
//        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
//        factoryBean.setJobClass(jobClass);
//        factoryBean.setGroup(jobClass.getSimpleName() + "Group");
//        factoryBean.setName(jobClass.getSimpleName());
//        return factoryBean;
//    }
//
//    private SimpleTriggerFactoryBean createTrigger(JobDetail jobDetail) {
//        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
//        factoryBean.setJobDetail(jobDetail);
//        factoryBean.setRepeatInterval(604800000);
//        factoryBean.setGroup(jobDetail.getKey().getGroup());
//        factoryBean.setName(jobDetail.getKey().getName() + "Trigger");
//        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
//        return factoryBean;
//    }

//    @Bean
//    public JobDetailFactoryBean calendarJobDetail() {
//        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
//        factoryBean.setJobClass(CalendarJob.class);
//        factoryBean.setGroup("calendarJobGroup");
//        factoryBean.setName("calendarJob");
//        return factoryBean;
//    }
//
//    @Bean
//    public SimpleTriggerFactoryBean calendarJobTrigger(JobDetail calendarJobDetail) {
//        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
//        factoryBean.setJobDetail(calendarJobDetail);
//        factoryBean.setRepeatInterval(7 * 24 * 60 * 60 * 1000); // Repeat every week in milliseconds
//        factoryBean.setGroup("calendarJobGroup");
//        factoryBean.setName("calendarJobTrigger");
//        return factoryBean;
//    }

    @Bean
    public JobDetail sampleJobDetail() {
        return JobBuilder.newJob(CalendarJob.class)
                .withIdentity("calendarJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger sampleJobTrigger(JobDetail jobDetail) {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMilliseconds(7 * 24 * 60 * 60 * 1000)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("sampleTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactory(Trigger trigger, JobDetail jobDetail) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setTriggers(trigger);
        schedulerFactory.setJobDetails(jobDetail);
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        schedulerFactory.setJobFactory(jobFactory);
        return schedulerFactory;
    }
}