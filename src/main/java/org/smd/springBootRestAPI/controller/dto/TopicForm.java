package org.smd.springBootRestAPI.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.smd.springBootRestAPI.model.Course;
import org.smd.springBootRestAPI.model.Topic;
import org.smd.springBootRestAPI.repository.CourseRepository;

public class TopicForm {

	@NotNull @NotEmpty @Length(min = 5)	
	private String title;
	
	@NotNull @NotEmpty @Length(min = 5)
	private String message;
	
	@NotNull @NotEmpty @Length(min = 5)
	private String courseName; // Determines a courseName: String

	TopicForm() {

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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Topic toTopic(CourseRepository repository) {

		Course course = repository.findByName(courseName); // Finds the received String by name from the repository 
		return new Topic(title, message, course); // returns a new instance of Topic as result
	}

}
