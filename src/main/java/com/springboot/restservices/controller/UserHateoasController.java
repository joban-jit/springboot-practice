package com.springboot.restservices.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.restservices.entities.Order;
import com.springboot.restservices.entities.User;
import com.springboot.restservices.exceptions.UserNotFoundException;
import com.springboot.restservices.services.UserService;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public CollectionModel<User> getAllUsers(){
		List<User> allUsers = userService.getAllUsers();

		allUsers.stream().forEach(user->{
			Long userId = user.getId();
			Link selfLink = WebMvcLinkBuilder
					.linkTo(this.getClass())
					.slash(userId)
					.withSelfRel();
			user.add(selfLink);
			CollectionModel<Order> allOrders = null;
			try {
				allOrders = WebMvcLinkBuilder
						.methodOn(OrderHateoasController.class)
						.getAllOrders(userId);
			} catch (UserNotFoundException e) {
				e.printStackTrace();
			}
			Link orderLink = WebMvcLinkBuilder
								.linkTo(allOrders)
								.withRel("all-orders");
			user.add(orderLink);

		});
		Link selfLinkGetAllUsers = WebMvcLinkBuilder
										.linkTo(this.getClass())
										.withSelfRel();
		return CollectionModel.of(allUsers, selfLinkGetAllUsers);
	}
	
	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			User userById = userService.getUserById(id).get();
			Long userId = userById.getId();
			Link selfLink = WebMvcLinkBuilder
								.linkTo(this.getClass())
								.slash(userId)
								.withSelfRel();
			userById.add(selfLink);
			return EntityModel.of(userById);
			
			
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
}
