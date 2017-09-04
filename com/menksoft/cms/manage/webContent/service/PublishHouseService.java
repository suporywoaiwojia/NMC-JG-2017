package com.menksoft.cms.manage.webContent.service;

import java.util.List;

import com.menksoft.cms.manage.webContent.entity.PublishHouse;



/**
 * 码表--呼麦风格管理
 * @author Administrator
 *
 */
public interface PublishHouseService {
	
	/**
	 * 创建新实体
	 * @param publishHouse
	 * @throws Throwable
	 */
	public abstract void save(PublishHouse publishHouse) throws Throwable;
	
	/**
	 * 更新实体
	 * @param publishHouse
	 * @throws Throwable
	 */
	public abstract void update(PublishHouse publishHouse) throws Throwable;
	
	/**
	 * 删除实体
	 * @param id
	 * @throws Throwable
	 */
	public abstract void delete(String id ) throws Throwable;

	/**
	 * 查询所有的数据
	 * @return
	 * @throws Throwable
	 */
	public abstract List<PublishHouse> getPublishHouses() throws Throwable;
	/**
	 * 查询单个实体
	 * @param id 
	 * @return
	 * @throws Throwable
	 */
	public abstract PublishHouse getPublishHousebyId(long id) throws Throwable;
	
	
	/**
	 * 检查实体的名称是否重复
	 * @param publishHouse 
	 * @return
	 * @throws Throwable
	 */
	public abstract List<PublishHouse> checkRepeat(PublishHouse publishHouse) throws Throwable;
}
