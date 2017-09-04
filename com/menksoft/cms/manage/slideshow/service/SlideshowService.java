/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : SlideshowService.java
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
package com.menksoft.cms.manage.slideshow.service;

import java.io.Serializable;
import java.util.List;

import com.menksoft.cms.manage.slideshow.entity.Slideshow;
import com.menksoft.cms.manage.slideshow.entity.SlideshowFile;

/**
 * @author wangh
 *
 */
public interface SlideshowService {

	/**
	 * 新增幻灯片信息
	 * @param slideshowType 幻灯片对象
	 * @throws Throwable 保存过程中发生错误抛出异常。
	 */
	public abstract void saveSlideshow(Slideshow slideshow) throws Throwable;

	/**
	 * 更新幻灯片信息
	 * @param slideshowType 幻灯片对象
	 * @throws Throwable 更新过程中发生错误抛出异常。
	 */
	public abstract void updateSlideshow(Slideshow slideshow) throws Throwable;

	/**
	 * 根据Id删除幻灯片
	 * @param id 幻灯片Id
	 * @throws Throwable 删除过程发生错误时抛出异常。
	 */
	public abstract void deleteById(Serializable id) throws Throwable;

	/**
	 * 批量删除幻灯片
	 * @param ids 幻灯片Id的数组
	 * @throws Throwable 删除过程发生错误时抛出异常。
	 */
	public abstract void deleteBatch(String ids) throws Throwable;

	/**
	 * 根据Id获取幻灯片对象
	 * @param id 幻灯片Id
	 * @return 幻灯片对象
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract Slideshow getSlideshowById(Serializable id)
			throws Throwable;

	/**
	 * 获取所有幻灯片对象
	 * @return 幻灯片对象集合
	 * @throws Throwable 执行过程中发生错误时抛出异常。
	 */
	public abstract List<Slideshow> getAllSlidesshow() throws Throwable;

	/**
	 * 根据幻灯片Id删除改幻灯片对应的所有图片
	 * @param id 幻灯片Id
	 */
	public abstract void deleteSlideshowFilesBySlidesId(Serializable id) throws Throwable;
	
	/**
	 * 前台获取幻灯片
	 * @param slidwshowId
	 * @return
	 * @throws Throwable
	 */
	public List<SlideshowFile> getSlideshowFileByWebsite(long slidwshowId)throws Throwable;
}