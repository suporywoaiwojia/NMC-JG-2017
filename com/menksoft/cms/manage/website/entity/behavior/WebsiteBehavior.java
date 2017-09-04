/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : WebsiteBehavior.java
 * 描述        : 会员管理
 * 作者        : 李彦佑
 * 日期        : 2012-11-14上午10:58:08
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1   2012-11-14  李彦佑
 */
package com.menksoft.cms.manage.website.entity.behavior;

import java.util.List;
import java.util.Map;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.website.entity.Website;

/**
 * @author lyy
 *
 */
public interface WebsiteBehavior {

	/**
	 * 查询链接列表
	 * @param startIndex	开始页
	 * @param count	显示数量
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<Website> getAllWebsites(
			Integer startIndex, Integer count) throws Throwable;
	
	/**
	 * 保存链接
	 * @param link	提交对象
	 * @throws Throwable
	 */
	public abstract void saveWebsite(Website website) throws Throwable;
	
	/**
	 * 根据ID查询链接数据
	 * @param id	查询对象ID
	 * @throws Throwable
	 */
	public abstract Website getWebsiteById(long id) throws Throwable;
	
	/**
	 * 编辑保存
	 * @param link	保存对象
	 * @throws Throwable
	 */
	public abstract void updateWebsite(Website website) throws Throwable;
	
	/**
	 * 根据ID删除数据,批量删除
	 * @param id	删除数据ID
	 * @throws Throwable
	 */
	public abstract void deleteWebsites(Map<String,Object>  deleteMap) throws Throwable;
	
	/**
	 * 根据名称判断是否存在重复数据
	 * @param Link	查询对象
	 * @return	查询结果
	 * @throws Throwable
	 */
	public abstract List<Website>  getWebsitesByName(String name) throws Throwable;
	
	/**
	 * 根据ID，名称判断重复数据
	 * @param name 名称
	 * @param id	ID
	 * @return
	 * @throws Throwable
	 */
	public abstract List<Website>  getWebsiteByNameAndId(String name,long id) throws Throwable;
	
	/**
	 * 判断是否存在指定名称的幻灯片类型
	 * @param slideshowType 幻灯片类型
	 * @return 如果存在，返回true, 否则，返回false；执行过程中发生错误时，返回true。
	 */
	public abstract boolean isExist(Website website);
}
