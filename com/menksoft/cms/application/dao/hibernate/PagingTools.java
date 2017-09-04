/**
 * Copyright (c) 2011, The David.Wang All rights reserved.
 * 
 * You can use this software for free.
 * If you want to report bug, please send mail to SeptWolves800515@gmail.com
 */
package com.menksoft.cms.application.dao.hibernate;

import java.util.List;

/**
 * 分页工具
 * 
 * @version 1.0
 * @author David
 */
public class PagingTools<T> {
	public static final int PAGER_ECORD_DEFAULT =20;
	
	/**
	 * 总页数
	 */
	private Integer totalPage;
	/**
	 * 当前页
	 */
	private Integer currentPage;
	/**
	 * 总记录数
	 */
	private Long count;
	/**
	 * 每页显示记录数
	 */
	private Integer pageRecord;
	/**
	 * 当前页数据集合
	 */
	private List<T> dataSet;

	public PagingTools() {
		this.count = 0L;
		this.pageRecord = 0;
		this.totalPage = 0;
		this.dataSet = null;
	}

	public PagingTools(Long count, Integer pageRecord, Integer startIndex, List<T> dataSet) {
		this.count = count;
		this.pageRecord = pageRecord;
		this.dataSet = dataSet;

		// 计算本次查询的总页数(总记录数/每页显示的记录数),如果能够整除,则页数为整除的结果;否则,需要在运算结果上加1.
		boolean isDivideExactly = ((this.count % this.pageRecord) == 0) ? true : false;
		this.totalPage = (int) (isDivideExactly ? (this.count / this.pageRecord)
				: (this.count / this.pageRecord) + 1);
		this.currentPage=(startIndex/this.pageRecord)+1;
	}

	/**
	 * @return the totalPage
	 */
	public Integer getTotalPage() {
		return totalPage;
	}
	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}
	/**
	 * @return the pageRecord
	 */
	public Integer getPageRecord() {
		return pageRecord;
	}
	/**
	 * @param pageRecord the pageRecord to set
	 */
	public void setPageRecord(Integer pageRecord) {
		this.pageRecord = pageRecord;
	}
	/**
	 * @return the dataSet
	 */
	public List<T> getDataSet() {
		return dataSet;
	}
	/**
	 * @param dataSet the dataSet to set
	 */
	public void setDataSet(List<T> dataSet) {
		this.dataSet = dataSet;
	}
}
