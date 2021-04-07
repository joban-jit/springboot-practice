package com.springboot.restservices.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMsDto {

	private Long userid;
	private String username;
	private String emailaddress;
	private String rolename;
	
}
