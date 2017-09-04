package com.menksoft.cms.manage.webContent.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.OrderPattern;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.webContent.entity.Project;
import com.menksoft.cms.manage.webContent.service.ProjectService;
@Service("ProjectService")
public class ProjectServiceImpl implements ProjectService {
	@Resource
	private HibernateTemplate hibernateTemplate;
	@Override
	public PagingTools<Project> getProject(String language,Project pro, Integer startIndex,
			Integer count) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Project.class);
		detachedCriteria.add(Restrictions.eq("l_type", language));
		if(pro.getName()!=null&&!pro.getName().equals(""))
			detachedCriteria.add(Restrictions.ilike("name", pro.getName(),MatchMode.ANYWHERE));
		
		if(pro.getNo()!=null&&!pro.getNo().equals(""))
			detachedCriteria.add(Restrictions.ilike("no", pro.getNo(),MatchMode.ANYWHERE));
		if(pro.getYear()!=null&&!pro.getYear().equals(""))
			detachedCriteria.add(Restrictions.eq("year", pro.getYear()));
		
		if(pro.getColumn()!=null&&pro.getColumn().getId()!=0){
			detachedCriteria.add(Restrictions.eq("column", pro.getColumn()));
		}
		if(pro.getState()!=null&&!pro.getState().equals(""))
			detachedCriteria.add(Restrictions.eq("state", pro.getState()));
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
			orders.put("id", OrderPattern.desc);
		PagingTools<Project> pt=hibernateTemplate.getEntitiesByDetachedCriteriaWithPagingAndOrders(detachedCriteria, startIndex, count,orders);
		return pt;
	}
	@Override
	public void save(Project pro) throws Throwable {
		hibernateTemplate.save(pro);
		
	}
	@Override
	public void update(Project pro) throws Throwable {
		
		hibernateTemplate.update(pro);
	}
	@Override
	public void delete(String ids) throws Throwable {
		String sql="delete from r_tab_project where id in("+ids+")";
		hibernateTemplate.executeSql( sql, null);
		
	}
	@Override
	public Project getProjectByid(long id) throws Throwable {
		return hibernateTemplate.getEntityById(Project.class, id);
	}
	@Override
	public List<Project> getList(String language) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Project.class);
		detachedCriteria.add(Restrictions.eq("l_type", language));
		detachedCriteria.add(Restrictions.or(Restrictions.eq("state", "3"),Restrictions.eq("state", "5")));
		return hibernateTemplate.getEntitiesByDetachedCriteria(detachedCriteria);
	}
	@Override
	public PagingTools<Project> getProject_check(String language, Project pro,
			Integer startIndex, Integer count,String user) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Project.class);
		detachedCriteria.add(Restrictions.eq("l_type", language));
		detachedCriteria.add(Restrictions.eq("approve_id", user));
		if(pro.getName()!=null&&!pro.getName().equals(""))
			detachedCriteria.add(Restrictions.ilike("name", pro.getName(),MatchMode.ANYWHERE));
		
		if(pro.getNo()!=null&&!pro.getNo().equals(""))
			detachedCriteria.add(Restrictions.ilike("no", pro.getNo(),MatchMode.ANYWHERE));
		if(pro.getYear()!=null&&!pro.getYear().equals(""))
			detachedCriteria.add(Restrictions.eq("year", pro.getYear()));
		
		if(pro.getColumn()!=null&&pro.getColumn().getId()!=0){
			detachedCriteria.add(Restrictions.eq("column", pro.getColumn()));
		}
		detachedCriteria.add(Restrictions.or(Restrictions.eq("state", "1"),Restrictions.eq("state", "2")));
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
			orders.put("id", OrderPattern.desc);
		PagingTools<Project> pt=hibernateTemplate.getEntitiesByDetachedCriteriaWithPagingAndOrders(detachedCriteria, startIndex, count,orders);
		return pt;
	}

}
