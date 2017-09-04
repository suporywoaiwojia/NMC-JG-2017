/**
 * Copyright (c) 2011, The David.Wang All rights reserved.
 * 
 * You can use this software for free.
 * If you want to report bug, please send mail to SeptWolves800515@gmail.com
 */
package com.menksoft.cms.application.core.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.DispatcherServlet;

import com.menksoft.cms.application.core.WebContextInitializable;

/**
 * @author David
 *
 */
public class DispatcherServletInitializer implements WebContextInitializable {
	private static final String SERVLET_NAME = "Spring MVC Dispatcher Servlet";
	private static final String CONTEXT_CONFIG_FILE_LOCATION_NAME = "contextConfigLocation";
	private static final String CONTEXT_CONFIG_FILE_LOCATION = "classpath:config/spring/application-mvc-context.xml";
	private static final String MAPPING_URL_PATTERNS = "/action/*";

	/* (non-Javadoc)
	 * @see org.demo.core.spring.WebContextInitializable#init(javax.servlet.ServletContext)
	 */
	@Override
	public void init(ServletContext context) throws ServletException {
		ServletRegistration mvc = context.addServlet(SERVLET_NAME, DispatcherServlet.class);
		mvc.setInitParameter(CONTEXT_CONFIG_FILE_LOCATION_NAME, CONTEXT_CONFIG_FILE_LOCATION);
		mvc.addMapping(MAPPING_URL_PATTERNS);
	}
}
