package com.menksoft.cms.manage.data.service;

import java.util.List;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.manage.data.entity.Content;
import com.menksoft.cms.manage.data.entity.Rapper;
import com.menksoft.cms.manage.webContent.entity.Columns;

/**
 * 码表--呼麦风格管理
 * @author Administrator
 *
 */
public interface RapperService {
	
	/**
	 * 创建新实体
	 * @param rapper
	 * @throws Throwable
	 */
	public abstract void save(Rapper rapper) throws Throwable;
	
	/**
	 * 更新实体
	 * @param rapper
	 * @throws Throwable
	 */
	public abstract void update(Rapper rapper) throws Throwable;
	
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
	public abstract List<Rapper> getRappers() throws Throwable;
	/**
	 * 查询单个实体
	 * @param id 
	 * @return
	 * @throws Throwable
	 */
	public abstract Rapper getRapperbyId(long id) throws Throwable;
	
	/**
	 * 依据栏目的id查询该栏目下的内容
	 * @param id 
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<Rapper> getRappers(Integer startIndex, Integer count) throws Throwable;
	/**
	 * 依据提交的条件 ，查询对应栏目下的内容
	 * @param id approve
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<Rapper> queryRappers(Rapper rapper,Integer startIndex, Integer count) throws Throwable;
	/**
	 * 依据栏目的id和审核人，查询该栏目下的内容
	 * @param id approve
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<Rapper> getRappersByColumnAndApprove(Columns column,User approve,Integer startIndex, Integer count) throws Throwable;
	/**
	 * 检查实体的名称和编码是否重复
	 * @param rapper 
	 * @return
	 * @throws Throwable
	 */
	public abstract List<Rapper> checkRepeat(Rapper rapper) throws Throwable;
}
