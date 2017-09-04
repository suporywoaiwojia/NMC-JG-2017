package com.menksoft.cms.manage.slideshow.service;

import java.io.Serializable;
import java.util.List;

import com.menksoft.cms.manage.slideshow.entity.SlideshowType;


public interface SlideshowTypeService {

	/**
	 * 新增幻灯片类型信息
	 * @param slideshowType 幻灯片类型对象
	 * @throws Throwable 保存过程中发生错误抛出异常。
	 */
	public abstract void save(SlideshowType slideshowType) throws Throwable;

	/**
	 * 更新幻灯片类型信息
	 * @param slideshowType 幻灯片类型对象
	 * @throws Throwable 更新过程中发生错误抛出异常。
	 */
	public abstract void update(SlideshowType slideshowType) throws Throwable;

	/**
	 * 根据Id删除幻灯片类型
	 * @param id 幻灯片类型Id
	 * @throws Throwable 删除过程发生错误时抛出异常。
	 */
	public abstract void deleteById(Serializable id) throws Throwable;

	/**
	 * 批量删除幻灯片类型
	 * @param ids 幻灯片类型Id的数组
	 * @throws Throwable 删除过程发生错误时抛出异常。
	 */
	public abstract void deleteBatch(Serializable[] ids) throws Throwable;

	/**
	 * 获取所有幻灯片类型的集合
	 * @return 幻灯片类型的集合
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract List<SlideshowType> getAllSlideshowType() throws Throwable;

	/**
	 * 根据Id获取幻灯片类型对象
	 * @param id 幻灯片类型Id
	 * @return 幻灯片类型对象
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract SlideshowType getSlideshowTypeById(Serializable id) throws Throwable;
	
	/**
	 * 判断是否存在给定名称的幻灯片类型
	 * @param slideshowType 幻灯片类型
	 * @return 如果存在，返回true；否则返回false；执行过程中发生错误时，返回true。
	 */
	public abstract boolean isExist(SlideshowType slideshowType);
}