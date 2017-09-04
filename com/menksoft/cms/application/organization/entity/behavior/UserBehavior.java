package com.menksoft.cms.application.organization.entity.behavior;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.application.organization.entity.User;

public interface UserBehavior {

	/**
	 * 用户登录获取用户
	 * @param loginId
	 * @return
	 * @throws Throwable
	 */
	public abstract User getUserWithRolesByLoginId(String loginId)
			throws Throwable;
	
	/**
	 *传入查询条件查询用户 列表
	 * @param criteria	查询条件
	 * @param startIndex	开始页
	 * @param count	每页显示数量
	 * @return
	 * @throws Throwable
	 */
	public PagingTools<User> queryUser(DetachedCriteria criteria,
			Integer startIndex, Integer count)throws Throwable;
	
	/**
	 * 新建保存
	 * @param user	保存对象
	 * @throws Throwable
	 */
	public void saveUser(User user)throws Throwable;
	
	/**
	 * 编辑保存
	 * @param user	保存对象
	 * @throws Throwable
	 */
	public void updateUser(User user)throws Throwable;
	
	/**
	 * 删除
	 * @param id	删除ID对象
	 * @throws Throwable
	 */
	public void deleteUser(String id)throws Throwable;
	
	/**
	 * 根据ID查询数据
	 * @param id	用户ID
	 * @return
	 * @throws Throwable
	 */
	public User queryUserById(long id )throws Throwable;
	
	/**
	 * 新建查询重复记录 --根据登陆ID查询数据
	 * @param loginId	登陆账号
	 * @return
	 * @throws Throwable
	 */
	public List<User> queryUserByLoginId(String loginId)throws Throwable;
	
	/**
	 * 编辑保存查询重复记录--根据登陆ID查询，并ID不等于当前编辑数据ID
	 * @param loginId	登陆ID
	 * @param id	用户ID
	 * @return
	 * @throws Throwable
	 */
	public List<User> queryUserByLoginIdForUpdate(String loginId,long id)throws Throwable;

	/**
	 * 根据用户名获取用户信息
	 * @param loginId 用户名
	 * @return 用户对象
	 * @throws Thorwable 
	 */
	public User getUserByLoginId(String loginId) throws Throwable;
	
	/**
	 * 查询审核人员
	 * @param criteria	查询条件
	 * @return
	 * @throws Throwable
	 */
	public List<User> getUserOfApprove (DetachedCriteria criteria)throws Throwable;
}