package com.samples.tx.controller;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samples.tx.entity.Users;
import com.samples.tx.service.HotelService;

@RestController
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

	@RequestMapping(value = "/registerUser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Users>> registerUserGet() {
		System.out.println("Starting registerUserGet" );
		List<Users> allUser = new ArrayList<Users>();
		try {
			allUser = hotelservice.getAllUsers();
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("Completed registerUserGet with user size" + allUser.size());
		return new ResponseEntity<List<Users>>(allUser, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/addNewUserJpa", method = RequestMethod.GET)
	public ResponseEntity<List<Users>> addNewUserJpa(@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname, @RequestParam("gender") String gen) {
		System.out.println("Starting addNewUserJpa");
		List<Users> allUser = null;
		String fname = firstname;
		String lname = lastname;
		String gender = gen;
		System.out.println("Firstname : " + firstname + "lastname : " + lname + " Gen : " + gen);
		try {
			Users user1 = new Users();
			user1.setFirstName(fname);
			user1.setLastName(lname);
			user1.setGender(gender);
			System.out.println("calling hotelservice.addUser");
			hotelservice.addUser(user1);
			allUser = hotelservice.getAllUsers();
			System.out.println("Completed addNewUserJpa");
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Users>>(allUser, HttpStatus.OK);

	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
	public ResponseEntity<List<Users>> registerUpdateUser(@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname, @RequestParam("gender") String gen) {
		System.out.println("Starting registerUpdateUser");
		List<Users> allUser = null;
		String fname = firstname;
		String lname = lastname;
		String gender = gen;
		System.out.println("Firstname : " + fname + "Lastname : " + lname + " Gender : " + gender);
		try {
			Users user1 = new Users();
			user1.setFirstName(fname);
			user1.setLastName(lname);
			user1.setGender(gender);
			hotelservice.updateUser(user1);
			allUser = hotelservice.getAllUsers();
			System.out.println("Completed registerUpdateUser");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Users>>(allUser, HttpStatus.OK);

	}

	@RequestMapping(value = "/removeUser", method = RequestMethod.GET)
	public ResponseEntity<List<Users>> removeUser(@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname, @RequestParam("gender") String gen) {
		System.out.println("Staring removeUser");
		List<Users> allUser = null;
		String fname = firstname;
		String lname = lastname;
		String gender = gen;
		try {
			Users user1 = new Users();
			user1.setFirstName(fname);
			user1.setLastName(lname);
			user1.setGender(gender);
			hotelservice.removeUser(user1);
			allUser = hotelservice.getAllUsers();
			System.out.println("Completed removeUser");

		} catch (Exception ex){
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Users>>(allUser, HttpStatus.OK);

	}


}
