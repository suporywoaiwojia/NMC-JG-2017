package com.menksoft.cms.manage.website.service;

import java.util.List;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.website.entity.Links;

public interface LinksService {
	
	public PagingTools<Links> getAllLinks(Integer startIndex, Integer count) throws Throwable;
	
	/**
	 * 新增链接信息
	 * @param link 链接对象
	 * @throws Throwable 保存过程中发生错误抛出异常。
	 */
	public abstract void save(Links link) throws Throwable;
	
	/**
	 * 更新链接信息
	 * @param link 链接对象
	 * @throws Throwable 更新过程中发生错误抛出异常。
	 */

	public abstract void update(Links link) throws Throwable;

	/**
	 * 根据Id删除链接信息
	 * @param id 链接Id
	 * @throws Throwable 删除过程发生错误时抛出异常。
	 */
	public abstract void deleteById(String id) throws Throwable;


	/**
	 * 根据Id获取链接对象
	 * @param id 链接Id
	 * @return 链接对象
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract Links getLinkById(long id) throws Throwable;
	
	/**
	 * 判断是否存在给定名称的链接
	 * @param name 链接名称
	 * @return 如果存在，返回true；否则返回false；执行过程中发生错误时，返回true。
	 */
	public abstract boolean isExist(String name);
	
	/**
	 * 前台获取全部友情链接
	 * @return
	 * @throws Throwable
	 */
	public List<Links> getAllLiks()throws Throwable;
}
