package com.menksoft.cms.manage.webContent.service.impl;


import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.webContent.entity.Center;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.service.CenterService;

@Service("centerservice")
public class CenterServiceImpl implements CenterService {
	@Resource
    private HibernateTemplate hibernateTemplate;
	@Override
	public void save(Center pro) throws Throwable {
		// TODO Auto-generated method stub
		hibernateTemplate.save(pro);
	}

	@Override
	public void delete(String ids) throws Throwable {
		// TODO Auto-generated method stub
		//此时应该将字符串ids分割为id数组，遍历删除实体
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			hibernateTemplate.deleteById(Center.class, "id", idArray[i]);
		}
		
	}

	@Override
	public void update(Center pro) throws Throwable {
		// TODO Auto-generated method stub
		hibernateTemplate.update(pro);
	}

	@Override
	public PagingTools<Center> getList(Columns column,Integer startIndex, Integer count) throws Throwable {
		// TODO Auto-generated method stub
		PagingTools<Center> list = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Center.class);
		//此处修改为对象
		detachedCriteria.add(Restrictions.eq("parent", column));
	    list = hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
		return list;
	}

}
