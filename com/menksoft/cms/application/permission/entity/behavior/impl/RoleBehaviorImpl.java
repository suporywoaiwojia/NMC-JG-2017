/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : RoleBehaviorImpl.java
 * 描述          : 
 * 作者          : 王弘
 * 日期          : 2012-11-13
 * 公司          : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *     1     2012-11-13    王弘               新建文件
 */
package com.menksoft.cms.application.permission.entity.behavior.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.OrderPattern;
import com.menksoft.cms.application.permission.entity.Role;
import com.menksoft.cms.application.permission.entity.behavior.RoleBehavior;
import com.menksoft.util.Const;

/**
 * @author wangh
 * 角色的行为类，负责实现角色基本的增、删、改操作以及相关的查询操作
 */
@Repository("roleBehavior")
public class RoleBehaviorImpl implements RoleBehavior {
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.entity.behavior.impl.RoleBehavior#saveRole(com.menksoft.cms.application.permission.entity.Role)
	 */
	@Override
	public void saveRole(Role role) throws Throwable {
		hibernateTemplate.save(role);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.entity.behavior.impl.RoleBehavior#deleteRole(com.menksoft.cms.application.permission.entity.Role)
	 */
	@Override
	public void deleteRole(Role role) throws Throwable {
		hibernateTemplate.delete(role);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.entity.behavior.impl.RoleBehavior#deleteRoleById(java.io.Serializable)
	 */
	@Override
	public void deleteRoleById(Serializable id) throws Throwable {
		hibernateTemplate.deleteById(Role.class, "id", id);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.entity.behavior.impl.RoleBehavior#updateRole(com.menksoft.cms.application.permission.entity.Role)
	 */
	@Override
	public void updateRole(Role role) throws Throwable {
		hibernateTemplate.update(role);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.entity.behavior.impl.RoleBehavior#updateBySql(java.lang.String, java.util.Map)
	 */
	@Override
	public int updateBySql(String sql, Map<String,Object> properties) throws Throwable {
		return hibernateTemplate.executeSql(sql, properties);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.entity.behavior.impl.RoleBehavior#getRoleById(java.io.Serializable)
	 */
	@Override
	public Role getRoleById(Serializable id) throws Throwable {
		return hibernateTemplate.getEntityById(Role.class, id);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.entity.behavior.impl.RoleBehavior#getAllRole()
	 */
	@Override
	public List<Role> getAllRole(DetachedCriteria detachedCriteria) throws Throwable {
		
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("id", OrderPattern.asc);
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(detachedCriteria, orders);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.entity.behavior.impl.RoleBehavior#getRoleByName(java.lang.String)
	 */
	@Override
	public Role getRoleByName(String name) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Role.class);
		detachedCriteria.add(Restrictions.eq("roleName", name));
		
		return hibernateTemplate.getEntityByDetachedCriteria(detachedCriteria);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.entity.behavior.impl.RoleBehavior#isExist(com.menksoft.cms.application.permission.entity.Role)
	 */
	@Override
	public boolean isExist(Role role) {
		boolean isExist = true;
		try {
			String name = role.getRoleName();
			Role existedRole = getRoleByName(name); 
			if (existedRole == null) {
				isExist = false;
			}else if(role.getId().trim().length() > 0 && role.getId().equals(existedRole.getId())) {
				isExist = false;
			}
		} catch (Throwable e) {
			isExist = true;
		}
		return isExist;
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.entity.behavior.impl.RoleBehavior#buildPrimaryKey()
	 */
	@Override
	public String buildPrimaryKey() {
		String suffix = Const.FORMAT_TIMESTAMP.format(new Date());
		
		return Role.PRIMARY_KEY_PREFIX + suffix;
	}
}
