package com.samples.tx.service;

import java.util.List;

import com.samples.tx.entity.Users;

public interface HotelService {
	
	public void addUser(Users u);
	
	public void addUser2(Users u);
	
	public List<Users> getAllUsers();
	
}
