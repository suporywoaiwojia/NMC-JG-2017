/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : SlideshowFileBehaviorImpl.java
 * 描述          : 
 * 作者          : 王弘
 * 日期          : 2012/11/13
 * 公司          : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *     1     2012/11/13    王弘               新建文件
 */
package com.menksoft.cms.manage.slideshow.entity.behavior.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.manage.slideshow.entity.SlideshowFile;
import com.menksoft.cms.manage.slideshow.entity.behavior.SlideshowFileBehavior;

/**
 * @author wangh
 * 幻灯片图片的行为类，负责实现幻灯片图片基本的增、删、改操作以及相关的查询操作
 */
@Repository("slideshowFileBehavior")
public class SlideshowFileBehaviorImpl implements SlideshowFileBehavior {
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.entity.behavior.impl.SlideshowFileBehavior#saveSlideshowFile(com.menksoft.cms.manage.slideshow.entity.SlideshowFile)
	 */
	@Override
	public void saveSlideshowFile(SlideshowFile slideshowFile) throws Throwable {
		hibernateTemplate.save(slideshowFile);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.entity.behavior.impl.SlideshowFileBehavior#deleteSlideshowFile(com.menksoft.cms.manage.slideshow.entity.SlideshowFile)
	 */
	@Override
	public void deleteSlideshowFile(SlideshowFile slideshowFile) throws Throwable {
		hibernateTemplate.delete(slideshowFile);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.entity.behavior.impl.SlideshowFileBehavior#deleteSlideshowFileById(java.io.Serializable)
	 */
	@Override
	public void deleteSlideshowFileById(Serializable id) throws Throwable {
		hibernateTemplate.deleteById(SlideshowFile.class, "id", id);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.entity.behavior.impl.SlideshowFileBehavior#updateBySql(java.lang.String, java.util.Map)
	 */
	@Override
	public int updateBySql(String sql, Map<String,Object> properties) throws Throwable {
		return hibernateTemplate.executeSql(sql, properties);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.entity.behavior.impl.SlideshowFileBehavior#getSlideshowFileById(java.io.Serializable)
	 */
	@Override
	public SlideshowFile getSlideshowFileById(Serializable id) throws Throwable {
		return hibernateTemplate.getEntityById(SlideshowFile.class, id);
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.entity.behavior.SlideshowFileBehavior#getSlideshowFileByWebsite(long)
	 */
	@Override
	public List<SlideshowFile> getSlideshowFileByWebsite(long id)
			throws Throwable {
		String sql="select * from r_tab_slideshow_file where slideshow_id="+id+" order by id";
		return hibernateTemplate.getEntitiesBySql(SlideshowFile.class, sql, null);
	}
}
