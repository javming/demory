package com.demo.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-12-01-13:20
 */
public class MyJob implements Job {
    private static final Logger _log = LoggerFactory.getLogger(MyJob.class);
    public void execute(JobExecutionContext context) throws JobExecutionException {
        _log.info("In SimpleQuartzJob - executing its JOB at "
                + new Date() + " by " + context.getTrigger().getCalendarName());
    }
}
