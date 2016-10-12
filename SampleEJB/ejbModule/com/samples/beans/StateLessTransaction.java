package com.samples.beans;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Session Bean implementation class StateLessTransaction
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.BEAN)
public class StateLessTransaction {

	/**
	 * Default constructor.
	 */

	@Resource
	private UserTransaction userTransaction;

	public StateLessTransaction() {
		// TODO Auto-generated constructor stub
	}

	public void fundTransfer() throws Exception {
		try {
			System.out.println("Started Fund tranfer StateLessTransaction");

			int b = 10;
			int c = 2;
			int a = b / c;
			System.out.println("Completed StateLessTransaction Fund Tranfer committed");
		} catch (Exception exception) {

			System.out.println("Inside Exception");
			throw new Exception();

		}
	}

}
