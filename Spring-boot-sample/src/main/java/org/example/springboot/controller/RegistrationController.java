package org.example.springboot.controller;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.example.springboot.model.Greeting;
import org.example.springboot.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

	@Autowired
	GreetingService greetingService;

	@RequestMapping(value = "/registerUser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Greeting>> registerUserGet() {
		System.out.println("Starting registerUser Function");
		Collection<Greeting> greetings = null;
		greetings = greetingService.findAll();
		return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);

	}

	@RequestMapping(value = "/registerUser/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Greeting> registerUserGetId(@PathVariable("id") BigInteger id) {
		System.out.println("Starting registerUser Function with id : " + id);
		Greeting greeting = null;
		greeting = greetingService.findOne(id.intValue());
		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);

	}
	

	/*
	 * @RequestMapping(value = "/addNewUserJpa", method = RequestMethod.GET)
	 * public ResponseEntity<List<Users>>
	 * addNewUserJpa(@RequestParam("firstname") String firstname,
	 * 
	 * @RequestParam("lastname") String lastname, @RequestParam("gender") String
	 * gen) { System.out.println("Starting addNewUserJpa 1"); List<Users>
	 * allUser = null; String fname = firstname; String lname = lastname; String
	 * gender = gen; System.out.println("Firstname : " + firstname +
	 * "lastname : " + lname + " Gen : " + gen); try { Users user1 = new
	 * Users(); user1.setFirstName(fname); user1.setLastName(lname);
	 * user1.setGender(gender); System.out.println(
	 * "calling hotelservice.addUser"); hotelservice.addUser(user1); allUser =
	 * hotelservice.getAllUsers(); System.out.println("Completed addNewUserJpa"
	 * ); } catch (Exception ex){ System.out.println("Got Exp add 1"
	 * +ex.getMessage()); ex.printStackTrace(); } return new
	 * ResponseEntity<List<Users>>(allUser, HttpStatus.OK);
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/updateUser", method = RequestMethod.GET) public
	 * ResponseEntity<List<Users>> registerUpdateUser(@RequestParam("firstname")
	 * String firstname,
	 * 
	 * @RequestParam("lastname") String lastname, @RequestParam("gender") String
	 * gen) { System.out.println("Starting registerUpdateUser"); List<Users>
	 * allUser = null; String fname = firstname; String lname = lastname; String
	 * gender = gen; System.out.println("Firstname : " + fname + "Lastname : " +
	 * lname + " Gender : " + gender); try { Users user1 = new Users();
	 * user1.setFirstName(fname); user1.setLastName(lname);
	 * user1.setGender(gender); hotelservice.updateUser(user1); allUser =
	 * hotelservice.getAllUsers(); System.out.println(
	 * "Completed registerUpdateUser"); } catch (Exception ex) {
	 * ex.printStackTrace(); } return new ResponseEntity<List<Users>>(allUser,
	 * HttpStatus.OK);
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/removeUser", method = RequestMethod.GET) public
	 * ResponseEntity<List<Users>> removeUser(@RequestParam("firstname") String
	 * firstname,
	 * 
	 * @RequestParam("lastname") String lastname, @RequestParam("gender") String
	 * gen) { System.out.println("Staring removeUser"); List<Users> allUser =
	 * null; String fname = firstname; String lname = lastname; String gender =
	 * gen; try { Users user1 = new Users(); user1.setFirstName(fname);
	 * user1.setLastName(lname); user1.setGender(gender);
	 * hotelservice.removeUser(user1); allUser = hotelservice.getAllUsers();
	 * System.out.println("Completed removeUser");
	 * 
	 * } catch (Exception ex){ ex.printStackTrace(); } return new
	 * ResponseEntity<List<Users>>(allUser, HttpStatus.OK);
	 * 
	 * }
	 */

}
