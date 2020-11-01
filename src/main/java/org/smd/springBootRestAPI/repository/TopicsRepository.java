package org.smd.springBootRestAPI.repository;

import org.smd.springBootRestAPI.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicsRepository extends JpaRepository<Topic, Long>{

	Page<Topic> findByCourse_Name(String courseName, Pageable pagination);
	

}
