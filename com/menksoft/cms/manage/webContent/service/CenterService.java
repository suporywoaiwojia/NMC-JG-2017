package com.menksoft.cms.manage.webContent.service;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.webContent.entity.Center;
import com.menksoft.cms.manage.webContent.entity.Columns;

public interface CenterService {
	/**
	 * 保存项目
	 * @param pro 保存对象
	 * @throws Throwable
	 */
	public void save(Center pro)throws Throwable;
	/**
	 * 删除项目
	 * @param ids 保存对象
	 * @throws Throwable
	 */
	public void delete(String ids)throws Throwable;
	/**
	 * 更新项目
	 * @param pro 保存对象
	 * @throws Throwable
	 */
	public void update(Center pro)throws Throwable;
	/**
	 * 项目下拉列表
	 * @param column
	 * @return
	 * @throws Throwable
	 */
	public PagingTools<Center> getList(Columns column,Integer startIndex, Integer count)throws Throwable;
	
}
