package com.gl.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gl.management.dao.UserRepository;
import com.gl.management.entity.User;
import com.gl.management.security.MyUserDetails;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) {
		User user=userRepository.getUserByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User "+username+" not found..!!");
		}
		return new MyUserDetails(user);
	}

}
