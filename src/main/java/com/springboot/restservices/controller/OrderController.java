package com.springboot.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restservices.entities.Order;
import com.springboot.restservices.entities.User;
import com.springboot.restservices.exceptions.UserNotFoundException;
import com.springboot.restservices.repositories.OrderRepository;
import com.springboot.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value= "/users")
public class OrderController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable("userid") Long userid) throws UserNotFoundException{
		Optional<User> userOptional = userRepository.findById(userid);
		if(userOptional.isEmpty()) {
			throw new UserNotFoundException("User not Found in user repo");
		}else {
			return userOptional.get().getOrder();
		}
	}
	
	@PostMapping("/{userid}/orders")
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);
		if(userOptional.isEmpty()) {
			throw new UserNotFoundException("User not Found in user repo");
		}else {
			User user = userOptional.get();
			order.setUser(user);
			return orderRepository.save(order);
		}
	}
	
	@GetMapping("/{userid}/orders/{orderid}")
	public Optional<Order> getOrderByOrderId(@PathVariable("userid") Long userid, @PathVariable("orderid") Long orderid) throws UserNotFoundException{
		Optional<User> userOptional = userRepository.findById(userid);
		if(userOptional.isEmpty()) {
			throw new UserNotFoundException("User not Found in user repo");
		}else {
			return userOptional.get().getOrder().stream().filter(o->o.getOrderid().equals(orderid)).findFirst();
		}
	}
	
}
