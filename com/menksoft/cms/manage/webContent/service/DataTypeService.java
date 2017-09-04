package com.menksoft.cms.manage.webContent.service;

import java.util.List;

import com.menksoft.cms.manage.webContent.entity.DataType;

/**
 * 码表--呼麦风格管理
 * @author Administrator
 *
 */
public interface DataTypeService {
	
	/**
	 * 创建新实体
	 * @param dataType
	 * @throws Throwable
	 */
	public abstract void save(DataType dataType) throws Throwable;
	
	/**
	 * 更新实体
	 * @param dataType
	 * @throws Throwable
	 */
	public abstract void update(DataType dataType) throws Throwable;
	
	/**
	 * 删除实体
	 * @param id
	 * @throws Throwable
	 */
	public abstract void delete(String id ) throws Throwable;

	/**
	 * 查询所有的数据
	 * @return
	 * @throws Throwable
	 */
	public abstract List<DataType> getDataTypes() throws Throwable;
	/**
	 * 查询单个实体
	 * @param id 
	 * @return
	 * @throws Throwable
	 */
	public abstract DataType getDataTypebyId(long id) throws Throwable;
	
	
	/**
	 * 检查实体的名称和编码是否重复
	 * @param dataType 
	 * @return
	 * @throws Throwable
	 */
	public abstract List<DataType> checkRepeat(DataType dataType) throws Throwable;
}
