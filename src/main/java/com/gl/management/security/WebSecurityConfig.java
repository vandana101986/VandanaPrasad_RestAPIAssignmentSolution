package com.gl.management.security;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gl.management.service.UserDetailsServiceImpl;

@SuppressWarnings("deprecation")
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin();
		http.requestMatcher(PathRequest.toH2Console());
		http.authorizeRequests().antMatchers("/h2-console/*","/h2-console/**").permitAll();
		http.authorizeRequests().antMatchers("/api/user", "/api/role").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.GET, "/api/employees","/api/employees/*","/api/employees/search/*").hasAnyAuthority("ADMIN","USER")
				.antMatchers(HttpMethod.POST, "/api/employees").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/employees").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/employees/*").hasAuthority("ADMIN")
				.anyRequest().authenticated().and().httpBasic()
				.and().cors().and().csrf().disable();
	}
}
