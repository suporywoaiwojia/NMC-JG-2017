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



import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.menksoft.cms.freemarker.FreeMarkerUtil;
import com.menksoft.cms.freemarker.service.MakePageService;
import com.menksoft.cms.manage.publish.service.PublishColumnService;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.service.ColumnsService;
import com.menksoft.util.Const;


/**
 * @author 呼和
 */
@Controller

public class PublishColumnAction {
	private Logger log = Logger.getLogger(this.getClass());
	@javax.annotation.Resource
	MakePageService MakePageService; 
	@javax.annotation.Resource
	ColumnsService columnsService; 
	@javax.annotation.Resource
	PublishColumnService publishColumnService; 
	
	@RequestMapping(value="/publish/column/list")
	public String goList(ModelMap modelMap) {
		Columns root=null;
		try {
			root = columnsService.getRootColumns();
			List<Columns> columns = columnsService.getAllChildColumnsByColumn(root);
			columns.add(0, root);
			
			
			
//			String xml =  new DHXTreeUtil().buildDHXTreeForXMLString(columns);
//			modelMap.put("xml", xml);
			//列表页静态标志 1为静态 0为动态
			String listDynamics=FreeMarkerUtil.getWebsite().getListDynamics();
			modelMap.put("listDynamics", listDynamics);
		} catch (Throwable e) {
			log.error("栏目静态页面生成" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
			modelMap.put(Const.MESSAGE_NAME, e);
		}
		return "manage/publish/pulish_column.jsp";
	}
	
	/**
	 * 生成栏目内容
	 * @param modelMap
	 * @param channelId	栏目ID
	 * @return
	 */
	@RequestMapping(value="/publish/channelList/{channelId}")
	public void freemarkerChannelPage(ModelMap modelMap,@PathVariable("channelId") String channelId,
			HttpServletResponse response,HttpServletRequest request) {
		String message="";
		PrintWriter writer = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			writer = response.getWriter();
			message=publishColumnService.columnHtmlPage(channelId,0,"1","1",response);
		} catch (Throwable e) {
			modelMap.put(Const.MESSAGE_NAME, e);
//			System.out.println(e.getMessage());
		}
		writer.print(message);
		writer.flush();
		writer.close();
	}
	

}
