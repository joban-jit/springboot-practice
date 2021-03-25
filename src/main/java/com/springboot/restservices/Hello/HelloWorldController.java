package com.springboot.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	
	@GetMapping("/helloworld")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails hellodWorldBean() {
		return new UserDetails("Jobanjit","Singh", "Tarn Taran");
	}
}
