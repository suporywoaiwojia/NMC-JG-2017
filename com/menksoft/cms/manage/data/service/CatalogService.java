package com.menksoft.cms.manage.data.service;

import java.util.List;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.data.entity.Catalog;
import com.menksoft.cms.manage.data.entity.Item;

/**
 * 码表--呼麦风格管理
 * @author Administrator
 *
 */
public interface CatalogService {
	
	/**
	 * 创建新实体
	 * @param catalog
	 * @throws Throwable
	 */
	public abstract void save(Catalog catalog) throws Throwable;
	
	/**
	 * 更新实体
	 * @param catalog
	 * @throws Throwable
	 */
	public abstract void update(Catalog catalog) throws Throwable;
	
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
	public abstract List<Catalog> getCatalogs() throws Throwable;
	/**
	 * 查询单个实体
	 * @param id 
	 * @return
	 * @throws Throwable
	 */
	public abstract Catalog getCatalogbyId(long id) throws Throwable;
	
	/**
	 * 依据栏目的id查询该栏目下的内容
	 * @param id 
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<Catalog> getCatalogsByItem(Item item,Integer startIndex, Integer count) throws Throwable;
	/**
	 * 依据条件查询该栏目下的内容
	 * @param id 
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<Catalog> getCatalogsByCatalog(Catalog catalog,Integer startIndex, Integer count) throws Throwable;
	/**
	 * 检查实体的名称和编码是否重复
	 * @param catalog 
	 * @return
	 * @throws Throwable
	 */
	public abstract List<Catalog> checkRepeat(Catalog catalog) throws Throwable;
}
