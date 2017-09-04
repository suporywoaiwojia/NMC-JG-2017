/**
 * 项目名称: 草原文化创意资源平台
 * 文件名    : SensitivewordsService.java
 * 描述        : 
 * 作者        : 景宏
 * 日期        : 上午10:42:41
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       上午11:02:42  景宏
 */
package com.menksoft.cms.manage.website.service;

import java.util.List;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.website.entity.Sensitivewords;


/**
 * @author 景宏
 */
public interface SensitivewordsService {
	
	/* (non-Javadoc)
	 * @see com.anda.cms.manage.website.sensitivewords.entity.service.impl.Sensitivewords#getAllSensitivewords(java.lang.Integer, java.lang.Integer)
	 */
	public abstract PagingTools<Sensitivewords> getAllSensitivewords (Sensitivewords sensitivewords,
			Integer startIndex, Integer count)  throws Throwable;
	
	/**
	 * 敏感词保存
	 * @param sensitivewords	提交对象
	 */
	public abstract void saveSensitivewords(Sensitivewords sensitivewords)  throws Throwable;
	
	/**
	 * 根据ID查询类型数据
	 * @param id	查询对象ID
	 * @throws Throwable
	 */
	public abstract Sensitivewords getSensitivewordsById(long id)  throws Throwable;
	
	/**
	 * 编辑保存
	 * @param sensitivewords	保存对象
	 * @throws Throwable
	 */
	public abstract void updateSensitivewords(Sensitivewords sensitivewords)  throws Throwable;
	
	/**
	 * 根据ID删除数据
	 * @param id	删除数据ID
	 * @throws Throwable
	 */
	public abstract void deleteSensitivewords(String id)  throws Throwable;
	
	/**
	 * 敏感词下拉菜单查询
	 * @return
	 * @throws Throwable
	 */
	public abstract List<Sensitivewords> querySensitivewords()  throws Throwable;
}
