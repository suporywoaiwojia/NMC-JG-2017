/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : CmsContentList.java  dbh_content_list
 * 描述        : 文章列表标签
 * 作者        : 呼和
 * 日期        : 2012/03/25
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1      2012/03/25  呼和
 */
package com.menksoft.cms.freemarker.Cms;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.menksoft.cms.application.dao.hibernate.OrderPattern;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.freemarker.DirectiveUtils;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.service.ColumnsService;
import com.menksoft.util.Const;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * @author 呼和
 */
 
public class CmsContentList  implements TemplateDirectiveModel{
	private static final String PARAM_NAME_channelId = "channelId";	//栏目ID
	private static final String PARAM_NAME_orderBy  = "order";	//排序顺序 1  时间倒叙 2 时间正序
	private static final String PARAM_NAME_recommend  = "recommend";	//1 显示推荐
	private static final String PARAM_NAME_count  = "count";	//显示数量
	private static final String PARAM_NAME_top  = "top";	//排行榜
	private static final String PARAM_NAME_parentId  = "parentId";	//排行榜
	private static final String PARAM_NAME_language  = "lg";	//排行榜
	@Resource
	ColumnsService columnService;

	@SuppressWarnings("all")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
			if(params.get(PARAM_NAME_channelId)==null&&params.get(PARAM_NAME_parentId)==null)
				throw new TemplateModelException("dbh_content_list"+"缺少参数"+PARAM_NAME_channelId);
			if(params.get(PARAM_NAME_channelId)!=null&&params.get(PARAM_NAME_parentId)!=null)
				throw new TemplateModelException("dbh_content_list"+"参数错误：parentId与channelId不可同时出现");
			if(params.get(PARAM_NAME_count)==null)
				throw new TemplateModelException("dbh_content_list"+"缺少参数"+PARAM_NAME_count);
			if(params.get("var")==null)
				throw new TemplateModelException("dbh_content_list"+"缺少参数"+"var");
			

			String basePath=Const.BASEPATH;
			//params.get(PARAM_NAME_top)==null非排行榜数据
			if(params.get(PARAM_NAME_top)==null){
//				PagingTools<Content> list=getList(params);
//				for(Content content:list.getDataSet()){
//					Columns column=null;
//					column=content.getColumn();
//					env.setVariable(params.get("var").toString(), DEFAULT_WRAPPER.wrap(content));
////					env.setVariable("url", DEFAULT_WRAPPER.wrap(basePath+"page/"+content.getColumn().getColumnPath()+"/"+content.getId()+".html"));
//					env.setVariable("url", DEFAULT_WRAPPER.wrap(basePath+"page/"+content.getId()+".html"));
//					env.setVariable("wapurl", DEFAULT_WRAPPER.wrap(""));
//					if(content.getContent()!=null)
//						env.setVariable("detailed",DEFAULT_WRAPPER.wrap(new String(content.getContent(),"GBK")));
//					try {
//						env.setVariable("attnum", DEFAULT_WRAPPER.wrap(contentFileService.getFileByWebsite(content).size()));
//					} catch (Throwable e) {
//						env.setVariable("attnum", DEFAULT_WRAPPER.wrap(0));
//						e.printStackTrace();
//					}
//					try {
//						env.setVariable("hits", DEFAULT_WRAPPER.wrap(topService.getTotalHits(String.valueOf(content.getId()),String.valueOf(column.getId()))));
//					} catch (NumberFormatException e) {
//						env.setVariable("hits", DEFAULT_WRAPPER.wrap("0"));
//					} catch (Throwable e) {
//						env.setVariable("hits", DEFAULT_WRAPPER.wrap("0"));
//					} 
//					body.render(env.getOut());
//				}
			}else if(params.get(PARAM_NAME_channelId)!=null){
//				Columns column=null;
				String columnId=DirectiveUtils.getString(PARAM_NAME_channelId, params);
				try {
//					column=columnService.getColumnsById(DirectiveUtils.getString(PARAM_NAME_channelId, params));
					
				} catch (Throwable e) {
					e.printStackTrace();
				}

			}
	}
	
	/**
	 * 查询列表数据
	 * @param params
	 * @return
	 * @throws TemplateException 
	 */
//	@SuppressWarnings("all")
//	public PagingTools<Content> getList(Map params) throws TemplateException{
//		DetachedCriteria criteria = DetachedCriteria.forClass(Content.class);
//		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
//		Columns column=new Columns();
//		String parent_path="1";
//		if(params.get(PARAM_NAME_channelId)!=null){
//			column.setId(DirectiveUtils.getLong(PARAM_NAME_channelId, params));
//			criteria.add(Restrictions.eq("column", column));
//		}
//		if(params.get(PARAM_NAME_language)==null){
//			
//			criteria.add(Restrictions.or(Restrictions.isNull("language"),Restrictions.eq("language", "")));
//		}else{
//			criteria.add(Restrictions.eq("language", DirectiveUtils.getString(PARAM_NAME_language, params)));
//		}
//		if(params.get(PARAM_NAME_channelId)==null&&params.get(PARAM_NAME_parentId)!=null)
//			criteria.createCriteria("column").add(Restrictions.like("parentPath", ","+DirectiveUtils.getString(PARAM_NAME_parentId, params),MatchMode.END));
//		
//		//发布状态已发布
//		criteria.add(Restrictions.eq("oublishState","1"));
//		criteria.add(Restrictions.ne("state","9"));
//		//置顶
//		
//		//推荐
//		if(params.get(PARAM_NAME_recommend)!=null&&DirectiveUtils.getInt(PARAM_NAME_recommend, params)==1){
//			criteria.add(Restrictions.eq("recommend", "1"));
//			
//		}
//		
//		if(params.get(PARAM_NAME_orderBy)!=null&&DirectiveUtils.getInt(PARAM_NAME_orderBy, params)==1)
//			
//			orders.put("publishDate", OrderPattern.desc);
//		if(params.get(PARAM_NAME_orderBy)!=null&&DirectiveUtils.getInt(PARAM_NAME_orderBy, params)==2)
//			orders.put("publishDate", OrderPattern.asc);
//		orders.put("putTop", OrderPattern.asc);
//		orders.put("recommend", OrderPattern.desc);
//		
//		PagingTools<Content> list = null;
//		try {
//			int count=DirectiveUtils.getInt(PARAM_NAME_count, params);
//			list=contentBehavior.getContents(criteria,0, 
//					count, orders);
//		}  catch (Throwable e) {
//			e.printStackTrace();
//		}
//		return list;		
//	}
}
