package com.menksoft.cms.manage.webContent.service;

import java.util.List;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.webContent.entity.Project;

/**
 * @author Administrator
 *项目信息
 */
/**
 * @author Administrator
 *
 */
public interface ProjectService {
	/**
	 * 列表显示
	 * @param pro
	 * @param startIndex
	 * @param count
	 * @return
	 * @throws Throwable
	 */
	public PagingTools<Project> getProject(String language,Project pro,
			Integer startIndex, Integer count) throws Throwable;
	
	
	/**
	 * 保存项目
	 * @param pro 保存对象
	 * @throws Throwable
	 */
	public void save(Project pro)throws Throwable;
	
	/**
	 * 编辑保存
	 * @param pro
	 * @throws Throwable
	 */
	public void update(Project pro)throws Throwable;
	
	/**
	 * 删除数据
	 * @param ids
	 * @throws Throwable
	 */
	public void delete(String ids)throws Throwable;
	
	/**
	 * 根据ID查询项目
	 * @param id
	 * @return
	 * @throws Throwable
	 */
	public Project getProjectByid(long id)throws Throwable;
	
	/**
	 * 项目下拉列表
	 * @param language
	 * @return
	 * @throws Throwable
	 */
	public List<Project> getList(String language)throws Throwable;
	
	/**
	 * 待审数据
	 * @param language
	 * @param pro
	 * @param startIndex
	 * @param count
	 * @return
	 * @throws Throwable
	 */
	public PagingTools<Project> getProject_check(String language,Project pro,
			Integer startIndex, Integer count,String user) throws Throwable;
}
