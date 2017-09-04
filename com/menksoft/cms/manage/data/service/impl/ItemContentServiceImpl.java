package com.menksoft.cms.manage.data.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.data.entity.ItemContent;
import com.menksoft.cms.manage.data.entity.Catalog;
import com.menksoft.cms.manage.data.entity.Item;
import com.menksoft.cms.manage.data.service.ItemContentService;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.entity.ContentFile;
import com.menksoft.cms.manage.webContent.entity.DataType;
import com.menksoft.cms.manage.webContent.service.ContentFileService;

@Service("ItemContentService")
public class ItemContentServiceImpl implements ItemContentService {
	 @Resource 
	    private HibernateTemplate hibernateTemplate;
     @Resource
     private ContentFileService contentFileService;
		/**
		 * 创建新实体
		 * @param itemContent
		 * @throws Throwable
		 */
		@Override
		public void save(ItemContent itemContent) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.save(itemContent);
		}
		/**
		 * 更新实体
		 * @param itemContent
		 * @throws Throwable
		 */
		@Override
		public void update(ItemContent itemContent) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.update(itemContent);
		}



		/**
		 * 查询所有的数据
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<ItemContent> getItemContents() throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ItemContent.class);
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
		public ItemContent getItemContentbyId(long id) throws Throwable {
			// TODO Auto-generated method stub
			ItemContent itemContent = hibernateTemplate.getEntityById(ItemContent.class, id);
			return itemContent;
		}
		/**
		 * 检查实体是否重复
		 * @param itemContent 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<ItemContent> checkRepeat(ItemContent itemContent) throws Throwable {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM r_data_itemContent WHERE id <>"+itemContent.getId();
//			String sqlStr = "select * from r_data_itemContent where bm='"+itemContent.getBm()+"or name='"+itemContent.getName()+"'";
			return hibernateTemplate.getEntitiesBySql(ItemContent.class, sql, null);
		}
		/**
		 * 依据栏目的id查询该栏目下的内容
		 * @param id 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public PagingTools<ItemContent> getItemContentsByItem(Item item,Integer startIndex, Integer count) throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria =DetachedCriteria.forClass(ItemContent.class);
			
			detachedCriteria.add(Restrictions.eq("contentsItem",item));
			return hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
			
		}
		@Override
		public PagingTools<ItemContent> getItemContentsByCatalog(
				Catalog catalog, Integer startIndex, Integer count)
				throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria =DetachedCriteria.forClass(ItemContent.class);
			
			detachedCriteria.add(Restrictions.eq("contentsCatalog",catalog));
			return hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
		}
		@Override
		public PagingTools<ItemContent> queryItems(ItemContent itemContent,
				Integer startIndex, Integer count) throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ItemContent.class);
			
			String name = itemContent.getName();
			if (name!=null&&name!="") {
				detachedCriteria.add(Restrictions.eq("name", name));
			}
			String page = itemContent.getPage();
			if (page!=null&&page!="") {
				detachedCriteria.add(Restrictions.eq("page", page));
			}
			String state = itemContent.getState();
			if (state!=null&&state!="") {
				detachedCriteria.add(Restrictions.eq("state", state));
			}
			Catalog catalog = itemContent.getContentsCatalog();
			//栏目
			if (catalog!=null) {
				detachedCriteria.add(Restrictions.eq("contentsCatalog", catalog));
			}
			Item item = itemContent.getContentsItem();
			if (item!=null) {
				detachedCriteria.add(Restrictions.eq("contentsItem", item));
			}
			return hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
		}
		
		/* (non-Javadoc)
		 * @see com.menksoft.cms.manage.data.service.ItemContentService#delete(java.util.List)
		 * 删除列表中的内容,首先删除关联的附件，然后删除实体
		 */
		@Override
		public String delete(String id) throws Throwable {
			// TODO Auto-generated method stub
			//删除与itemcontent相关的附件,这里为批量删除做准备
			String[] list =  id.split(",");
			Integer length = list.length;
			if (length>0) {
				for (int i = 0; i < length; i++) {
					 List<ContentFile> listContentFiles = getItemContentbyId(Long.valueOf(list[i])).getContentFiles();
			            Integer contentFCount = listContentFiles.size();
			            if (contentFCount>0) {
			            	contentFileService.deleteFiles(listContentFiles);
						}
				}
			}
			
			//删除实体
			hibernateTemplate.executeSql("delete from r_data_item_content where id in("+id+")", null);
			
			return "success";
		}
		
}
