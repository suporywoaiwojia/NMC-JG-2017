/**
 * Copyright (c) 2011, The David.Wang All rights reserved.
 * 
 * You can use this software for free.
 * If you want to report bug, please send mail to SeptWolves800515@gmail.com
 */
package com.menksoft.cms.application.core.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * Hibernate Open Session In View Filter,用于延长Hibernate Session的生命周期
 * 
 * @see {@link org.springframework.orm.hibernate3.support.OpenSessionInViewFilter}
 * @version 1.0
 * @author David
 */
public class OpenSessionInViewFilter extends org.springframework.orm.hibernate4.support.OpenSessionInViewFilter {
	/**
	 * 不采用OpenSessionInView方式访问的文件的路径参数名
	 */
	public static final String EXCLUDE_PATHS_PARAM_NAME = "excludePaths";
	/**
	 * 采用OpenSessionInView方式访问的文件的路径参数名
	 */
	public static final String INCLUDE_PATHS_PARAM_NAME = "includePaths";

	/**
	 * 缺省不采用OpenSessionInView方式访问的文件的路径
	 */
	public static final String[] DEFAULT_EXCLUDE_PATHS = {"js/", "css/", "images/"};
	/**
	 * 缺省采用OpenSessionInView方式访问的文件的路径
	 */
	public static final String[] DEFAULT_INCLUDE_PATHS = {"views/"};

	/**
	 * 不采用OpenSessionInView方式访问的文件的路径，可以通过配置文件指定，如果有多个路径，彼此之间使用","分隔。
	 */
	private String[] excludePaths = DEFAULT_EXCLUDE_PATHS;
	/**
	 * 采用OpenSessionInView方式访问的文件的路径，可以通过配置文件指定，如果有多个路径，彼此之间使用","分隔。
	 */
	private String[] includePaths = DEFAULT_INCLUDE_PATHS;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String requestPath = request.getServletPath();

		for (String includePath : this.includePaths) {
			if (requestPath.indexOf(includePath) != -1) {
				return false;
			}
		}

		for (String excludePath : this.excludePaths) {
			if (requestPath.indexOf(excludePath) != -1) {
				return true;
			}
		}

		return false;
	}

	@Override
	protected void initFilterBean() throws ServletException {
		String includePaths = getServletContext().getInitParameter(INCLUDE_PATHS_PARAM_NAME);

		if (StringUtils.hasLength(includePaths)) {
			this.includePaths = includePaths.split(",");
		}

		String excludePaths = getServletContext().getInitParameter(EXCLUDE_PATHS_PARAM_NAME);

		if (StringUtils.hasLength(excludePaths)) {
			this.excludePaths = excludePaths.split(",");
		}
	}
}
