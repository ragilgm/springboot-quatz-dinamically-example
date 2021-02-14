package com.example.demo.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.info.TimerInfo;
import com.example.demo.playground.PlayGroundServices;

@RestController
public class PlaygroundController {
	
	
	@Autowired
	private PlayGroundServices playGroundServices;
		
	@PostMapping("/runtest")
	public void runJob() {
		playGroundServices.runTestBuilder();
	}
	
	@GetMapping("/getjobs")
	public List<TimerInfo> getJobs() {
		return playGroundServices.getAllRunningJobs();
	}
	@GetMapping("/getjobs/{id}")
	public TimerInfo getJobs(@PathVariable("id") String id) {
		System.out.println(id);
		return playGroundServices.getAllRunningJobsById(id);
	}
	@DeleteMapping("/getjobs/{id}")
	public void delteTimer(@PathVariable("id") String id) {
		 playGroundServices.deleteTimerId(id);
	}

}
