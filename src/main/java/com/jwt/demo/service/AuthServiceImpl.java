package com.jwt.demo.service;

import com.jwt.demo.dto.UserDto;
import com.jwt.demo.entity.User;
import com.jwt.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String generateToken(String username) {
		
		return jwtService.generateToken(username);
	}

	@Override
	public UserDto getCurrentUser(String username) {
		User user = this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
		return this.modelMapper.map(user, UserDto.class);
	}

}
