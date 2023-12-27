package com.jwt.demo.dto;

import com.jwt.demo.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserInputDto {
	private int id;
	private String username;
	private String password;
	private String email;
	private Role role;
}
