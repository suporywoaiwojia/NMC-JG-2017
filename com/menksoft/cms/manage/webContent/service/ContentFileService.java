package com.menksoft.cms.manage.webContent.service;

import java.util.List;

import com.menksoft.cms.manage.webContent.entity.ContentFile;

/**
 * @author Administrator
 * 附件信息
 *
 */
public interface ContentFileService {
	/**
	 * 保存附件
	 * @param list 附件集合
	 * @param id 所属信息ID
	 * @throws Throwable
	 */
	public void save(List<ContentFile> list,long id,String type)throws Throwable;
	
	/**
	 * 获取附件类别
	 * @param type 属性 项目信息 曲目信息等
	 * @param filetype 附件属性 照片 封面 曲集等
	 * @return
	 * @throws Throwable
	 */
	public List<ContentFile>getList(String type,String filetype,long contentid)throws Throwable;
	
	/**
	 * 根据附件名称查询指定类型附件信息 【不同类型公用附件，保存名称相同】
	 * @param saveName 附件保存名称
	 * @param type 1项目信息 2传承人 3曲目 4出版物 5专项活动
	 * @return
	 * @throws Throwable
	 */
	public List<ContentFile>getListforFileName(String saveName,String type)throws Throwable;
	
	/**
	 * 
	 * @param 删除附件
	 * @param 
	 * @return
	 * @throws Throwable
	 */
	public String deleteFiles(List<ContentFile> listContentFiles) throws Throwable;
}
