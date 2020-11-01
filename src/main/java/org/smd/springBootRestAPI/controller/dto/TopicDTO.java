package org.smd.springBootRestAPI.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.smd.springBootRestAPI.model.Topic;
import org.springframework.data.domain.Page;

public class TopicDTO {
	private Long id;
	private String title;
	private String message;
	private LocalDateTime creationDate;

	public TopicDTO(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.creationDate = topic.getCreationDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public static Page<TopicDTO> converter(Page<Topic> topics) {
		//return topics.stream().map(TopicDTO::new).collect(Collectors.toList());
		return topics.map(TopicDTO::new); //return many instances of TopicDTO
	}
	
	
}