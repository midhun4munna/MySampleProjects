package com.samples.beans;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import com.samples.entity.Users;

/**
 * Session Bean implementation class StateFulTransaction
 */
@Stateful
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class StateFulTransaction {

	@PersistenceContext(unitName = "UserPU")
	private EntityManager em;

	public StateFulTransaction() {

	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void fundTransfer() throws Exception {
		try {
			System.out.println("Started Fund tranfer StateFulTransaction-----");
			Users u = new Users();
			u.setFirstName("Remanan");
			u.setLastName("Raj");
			u.setGender("male");
			System.out.println("Persisting StateFulTransaction-----");
			em.persist(u);

			int b = 10;
			int c = 2;
			int a = b / c;
			System.out.println("Completed StateFulTransaction Fund Tranfer committed----");
		} catch (Exception exception) {

			System.out.println("Inside Exception");
			throw new Exception();

		}
	}

	public void init() {
		System.out.println("............Initializing Bean................");
	}

}
