/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : RoleService.java
 * 描述          : 
 * 作者          : 王弘
 * 日期          : 2012-11-15
 * 公司          : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *     1     2012-11-15       王弘               新建文件
 */
package com.menksoft.cms.application.permission.service;

import java.io.Serializable;
import java.util.List;

import com.menksoft.cms.application.permission.entity.Role;

/**
 * @author wangh
 *
 */
public interface RoleService {

	/**
	 * 新增角色信息
	 * @param role 角色对象
	 * @throws Throwable 保存过程中发生错误抛出异常。
	 */
	public abstract void saveRole(Role role) throws Throwable;

	/**
	 * 更新角色信息
	 * @param role 角色对象
	 * @throws Throwable 更新过程中发生错误抛出异常。
	 */
	public abstract void updateRole(Role role) throws Throwable;

	/**
	 * 根据Id删除角色
	 * @param id 角色Id
	 * @throws Throwable 删除过程发生错误时抛出异常。
	 */
	public abstract void deleteById(Serializable id) throws Throwable;

	/**
	 * 批量删除角色
	 * @param ids 角色Id的数组
	 * @throws Throwable 删除过程发生错误时抛出异常。
	 */
	public abstract void deleteBatch(Serializable[] ids) throws Throwable;

	/**
	 * 获取所有角色的集合除内置角色
	 * @return 角色的集合
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract List<Role> getAllRole() throws Throwable;

	/**
	 * 根据Id获取角色对象
	 * @param id 角色Id
	 * @return 角色对象
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract Role getRoleById(Serializable id) throws Throwable;

	/**
	 * 判断是否存在给定名称的角色
	 * @param role 角色
	 * @return 如果存在，返回true；否则返回false；执行过程中发生错误时，返回true。
	 */
	public abstract boolean isExist(Role role);
	
	/**
	 * 保存角色权限
	 * @param role 角色对象
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public void saveRoleResource(Role role) throws Throwable;
	
	/**
	 * 查询所有权限
	 * @return
	 * @throws Throwable
	 */
	public abstract List<Role> queryAllRole() throws Throwable;

}