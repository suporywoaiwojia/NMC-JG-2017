package com.menksoft.cms.manage.slideshow.entity.behavior;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.menksoft.cms.manage.slideshow.entity.SlideshowType;



public interface SlideshowTypeBehavior {

	/**
	 * 新增幻灯片类型
	 * @param slideshowType 幻灯片类型对象
	 * @throws Throwable 保存过程中发生错误时抛出异常。
	 * 例如：对象为空或对象的Id不为0(持久态)
	 */
	public abstract void saveSlideshowType(SlideshowType slideshowType)
			throws Throwable;

	/**
	 * 删除幻灯片类型
	 * @param slideshowType 幻灯片类型对象
	 * @throws Throwable 删除过程中发生错误时抛出异常。
	 * 例如：对象为空或对象的Id为0(非持久态)
	 */
	public abstract void deleteSlideshowType(SlideshowType slideshowType)
			throws Throwable;

	/**
	 * 根据幻灯片类型Id删除对应的幻灯片类型
	 * @param id 幻灯片类型Id
	 * @throws Throwable 删除过程中发生错误时抛出异常。
	 */
	public abstract void deleteSlideshowTypeById(Serializable id) throws Throwable;

	/**
	 * 更新幻灯片类型信息
	 * @param slideshowType 幻灯片类型对象
	 * @throws Throwable 更新过程中发生错误时抛出异常。
	 * 例如：对象为空或对象的Id为0(非持久态)
	 */
	public abstract void updateSlideshowType(SlideshowType slideshowType)
			throws Throwable;

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
	 * 根据Id获取幻灯片类型对象
	 * @param id 幻灯片类型Id
	 * @return 幻灯片类型对象
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract SlideshowType getSlideshowTypeById(Serializable id)
			throws Throwable;

	/**
	 * 获取系统中所有幻灯片类型
	 * @return 幻灯片类型的集合
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract List<SlideshowType> getAllSlideshowType() throws Throwable;

	/**
	 * 根据名称获取幻灯片类型对象
	 * @param name 幻灯片类型名称
	 * @return 幻灯片类型对象
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract SlideshowType getSlideshowTypeByName(String name) throws Throwable;
	
	/**
	 * 判断是否存在指定名称的幻灯片类型
	 * @param slideshowType 幻灯片类型
	 * @return 如果存在，返回true, 否则，返回false；执行过程中发生错误时，返回true。
	 */
	public abstract boolean isExist(SlideshowType slideshowType);
}