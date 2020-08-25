package org.smd.springBootRestAPI.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.smd.springBootRestAPI.controller.dto.TopicDTO;
import org.smd.springBootRestAPI.controller.dto.TopicForm;
import org.smd.springBootRestAPI.model.Topic;
import org.smd.springBootRestAPI.repository.CourseRepository;
import org.smd.springBootRestAPI.repository.TopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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

	@GetMapping("/{id}") // Setting an {id}: String as endpoint creates a new endpoint
	public Optional<Topic> listChosen(@PathVariable Long id) { // As specific Topic may be requested via URI endpoint.
														// @PathVariable assumes that the param id is named the same as
														// the endpoint. Whenever the method param differs from the
														// endpoint, @PathVariable must be set: @PathVariable("param")
		 
		return topicsRepository.findById(id); // Uses as default Spring Method

	}

	@PostMapping
	@Transactional
	public ResponseEntity<TopicDTO> register(@RequestBody TopicForm topicForm, UriComponentsBuilder uriBuilder) { // ResponseEntity<TopicDTO>
																													// is
																													// a
																													// Http
																													// dealer
																													// type
																													// with
																													// a
																													// specified
																													// generic
																													// validation
		//

		Topic topic = topicForm.toTopic(courseRepository); // Passes a CourseRepository as void param from
															// topicForm(DTO)
		topicsRepository.save(topic);

		URI uri = uriBuilder.path("/topics/{id}") // sends a parametrized HTTP GET request
				.buildAndExpand(topic.getId()) // exchanges the {id} part from the earlier path() with the returned
												// getId()
				.toUri(); // creates the requested URI tobe called

		return ResponseEntity.created(uri) // created() returns a HTTP 201 with the location e requires an URI with call
											// for object representation of the created object
				.body(new TopicDTO(topic)); // builds the body of the response
	}

}
