/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : CmsChannel.java
 * 描述        : 根据频道ID获取频道内容
 * 作者        : 呼和
 * 日期        : 下午2:40:22
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       下午2:40:22  lenovo
 */
package com.menksoft.cms.freemarker.Cms;


import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.annotation.Resource;

import com.menksoft.cms.freemarker.DirectiveUtils;
import com.menksoft.cms.freemarker.FreeMarkerUtil;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.service.ColumnsService;
import com.menksoft.cms.manage.website.entity.Website;
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
public class CmsChannel implements TemplateDirectiveModel {
	private static final String PARAM_NAME_ID = "id";
	private static final String PARAM_NAME_LG = "lg";


	
	@Resource
	ColumnsService columnsService;
	@SuppressWarnings("all")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Writer out = env.getOut();// 固定写法
		long id=DirectiveUtils.getLong(PARAM_NAME_ID, params);
		String lg=DirectiveUtils.getString(PARAM_NAME_LG, params);
		if(lg!=null)
			lg=lg.toUpperCase();
		if(params.get("id")==null)
			throw new TemplateModelException("dbh_channel"+"缺少参数"+PARAM_NAME_ID);
		Columns column=new Columns();
		String path="";
		column=getColumnByid(id);
		path=getColumnsPath(column,id,lg);
		if(id!=0&&column!=null){
		//名称
//			 if(lg!=null&&!lg.equals("")){
//				 ColumnLanguage columnLanguage=null;
//				try {
//					columnLanguage = columnLanguageService.getClbylg(column.getId(), lg);
//				
//				} catch (Throwable e) {
//					e.printStackTrace();
//				}
//				 if(columnLanguage!=null){
//					 env.setVariable("name", DEFAULT_WRAPPER.wrap(columnLanguage.getName()));
//				 }
//			 }else{
				 env.setVariable("name", DEFAULT_WRAPPER.wrap(column.getName()));
//			 }
			 
			 env.setVariable("path", DEFAULT_WRAPPER.wrap(column.getColumnPath()));
			//栏目属性为单页的页面路径
			 if(column.getColumnType().equals("5")){
				 try {
//					List<Content> list=contentService.getContentByWebsite(column);
					path="";
					
//					if(list.size()>0)
////						path=Const.BASEPATH+"page/"+column.getColumnPath()+"/"+list.get(0).getId()+FreeMarkerUtil.getString("pageFormat");
//						path=Const.BASEPATH+"page/"+list.get(0).getId()+FreeMarkerUtil.getString("pageFormat");
				} catch (Throwable e) {
					e.printStackTrace();
				}
				
			 }
		}
		 env.setVariable("url", DEFAULT_WRAPPER.wrap(path));
		 env.setVariable("wapurl", DEFAULT_WRAPPER.wrap(getWapPath(column,id)));
		body.render(env.getOut());
	}
	/**
	 * 获取栏目内容
	 * @return
	 */
	public Columns getColumnByid(long id){
		Columns column=null;
		 try {
			
			 column=columnsService.getColumnsById(id);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		 return column;
	}
	
	/**
	 * 根据动静态 判断栏目路径
	 * @return
	 */
	public String getColumnsPath(Columns column,long id,String lg){
		String path="";
		//查询站点信息 indexDynamic=1 为静态主页 0为动态主页
		Website website=FreeMarkerUtil.getWebsite();
		String indexDynamic=website.getIndexDynamics();
//		String listDynamic=website.getListDynamics();
		String basePath="";
		String html=FreeMarkerUtil.getString("pageFormat");
//		if(listDynamic.equals("0")){
			basePath=Const.BASEPATH+"action/";
//		}else{
//			basePath=Const.BASEPATH;
//		}
		 if(id==0){
			 if(indexDynamic.equals("0")){
				 path=Const.BASEPATH+"action/"+"index";
				 if(lg!=null&&!lg.equals(""))
					 path=Const.BASEPATH+"action/"+lg+"/"+"index";
			 }
			
			 if(FreeMarkerUtil.getWebsite().getIndexDynamics().equals("1")){
				 path=Const.BASEPATH+"index"+FreeMarkerUtil.getString("pageFormat");
				 if(lg!=null&&!lg.equals(""))
					 path=Const.BASEPATH+"index_"+lg+FreeMarkerUtil.getString("pageFormat");
			 }
		 }else{
			 if(column!=null){
				 if(column.getPageNum()==0){
					//动态主页路径取id
					if(indexDynamic.equals("0")){
						
						path=Const.BASEPATH+"action/page/"+String.valueOf(column.getId());
						 if(lg!=null&&!lg.equals(""))
							 path=Const.BASEPATH+"action/page/"+String.valueOf(column.getId())+"/"+lg;
					}else{
//						
						path=Const.BASEPATH+"page/"+column.getId()+"_index"+html;
						if(lg!=null&&!lg.equals(""))
							path=Const.BASEPATH+"page/"+column.getId()+"_index_"+lg+html;
//						path=Const.BASEPATH+"page/";
					}
				 }else{
					 //判断栏目页数是否为0 为0则 path=上则 若不等于0 则 url=list/id/pagenum or path/columnPth/pagenum
//					 if(listDynamic.equals("0")){
						 path=basePath+"list/"+String.valueOf(column.getId())+"/1"; 
						 if(lg!=null&&!lg.equals(""))
							 path=basePath+"list/"+lg+"/"+String.valueOf(column.getId())+"/1";
//					 }else{
////						 path=basePath+"page/"+column.getColumnPath()+"/list_1"+FreeMarkerUtil.getString("pageFormat");
//						 path=basePath+"page"+"/list_1"+html;
//					 }
				 }
			 }
		 }
		 return path;
	}
	/**
	 * 根据动静态 判断栏目路径 wap
	 * @return
	 */
	public String getWapPath(Columns column,long id){
		String paths="";
		//查询站点信息 indexDynamic=1 为静态主页 0为动态主页
		Website website=FreeMarkerUtil.getWebsite();
		String indexDynamic=website.getIndexDynamics();
//		String listDynamic=website.getListDynamics();
		String basePath="";
//		if(listDynamic.equals("0")){
			basePath=Const.BASEPATH+"action/";
//		}else{
//			basePath=Const.BASEPATH;
//		}
		 if(id==0&&column!=null){
			 if(indexDynamic.equals("0"))
				 paths=Const.BASEPATH+"action/"+"wap/index";
			
			 if(FreeMarkerUtil.getWebsite().getIndexDynamics().equals("1"))
				 paths=Const.BASEPATH+"wap_index"+FreeMarkerUtil.getString("pageFormat");
		 }else{
			 if(column!=null){
				 if(column.getPageNum()==0){
					//动态主页路径取id
					 
					 if(indexDynamic.equals("0")){
						 paths =Const.BASEPATH+"action/wap/page/"+String.valueOf(column.getId());
					 }else{
//						 paths=Const.BASEPATH+"page/"+column.getColumnPath()+"wap_index"+FreeMarkerUtil.getString("pageFormat");
						 paths=Const.BASEPATH+"page/"+"wap_index"+FreeMarkerUtil.getString("pageFormat");
					 }
				 }else{
					 //判断栏目页数是否为0 为0则 path=上则 若不等于0 则 url=list/id/pagenum or path/columnPth/pagenum
//					 if(listDynamic.equals("0")){
						 paths= basePath+"wap/list/"+String.valueOf(column.getId())+"/1";
//					 }else{
////						 paths=basePath+"page/"+column.getColumnPath()+"/wap_list_1"+FreeMarkerUtil.getString("pageFormat");
//						 paths=basePath+"page/"+"/wap_list_1"+FreeMarkerUtil.getString("pageFormat");
//					 }
				 }
			 }
		 }
		 return paths;
	}

}
