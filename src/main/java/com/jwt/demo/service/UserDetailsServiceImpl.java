package com.jwt.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jwt.demo.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found")); 
	}

}
