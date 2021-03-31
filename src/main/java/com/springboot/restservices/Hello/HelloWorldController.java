package com.springboot.restservices.Hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private ResourceBundleMessageSource resourceBundleMessageSource;
	
	@GetMapping("/helloworld")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails hellodWorldBean() {
		return new UserDetails("Jobanjit","Singh", "Tarn Taran");
	}
	
	@GetMapping("/hello-int")
	public String getMesssageInI18NFormat(@RequestHeader(name="Accept-Language", required = false) String locale) {
		return resourceBundleMessageSource.getMessage("label.hello",null, new Locale(locale));
	}
	
	@GetMapping("/hello-int2")
	public String getMesssageInI18NFormat2() {
		return resourceBundleMessageSource.getMessage("label.hello",null, LocaleContextHolder.getLocale());
	}
}
