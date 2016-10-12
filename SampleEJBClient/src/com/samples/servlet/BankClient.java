package com.samples.servlet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import com.samples.beans.BankRemote;
import com.samples.beans.BeanManagedTxProcess;
import com.samples.beans.ContainerManagedTxProcess;
import com.samples.beans.HdfcBank;
import com.samples.beans.Service;
import com.samples.beans.StateFulTransaction;
import com.samples.beans.StateLessTransaction;
import com.samples.beans.Address;

/**
 * Servlet implementation class BankClient
 */
@WebServlet("/BankClient")
public class BankClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	HdfcBank bankRemote;

	@Resource
	private UserTransaction userTransaction;

	/**
	 * Default constructor.
	 */
	public BankClient() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println("Inside doGet of BankClient");
			String firstName = request.getParameter("username");
			Context context = new InitialContext();
			System.out.println("Try Getting bankBean");
			boolean b = true;
			BankRemote bank = (BankRemote) context.lookup("java:global/MySampleEJB/SampleEJB/Bank");
			System.out.println("Got bankBean");
			System.out.println("HDFC :" + bankRemote.add(40, 32));
			System.out.println("BANK :" + bank.add(40, 32));

			// Message Driven bean Testing
			System.out.println("lookup ExpiryQueue");
			Queue queue = (Queue) context.lookup("java:/jms/queue/ExpiryQueue");
			System.out.println("ConnectionFactory");
			QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup("java:/ConnectionFactory");
			System.out.println("Creating Connection");
			QueueConnection connection = factory.createQueueConnection();
			QueueSession session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			QueueSender sender = session.createSender(queue);
			Address address = new Address();
			address.setName("Apsara");
			ObjectMessage objectMessage = session.createObjectMessage(address);
			System.out.println("Sending");
			sender.send(objectMessage);
			System.out.println("Send success");
			Service s = (Service) request.getSession().getAttribute("service");
			System.out.println("Session id :" + request.getSession().hashCode());
			System.out.println("Service" + s);
			if (s == null) {
				System.out.println("Service is null");
				s = (Service) context.lookup("java:global/MySampleEJB/SampleEJB/StatefulBank");
				request.getSession().setAttribute("service", s);
			}
			System.out.println("Stateful" + s.add(20, 30));
			userTransaction.begin();
			System.out.println("Transaction started");
			BeanManagedTxProcess beanTx = (BeanManagedTxProcess) context
					.lookup("java:global/MySampleEJB/SampleEJB/BeanManagedTxProcess");
			System.out.println("------------ Starting Container Managed Transaction -----------------");
			ContainerManagedTxProcess containerTx = (ContainerManagedTxProcess) context
					.lookup("java:global/MySampleEJB/SampleEJB/ContainerManagedTxProcess");
			System.out.println("Calling StateFulTransaction fundTransfer");
			containerTx.addUser1(firstName);
			containerTx.addUser2(firstName);
			System.out.println("+++++++++++ Completed Container Managed Transaction +++++++++");
			userTransaction.commit();
			System.out.println("------------- Starting BEAN Managed Tx -----------------"+firstName);
			beanTx.addUser1(firstName);
			System.out.println("++++++++++++ Completed BEAN Managed Tx ++++++++++++++");

		} catch (Exception ex) {
			System.out.println("Exception in client " + ex);
			try {
				userTransaction.rollback();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
