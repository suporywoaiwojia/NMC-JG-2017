package com.menksoft.cms.manage.website.entity.behavior.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.OrderPattern;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.website.entity.Links;
import com.menksoft.cms.manage.website.entity.behavior.LinkBehavior;

@Repository("linkBehavior")
public class LinksBehaviorImpl implements LinkBehavior{

	@Resource
	private HibernateTemplate hibernateTemplate;
	@Override
	public PagingTools<Links> getAllLinks(Integer startIndex, Integer count)
			throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(Links.class);
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("id", OrderPattern.desc);
		return  hibernateTemplate.getEntitiesByDetachedCriteriaWithPagingAndOrders
				(criteria, startIndex, count,orders);
	}

	@Override
	public void saveLink(Links link) throws Throwable {
		hibernateTemplate.save(link);
	}

	@Override
	public Links getLinkById(long id) throws Throwable {
		Links link=null;
		link=hibernateTemplate.getEntityById(Links.class, id);
		return link;
	}

	@Override
	public void updateLink(Links link) throws Throwable {
			hibernateTemplate.update(link);
	}

	@Override
	public void deleteLinks(String ids) throws Throwable {
		hibernateTemplate.executeSql("delete from r_tab_links where id in("+ids+")", null);
		
	}

	@Override
	public List<Links> getLinksByName(String name) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Links.class);
		detachedCriteria.add(Restrictions.eq("name", name));
		return hibernateTemplate.getEntitiesByDetachedCriteria(detachedCriteria);
	}

	@Override
	public List<Links> getLinkByNameAndId(String name, long id) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Links.class);
		detachedCriteria.add(Restrictions.eq("name", name));
		detachedCriteria.add(Restrictions.ne("id", id));
		return hibernateTemplate.getEntitiesByDetachedCriteria(detachedCriteria);
	}



	@Override
	public List<Links> getAllLinks(DetachedCriteria criteria) throws Throwable {
		return hibernateTemplate.getEntitiesByDetachedCriteria(criteria);
	}

}
