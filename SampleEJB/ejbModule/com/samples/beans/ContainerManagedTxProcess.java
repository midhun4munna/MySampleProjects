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
public class ContainerManagedTxProcess {

	@PersistenceContext(unitName = "UserPU")
	private EntityManager em;

	public ContainerManagedTxProcess() {

	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void addUser1(String fname) throws Exception {
		try {
			System.out.println("-----Inside addUser1 MANDATORY-----"+fname);
			Users u = new Users();
			u.setFirstName(fname+"USER1");
			u.setLastName("Raj");
			u.setGender("male");
			em.persist(u);
			System.out.println("-----MANDATORY persited-----");
			int b = 10;
			int c = 2;
			int a = b / c;
		} catch (Exception exception) {

			System.out.println("Inside Exception");
			throw new Exception();

		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addUser2(String fname) throws Exception {
		try {
			System.out.println("-----Inside addUser2 REQUIRED-----"+fname);
			Users u = new Users();
			u.setFirstName(fname+"USER2");
			u.setLastName("Ravu");
			u.setGender("male");
			em.persist(u);
			System.out.println("-----REQUIRED persited-----");
			int b = 10;
			int c = 2;
			int a = b / c;
		} catch (Exception exception) {

			System.out.println("Inside Exception");
			throw new Exception();

		}
	}

	public void init() {
		System.out.println("............Initializing Bean................");
	}

}