/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : Column.java
 * 描述        : 
 * 作者        : lenovo
 * 日期        : 下午2:59:19
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       下午2:59:19  lenovo
 */
package com.menksoft.cms.freemarker.Cms;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
/**
 * @author 呼和
 */

public class CmsChannelList implements TemplateDirectiveModel{
	private static final String PARAM_NAME_ID = "parentid";
	private static final String PARAM_NAME_LG = "lg";
	@Resource
	ColumnsService columnsService;

	@SuppressWarnings("all")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,   
		TemplateDirectiveBody body) throws TemplateException, IOException  {
		//处理标签校验
//		 	//将查询结果放入其中 
		long id=DirectiveUtils.getLong(PARAM_NAME_ID, params);
		String lg=DirectiveUtils.getString(PARAM_NAME_LG, params);
			if(lg!=null)
				lg=lg.toUpperCase();
		//查询站点信息 indexDynamic=1 为静态主页 0为动态主页
		String indexDynamic=FreeMarkerUtil.getWebsite().getIndexDynamics();;
		List<Columns> list;
		list=getColumn(id,lg);
		String basePath=Const.BASEPATH+"action/";
		for(Columns column:list){
			//动态主页路径取id
//			String path=(indexDynamic.equals("0")?basePath+String.valueOf(column.getId()):basePath+"page/"+column.getColumnPath());
			String path=(indexDynamic.equals("0")?basePath+String.valueOf(column.getId()):basePath+"page/"+column.getId()+"_index"+FreeMarkerUtil.getString("pageFormat"));
			 if(lg!=null&&!lg.equals(""))
				 path=(indexDynamic.equals("0")?basePath+String.valueOf(column.getId()):basePath+"page/"+column.getId()+"_index_"+"lg"+FreeMarkerUtil.getString("pageFormat"));
			String columnpath=path;
			env.setVariable(params.get("var").toString(), DEFAULT_WRAPPER.wrap(column));
			 env.setVariable("url", DEFAULT_WRAPPER.wrap(getColumnsPath(column,id,columnpath,lg)));
			 env.setVariable("wapurl", DEFAULT_WRAPPER.wrap(getWapPath(column,id,columnpath)));
			body.render(env.getOut());
		}

	}

	
	/**
	 * 获取栏目内容
	 * @return
	 */
	public List<Columns> getColumn(long parentid,String lg){
		 List<Columns> list=new ArrayList<Columns>();
		 List<Columns> list1=new ArrayList<Columns>();
		 try {
			 list=columnsService.getColumnByWebsite(parentid);
			
			// if(lg!=null&&!lg.equals("")){
				 for(int i=0;i<list.size();i++){
				//	 ColumnLanguage columnLanguage=null;
				//	 columnLanguage= columnLanguageService.getClbylg(list.get(i).getId(), lg);
				//	 if(columnLanguage!=null){
					//	 list.get(i).setName(columnLanguage.getName());
						 list1.add( list.get(i));
					 }
				// }
				 list=null;
				 list=list1;
			// }
		} catch (Throwable e) {
			e.printStackTrace();
		}
		 return list;
	}
	public String getColumnsPath(Columns column,long id,String columnpath,String lg){
		String path="";
		//查询站点信息 indexDynamic=1 为静态主页 0为动态主页
		Website website=FreeMarkerUtil.getWebsite();
		String indexDynamic=website.getIndexDynamics();
//		String listDynamic=website.getListDynamics();
		String basePath=Const.BASEPATH+"action/";
		 if(id==0){
			 if(indexDynamic.equals("0")){
				 path=basePath+"index";
				 if(lg!=null&&!lg.equals(""))
					 path=basePath+lg+"/index";
			 }
			 if(FreeMarkerUtil.getWebsite().getIndexDynamics().equals("1")){
				 path="index"+FreeMarkerUtil.getString("pageFormat");
				 if(lg!=null&&!lg.equals(""))
					 path="index_"+lg+FreeMarkerUtil.getString("pageFormat");
			 }
		 }else{
			 if(column.getPageNum()==0&&!column.getColumnType().equals("5")){
				//动态主页路径取id
				 path =(indexDynamic.equals("0")?basePath+"page/"+String.valueOf(column.getId()):columnpath);
				 if(lg!=null&&!lg.equals(""))
					 path =(indexDynamic.equals("0")?basePath+"page/"+String.valueOf(column.getId())+"/lg":columnpath);
				
			 }else if(column.getPageNum()>0&&!column.getColumnType().equals("5")){
				 //判断栏目页数是否为0 为0则 path=上则 若不等于0 则 url=list/id/pagenum or path/columnPth/pagenum
//				 path=listDynamic.equals("0")?basePath+"list/"+String.valueOf(column.getId())+"/1":"page/"+columnpath+"/list_1"+FreeMarkerUtil.getString("pageFormat");
				 path=basePath+"list/"+String.valueOf(column.getId())+"/1";
				 if(lg!=null&&!lg.equals(""))
//					 path=listDynamic.equals("0")?basePath+"list/"+String.valueOf(column.getId())+"/1":"page/"+columnpath+"/list_1"+FreeMarkerUtil.getString("pageFormat");
					 path=basePath+"list/"+lg+"/"+String.valueOf(column.getId())+"/1";
			 }
			 //栏目属性为单页的页面路径
			 if(column.getColumnType().equals("5")){
//					List<Content> list;
//					try {
//						list = contentService.getContentByWebsite(column);
//						if(list.size()>0)
////							path=Const.BASEPATH+"page/"+column.getColumnPath()+"/"+list.get(0).getId()+FreeMarkerUtil.getString("pageFormat");
//							path=Const.BASEPATH+"page"+"/"+list.get(0).getId()+FreeMarkerUtil.getString("pageFormat");
//					} catch (Throwable e) {
//						e.printStackTrace();
//					}
					
			 }
		 }
		 return path;
	}
	public String getWapPath(Columns column,long id,String columnpath){
		String path="";
		//查询站点信息 indexDynamic=1 为静态主页 0为动态主页
		Website website=FreeMarkerUtil.getWebsite();
		String indexDynamic=website.getIndexDynamics();
//		String listDynamic=website.getListDynamics();
		String basePath=Const.BASEPATH+"action/";
		 if(id==0){
			 if(indexDynamic.equals("0"))
				 path=basePath+"wap/index";
			 if(FreeMarkerUtil.getWebsite().getIndexDynamics().equals("1"))
				 path="wap_index"+FreeMarkerUtil.getString("pageFormat");
		 }else{
			 if(column.getPageNum()==0){
				//动态主页路径取id
				 path =(indexDynamic.equals("0")?basePath+"wap/page/"+String.valueOf(column.getId()):columnpath);
				
			 }else{
				 //判断栏目页数是否为0 为0则 path=上则 若不等于0 则 url=list/id/pagenum or path/columnPth/pagenum
//				 path=listDynamic.equals("0")?basePath+"wap/list/"+String.valueOf(column.getId())+"/1":"page/"+columnpath+"/wap_list_1"+FreeMarkerUtil.getString("pageFormat");
				 path=basePath+"wap/list/"+String.valueOf(column.getId())+"/1";
			 }
		 }
		 return path;
	}
}
