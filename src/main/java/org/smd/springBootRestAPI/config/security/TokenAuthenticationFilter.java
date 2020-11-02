package org.smd.springBootRestAPI.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.smd.springBootRestAPI.model.User;
import org.smd.springBootRestAPI.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private String authMethod = "Bearer ";
	private TokenService tokenService;
	private UserRepository repository;
	
	public TokenAuthenticationFilter(TokenService tokenService, UserRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = retrieveToken(request);

		boolean authenticated = tokenService.isValidToken(token);
		if (authenticated) {
			authenticateClient(token);
		}

		filterChain.doFilter(request, response);
	}

	private void authenticateClient(String token) {
		Long idUser = tokenService.getIdUser(token);
		User user = repository.findById(idUser).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null , user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String retrieveToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if (token == null || token.isEmpty() || token.isBlank() || !token.startsWith(authMethod)) {
			return null;
		}
		return token.substring(authMethod.length(), token.length());
	}

}
