/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : ApplicationContextHolder.java
 * 描述        : 获取ApplicationContext
 * 作者        : 呼和
 * 日期        : 上午9:29:47
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       上午9:29:47  lenovo
 */
package com.menksoft.cms.freemarker;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class ApplicationContextHolder implements ApplicationContextAware{
	private static ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:config/spring/*.xml");;
	@SuppressWarnings("all")
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		if(this.applicationContext != null) {
			throw new IllegalStateException("ApplicationContextHolder already holded 'applicationContext'.");
		}
		this.applicationContext = context;
		} 
	public static ApplicationContext getApplicationContext() {
		if(applicationContext == null)
			throw new IllegalStateException("'applicationContext' property is null,ApplicationContextHolder not yet init."); 
		return applicationContext;
		}
	public static Object getBean(String beanName) {
		return getApplicationContext().getBean(beanName); 
	} 
	public static void cleanHolder() {
		applicationContext = null;
		}
	}

