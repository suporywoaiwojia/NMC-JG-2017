///**
// * 项目名称: 蒙科立SSH框架
// * 文件名     : ColumnsAction.java
// * 描述          : 
// * 作者          : 王弘
// * 日期          : 2012-11-17
// * 公司          : 内蒙古安达传媒文化有限责任公司
// *
// * 修改记录:
// * ------------------------------------------------------------------------------
// * 版本:  1.00
// * 
// * |  序号    |  修改日期     |  作者     |     备注            |  
// *     1     2012-11-17    王弘               新建文件
// */
//package com.menksoft.cms.manage.webContent.action;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JsonConfig;
//import net.sf.json.util.CycleDetectionStrategy;
//import net.sf.json.util.PropertyFilter;
//
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.util.Assert;
//import org.springframework.web.bind.ServletRequestUtils;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.menksoft.cms.manage.webContent.entity.Columns;
//import com.menksoft.cms.manage.webContent.service.ColumnsService;
//import com.menksoft.cms.manage.webContent.util.TemplateUtil;
//import com.menksoft.util.Const;
//
///**
// * @author wangh
// * 栏目控制器类，负责栏目管理各类业务处理的控制转发。
// */
//@Controller
//public class ColumnsAction {
//	private Logger log = Logger.getLogger(this.getClass());
//	
//	@Resource
//	private ColumnsService columnsService;
//
//	
//	@RequestMapping(value="/manage/columns/list/{parentID}")
//	public String forwardToColumnsListPage(Columns column, ModelMap modelMap,@PathVariable("parentID") long parentID) {
//		try {
//			//下面的代码没有用到
////			long rootId = 1;
//			Columns paclomn = columnsService.getColumnsById(parentID);
//			System.out.println(paclomn.getName());
//			List<Columns> columns = new ArrayList<Columns>();
//			columns = columnsService.getChildColumnsByColumn(paclomn);
//			System.out.println(columns.size());
//			modelMap.put("data", columns);
////			modelMap.put("columnId", column.getId());
//            for (int i = 0; i < columns.size(); i++) {
//				System.out.println(columns.get(i).getName());
//			}
//
//		} catch (Throwable e) {
//			log.error("栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
//			modelMap.put(Const.MESSAGE_NAME, e);
//			// TODO 错误处理，需要跳转到公共错误页面。
//		}
//		
//		return "manage/columns/columnsMain.jsp";
//	}
//
//	/**
//	 * 依据上级目录的id，查询其所有子目录
//	 * @param modelMap
//	 * @param parentID
//	 * @return
//	 */
//	@RequestMapping(value="/manage/columns/sublist/{parentID}",method=RequestMethod.GET)
//    public  @ResponseBody JSONArray querySubColumns(ModelMap modelMap,@PathVariable("parentID") long parentID){
//		JSONArray jsonArray=null;
//		 JsonConfig cfg = new JsonConfig(); 
//		 cfg.setJsonPropertyFilter(new PropertyFilter()  
//		    {  
//		         public boolean apply(Object source, String name, Object value) {  
//		           if(name.equals("columnPath")||name.equals("actionPath")||name.equals("columnType")||name.equals("listModel")
//		        		   ||name.equals("contentModel")||name.equals("albumModel")||name.equals("pageNum")||name.equals("columnOrder")
//		        		   ||name.equals("approve")||name.equals("views")||name.equals("state")||name.equals("parentPath")
//		        		   ||name.equals("tablename")||name.equals("parent")||name.equals("no")) {  
//		             return true;  
//		           } else {  
//		             return false;  
//		          }  
//		        }  
//		       });  
//		    //net.sf.json.JSONException: java.lang.reflect.InvocationTargetException异常  
//		   cfg.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
//		   cfg.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);      
//		try {
//			Columns paclomn = columnsService.getColumnsById(parentID);
//			List<Columns> columnsNow = new ArrayList<Columns>();
//			columnsNow = columnsService.getChildColumnsByColumn(paclomn);
//			jsonArray=JSONArray.fromObject(columnsNow,cfg);
//		} catch (Throwable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 return jsonArray;
//    }
//	
//	@RequestMapping(value="/manage/columns/edit", method=RequestMethod.GET)
//	public String forwardToColumnsEditPage(Columns column, ModelMap modelMap) {
//		try {
//			Columns root = columnsService.getRootColumns();
//			List<Columns> columns = columnsService.getAllChildColumnsByColumn(root);
//			columns.add(0, root);
//			
//			
//			
//			column = columnsService.getColumnsById(column.getId());
//			String[] columnTemplateFilesName = TemplateUtil.getColumnTemplateFilesName();
//			String[] contentTemplateFilesName = TemplateUtil.getContentTemplateFilesName();
//			modelMap.put("column", column);
//			modelMap.put("columnTemplateFiles", columnTemplateFilesName);
//			modelMap.put("contentTemplateFiles", contentTemplateFilesName);
//		} catch (Throwable e) {
//			log.error("栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
//			modelMap.put(Const.MESSAGE_NAME, e);
//		}
//		
//		return "manage/columns/columnsEdit.jsp";
//	}
//	
//	/**
//	 * 删除栏目
//	 * @param ids
//	 * @return
//	 */
//	@RequestMapping(value="/manage/columns/delete/{ids}", method=RequestMethod.GET)
//	public @ResponseBody String deleteColumns(@PathVariable String ids) {
//		Assert.hasText(ids, "栏目信息"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED);
//		String msg="0";
//		String[] idArray = ids.split(",");
//		try {
//			columnsService.deleteBatch(idArray);
//			msg="1";
//		} catch (Throwable e) {
//			log.error("栏目信息"+Const.MESSAGE_FAIL_UNCHECKED_DELETE, e);
//			throw new RuntimeException(e);
//		}
//		return msg;
//	}
//	
//	/**
//	 * 创建新的栏目
//	 * @param modelMap
//	 * @param parentId
//	 * @return
//	 */
//	@RequestMapping(value="/manage/columns/add/{parentId}", method=RequestMethod.GET)
//	public String forwardToColumnsAddPage(ModelMap modelMap, @PathVariable String parentId) {
//		try {
//			
//			String[] columnTemplateFilesName = TemplateUtil.getColumnTemplateFilesName();
//			String[] contentTemplateFilesName = TemplateUtil.getContentTemplateFilesName();
//			
//			Columns parent = columnsService.getColumnsById(Long.parseLong(parentId));
//			
//			modelMap.put("columnTemplateFiles", columnTemplateFilesName);
//			modelMap.put("contentTemplateFiles", contentTemplateFilesName);
//			modelMap.put("parent", parent);
//			
////			modelMap.put("albumModelTemplateFile", parent.getAlbumModel());
//		} catch (Throwable e) {
//			log.error("栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
//			modelMap.put(Const.MESSAGE_NAME, e);
//		}
//		
//		return "manage/columns/columnsAdd.jsp";
//	}
//	
//	@RequestMapping(value="/manage/columns/save", method=RequestMethod.POST)
//	public @ResponseBody String addColumn(Columns column, HttpServletRequest request, ModelMap modelMap) {
//		long parentId = ServletRequestUtils.getLongParameter(request, "parentId", 0);
//		Columns parent = new Columns();
//		parent.setId(parentId);
//	//	String[] lName=null;
//	//	String[] language=null;
//		String msg="0";
//		try {
////			//获取多语言标志 及 名称
////			lName=ServletRequestUtils.getStringParameters(request, "lname");
////			language=ServletRequestUtils.getStringParameters(request, "language");
//			
//			column.setParent(parent);
//			columnsService.saveColumns(column);
////			for(int i=0;i<lName.length;i++){
////				ColumnLanguage cl=new ColumnLanguage();
////				if(lName[i]!=null&&!lName[i].equals("")){
////					cl.setColumn_id(column.getId());
////					cl.setLanguage(language[i]);
////					cl.setName(lName[i]);
////					columnLanguageService.saveColumnL(cl);
////				}
////			}
//			modelMap.put(Const.MESSAGE_NAME, Const.MESSAGE_SUCCESS_SAVE);
//			modelMap.put("newColumn", column);
//			msg="1";
//		} catch (Throwable e) {
//			log.error("栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
//			modelMap.put(Const.MESSAGE_NAME, e);
//		}
//		
//		return msg;
//	}
//	
//	@RequestMapping(value="/manage/columns/update", method=RequestMethod.POST)
//	public @ResponseBody String updateColumn(Columns column, HttpServletRequest request, ModelMap modelMap) {
//		//依据提交的对象，取出各自属性，更新保存
//		long parentId = ServletRequestUtils.getLongParameter(request, "parentId", 0);
//		Columns parent = new Columns();
//		parent.setId(parentId);
//	//	String[] lName=null;
//	//	String[] language=null;
//		String msg="0";
//		try {
//			//获取多语言标志 及 名称
////			lName=ServletRequestUtils.getStringParameters(request, "lname");
////			language=ServletRequestUtils.getStringParameters(request, "language");
//			
//			column.setParent(parent);
//			column = columnsService.updateColumns(column);
//			//columnLanguageService.delClbyColumn_id(column.getId());
////			for(int i=0;i<lName.length;i++){
////				ColumnLanguage cl=new ColumnLanguage();
////				if(lName[i]!=null&&!lName[i].equals("")){
////					cl.setColumn_id(column.getId());
////					cl.setLanguage(language[i]);
////					cl.setName(lName[i]);
////					columnLanguageService.saveColumnL(cl);
////				}
////			}
//			modelMap.put(Const.MESSAGE_NAME, Const.MESSAGE_SUCCESS_SAVE);
//			modelMap.put("newColumn", column);
//			msg="1";
//		} catch (Throwable e) {
//			log.error("栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
//			modelMap.put(Const.MESSAGE_NAME, e);
//		}
//		
//		return msg;
//	}
//	
//	@RequestMapping(value="/manage/columns/path/{id}", method=RequestMethod.GET)
//	public @ResponseBody String getpath(ModelMap modelMap,@PathVariable long id) {
//		String path="";
//		try {
//			Columns c=columnsService.getColumnsById(id);
//			if(c.getActionPath()!=null)
//				path=c.getActionPath();
//		} catch (Throwable e) {
//			log.error("栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
//			modelMap.put(Const.MESSAGE_NAME, e);
//		}
//		
//		return path;
//	}
//}
/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : ColumnsAction.java
 * 描述          : 
 * 作者          : 王弘
 * 日期          : 2012-11-17
 * 公司          : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *     1     2012-11-17    王弘               新建文件
 */
