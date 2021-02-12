package com.example.demo.timer_service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.info.TimerInfo;
import com.example.demo.util.TimeUtil;


@Service
public class SchedulerServices {
	
	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(SchedulerServices.class);
	
	private final Scheduler scheduler;
	
	@Autowired
	private SchedulerServices(Scheduler scheduler) {
		this.scheduler=scheduler;
	}
	
	public void schedule(final Class jobClass, TimerInfo info) {
		final JobDetail jobDetail =TimeUtil.buildJobdetail(jobClass, info);
		final Trigger trigger = TimeUtil.buidTrigger(jobClass, info);
		
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
	}
	
	
	@PostConstruct
	public void init() {
		try {
			scheduler.start();
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
	}
	
	@PreDestroy
	public void preDestroy() {
		try {
			scheduler.shutdown();
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
	}

}
