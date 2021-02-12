package com.example.demo.playground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.info.TimerInfo;
import com.example.demo.job.TestJob;
import com.example.demo.timer_service.SchedulerServices;

@Service
public class PlayGroundServices {

	
	@Autowired
	private SchedulerServices schedulerServices;

	public void runTestBuilder() {
		TimerInfo timerInfo = new TimerInfo();
		timerInfo.setTotalFireCount(5);
		timerInfo.setRepeatInterval(2000);
		timerInfo.setInitialOfset(1000);
		timerInfo.setRunForever(true);
		timerInfo.setCallbackDate(" ini adalah callback data ");
		schedulerServices.schedule(TestJob.class, timerInfo);
	}
	

	
}
