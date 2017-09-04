package com.menksoft.cms.manage.website.entity.behavior;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.website.entity.Links;

public interface LinkBehavior {
	/**
	 * 查询链接列表
	 * @param startIndex	开始页
	 * @param count	显示数量
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<Links> getAllLinks(
			Integer startIndex, Integer count) throws Throwable;
	
	/**
	 * 保存链接
	 * @param link	提交对象
	 * @throws Throwable
	 */
	public abstract void saveLink(Links link) throws Throwable;
	
	/**
	 * 根据ID查询链接数据
	 * @param id	查询对象ID
	 * @throws Throwable
	 */
	public abstract Links getLinkById(long id) throws Throwable;
	
	/**
	 * 编辑保存
	 * @param link	保存对象
	 * @throws Throwable
	 */
	public abstract void updateLink(Links link) throws Throwable;
	
	/**
	 * 根据ID删除数据,批量删除
	 * @param id	删除数据ID
	 * @throws Throwable
	 */
	public abstract void deleteLinks(String ids) throws Throwable;
	
	/**
	 * 根据名称判断是否存在重复数据
	 * @param Link	查询对象
	 * @return	查询结果
	 * @throws Throwable
	 */
	public abstract List<Links>  getLinksByName(String name) throws Throwable;
	
	/**
	 * 根据ID，名称判断重复数据
	 * @param name 名称
	 * @param id	ID
	 * @return
	 * @throws Throwable
	 */
	public abstract List<Links>  getLinkByNameAndId(String name,long id) throws Throwable;
	
	
	/**
	 * 前台获取全部友情链接
	 * @param criteria
	 * @return
	 * @throws Throwable
	 */
	public List<Links> getAllLinks(DetachedCriteria criteria)throws Throwable;
}

