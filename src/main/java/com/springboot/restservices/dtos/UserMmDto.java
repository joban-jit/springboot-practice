package com.springboot.restservices.dtos;

import java.util.List;

import com.springboot.restservices.entities.Order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserMmDto {

	private Long userid;
	private String username;
	private String firstname;
	private List<Order> orders;
	
}
