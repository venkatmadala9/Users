package com.simple.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.simple.users.shared.UserDto;

public interface UserService extends UserDetailsService{

	UserDto CreateUser(UserDto userDetails);
	UserDto getUserDetailsByEmail(String email);
}
