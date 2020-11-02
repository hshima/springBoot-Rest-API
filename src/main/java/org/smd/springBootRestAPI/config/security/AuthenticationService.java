package org.smd.springBootRestAPI.config.security;

import java.util.Optional;

import org.smd.springBootRestAPI.model.User;
import org.smd.springBootRestAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(username);
		if (user.isPresent()) return user.get();
		throw new UsernameNotFoundException("Authentication were not found");
	}
	
}
