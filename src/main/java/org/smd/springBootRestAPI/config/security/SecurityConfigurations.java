package org.smd.springBootRestAPI.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService autenticationService;
	
	// Allows authentication configurations
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	// Authorization configuration (such as http requests, profile, etc...)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/topics").permitAll() // Allows access to every GET request
			.antMatchers(HttpMethod.GET, "/topics/*").permitAll()
			.antMatchers(HttpMethod.POST, "/auth").permitAll()
			.anyRequest().authenticated()
			//.and().formLogin() // Uses spring`s default form //Prevents session to be created
			.and().csrf().disable() // CrossSiteRequestForjure prevention (as this API has no session, there's no such problem)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // applies stateless configuration
			;
	}

	// Static resources (css, js, images, etc...) 
	@Override
	public void configure(WebSecurity web) throws Exception {

	}
	
}
