/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : PblishIndexService.java
 * 描述        : 生成静态页面
 * 作者        : lenovo
 * 日期        : 下午4:23:48
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       下午4:23:48  lenovo
 */
package com.menksoft.cms.manage.publish.service;

import java.util.List;

import com.menksoft.cms.manage.webContent.entity.Columns;

/**
 * @author 呼和
 */
public interface PublishIndexService {
	/**
	 * 删除首页
	 */
	public String deleteIndexPage();
	
	public List<Columns> getColumnsFindex()throws Throwable;
}
