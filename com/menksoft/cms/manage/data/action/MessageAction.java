package com.menksoft.cms.manage.data.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.application.organization.service.UserService;
import com.menksoft.cms.manage.data.entity.Message;
import com.menksoft.cms.manage.data.service.CheckRepeatService;
import com.menksoft.cms.manage.data.service.MessageService;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.entity.ContentFile;
import com.menksoft.cms.manage.webContent.entity.Country;
import com.menksoft.cms.manage.webContent.entity.DataType;
import com.menksoft.cms.manage.webContent.entity.Language;
import com.menksoft.cms.manage.webContent.entity.PublishHouse;
import com.menksoft.cms.manage.webContent.service.ColumnsService;
import com.menksoft.cms.manage.webContent.service.ContentFileService;
import com.menksoft.cms.manage.webContent.service.CountryService;
import com.menksoft.cms.manage.webContent.service.DataTypeService;
import com.menksoft.cms.manage.webContent.service.LanguageService;
import com.menksoft.cms.manage.webContent.service.PublishHouseService;
import com.menksoft.cms.manage.webContent.util.SetContentFile;



@Controller
public class MessageAction {
	 @Resource
	 private MessageService messageService;
	 @Resource
	 private  CheckRepeatService crService;
	 
	 @Resource
	 private ColumnsService columnsService;
	 
	 @Resource
	 private DataTypeService dataTypeService;
	 
	 @Resource
	 private LanguageService languageService;
	 
	 @Resource 
	 private CountryService countryService;
	 @Resource
	 private PublishHouseService publishHouseService;
	 @Resource
	 private ContentFileService contentFileService;
	 @Resource
	 private UserService userService;
	 
