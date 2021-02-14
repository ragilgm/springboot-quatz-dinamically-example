package com.example.demo.timer_service;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.info.TimerInfo;

public class SImpleTriggerListener implements TriggerListener {

	@Autowired
	private final SchedulerServices schedulerServices;

	public SImpleTriggerListener(SchedulerServices schedulerServices) {
		this.schedulerServices = schedulerServices;
	}

	@Override
	public String getName() {
		return SImpleTriggerListener.class.getSimpleName();
	}

	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		final String timerId = trigger.getKey().getName();

		final JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		final TimerInfo timerInfo = (TimerInfo) jobDataMap.get(timerId);

		if (!timerInfo.isRunForever()) {
			int remainingFireCount = timerInfo.getRemainingFireCount();

			if (remainingFireCount == 0) {
				return;
			}

			timerInfo.setRemainingFireCount(remainingFireCount - 1);

		}

		schedulerServices.updateTimer(timerId, timerInfo);

	}

	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void triggerMisfired(Trigger trigger) {
		// TODO Auto-generated method stub

	}

	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context,
			CompletedExecutionInstruction triggerInstructionCode) {
		// TODO Auto-generated method stub

	}

}
