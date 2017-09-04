/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : PublishColumnService.java
 * 描述        : 生成栏目静态页
 * 作者        : 呼和
 * 日期        : 上午10:31:52
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       上午10:31:52  lenovo
 */
package com.menksoft.cms.manage.publish.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 呼和
 */
public interface PublishColumnService {
	/**
	 * 跳转标签解析方法
	 * @param ids	栏目ID
	 * @param pageNum	当前页数
	 * @param indexDynamics	首页静态标志 1 0动态
	 * @param listDynamics	列表静态标志 1	0动态
	 * @param response
	 * @return
	 * @throws NumberFormatException
	 * @throws Throwable
	 */
	public String columnHtmlPage(String ids,int pageNum,String indexDynamics,String listDynamics,HttpServletResponse response
			) throws NumberFormatException, Throwable;
	
	/**
	 * 栏目生成（全部）
	 * @param pageNum	当前页数
	 * @param indexDynamics	首页静态标志 1 0动态
	 * @param listDynamics	列表静态标志 1	0动态
	 * @param response
	 * @return
	 * @throws NumberFormatException
	 * @throws Throwable
	 * */
	public String columnHtmlPageAll(int pageNum,String indexDynamics,String listDynamics,HttpServletResponse response
			) throws NumberFormatException, Throwable;
}
