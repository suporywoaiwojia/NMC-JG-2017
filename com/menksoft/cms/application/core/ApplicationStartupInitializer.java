/**
 * Copyright (c) 2011, The David.Wang All rights reserved.
 * 
 * You can use this software for free.
 * If you want to report bug, please send mail to SeptWolves800515@gmail.com
 */
package com.menksoft.cms.application.core;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;

import com.menksoft.cms.application.core.servlet.ManageServletInitializer;
import com.menksoft.cms.application.core.spring.CharacterEncodingFilterInitializer;
import com.menksoft.cms.application.core.spring.ContextLoaderListenerInitializer;
import com.menksoft.cms.application.core.spring.DispatcherServletInitializer;
import com.menksoft.cms.application.core.spring.Log4jConfigListnerInitializer;
import com.menksoft.cms.application.core.spring.OpenSessionInViewFilterInitializer;
import com.menksoft.cms.application.core.spring.SecurityFilterInitializer;

/**
 * @author David
 *
 */
public class ApplicationStartupInitializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext context) throws ServletException {
		new Log4jConfigListnerInitializer().init(context);
		new ContextLoaderListenerInitializer().init(context);
		new CharacterEncodingFilterInitializer().init(context);
		new OpenSessionInViewFilterInitializer().init(context);
		new DispatcherServletInitializer().init(context);
		new ManageServletInitializer().init(context);
		new SecurityFilterInitializer().init(context);
	}
}