package com.example.demo.util;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.example.demo.info.TimerInfo;

public class TimeUtil {
	
	public static JobDetail buildJobdetail(final Class<? extends Job> jobClass, final TimerInfo info) {
		final JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put(jobClass.getSimpleName(), info);
		return JobBuilder
				.newJob(jobClass)
				.withIdentity(jobClass.getSimpleName())
				.setJobData(jobDataMap)
				.build();
	}
	
	public static Trigger buidTrigger(final Class<? extends Job> jobClass, final TimerInfo info) {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
													.withIntervalInSeconds((int) info.getRepeatInterval());
		if(info.isRunForever()) {
			scheduleBuilder.repeatForever();
		}else {
			scheduleBuilder.withRepeatCount(info.getTotalFireCount()-1);
		}
		return TriggerBuilder.newTrigger().withIdentity(jobClass.getSimpleName())
				.withSchedule(scheduleBuilder).startAt(new Date(System.currentTimeMillis()+info.getInitialOfset()))
				.build();
		
	}

}
