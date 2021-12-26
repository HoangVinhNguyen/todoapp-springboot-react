package com.todoapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.todoapp.model.entity.User;
import com.todoapp.service.UserService;

public class TodoappUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = service.getUserByUsername(username);
		if (user != null) {
			return new TodoappUserDetails(user);
		}
		
		throw new UsernameNotFoundException("Could not find user with email: " + username);
	}

}
