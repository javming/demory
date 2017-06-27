package com.demo.quartz;


import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-12-01-13:38
 */
public class SimpleTriggerRunner {
    private static SchedulerFactory factory = new StdSchedulerFactory();
    private static Scheduler scheduler;

    static {
        try {
            scheduler = factory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SchedulerException {

        JobDetail job = newJob(MyJob.class).withIdentity("job1","group1").build();
        Trigger trigger = newTrigger().withIdentity("trigger1","group1").startNow().
                withSchedule(simpleSchedule().withIntervalInSeconds(40).repeatForever()).build();
        scheduler.scheduleJob(job,trigger);
        scheduler.start();
    }
    @Test
    public void testCron() throws SchedulerException {
        JobDetail job = newJob(SimpleJob.class).withIdentity("job1","group1").build();
        Trigger trigger = newTrigger().withIdentity("trigger1","group1").startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?")).build();
        scheduler.scheduleJob(job,trigger);
        scheduler.start();
    }
}
