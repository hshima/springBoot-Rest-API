package org.smd.springBootRestAPI.repository;

import org.smd.springBootRestAPI.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Course findByName(String courseName); // A spring patterned method to get attribute automatically 

	

}
