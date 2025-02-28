package com.order.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthRest {
	
	@GetMapping
	public String getHealth() {
		return "Health OK";
	}
}
