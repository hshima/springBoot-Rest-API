package org.smd.springBootRestAPI.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.smd.springBootRestAPI.controller.dto.TokenDto;
import org.springframework.web.filter.OncePerRequestFilter;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private String authMethod = "Bearer ";
	private TokenService tokenService;

	public TokenAuthenticationFilter(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = retrieveToken(request);

		boolean authenticable = tokenService.isValidToken(token);
		System.out.println(authenticable);

		filterChain.doFilter(request, response);
	}

	private String retrieveToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if (token == null || token.isEmpty() || token.isBlank() || !token.startsWith(authMethod)) {
			return null;
		}
		return token.substring(authMethod.length(), token.length());
	}

}
