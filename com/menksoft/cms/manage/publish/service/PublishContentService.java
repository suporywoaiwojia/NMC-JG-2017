/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : PublishContentService.java
 * 描述        : 生成内容静态页
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
public interface PublishContentService {
	public String contentHtmlPage(String ids,HttpServletResponse response) throws NumberFormatException, Throwable;
	
	/**
	 * 生成全部内容
	 * @param response
	 * @return
	 * @throws NumberFormatException
	 * @throws Throwable
	 */
	public String contentHtmlPageAll(HttpServletResponse response) throws NumberFormatException, Throwable;
}
