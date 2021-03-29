package com.springboot.restservices.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {

	@ExceptionHandler(UserNameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomErrorDetails usernameNotFound(UserNameNotFoundException ex) {
		return new CustomErrorDetails(LocalDateTime.now(), "From @RestControllerAdvice NOT FOUND",ex.getLocalizedMessage());
	}
	
}
