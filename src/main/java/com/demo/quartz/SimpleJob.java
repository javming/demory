package com.demo.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-12-01-17:05
 */
public class SimpleJob implements Job{
    private static final Logger _log = LoggerFactory.getLogger(SimpleJob.class);
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        _log.info("SimpleJob "+key+" says that it is execute at "+new Date());
    }
}
