package com.menksoft.cms.manage.data.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.manage.data.entity.Item;
import com.menksoft.cms.manage.data.service.ItemService;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.entity.ContentFile;
import com.menksoft.cms.manage.webContent.entity.DataType;
import com.menksoft.cms.manage.webContent.entity.Language;
import com.menksoft.cms.manage.webContent.service.ContentFileService;
import com.menksoft.cms.manage.webContent.service.DataTypeService;
import com.menksoft.cms.manage.webContent.service.LanguageService;

@Service("ItemService")
public class ItemServiceImpl implements ItemService {
	 @Resource 
	    private HibernateTemplate hibernateTemplate;
     @Resource
     private DataTypeService dataTypeService;
     @Resource
     private LanguageService languageService;
     @Resource
     private ContentFileService contentFileService;
		/**
		 * 创建新实体
		 * @param item
		 * @throws Throwable
		 */
		@Override
		public void save(Item item) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.save(item);
		}
		/**
		 * 更新实体
		 * @param item
		 * @throws Throwable
		 */
		@Override
		public void update(Item item) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.update(item);
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
//			hibernateTemplate.delete(item);
//			hibernateTemplate.deleteById(Item.class, idName, id);
			//先删除关联的附件，置空相关的外键，然后删除实体
			String[] list =  id.split(",");
			Integer length = list.length;
			if (length>0) {
				for (int i = 0; i < length; i++) {
					 List<ContentFile> listContentFiles = getItembyId(Long.valueOf(list[i])).getContentFiles();
			            Integer contentFCount = listContentFiles.size();
			            if (contentFCount>0) {
			            	contentFileService.deleteFiles(listContentFiles);
						}
				}
			}
			hibernateTemplate.executeSql("delete from r_data_item where id in("+id+")", null);
		}


		/**
		 * 查询所有的数据
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<Item> getItems() throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Item.class);
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
		public Item getItembyId(long id) throws Throwable {
			// TODO Auto-generated method stub
			Item item = hibernateTemplate.getEntityById(Item.class, id);
			return item;
		}
		/**
		 * 检查实体是否重复
		 * @param item 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<Item> checkRepeat(Item item) throws Throwable {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM r_data_item WHERE id <>"+item.getId();
//			String sqlStr = "select * from r_data_item where bm='"+item.getBm()+"or name='"+item.getName()+"'";
			return hibernateTemplate.getEntitiesBySql(Item.class, sql, null);
		}
		/**
		 * 依据栏目的id查询该栏目下的内容
		 * @param id 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public PagingTools<Item> getItemsByColumn(Columns column,Integer startIndex, Integer count) throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria =DetachedCriteria.forClass(Item.class);
			
			detachedCriteria.add(Restrictions.eq("parent",column));
			return hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
			
		}
		/**
		 * 依据栏目的id和审核人，查询该栏目下的内容
		 * @param id approve
		 * @return
		 * @throws Throwable
		 */
		@Override
		public PagingTools<Item> getItemsByColumnAndApprove(Columns column,
				User approve, Integer startIndex, Integer count)
				throws Throwable {
			// TODO Auto-generated method stub
            DetachedCriteria detachedCriteria =DetachedCriteria.forClass(Item.class);
			detachedCriteria.add(Restrictions.eq("approve", approve));
			detachedCriteria.add(Restrictions.eq("parent",column));
			return hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
		}
		@Override
		public PagingTools<Item> queryItems(Item item, Integer startIndex,
				Integer count) throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Item.class);
			
			String name = item.getName();
			if (name!=null&&name!="") {
				detachedCriteria.add(Restrictions.eq("name", name));
			}
			String author = item.getAuthor();
			if (author!=null&&author!="") {
				detachedCriteria.add(Restrictions.eq("author", author));
			}
            //添加语言
			Language language =  languageService.getLanguagebyId(item.getItemLanguage().getId());
			
			if (language!=null&&(!language.getName().equals("全部"))) {
				detachedCriteria.add(Restrictions.eq("itemLanguage", language));
			}
			DataType type = dataTypeService.getDataTypebyId(item.getItemType().getId()) ;
			
			//排除为空和类型为全部的情况
			if (type!=null&&(!type.getName().equals("全部"))) {
				detachedCriteria.add(Restrictions.eq("itemType", type));
			}
			Columns columns = item.getParent();
			if (columns!=null) {
				detachedCriteria.add(Restrictions.eq("parent", columns));
			}
			return hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
		}
}
