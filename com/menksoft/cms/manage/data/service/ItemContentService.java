package com.menksoft.cms.manage.data.service;

import java.util.List;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.data.entity.ItemContent;
import com.menksoft.cms.manage.data.entity.Catalog;
import com.menksoft.cms.manage.data.entity.Item;

/**
 * 码表--呼麦风格管理
 * @author Administrator
 *
 */
public interface ItemContentService {
	
	/**
	 * 创建新实体
	 * @param itemContent
	 * @throws Throwable
	 */
	public abstract void save(ItemContent itemContent) throws Throwable;
	
	/**
	 * 更新实体
	 * @param itemContent
	 * @throws Throwable
	 */
	public abstract void update(ItemContent itemContent) throws Throwable;
	
	/**
	 * 删除实体
	 * @param id
	 * @throws Throwable
	 */
	public abstract String delete(String id ) throws Throwable;

	/**
	 * 查询所有的数据
	 * @return
	 * @throws Throwable
	 */
	public abstract List<ItemContent> getItemContents() throws Throwable;
	/**
	 * 查询单个实体
	 * @param id 
	 * @return
	 * @throws Throwable
	 */
	public abstract ItemContent getItemContentbyId(long id) throws Throwable;
	/**
	 * 依据提交的条件 ，查询对应栏目下的内容
	 * @param id approve
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<ItemContent> queryItems(ItemContent itemContent,Integer startIndex, Integer count) throws Throwable;	
	/**
	 * 依据column栏目的id查询该栏目下的内容
	 * @param id 
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<ItemContent> getItemContentsByItem(Item item,Integer startIndex, Integer count) throws Throwable;
	/**
	 * 依据catalog目录的id查询该目录下的内容
	 * @param id 
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<ItemContent> getItemContentsByCatalog(Catalog catalog,Integer startIndex, Integer count) throws Throwable;
	
	/**
	 * 检查实体的名称和编码是否重复
	 * @param itemContent 
	 * @return
	 * @throws Throwable
	 */
	public abstract List<ItemContent> checkRepeat(ItemContent itemContent) throws Throwable;
}
