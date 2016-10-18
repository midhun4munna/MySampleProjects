package com.samples.tx.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.samples.tx.entity.Address;
import com.samples.tx.entity.Users;

public class UserDAO {

	@PersistenceContext
	private EntityManager manager;

	public void addUser(Users usr) {
		System.out.println("User 555555");
		manager.persist(usr);
		System.out.println("User Saved to database from UserDAO");
	}

	public void addAddress(Address add) {
		System.out.println("Inside addAddress of UserDAO");
		System.out.println("Address Saved to database from UserDAO");
	}

	public List<Users> getAllUsers() {
		System.out.println("Getting All Users");
		List<Users> users = manager.createQuery("SELECT e FROM Users e").getResultList();
		return users;
	}

}
