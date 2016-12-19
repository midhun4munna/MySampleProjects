package org.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("/")
	public String register() {
		System.out.println("Starting registerUser Function with id : ");
		return "index";
	}

}
