package com.samples.servlets.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.samples.servlets.Entity.UserDetails;

public class UserDAO {

	public Connection conn = null;
	public Statement stmt = null;
	ResultSet rs = null;

	public void registerUser(UserDetails user) {
		try {
			System.out.println("Start Registring User");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mytest", "root", "admin");
			stmt = conn.createStatement();
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			String gender = user.getGender();
			String sql = "INSERT INTO UserDetails VALUES ('" + firstName + "', '" + lastName + "', '" + gender + "')";
			stmt.executeUpdate(sql);
			System.out.println("Inserted records into the table User...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

	public UserDetails getUser(String firstName) {
		UserDetails user = new UserDetails();
		try {
			System.out.println("Start Retreiving User");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mytest", "root", "admin");
			stmt = conn.createStatement();
			String sql = "select * from  UserDetails where firstname = '" + firstName + "'";
			rs = stmt.executeQuery(sql);
			System.out.println("Retreived User Details...");
			while (rs.next()) {
				String fName = rs.getString("firstname");
				String lName = rs.getString("lastname");
				String gender = rs.getString("gender");

				user.setFirstName(fName);
				user.setLastName(lName);
				user.setGender(gender);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return user;
	}

	public List<UserDetails> getAllUser() {
		List<UserDetails> allUsers = new ArrayList<UserDetails>();

		try {
			System.out.println("Start Retreiving User");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mytest", "root", "admin");
			stmt = conn.createStatement();
			String sql = "select * from  UserDetails";
			rs = stmt.executeQuery(sql);
			System.out.println("Retreived User Details...");
			while (rs.next()) {
				UserDetails user = new UserDetails();
				String fName = rs.getString("firstname");
				String lName = rs.getString("lastname");
				String gender = rs.getString("gender");

				user.setFirstName(fName);
				user.setLastName(lName);
				user.setGender(gender);
				allUsers.add(user);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return allUsers;
	}

}
