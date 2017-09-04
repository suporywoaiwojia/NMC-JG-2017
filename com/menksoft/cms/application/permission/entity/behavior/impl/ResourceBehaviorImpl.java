/**
 * Copyright (c) 2011, The David.Wang All rights reserved.
 * 
 * You can use this software for free.
 * If you want to report bug, please send mail to SeptWolves800515@gmail.com
 */
package com.menksoft.cms.application.permission.entity.behavior.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.OrderPattern;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.application.permission.entity.Resource;
import com.menksoft.cms.application.permission.entity.ResourceType;

/**
 * @author David
 *
 */
@Repository("resourceBehavior")
public class ResourceBehaviorImpl {
	@javax.annotation.Resource
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * 获取全部功能点
	 * @return
	 * @throws Throwable
	 */
	public List<Resource> getAllResources() throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(Resource.class);
		criteria.setFetchMode("roles", FetchMode.JOIN);
		
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("parent", OrderPattern.asc);
		orders.put("displayOrder", OrderPattern.asc);

		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(criteria, orders);
	}
	/**
	 * 获取主功能点
	 * @return
	 * @throws Throwable
	 */
	public List<Resource> getFatherResources() throws Throwable{
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("displayOrder", OrderPattern.asc);
		DetachedCriteria criteria = DetachedCriteria.forClass(Resource.class);
		criteria.setFetchMode("roles", FetchMode.JOIN);
		criteria.add(Restrictions.isNull("parent"));
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(criteria,orders);
	}
	
	public Resource getRootResource() throws Throwable {
		ResourceType modelResource = new ResourceType();
		modelResource.setId(-1);
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Resource.class);
		criteria.add(Restrictions.eq("type", modelResource));
//		criteria.add(Restrictions.isNull("parent"));

		return hibernateTemplate.getEntityByDetachedCriteria(criteria);
	}
	
	public List<Resource> getResourcesByUser(User user, Resource parent) throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(Resource.class);
		criteria.add(Restrictions.eq("parent", parent));
		criteria.createCriteria("roles").createCriteria("users").add(Restrictions.eq("loginId", user.getLoginId()));
		
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("displayOrder", OrderPattern.asc);
		
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(criteria,orders);
	}
	
	public List<Resource> getResourcesByUser(User user) throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(Resource.class);
		criteria.add(Restrictions.isNotNull("parent"));
		criteria.createCriteria("roles").createCriteria("users").add(Restrictions.eq("loginId", user.getLoginId()));
		
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("displayOrder", OrderPattern.asc);
		
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(criteria,orders);
	}
	
	public List<Resource> getAllRootModuleResources() throws Throwable {
		Resource root = getRootResource();
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Resource.class);
		criteria.add(Restrictions.eq("parent", root));
		
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("displayOrder", OrderPattern.asc);
		
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(criteria,orders);
	}
	
	public List<Resource> getAllModuleResources() throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(Resource.class);
		criteria.add(Restrictions.isNotNull("parent"));
		
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("parent", OrderPattern.asc);
		orders.put("displayOrder", OrderPattern.asc);
		
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(criteria,orders);
	}
	
	public List<Resource> getAllChildModuleResources() throws Throwable {
		ResourceType type = new ResourceType();
		type.setId(2);
		DetachedCriteria criteria = DetachedCriteria.forClass(Resource.class);
		criteria.add(Restrictions.eq("type", type));
		
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("parent", OrderPattern.asc);
		orders.put("displayOrder", OrderPattern.asc);
		
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(criteria,orders);
	}

	
	/**
	 * 获取子功能点
	 * @param resource	传入父功能对象
	 * @return
	 * @throws Throwable
	 */
	public List<Resource> getChildResources(Resource resource) throws Throwable{
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("displayOrder", OrderPattern.asc);
		DetachedCriteria criteria = DetachedCriteria.forClass(Resource.class);
		criteria.add(Restrictions.eq("parent", resource.getParent()));
		criteria.setFetchMode("roles", FetchMode.JOIN);
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(criteria,orders);
	}
	
	public List<Resource> getAllResourcesWithNotRoot() throws Throwable{
		DetachedCriteria criteria = DetachedCriteria.forClass(Resource.class);
		ResourceType modelResource = new ResourceType();
		modelResource.setId(1);
		ResourceType functionResource = new ResourceType();
		functionResource.setId(2);
		criteria.add(Restrictions.or(Restrictions.eq("type", modelResource), Restrictions.eq("type", functionResource)));
		criteria.add(Restrictions.isNotNull("parent"));
		
		criteria.setFetchMode("parent", FetchMode.JOIN);
		criteria.setFetchMode("roles", FetchMode.JOIN);
		
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("parent", OrderPattern.asc);
		orders.put("displayOrder", OrderPattern.asc);
		
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(criteria, orders);
	}
	
	public List<Resource> removeReduplicate(List<Resource> resources){
		List<Resource> singleResources = null;
		if(resources != null) {
			singleResources = new ArrayList<Resource>();
			String resourcesIds = ",";
			for(Resource resource : resources) {
				long id = resource.getId();
				if(resourcesIds.indexOf(","+id+",") == -1) {
					singleResources.add(resource);
					resourcesIds += (resource.getId()+",");
				}
			}
		}
		
		return singleResources;
	}
}