package com.samples.servlets.eventListerners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class CountUserListener implements HttpSessionListener {

	static int count = 0;

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {

		System.out.println("Session Created");
		count++;
		System.out.println("New Count................."+count);

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("Session Destroyed");
		count--;
		System.out.println("Count After Destroy ................."+count);

	}

}
