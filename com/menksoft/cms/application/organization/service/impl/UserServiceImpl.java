/**
 * Copyright (c) 2011, The David.Wang All rights reserved.
 * 
 * You can use this software for free.
 * If you want to report bug, please send mail to SeptWolves800515@gmail.com
 */
package com.menksoft.cms.application.organization.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.application.organization.entity.behavior.UserBehavior;
import com.menksoft.cms.application.organization.service.UserService;
import com.menksoft.cms.application.permission.entity.Role;
import com.menksoft.util.Const;
import com.menksoft.util.MD5;
import com.menksoft.util.ValidateUtil;

/**
 * @author David
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private UserBehavior userBehavior;
    @Resource
    private HibernateTemplate hibernateTemplate;
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.organization.service.impl.UserService#getUserWithRolesByLoginId(java.lang.String)
	 */
	@Override
	public User getUserWithRolesByLoginId(String loginId){
		User user = null;
		try {
			user = userBehavior.getUserWithRolesByLoginId(loginId);
		} catch (Throwable e) {
			log.error("获取系统所有用户失败.", e);
			throw new RuntimeException("获取系统所有用户失败.", e);
		}

		return user;
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.organization.service.UserService#queryUser(com.menksoft.cms.application.organization.entity.User, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PagingTools<User> queryUser(User user, Integer startIndex,
			Integer count) throws Throwable {
		PagingTools<User> pagingtools=null;
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		try{
			criteria.add(Restrictions.or(Restrictions.isNull("built"),Restrictions.eq("built","")));
			if(user.getLoginId()!=null&&!user.getLoginId().equals(""))
				criteria.add(Restrictions.ilike("loginId", user.getLoginId(),MatchMode.ANYWHERE));
			if(user.getUserName()!=null&&!user.getUserName().equals(""))
				criteria.add(Restrictions.ilike("userName", user.getUserName(),MatchMode.ANYWHERE));
			pagingtools=userBehavior.queryUser(criteria, startIndex, count);
		}catch(Throwable e){
			log.error("用户查询"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			throw new RuntimeException("用户查询"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
		}
		return pagingtools;
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.organization.service.UserService#saveUser(com.menksoft.cms.application.organization.entity.User)
	 */
	@Override
	public void saveUser(User user) throws Throwable {
		//唯一校验
		Assert.isTrue(checkRepeat(user,"insert"), 
				user.getLoginId()+Const.MESSAGE_FAIL_EXISTED);
		
		List<Role> list=new ArrayList<Role>();
		try{
			
			for(int i=0;i<user.getRoles().size();i++){
				if(user.getRoles().get(i).getId()!=null)
					list.add(user.getRoles().get(i));
			}
			
			user.setPassword(MD5.getMD5ofStr(user.getPassword()));
			user.setRoles(list);
			userBehavior.saveUser(user);
		}catch(Throwable e){
			log.error("用户信息"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
			throw new RuntimeException("用户信息"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.organization.service.UserService#updateUser(com.menksoft.cms.application.organization.entity.User)
	 */
	@Override
	public void updateUser(User user) throws Throwable {
		
		//唯一校验
		Assert.isTrue(checkRepeat(user,"update"), 
				user.getLoginId()+Const.MESSAGE_FAIL_EXISTED);
		//邮箱校验
		
		if(user.getEmail()!=null&&!user.getEmail().equals(""))
			Assert.isTrue(ValidateUtil.isEmail(user.getEmail()), Const.MESSAGE_FAIL_VALADATE_MAIL);
		List<Role> list=new ArrayList<Role>();
		try{
			for(int i=0;i<user.getRoles().size();i++){
				if(user.getRoles().get(i).getId()!=null)
					list.add(user.getRoles().get(i));
			}
			User olduser=userBehavior.queryUserById(user.getId());
			if(!olduser.getPassword().equals(user.getPassword()))
				user.setPassword(MD5.getMD5ofStr(user.getPassword()));
			user.setRoles(list);
			userBehavior.updateUser(user);
		}catch(Throwable e){
			log.error("用户信息"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
			throw new RuntimeException("用户信息"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
		}
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.organization.service.UserService#deleteUser(java.lang.String)
	 */
	@Override
	public void deleteUser(String id) throws Throwable {
		try {
			
		
			userBehavior.deleteUser(id);
		} catch (Throwable e) {
			log.error("用户信息"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED, e);
			throw new RuntimeException("用户信息"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED, e);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.organization.service.UserService#queryUserById(long)
	 */
	@Override
	public User queryUserById(long id) throws Throwable {
		User user =null;
		try {
			user=userBehavior.queryUserById(id);
		}catch(Throwable e){
			log.error("用户信息"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			throw new RuntimeException("用户信息"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
		}
		return user;
	}
	
	public boolean checkRepeat(User user,String flag)throws Throwable {
		List<User> list=null;
		boolean temp=true;
		try {
			if(flag.equals("insert"))
				list=userBehavior.queryUserByLoginId(user.getLoginId());
			if(flag.equals("update"))
				list=userBehavior.queryUserByLoginIdForUpdate(user.getLoginId(),user.getId());
			if(list.size()>0)
				temp=false;
		}catch(Throwable e){
			log.error("用户信息"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			throw new RuntimeException("用户信息"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
		}
		return temp;
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.organization.service.UserService#getUserOfApprove()
	 */
	@Override
	public List<User> getUserOfApprove() throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		List<User> user=null;
		criteria.createAlias("roles", "r"); 
		criteria.add(Restrictions.eq("r.roleName","审核管理员"));  
		try{
			user=userBehavior.getUserOfApprove(criteria);
		}catch(Throwable e){
			log.error("用户信息"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			throw new RuntimeException("用户信息"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
		}
		return user;
	}
	/**
	 * 根据Name查询数据
	 * @param name
	 * @return
	 * @throws Throwable
	 */
	@Override
	public List<User> queryUserByName(String name) throws Throwable {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		detachedCriteria.add(Restrictions.eq("userName", name));
		return hibernateTemplate.getEntitiesByDetachedCriteria(detachedCriteria);
		
	}
}
