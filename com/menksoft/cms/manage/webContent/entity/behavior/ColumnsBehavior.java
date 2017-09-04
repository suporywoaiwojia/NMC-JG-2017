/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : ColumnsBehavior.java
 * 描述          : 
 * 作者          : 王弘
 * 日期          : 2012-11-20
 * 公司          : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *     1     2012-11-20       王弘               新建文件
 */
package com.menksoft.cms.manage.webContent.entity.behavior;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;


import com.menksoft.cms.manage.webContent.entity.Columns;

/**
 * @author wangh
 *
 */
public interface ColumnsBehavior {

	/**
	 * 新增网站栏目
	 * @param column 栏目对象
	 * @throws Throwable 保存过程中发生错误时抛出异常。
	 * 例如：对象为空或对象的Id不为0(持久态)
	 */
	public abstract void saveColumns(Columns column) throws Throwable;

	/**
	 * 删除网站栏目
	 * @param column 栏目对象
	 * @throws Throwable 删除过程中发生错误时抛出异常。
	 * 例如：对象为空或对象的Id为0(非持久态)
	 */
	public abstract void deleteColumns(Columns column) throws Throwable;

	/**
	 * 根据网站栏目Id删除对应的网站栏目
	 * @param id 栏目Id
	 * @throws Throwable 删除过程中发生错误时抛出异常。
	 */
	public abstract void deleteColumnsById(Serializable id) throws Throwable;

	/**
	 * 更新网站栏目信息
	 * @param column 栏目对象
	 * @throws Throwable 更新过程中发生错误时抛出异常。
	 * 例如：对象为空或对象的Id为0(非持久态)
	 */
	public abstract void updateColumns(Columns column) throws Throwable;

	/**
	 * 执行一条sql语句，用于更新网站栏目对象的信息(update、delete、select)
	 * @param sql sql语句
	 * @param properties 执行语句对应的参数
	 * @return 执行语句受影响的行数
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 * 例如：执行语句为空或语句存在语法错误。
	 */
	public abstract int updateBySql(String sql, Map<String, Object> properties)
			throws Throwable;

	/**
	 * 根据Id获取网站栏目对象
	 * @param id 栏目Id
	 * @return 栏目对象
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract Columns getColumnsById(Serializable id) throws Throwable;

	/**
	 * 根据指定栏目获取该栏目下的下级栏目
	 * @return 指定栏目的下级栏目的集合
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract List<Columns> getChildColumnsByColumn(Columns column)
			throws Throwable;

	/**
	 * 根据指定栏目获取该栏目下的所有下级栏目
	 * @return 指定栏目的所有下级栏目的集合
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract List<Columns> getAllChildColumnsByColumn(Columns column,String type)
			throws Throwable;
	public abstract List<Columns> getAllChildColumnsByColumn(Columns column)
			throws Throwable;
	/**
	 * 获取栏目的根目录
	 * @return 根目录栏目
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract Columns getRootColumns() throws Throwable;
	
	/**
	 * 前台获取栏目内容
	 * @return
	 * @throws Throwable
	 */
	public abstract List<Columns> getColumnByWebsite(long id)
			throws Throwable;
	/**
	 * 根据内容查询栏目
	 * @param criteria
	 * @return
	 * @throws Throwable
	 */
	public Columns getColumnsBycontent(DetachedCriteria criteria)throws Throwable;
	
	/**
	 * 前台获取栏目内容
	 * @param criteria
	 * @return
	 * @throws Throwable
	 */
	public List<Columns> getColumnsByWebsite(DetachedCriteria criteria)throws Throwable;
	/**
	 * 获取导航BOT
	 * @return
	 * @throws Throwable
	 */
	public  List<Columns> getColumnBot(String parentPath) throws Throwable;
	
	
	public Columns getColumnBytablename(String tablename)throws Throwable;
}