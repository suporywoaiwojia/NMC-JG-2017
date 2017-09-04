/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : ColumnsService.java
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
package com.menksoft.cms.manage.webContent.service;

import java.io.Serializable;
import java.util.List;

import com.menksoft.cms.manage.webContent.entity.Columns;

/**
 * @author wangh
 *
 */
public interface ColumnsService {

	public abstract void saveColumns(Columns column) throws Throwable;

	public abstract Columns updateColumns(Columns column) throws Throwable;

	public abstract void deleteBatch(Serializable[] parentPaths)
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
	 * 自定义标签获取栏目
	 * @param parentid 上级ID
	 * @param lg 语言标签
	 * @return
	 * @throws Throwable
	 */
	public List<Columns> getColumnByWebsite(long parentid)throws Throwable;

	/**
	 * 获取所有栏目，除parent为空的顶级栏目以外
	 * @return
	 * @throws Throwable
	 */
	public List<Columns> getAllColumn()throws Throwable;
	
	/**
	 * 获取导航BOT
	 * @return
	 * @throws Throwable
	 */
	public List<Columns> getBotColumn(String parentPath)throws Throwable;
	
	
	/**
	 * 根据表名获取项目属性
	 * @param tablename
	 * @return
	 * @throws Throwable
	 */
	public Columns getColumnBytable(String tablename)throws Throwable;
	
	public List<Columns> getColumnBytype(String columnType)throws Throwable;
	
	public Columns getColumnByno(String no)throws Throwable;
}