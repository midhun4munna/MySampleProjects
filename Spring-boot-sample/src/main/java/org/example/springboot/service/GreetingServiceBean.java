package org.example.springboot.service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.example.springboot.model.Greeting;
import org.example.springboot.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceBean implements GreetingService{
	
	@Autowired
	private GreetingRepository greetingRepository;


	public Collection<Greeting> findAll() {
		return greetingRepository.findAll();
	}

	public Greeting findOne(Integer id) {
		return greetingRepository.findOne(id);
	}

	
	
}
