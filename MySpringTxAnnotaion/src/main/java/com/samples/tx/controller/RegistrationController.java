package com.samples.tx.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.samples.tx.entity.Address;
import com.samples.tx.entity.Users;
import com.samples.tx.service.HotelService;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;

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
	public ModelAndView registerUserPost(@RequestParam("fname") String fname1,@RequestParam("lname") String lname1,@RequestParam("gen") String gender1) {
		System.out.println("Inside RegistrationController POST : " + test);
		String view = null;
		String message = null;
		EntityManager em = null;
		try {
			String fname = fname1;
			String lname = lname1;
			String gender = gender1;
			if (gender.equals("male")) {
				view = "success";
				message = "Welcome to Spring Mvc Dear " + fname;
				Users user1 = new Users();
				user1.setFirstName(fname);
				user1.setLastName(lname);
				user1.setGender(gender);
				hotelservice.addUser(user1);
				System.out.println("Getting  Emf");
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
				System.out.println("Got  Emf");
				em = emf.createEntityManager();
				em.getTransaction().begin();
				System.out.println("Creating User");
				// Users user = new Users();
				// user.setFirstName(fname);
				// user.setLastName(lname);
				// user.setGender(gender);
				// em.persist(user);

				System.out.println("Creating Address");
				// Address address = new Address();
				// address.setFirstName(fname);
				// address.setState("Kerala");
				// address.setCountry("India");

				// em.persist(address);
				System.out.println("Address Persisted");

				em.getTransaction().commit();
				System.out.println("Committed");
			} else if (gender.equals("female")) {
				view = "error";
				message = "Female Registration not Allowed";
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
			if (em != null)
				em.close();
		}
		return new ModelAndView(view, "message", message);
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.GET,params="fname=munna")
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

	@RequestMapping(value = "/registerUser", method = RequestMethod.PUT,params="fname=munna")
	public ModelAndView registerUserPut(@RequestParam("fname") String fname1,@RequestParam("gender") String gender1) {
		System.out.println("Inside RegistrationController GET");
		String fname = fname1;
		String gender = gender1;
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
