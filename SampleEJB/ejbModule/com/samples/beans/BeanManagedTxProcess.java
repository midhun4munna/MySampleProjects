package com.samples.beans;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.samples.entity.Users;

/**
 * Session Bean implementation class StateLessTransaction
 */
@Stateful
@TransactionManagement(value = TransactionManagementType.BEAN)
public class BeanManagedTxProcess {


	@PersistenceContext(unitName = "UserPU")
	private EntityManager em;
	
	@Resource
	private UserTransaction userTransaction;

	public BeanManagedTxProcess() {
		// TODO Auto-generated constructor stub
	}

	public void addUser1(String fname) throws Exception {
		try {
			System.out.println("-----Inside addUser1 BEAN-Managaged TX-----"+fname);
			userTransaction.begin();
			Users u = new Users();
			u.setFirstName(fname+"Bean1");
			u.setLastName("Raj");
			u.setGender("male");
			em.persist(u);
			userTransaction.commit();
			System.out.println("-----BEAN-Managaged TX persited-----");
			int b = 10;
			int c = 2;
			int a = b / c;
		} catch (Exception exception) {

			System.out.println("Inside Exception"+exception.getMessage());
			exception.printStackTrace();
			throw new Exception();

		}
	}

}