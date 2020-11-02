package org.smd.springBootRestAPI.security;

import java.util.Date;

import org.smd.springBootRestAPI.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${springBoot-Rest-API.jwt.expiration}") // Sets the property that must be injected as String 
	private String expiration;
	
	@Value("${springBoot-Rest-API.jwt.secret}") // Sets the property that must be injected as String 
	private String secretKey;
	
	public String gerarToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		
		Date today = new Date();
		Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("Forum API")
				.setSubject(user.getId().toString())
				.setIssuedAt(today)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact() // Merges all the params built to a String
				;
	}

}
