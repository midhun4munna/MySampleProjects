package org.example.springboot.controller;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.example.springboot.model.Greeting;
import org.example.springboot.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

	@Autowired
	GreetingService greetingService;

	@RequestMapping(value = "/registerUser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Greeting>> registerUserGet() {
		System.out.println("Starting registerUser Function");
		Collection<Greeting> greetings = null;
		greetings = greetingService.findAll();
		return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);

	}

	@RequestMapping(value = "/registerUser/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Greeting> registerUserGetId(@PathVariable("id") BigInteger id) {
		System.out.println("Starting registerUser Function with id : " + id);
		Greeting greeting = null;
		greeting = greetingService.findOne(id.intValue());
		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);

	}

}
