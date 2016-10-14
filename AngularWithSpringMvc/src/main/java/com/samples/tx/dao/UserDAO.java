package com.samples.tx.dao;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.samples.tx.entity.Address;
import com.samples.tx.entity.Users;

public class UserDAO extends HibernateDaoSupport{
	
	private SessionFactory sessionFactory;
	
	public void addUser(Users usr) {
		System.out.println("User 555555");
		currentSession().save(usr);
		System.out.println("User Saved to database from UserDAO");
	}
	
	public void addAddress(Address add) {
		System.out.println("Inside addAddress of UserDAO");
		currentSession().save(add);
		System.out.println("Address Saved to database from UserDAO");
	}
	
	public void getUsers() {
		System.out.println("User 555555");
		
		System.out.println("User Saved to database from UserDAO");
	}
	

}
