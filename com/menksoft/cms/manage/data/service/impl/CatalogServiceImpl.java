package com.menksoft.cms.manage.data.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.data.entity.Catalog;
import com.menksoft.cms.manage.data.entity.Item;
import com.menksoft.cms.manage.data.entity.ItemContent;
import com.menksoft.cms.manage.data.service.CatalogService;
import com.menksoft.cms.manage.data.service.ItemService;
import com.menksoft.cms.manage.webContent.entity.ContentFile;
import com.menksoft.cms.manage.webContent.service.ContentFileService;

@Service("CatalogService")
public class CatalogServiceImpl implements CatalogService {
	 @Resource 
	    private HibernateTemplate hibernateTemplate;
     @Resource
     private ItemService itemService;
     @Resource
     private ContentFileService contentFileService;
     
		/**
		 * 创建新实体
		 * @param catalog
		 * @throws Throwable
		 */
		@Override
		public void save(Catalog catalog) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.save(catalog);
		}
		/**
		 * 更新实体
		 * @param catalog
		 * @throws Throwable
		 */
		@Override
		public void update(Catalog catalog) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.update(catalog);
		}
		/**
		 * 删除实体
		 * @param id
		 * @param idName
		 * @throws Throwable
		 */
		@Override
		public void delete(String id ) throws Throwable {
			// TODO Auto-generated method stub
			//删除与目录相关的附件,这里为批量删除做准备
			String[] list =  id.split(",");
			Integer length = list.length;
			if (length>0) {
				for (int i = 0; i < length; i++) {
					 List<ContentFile> listContentFiles = getCatalogbyId(Long.valueOf(list[i])).getContentFiles();
			            Integer contentFCount = listContentFiles.size();
			            if (contentFCount>0) {
			            	contentFileService.deleteFiles(listContentFiles);
						}
				}
			}
           
    		
            //删除目录实体
    		hibernateTemplate.executeSql("delete from r_data_catalog where id in("+id+")", null);    					
		//	hibernateTemplate.executeSql("delete from r_data_catalog where id in("+id+")", null);
		}


		/**
		 * 查询所有的数据
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<Catalog> getCatalogs() throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Catalog.class);
			//add some condition
			return  hibernateTemplate.getEntitiesByDetachedCriteria(detachedCriteria);
		}
		/**
		 * 查询单个实体
		 * @param id 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public Catalog getCatalogbyId(long id) throws Throwable {
			// TODO Auto-generated method stub
			Catalog catalog = hibernateTemplate.getEntityById(Catalog.class, id);
			return catalog;
		}
		/**
		 * 检查实体是否重复
		 * @param catalog 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<Catalog> checkRepeat(Catalog catalog) throws Throwable {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM r_data_catalog WHERE id <>"+catalog.getId();
//			String sqlStr = "select * from r_data_catalog where bm='"+catalog.getBm()+"or name='"+catalog.getName()+"'";
			return hibernateTemplate.getEntitiesBySql(Catalog.class, sql, null);
		}
		/**
		 * 依据栏目的id查询该栏目下的内容
		 * @param id 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public PagingTools<Catalog> getCatalogsByCatalog(Catalog catalog,Integer startIndex, Integer count) throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria =DetachedCriteria.forClass(Catalog.class);
			
			String name = catalog.getName();
			if (name!=null&&name!="") {
				detachedCriteria.add(Restrictions.eq("name", name));
			}
			String page = catalog.getPageRange();
			if (page!=null&&page!="") {
				detachedCriteria.add(Restrictions.eq("pageRange", page));
			}
			//目录所属资料条目
			Item item = itemService.getItembyId(catalog.getCatalogItem().getId());
			if(item!=null){
			detachedCriteria.add(Restrictions.eq("catalogItem",item));
			}
			return hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
			
		}
		
		@Override
		public PagingTools<Catalog> getCatalogsByItem(Item item,
				Integer startIndex, Integer count) throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria =DetachedCriteria.forClass(Catalog.class);
			detachedCriteria.add(Restrictions.eq("catalogItem",item));
			return  hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
		}
}
