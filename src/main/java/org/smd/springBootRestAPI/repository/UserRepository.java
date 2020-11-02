package org.smd.springBootRestAPI.repository;

import java.util.Optional;

import org.smd.springBootRestAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);

}
