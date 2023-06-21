package com.amarnath.learning.learningrestfulwebservicesproject.hellocontroller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private MessageSource messageSource;

	public HelloController(MessageSource messageSource) {

		this.messageSource = messageSource;

	}

	@GetMapping("/hello")
	private String GetHelloMessage() {
		return "Hello Good Morning";

	}

	@GetMapping("/hello-international")
	private String GetHelloMessageInternational() {

		Locale locale = LocaleContextHolder.getLocale();
		 return messageSource.getMessage("good.morning.message", null, "Defult Massage", locale);

	}

}
