package com.samples.servlets.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Servlet Filter implementation class CommonFilter
 */
@WebFilter(servletNames = "MyServlet", initParams = { @WebInitParam(name = "count", value = "10"),
		@WebInitParam(name = "strVal", value = "hi Filter") })

public class CommonFilter implements Filter {

	int count;
	String str;

	public CommonFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		System.out.println("Inside CommonFilter destroy");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Inside CommonFilter doFilter");
		String gender = request.getParameter("gender");
		count++;
		System.out.println("doFilter Count is " + count + " String is " + str);
		if (gender.equals("female")) {
			System.out.println("Female reg is not allowed in CommonFilter");
			RequestDispatcher rd = request.getRequestDispatcher("/Registration.html");
			rd.include(request, response);
		} else
			chain.doFilter(request, response);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Inside CommonFilter init");
		count = Integer.parseInt(fConfig.getInitParameter("count"));
		str = fConfig.getInitParameter("strVal");
		System.out.println("Count is " + count + " String is " + str);

	}

}
