package com.menksoft.cms.manage.data.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.manage.data.entity.Content;
import com.menksoft.cms.manage.data.entity.Item;
import com.menksoft.cms.manage.data.service.ContentService;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.entity.DataType;
import com.menksoft.cms.manage.webContent.service.DataTypeService;
import com.menksoft.cms.manage.webContent.service.LanguageService;

@Service("ContentService")
public class ContentServiceImpl implements ContentService {
	 @Resource 
	    private HibernateTemplate hibernateTemplate;
     @Resource
     private DataTypeService dataTypeService;
     @Resource
     private LanguageService languageService;
     
		/**
		 * 创建新实体
		 * @param content
		 * @throws Throwable
		 */
		@Override
		public void save(Content content) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.save(content);
		}
		/**
		 * 更新实体
		 * @param content
		 * @throws Throwable
		 */
		@Override
		public void update(Content content) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.update(content);
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
//			hibernateTemplate.delete(content);
//			hibernateTemplate.deleteById(Content.class, idName, id);
			hibernateTemplate.executeSql("delete from r_data_content where id in("+id+")", null);
		}


		/**
		 * 查询所有的数据
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<Content> getContents() throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Content.class);
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
		public Content getContentbyId(long id) throws Throwable {
			// TODO Auto-generated method stub
			Content content = hibernateTemplate.getEntityById(Content.class, id);
			return content;
		}
		/**
		 * 检查实体是否重复
		 * @param content 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<Content> checkRepeat(Content content) throws Throwable {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM r_data_content WHERE id <>"+content.getId();
//			String sqlStr = "select * from r_data_content where bm='"+content.getBm()+"or name='"+content.getName()+"'";
			return hibernateTemplate.getEntitiesBySql(Content.class, sql, null);
		}
		/**
		 * 依据栏目的id查询该栏目下的内容
		 * @param id 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public PagingTools<Content> getContentsByColumn(Columns column,Integer startIndex, Integer count) throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria =DetachedCriteria.forClass(Content.class);
			
			detachedCriteria.add(Restrictions.eq("columnContents",column));
			return hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
			
		}
		/**
		 * 依据item的id查询该栏目下的内容
		 * @param id 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public PagingTools<Content> getContentsByItem(Item item,
				Integer startIndex, Integer count) throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Content.class);
			detachedCriteria.add(Restrictions.eq("contentItem", item));
			return hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
		}
		/**
		 * 依据栏目的id和审核人，查询该栏目下的内容
		 * @param id approve
		 * @return
		 * @throws Throwable
		 */
		@Override
		public PagingTools<Content> getContentsByColumnAndApprove(Columns column,
				User approve, Integer startIndex, Integer count)
				throws Throwable {
			// TODO Auto-generated method stub
				DetachedCriteria detachedCriteria =DetachedCriteria.forClass(Content.class);
			    detachedCriteria.add(Restrictions.eq("approve", approve));
				detachedCriteria.add(Restrictions.eq("columnContents",column));
				return hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
		}
		/**
		 * 依据查询条件，查询对应栏目下的内容
		 * @param id approve
		 * @return
		 * @throws Throwable
		 */
		@Override
		public PagingTools<Content> queryContents(Content content,
				Integer startIndex, Integer count) throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Content.class);
			
			String name = content.getName();
			if (name!=null&&name!="") {
				detachedCriteria.add(Restrictions.eq("name", name));
			}
			String author = content.getAuthor();
			if (author!=null&&author!="") {
				detachedCriteria.add(Restrictions.eq("author", author));
			}
			String state = content.getState();
			if (state!=null&&state!="") {
				detachedCriteria.add(Restrictions.eq("state", state));
			}
			DataType type = dataTypeService.getDataTypebyId(content.getType().getId()) ;
			//排除为空和类型为全部的情况
			if (type!=null&&(!type.getName().equals("全部"))) {
				detachedCriteria.add(Restrictions.eq("type", type));
			}
			Columns columns = content.getColumnContents();
			if (columns!=null) {
				detachedCriteria.add(Restrictions.eq("columnContents", columns));
			}
			
			return hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
		}
}
