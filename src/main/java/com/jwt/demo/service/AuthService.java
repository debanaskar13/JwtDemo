package com.jwt.demo.service;

import com.jwt.demo.dto.UserDto;

public interface AuthService {
	String generateToken(String username);

    UserDto getCurrentUser(String username);
}
