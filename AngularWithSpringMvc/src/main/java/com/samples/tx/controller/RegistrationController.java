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
	public ModelAndView registerUserGet(HttpServletRequest request,HttpServletResponse response) {
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
				System.out.println("UserList Size ----------- "+allUser.size());
				for (Users u : allUser) {
					System.out.println("User Details ----------------- FirstName " + u.getFirstName() + " LastName "
							+ u.getFirstName());
				}
				em.getTransaction().commit();
				System.out.println("Committed");
			} 
			Gson gson = new Gson();
			responseStr1 =  gson.toJson(allUser);
		
			System.out.println("--------JsonArray-------"+responseStr1);
			System.out.println("Inside RegistrationController GET" + message);
		}catch(

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
	} return new ModelAndView(view,"message",responseStr1);

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
