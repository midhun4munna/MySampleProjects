package com.samples.tx.service;

import com.samples.tx.dao.UserDAO;
import com.samples.tx.entity.Address;
import com.samples.tx.entity.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.SUPPORTS)
public class HotelServiceImpl implements HotelService {

	private UserDAO userDAO;

	@Transactional(propagation=Propagation.REQUIRED)
	public void addUser(Users u) {
		System.out.println("Inside addUser1 method of HotelService11111111");
		userDAO.addUser(u);
		//Address address1 = new Address();
		//address1.setFirstName(u.getFirstName());
		//address1.setState("TamilNadu");
		//address1.setCountry("Iran");
		//userDAO.addAddress(address1);
		System.out.println("Add User1 Completed");
	}

	@Transactional(propagation=Propagation.MANDATORY)
	public void addUser2(Users u) {
		System.out.println("Inside addUser2 method of HotelService22222222");
		userDAO.addUser(u);
		Address address1 = new Address();
		address1.setFirstName(u.getFirstName());
		address1.setState("TamilNadu");
		address1.setCountry("Iran");
		userDAO.addAddress(address1);
		System.out.println("Add User2 Completed");
	}

	public void addUser3(Users u) {
		System.out.println("Inside addUser3 method of HotelService333333333");
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