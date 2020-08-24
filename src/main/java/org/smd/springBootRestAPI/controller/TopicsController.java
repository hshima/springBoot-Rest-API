package org.smd.springBootRestAPI.controller;

import java.util.List;

import org.smd.springBootRestAPI.controller.dto.TopicDTO;
import org.smd.springBootRestAPI.controller.dto.TopicForm;
import org.smd.springBootRestAPI.model.Topic;
import org.smd.springBootRestAPI.repository.CourseRepository;
import org.smd.springBootRestAPI.repository.TopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Improves a class, removing the need of a @ResponseBody
@RequestMapping("/topics") // maps an endpoint
public class TopicsController {

	@Autowired
	TopicsRepository topicsRepository;
	
	@Autowired
	CourseRepository courseRepository; // Created a repository injection

	@GetMapping
	public List<TopicDTO> list(String courseName) { // As specific data may be requested via URL param, courseName is
													// set

		if (courseName == null) {
			List<Topic> topics = topicsRepository.findAll();
			return TopicDTO.converter(topics);
		} else {
			List<Topic> topics = topicsRepository.findByCourse_Name(courseName);
			return TopicDTO.converter(topics);
		}

	}
	
	@PostMapping
	@Transactional
	public void register(@RequestBody TopicForm topicForm) {
		
		Topic topic = topicForm.toTopic(courseRepository); // Passes a CourseRepository as void param from topicForm(DTO)
		topicsRepository.save(topic);
		
		
	}

}
