package com.samples.servlets.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class MyTagHandler extends TagSupport {

	public int doStartTag() throws JspException {
		System.out.println("Inside doStartTag of MyTagHandler");
		JspWriter out = pageContext.getOut();// returns the instance of
												// JspWriter
		try {
			out.print("hi this is my custom tag");// printing date and
													// time using JspWriter
		} catch (Exception e) {
			System.out.println(e);
		}
		return SKIP_BODY;// will not evaluate the body content of the tag
	}

}
