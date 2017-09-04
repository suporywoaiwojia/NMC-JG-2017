package com.menksoft.cms.application.organization.service;

import java.util.List;


import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
public interface UserService {

	public abstract User getUserWithRolesByLoginId(String loginId);
	
	/**
	 * 列表查询数据
	 * @param user	查询条件
	 * @param startIndex	开始页数
	 * @param count	每页显示数量
	 * @return
	 * @throws Throwable
	 */
	public PagingTools<User> queryUser(User user,Integer startIndex, 
			Integer count)throws Throwable;
	
	/**
	 * 保存用户
	 * @param user
	 * @throws Throwable
	 */
	public void saveUser(User user)throws Throwable;
	
	/**
	 * 编辑保存
	 * @param user
	 * @throws Throwable
	 */
	public void updateUser(User user)throws Throwable;
	
	/**
	 * 删除用户
	 * @param id
	 * @throws Throwable
	 */
	public void deleteUser(String id)throws Throwable;
	
	/**
	 * 根据ID查询数据
	 * @param id
	 * @return
	 * @throws Throwable
	 */
	public User queryUserById(long id) throws Throwable;
	/**
	 * 根据Name查询数据
	 * @param name
	 * @return
	 * @throws Throwable
	 */
	public List<User> queryUserByName(String name) throws Throwable;
	
	public List<User> getUserOfApprove() throws Throwable;

}