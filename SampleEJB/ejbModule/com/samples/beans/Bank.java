package com.samples.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Bank
 */
@Stateless(mappedName = "bankBean")
public class Bank implements BankRemote {

	/**
	 * Default constructor.
	 */
	public int count = 0;

	public Bank() {
		// TODO Auto-generated constructor stub
	}

	public int add(int a, int b) {
		count++;
		System.out.println("Stateless Count" + count);
		return a + b;
	}

}
