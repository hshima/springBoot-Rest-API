package org.smd.springBootRestAPI.controller.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.smd.springBootRestAPI.model.Topic;
import org.smd.springBootRestAPI.repository.TopicsRepository;

import com.sun.istack.NotNull;

public class ToUpdateTopic {

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String title;

	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String message;

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public Topic update(Long id, TopicsRepository topicsRepository) {
		Topic one = topicsRepository.getOne(id); // gets the identified param from the repository
		one.setTitle(this.title);
		one.setMessage(this.message);
		return one;
	}

}
