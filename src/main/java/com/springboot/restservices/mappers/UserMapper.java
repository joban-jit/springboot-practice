package com.springboot.restservices.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.springboot.restservices.dtos.UserMsDto;
import com.springboot.restservices.entities.User;

@Mapper(componentModel="Spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	// user to userMsDTO
	@Mappings({ // would be applicabe for below abstract methods as well
		@Mapping(source = "email",target = "emailaddress"),
		@Mapping(source = "role",target = "rolename") 
		
	})
	UserMsDto userToUserMsDto(User user);
	
	// List<User> to List<UserMsDto>
	
	List<UserMsDto> usersToUserMsDtos(List<User> user);
}
