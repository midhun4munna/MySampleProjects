package com.cts.tx.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cts.tx.entity.Address;
import com.cts.tx.entity.Users;
import com.cts.tx.service.HotelService;

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

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ModelAndView registerUserPost(HttpServletRequest request) {
		System.out.println("Inside RegistrationController POST : " + test);
		String view = null;
		String message = null;
		EntityManager em = null;
		try {
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String gender = request.getParameter("gen");
			if (gender.equals("male")) {
				System.out.println("Starting JDBC 1");
				view = "success";
				message = "Welcome to Spring Mvc Dear " + fname;
				Users user1 = new Users();
				user1.setFirstName(fname);
				user1.setLastName(lname);
				user1.setGender(gender);
				hotelservice.addUser2(user1);	
				System.out.println("Transaction Comnpleted ");
//				EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
//				System.out.println("Got  Emf");
//				em = emf.createEntityManager();
//				em.getTransaction().begin();
//				System.out.println("Creating User");
//
//				Users user = new Users();
//				user.setFirstName(fname+"test");
//				user.setLastName(lname);
//				user.setGender(gender);
//				em.persist(user);
//
//				System.out.println("Creating Address");
//				Address address = new Address();
//				address.setFirstName(fname+"test");
//				address.setState("Kerala");
//				em.persist(address);
//				em.getTransaction().commit();
//				System.out.println("Committed");

			} else if (gender.equals("female")) {
				System.out.println("Inside Else----------");
				view = "error";
				message = "Female Registration not Allowed";
				return new ModelAndView(view, "message", message);
			}

			System.out.println("Inside RegistrationController POST" + message);
		} catch (Exception ex) {
			System.out.println("Error in Registration" + ex.getMessage());
			System.out.println("Roll Backed");
			ex.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return new ModelAndView(view, "message", message);
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.GET)
	public ModelAndView registerUserGet(HttpServletRequest request) {
		System.out.println("Inside RegistrationController GET");
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

	/**
	 * @RequestMapping(value = "/registerUsers", method = RequestMethod.PUT)
	 *                       public ModelAndView
	 *                       registerTransaction(HttpServletRequest request) {
	 *                       System.out.println(
	 *                       "Inside RegistrationController GET");
	 *                       ApplicationContext cntxt = new
	 *                       ClassPathXmlApplicationContext();
	 * 
	 *                       String fname = request.getParameter("firstname");
	 *                       String gender = request.getParameter("gender");
	 *                       String view = null; String message = null; if
	 *                       (gender.equals("male")) { view = "success"; message
	 *                       = "Welcome to Spring Mvc Dear " + fname; } else if
	 *                       (gender.equals("female")) { view = "error"; message
	 *                       = "Female Registration not Allowed"; }
	 *                       System.out.println(
	 *                       "Inside RegistrationController GET" + message);
	 *                       return new ModelAndView(view, "message", message);
	 *                       }
	 **/

}
