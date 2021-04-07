package com.springboot.restservices.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restservices.dtos.UserMsDto;
import com.springboot.restservices.entities.User;
import com.springboot.restservices.exceptions.UserNotFoundException;
import com.springboot.restservices.mappers.UserMapper;
import com.springboot.restservices.repositories.UserRepository;

@RestController
@RequestMapping("/mapstruct/users")
@Validated
public class UserMapStructController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping
	public List<UserMsDto> getAllUsers(){
		return userMapper.usersToUserMsDtos(userRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public UserMsDto getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		Optional<User> findById = userRepository.findById(id);
		if(findById.isEmpty()) {
			throw new UserNotFoundException("User not found in UserMapStruct");
		}
		User user = findById.get();
		return userMapper.userToUserMsDto(user);
	}
}
