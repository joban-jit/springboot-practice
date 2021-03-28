package com.springboot.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.restservices.entities.User;
import com.springboot.restservices.exceptions.UserExistsException;
import com.springboot.restservices.exceptions.UserNotFoundException;
import com.springboot.restservices.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User createUser(User user) throws UserExistsException {
		User existingUser = userRepository.findByUsername(user.getUsername());
		if(Optional.ofNullable(existingUser).isEmpty()) {
			return userRepository.save(user);
		}else {
			throw new UserExistsException("User already exists in repository");
		}
	}

	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("User not found in user repository");
		}
		return user;
	}

	public User updateUserById(User user, Long id) throws UserNotFoundException {
		if(userRepository.findById(id).isEmpty()) {
			throw new UserNotFoundException("Update request for user not found in user repository");
		}
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Delete request for user not found in user repository");
		}
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
