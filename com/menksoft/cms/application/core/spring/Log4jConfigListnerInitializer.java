/**
 * Copyright (c) 2011, The David.Wang All rights reserved.
 * 
 * You can use this software for free.
 * If you want to report bug, please send mail to SeptWolves800515@gmail.com
 */
package com.menksoft.cms.application.core.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.util.Log4jConfigListener;

import com.menksoft.cms.application.core.WebContextInitializable;

/**
 * @author David
 *
 */
public class Log4jConfigListnerInitializer implements WebContextInitializable {
	private static final String CONFIG_FILE_LOGCATION_NAME = "log4jConfigLocation";
	private static final String CONFIG_FILE_LOGCATION = "classpath:config/log4j/log4j.properties";
	private static final String LOG_REFRESH_INTERVAL_NAME = "log4jRefreshInterval";
	private static final String LOG_REFRESH_INTERVAL = "600000";

	/* (non-Javadoc)
	 * @see org.demo.core.spring.WebContextInitializable#init(javax.servlet.ServletContext)
	 */
	@Override
	public void init(ServletContext context) throws ServletException {
		context.setInitParameter(CONFIG_FILE_LOGCATION_NAME, CONFIG_FILE_LOGCATION);
		context.setInitParameter(LOG_REFRESH_INTERVAL_NAME, LOG_REFRESH_INTERVAL);

		context.addListener(Log4jConfigListener.class);
	}
}
