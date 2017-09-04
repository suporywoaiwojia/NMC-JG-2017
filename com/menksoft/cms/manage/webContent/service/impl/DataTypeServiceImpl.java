package com.menksoft.cms.manage.webContent.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.manage.webContent.entity.DataType;
import com.menksoft.cms.manage.webContent.service.DataTypeService;

@Service("DataTypeService")
public class DataTypeServiceImpl implements DataTypeService {
	 @Resource 
	    private HibernateTemplate hibernateTemplate;

		/**
		 * 创建新实体
		 * @param dataType
		 * @throws Throwable
		 */
		@Override
		public void save(DataType dataType) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.save(dataType);
		}
		/**
		 * 更新实体
		 * @param dataType
		 * @throws Throwable
		 */
		@Override
		public void update(DataType dataType) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.update(dataType);
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
//			hibernateTemplate.delete(dataType);
//			hibernateTemplate.deleteById(DataType.class, idName, id);
			hibernateTemplate.executeSql("delete from r_data_datatype where id in("+id+")", null);
		}


		/**
		 * 查询所有的数据
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<DataType> getDataTypes() throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DataType.class);
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
		public DataType getDataTypebyId(long id) throws Throwable {
			// TODO Auto-generated method stub
			DataType dataType = hibernateTemplate.getEntityById(DataType.class, id);
			return dataType;
		}
		/**
		 * 检查实体是否重复
		 * @param dataType 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<DataType> checkRepeat(DataType dataType) throws Throwable {
			// TODO Auto-generated method stub
	//		String sql = "SELECT * FROM r_data_dataType WHERE id <>"+dataType.getId();
			String sql = "select * from r_data_dataType where name='"+dataType.getName()+"'";
			return hibernateTemplate.getEntitiesBySql(DataType.class, sql, null);
		}
		
}
