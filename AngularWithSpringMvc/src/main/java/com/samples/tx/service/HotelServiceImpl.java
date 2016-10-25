package com.samples.tx.service;

import com.samples.tx.dao.UserDAO;
import com.samples.tx.entity.Address;
import com.samples.tx.entity.Users;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class HotelServiceImpl implements HotelService {

	@Autowired
	private UserDAO userDAO;

	public void addUser(Users u) {
		System.out.println("Inside addUser1 method of HotelService");
		userDAO.addUser(u);
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
		System.out.println("Inside addUser3 method of HotelService");
		//userDAO.addUser(u);
		Address address1 = new Address();
		address1.setFirstName(u.getFirstName() + "hhh");
		address1.setState("TamilNadu");
		address1.setCountry("Iran");
		userDAO.addAddress(address1);
		System.out.println("Add User3 Completed");
	}
	
	public List<Users> getAllUsers(){
		System.out.println("Inside getAllUsers method of HotelService");
		return userDAO.getAllUsers();
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void updateUser(Users u) {
		System.out.println("Inside updateUser method of HotelService");
		userDAO.updateUser(u);
		System.out.println("Update user completed");
	}
	
	public void removeUser(Users u) {
		System.out.println("Inside removeUser method of HotelService");
		userDAO.removeUser(u);
		System.out.println("RemoveUser completed");
	}

}
