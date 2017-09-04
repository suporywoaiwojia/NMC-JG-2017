package com.menksoft.cms.manage.data.service;

import java.util.List;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.manage.data.entity.Content;
import com.menksoft.cms.manage.data.entity.Message;
import com.menksoft.cms.manage.webContent.entity.Columns;

/**
 * 码表--呼麦风格管理
 * @author Administrator
 *
 */
public interface MessageService {
	
	/**
	 * 创建新实体
	 * @param message
	 * @throws Throwable
	 */
	public abstract void save(Message message) throws Throwable;
	
	/**
	 * 更新实体
	 * @param message
	 * @throws Throwable
	 */
	public abstract void update(Message message) throws Throwable;
	
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
	public abstract List<Message> getMessages() throws Throwable;
	/**
	 * 查询单个实体
	 * @param id 
	 * @return
	 * @throws Throwable
	 */
	public abstract Message getMessagebyId(long id) throws Throwable;
	
	/**
	 * 依据栏目的id查询该栏目下的内容
	 * @param id 
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<Message> getMessages(Integer startIndex, Integer count) throws Throwable;
	/**
	 * 依据提交的条件 ，查询对应栏目下的内容
	 * @param id approve
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<Message> queryMessages(Message message,Integer startIndex, Integer count) throws Throwable;
	/**
	 * 依据栏目的id和审核人，查询该栏目下的内容
	 * @param id approve
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<Message> getMessagesByColumnAndApprove(Columns column,User approve,Integer startIndex, Integer count) throws Throwable;
	/**
	 * 检查实体的名称和编码是否重复
	 * @param message 
	 * @return
	 * @throws Throwable
	 */
	public abstract List<Message> checkRepeat(Message message) throws Throwable;
}
