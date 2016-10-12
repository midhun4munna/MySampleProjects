package com.samples.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class StatefulBank
 */
@Stateful
@LocalBean
public class StatefulBank implements Service {

	/**
	 * Default constructor.
	 */
	public int count = 0;

	public StatefulBank() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int add(int a, int b) {
		// TODO Auto-generated method stub\
		count++;
		System.out.println("Stateful Count" + count);
		return a + b;
	}

}
