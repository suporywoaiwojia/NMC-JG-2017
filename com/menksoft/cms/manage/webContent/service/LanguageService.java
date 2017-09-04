package com.menksoft.cms.manage.webContent.service;

import java.util.List;

import com.menksoft.cms.manage.webContent.entity.Language;



/**
 * 码表--呼麦风格管理
 * @author Administrator
 *
 */
public interface LanguageService {
	
	/**
	 * 创建新实体
	 * @param language
	 * @throws Throwable
	 */
	public abstract void save(Language language) throws Throwable;
	
	/**
	 * 更新实体
	 * @param language
	 * @throws Throwable
	 */
	public abstract void update(Language language) throws Throwable;
	
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
	public abstract List<Language> getLanguages() throws Throwable;
	/**
	 * 查询单个实体
	 * @param id 
	 * @return
	 * @throws Throwable
	 */
	public abstract Language getLanguagebyId(long id) throws Throwable;
	
	
	/**
	 * 检查实体的名称是否重复
	 * @param language 
	 * @return
	 * @throws Throwable
	 */
	public abstract List<Language> checkRepeat(Language language) throws Throwable;
}
