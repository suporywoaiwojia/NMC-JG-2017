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

import org.springframework.web.filter.DelegatingFilterProxy;

import com.menksoft.cms.application.core.WebContextInitializable;

/**
 * @author David
 *
 */
public class SecurityFilterInitializer implements WebContextInitializable {
	/* 过滤器的名称必须为'springSecurityFilterChain' */
	private static final String FILTER_NAME = "springSecurityFilterChain";
	private static final String FILTER_PATTERNS = "/*";

	/* (non-Javadoc)
	 * @see org.demo.core.WebContextInitializable#init(javax.servlet.ServletContext)
	 */
	@Override
	public void init(ServletContext context) throws ServletException {
		FilterRegistration webCharacterEncoding = context.addFilter(FILTER_NAME, DelegatingFilterProxy.class);
		webCharacterEncoding.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, FILTER_NAME);
		webCharacterEncoding.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, FILTER_PATTERNS);
	}
}