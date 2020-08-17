package org.smd.springBootRestAPI.controller;

import java.util.Arrays;
import java.util.List;

import org.smd.springBootRestAPI.model.Course;
import org.smd.springBootRestAPI.model.Topic;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Improves a class, removing the need of a @ResponseBody
public class TopicsController {

	@RequestMapping("/topics") // maps an endpoint
	
	public List<Topic> list() {
		Topic topic = new Topic("First Topic", "Description of validation of the first Topic", new Course("Jva essential", "Java"));
		
		return Arrays.asList(topic, topic, topic);
	}
}
