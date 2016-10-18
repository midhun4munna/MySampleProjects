package com.samples.tx.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.samples.tx.entity.Address;
import com.samples.tx.entity.Users;
import com.samples.tx.service.HotelService;

@Controller
public class RegistrationController {

	private HotelService hotelservice;
	private String test;

	public HotelService getHotelservice() {
		return hotelservice;
	}

	public void setHotelservice(HotelService hotelservice) {
		this.hotelservice = hotelservice;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		System.out.println("Setting test param to : " + test);
		this.test = test;
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.GET)
	public ModelAndView registerUserGet(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside RegistrationController GET : " + test);
		String view = null;
		String message = null;
		EntityManager em = null;
		String responseStr1 = null;
		List<Users> allUser = null;
		try {
			if (true) {
				System.out.println("Starting JDBC 1");
				view = "success";
				message = "Welcome to Spring Mvc Dear ";

				EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
				em = emf.createEntityManager();
				em.getTransaction().begin();
				allUser = em.createQuery("SELECT e FROM Users e").getResultList();
				System.out.println("UserList Size ----------- " + allUser.size());
				for (Users u : allUser) {
					System.out.println("User Details ----------------- FirstName " + u.getFirstName() + " LastName "
							+ u.getFirstName());
				}
				em.getTransaction().commit();
				System.out.println("Committed");
			}
			Gson gson = new Gson();
			responseStr1 = gson.toJson(allUser);

			System.out.println("--------JsonArray-------" + responseStr1);
			System.out.println("Inside RegistrationController GET" + message);
		} catch (

		Exception ex)

		{
			System.out.println("Error in Registration" + ex.getMessage());
			System.out.println("Roll Backed");
			ex.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally

		{
			if (em != null) {
				em.close();
			}
		}
		return new ModelAndView(view, "message", responseStr1);

	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
	public ModelAndView registerUpdateUser(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside registerUpdateUser");
		String view = null;
		String message = null;
		EntityManager em = null;
		String responseStr1 = null;
		List<Users> allUser = null;
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String gender = request.getParameter("gender");
		System.out.println("Firstname : " + fname + "Lastname : " + lname + " Gender : " + gender);
		try {
			if (true) {
				System.out.println("Starting JDBC 1");
				view = "success";
				message = "Welcome to Spring Mvc Dear ";

				EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
				em = emf.createEntityManager();
				em.getTransaction().begin();
				Users u1 = em.find(Users.class, fname);
				System.out.println("UserList Size ----------- " + u1.getFirstName() + " " + u1.getLastName() + " "
						+ u1.getGender());
				u1.setLastName(lname);
				u1.setGender(gender);
				em.getTransaction().commit();
				System.out.println("Committed");
				em.getTransaction().begin();
				allUser = em.createQuery("SELECT e FROM Users e").getResultList();
				for (Users u : allUser) {
					System.out.println("User Details ----------------- FirstName " + u.getFirstName() + " LastName "
							+ u.getFirstName());
				}
				em.getTransaction().commit();
				System.out.println("Committed");

			}
			Gson gson = new Gson();
			responseStr1 = gson.toJson(allUser);

			System.out.println("--------JsonArray-------" + responseStr1);
			System.out.println("Inside RegistrationController GET" + message);
		} catch (

		Exception ex)

		{
			System.out.println("Error in Registration" + ex.getMessage());
			System.out.println("Roll Backed");
			ex.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally

		{
			if (em != null) {
				em.close();
			}
		}
		return new ModelAndView(view, "message", responseStr1);

	}

	@RequestMapping(value = "/removeUser", method = RequestMethod.GET)
	public ModelAndView removeUser(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside removeUser");
		String view = null;
		String message = null;
		EntityManager em = null;
		String responseStr1 = null;
		List<Users> allUser = null;
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String gender = request.getParameter("gender");
		System.out.println("Firstname : " + fname + "Lastname : " + lname + " Gender : " + gender);
		try {
			if (true) {
				System.out.println("Starting JDBC 1");
				view = "success";
				message = "Welcome to Spring Mvc Dear ";

				EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
				em = emf.createEntityManager();
				em.getTransaction().begin();
				Users u1 = em.find(Users.class, fname);
				System.out.println("UserList Size ----------- " + u1.getFirstName() + " " + u1.getLastName() + " "
						+ u1.getGender());
				em.remove(u1);
				em.getTransaction().commit();
				System.out.println("Committed");
				em.getTransaction().begin();
				allUser = em.createQuery("SELECT e FROM Users e").getResultList();
				for (Users u : allUser) {
					System.out.println("User Details ----------------- FirstName " + u.getFirstName() + " LastName "
							+ u.getFirstName());
				}
				em.getTransaction().commit();
				System.out.println("Committed");

			}
			Gson gson = new Gson();
			responseStr1 = gson.toJson(allUser);

			System.out.println("--------JsonArray-------" + responseStr1);
			System.out.println("Inside RegistrationController GET" + message);
		} catch (

		Exception ex)

		{
			System.out.println("Error in Registration" + ex.getMessage());
			System.out.println("Roll Backed");
			ex.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally

		{
			if (em != null) {
				em.close();
			}
		}
		return new ModelAndView(view, "message", responseStr1);

	}

	@RequestMapping(value = "/addNewUser", method = RequestMethod.GET)
	public ModelAndView addNewUser(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside addNewUser");
		String view = null;
		String message = null;
		EntityManager em = null;
		String responseStr1 = null;
		List<Users> allUser = null;
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String gender = request.getParameter("gender");
		System.out.println("Firstname : " + fname + "Lastname : " + lname + " Gender : " + gender);
		try {
			if (true) {
				System.out.println("Starting JDBC 1");
				view = "success";
				message = "Welcome to Spring Mvc Dear ";

				EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
				em = emf.createEntityManager();
				em.getTransaction().begin();
				Users u1 = new Users();
				u1.setFirstName(fname);
				u1.setLastName(lname);
				u1.setGender(gender);
				em.persist(u1);
				em.getTransaction().commit();
				System.out.println("Committed");
				em.getTransaction().begin();
				allUser = em.createQuery("SELECT e FROM Users e").getResultList();
				for (Users u : allUser) {
					System.out.println("User Details ----------------- FirstName " + u.getFirstName() + " LastName "
							+ u.getFirstName());
				}
				em.getTransaction().commit();
				System.out.println("Committed");

			}
			Gson gson = new Gson();
			responseStr1 = gson.toJson(allUser);

			System.out.println("--------JsonArray-------" + responseStr1);
			System.out.println("Inside RegistrationController GET" + message);
		} catch (

		Exception ex)

		{
			System.out.println("Error in Registration" + ex.getMessage());
			System.out.println("Roll Backed");
			ex.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally

		{
			if (em != null) {
				em.close();
			}
		}
		return new ModelAndView(view, "message", responseStr1);

	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ModelAndView registerUserPost(HttpServletRequest request) {
		System.out.println("Inside RegistrationController POST");
		String fname = request.getParameter("firstname");
		String gender = request.getParameter("gender");
		String view = null;
		String message = null;
		if (gender.equals("male")) {
			view = "success";
			message = "Welcome to Spring Mvc Dear " + fname;
		} else if (gender.equals("female")) {
			view = "error";
			message = "Female Registration not Allowed";
		}
		System.out.println("Inside RegistrationController GET" + message);
		return new ModelAndView(view, "message", message);
	}


	@RequestMapping(value = "/addNewUserJpa", method = RequestMethod.GET)
	public ModelAndView addNewUserJpa(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside addNewUserJpa");
		String view = null;
		String message = null;
		EntityManager em = null;
		String responseStr1 = null;
		List<Users> allUser = null;
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String gender = request.getParameter("gender");
		System.out.println("Firstname : " + fname + "Lastname : " + lname + " Gender : " + gender);
		try {
			view = "success";
			message = "Welcome to Spring Mvc Dear ";
			Users user1 = new Users();
			user1.setFirstName(fname);
			user1.setLastName(lname);
			user1.setGender(gender);
			hotelservice.addUser(user1);
			allUser = hotelservice.getAllUsers();
			Gson gson = new Gson();
			responseStr1 = gson.toJson(allUser);

			System.out.println("--------JsonArray-------" + responseStr1);
			System.out.println("Transaction Comnpleted ");
			System.out.println("--------JsonArray-------");
			System.out.println("Inside RegistrationController GET" + message);
		} catch (

		Exception ex)

		{
			System.out.println("Error in Registration" + ex.getMessage());
			System.out.println("Roll Backed");
			ex.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally

		{
			if (em != null) {
				em.close();
			}
		}
		return new ModelAndView(view, "message", responseStr1);

	}

}
