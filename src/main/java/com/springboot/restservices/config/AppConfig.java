package com.springboot.restservices.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	// to enable live http traces
//	@Bean
//	public HttpTraceRepository htttpTraceRepository()
//	{
//	  return new InMemoryHttpTraceRepository();
//	}
	
}
