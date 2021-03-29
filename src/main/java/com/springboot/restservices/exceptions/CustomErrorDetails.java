package com.springboot.restservices.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomErrorDetails {

	private LocalDateTime timestamp;
	private String message;
	private String errordetails;
	
	
}
