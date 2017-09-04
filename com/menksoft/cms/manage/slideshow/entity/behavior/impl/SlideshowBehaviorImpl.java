/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : SlideshowBehaviorImpl.java
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.OrderPattern;
import com.menksoft.cms.manage.slideshow.entity.Slideshow;
import com.menksoft.cms.manage.slideshow.entity.behavior.SlideshowBehavior;

/**
 * @author wangh
 * 幻灯片的行为类，负责实现幻灯片基本的增、删、改操作以及相关的查询操作
 */
@Repository("slideshowBehavior")
public class SlideshowBehaviorImpl implements SlideshowBehavior {
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.entity.behavior.impl.SlideshowBehavior#saveSlideshow(com.menksoft.cms.manage.slideshow.entity.Slideshow)
	 */
	@Override
	public void saveSlideshow(Slideshow slideshow) throws Throwable {
		hibernateTemplate.save(slideshow);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.entity.behavior.impl.SlideshowBehavior#deleteSlideshow(com.menksoft.cms.manage.slideshow.entity.Slideshow)
	 */
	@Override
	public void deleteSlideshow(Slideshow slideshow) throws Throwable {
		hibernateTemplate.delete(slideshow);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.entity.behavior.impl.SlideshowBehavior#deleteSlideshowById(java.io.Serializable)
	 */
	@Override
	public void deleteSlideshowById(Serializable id) throws Throwable {
		hibernateTemplate.deleteById(Slideshow.class, "id", id);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.entity.behavior.impl.SlideshowBehavior#updateSlideshow(com.menksoft.cms.manage.slideshow.entity.Slideshow)
	 */
	@Override
	public void updateSlideshow(Slideshow slideshow) throws Throwable {
		hibernateTemplate.update(slideshow);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.entity.behavior.impl.SlideshowBehavior#updateBySql(java.lang.String, java.util.Map)
	 */
	@Override
	public int updateBySql(String sql, Map<String,Object> properties) throws Throwable {
		return hibernateTemplate.executeSql(sql, properties);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.entity.behavior.impl.SlideshowBehavior#getSlideshowById(java.io.Serializable)
	 */
	@Override
	public Slideshow getSlideshowById(Serializable id) throws Throwable {
		return hibernateTemplate.getEntityById(Slideshow.class, id);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.entity.behavior.impl.SlideshowBehavior#getSlideshowByDetachedCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	@Override
	public Slideshow getSlideshowByDetachedCriteria(DetachedCriteria detachedCriteria) throws Throwable {
		return hibernateTemplate.getEntityByDetachedCriteria(detachedCriteria);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.entity.behavior.impl.SlideshowBehavior#getAllSlidesshow()
	 */
	@Override
	public List<Slideshow> getAllSlidesshow() throws Throwable {
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("creatdate", OrderPattern.asc);
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Slideshow.class);
		detachedCriteria.setFetchMode("type", FetchMode.JOIN);
		
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(detachedCriteria, orders);
	}
}
