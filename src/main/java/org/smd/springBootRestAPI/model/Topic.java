package org.smd.springBootRestAPI.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Topic {

	private Long id;
	private String title;
	private String message;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	private TopicStatus status = TopicStatus.NOT_REPLIED;
	private User author;
	private Course course;
	private List<Response> responses = new ArrayList<>();


	public Topic(String title, String message, Course course) {
		this.title = title;
		this.message = message;
		this.course = course;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topic other = (Topic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public TopicStatus getStatus() {
		return status;
	}

	public void setStatus(TopicStatus status) {
		this.status = status;
	}

	public User getAutor() {
		return author;
	}

	public void setAutor(User author) {
		this.author = author;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Response> getResponse() {
		return responses;
	}

	public void setResponse(List<Response> responses) {
		this.responses = responses;
	}

}
