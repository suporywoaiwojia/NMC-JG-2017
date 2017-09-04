package com.menksoft.cms.redis.record.service;


import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.redis.record.entity.ViewRecords;


public interface ViewRecordsService {
	
	/**
	 * 保存观看记录
	 * @param loginid
	 * @param viewRecord
	 * @throws Throwable
	 */
	public void setViewRecords(String loginid,String[] viewRecord)throws Throwable;
	
	/**
	 * 获取观看记录
	 * @param loginid
	 * @return
	 * @throws Throwable
	 */
	public PagingTools<ViewRecords> getViewRecords(long id,int pageRecord,int start)throws Throwable;
	
	
	/**
	 * 根据用户id 查询观看记录 并根据记录下标删除记录
	 * @param id
	 * @param index
	 * @return
	 * @throws Throwable
	 */
	public String deleteViewRecords(long id,int index)throws Throwable;
}
