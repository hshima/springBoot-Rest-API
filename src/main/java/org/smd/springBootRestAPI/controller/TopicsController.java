package org.smd.springBootRestAPI.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.smd.springBootRestAPI.controller.dto.ToUpdateTopic;
import org.smd.springBootRestAPI.controller.dto.TopicDTO;
import org.smd.springBootRestAPI.controller.dto.TopicForm;
import org.smd.springBootRestAPI.model.Topic;
import org.smd.springBootRestAPI.repository.CourseRepository;
import org.smd.springBootRestAPI.repository.TopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@Cacheable(value = "topicsList") // Allows to use cache in a defined identifier
	public Page<TopicDTO> list(
				@RequestParam(required = false) String courseName, // As specific data may be requested via URL param, courseName is set as not mandatory
//				For pagination dealing, the following params are required
//				@RequestParam Integer page, 
//				@RequestParam Integer qty,
//				@RequestParam String sorting
				@PageableDefault(sort = "id",direction = Direction.ASC, page = 0, size = 10) // Defines default Pageable attributes if not informed
					Pageable pageable // Allows pageable attributes to be passed as argument. Requires attributes to be declared as Pageable definition
			) {
		
		//Pageable pageable = PageRequest.of(page, qty, Direction.DESC, sorting); // Allows use of data pagination and orders by specified field as ascending order is fixed
		

		if (courseName == null) {
			Page<Topic> // Method findAll(Pageable p) returns a Page object with properties about the returned page
				topics = topicsRepository.findAll(pageable); // Uses pageable as argumento for data retrieval
			return TopicDTO.converter(topics);
		} else {
			Page<Topic> topics = topicsRepository.findByCourse_Name(courseName, pageable);
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
	@CacheEvict(value = "topicsList", allEntries = true)
	public ResponseEntity<TopicDTO> register(@RequestBody @Valid // @Valid searches if topicForm has attribute
																	// validation
	TopicForm topicForm, // ResponseEntity<TopicDTO> is a Http dealer type with a specified generic
							// validation
			UriComponentsBuilder uriBuilder) {
		Topic topic = topicForm.toTopic(courseRepository); // Passes a CourseRepository as void param from
															// topicForm(DTO)
		topicsRepository.save(topic);

		URI uri = uriBuilder.path("/topics/{id}") // sends a parametrized HTTP GET request
				.buildAndExpand(topic.getId()) // exchanges the {id} part from the earlier path() with the returned
												// getId()
				.toUri(); // creates the requested URI to be called

		return ResponseEntity.created(uri) // created() returns a HTTP 201 with the location e requires an URI with call
											// for object representation of the created object
				.body(new TopicDTO(topic)); // builds the body of the response
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "topicsList", allEntries = true)
	public ResponseEntity<TopicDTO> update(@PathVariable Long id, @RequestBody @Valid ToUpdateTopic toUpdateTopic) { // Created
																														// a
																														// new
																														// DTO
																														// for
																														// input
																														// validation

		Optional<Topic> optional = topicsRepository.findById(id);

		if (optional.isPresent()) {
			Topic topic = toUpdateTopic.update(id, topicsRepository); // passes the id and injects a repository for
																		// Topic processing
			return ResponseEntity // From a class that deals with HTTP response
					.ok(new TopicDTO(topic)); // returns a 200 - OK, sending a Topic instance
		}
		return ResponseEntity // From a class that deals with HTTP response
				.notFound() // return a 404 - Not Found response
				.build(); // Doesn't gives a body as response
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "topicsList", allEntries = true)
	public ResponseEntity<?> remove(@PathVariable Long id) {
		Optional<Topic> optional = topicsRepository.findById(id);
		if (optional.isPresent()) {
			topicsRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
