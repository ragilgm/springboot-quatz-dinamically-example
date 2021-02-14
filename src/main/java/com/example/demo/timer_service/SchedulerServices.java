package com.example.demo.timer_service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
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
		this.scheduler = scheduler;
	}

	public void schedule(final Class<? extends Job> jobClass, TimerInfo info) {
		final JobDetail jobDetail = TimeUtil.buildJobdetail(jobClass, info);
		final Trigger trigger = TimeUtil.buidTrigger(jobClass, info);
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public List<TimerInfo> getAllRunningTimmer() {
		try {
			List<TimerInfo> timeInfo = scheduler.getJobKeys(GroupMatcher.anyGroup()).stream().map(jobKey -> {
				try {
					LOG.info(jobKey.getName());
					JobDetail jobDetail = scheduler.getJobDetail(jobKey);
					return (TimerInfo) jobDetail.getJobDataMap().get(jobKey.getName());
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
					return null;
				}
			}).filter(Objects::nonNull).collect(Collectors.toList());
			System.out.println(timeInfo);
			return timeInfo;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return Collections.emptyList();
		}
	}

	public TimerInfo getAllRunningTimmerById(String id) {
		try { 
			final JobDetail jobDetail = scheduler.getJobDetail(new JobKey(id));	
			return (TimerInfo) jobDetail.getJobDataMap().get(id);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	@PostConstruct
	public void init() {
		try {
			scheduler.start();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@PreDestroy
	public void preDestroy() {
		try {
			scheduler.shutdown();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
