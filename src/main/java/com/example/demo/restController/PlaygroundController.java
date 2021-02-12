package com.example.demo.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.playground.PlayGroundServices;

@RestController
public class PlaygroundController {
	
	
	@Autowired
	private PlayGroundServices playGroundServices;
	
	@PostMapping("/runtest")
	public void runJob() {
		playGroundServices.runTestBuilder();
	}

}
