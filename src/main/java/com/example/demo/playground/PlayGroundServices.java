package com.example.demo.playground;

import java.util.List;

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
		timerInfo.setRemainingFireCount(timerInfo.getTotalFireCount());
		timerInfo.setRepeatInterval(20);
		timerInfo.setInitialOfset(1000);
		timerInfo.setRunForever(false);
		timerInfo.setCallbackDate(" ini adalah callback data ");
		schedulerServices.schedule(TestJob.class, timerInfo);
	}
	
	public List<TimerInfo> getAllRunningJobs(){
		return schedulerServices.getAllRunningTimmer();
	}

	public TimerInfo getAllRunningJobsById(String id) {
		return schedulerServices.getAllRunningTimmerById(id);
	}
		
	
	public void deleteTimerId(String timerId) {
		 schedulerServices.deleteTimer(timerId);
	}
	

	
}