	 //重写日期和对象绑定的格式
	 @InitBinder
	 protected void initBinder(WebDataBinder binder) {
	     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	 }
	    /**
	     * 返回新建页面
	     * @param modelMap
	     * @param 
	     * @return
	     */
	  @RequestMapping(value="/manage/data/message/addNew/{columnId}",method=RequestMethod.GET)
	   	public String addNew(ModelMap modelMap,@PathVariable("columnId") String columnId,HttpServletRequest request){
		  //查询所有的column，返回到页面提供选择
		System.out.println(columnId+"==============================");
		try {
			Columns column = columnsService.getColumnsById(Long.valueOf(columnId));
			modelMap.addAttribute("column", column);

			//查询到所有的栏目
			 List<Columns>  columnList = columnsService.getAllColumn();
			 modelMap.addAttribute("columnList", columnList);
			 //查询到所有国籍
			 List<Country> countryList = countryService.getCountrys();
			 modelMap.addAttribute("countryList", countryList);

			
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
	            
		  return "/manage/data/message/data_list_message_new.jsp";
	    }
	 
	    /**
	     * 
	     * 保存提交的数据，返回到列表页
	     * @param modelMap
	     * @param message ajax提交的json数据，转换为对象
	     * @return
	     */
	    @RequestMapping(value="/manage/data/message/save",method=RequestMethod.POST)
	   	public @ResponseBody String save(ModelMap modelMap,Message message,HttpServletRequest request){
	    	String msg = "0";
	    	try {
	    		//每次保存，都需要修改状态为待审
	    		//3---通过；2----待审
	    		String approveState =columnsService.getColumnsById(message.getColumn().getId()).getApprove() ;
	    		
		    	if (approveState!=null&&approveState.equals("0")) {
		    		message.setState("3");
		    	}else {
		    		message.setState("2");
				}
    			messageService.save(message);
    			msg="1";
	    		
	   		} catch (Throwable e) {
	   			e.printStackTrace();
	   			
	   		}
	   		return msg;
	   	}

	    /**
	     * 依据数据ID跳转到编辑页面
	     * @param modelMap
	     * @param  message
	     * @return
	     */
	    @RequestMapping(value="/manage/data/message/edite/{Id}",method=RequestMethod.GET)
	    public String getMessageById(ModelMap modelMap,@PathVariable("Id") String Id,HttpServletRequest request){
	    	String msg="/manage/data/message/data_list_message_edit.jsp";
	    	
	    	try {
	    		//查找到当前message
	    	    Message message = messageService.getMessagebyId(Long.valueOf(Id));
	    	    modelMap.addAttribute("message", message);
	    	    //查找到当前对应的column
				Columns column = columnsService.getColumnsById(message.getColumn().getId());
				modelMap.addAttribute("column", column);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  
	    	return msg;
	    	
	    }
	    /**
	     * 编辑数据并保存
	     * @param modelMap
	     * @param  message
	     * @return
	     */
	    @RequestMapping(value="/manage/data/message/update",method=RequestMethod.POST)
	    @ResponseBody
	   	public String update(ModelMap modelMap,Message message,HttpServletRequest request){
	   		String msg="0";
	   		
	   		try {
	   		//  b=crService.getRepeatListForUpdate(Message.class,message.getBm(), message.getName(), "r_data_hmstyle",message.getId());
	   		//每次保存，都需要修改状态为待审
	    		//3---通过；2----待审
	    		String approveState =columnsService.getColumnsById(message.getColumn().getId()).getApprove() ;
	    		
		    	if (approveState!=null&&approveState.equals("0")) {
		    		message.setState("3");
		    	}else {
		    		message.setState("2");
				}
	    			messageService.update(message);
	    			msg="1";
	   		    
	   		} catch (Throwable e) {
	   			e.printStackTrace();
	   		}
	   		return msg;
	   	}
		
	    /**
	     * 删除数据
	     * @param modelMap
	     * @param message
	     * @return
	     */
	    @RequestMapping(value="/manage/data/message/delete/{id}",method=RequestMethod.GET)
	   	public @ResponseBody String delete(ModelMap modelMap,@PathVariable("id") String id){
	    	String msg= "0";
	   		try {
	   	    	messageService.delete(id);
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
      @RequestMapping(value="/manage/data/message/query/{startIndex}",method=RequestMethod.POST)
	    
	    public String getListDataByConditions(ModelMap modelMap,
	    		@PathVariable("startIndex") Integer startIndex,
	    		Message message,HttpServletRequest request){
    	  PagingTools<Message> pagingTools = null;
    	  String pageString = "";
    	try {
    		//查询到所有符合条件的实体
    		Integer count = 20;
    		pagingTools = messageService.queryMessages(message, Integer.valueOf(startIndex), count);
    		modelMap.addAttribute("pagingTool", pagingTools);
    		
			//用于判断是审核页面还是内容页面
			  modelMap.put("approveState", "0");
			  
            //返回当前所属栏目
			  
			  Columns column = columnsService.getColumnsById(message.getColumn().getId());
			  modelMap.addAttribute("column", column);


		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
    	  return pageString;
    	  
      }
	    /**
	     * 依据栏目的ID，查询获得所有的数据
	     * @param modelMap
	     * @return
	     */
	    @RequestMapping(value="/manage/data/message/{columnID}/{startIndex}/{count}",method=RequestMethod.GET)
	    public String getListDataByColumn(ModelMap modelMap,@PathVariable("columnID") String columnID,
	    		@PathVariable("startIndex") String startIndex,@PathVariable("count") String count){
	    	
	    	PagingTools<Message> listTools = null;
	    	String path = "";
				try {
					//查询到数据库中存储的返回的页面的地址path
					Columns columns = columnsService.getColumnsById(Long.valueOf(columnID));
					path=columns.getColumnPath();
					System.out.println(path+"============");
					//查询到对应页数的数据
					listTools = messageService.getMessages(Integer.valueOf(startIndex), Integer.valueOf(count));
					modelMap.addAttribute("pagingTool", listTools);
					//返回当前目录
					 modelMap.put("column", columns);
					//控制是审核列表页面还是内容列表页面
					 modelMap.put("approveState", "0");
					
					//查询到所有的数据类型，返回
				     List<Country> countryList = countryService.getCountrys();
				     modelMap.addAttribute("countryList", countryList);
					  
			       //用于新建时访问当前被选中的栏目的ID
					 modelMap.put("columnId", columns.getId());
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	    	return path;
	    }


}
