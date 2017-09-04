/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : RoleBehavior.java
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
package com.menksoft.cms.application.permission.entity.behavior;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.menksoft.cms.application.permission.entity.Role;

/**
 * @author wangh
 *
 */
public interface RoleBehavior {

	/**
	 * 新增角色
	 * @param role 角色对象
	 * @throws Throwable 保存过程中发生错误时抛出异常。
	 */
	public abstract void saveRole(Role role) throws Throwable;

	/**
	 * 删除角色
	 * @param role 角色对象
	 * @throws Throwable 删除过程中发生错误时抛出异常。
	 * 例如：对象为空或对象的Id为0(非持久态)
	 */
	public abstract void deleteRole(Role role) throws Throwable;

	/**
	 * 根据角色Id删除对应的角色
	 * @param id 角色Id
	 * @throws Throwable 删除过程中发生错误时抛出异常。
	 */
	public abstract void deleteRoleById(Serializable id) throws Throwable;

	/**
	 * 更新角色信息
	 * @param role 幻灯片类型对象
	 * @throws Throwable 更新过程中发生错误时抛出异常。
	 * 例如：对象为空或对象的Id为0(非持久态)
	 */
	public abstract void updateRole(Role role) throws Throwable;

	/**
	 * 执行一条sql语句，用于更新角色对象的信息(update、delete、select)
	 * @param sql sql语句
	 * @param properties 执行语句对应的参数
	 * @return 执行语句受影响的行数
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 * 例如：执行语句为空或语句存在语法错误。
	 */
	public abstract int updateBySql(String sql, Map<String, Object> properties)
			throws Throwable;

	/**
	 * 根据Id获取角色对象
	 * @param id 角色Id
	 * @return 角色对象
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract Role getRoleById(Serializable id) throws Throwable;

	/**
	 * 获取系统中所有角色
	 * @return 角色的集合
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract List<Role> getAllRole(DetachedCriteria detachedCriteria) throws Throwable;

	/**
	 * 根据名称获取角色对象
	 * @param name 角色名称
	 * @return 角色对象
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract Role getRoleByName(String name) throws Throwable;

	/**
	 * 判断是否存在指定名称的角色
	 * @param role 角色
	 * @return 如果存在，返回true, 否则，返回false；执行过程中发生错误时，返回true。
	 */
	public abstract boolean isExist(Role role);

	/**
	 * 生成角色主键
	 * @return 角色主键
	 */
	public abstract String buildPrimaryKey();

}