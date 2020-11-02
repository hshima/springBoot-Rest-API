package org.smd.springBootRestAPI.controller;

import javax.validation.Valid;

import org.smd.springBootRestAPI.controller.dto.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@PostMapping
	public ResponseEntity<?> authenticate(
				@RequestBody @Valid LoginForm form){
		System.out.println(form.getEmail());
		System.out.println(form.getPassword());
		return ResponseEntity.ok().build();
	}
}
