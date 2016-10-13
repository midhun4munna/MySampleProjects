package com.samples.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.samples.servlets.Entity.UserVehicle;
import com.samples.servlets.Entity.Users;

/**
 * Servlet implementation class AddUserAndVehicle
 */
@WebServlet("/AddUserAndVehicle")
public class AddUserAndVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUserAndVehicle() {
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

		try {
			String reqType = request.getParameter("reqType");
			System.out.println("Got Request Type is : " + reqType);
			if (reqType.equals("addvehicle")) {
				String firstName = request.getParameter("firstname");
				String vehicleName = request.getParameter("vehiclename");
				String type = request.getParameter("type");
				String company = request.getParameter("company");

				System.out.println("Start getting Emfactory : " + firstName);
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
				System.out.println("Got  Emf");
				EntityManager em = emf.createEntityManager();
				em.getTransaction().begin();
				System.out.println("Queriying User");

				Users user = em.find(Users.class, firstName);
				System.out.println("Creating Vehicle");
				UserVehicle vehicle = new UserVehicle();
				vehicle.setName(vehicleName);
				vehicle.setType(type);
				vehicle.setCompany(company);

				user.addNewVehicle(vehicle);
				vehicle.setUsers(user);

				System.out.println("Start persisting Vehicle ");
				em.persist(vehicle);

				System.out.println("persisted User");
				em.getTransaction().commit();
				System.out.println("Commit Completed");
				System.out.println("");
			} else if (reqType.equals("removevehicle")) {
				int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
				String userName = request.getParameter("username");
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
				System.out.println("Got  Emf");
				EntityManager em = emf.createEntityManager();
				em.getTransaction().begin();
				System.out.println("Got  Em");
				System.out.println("Creating Veh 1");

				UserVehicle vehicle = em.find(UserVehicle.class, vehicleId);
				Users user = em.find(Users.class, userName);
				user.removeUserVehicle(vehicle);

				System.out.println("persisted User");
				em.getTransaction().commit();
				System.out.println("Commit Completed");
			}
		} catch (Exception ex) {
			System.out.println("Error " + ex.getMessage());
			ex.printStackTrace();
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
			String firstName = request.getParameter("firstname");
			String lastName = request.getParameter("lastname");
			String gender = request.getParameter("gender");
			System.out.println("Start getting Emfactory : " + firstName);
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
			System.out.println("Got  Emf");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			System.out.println("Creating User");

			Users user1 = new Users();
			user1.setFirstName(firstName);
			user1.setLastName(lastName);
			user1.setGender(gender);
			//
			// vehicle1u1.setUsers(user1);
			// vehicle2u1.setUsers(user1);
			//
			System.out.println("Start persisting User ");
			em.persist(user1);

			System.out.println("persisted User");
			em.getTransaction().commit();
			System.out.println("Commit Completed");
		} catch (Exception ex) {
			System.out.println("Error " + ex.getMessage());
			ex.printStackTrace();
		}
	}

}
