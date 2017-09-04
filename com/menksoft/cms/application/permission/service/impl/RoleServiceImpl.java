/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : RoleServiceImpl.java
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
 *     1     2012-11-13       王弘               新建文件
 */
package com.menksoft.cms.application.permission.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.menksoft.cms.application.permission.entity.Resource;
import com.menksoft.cms.application.permission.entity.Role;
import com.menksoft.cms.application.permission.entity.behavior.RoleBehavior;
import com.menksoft.cms.application.permission.service.RoleService;
import com.menksoft.cms.application.security.config.SecurityMetadataSource;
import com.menksoft.util.Const;
import com.menksoft.util.ValidateUtil;

/**
 * @author wangh
 * 角色的业务类，负责实现角色管理模块的各类业务操作。
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@javax.annotation.Resource
	private RoleBehavior roleBehavior;
	
	@javax.annotation.Resource
	private SecurityMetadataSource securityMetadataSource;
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.service.impl.RoleService#saveRole(com.menksoft.cms.application.permission.entity.Role)
	 */
	@Override
	public void saveRole(Role role) throws Throwable {
		Assert.notNull(role, Const.MESSAGE_FAIL_ILLEGAL_ARGUMENT);
		String name = role.getRoleName();
		Assert.hasText(name, Const.MESSAGE_FAIL_SAVE_ISNULL + "角色名称。");
		Assert.isTrue(!ValidateUtil.nameSpecialCharacters(role.getRoleName()),"角色名称"+Const.MESSAGE_FAIL_VALIDATE_NAMESPECIALCHARACTERS);
		Assert.isTrue(!roleBehavior.isExist(role), name + Const.MESSAGE_FAIL_EXISTED);
		
		try {
			role.setId(roleBehavior.buildPrimaryKey());
			roleBehavior.saveRole(role);
		} catch (Throwable e) {
			throw new RuntimeException("角色"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.service.impl.RoleService#updateRole(com.menksoft.cms.application.permission.entity.Role)
	 */
	@Override
	public void updateRole(Role role) throws Throwable {
		
		try {
			Role oldRole = roleBehavior.getRoleById(role.getId());
			oldRole.setRoleName(role.getRoleName());
			roleBehavior.updateRole(oldRole);
		} catch (Throwable e) {
			throw new RuntimeException("角色"+Const.MESSAGE_FAIL_UNCHECKED_SAVE);
		}
	}
	
	public void saveRoleResource(Role role) throws Throwable {
		
		try {
			String roleId = role.getId();
			List<Resource> resources = role.getResources();
			if(resources != null) {
				StringBuffer sql = new StringBuffer();
				sql.append("DELETE FROM R_TAB_ROLE_RESOURCE WHERE ROLES_ID = '").append(roleId).append("'");
				roleBehavior.updateBySql(sql.toString(), null);
				
				for(Resource resource : resources) {
					sql = new StringBuffer();
					sql.append("INSERT INTO R_TAB_ROLE_RESOURCE(ROLES_ID, RESOURCES_ID) VALUES('");
					sql.append(roleId).append("',").append(resource.getId()).append(")");
					roleBehavior.updateBySql(sql.toString(), null);
				}
				
				securityMetadataSource.initResources();
			}
		} catch (Throwable e) {
			throw new RuntimeException("角色权限"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.service.impl.RoleService#deleteById(java.io.Serializable)
	 */
	@Override
	public void deleteById(Serializable id) throws Throwable {
		Assert.notNull(id, "角色"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED);
		
		try {
			roleBehavior.deleteRoleById(id);
		} catch (Throwable e) {
			throw new RuntimeException("角色"+Const.MESSAGE_FAIL_UNCHECKED_DELETE);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.service.impl.RoleService#deleteBatch(java.io.Serializable[])
	 */
	@Override
	public void deleteBatch(Serializable[] ids) throws Throwable {
		Assert.notEmpty(ids, "角色"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED);
		
		try {
			Map<String, Object> properties = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer("delete from R_TAB_ROLE where ");
			int length = ids.length;
			for(int i=0; i<length; i++) {
				properties.put("id"+i, ids[i]);
				hql.append("id = :id"+i);
				hql.append(" or ");
			}
			
			hql.delete(hql.length()-4, hql.length());
			
			roleBehavior.updateBySql(hql.toString(), properties);
		} catch (Throwable e) {
			throw new RuntimeException("角色"+Const.MESSAGE_FAIL_UNCHECKED_DELETE);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.service.impl.RoleService#getAllRole()
	 */
	@Override
	public List<Role> getAllRole() throws Throwable {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Role.class);
			detachedCriteria.add(Restrictions.eq("builtIn", 0));
			return roleBehavior.getAllRole(detachedCriteria);
		} catch (Throwable e) {
			throw new RuntimeException("角色"+Const.MESSAGE_FAIL_UNCHECKED_QUERY);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.service.impl.RoleService#getRoleById(java.io.Serializable)
	 */
	@Override
	public Role getRoleById(Serializable id) throws Throwable {
		Assert.notNull(id, Const.MESSAGE_FAIL_ILLEGAL_ARGUMENT);
		try {
			return roleBehavior.getRoleById(id);
		} catch (Throwable e) {
			throw new RuntimeException("角色"+Const.MESSAGE_FAIL_UNCHECKED_QUERY);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.service.impl.RoleService#isExist(com.menksoft.cms.application.permission.entity.Role)
	 */
	@Override
	public boolean isExist(Role role) {
		return roleBehavior.isExist(role);
	}

	@Override
	public List<Role> queryAllRole() throws Throwable {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Role.class);
			
			return roleBehavior.getAllRole(detachedCriteria);
		} catch (Throwable e) {
			throw new RuntimeException("角色"+Const.MESSAGE_FAIL_UNCHECKED_QUERY);
		}
	}
}
