package com.samples.tx.dao;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.samples.tx.entity.Address;
import com.samples.tx.entity.Users;

public class UserDAO extends  JdbcDaoSupport{
	
	private SessionFactory sessionFactory;
	
	public void addUser(Users usr) {
		System.out.println("Inside addUser of UserDAO");
		String insertSql ="INSERT INTO users (FirstName, LastName, gender) VALUES(?,?,?);";
		getJdbcTemplate().update(insertSql,new Object[]{usr.getFirstName(),usr.getLastName(),usr.getGender()});
		System.out.println("User Saved to database from UserDAO");
	}
	
	public void addAddress(Address add) {
		System.out.println("Inside addAddress of UserDAO");
		//currentSession().save(add);
		System.out.println("Address Saved to database from UserDAO");
	}
	

}
