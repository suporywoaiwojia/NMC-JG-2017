/**
 * 项目名称: 草原文化创意资源平台
 * 文件名     : SlideshowTypeBehaviorImpl.java
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

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.OrderPattern;
import com.menksoft.cms.manage.slideshow.entity.SlideshowType;
import com.menksoft.cms.manage.slideshow.entity.behavior.SlideshowTypeBehavior;



/**
 * @author wangh
 * 
 * 幻灯片类型的行为类，负责实现幻灯片类型基本的增、删、改操作以及相关的查询操作
 */
@Repository("slideshowTypeBehavior")
public class SlideshowTypeBehaviorImpl implements SlideshowTypeBehavior {
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	/* (non-Javadoc)
	 * @see com.anda.cms.manage.slideshow.entity.behavior.impl.SlideshowTypeBehavior#saveSlideshowType(com.anda.cms.manage.slideshow.entity.SlideshowType)
	 */
	@Override
	public void saveSlideshowType(SlideshowType slideshowType) throws Throwable {
		hibernateTemplate.save(slideshowType);
	}
	
	/* (non-Javadoc)
	 * @see com.anda.cms.manage.slideshow.entity.behavior.impl.SlideshowTypeBehavior#deleteSlideshowType(com.anda.cms.manage.slideshow.entity.SlideshowType)
	 */
	@Override
	public void deleteSlideshowType(SlideshowType slideshowType) throws Throwable {
		hibernateTemplate.delete(slideshowType);
	}
	
	/* (non-Javadoc)
	 * @see com.anda.cms.manage.slideshow.entity.behavior.impl.SlideshowTypeBehavior#deleteSlideshowTypeById(long)
	 */
	@Override
	public void deleteSlideshowTypeById(Serializable id) throws Throwable {
		hibernateTemplate.deleteById(SlideshowType.class, "id", id);
	}
	
	/* (non-Javadoc)
	 * @see com.anda.cms.manage.slideshow.entity.behavior.impl.SlideshowTypeBehavior#updateSlideshowType(com.anda.cms.manage.slideshow.entity.SlideshowType)
	 */
	@Override
	public void updateSlideshowType(SlideshowType slideshowType) throws Throwable {
		hibernateTemplate.update(slideshowType);
	}
	
	/* (non-Javadoc)
	 * @see com.anda.cms.manage.slideshow.entity.behavior.impl.SlideshowTypeBehavior#updateByHql(java.lang.String, java.util.Map)
	 */
	@Override
	public int updateBySql(String sql, Map<String,Object> properties) throws Throwable {
		return hibernateTemplate.executeSql(sql, properties);
	}
	
	/* (non-Javadoc)
	 * @see com.anda.cms.manage.slideshow.entity.behavior.impl.SlideshowTypeBehavior#getSlideshowTypeById(long)
	 */
	@Override
	public SlideshowType getSlideshowTypeById(Serializable id) throws Throwable {
		return hibernateTemplate.getEntityById(SlideshowType.class, id);
	}
	
	/* (non-Javadoc)
	 * @see com.anda.cms.manage.slideshow.entity.behavior.impl.SlideshowTypeBehavior#getAllSlideshowType()
	 */
	@Override
	public List<SlideshowType> getAllSlideshowType() throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SlideshowType.class);
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("creatdate", OrderPattern.asc);
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(detachedCriteria, orders);
	}

	/* (non-Javadoc)
	 * @see com.anda.cms.manage.slideshow.entity.behavior.impl.SlideshowTypeBehavior#getSlideshowTypeByName(String)
	 */
	@Override
	public SlideshowType getSlideshowTypeByName(String name) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SlideshowType.class);
		detachedCriteria.add(Restrictions.eq("name", name));
		
		return hibernateTemplate.getEntityByDetachedCriteria(detachedCriteria);
	}

	/* (non-Javadoc)
	 * @see com.anda.cms.manage.slideshow.entity.behavior.impl.SlideshowTypeBehavior#isExist(String)
	 */
	@Override
	public boolean isExist(SlideshowType slideshowType) {
		boolean isExist = true;
		try {
			String name = slideshowType.getName();
			SlideshowType existedSlideshowType = getSlideshowTypeByName(name); 
			if (existedSlideshowType == null) {
				isExist = false;
			}else if(slideshowType.getId() > 0 && slideshowType.getId() == existedSlideshowType.getId()) {
				isExist = false;
			}
		} catch (Throwable e) {
			isExist = true;
		}
		return isExist;
	}
}
