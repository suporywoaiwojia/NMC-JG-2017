package com.menksoft.cms.manage.webContent.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.manage.webContent.entity.Language;
import com.menksoft.cms.manage.webContent.service.LanguageService;

@Service("LanguageService")
public class LanguageServiceImpl implements LanguageService {
	 @Resource 
	    private HibernateTemplate hibernateTemplate;

		/**
		 * 创建新实体
		 * @param language
		 * @throws Throwable
		 */
		@Override
		public void save(Language language) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.save(language);
		}
		/**
		 * 更新实体
		 * @param language
		 * @throws Throwable
		 */
		@Override
		public void update(Language language) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.update(language);
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
//			hibernateTemplate.delete(language);
//			hibernateTemplate.deleteById(Language.class, idName, id);
			hibernateTemplate.executeSql("delete from r_tap_language where id in("+id+")", null);
		}


		/**
		 * 查询所有的数据
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<Language> getLanguages() throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Language.class);
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
		public Language getLanguagebyId(long id) throws Throwable {
			// TODO Auto-generated method stub
			Language language = hibernateTemplate.getEntityById(Language.class, id);
			return language;
		}
		/**
		 * 检查实体是否重复
		 * @param language 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<Language> checkRepeat(Language language) throws Throwable {
			// TODO Auto-generated method stub
	//		String sql = "SELECT * FROM r_data_language WHERE id <>"+language.getId();
			String sql = "select * from r_tap_language where name='"+language.getName()+"'";
			return hibernateTemplate.getEntitiesBySql(Language.class, sql, null);
		}
		
}
