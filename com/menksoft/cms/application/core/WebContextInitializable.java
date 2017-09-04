/**
 * Copyright (c) 2011, The David.Wang All rights reserved.
 * 
 * You can use this software for free.
 * If you want to report bug, please send mail to SeptWolves800515@gmail.com
 */
package com.menksoft.cms.application.core;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author David
 *
 */
public interface WebContextInitializable {
	/**
	 * 通过ServletContext对上下文环境进行初始化
	 * @param context Servlet上下文环境
	 * @throws ServletException
	 */
	public void init(ServletContext context) throws ServletException;
}
