package com.springboot.restservices.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.restservices.entities.User;
import com.springboot.restservices.exceptions.UserExistsException;
import com.springboot.restservices.exceptions.UserNameNotFoundException;
import com.springboot.restservices.exceptions.UserNotFoundException;
import com.springboot.restservices.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Api(tags = "User Management RESTful Services", value="User_Controller")
@RestController
@Validated
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@ApiOperation(value="Retrieve list of Users")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@ApiOperation(value="Create a new user")
	@PostMapping
	public ResponseEntity<Void> createUser(
			@ApiParam("User information for a new user to be created.")
			@Valid @RequestBody User user, 
			UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers
			.setLocation(
					builder.path("/users/{id}")
					.buildAndExpand(user.getId())
					.toUri()
					);
			return new ResponseEntity<>(headers, HttpStatus.CREATED);
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
			Optional<User> userById = userService.getUserById(id);
			if(userById.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found !");
			}
			return userById.get();
			
	}
	
	@PutMapping("/{id}")
	public User updateUserById(@RequestBody User user, @PathVariable("id") Long id){
		try {
			return userService.updateUserById(user, id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}
	
	@GetMapping("/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
		User userByUsername = userService.getUserByUsername(username);
		if(Optional.ofNullable(userByUsername).isEmpty()) {
			throw new UserNameNotFoundException("Requested user: '"+username+"' not found in User repository.");
		}else {
			return userByUsername;
		}
	}
}
