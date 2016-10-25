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
		manager.persist(usr);
		System.out.println("User Saved to database from UserDAO");
	}

	public List<Users> getAllUsers() {
		System.out.println("Getting All Users");
		List<Users> users = manager.createQuery("SELECT e FROM Users e").getResultList();
		return users;
	}
	
	public void updateUser(Users user) {
		System.out.println("Updating User started");
		Users user1 = (Users)manager.find(Users.class, user.getFirstName());
		user1.setLastName(user.getLastName());
		user1.setGender(user.getGender());
		manager.merge(user);
		System.out.println("User Updated Successfully");
	}
	
	public void removeUser(Users user) {
		System.out.println("Removing User started");
		Users user1 = (Users)manager.find(Users.class, user.getFirstName());
		manager.remove(user1);
		System.out.println("User Removed Successfully");
	}

}
