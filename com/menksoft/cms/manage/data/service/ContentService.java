package com.menksoft.cms.manage.data.service;

import java.util.List;





import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.manage.data.entity.Content;
import com.menksoft.cms.manage.data.entity.Item;
import com.menksoft.cms.manage.webContent.entity.Columns;

/**
 * 码表--呼麦风格管理
 * @author Administrator
 *
 */
public interface ContentService {
	
	/**
	 * 创建新实体
	 * @param content
	 * @throws Throwable
	 */
	public abstract void save(Content content) throws Throwable;
	
	/**
	 * 更新实体
	 * @param content
	 * @throws Throwable
	 */
	public abstract void update(Content content) throws Throwable;
	
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
	public abstract List<Content> getContents() throws Throwable;
	/**
	 * 查询单个实体
	 * @param id 
	 * @return
	 * @throws Throwable
	 */
	public abstract Content getContentbyId(long id) throws Throwable;
	
	/**
	 * 依据栏目的id查询该栏目下的内容
	 * @param id 
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<Content> getContentsByColumn(Columns column,Integer startIndex, Integer count) throws Throwable;
	/**
	 * 依据item的id查询该栏目下的内容
	 * @param id 
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<Content> getContentsByItem(Item item,Integer startIndex, Integer count) throws Throwable;
	/**
	 * 依据提交的条件 ，查询对应栏目下的内容
	 * @param id approve
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<Content> queryContents(Content content,Integer startIndex, Integer count) throws Throwable;
	/**
	 * 依据栏目的id和审核人，查询该栏目下的内容
	 * @param id approve
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<Content> getContentsByColumnAndApprove(Columns column,User approve,Integer startIndex, Integer count) throws Throwable;
	/**
	 * 检查实体的名称和编码是否重复
	 * @param content 
	 * @return
	 * @throws Throwable
	 */
	public abstract List<Content> checkRepeat(Content content) throws Throwable;
}
