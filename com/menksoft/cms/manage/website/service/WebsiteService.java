/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : WebsiteService.java
 * 描述        : 会员管理
 * 作者        : 李彦佑
 * 日期        : 2012-11-14上午11:16:30
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1   2012-11-14  李彦佑
 */
package com.menksoft.cms.manage.website.service;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.website.entity.Website;

/**
 * @author Administrator
 *
 */
public interface WebsiteService {

	
	public PagingTools<Website> getAllWebsites(Integer startIndex, Integer count) throws Throwable;
	
	/**
	 * 新增站点信息
	 * @param website 站点对象
	 * @throws Throwable 保存过程中发生错误抛出异常。
	 */
	public abstract void save(Website website) throws Throwable;
	
	/**
	 * 更新站点信息
	 * @param website 站点对象
	 * @throws Throwable 更新过程中发生错误抛出异常。
	 */

	public abstract void update(Website website) throws Throwable;

	/**
	 * 根据Id删除站点信息
	 * @param id 链接Id
	 * @throws Throwable 删除过程发生错误时抛出异常。
	 */
	public abstract void deleteById(String id) throws Throwable;


	/**
	 * 根据Id获取站点对象
	 * @param id 站点Id
	 * @return 站点对象
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract Website getWebsiteById(long id) throws Throwable;
	
	/**
	 * 判断是否存在给定名称的站点
	 * @param name 站点名称
	 * @return 如果存在，返回true；否则返回false；执行过程中发生错误时，返回true。
	 */
	public abstract boolean isExist(String name);
	


}
