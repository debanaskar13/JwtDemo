package com.jwt.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.jwt.demo.dto.AuthDto;
import com.jwt.demo.dto.UserDto;
import com.jwt.demo.service.AuthService;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private AuthenticationManager authManager;

	@PostMapping("/login")
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String,String>> generateToken(@RequestBody AuthDto dto) {
		Map<String,String> response = new HashMap<>();
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dto.getUsername(),
				dto.getPassword());

		Authentication authentication = this.authManager.authenticate(authToken);

		if (authentication.isAuthenticated()) {
			String token = authService.generateToken(dto.getUsername());
			response.put("token", token);
			return ResponseEntity.ok(response);
		} else {
			throw new UsernameNotFoundException("Invalid Username/Password");
		}
	}

	@PostMapping("/register")
	public UserDto registerUser(@RequestBody UserDto dto) {

		return dto;
	}

	@GetMapping("/current_user")
	public UserDto getCurrentUser(Principal principal) {
        return authService.getCurrentUser(principal.getName());
    }

}
