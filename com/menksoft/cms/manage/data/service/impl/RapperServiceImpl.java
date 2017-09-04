package com.menksoft.cms.manage.data.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.manage.data.entity.Rapper;
import com.menksoft.cms.manage.data.service.RapperService;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.entity.DataType;
import com.menksoft.cms.manage.webContent.entity.Language;
import com.menksoft.cms.manage.webContent.service.DataTypeService;
import com.menksoft.cms.manage.webContent.service.LanguageService;

@Service("RapperService")
public class RapperServiceImpl implements RapperService {
	 @Resource 
	    private HibernateTemplate hibernateTemplate;
     @Resource
     private DataTypeService dataTypeService;
     @Resource
     private LanguageService languageService;
		/**
		 * 创建新实体
		 * @param rapper
		 * @throws Throwable
		 */
		@Override
		public void save(Rapper rapper) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.save(rapper);
		}
		/**
		 * 更新实体
		 * @param rapper
		 * @throws Throwable
		 */
		@Override
		public void update(Rapper rapper) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.update(rapper);
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
//			hibernateTemplate.delete(rapper);
//			hibernateTemplate.deleteById(Rapper.class, idName, id);
		
			hibernateTemplate.executeSql("delete from r_data_rapper where id in("+id+")", null);
		}


		/**
		 * 查询所有的数据
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<Rapper> getRappers() throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Rapper.class);
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
		public Rapper getRapperbyId(long id) throws Throwable {
			// TODO Auto-generated method stub
			Rapper rapper = hibernateTemplate.getEntityById(Rapper.class, id);
			return rapper;
		}
		/**
		 * 检查实体是否重复
		 * @param rapper 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<Rapper> checkRepeat(Rapper rapper) throws Throwable {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM r_data_rapper WHERE id <>"+rapper.getId();
//			String sqlStr = "select * from r_data_rapper where bm='"+rapper.getBm()+"or name='"+rapper.getName()+"'";
			return hibernateTemplate.getEntitiesBySql(Rapper.class, sql, null);
		}
		/**
		 * 依据栏目的id查询该栏目下的内容
		 * @param id 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public PagingTools<Rapper> getRappers(Integer startIndex, Integer count) throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria =DetachedCriteria.forClass(Rapper.class);
			
			return hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
			
		}
		/**
		 * 依据栏目的id和审核人，查询该栏目下的内容
		 * @param id approve
		 * @return
		 * @throws Throwable
		 */
		@Override
		public PagingTools<Rapper> getRappersByColumnAndApprove(Columns column,
				User approve, Integer startIndex, Integer count)
				throws Throwable {
			// TODO Auto-generated method stub
            DetachedCriteria detachedCriteria =DetachedCriteria.forClass(Rapper.class);
			detachedCriteria.add(Restrictions.eq("approve", approve));
			detachedCriteria.add(Restrictions.eq("parent",column));
			return hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
		}
		@Override
		public PagingTools<Rapper> queryRappers(Rapper rapper, Integer startIndex,
				Integer count) throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Rapper.class);
			
			String name = rapper.getName();
			if (name!=null&&name!="") {
				detachedCriteria.add(Restrictions.eq("name", name));
			}
			String sex = rapper.getSex();
			if (sex!=null&&sex!="") {
				detachedCriteria.add(Restrictions.eq("sex", sex));
			}
          
			return hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
		}
}
