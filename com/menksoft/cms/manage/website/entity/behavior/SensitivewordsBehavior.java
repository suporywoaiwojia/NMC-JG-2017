/**
 * 项目名称: 草原文化创意资源平台
 * 文件名    : SensitivewordsBehavior.java
 * 描述        : 广告类型管理数据操作层
 * 作者        : 景宏
 * 日期        : 上午10:30:53
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       下午3:56:40  景宏
 */
package com.menksoft.cms.manage.website.entity.behavior;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.website.entity.Sensitivewords;


/**
 * @author 景宏
 */
public interface SensitivewordsBehavior {
	
	/**
	 * 查询敏感词列表
	 * @param startIndex	开始页
	 * @param count	显示数量
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<Sensitivewords> getAllSensitivewords(DetachedCriteria criteria,
			Integer startIndex, Integer count) throws Throwable;
	
	/**
	 * 敏感词保存
	 * @param sensitivewords	提交对象
	 * @throws Throwable
	 */
	public abstract void saveSensitivewords(Sensitivewords sensitivewords) throws Throwable;
	
	/**
	 * 根据ID查询类型数据
	 * @param id	查询对象ID
	 * @throws Throwable
	 */
	public abstract Sensitivewords getSensitivewordsById(long id) throws Throwable;
	
	/**
	 * 编辑保存
	 * @param sensitivewords	保存对象
	 * @throws Throwable
	 */
	public abstract void updateSensitivewords(Sensitivewords sensitivewords) throws Throwable;
	
	/**
	 * 根据ID删除数据,批量删除
	 * @param id	删除数据ID
	 * @throws Throwable
	 */
	public abstract void deleteSensitivewords(String id) throws Throwable;
	
	/**
	 * 根据名称判断是否存在重复数据
	 * @param sensitivewords	查询对象
	 * @return	查询结果
	 * @throws Throwable
	 */
	public abstract List<Sensitivewords>  getSensitivewordsByName(String name) throws Throwable;
	
	/**
	 * 根据ID，名称判断重复数据
	 * @param name 名称
	 * @param id	ID
	 * @return
	 * @throws Throwable
	 */
	public abstract List<Sensitivewords>  getSensitivewordsByNameAndId(String name,long id) throws Throwable;
	
	/**
	 * 下拉菜单查询敏感词
	 * @return
	 * @throws Throwable
	 */
	public abstract List<Sensitivewords>  querySensitivewords() throws Throwable;
}
