package com.menksoft.cms.redis.record.service;


import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.redis.record.entity.FavoriteRecords;


public interface FavoriteRecordsService {
	
	/**
	 * 保存观看记录
	 * @param loginid
	 * @param viewRecord
	 * @throws Throwable
	 */
	public String setFavoriteRecords(String loginid,String[] FavoriteRecord)throws Throwable;
	
	/**
	 * 获取观看记录
	 * @param loginid
	 * @return
	 * @throws Throwable
	 */
	public PagingTools<FavoriteRecords> getFavoriteRecords(long id,int pageRecord,int start)throws Throwable;
	
	
	/**
	 * 根据用户ID 删除收藏记录
	 * @param id
	 * @param index 记录下标
	 * @return
	 * @throws Throwable
	 */
	public String deleteFavoriteRecords(long id,int index)throws Throwable;
}
