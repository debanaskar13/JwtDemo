package com.jwt.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.demo.dto.UserDto;
import com.jwt.demo.entity.User;
import com.jwt.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return modelMapper.map(this.repository.save(user), UserDto.class);
	}

	@Override
	public UserDto getUser(int userId) {
		User user = this.repository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(int userId, UserDto user) {
		User user1 = this.repository.findById(userId)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		user1.setEmail(user.getEmail());
		user1.setRole(user.getRole());
		user1.setPassword(passwordEncoder.encode(user.getPassword()));
		return modelMapper.map(this.repository.save(user1), UserDto.class);
	}

	@Override
	public void deleteUser(int userId) {
		getUser(userId);
		this.repository.deleteById(userId);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> allUsers = this.repository.findAll();
		return allUsers.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
	}

}
