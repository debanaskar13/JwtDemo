package com.jwt.demo.service;

import java.util.List;

import com.jwt.demo.dto.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);

	UserDto getUser(int userId);

	UserDto updateUser(int userId, UserDto user);

	void deleteUser(int userId);

	List<UserDto> getAllUsers();

}
