package com.jwt.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.demo.dto.UserDto;
import com.jwt.demo.dto.UserInputDto;
import com.jwt.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/users")
	public UserDto createUser(@RequestBody UserDto user) {
		return this.userService.createUser(user);
	}

	@GetMapping("/users/{id}")
	public UserDto getUser(@PathVariable("id") int userId) {
		return this.userService.getUser(userId);
	}

	@GetMapping("/users")
	public List<UserDto> getAllUser() {
		return this.userService.getAllUsers();
	}

	@PutMapping("/users/{id}")
	public UserDto updateUser(@PathVariable("id") int userId, @RequestBody UserDto user) {
		return this.userService.updateUser(userId, user);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int userId) {
		this.userService.deleteUser(userId);
		return ResponseEntity.ok("User Deleted Successfully");
	}

}
