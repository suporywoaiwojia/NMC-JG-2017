package com.menksoft.cms.manage.webContent.service;

import java.util.List;

import com.menksoft.cms.manage.webContent.entity.Country;



/**
 * 码表--呼麦风格管理
 * @author Administrator
 *
 */
public interface CountryService {
	
	/**
	 * 创建新实体
	 * @param country
	 * @throws Throwable
	 */
	public abstract void save(Country country) throws Throwable;
	
	/**
	 * 更新实体
	 * @param country
	 * @throws Throwable
	 */
	public abstract void update(Country country) throws Throwable;
	
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
	public abstract List<Country> getCountrys() throws Throwable;
	/**
	 * 查询单个实体
	 * @param id 
	 * @return
	 * @throws Throwable
	 */
	public abstract Country getCountrybyId(long id) throws Throwable;
	
	
	/**
	 * 检查实体的名称是否重复
	 * @param country 
	 * @return
	 * @throws Throwable
	 */
	public abstract List<Country> checkRepeat(Country country) throws Throwable;
}
