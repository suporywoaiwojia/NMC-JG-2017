/**
 * 项目名称: 草原文化创意资源平台
 * 文件名    : CharacterEncodingFilterInitializer.java
 * 描述        : 框架
 * 作者        : 王弘
 * 日期        : 2011/12/02 
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       2011/12/02   王弘
 */
package com.menksoft.cms.application.core.spring;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;

import com.menksoft.cms.application.core.WebContextInitializable;

/**
 * @author David
 *
 */
public class CharacterEncodingFilterInitializer implements WebContextInitializable {
	private static final String FILTER_NAME = "webCharacterEncoding";
	private static final String FILTER_PATTERNS = "/*";
	private static final String CHARACTER_ENCODING_NAME = "encoding";
	private static final String CHARACTER_ENCODING = "utf-8";

	/* (non-Javadoc)
	 * @see org.demo.core.spring.WebContextInitializable#init(javax.servlet.ServletContext)
	 */
	@Override
	public void init(ServletContext context) throws ServletException {
		FilterRegistration webCharacterEncoding = context.addFilter(FILTER_NAME, CharacterEncodingFilter.class);
		webCharacterEncoding.setInitParameter(CHARACTER_ENCODING_NAME, CHARACTER_ENCODING);
		webCharacterEncoding.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, FILTER_NAME);
		webCharacterEncoding.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, FILTER_PATTERNS);
	}
}
