package com.menksoft.cms.freemarker.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.entity.ContentFile;

import freemarker.template.TemplateException;

public interface MakePageService {

	/**
	 * 生成页面  dbh_channel_list，dbh_channel，dbh_content_list，dbh_slideshow
	 * @param ftl	模版名称
	 * @param htmlName	生成页面名称
	 * @param pathName	生成文件路径
	 * @throws Throwable
	 */
	public abstract String MakePage(String ftl, String htmlName, String pathName,HttpServletResponse response) throws Throwable,IOException, TemplateException ;
	/**
	 * 单页生成
	 * @param ftl
	 * @param htmlName
	 * @param pathName
	 * @param response
	 * @return
	 * @throws Throwable
	 * @throws IOException
	 * @throws TemplateException
	 */
//	public abstract String MakePageForOne(String ftl, String htmlName, Content content,HttpServletResponse response) throws Throwable,IOException, TemplateException ;
	/**
	 * 生成列表带翻页页面 dbh_content_page
	 * column 生成栏目对象
	 * int pageNum 当前页数 若为静态列表 则传入0
	 * listDynamics //查询站点信息 listDynamics=1 为静态列表 0为动态列表
	 * @throws Throwable
	 */
//	public abstract String MakeListPage(int pageNum,Columns column,
//			PagingTools<Content> list,HttpServletResponse response) throws Throwable,IOException, TemplateException ;
	

	/**
	 * 生成内容页面 dbh_content
	 * @param ftl	模版名称
	 * @param channelId	 栏目ID
	 * @throws Throwable
	 */
//	public abstract String MakeContentPage(Columns column,List<Content> list) throws Throwable,IOException, TemplateException ;
	
	/**
	 * 标签，关键字查询内容列表
	 * @param pageNum	当前页数
	 * @param column	查询栏目对象
	 * @param list	查询结果集
	 * @param response
	 * @return
	 * @throws Throwable
	 * @throws IOException
	 * @throws TemplateException
	 */
//	public String MakeSearchList(int pageNum,Columns column,PagingTools<Content> list,HttpServletResponse response,String key,String lg)  
//            throws  Throwable,IOException, TemplateException  ;
//	
	/**
	 * 生成视频/音频 ，图片专辑页面
	 * @param column
	 * @param list
	 * @param response
	 * @return
	 */
	public String MakeAttFile(Columns column,List<ContentFile> list,String lg)
			throws Throwable,IOException, TemplateException  ;
	
	
	
	
	
	//dateamount每页记录数
	/**
	 * 自定义传入LIST或翻页生成页面
	 * @param pageNum 每页显示记录数量
	 * @param memberid 会员ID
	 * @param model 模版页面地址
	 * @param paglist 翻页集合
	 * @param response
	 * @param list 非翻页集合
	 * @param flag 自定义参数
	 * @return
	 * @throws Throwable
	 * @throws IOException
	 * @throws TemplateException
	 */
	public abstract String MakeListPageformodel(int pageNum,long memberid,String model,
			PagingTools<?> paglist,HttpServletResponse response,List<?> list,String flag) throws Throwable,IOException, TemplateException ;
	
	/**
	 * 页面导出
	 * @param ftl
	 * @param htmlName
	 * @param pathName
	 * @param response
	 * @return
	 * @throws Throwable
	 * @throws IOException
	 * @throws TemplateException
	 */
	public boolean  exportPage(String ftl, String htmlName, String pathName,HttpServletResponse response,List<?> list1,List<?> list2,List<?> list3,long id) ;
}