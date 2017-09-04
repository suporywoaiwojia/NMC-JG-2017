package com.menksoft.cms.manage.data.action;

import java.io.File;
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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.application.organization.service.UserService;
import com.menksoft.cms.manage.data.entity.Rapper;
import com.menksoft.cms.manage.data.service.CheckRepeatService;
import com.menksoft.cms.manage.data.service.RapperService;
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
import com.menksoft.util.CopyFileTool;



@Controller
public class RapperAction {
	 @Resource
	 private RapperService rapperService;
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
	  @RequestMapping(value="/manage/data/rapper/addNew/{columnId}",method=RequestMethod.GET)
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
	            
		  return "/manage/data/scyr/data_list_scyr_new.jsp";
	    }
	 
	    /**
	     * 
	     * 保存提交的数据，返回到列表页
	     * @param modelMap
	     * @param rapper ajax提交的json数据，转换为对象
	     * @return
	     */
	    @RequestMapping(value="/manage/data/rapper/save",method=RequestMethod.POST)
	   	public @ResponseBody String save(ModelMap modelMap,Rapper rapper,HttpServletRequest request){
	    	String msg = "0";
	    	try {
	    		//每次保存，都需要修改状态为待审
	    		//3---通过；2----待审
	    		String approveState =columnsService.getColumnsById(rapper.getColumn().getId()).getApprove() ;
	    		System.out.println(rapper.getName()+"============"+rapper.getAddress());
		    	if (approveState!=null&&approveState.equals("0")) {
		    		rapper.setState("3");
		    	}else {
		    		rapper.setState("2");
				}
    			rapperService.save(rapper);
    			msg="1";
	    		
	   		} catch (Throwable e) {
	   			e.printStackTrace();
	   			
	   		}
	   		return msg;
	   	}

	    /**
	     * 依据数据ID跳转到编辑页面
	     * @param modelMap
	     * @param  rapper
	     * @return
	     */
	    @RequestMapping(value="/manage/data/rapper/edite/{Id}",method=RequestMethod.GET)
	    public String getRapperById(ModelMap modelMap,@PathVariable("Id") String Id,HttpServletRequest request){
	    	String msg="/manage/data/scyr/data_list_scyr_edit.jsp";
	    	
	    	try {
	    		//查找到当前rapper
	    	    Rapper rapper = rapperService.getRapperbyId(Long.valueOf(Id));
	    	    modelMap.addAttribute("rapper", rapper);
	    	    //查找到当前对应的column
				Columns column = columnsService.getColumnsById(rapper.getColumn().getId());
				modelMap.addAttribute("column", column);
				//查询到所有的栏目
				 List<Columns>  columnList = columnsService.getAllColumn();
				 modelMap.addAttribute("columnList", columnList);
				 //查询到所有国籍
				 List<Country> countryList = countryService.getCountrys();
				 modelMap.addAttribute("countryList", countryList);
				 
				 //查询到可以审核的用户
				  List<User> approveList = userService.getUserOfApprove();
				  modelMap.put("approveList", approveList);
				  //用于判断是内容页面还是审核
				 modelMap.addAttribute("approveState", "0");
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  
	    	return msg;
	    	
	    }
	    
	    /**
	     * 依据数据ID跳转到审核页面
	     * @param modelMap
	     * @param  rapper
	     * @return
	     */
	    @RequestMapping(value="/check/manage/data/rapper/edite/{Id}",method=RequestMethod.GET)
	    public String loadApprovePageById(ModelMap modelMap,@PathVariable("Id") String Id,HttpServletRequest request){
	    	String msg="/manage/data/scyr/data_list_scyr_edit.jsp";
	    	
	    	try {
	    		//查找到当前rapper
	    	    Rapper rapper = rapperService.getRapperbyId(Long.valueOf(Id));
	    	    modelMap.addAttribute("rapper", rapper);
	    	    //查找到当前对应的column
				Columns column = columnsService.getColumnsById(rapper.getColumn().getId());
				modelMap.addAttribute("column", column);
				//查询到所有的栏目
				 List<Columns>  columnList = columnsService.getAllColumn();
				 modelMap.addAttribute("columnList", columnList);
				 //查询到所有国籍
				 List<Country> countryList = countryService.getCountrys();
				 modelMap.addAttribute("countryList", countryList);
				 
				 //查询到可以审核的用户
				  List<User> approveList = userService.getUserOfApprove();
				  modelMap.put("approveList", approveList);
				  //用于判断是内容页面还是审核
				  modelMap.addAttribute("approveState", "1");
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  
	    	return msg;
	    	
	    }
	    /**
	     * 编辑数据并保存
	     * @param modelMap
	     * @param  rapper
	     * @return
	     */
	    @RequestMapping(value="/manage/data/rapper/update",method=RequestMethod.POST)
	    @ResponseBody
	   	public String update(ModelMap modelMap,Rapper rapper,HttpServletRequest request){
	   		String msg="0";
	   		
	   		try {
	   		//  b=crService.getRepeatListForUpdate(Rapper.class,rapper.getBm(), rapper.getName(), "r_data_hmstyle",rapper.getId());
	   		//每次保存，都需要修改状态为待审
	    		//3---通过；2----待审
	    		String approveState =columnsService.getColumnsById(rapper.getColumn().getId()).getApprove() ;
	    		//将文件复制到指定文件夹,并设置rapper的头像访问地址
	    	    rapper = createRapper(request,rapper);
		    	if (approveState!=null&&approveState.equals("0")) {
		    		rapper.setState("3");
		    	}else {
		    		rapper.setState("2");
				}
	    			rapperService.update(rapper);
	    			msg="1";
	   		    
	   		} catch (Throwable e) {
	   			e.printStackTrace();
	   		}
	   		return msg;
	   	}
		
	    /**
	     * 删除数据
	     * @param modelMap
	     * @param rapper
	     * @return
	     */
	    @RequestMapping(value="/manage/data/rapper/delete/{id}",method=RequestMethod.GET)
	   	public @ResponseBody String delete(ModelMap modelMap,@PathVariable("id") String id){
	    	String msg= "0";
	    	System.out.println("+++++++++++++++++++++++++++++++++++++++");
	   		try {
	   	    	rapperService.delete(id);
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
      @RequestMapping(value="/manage/data/rapper/query/{startIndex}",method=RequestMethod.POST)
	    
	    public String getListDataByConditions(ModelMap modelMap,
	    		@PathVariable("startIndex") Integer startIndex,
	    		Rapper rapper,HttpServletRequest request){
    	  PagingTools<Rapper> pagingTools = null;
    	  String pageString = "";
    	try {
    		//查询到所有符合条件的实体
    		Integer count = 20;
    		pagingTools = rapperService.queryRappers(rapper, Integer.valueOf(startIndex), count);
    		modelMap.addAttribute("pagingTool", pagingTools);
    		
			//用于判断是审核页面还是内容页面
			  modelMap.put("approveState", "0");
			  

			//查询到所有的栏目
			  List<Columns>  columnList = columnsService.getAllColumn();
			  modelMap.addAttribute("columnList", columnList);


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
	    @RequestMapping(value="/manage/data/rapper/{columnID}/{startIndex}/{count}",method=RequestMethod.GET)
	    public String getListDataByColumn(ModelMap modelMap,@PathVariable("columnID") String columnID,
	    		@PathVariable("startIndex") String startIndex,@PathVariable("count") String count){
	    	
	    	PagingTools<Rapper> listTools = null;
	    	String path = "";
				try {
					//查询到数据库中存储的返回的页面的地址path
					Columns columns = columnsService.getColumnsById(Long.valueOf(columnID));
					path=columns.getColumnPath();
					System.out.println(path+"============");
					//查询到对应页数的数据
					listTools = rapperService.getRappers(Integer.valueOf(startIndex), Integer.valueOf(count));
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

	    /**
	     * 依据栏目的ID，查询获得所有的数据,进入到审核列表页面
	     * @param modelMap
	     * @return
	     */
	    @RequestMapping(value="/check/manage/data/rapper/{columnID}/{startIndex}/{count}",method=RequestMethod.GET)
	    public String loadApprovePListByColumn(ModelMap modelMap,@PathVariable("columnID") String columnID,
	    		@PathVariable("startIndex") String startIndex,@PathVariable("count") String count){
	    	
	    	PagingTools<Rapper> listTools = null;
	    	String path = "";
				try {
					//查询到数据库中存储的返回的页面的地址path
					Columns columns = columnsService.getColumnsById(Long.valueOf(columnID));
					path=columns.getColumnPath();
					System.out.println(path+"============");
					//查询到对应页数的数据
					listTools = rapperService.getRappers(Integer.valueOf(startIndex), Integer.valueOf(count));
					modelMap.addAttribute("pagingTool", listTools);
					//返回当前目录
					 modelMap.put("column", columns);
					//控制是审核列表页面还是内容列表页面
					 modelMap.put("approveState", "1");
					
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
	    
	    protected Rapper createRapper(HttpServletRequest request,Rapper rapper) {
	    		//获取 图片文件名称
	    		String[] filesName = ServletRequestUtils.getStringParameters(request, "filesName");
	    		//获取 图片文件路径
	    		String[] filesUploadPath = ServletRequestUtils.getStringParameters(request, "filesUploadPath");
	    		//获取 图片文件路径
	    		String[] filesHttpPath = ServletRequestUtils.getStringParameters(request, "filesHttpPath");
	    	    
	    			//对保存的数据，复制到最终的目录中
	    	    
	    	    rapper.setFilesHttpPath(filesHttpPath[0]);
	    	    rapper.setFilesName(filesName[0]);
	    	   //上传临时文件路径
	    	    rapper.setFilesUploadPath(filesUploadPath[0]);
	    	    
	    	    String filePathTemp = filesUploadPath[0];
				String filePath =filePathTemp.replaceFirst("temp", "portrait");
				System.out.println(filePathTemp+"============"+filePath);
				//变换路径
				File fileSour = new File(filePathTemp);
				File fileDes = new File(filePath);
	    	    CopyFileTool.fileChannelCopy(fileSour,fileDes);
	    		return rapper;
	    	}
}