package com.menksoft.cms.manage.webContent.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.application.organization.service.UserService;
import com.menksoft.cms.manage.data.entity.Content;
import com.menksoft.cms.manage.data.entity.Item;
import com.menksoft.cms.manage.data.service.ContentService;
import com.menksoft.cms.manage.data.service.ItemService;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.entity.DataType;
import com.menksoft.cms.manage.webContent.entity.Language;
import com.menksoft.cms.manage.webContent.service.ColumnsService;
import com.menksoft.cms.manage.webContent.service.DataTypeService;
import com.menksoft.cms.manage.webContent.service.LanguageService;
import com.menksoft.cms.manage.webContent.util.TemplateUtil;
import com.menksoft.util.Const;

/**
 * @author wangh
 * 栏目控制器类，负责栏目管理各类业务处理的控制转发。
 */
@Controller
public class ColumnsAction {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private ColumnsService columnsService;
    @Resource
    private ItemService itemService;
    
    @Resource 
    private ContentService contentService;
    
    @Resource
    private UserService userService;
    
    @Resource
    private DataTypeService dataTypeService;
    
    @Resource
    private LanguageService languageService;
    
    
	@RequestMapping(value="/manage/columns/main")
	public String forwardToColumnsMainPage(ModelMap modelMap) {
		try {
			Columns column = columnsService.getRootColumns();
			List<Columns> columns = columnsService.getAllChildColumnsByColumn(column);
			columns.add(0, column);
			
			
		} catch (Throwable e) {
			log.error("栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
			modelMap.put(Const.MESSAGE_NAME, e);
			// TODO 错误处理，需要跳转到公共错误页面。
		}
		return "manage/columns/columnsMain.jsp";
	}
	@RequestMapping(value="/manage/columns/list")
	public String forwardToColumnsListPage(Columns column, ModelMap modelMap) {
		try {
			List<Columns> columns = columnsService.getChildColumnsByColumn(column);
			modelMap.put("columns", columns);
			modelMap.put("columnId", column.getId());
		} catch (Throwable e) {
			log.error("栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
			modelMap.put(Const.MESSAGE_NAME, e);
			// TODO 错误处理，需要跳转到公共错误页面。
		}
		
		return "manage/columns/columnsList.jsp";
	}
	
	@RequestMapping(value="/manage/columns/edit", method=RequestMethod.GET)
	public String forwardToColumnsEditPage(Columns column, ModelMap modelMap) {
		try {
			Columns root = columnsService.getRootColumns();
			List<Columns> columns = columnsService.getAllChildColumnsByColumn(root);
			columns.add(0, root);
			
			
			column = columnsService.getColumnsById(column.getId());
			String[] columnTemplateFilesName = TemplateUtil.getColumnTemplateFilesName();
			String[] contentTemplateFilesName = TemplateUtil.getContentTemplateFilesName();
			
			modelMap.put("column", column);
			modelMap.put("columnTemplateFiles", columnTemplateFilesName);
			modelMap.put("contentTemplateFiles", contentTemplateFilesName);
		} catch (Throwable e) {
			log.error("栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
			modelMap.put(Const.MESSAGE_NAME, e);
		}
		
		return "manage/columns/columnsEdit.jsp";
	}
	
	@RequestMapping(value="/manage/columns/delete/{ids}", method=RequestMethod.GET)
	public @ResponseBody String deleteColumns(@PathVariable String ids) {
		Assert.hasText(ids, "栏目信息"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED);
		String msg="0";
		String[] idArray = ids.split(",");
		try {
			columnsService.deleteBatch(idArray);
			msg="1";
		} catch (Throwable e) {
			log.error("栏目信息"+Const.MESSAGE_FAIL_UNCHECKED_DELETE, e);
			throw new RuntimeException(e);
		}
		return msg;
	}
	
	@RequestMapping(value="/manage/columns/add/{parentId}", method=RequestMethod.GET)
	public String forwardToColumnsAddPage(ModelMap modelMap, @PathVariable String parentId) {
		try {
			
			String[] columnTemplateFilesName = TemplateUtil.getColumnTemplateFilesName();
			String[] contentTemplateFilesName = TemplateUtil.getContentTemplateFilesName();
			
			Columns parent = columnsService.getColumnsById(Long.parseLong(parentId));
			
			modelMap.put("columnTemplateFiles", columnTemplateFilesName);
			modelMap.put("contentTemplateFiles", contentTemplateFilesName);
			modelMap.put("parent", parent);
			
//			modelMap.put("albumModelTemplateFile", parent.getAlbumModel());
		} catch (Throwable e) {
			log.error("栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
			modelMap.put(Const.MESSAGE_NAME, e);
		}
		
		return "manage/columns/columnsAdd.jsp";
	}
	
	@RequestMapping(value="/manage/columns/save", method=RequestMethod.POST)
	public String addColumn(Columns column, HttpServletRequest request, ModelMap modelMap) {
		long parentId = ServletRequestUtils.getLongParameter(request, "parentId", 0);
		Columns parent = new Columns();
		parent.setId(parentId);
		String[] lName=null;
		String[] language=null;
		try {
//			//获取多语言标志 及 名称
//			lName=ServletRequestUtils.getStringParameters(request, "lname");
//			language=ServletRequestUtils.getStringParameters(request, "language");
			
			column.setParent(parent);
			columnsService.saveColumns(column);
//			for(int i=0;i<lName.length;i++){
//				ColumnLanguage cl=new ColumnLanguage();
//				if(lName[i]!=null&&!lName[i].equals("")){
//					cl.setColumn_id(column.getId());
//					cl.setLanguage(language[i]);
//					cl.setName(lName[i]);
//					columnLanguageService.saveColumnL(cl);
//				}
//			}
			modelMap.put(Const.MESSAGE_NAME, Const.MESSAGE_SUCCESS_SAVE);
			modelMap.put("newColumn", column);
		} catch (Throwable e) {
			log.error("栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
			modelMap.put(Const.MESSAGE_NAME, e);
		}
		
		return forwardToColumnsListPage(parent, modelMap);
	}
	
	@RequestMapping(value="/manage/columns/update", method=RequestMethod.POST)
	public String updateColumn(Columns column, HttpServletRequest request, ModelMap modelMap) {
		long parentId = ServletRequestUtils.getLongParameter(request, "parentId", 0);
		Columns parent = new Columns();
		parent.setId(parentId);
		String[] lName=null;
		String[] language=null;
		try {
			//获取多语言标志 及 名称
//			lName=ServletRequestUtils.getStringParameters(request, "lname");
//			language=ServletRequestUtils.getStringParameters(request, "language");
			
			column.setParent(parent);
			columnsService.updateColumns(column);
//			columnLanguageService.delClbyColumn_id(column.getId());
//			for(int i=0;i<lName.length;i++){
//				ColumnLanguage cl=new ColumnLanguage();
//				if(lName[i]!=null&&!lName[i].equals("")){
//					cl.setColumn_id(column.getId());
//					cl.setLanguage(language[i]);
//					cl.setName(lName[i]);
//					columnLanguageService.saveColumnL(cl);
//				}
//			}
			modelMap.put(Const.MESSAGE_NAME, Const.MESSAGE_SUCCESS_SAVE);
		//	modelMap.put("newColumn", column);
		} catch (Throwable e) {
			log.error("栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
			modelMap.put(Const.MESSAGE_NAME, e);
		}
		
		return forwardToColumnsListPage(parent, modelMap);
	}
	
	
	
    /**
     * 查询待审核的数据内容,返回内容，和审核状态
     * @param modelMap
     * @param  ItemcontentId
     * @return
     */
	@RequestMapping(value="/check/manage/columns/path/{id}/{startIndex}/{count}/{type}", method=RequestMethod.GET)
	public  String getCheckContent(ModelMap modelMap,@PathVariable long id,
			@PathVariable("startIndex") Integer startIndex,
			@PathVariable("count") Integer count,
			@PathVariable("type") String type,HttpServletRequest request) {
		String path="";
		//首先取得当前登录的账户，匹配审核人，查询到所有的数据，显示数据状态，对待审核的数据，可以进行查看和审核，提出意见，审核通过或者退回
		//获取登陆用户

		
	    SecurityContextImpl securityContextImpl = (SecurityContextImpl) request  
				  .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		  
		String temName = securityContextImpl.getAuthentication().getName();
		String userName = temName.substring(0, temName.indexOf("-")) ;
		User user = userService.getUserWithRolesByLoginId(userName);
		System.out.println(user.getUserName()+"+++"+user.getLoginId()+"====");
        try {
			
			Columns c=columnsService.getColumnsById(id);
			if(c.getColumnPath()!=null){
				path=c.getColumnPath();
			}
			//查询数据,查询审核人和登录用户匹配的信息
			if (c!=null&&user!=null) {
				if(type.equals("special")){//声像图文栏目,
					PagingTools<Item> pagingTools  = itemService.getItemsByColumnAndApprove(c, user, startIndex, count);
							
					modelMap.put("pagingTools", pagingTools);
					//添加传递来的栏目
					modelMap.put("approveState", "1");
				}else if (type.equals("common")) {//通用栏目
					PagingTools<Content> pagingTools = contentService.getContentsByColumnAndApprove(c, user, startIndex, count);
					
					modelMap.put("pagingTools", pagingTools);
					modelMap.put("approveState", "1");
				}
			   
			}

		} catch (Throwable e) {
			log.error("栏目信息" + Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
			modelMap.put(Const.MESSAGE_NAME, e);
		}
		return path;
		
	}
}
