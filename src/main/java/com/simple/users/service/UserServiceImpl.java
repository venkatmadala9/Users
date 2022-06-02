package com.simple.users.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.users.data.UserEntity;
import com.simple.users.data.UserRepository;
import com.simple.users.shared.UserDto;

@Service
public class UserServiceImpl implements UserService {

	UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDto CreateUser(UserDto userDetails) {

		userDetails.setUserId(UUID.randomUUID().toString());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
		
		userEntity.setEncryptedPassword("test");
		userRepository.save(userEntity);
		return null;
	}

}
