package com.example.demo.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.example.demo.info.TimerInfo;



@Component
public class TestJob implements Job{
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TestJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		TimerInfo info = (TimerInfo) jobDataMap.get(TestJob.class.getSimpleName());
		
		logger.info("Remaining Fire count is : "+info.getRemainingFireCount());
		logger.info("Hello world");
		
		
	}

}
