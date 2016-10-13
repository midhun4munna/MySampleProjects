package com.samples.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.samples.servlets.Entity.UserDetails;
import com.samples.servlets.Entity.UserVehicle;
import com.samples.servlets.Entity.Users;
import com.samples.servlets.dao.UserDAO;

/**
 * Servlet implementation class Register
 */
@WebServlet(name = "MyServlet", urlPatterns = "/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		ServletContext ctx=getServletContext();  
		String con=(String)ctx.getAttribute("mycon");  
		System.out.println("Attribute con"+con);
		if (type.equals("single")) {
			System.out.println("Getting Single User Details");
			System.out.println("UserName : " + request.getParameter("username"));
			String userName = request.getParameter("username");
			System.out.println("Start Getting UserDetails of User " + userName);
			UserDAO userDao = new UserDAO();
			UserDetails u = userDao.getUser(userName);
			String responseStr = "FirstName:" + u.getFirstName() + ", LastName:" + u.getLastName() + ", Gender:"
					+ u.getGender();
			System.out.println("UserDetails fName : " + u.getFirstName() + ", lName : " + u.getLastName()
					+ ", Gender : " + u.getGender() + "");
			// response.setContentType("application/json");
			// response.setCharacterEncoding("UTF-8");
			JSONObject json = new JSONObject();
			json.put("fName", u.getFirstName());
			json.put("lName", u.getLastName());
			json.put("gender", u.getGender());
			String responseStr1 = json.toString();
			response.getWriter().append(responseStr1);
		} else if (type.equals("all")) {
			System.out.println("Getting All User Details");
			System.out.println("UserName : " + request.getParameter("username"));
			String userName = request.getParameter("username");
			System.out.println("Start Getting UserDetails of User " + userName);
			UserDAO userDao = new UserDAO();
			UserDetails u = userDao.getUser(userName);
			String responseStr = "FirstName:" + u.getFirstName() + ", LastName:" + u.getLastName() + ", Gender:"
					+ u.getGender();
			System.out.println("UserDetails fName : " + u.getFirstName() + ", lName : " + u.getLastName()
					+ ", Gender : " + u.getGender() + "");
			// response.setContentType("application/json");
			// response.setCharacterEncoding("UTF-8");
			JSONObject json = new JSONObject();
			json.put("fName", u.getFirstName());
			json.put("lName", u.getLastName());
			json.put("gender", u.getGender());
			String responseStr1 = json.toString();
			response.getWriter().append(responseStr1);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println("Inside doPost Method in Register Servlet");
			System.out.println("FirstName : " + request.getParameter("firstname") + ", " + "LastName : "
					+ request.getParameter("lastname") + ", " + "Gender : " + request.getParameter("gender"));
			System.out.println("LastName : " + request.getParameter("lastname"));
			String firstName = request.getParameter("firstname");
			String lastName = request.getParameter("lastname");
			String gender = request.getParameter("gender");
			UserDetails u = new UserDetails();
			u.setFirstName(firstName);
			u.setLastName(lastName);
			u.setGender(gender);
			UserDAO userDao = new UserDAO();
			userDao.registerUser(u);
			response.getWriter().append("User " + firstName + " Saved to database");
			// RequestDispatcher rd =
			// request.getRequestDispatcher("/Registration.html");
			// rd.include(request, response);
		} catch (Exception ex) {
			System.out.println("Exception :" + ex.getMessage());
			ex.printStackTrace();
		}
	}

}
