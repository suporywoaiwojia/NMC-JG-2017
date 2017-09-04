/**
 * Copyright (c) 2011, The David.Wang All rights reserved.
 * 
 * You can use this software for free.
 * If you want to report bug, please send mail to SeptWolves800515@gmail.com
 */
package com.menksoft.cms.application.core.spring;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.menksoft.cms.application.core.WebContextInitializable;
import com.menksoft.cms.application.core.util.OpenSessionInViewFilter;

/**
 * @author David
 *
 */
public class OpenSessionInViewFilterInitializer implements WebContextInitializable {
	private static final String FILTER_NAME = "hibernateOpenSessionInViewFilter";
	private static final String FILTER_PATTERNS = "/*";

	/* (non-Javadoc)
	 * @see org.demo.core.spring.WebContextInitializable#init(javax.servlet.ServletContext)
	 */
	@Override
	public void init(ServletContext context) throws ServletException {
		FilterRegistration webCharacterEncoding = context.addFilter(FILTER_NAME, OpenSessionInViewFilter.class);
		webCharacterEncoding.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, FILTER_NAME);
		webCharacterEncoding.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, FILTER_PATTERNS);
	}
}
