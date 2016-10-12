package com.samples.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class HdfcBank
 */
@Stateless
@LocalBean
public class HdfcBank{

    /**
     * Default constructor. 
     */
    public HdfcBank() {
        // TODO Auto-generated constructor stub
    }

	
	public int add(int a, int b) {
		// TODO Auto-generated method stub
		return a-b;
	}


	public void send() {
		// TODO Auto-generated method stub
		
	}

}
