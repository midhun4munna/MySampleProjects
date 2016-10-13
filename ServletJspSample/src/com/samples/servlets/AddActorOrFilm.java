
package com.samples.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.samples.servlets.Entity.Actor;
import com.samples.servlets.Entity.Film;
import com.samples.servlets.Entity.UserDetails;
import com.samples.servlets.Entity.UserVehicle;
import com.samples.servlets.Entity.Users;
import com.samples.servlets.dao.UserDAO;

/**
 * Servlet implementation class AddActorOrFilm
 */
@WebServlet("/AddActorOrFilm")
public class AddActorOrFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddActorOrFilm() {
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
		/**
		 * System.out.println("Inside doGet Method in Register Servlet");
		 * System.out.println("UserName : "+request.getParameter("username"));
		 * String userName = request.getParameter("username");
		 * System.out.println("Start Getting UserDetails of User "+userName);
		 * UserDAO userDao = new UserDAO(); UserDetails u =
		 * userDao.getUser(userName); //String responseStr =
		 * "FirstName:"+u.getFirstName()+", LastName:"+u.getLastName()+
		 * ", Gender:"+u.getGender(); System.out.println("UserDetails fName : "
		 * +u.getFirstName()+", lName : "+u.getLastName()+", Gender : "
		 * +u.getGender()+""); //response.setContentType("application/json");
		 * //response.setCharacterEncoding("UTF-8"); JSONObject json = new
		 * JSONObject(); json.put("fName", u.getFirstName()); json.put("lName",
		 * u.getLastName()); json.put("gender", u.getGender()); String
		 * responseStr = json.toString();
		 * response.getWriter().append(responseStr);
		 */
		String actorname = request.getParameter("username");
		System.out.println("Start getting Emfactory : " + actorname);
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
			System.out.println("Got  Emf");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();

			System.out.println("Got  Em");
			System.out.println("Creating Film 1");
			Film film1 = new Film();
			film1.setFilmName("Oppam");
			System.out.println("Creating Film 2");
			Film film2 = new Film();
			film2.setFilmName("Drishyam");

			Film f1 = em.find(Film.class, 1);

			Set<Film> filmSet1 = new HashSet<Film>();
			filmSet1.add(film1);
			filmSet1.add(film2);

			System.out.println("Got Film f1 " + f1.getFilmName());
			Actor actor1 = new Actor();
			actor1.setName(actorname);
			actor1.setFilms(filmSet1);

			// Set<Actor> actorSet1 = new HashSet<Actor>();
			// actorSet1.add(actor1);

			f1.addActor(actor1);
			// film2.setActors(actorSet1);

			System.out.println("Start persisting Actor");
			em.persist(actor1);
			System.out.println("persisted Actor");
			em.getTransaction().commit();
			System.out.println("Commit Completed");
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
	}

}
