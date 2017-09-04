/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : ColumnsBehaviorImpl.java
 * 描述          : 
 * 作者          : 王弘
 * 日期          : 2012-11-15
 * 公司          : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *     1     2012-11-15    王弘               新建文件
 */
package com.menksoft.cms.manage.webContent.entity.behavior.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.OrderPattern;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.entity.behavior.ColumnsBehavior;

/**
 * @author wangh
 * 网站栏目的行为类，负责实现网站栏目基本的增、删、改操作以及相关的查询操作
 */
@Repository("columnsBehavior")
public class ColumnsBehaviorImpl implements ColumnsBehavior {
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.webContent.entity.behavior.impl.ColumnsBehavior#saveColumns(com.menksoft.cms.manage.webContent.entity.Columns)
	 */
	@Override
	public void saveColumns(Columns column) throws Throwable {
		hibernateTemplate.save(column);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.webContent.entity.behavior.impl.ColumnsBehavior#deleteColumns(com.menksoft.cms.manage.webContent.entity.Columns)
	 */
	@Override
	public void deleteColumns(Columns column) throws Throwable {
		hibernateTemplate.delete(column);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.webContent.entity.behavior.impl.ColumnsBehavior#deleteColumnsById(java.io.Serializable)
	 */
	@Override
	public void deleteColumnsById(Serializable id) throws Throwable {
		hibernateTemplate.deleteById(Columns.class, "id", id);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.webContent.entity.behavior.impl.ColumnsBehavior#updateColumns(com.menksoft.cms.manage.webContent.entity.Columns)
	 */
	@Override
	public void updateColumns(Columns column) throws Throwable {
		hibernateTemplate.update(column);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.webContent.entity.behavior.impl.ColumnsBehavior#updateBySql(java.lang.String, java.util.Map)
	 */
	@Override
	public int updateBySql(String sql, Map<String,Object> properties) throws Throwable {
		return hibernateTemplate.executeSql(sql, properties);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.webContent.entity.behavior.impl.ColumnsBehavior#getColumnsById(java.io.Serializable)
	 */
	@Override
	public Columns getColumnsById(Serializable id) throws Throwable {
		return hibernateTemplate.getEntityById(Columns.class, id);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.webContent.entity.behavior.impl.ColumnsBehavior#getChildColumnsByColumn(com.menksoft.cms.manage.webContent.entity.Columns)
	 */
	@Override
	public List<Columns> getChildColumnsByColumn(Columns column) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Columns.class);
		detachedCriteria.add(Restrictions.eq("parent", column));
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("columnOrder", OrderPattern.asc);
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(detachedCriteria, orders);
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.webContent.entity.behavior.impl.ColumnsBehavior#getAllChildColumnsByColumn(com.menksoft.cms.manage.webContent.entity.Columns)
	 */
	@Override
	public List<Columns> getAllChildColumnsByColumn(Columns column,String type) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Columns.class);
		detachedCriteria.add(Restrictions.isNotNull("parent"));
		detachedCriteria.add(Restrictions.like("parentPath", column.getParentPath()==null ? "" : column.getParentPath(), MatchMode.END));
		detachedCriteria.add(Restrictions.eq("columnType", type));
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("parentPath", OrderPattern.asc);
		orders.put("columnOrder", OrderPattern.asc);
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(detachedCriteria, orders);
	}
	public List<Columns> getAllChildColumnsByColumn(Columns column) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Columns.class);
		detachedCriteria.add(Restrictions.isNotNull("parent"));
		detachedCriteria.add(Restrictions.like("parentPath", column.getParentPath()==null ? "" : column.getParentPath(), MatchMode.END));

		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("parentPath", OrderPattern.asc);
		orders.put("columnOrder", OrderPattern.asc);
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(detachedCriteria, orders);
	}
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.webContent.entity.behavior.impl.ColumnsBehavior#getRootColumns()
	 */
	@Override
	public Columns getRootColumns() throws Throwable{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Columns.class);
		detachedCriteria.add(Restrictions.isNull("parent"));

		return hibernateTemplate.getEntityByDetachedCriteria(detachedCriteria);
	}

	@Override
	public List<Columns> getColumnByWebsite(long id) throws Throwable {
		
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("parentPath", OrderPattern.asc);
		orders.put("columnOrder", OrderPattern.asc);
		StringBuffer sql=new StringBuffer();
		sql.append("select * from r_tab_column where state=1");
		if(id==0)
			sql.append(" and parent_id=1");
		if(id>0)
			sql.append(" and parent_id="+id);
	
		sql.append(" order by parentPath,columnOrder");
		return hibernateTemplate.getEntitiesBySql(Columns.class, sql.toString(), null);
	}

	@Override
	public Columns getColumnsBycontent(DetachedCriteria criteria)
			throws Throwable {
		return hibernateTemplate.getEntityByDetachedCriteria(criteria);
	}

	@Override
	public List<Columns> getColumnsByWebsite(DetachedCriteria criteria)
			throws Throwable {
		
		return hibernateTemplate.getEntitiesByDetachedCriteria(criteria);
	}

	@Override
	public List<Columns> getColumnBot(String parentPath) throws Throwable {
		
		StringBuffer sql=new StringBuffer();
		sql.append("select * from r_tab_column where id in("+parentPath+")");
		sql.append(" order by id");
		return hibernateTemplate.getEntitiesBySql(Columns.class, sql.toString(), null);
	}

	@Override
	public Columns getColumnBytablename(String tablename) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Columns.class);
		detachedCriteria.add(Restrictions.eq("tablename", tablename));
		return hibernateTemplate.getEntityByDetachedCriteria(detachedCriteria);
	}
}
