package org.smd.springBootRestAPI.controller;

import java.util.List;

import org.smd.springBootRestAPI.dto.TopicDTO;
import org.smd.springBootRestAPI.model.Topic;
import org.smd.springBootRestAPI.repository.TopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Improves a class, removing the need of a @ResponseBody
@RequestMapping("/topics") // maps an endpoint
public class TopicsController {

	@Autowired
	TopicsRepository topicsRepository;

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

}
