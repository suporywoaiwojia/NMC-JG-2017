/**
 * Copyright (c) 2011, The David.Wang All rights reserved.
 * 
 * You can use this software for free.
 * If you want to report bug, please send mail to SeptWolves800515@gmail.com
 */
package com.menksoft.cms.application.organization.entity.behavior.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.OrderPattern;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.application.organization.entity.behavior.UserBehavior;


/**
 * @author David
 *
 */
@Repository("userBehavior")
public class UserBehaviorImpl implements UserBehavior {
	@Resource
	private HibernateTemplate hibernateTemplate;

	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.organization.entity.behavior.impl.UserBehavior#getUserWithRolesByLoginId(java.lang.String)
	 */
	@Override
	public User getUserWithRolesByLoginId(String loginId) throws Throwable{
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("loginId", loginId));
		criteria.setFetchMode("roles", FetchMode.JOIN);

		return hibernateTemplate.getEntityByDetachedCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.organization.entity.behavior.UserBehavior#queryUser(org.hibernate.criterion.DetachedCriteria, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PagingTools<User> queryUser(DetachedCriteria criteria,
			Integer startIndex, Integer count) throws Throwable {
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("displayOrder", OrderPattern.asc);
		return  hibernateTemplate.getEntitiesByDetachedCriteriaWithPagingAndOrders
				(criteria, startIndex, count,orders);
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.organization.entity.behavior.UserBehavior#saveUser(com.menksoft.cms.application.organization.entity.User)
	 */
	@Override
	public void saveUser(User user) throws Throwable {
		hibernateTemplate.save(user);
		
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.organization.entity.behavior.UserBehavior#updateUser(com.menksoft.cms.application.organization.entity.User)
	 */
	@Override
	public void updateUser(User user) throws Throwable {
		hibernateTemplate.update(user);
		
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.organization.entity.behavior.UserBehavior#deleteUser(java.util.Map)
	 */
	@Override
	public void deleteUser(String id) throws Throwable {
		hibernateTemplate.executeSql("delete from r_tab_user_role where users_id in("+id+")", null);
		hibernateTemplate.executeSql("delete from r_tab_users where id in("+id+")", null);
		
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.organization.entity.behavior.UserBehavior#queryUserById(long)
	 */
	@Override
	public User queryUserById(long id) throws Throwable {
		return hibernateTemplate.getEntityById(User.class, id);
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.organization.entity.behavior.UserBehavior#queryUserByLoginId(java.lang.String)
	 */
	@Override
	public List<User> queryUserByLoginId(String loginId) throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("loginId", loginId));
		return hibernateTemplate.getEntitiesByDetachedCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.organization.entity.behavior.UserBehavior#queryUserByLoginIdForUpdate(java.lang.String, java.lang.String)
	 */
	@Override
	public List<User> queryUserByLoginIdForUpdate(String loginId, long id)
			throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("loginId", loginId));
		criteria.add(Restrictions.ne("id", id));
		return hibernateTemplate.getEntitiesByDetachedCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.organization.entity.behavior.UserBehavior#getUserByLoginId(java.lang.String)
	 */
	@Override
	public User getUserByLoginId(String loginId) throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("loginId", loginId));
		return hibernateTemplate.getEntityByDetachedCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.organization.entity.behavior.UserBehavior#getUserOfApprove(org.hibernate.criterion.DetachedCriteria)
	 */
	@Override
	public List<User> getUserOfApprove(DetachedCriteria criteria)
			throws Throwable {
		return hibernateTemplate.getEntitiesByDetachedCriteria(criteria);
	}
}
