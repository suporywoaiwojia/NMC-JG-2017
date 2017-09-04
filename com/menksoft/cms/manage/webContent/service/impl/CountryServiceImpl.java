package com.menksoft.cms.manage.webContent.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.manage.webContent.entity.Country;
import com.menksoft.cms.manage.webContent.service.CountryService;

@Service("CountryService")
public class CountryServiceImpl implements CountryService {
	 @Resource 
	    private HibernateTemplate hibernateTemplate;

		/**
		 * 创建新实体
		 * @param country
		 * @throws Throwable
		 */
		@Override
		public void save(Country country) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.save(country);
		}
		/**
		 * 更新实体
		 * @param country
		 * @throws Throwable
		 */
		@Override
		public void update(Country country) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.update(country);
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
//			hibernateTemplate.delete(country);
//			hibernateTemplate.deleteById(Country.class, idName, id);
			hibernateTemplate.executeSql("delete from r_tap_country where id in("+id+")", null);
		}


		/**
		 * 查询所有的数据
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<Country> getCountrys() throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Country.class);
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
		public Country getCountrybyId(long id) throws Throwable {
			// TODO Auto-generated method stub
			Country country = hibernateTemplate.getEntityById(Country.class, id);
			return country;
		}
		/**
		 * 检查实体是否重复
		 * @param country 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<Country> checkRepeat(Country country) throws Throwable {
			// TODO Auto-generated method stub
	//		String sql = "SELECT * FROM r_data_country WHERE id <>"+country.getId();
			String sql = "select * from r_tap_country where name='"+country.getName()+"'";
			return hibernateTemplate.getEntitiesBySql(Country.class, sql, null);
		}
		
}
