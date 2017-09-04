/**
 * Copyright (c) 2011, The David.Wang All rights reserved.
 * 
 * You can use this software for free.
 * If you want to report bug, please send mail to SeptWolves800515@gmail.com
 */
package com.menksoft.cms.application.core.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.menksoft.cms.application.core.WebContextInitializable;

/**
 * @author Daivd
 *
 */
public class ManageServletInitializer implements WebContextInitializable {

	/* (non-Javadoc)
	 * @see com.nmgnews.cms.application.core.WebContextInitializable#init(javax.servlet.ServletContext)
	 */
	@Override
	public void init(ServletContext context) throws ServletException {
		context.addServlet("manageServlet", ManageServlet.class);
	}
}
