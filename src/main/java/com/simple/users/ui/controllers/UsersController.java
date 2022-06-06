package com.simple.users.ui.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.users.service.UserService;
import com.simple.users.shared.UserDto;
import com.simple.users.ui.model.User;
import com.simple.users.ui.model.UserModel;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	UserService userService;

	@GetMapping("/status/check")
	public String status()
	{
		return "working on port " + env.getProperty("local.server.port");
	}
	
	@PostMapping( consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
				  produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}	)
	public ResponseEntity<UserModel> createUser(@RequestBody User user)
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto = modelMapper.map(user, UserDto.class);
		UserDto usersDto = userService.CreateUser(userDto);
		
		UserModel userModel = modelMapper.map(usersDto, UserModel.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
	}
}
