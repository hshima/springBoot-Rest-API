package org.smd.springBootRestAPI.controller;

import javax.validation.Valid;

import org.smd.springBootRestAPI.controller.dto.LoginForm;
import org.smd.springBootRestAPI.controller.dto.TokenDto;
import org.smd.springBootRestAPI.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> authenticate(
				@RequestBody @Valid LoginForm form){
		
		UsernamePasswordAuthenticationToken loginData = form.convert();
		
		try {
			Authentication authentication = manager.authenticate(loginData);
			String token = tokenService.gerarToken(authentication);
			//System.out.println(token);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));	// Delivers a token to be used in the body. build() is not required in this case
		} catch(AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
}
