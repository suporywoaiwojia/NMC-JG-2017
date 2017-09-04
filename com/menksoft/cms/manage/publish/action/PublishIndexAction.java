/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : PublishWebsite.java
 * 描述        : 内容发布
 * 作者        : 呼和
 * 日期        : 下午4:29:06
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       下午4:29:06  呼和
 */
package com.menksoft.cms.manage.publish.action;




//import org.apache.log4j.Logger;
import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.menksoft.cms.freemarker.FreeMarkerUtil;
import com.menksoft.cms.freemarker.service.MakePageService;
import com.menksoft.cms.manage.publish.service.PublishContentService;
import com.menksoft.cms.manage.publish.service.PublishIndexService;
import com.menksoft.util.Const;
/**
 * @author 呼和
 */
@Controller

public class PublishIndexAction {
//	private Logger log = Logger.getLogger(this.getClass());
	@javax.annotation.Resource
	MakePageService MakePageService; 
	@javax.annotation.Resource
	PublishIndexService publishIndexService;

	@javax.annotation.Resource
	PublishContentService publishContentService;
	@RequestMapping(value="/publish/index/list")
	public String goList(ModelMap modelMap) {
		//主页静态标志 1为静态 0为动态
		try{
		String indexDynamics=FreeMarkerUtil.getWebsite().getIndexDynamics();
		
		modelMap.put("indexDynamics", indexDynamics);
		}catch(Exception e){
			
			return "manage/errer.html";
		}
		return "manage/publish/pulish_in.jsp";
	}
	
	/**
	 * 生成首页
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/publish/index")
	public void freemarkerPage(ModelMap modelMap,HttpServletResponse response) {
		String message="";
		PrintWriter writer = null;
		try {  
			response.setContentType("text/html;charset=UTF-8");
			writer = response.getWriter();
			message=MakePageService.MakePage("index.html", "index","",response)+"</br>";
			
			//获取其他语言版本主页模版
			
//			String[] files =files("index");
//			for(int i=0;i<files.length;i++){
//				message=message+MakePageService.MakePage(files[i], files[i].replace(".html", ""),"",response)+"</br>";
//			}
//			//message=MakePageService.MakePage("wap_index.html", "wap_index","",response)+"</br>";
//			List <Columns> list=new ArrayList<Columns>();
//			list=publishIndexService.getColumnsFindex();
//			for(int i=0;i<list.size();i++){
//				List<ColumnLanguage> list_lg=columnlanguageService.getCl(list.get(i).getId());
//				message=message+MakePageService.MakePage("column//"+list.get(i).getListModel(), list.get(i).getId()+"_index", "\\page\\",response)+"<br/>";
//				//生成其他语言二级主页
//				String model=list.get(i).getListModel();
//				int p=model.lastIndexOf(".");
//				for(int a=0;a<list_lg.size();a++){
//					StringBuilder  sb=new StringBuilder(model);
//					message=message+MakePageService.MakePage("column//"+sb.insert(p, "_"+list_lg.get(a).getLanguage()), list.get(i).getId()+"_index_"+list_lg.get(a).getLanguage(), "\\page\\",response)+"<br/>";
//				}
//					
//			}
			MakePageService.MakePage("regedit.html", "regedit","",response);
		} catch (Throwable e) {
			modelMap.put(Const.MESSAGE_NAME, e);
		}
		writer.print(message);
		writer.flush();
		writer.close();
	}
	/**
	 * 删除首页
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/publish/delete")
	public String deleteIndexPage(ModelMap modelMap){
		try{
			String msg=publishIndexService.deleteIndexPage();
			modelMap.put(Const.MESSAGE_NAME, msg);
		}catch(Exception e){
			
		return "manage/errer.html";
		}
		return "manage/publish/pulish_index.jsp";
	}
	
	/**
	 * 整站生成
	 * @param modelMap
	 * @param response
	 */
	@RequestMapping(value="/build/allpage")
	public void buildAllPage(ModelMap modelMap,HttpServletResponse response) {
		//首页生成
		String message="";
		  PrintWriter writer = null;
		//主页静态标志 1为静态 0为动态
		  String indexDynamics=FreeMarkerUtil.getWebsite().getIndexDynamics();
//		  String listDynamics=FreeMarkerUtil.getWebsite().getListDynamics();
		try {
			
			response.setContentType("text/html;charset=UTF-8");
			writer = response.getWriter();
			if(indexDynamics.equals("1")){
				message=MakePageService.MakePage("regedit.html", "regedit","",response)+"</br>";
				message=message+MakePageService.MakePage("index.html", "index","",response)+"</br>";
				message=message+MakePageService.MakePage("wap_index.html", "wap_index","",response)+"</br>";
				//获取其他语言版本主页模版
				String[] regedit=files("regedit");
				for(int i=0;i<regedit.length;i++){
					message=message+MakePageService.MakePage(regedit[i], regedit[i].replace(".html", ""),"",response)+"</br>";
				}
				String[] index=files("index");
				for(int i=0;i<index.length;i++){
					message=message+MakePageService.MakePage(index[i], index[i].replace(".html", ""),"",response)+"</br>";
				}
				
			}
			message=message+publishContentService.contentHtmlPageAll(response);
//			message=message+publishColumnService.columnHtmlPageAll(0, indexDynamics, response);
			
		}  catch (Throwable e) {
//			e.printStackTrace();
			if(e.getMessage().indexOf("Template")!=-1&&e.getMessage().indexOf("Template")!=-1)
				message="发布失败：模版未设置！("+e.getMessage()+")";
			if(e.getMessage().indexOf("Error in template")!=-1)
				message="发布失败："+e.getMessage().replace("\n", "</br>").replace("on line ", "模版错误位置:")
						.replace(", column ", "行,字符")+"</br>模版发生错误，请检查该模版";
			modelMap.put(Const.MESSAGE_NAME, e);
		}
		writer.println(message);
		writer.flush();
		writer.close();
	}
	
	public String[] files(final String filename){
		File f = new File(Const.APP_ROOT+"\\WEB-INF\\views\\template\\");
		String[] files = f.list(new FilenameFilter() {
		    @Override
		    public boolean accept(File dir, String name) {
		        return name.startsWith(filename+"_");
		    }
		});
		return files;
	}
}
