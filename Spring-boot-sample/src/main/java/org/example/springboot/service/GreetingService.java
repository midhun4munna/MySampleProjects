package org.example.springboot.service;

import java.math.BigInteger;
import java.util.Collection;

import org.example.springboot.model.Greeting;

public interface GreetingService {

	public Collection<Greeting> findAll();
	
	public Greeting findOne(Integer id);
}
