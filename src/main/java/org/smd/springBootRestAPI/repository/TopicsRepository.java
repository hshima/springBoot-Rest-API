package org.smd.springBootRestAPI.repository;

import java.util.List;

import org.smd.springBootRestAPI.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicsRepository extends JpaRepository<Topic, Long>{

	List<Topic> findByCourse_Name(String courseName);
	

}
