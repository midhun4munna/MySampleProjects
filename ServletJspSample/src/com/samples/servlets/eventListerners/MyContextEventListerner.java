package com.samples.servlets.eventListerners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyContextEventListerner implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		System.out.print("---------------Context is Destroyed------------------");

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {

		System.out.print("--------------------Context is Initialized----------------------");
		ServletContext ctx=event.getServletContext();  
		ctx.setAttribute("mycon", "myconn");  
		
	}

}
