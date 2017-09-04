package com.menksoft.cms.manage.data.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.inject.New;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.application.organization.service.UserService;
import com.menksoft.cms.manage.data.entity.Content;
import com.menksoft.cms.manage.data.entity.Item;
import com.menksoft.cms.manage.data.service.CheckRepeatService;
import com.menksoft.cms.manage.data.service.ContentService;
import com.menksoft.cms.manage.data.service.ItemService;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.entity.DataType;
import com.menksoft.cms.manage.webContent.entity.Language;
import com.menksoft.cms.manage.webContent.service.ColumnsService;
import com.menksoft.cms.manage.webContent.service.DataTypeService;
import com.menksoft.cms.manage.webContent.service.LanguageService;


@Controller
public class ContentAction {
	 @Resource
	 private ContentService contentService;
	 @Resource
	 private  CheckRepeatService crService;
	 
	 @Resource
	 private ColumnsService columnsService;
	 @Resource
	 private ItemService itemService;
	 
	 @Resource
	 private UserService userService;
	 
	 @Resource
	 private DataTypeService dataTypeService;
	 
	 @Resource 
	 private LanguageService languageService;
	 
	    /**
	     * 返回新建页面
	     * @param modelMap
	     * @param 
	     * @return
	     */
	  @RequestMapping(value="/manage/data/content/addNew/{columnId}",method=RequestMethod.GET)
	   	public String addNew(ModelMap modelMap,@PathVariable("columnId") String columnId,HttpServletRequest request){
	   
		  try {
	      //，返回当前栏目
		  Columns column = columnsService.getColumnsById(Long.valueOf(columnId));
		 
		  modelMap.put("column", column);
		 
		  //查询到所有的数据类型，返回
		  List<DataType> typeList = dataTypeService.getDataTypes();
		  modelMap.addAttribute("typeList", typeList);
		  
		//查询到所有的语言，返回
		  List<Language> languageList = languageService.getLanguages();
		  modelMap.addAttribute("languageList", languageList);
		  
		  //查询到当前登录的用户，查询到可以审核的用户
		  SecurityContextImpl securityContextImpl = (SecurityContextImpl) request  
				  .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		  
		String temName = securityContextImpl.getAuthentication().getName();
		String userName = temName.substring(0, temName.indexOf("-")) ;
		
	    User loginUser = userService.getUserWithRolesByLoginId(userName);
	    		
	    if (loginUser!=null) {
	    
			modelMap.put("loginUser", loginUser);
		}
		  List<User> approveList = userService.getUserOfApprove();
		  modelMap.put("approveList", approveList);
		  
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return "/manage/data/data_list_common_new.jsp";
	    }
	 
	    /**
	     * 
	     * 保存提交的数据，返回到列表页
	     * @param modelMap
	     * @param content ajax提交的json数据，转换为对象
	     * @return
	     */
	    @RequestMapping(value="/manage/data/content/save",method=RequestMethod.POST)
	   	public @ResponseBody String save(ModelMap modelMap,Content content,HttpServletRequest request){
	    	String msg = "0";
	        
	    	try {
			    	//查询栏目的审核开关,如果不为空，且等于0，即关闭审核，则数据保存后状态为“通过”，否则为待审
	    		
	    		String approveState =columnsService.getColumnsById(content.getColumnContents().getId()).getApprove() ;
	    		
			    	if (approveState!=null&&approveState.equals("0")) {
			    		 content.setState("3");
			    	}else{
			    		content.setState("2");
					}
	    			contentService.save(content);
	    			msg="1";
	    		
	   		} catch (Throwable e) {
	   			e.printStackTrace();
	   			
	   		}
	   		return msg;
	   	}
	    /**
	     * 
	     * 暂存数据，即没有提交审核，状态为1，审核人为空
	     * @param modelMap
	     * @param content ajax提交的json数据，转换为对象
	     * @return
	     */
	    @RequestMapping(value="/manage/data/content/tempSave",method=RequestMethod.POST)
	   	public @ResponseBody String tempSave(ModelMap modelMap,Content content){
	    	String msg = "0";
	    	
	    	try {
	    		content.setApprove(null);
		    	content.setState("1");
				contentService.save(content);
				msg="1";
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return msg;
	    }
	    /**
	     * 
	     * 发布数据，修改数据状态为5,能够在前台查看
	     * @param modelMap
	     * @param content ajax提交的json数据，转换为对象
	     * @return
	     */ 
	    @RequestMapping(value="/manage/data/content/publish/{contentId}",method=RequestMethod.GET)
	   	public @ResponseBody String publishData(ModelMap modelMap,@PathVariable("contentId") Long contentId){
	    	String msg = "0";
	    	try {
	    		Content content = contentService.getContentbyId(contentId);
	    		content.setState("5");
				contentService.update(content);
				msg="1";
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return msg;
	    }
	    /**
	     * 跳转到审核页面
	     * @param modelMap
	     * @param  content
	     * @return
	     */
	    @RequestMapping(value="/check/manage/data/content/approve/{contentId}",method=RequestMethod.GET)
		  
	   	public String loadToApprovePage(ModelMap modelMap,@PathVariable("contentId") String contentId){	   
	    	String page = "/manage/data/data_list_common_edit.jsp";

			try {
				  //查询到所有栏目
			    List<Columns> columnList =  columnsService.getAllColumn();
			    modelMap.put("columnList", columnList);
			    //所有具有审核的人员
			    List<User> approveList = userService.getUserOfApprove();
			    modelMap.put("approveList", approveList);
		        //查询到内容
				Content currentCon = contentService.getContentbyId(Long.valueOf(contentId));
				//fuwenben
				if(currentCon.getTextAreaContent()!=null){
				String richTextString = new String(currentCon.getTextAreaContent(),"GBK");
				modelMap.put("richText", richTextString);
				
				}
				//查询到所有的数据类型，返回
				  List<DataType> typeList = dataTypeService.getDataTypes();
				  modelMap.addAttribute("typeList", typeList);
				//查询到所有的语言，返回
				  List<Language> languageList = languageService.getLanguages();
				  modelMap.addAttribute("languageList", languageList); 
				//将查询到的对象返回  
				 modelMap.put("currentCon", currentCon);
				//将审核状态返回
				 modelMap.addAttribute("approveState", "1");
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return page;
	    }
	    /**
	     * 提交审核意见，和审核结果
	     * @param modelMap
	     * @param  content
	     * @return
	     */
	    @RequestMapping(value="/check/manage/data/content/approve/{contentId}/{approveOpinion}/{state}",method=RequestMethod.GET)
	 	public String submitOpinionAndResult(ModelMap modelMap,@PathVariable("contentId") String contentId,
	 			@PathVariable("approveOpinion") String approveOpinion,
	 			@PathVariable("state") String  state){	
	 		String page = "/manage/data/data_list_common.jsp";
	 		try {
				Content currentCon = contentService.getContentbyId(Long.valueOf(contentId));
				currentCon.setApproveOpinion(approveOpinion);
				currentCon.setState(state);
				contentService.update(currentCon);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		return page;
	 	}
	    	
	    /**
	     * 跳转到编辑页面
	     * @param modelMap
	     * @param  content
	     * @return
	     */
	    @RequestMapping(value="/manage/data/content/edit/{contentId}",method=RequestMethod.GET)
	  
	   	public String loadToEditePage(ModelMap modelMap,@PathVariable("contentId") long contentId){
	    	String page = "/manage/data/data_list_common_edit.jsp";
	    	try {
	        //查询到所有栏目
		    List<Columns> columnList =  columnsService.getAllColumn();
		    modelMap.put("columnList", columnList);
		    //所有具有审核的人员
		    List<User> approveList = userService.getUserOfApprove();
		    modelMap.put("approveList", approveList);
	        //查询到内容
			Content currentCon = contentService.getContentbyId(contentId);
			//fuwenben
			if(currentCon.getTextAreaContent()!=null){
			String richTextString = new String(currentCon.getTextAreaContent(),"GBK");
			modelMap.put("richText", richTextString);
			
			}
			//查询到所有的数据类型，返回
			  List<DataType> typeList = dataTypeService.getDataTypes();
			  modelMap.addAttribute("typeList", typeList);
			//查询到所有的语言，返回
			  List<Language> languageList = languageService.getLanguages();
			  modelMap.addAttribute("languageList", languageList); 
			  //返回查询到的对象
			modelMap.put("currentCon", currentCon);
			//用于审核还是内容判断
			modelMap.addAttribute("approveState", "0");
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return page;
	    	
	    }
	    /**
	     * 编辑数据并保存
	     * @param modelMap
	     * @param  content
	     * @return
	     */
	    @RequestMapping(value="/manage/data/content/update",method=RequestMethod.POST)
	    @ResponseBody
	   	public String update(ModelMap modelMap,Content content){
	   		String msg="0";
	   	
	   		try {
	   		//  b=crService.getRepeatListForUpdate(Content.class,content.getBm(), content.getName(), "r_data_hmstyle",content.getId());
	   		   //查询栏目的审核开关,如果不为空，且等于0，即关闭审核，则数据保存后状态为“通过”，否则为待审
		    	String approveState =columnsService.getColumnsById(content.getColumnContents().getId()).getApprove() ;
		    			
		    	
		    	if (approveState!=null&&approveState.equals("0")) {
		    		
		    		 content.setState("3");
		    	}else{
		    		content.setState("2");
				}
	   			 contentService.update(content);
	    			msg="1";	    		
	   		    
	   		} catch (Throwable e) {
	   			e.printStackTrace();
	   		}
	   		return msg;
	   	}
		
	    /**
	     * 删除数据
	     * @param modelMap
	     * @param content
	     * @return
	     */
	    @RequestMapping(value="/manage/data/content/delete/{id}",method=RequestMethod.GET)
	   	public @ResponseBody String delete(ModelMap modelMap,@PathVariable("id") String id){
	    	String msg= "0";
	   		try {
	  		  contentService.delete(id);
	  		  msg="1";
	   		} catch (Throwable e) {
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   		}
	   		return msg;
	   	}
	    /**
	     * 依据提交的条件，查询获得的数据
	     * @param modelMap
	     * @return
	     */
	    @RequestMapping(value="/manage/data/content/query/{startIndex}",method=RequestMethod.POST)
	    
	    public String getListDataByColumn(ModelMap modelMap,
	    		@PathVariable("startIndex") Integer startIndex,
	    		Content content,HttpServletRequest request){
	    	
	    	PagingTools<Content> pagingTools = null;
	    	String msg = "/manage/data/data_list_common.jsp";
				try {
					request.setCharacterEncoding("utf-8");
					Integer count = 20;
					pagingTools = contentService.queryContents(content, startIndex, count);
					modelMap.put("pagingTool", pagingTools);
					
				     //approveState为0，则是内容页面，否则为审核页面
					  modelMap.put("approveState", "0");
					//查询到所有的数据类型，返回
					  List<DataType> typeList = dataTypeService.getDataTypes();
					  modelMap.addAttribute("typeList", typeList);
					  
					//查询到所有的语言，返回
					  List<Language> languageList = languageService.getLanguages();
					  modelMap.addAttribute("languageList", languageList);
					//查询到所有的栏目
					  List<Columns>  columnList = columnsService.getAllColumn();
					  modelMap.addAttribute("columnList", columnList);
					//查询到当前的栏目
					  Columns columns = columnsService.getColumnsById(content.getColumnContents().getId());
					  modelMap.addAttribute("column", columns);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	    	return msg;
	    }
	    /**
	     * 依据栏目的ID，查询获得所有的数据，翻页查询
	     * @param modelMap
	     * @return
	     */
	    @RequestMapping(value="/manage/data/content/{columnID}/{startIndex}/{count}",method=RequestMethod.GET)
	    public String getListDataByColumn(ModelMap modelMap,@PathVariable("columnID") String columnID,
	    		@PathVariable("startIndex") String startIndex,@PathVariable("count") String count){
	    	PagingTools<Content> listTools = null;
	    	String path = "";
				try {
					//查询到数据库中存储的返回的页面的地址path
					Columns columns = columnsService.getColumnsById(Long.valueOf(columnID));
					path=columns.getColumnPath();
					//查询到对应页数的数据
					listTools = contentService.getContentsByColumn(columns, Integer.valueOf(startIndex), Integer.valueOf(count));
					modelMap.addAttribute("pagingTool", listTools);
					//返回当前目录
					 modelMap.put("column", columns);
					//控制是审核列表页面还是内容列表页面
					 modelMap.put("approveState", "0");
					
					//查询到所有的数据类型，返回
				     List<DataType> typeList = dataTypeService.getDataTypes();
				     modelMap.addAttribute("typeList", typeList);
					  
					//查询到所有的语言，返回
					 List<Language> languageList = languageService.getLanguages();
					 modelMap.addAttribute("languageList", languageList);
					//查询到所有的栏目
					 List<Columns>  columnList = columnsService.getAllColumn();
					 modelMap.addAttribute("columnList", columnList);
			       //用于新建时访问当前被选中的栏目的ID
					 modelMap.put("columnId", columns.getId());
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	    	return path;
	    }
	    /**
	     * 进入审核页面，依据栏目的ID，查询获得所有的数据
	     * @param modelMap
	     * @return
	     */
	    @RequestMapping(value="/check/manage/data/content/{columnID}/{startIndex}/{count}",method=RequestMethod.GET)
	    public String loadApproveListByColumn(ModelMap modelMap,@PathVariable("columnID") String columnID,
	    		@PathVariable("startIndex") String startIndex,@PathVariable("count") String count){
	    	PagingTools<Content> listTools = null;
	    	String path = "";
				try {
					//查询到数据库中存储的返回的页面的地址path
					Columns columns = columnsService.getColumnsById(Long.valueOf(columnID));
					path=columns.getColumnPath();
					//查询到对应页数的数据
					listTools = contentService.getContentsByColumn(columns, Integer.valueOf(startIndex), Integer.valueOf(count));
					modelMap.addAttribute("pagingTool", listTools);
					//返回当前目录
					 modelMap.put("column", columns);
					//控制是审核列表页面还是内容列表页面
					 modelMap.put("approveState", "1");
					
					//查询到所有的数据类型，返回
				     List<DataType> typeList = dataTypeService.getDataTypes();
				     modelMap.addAttribute("typeList", typeList);
					  
					//查询到所有的语言，返回
					 List<Language> languageList = languageService.getLanguages();
					 modelMap.addAttribute("languageList", languageList);
					//查询到所有的栏目
					 List<Columns>  columnList = columnsService.getAllColumn();
					 modelMap.addAttribute("columnList", columnList);
			       //用于新建时访问当前被选中的栏目的ID
					 modelMap.put("columnId", columns.getId());
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	    	return path;
	    }
	    /**
	     * 查询获得所有的数据
	     * @param modelMap
	     * @return
	     */
	    @RequestMapping(value="/data/content/queryall",method=RequestMethod.GET)
		public String getList(ModelMap modelMap){
			List<Content> list = new ArrayList<Content>();
			try {
			   list = contentService.getContents();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			modelMap.put("datalist", list);
			return "manage/data/data_list_content.jsp";
		}
	    
	    /**
	     * 检查实体是否重复
	     * @param modelMap
	     * @param content
	     * @return
	     */
	  
	    public String checkBMAndName(Content content) {
	    	List<Content> list = new ArrayList<Content>();
				 String msgString = "0";
				try {
					list = contentService.checkRepeat(content);
					   if(list.size()>0){
						   msgString="1";
					   }
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		    	return msgString;
		}
}
