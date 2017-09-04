/**
 * 项目名称: 草原文化创意资源平台
 * 文件名    : SensitivewordsBehaviorImpl.java
 * 描述        : 敏感词数据操作
 * 作者        : 景宏
 * 日期        : 上午10:40:45
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       下午3:56:40  景宏    
 */
package com.menksoft.cms.manage.website.entity.behavior.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.OrderPattern;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.website.entity.Sensitivewords;
import com.menksoft.cms.manage.website.entity.behavior.SensitivewordsBehavior;



/**
 * 敏感词
 * @author 景宏
 * 关键词的行为类，负责实现广告基本的增、删、改操作以及相关的查询操作
 */
@Repository("sensitivewordsBehavior")
public class SensitivewordsBehaviorImpl implements SensitivewordsBehavior {
	@javax.annotation.Resource
	private HibernateTemplate hibernateTemplate;
	
	/* (non-Javadoc)
	 * @see com.anda.cms.manage.website.sensitivewords.entity.behavior.impl.sensitivewordsBehavior#getAllSensitivewords(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PagingTools<Sensitivewords> getAllSensitivewords(DetachedCriteria criteria,Integer startIndex, Integer count) 
			throws Throwable {
		return  hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging
				(criteria, startIndex, count);
	}

	/* (non-Javadoc)
	 * @see com.anda.cms.manage.website.sensitivewords.entity.behavior.SensitivewordsBehavior#Sensitivewordssave(com.anda.cms.manage.website.sensitivewords.entity.Sensitivewords)
	 */
	@Override
	public void saveSensitivewords(Sensitivewords sensitivewords) throws Throwable {
		hibernateTemplate.save(sensitivewords);
	}

	/* (non-Javadoc)
	 * @see com.anda.cms.manage.website.sensitivewords.entity.behavior.SensitivewordsBehavior#getSensitivewordsById(long)
	 */
	@Override
	public Sensitivewords getSensitivewordsById(long id) throws Throwable {
		Sensitivewords sensitivewords=null;
		sensitivewords=hibernateTemplate.getEntityById(Sensitivewords.class, id);
		return sensitivewords;
	}

	/* (non-Javadoc)
	 * @see com.anda.cms.manage.website.sensitivewords.entity.behavior.SensitivewordsBehavior#updateSensitivewords(com.anda.cms.manage.website.sensitivewords.entity.Sensitivewords)
	 */
	@Override
	public void updateSensitivewords(Sensitivewords sensitivewords) throws Throwable {
		hibernateTemplate.update(sensitivewords);
	}

	/* (non-Javadoc)
	 * @see com.anda.cms.manage.website.sensitivewords.entity.behavior.SensitivewordsBehavior#deleteSensitivewords(com.anda.cms.manage.website.sensitivewords.entity.Sensitivewords)
	 */
	@Override
	public void deleteSensitivewords(String id) throws Throwable {
		hibernateTemplate.executeSql("delete from r_tab_Sensitivewords where id in("+id+")", null);
	}

	/* (non-Javadoc)
	 * @see com.anda.cms.manage.website.sensitivewords.entity.behavior.SensitivewordsBehavior#getSensitivewordsByName(java.lang.String)
	 */
	@Override
	public List<Sensitivewords> getSensitivewordsByName(String name)
			throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Sensitivewords.class);
		detachedCriteria.add(Restrictions.eq("name", name));
		return hibernateTemplate.getEntitiesByDetachedCriteria(detachedCriteria);
	}

	/* (non-Javadoc)
	 * @see com.anda.cms.manage.website.sensitivewords.entity.behavior.SensitivewordsTypeBehavior#getSensitivewordsByNameAndId(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Sensitivewords> getSensitivewordsByNameAndId(String name, long id)
			throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Sensitivewords.class);
		detachedCriteria.add(Restrictions.eq("name", name));
		detachedCriteria.add(Restrictions.ne("id", id));
		return hibernateTemplate.getEntitiesByDetachedCriteria(detachedCriteria);
	}

	/* (non-Javadoc)
	 * @see com.anda.cms.manage.website.sensitivewords.entity.behavior.SensitivewordsBehavior#querySensitivewords()
	 */
	@Override
	public List<Sensitivewords> querySensitivewords() throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Sensitivewords.class);
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("creatdate", OrderPattern.asc);
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(detachedCriteria, orders);
	}
}
