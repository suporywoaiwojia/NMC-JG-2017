/**
 * Copyright (c) 2011, The David.Wang All rights reserved.
 * 
 * You can use this software for free.
 * If you want to report bug, please send mail to SeptWolves800515@gmail.com
 */
package com.menksoft.cms.manage.login.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menksoft.cms.application.permission.entity.Resource;
import com.menksoft.cms.application.permission.service.ResourceService;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.service.ColumnsService;
import com.menksoft.cms.phone.action.DateJsonValueProcessor;
//import com.menksoft.cms.manage.preferences.service.impl.UserPreferencesServiceImpl;
import com.menksoft.util.Const;
import com.menksoft.util.TreeBuild;

/**
 * @author David
 *
 */
@Controller
public class ManageLogin {
	private Logger log = Logger.getLogger(this.getClass());
	
	@javax.annotation.Resource
	ResourceService resourceService;
	@javax.annotation.Resource
	private ColumnsService columnsService;
//	@javax.annotation.Resource
//	private UserPreferencesServiceImpl userPreferencesService;
	
	@RequestMapping(value="/manage/login", method=RequestMethod.GET)
	public String forwardToManagerLoginPage() {
		return "manage/login.jsp";
	}

	@RequestMapping(value="/manage/main", method=RequestMethod.GET)
	public String forwardToManageMainPage(ModelMap modelMap) {
		List<Resource> resources = null;
		try {
			resources = resourceService.getRootModuleResources();
			modelMap.addAttribute("list",resources);
			//--------项目树信息
			List<Columns> columns1=null;
			List<Map<String,Object>> comboTreeList  =new ArrayList<Map<String,Object>>(); 
			Columns column = columnsService.getRootColumns();
			columns1=columnsService.getAllColumn();
			columns1.add(0,column);
			comboTreeList=TreeBuild.createComboTreeTree(columns1);
			 JSONArray jsonArray =JSONArray.fromObject(comboTreeList);
			modelMap.put("Treedata", jsonArray);
		} catch (Throwable e) {
			log.error("系统资源" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
			// TODO 错误处理，需要跳转到公共错误页面。
		}
		return "manage/main.jsp";
				//return "manage/main-v1.1.jsp";
	}
	
	/**
	 * 获取树结构
	 * @return
	 */
	@RequestMapping(value="/manage/column", method=RequestMethod.GET)
	public 	@ResponseBody JSONArray getColumnlist(){
		
		List<Columns> columns1=null;
		List<Map<String,Object>> comboTreeList  =new ArrayList<Map<String,Object>>(); 
		try {
		
			Columns column = columnsService.getRootColumns();
			columns1=columnsService.getAllColumn();
			columns1.add(0,column);
			comboTreeList=TreeBuild.createComboTreeTree(columns1);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 JSONArray jsonArray =JSONArray.fromObject(comboTreeList);
		 return jsonArray;
	}
	@RequestMapping(value="/manage/right", method=RequestMethod.GET)
	public String forwardToright(ModelMap modelMap) {
		
		
		return "manage/right.jsp";
	}
	@RequestMapping(value="/manage/leftsubmain/{parentid}", method=RequestMethod.GET)
	public @ResponseBody JSONArray forwardToleftsubmain(ModelMap modelMap,@PathVariable("parentid") long parentid) {
		List<Resource> resources = null;
		 JSONArray jsonArray =null;
		JsonConfig cfg = new JsonConfig();  
		 cfg.setExcludes(new String[]{"handler","hibernateLazyInitializer"}); 
		try {
			 cfg.setJsonPropertyFilter(new PropertyFilter()  
			    {  
			         public boolean apply(Object source, String name, Object value) {  
			           if(name.equals("parent")||name.equals("type")||name.equals("roles")) {  
			             return true;  
			           } else {  
			             return false;  
			          }  
			        }  
			       });
			 cfg.setExcludes(new String[]{"handler","hibernateLazyInitializer"}); 
			 cfg.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);     
			 cfg.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd")); 
			Resource p=new Resource();
			Resource resource=new Resource();
			p.setId(parentid);
			resource.setParent(p);
			resources = resourceService.getChildResources(resource);
			jsonArray  = JSONArray.fromObject( resources,cfg);
		
		} catch (Throwable e) {
			log.error("系统资源" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
			// TODO 错误处理，需要跳转到公共错误页面。
		}
		
		return jsonArray;
	}
	@RequestMapping(value="/manage/submain/{parentId}/{parentName}", method=RequestMethod.GET)
	public String forwardToSubMainPage(ModelMap modelMap,
			@PathVariable("parentId") long parentId,@PathVariable("parentName") String parentName) {
		List<Resource> resources = null;
		Resource resource=new Resource();
		Resource parent=new Resource();
		parent.setId(parentId);
		resource.setParent(parent);
		resources=resourceService.getChildResources(resource);
		modelMap.addAttribute("menuName",parentName);
		modelMap.addAttribute("list",resources);
		return "manage/left.jsp";
	}
	
	@RequestMapping(value="/manage/leftdesktop", method=RequestMethod.GET)
	public String forwardToSubMainPage(ModelMap modelMap) {
		return "manage/leftDesktop.jsp";
	}
	
	@RequestMapping(value="/manage/demo", method=RequestMethod.GET)
	public String forwardToDemoPage(ModelMap modelMap) {
		return "manage/demo/list.jsp";
	}
	
	@RequestMapping(value="/manage/accesscontrol/sessioninvalid")
	public String forwardToSessionInvalidPage(HttpServletRequest request) {
		String page = "index.jsp";
		String url = request.getRequestURI();
		if (url.indexOf("/wap/") != -1) {
			page = "wapindex.jsp";
		}
		
		return page;
	}
	
	
}
