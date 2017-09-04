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

public class CmsBot implements TemplateDirectiveModel{
	private static final String Column_Id = "columnId";
	private static final String PARAM_NAME_LG = "lg";
	@Resource
	ColumnsService columnsService;

	
	@SuppressWarnings("all")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,   
		TemplateDirectiveBody body) throws TemplateException, IOException  {
		//处理标签校验
//		//将查询结果放入其中 
		long id=DirectiveUtils.getLong(Column_Id, params);
		//查询站点信息 indexDynamic=1 为静态主页 0为动态主页
		String indexDynamic=FreeMarkerUtil.getWebsite().getIndexDynamics();;
		List<Columns> list;
		list=getColumn(id);
		String basePath=Const.BASEPATH+"action/";
		String lg=DirectiveUtils.getString(PARAM_NAME_LG, params);
			body.render(env.getOut());
		

	}

	
	/**
	 * 获取栏目bot
	 * @return
	 */
	public List<Columns> getColumn(long id){
		 List<Columns> list=new ArrayList<Columns>();
		 try {
			Columns column= columnsService.getColumnsById(id);
			 list=columnsService.getBotColumn(column.getParentPath());
			 list.add(column);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		 return list;
	}
	public String getColumnsPath(Columns column,String columnpath,String lg){
		String path="";
		//查询站点信息 indexDynamic=1 为静态主页 0为动态主页
		Website website=FreeMarkerUtil.getWebsite();
		String indexDynamic=website.getIndexDynamics();
//		String listDynamic=website.getListDynamics();
		String basePath=Const.BASEPATH+"action/";
		String html=FreeMarkerUtil.getString("pageFormat");
		 if(column.getParent()==null){
			 if(indexDynamic.equals("0")&&lg==null){
				 path=basePath+"index";
			 }else{
				 path=basePath+"mn/index";
			 }
			 if(FreeMarkerUtil.getWebsite().getIndexDynamics().equals("1")&&lg==null){
				path=Const.BASEPATH+"index"+html;
				column.setName("首页");
			 }
			 if(FreeMarkerUtil.getWebsite().getIndexDynamics().equals("1")&&lg!=null){
				 path=Const.BASEPATH+"index_"+lg+html;
				 column.setName("index");
			 }
			
		 }else{
			 if(column.getPageNum()==0&&!column.getColumnType().equals("5")){
				//动态主页路径取id
				 path =(indexDynamic.equals("0")?basePath+"page/"+String.valueOf(column.getId()):columnpath+html);
				 if(lg!=null)
					 path =(indexDynamic.equals("0")?basePath+"page/"+String.valueOf(column.getId())+"/"+lg:columnpath+"_"+lg+html);
			 }else if(column.getPageNum()>0&&!column.getColumnType().equals("5")){
				 //判断栏目页数是否为0 为0则 path=上则 若不等于0 则 url=list/id/pagenum or path/columnPth/pagenum
				 path=basePath+"list/"+String.valueOf(column.getId())+"/1";
				 if(lg!=null)
					 path=basePath+"list/"+lg+"/"+String.valueOf(column.getId())+"/1";
			 }
			 //栏目属性为单页的页面路径
			 if(column.getColumnType().equals("5")){
//					List<Content> list;
//					try {
//						list = contentService.getContentByWebsite(column);
//						if(list.size()>0)
////							path=Const.BASEPATH+"page/"+column.getColumnPath()+"/"+list.get(0).getId()+FreeMarkerUtil.getString("pageFormat");
//							path=Const.BASEPATH+"page/"+list.get(0).getId()+html;
//					} catch (Throwable e) {
//						e.printStackTrace();
//					}
					
			 }
		 }
		 return path;
	}
}
