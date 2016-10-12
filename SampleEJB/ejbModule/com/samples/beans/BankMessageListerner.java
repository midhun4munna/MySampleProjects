package com.samples.beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Message-Driven Bean implementation class for: BankMessageListerner
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/ExpiryQueue") })
public class BankMessageListerner implements MessageListener {

	/**
	 * Default constructor.
	 */
	public BankMessageListerner() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {

		ObjectMessage objectMessage = null;
		try {
			System.out.println("Recieved msg in Queue");
			objectMessage = (ObjectMessage) message;
			Address address = (Address) objectMessage.getObject();
			System.out.println("Address Object recieved "+address.getName());
		} catch (JMSException ex) {
			System.out.println("Exception in Listen : " + ex);
			ex.printStackTrace();

			// mdctx.setRollbackOnly();
		}

	}

}
