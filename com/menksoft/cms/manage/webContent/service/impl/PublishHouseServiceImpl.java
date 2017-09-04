package com.menksoft.cms.manage.webContent.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.manage.webContent.entity.PublishHouse;
import com.menksoft.cms.manage.webContent.service.PublishHouseService;

@Service("PublishHouseService")
public class PublishHouseServiceImpl implements PublishHouseService {
	 @Resource 
	    private HibernateTemplate hibernateTemplate;

		/**
		 * 创建新实体
		 * @param publishHouse
		 * @throws Throwable
		 */
		@Override
		public void save(PublishHouse publishHouse) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.save(publishHouse);
		}
		/**
		 * 更新实体
		 * @param publishHouse
		 * @throws Throwable
		 */
		@Override
		public void update(PublishHouse publishHouse) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.update(publishHouse);
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
//			hibernateTemplate.delete(publishHouse);
//			hibernateTemplate.deleteById(PublishHouse.class, idName, id);
			hibernateTemplate.executeSql("delete from r_tap_publishHouse where id in("+id+")", null);
		}


		/**
		 * 查询所有的数据
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<PublishHouse> getPublishHouses() throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PublishHouse.class);
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
		public PublishHouse getPublishHousebyId(long id) throws Throwable {
			// TODO Auto-generated method stub
			PublishHouse publishHouse = hibernateTemplate.getEntityById(PublishHouse.class, id);
			return publishHouse;
		}
		/**
		 * 检查实体是否重复
		 * @param publishHouse 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<PublishHouse> checkRepeat(PublishHouse publishHouse) throws Throwable {
			// TODO Auto-generated method stub
	//		String sql = "SELECT * FROM r_data_publishHouse WHERE id <>"+publishHouse.getId();
			String sql = "select * from r_tap_publishhouse where name='"+publishHouse.getName()+"'";
			return hibernateTemplate.getEntitiesBySql(PublishHouse.class, sql, null);
		}
		
}
