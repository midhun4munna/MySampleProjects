package com.cts.tx.service;

import com.cts.tx.dao.UserDAO;
import com.cts.tx.entity.Address;
import com.cts.tx.entity.Users;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cts.tx.entity.Users;

public class HotelServiceImpl implements HotelService {

	@Autowired
	private UserDAO userDAO;

	public void addUser(Users u) {
		System.out.println("Inside addUser1 method of HotelService11111111");
		userDAO.addUser(u);
		Address address1 = new Address();
		address1.setFirstName(u.getFirstName());
		address1.setState("TamilNadu");
		address1.setCountry("Iran");
		userDAO.addAddress(address1);
		System.out.println("Add User1 Completed");
	}

	public void addUser2(Users u) {
		userDAO.addUser(u);
		Address address1 = new Address();
		address1.setFirstName(u.getFirstName());
		address1.setState("TamilNadu");
		address1.setCountry("Usbakistan");
		userDAO.addAddress(address1);
		System.out.println("Method addUser2 Completed");
	}

	public void addUser3(Users u) {
		System.out.println("Inside addUser3 method of HotelService11111111");
		//userDAO.addUser(u);
		Address address1 = new Address();
		address1.setFirstName(u.getFirstName() + "hhh");
		address1.setState("TamilNadu");
		address1.setCountry("Iran");
		userDAO.addAddress(address1);
		System.out.println("Add User3 Completed");
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}