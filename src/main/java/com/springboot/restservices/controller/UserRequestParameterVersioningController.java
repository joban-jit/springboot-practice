package com.springboot.restservices.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restservices.dtos.UserDtoV1;
import com.springboot.restservices.dtos.UserDtoV2;
import com.springboot.restservices.entities.User;
import com.springboot.restservices.exceptions.UserNotFoundException;
import com.springboot.restservices.services.UserService;

@RestController
@RequestMapping(value = "/versioning/params/users")
@Validated
public class UserRequestParameterVersioningController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping(value = "/{id}", params = "version=1")
	public UserDtoV1 getUserByIdV1(@PathVariable("id") @Min(1) Long id
			) throws UserNotFoundException {
		

		Optional<User> userById = userService.getUserById(id);
		if(userById.isEmpty()) {
			throw new UserNotFoundException("User not found in model controller V1");
		}
		User user = userById.get();
		return modelMapper.map(user,UserDtoV1.class);
	}
	
	@GetMapping(value = "/{id}", params = "version=2")
	public UserDtoV2 getUserByIdV2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userById = userService.getUserById(id);
		if(userById.isEmpty()) {
			throw new UserNotFoundException("User not found in model controller V2");
		}
		User user = userById.get();
		return modelMapper.map(user,UserDtoV2.class);
	}
}
