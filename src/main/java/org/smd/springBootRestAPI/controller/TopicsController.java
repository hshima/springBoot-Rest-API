package org.smd.springBootRestAPI.controller;

import java.util.Arrays;
import java.util.List;

import org.smd.springBootRestAPI.model.Course;
import org.smd.springBootRestAPI.model.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TopicsController {

	@RequestMapping("/topics") // maps an endpoint
	@ResponseBody // Defines that a json response will be sent in the body
	public List<Topic> list() {
		Topic topic = new Topic("First Topic", "Description of validation of the first Topic", new Course("Jva essential", "Java"));
		
		return Arrays.asList(topic, topic, topic);
	}
}
