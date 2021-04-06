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

import com.springboot.restservices.dtos.UserMmDto;
import com.springboot.restservices.entities.User;
import com.springboot.restservices.exceptions.UserNotFoundException;
import com.springboot.restservices.services.UserService;

@RestController
@RequestMapping("/modelmapper/users")
@Validated
public class UserModelMapperController {

	@Autowired
	private UserService userService;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/{id}")
	public UserMmDto getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userById = userService.getUserById(id);
		if(userById.isEmpty()) {
			throw new UserNotFoundException("User not found in model controller");
		}
		User user = userById.get();
		return modelMapper.map(user, UserMmDto.class);
		
	}
}
