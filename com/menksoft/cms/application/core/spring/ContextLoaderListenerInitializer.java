/**
 * Copyright (c) 2011, The David.Wang All rights reserved.
 * 
 * You can use this software for free.
 * If you want to report bug, please send mail to SeptWolves800515@gmail.com
 */
package com.menksoft.cms.application.core.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.context.ContextLoaderListener;

import com.menksoft.cms.application.core.WebContextInitializable;

/**
 * @author David
 *
 */
public class ContextLoaderListenerInitializer implements WebContextInitializable {
	private static final String CONTEXT_CONFIG_FILE_LOCATION_NAME = "contextConfigLocation";
	private static final String CONTEXT_CONFIG_FILE_LOCATION = "classpath:config/spring/**/application-*-context.xml";

	/* (non-Javadoc)
	 * @see org.demo.core.spring.WebContextInitializable#init(javax.servlet.ServletContext)
	 */
	@Override
	public void init(ServletContext context) throws ServletException {
		context.setInitParameter(CONTEXT_CONFIG_FILE_LOCATION_NAME, CONTEXT_CONFIG_FILE_LOCATION);

		context.addListener(ContextLoaderListener.class);
	}
}
