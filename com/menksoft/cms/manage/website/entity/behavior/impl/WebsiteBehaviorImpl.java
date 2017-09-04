/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : Website.java
 * 描述        : 会员管理
 * 作者        : 李彦佑
 * 日期        : 2012-11-14上午10:56:58
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1   2012-11-14  李彦佑
 */
package com.menksoft.cms.manage.website.entity.behavior.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.website.entity.Website;
import com.menksoft.cms.manage.website.entity.behavior.WebsiteBehavior;

/**
 * @author lyy
 *
 */
@Repository("websiteBehavior")
public class WebsiteBehaviorImpl implements WebsiteBehavior {
	@Resource
	private HibernateTemplate hibernateTemplate;
		
	@Override
	public PagingTools<Website> getAllWebsites(Integer startIndex, Integer count)
			throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(Website.class);
		return  hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging
				(criteria, startIndex, count);
	}

	@Override
	public void saveWebsite(Website website) throws Throwable {
		hibernateTemplate.save(website);
		
	}

	@Override
	public Website getWebsiteById(long id) throws Throwable {
		Website website=null;
		website=hibernateTemplate.getEntityById(Website.class, id);
		return website;
	}

	@Override
	public void updateWebsite(Website website) throws Throwable {
		hibernateTemplate.update(website);
		
	}

	@Override
	public void deleteWebsites(Map<String, Object> deleteMap) throws Throwable {
		hibernateTemplate.executeHql("delete from r_tab_website where id=:id", deleteMap);
		
	}

	@Override
	public List<Website> getWebsitesByName(String name) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Website.class);
		detachedCriteria.add(Restrictions.eq("name", name));
		return hibernateTemplate.getEntitiesByDetachedCriteria(detachedCriteria);
	}

	@Override
	public List<Website> getWebsiteByNameAndId(String name, long id)
			throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Website.class);
		detachedCriteria.add(Restrictions.eq("name", name));
		detachedCriteria.add(Restrictions.ne("id", id));
		return hibernateTemplate.getEntitiesByDetachedCriteria(detachedCriteria);
	}

	@Override
	public boolean isExist(Website website) {
		// TODO 未实现的方法
		return false;
	}



}
