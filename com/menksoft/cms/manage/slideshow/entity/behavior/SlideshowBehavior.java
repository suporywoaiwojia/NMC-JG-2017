/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : SlideshowBehavior.java
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
package com.menksoft.cms.manage.slideshow.entity.behavior;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.menksoft.cms.manage.slideshow.entity.Slideshow;

/**
 * @author wangh
 *
 */
public interface SlideshowBehavior {

	/**
	 * 新增幻灯片
	 * @param slideshow 幻灯片对象
	 * @throws Throwable 保存过程中发生错误时抛出异常。
	 * 例如：对象为空或对象的Id不为0(持久态)
	 */
	public abstract void saveSlideshow(Slideshow slideshow) throws Throwable;

	/**
	 * 删除幻灯片
	 * @param slideshow 幻灯片对象
	 * @throws Throwable 删除过程中发生错误时抛出异常。
	 * 例如：对象为空或对象的Id为0(非持久态)
	 */
	public abstract void deleteSlideshow(Slideshow slideshow) throws Throwable;

	/**
	 * 根据幻灯片Id删除对应的幻灯片
	 * @param id 幻灯片Id
	 * @throws Throwable 删除过程中发生错误时抛出异常。
	 */
	public abstract void deleteSlideshowById(Serializable id) throws Throwable;

	/**
	 * 更新幻灯片信息
	 * @param slideshow 幻灯片对象
	 * @throws Throwable 更新过程中发生错误时抛出异常。
	 * 例如：对象为空或对象的Id为0(非持久态)
	 */
	public abstract void updateSlideshow(Slideshow slideshow) throws Throwable;

	/**
	 * 执行一条sql语句，用于更新幻灯片类型对象的信息(update、delete、select)
	 * @param sql sql语句
	 * @param properties 执行语句对应的参数
	 * @return 执行语句受影响的行数
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 * 例如：执行语句为空或语句存在语法错误。
	 */
	public abstract int updateBySql(String sql, Map<String, Object> properties)
			throws Throwable;

	/**
	 * 根据Id获取幻灯片对象
	 * @param id 幻灯片Id
	 * @return 幻灯片对象
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract Slideshow getSlideshowById(Serializable id)
			throws Throwable;

	/**
	 * 根据条件获取幻灯片对象
	 * @param detachedCriteria 条件对象
	 * @return 幻灯片对象
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract Slideshow getSlideshowByDetachedCriteria(
			DetachedCriteria detachedCriteria) throws Throwable;

	/**
	 * 获取所有幻灯片对象
	 * @return 幻灯片对象集合
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract List<Slideshow> getAllSlidesshow() throws Throwable;

}