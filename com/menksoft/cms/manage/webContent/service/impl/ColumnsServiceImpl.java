/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : ColumnsServiceImpl.java
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
package com.menksoft.cms.manage.webContent.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.entity.behavior.ColumnsBehavior;
import com.menksoft.cms.manage.webContent.service.ColumnsService;
import com.menksoft.util.Const;

/**
 * @author wangh
 * 网站栏目的业务类，负责实现栏目管理模块的各类业务操作。
 */
@Service("columnsService")
public class ColumnsServiceImpl implements ColumnsService {
	@Resource
	ColumnsBehavior columnsBehavior;
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.webContent.service.impl.ColumnsService#saveColumns(com.menksoft.cms.manage.webContent.entity.Columns)
	 */
	@Override
	public void saveColumns(Columns column) throws Throwable {
		
		
		try {
			long parentId = column.getParent().getId();
			Columns parent = columnsBehavior.getColumnsById(parentId);
			column.setParent(parent);
			String parentPath = (parent.getParentPath()==null || parent.getParentPath().trim().equals(""))
					? parent.getId()+"" 
					: parent.getParentPath()+","+parent.getId();
			column.setParentPath(parentPath);
			String [] a=parentPath.split(",");
			column.setColumnType(String.valueOf(a.length+1));
			columnsBehavior.saveColumns(column);
//			CreatFolder.mkdir(column.getColumnPath());
		} catch (Throwable e) {
			throw new RuntimeException("栏目信息"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.webContent.service.impl.ColumnsService#updateColumns(com.menksoft.cms.manage.webContent.entity.Columns)
	 */
	@Override
	public Columns updateColumns(Columns column) throws Throwable {
		
		
		try {
//			long parentId = column.getParent().getId();
//			Columns parent = columnsBehavior.getColumnsById(parentId);
//			String parentPath = (parent.getParentPath()==null || parent.getParentPath().trim().equals(""))
//					? parent.getId()+"" 
//					: parent.getParentPath()+","+parent.getId();
//			
			Columns oldColumn = columnsBehavior.getColumnsById(column.getId());
//			String [] a=parentPath.split(",");
//			oldColumn.setColumnType(String.valueOf(a.length+1));
//			oldColumn.setParent(parent);
//			oldColumn.setName(column.getName());
//			oldColumn.setType(column.getType());
//			oldColumn.setActionPath(column.getActionPath());
//			oldColumn.setSecondModel(column.getSecondModel());
//			oldColumn.setListModel(column.getListModel());
//			oldColumn.setContentModel(column.getContentModel());
//			oldColumn.setPageNum(column.getPageNum());
//			oldColumn.setColumnOrder(column.getColumnOrder());
//			oldColumn.setApprove(column.getApprove());
//			oldColumn.setViews(column.getViews());
//			oldColumn.setState(column.getState());
//			oldColumn.setParentPath(parentPath);
//			oldColumn.setAlbumModel(column.getAlbumModel());
//			columnsBehavior.updateColumns(oldColumn);
//			CreatFolder.mkdir(oldColumn.getColumnPath());
			hibernateTemplate.update(column);
			return oldColumn;
		} catch (Throwable e) {
			throw new RuntimeException("栏目信息"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.webContent.service.impl.ColumnsService#deleteBatch(java.io.Serializable[])
	 */
	@Override
	public void deleteBatch(Serializable[] parentPaths) throws Throwable {
		Assert.notEmpty(parentPaths, "栏目信息"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED);
		
		try {
			Map<String, Object> properties = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer("delete from R_TAB_COLUMN where ");
			int length = parentPaths.length;
			for(int i=0; i<length; i++) {
				properties.put("id"+i, parentPaths[i]);
				hql.append("id = :id"+i);
				hql.append(" or ");
			}
			
			hql.delete(hql.length()-4, hql.length());
			
			columnsBehavior.updateBySql(hql.toString(), properties);
		} catch (Throwable e) {
			throw new RuntimeException("栏目信息"+Const.MESSAGE_FAIL_UNCHECKED_DELETE, e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.webContent.service.impl.ColumnsService#getColumnsById(java.io.Serializable)
	 */
	@Override
	public Columns getColumnsById(Serializable id) throws Throwable {
		try {
			return columnsBehavior.getColumnsById(id);
		} catch (Throwable e) {
			throw new RuntimeException("栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.webContent.service.impl.ColumnsService#getChildColumnsByColumn(com.menksoft.cms.manage.webContent.entity.Columns)
	 */
	@Override
	public List<Columns> getChildColumnsByColumn(Columns column) throws Throwable {
		Assert.notNull(column, "栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY);
		if(column.getParent() != null) {
			Assert.isTrue(column.getParent().getId() > 0, "栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY);
		}
		try {
			return columnsBehavior.getChildColumnsByColumn(column);
		} catch (Throwable e) {
			throw new RuntimeException("栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.webContent.service.impl.ColumnsService#getAllChildColumnsByColumn(com.menksoft.cms.manage.webContent.entity.Columns)
	 */
	@Override
	public List<Columns> getAllChildColumnsByColumn(Columns column,String type) throws Throwable {
		Assert.notNull(column, "栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY);
		
		try {
			return columnsBehavior.getAllChildColumnsByColumn(column,type);
		} catch (Throwable e) {
			throw new RuntimeException("栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
		}
	}
	public List<Columns> getAllChildColumnsByColumn(Columns column) throws Throwable {
		Assert.notNull(column, "栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY);
		
		try {
			return columnsBehavior.getAllChildColumnsByColumn(column);
		} catch (Throwable e) {
			throw new RuntimeException("栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
		}
	}
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.webContent.service.impl.ColumnsService#getRootColumns()
	 */
	@Override
	public Columns getRootColumns() throws Throwable{
		return columnsBehavior.getRootColumns();
	}

	@Override
	public List<Columns> getColumnByWebsite(long parentid) throws Throwable {
		
		return columnsBehavior.getColumnByWebsite(parentid);
	}

	@Override
	public List<Columns> getAllColumn() throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Columns.class);
		detachedCriteria.add(Restrictions.isNotNull("parent"));
		return columnsBehavior.getColumnsByWebsite(detachedCriteria);
	}

	@Override
	public List<Columns> getBotColumn(String parentPath) throws Throwable {
		return columnsBehavior.getColumnBot(parentPath);
	}

	@Override
	public Columns getColumnBytable(String tablename) throws Throwable {
		// TODO Auto-generated method stub
		return columnsBehavior.getColumnBytablename(tablename);
	}

	@Override
	public List<Columns> getColumnBytype(String columnType) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Columns.class);
		detachedCriteria.add(Restrictions.eq("columnType", columnType));
		return hibernateTemplate.getEntitiesByDetachedCriteria(detachedCriteria);
	}

	@Override
	public Columns getColumnByno(String no) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Columns.class);
		detachedCriteria.add(Restrictions.eq("no", no));
		return hibernateTemplate.getEntityByDetachedCriteria(detachedCriteria);
	}


}
