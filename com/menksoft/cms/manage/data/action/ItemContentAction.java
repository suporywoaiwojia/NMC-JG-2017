package com.menksoft.cms.manage.data.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;

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
import com.menksoft.cms.manage.data.entity.ItemContent;
import com.menksoft.cms.manage.data.entity.Catalog;
import com.menksoft.cms.manage.data.entity.Item;
import com.menksoft.cms.manage.data.service.ItemContentService;
import com.menksoft.cms.manage.data.service.CatalogService;
import com.menksoft.cms.manage.data.service.CheckRepeatService;
import com.menksoft.cms.manage.data.service.ItemService;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.entity.ContentFile;
import com.menksoft.cms.manage.webContent.service.ColumnsService;
import com.menksoft.cms.manage.webContent.service.ContentFileService;
import com.menksoft.cms.manage.webContent.util.SetContentFile;



@Controller
public class ItemContentAction {
	 @Resource
	 private ItemService itemService;
	 @Resource
	 private  CheckRepeatService crService;
	 
	 @Resource
	 private ItemContentService itemContentService;
	 
	 @Resource
	 private CatalogService catalogService;
	 
	 @Resource
	 private ColumnsService columnsService;
	 
	 @Resource
	 private UserService userService;
	 
	 @Resource
	 private ContentFileService contentFileService;
	 
	 @InitBinder
	 protected void initBinder(WebDataBinder binder) {
	     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	 }
	    /**
	     * 返回新建页面
	     * @param modelMap
	     * @param type区分是目录还是条目下，创建内容
	     * @return
	     */
	  @RequestMapping(value="/manage/data/itemContent/addNew/{Id}/{type}",method=RequestMethod.GET)
	   	public String addNew(HttpServletRequest request,ModelMap modelMap,@PathVariable("Id") String Id,
	   			@PathVariable("type") String type){
		  //查询所有的column，返回到页面提供选择
	
		try {
		   //查询到对应的catalog或者item，返回,如果type为item则为条目，如果为catalog则是目录
			Catalog catalog =null;
			Item item = null;
			if(type.equals("catalog")){
	    	 catalog = catalogService.getCatalogbyId(Long.valueOf(Id));
		     modelMap.addAttribute("catalog", catalog);
	    	//查询对应item
	    	item = catalog.getCatalogItem();
	    	modelMap.addAttribute("item", item);
		   }else if(type.equals("item")){
			item = itemService.getItembyId(Long.valueOf(Id));   
			modelMap.addAttribute("item", item); 
		   }

	    	//查询到当前内容所属栏目，返回附件存储地址
			Columns columns = item.getParent();
			modelMap.addAttribute("columns", columns);
		   //查询到当前登录的用户，查询到可以审核的用户
			SecurityContextImpl securityContextImpl = (SecurityContextImpl) request  
					  .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
			  
			String temName = securityContextImpl.getAuthentication().getName();
			String userName = temName.substring(0, temName.indexOf("-")) ;
		    User loginUser = userService.getUserWithRolesByLoginId(userName);
		    if (loginUser!=null) {
				modelMap.put("loginUser", loginUser);
			}
		    
		    //查询到所有具有审核权限的用户
			  List<User> approveList = userService.getUserOfApprove();
			  modelMap.put("approveList", approveList);
	    	
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	            
		  return "/manage/data/sxtw/content/data_itemContent_new.jsp";
	    }
	 
	    /**
	     * 
	     * 保存提交的数据，返回到列表页
	     * @param modelMap
	     * @param item ajax提交的json数据，转换为对象
	     * @return
	     */
	    @RequestMapping(value="/manage/data/itemContent/save",method=RequestMethod.POST)
	   	public @ResponseBody String save(ModelMap modelMap,ItemContent itemContent,HttpServletRequest request){
	    	String msg = "0";
	    	List<ContentFile> cf_list=new ArrayList<ContentFile>();//附件
	    	try {
	    		
    			itemContentService.save(itemContent);
    			//获取并保存附件,类型为内容，参数选3
    			cf_list=SetContentFile.getFileList(request,itemContent.getId(),"3");
    			contentFileService.save(cf_list, itemContent.getId(),"3");
    			msg="1";
	    		
	   		} catch (Throwable e) {
	   			e.printStackTrace();
	   			
	   		}
	   		return msg;
	   	}

	    /**
	     * 依据数据ID跳转到编辑页面
	     * @param modelMap
	     * @param  item
	     * @return
	     */
	    @RequestMapping(value="/manage/data/itemContent/edite/{Id}",method=RequestMethod.GET)
	    public String getItemById(ModelMap modelMap,@PathVariable("Id") String Id,HttpServletRequest request){
	    	String msg="/manage/data/sxtw/content/data_itemContent_edit.jsp";
	    
	    	try {
				//查询到对应的内容资料对象
	    		ItemContent itemContent = itemContentService.getItemContentbyId(Long.valueOf(Id));
				modelMap.addAttribute("itemContent", itemContent);
				//查询到对应的所有附件内容
	    		List<ContentFile> listContents = itemContent.getContentFiles();
	    		modelMap.addAttribute("listContents", listContents);
	    		
				 //查询到对应的catalog或者item，返回
		    	 //如果查询到包含目录，则返回目录，入果没有目录，则直接是资料条目
				Catalog catalog = itemContent.getContentsCatalog();
			    modelMap.addAttribute("catalog", catalog);
			    
		    	//查询和内容对应的条目，跳过目录直接查询,查询对应item
		    	Item item = itemContent.getContentsItem();
		    	modelMap.addAttribute("item", item);
		    	//查询到当前内容所属栏目，返回附件存储地址
				Columns columns = item.getParent();
				modelMap.addAttribute("columns", columns);
			   //查询到当前登录的用户，查询到可以审核的用户
				SecurityContextImpl securityContextImpl = (SecurityContextImpl) request  
						  .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				  
				String temName = securityContextImpl.getAuthentication().getName();
				String userName = temName.substring(0, temName.indexOf("-")) ;
			    User loginUser = userService.getUserWithRolesByLoginId(userName);
			    if (loginUser!=null) {
					modelMap.put("loginUser", loginUser);
				}
			    
			    //查询到所有具有审核权限的用户
				  List<User> approveList = userService.getUserOfApprove();
				  modelMap.put("approveList", approveList);
					//返回控制审核的字段
					modelMap.addAttribute("approveState", "0");
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  
	    	return msg;
	    	
	    }
	    /**
	     * 进入到审核页面，依据数据ID跳转到编辑页面
	     * @param modelMap
	     * @param  item
	     * @return
	     */
	   @RequestMapping(value="/check/manage/data/itemContent/edite/{Id}",method=RequestMethod.GET)
	    public String loadApprovePageById(ModelMap modelMap,@PathVariable("Id") String Id,HttpServletRequest request){
	    	String msg="/manage/data/sxtw/content/data_itemContent_edit.jsp";
	    
	    	try {
				//查询到对应的内容资料对象
	    		ItemContent itemContent = itemContentService.getItemContentbyId(Long.valueOf(Id));
				modelMap.addAttribute("itemContent", itemContent);
				//查询到对应的所有附件内容
	    		List<ContentFile> listContents = itemContent.getContentFiles();
	    		modelMap.addAttribute("listContents", listContents);
	    		
				 //查询到对应的catalog或者item，返回
		    	 //如果查询到包含目录，则返回目录，入果没有目录，则直接是资料条目
				Catalog catalog = itemContent.getContentsCatalog();
			    modelMap.addAttribute("catalog", catalog);
			    
		    	//查询和内容对应的条目，跳过目录直接查询,查询对应item
		    	Item item = itemContent.getContentsItem();
		    	modelMap.addAttribute("item", item);
		    	//查询到当前内容所属栏目，返回附件存储地址
				Columns columns = item.getParent();
				modelMap.addAttribute("columns", columns);
			   //查询到当前登录的用户，查询到可以审核的用户
				SecurityContextImpl securityContextImpl = (SecurityContextImpl) request  
						  .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				  
				String temName = securityContextImpl.getAuthentication().getName();
				String userName = temName.substring(0, temName.indexOf("-")) ;
			    User loginUser = userService.getUserWithRolesByLoginId(userName);
			    if (loginUser!=null) {
					modelMap.put("loginUser", loginUser);
				}
			    
			    //查询到所有具有审核权限的用户
				  List<User> approveList = userService.getUserOfApprove();
				  modelMap.put("approveList", approveList);
					//返回控制审核的字段
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
	     * @param  item
	     * @return
	     */
	    @RequestMapping(value="/manage/data/itemcontent/update",method=RequestMethod.POST)
	    @ResponseBody
	   	public String update(ModelMap modelMap,ItemContent itemContent,HttpServletRequest request){
	   		String msg="0";
	   		List<ContentFile> cf_list=new ArrayList<ContentFile>();//附件
	   		try {
	   		//  b=crService.getRepeatListForUpdate(Item.class,item.getBm(), item.getName(), "r_data_hmstyle",item.getId());
	    	
	    			itemContentService.update(itemContent);
	    			//获取并保存附件,类型选3，对应内容
	    			cf_list=SetContentFile.getFileList(request,itemContent.getId(),"3");
	    			contentFileService.save(cf_list, itemContent.getId(),"3");
	    			msg="1";
	   		    
	   		} catch (Throwable e) {
	   			e.printStackTrace();
	   		}
	   		return msg;
	   	}
		
	    /**
	     * 删除数据
	     * @param modelMap
	     * @param item
	     * @return
	     */
	    @RequestMapping(value="/manage/data/itemContent/delete/{id}",method=RequestMethod.GET)
	   	public @ResponseBody String delete(ModelMap modelMap,@PathVariable("id") String id){
	    	String msg= "0";
	   		try {
	   		  //先删除关联的附件，置空相关的外键，然后删除实体
	   		//List<ContentFile> listContentFiles
	   	    ItemContent itemContent = itemContentService.getItemContentbyId(Long.valueOf(id));	
	     	contentFileService.deleteFiles(itemContent.getContentFiles());	
            //删除内容实体
	     	if (id!=null&&id!="") {
	     		itemContentService.delete(id);
			}
	  		  msg="1";
	   		} catch (Throwable e) {
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   		}
	   		return msg;
	   	}
	    /**
	     * 依据传入的条件，查询获得所有的数据
	     * @param modelMap
	     * @return
	     */
	    @RequestMapping(value="/manage/data/itemContent/query/{startIndex}",method=RequestMethod.POST)
	   	public String queryList(ModelMap modelMap,@PathVariable("startIndex") Integer startIndex,
	   			ItemContent itemContent,HttpServletRequest request){
	    	String page = "/manage/data/sxtw/content/data_list_itemContent.jsp";
	    	
	    	try {
				//查询到所有符合条件的内容
				Integer count = 20;
				PagingTools<ItemContent> listTools= itemContentService.queryItems(itemContent, Integer.valueOf(startIndex), count);
				modelMap.addAttribute("pagingTool", listTools);
				//查询到对应的item
				Item item = itemContent.getContentsItem();
				modelMap.addAttribute("itemTem", item);
				
				//查询到对应的目录catalog
				Catalog catalog = itemContent.getContentsCatalog();
				modelMap.addAttribute("catalogTem",catalog);
				
				//返回所有的item
				List<Item> itemList = itemService.getItems();
				modelMap.addAttribute("itemList", itemList);
				
				//返回所有的目录
			    List<Catalog> catalogList = catalogService.getCatalogs();
			    modelMap.addAttribute("catalogList", catalogList);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return page;   
	    } 
	    /**
	     * 
	     * 依据catalog目录的ID，查询获得所有的数据
	     * @param modelMap
	     * @return
	     */
	    @RequestMapping(value="/manage/data/catalog/itemContent/{catalogID}/{startIndex}/{count}",method=RequestMethod.GET)
	    public String getListDataByColumn(ModelMap modelMap,@PathVariable("catalogID") String catalogID,
	    		@PathVariable("startIndex") String startIndex,@PathVariable("count") String count){
	    	
	    	PagingTools<ItemContent> listTools = null;
	    	String msg = "/manage/data/sxtw/content/data_list_itemContent.jsp";
				try {
					
					Catalog catalog = catalogService.getCatalogbyId(Integer.valueOf(catalogID));
					//返回当前的目录
					modelMap.addAttribute("catalogTem", catalog);
					//返回当前目录下的内容列表
					listTools = itemContentService.getItemContentsByCatalog(catalog, Integer.valueOf(startIndex), Integer.valueOf(count));
					modelMap.addAttribute("pagingTool", listTools);
					//查询到对应的item
					Item item = catalog.getCatalogItem();
					modelMap.addAttribute("itemTem",item);
					
					//返回所有的item
					List<Item> itemList = itemService.getItems();
					modelMap.addAttribute("itemList", itemList);
					
					//返回所有的目录
				    List<Catalog> catalogList = catalogService.getCatalogs();
				    modelMap.addAttribute("catalogList", catalogList);
					//返回控制审核的字段
					modelMap.addAttribute("approveState", "0");
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	    	return msg;
	    }
	    
	    /**
	     * 
	     * 审核列表，依据catalog目录的ID，查询获得所有的数据
	     * @param modelMap
	     * @return
	     */
	    @RequestMapping(value="/check/manage/data/catalog/itemContent/{catalogID}/{startIndex}/{count}",method=RequestMethod.GET)
	    public String loadApproveListByColumn(ModelMap modelMap,@PathVariable("catalogID") String catalogID,
	    		@PathVariable("startIndex") String startIndex,@PathVariable("count") String count){
	    	
	    	PagingTools<ItemContent> listTools = null;
	    	String msg = "/manage/data/sxtw/content/data_list_itemContent.jsp";
				try {
					
					Catalog catalog = catalogService.getCatalogbyId(Integer.valueOf(catalogID));
					//返回当前的目录
					modelMap.addAttribute("catalogTem", catalog);
					//返回当前目录下的内容列表
					listTools = itemContentService.getItemContentsByCatalog(catalog, Integer.valueOf(startIndex), Integer.valueOf(count));
					modelMap.addAttribute("pagingTool", listTools);
					//查询到对应的item
					Item item = catalog.getCatalogItem();
					modelMap.addAttribute("itemTem",item);
					
					//返回所有的item
					List<Item> itemList = itemService.getItems();
					modelMap.addAttribute("itemList", itemList);
					
					//返回所有的目录
				    List<Catalog> catalogList = catalogService.getCatalogs();
				    modelMap.addAttribute("catalogList", catalogList);
					//返回控制审核的字段
					modelMap.addAttribute("approveState", "1");
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	    	return msg;
	    }
	    /**
	     * 依据item条目的ID，查询获得内容的数据
	     * @param modelMap
	     * @return
	     */
	    @RequestMapping(value="/manage/data/item/itemContent/{itemID}/{startIndex}/{count}",method=RequestMethod.GET)
	    public String getContentsDataByItem(ModelMap modelMap,@PathVariable("itemID") String itemID,
	    		@PathVariable("startIndex") String startIndex,@PathVariable("count") String count){
	    	
	    	PagingTools<ItemContent> listTools = null;
	    	String msg = "/manage/data/sxtw/content/data_list_itemContent.jsp";
				try {
					//查询到具体的item
					Item item = itemService.getItembyId(Long.valueOf(itemID));
					modelMap.addAttribute("itemTem", item);
					//依据item查询对应的内容
				   listTools = itemContentService.getItemContentsByItem(item,Integer.valueOf(startIndex), Integer.valueOf(count));				
				   modelMap.addAttribute("pagingTool", listTools);
					
					//返回所有的item
					List<Item> itemList = itemService.getItems();
					modelMap.addAttribute("itemList", itemList);
					
					//返回所有的目录
				    List<Catalog> catalogList = catalogService.getCatalogs();
				    modelMap.addAttribute("catalogList", catalogList);
					//返回控制审核的字段
					modelMap.addAttribute("approveState", "0");
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	    	return msg;
	    }
	    /**
	     * 进入到审核列表，依据item条目的ID，查询获得内容的数据
	     * @param modelMap
	     * @return
	     */
	    @RequestMapping(value="/check/manage/data/item/itemContent/{itemID}/{startIndex}/{count}",method=RequestMethod.GET)
	    public String loadApproveListByItem(ModelMap modelMap,@PathVariable("itemID") String itemID,
	    		@PathVariable("startIndex") String startIndex,@PathVariable("count") String count){
	    	
	    	PagingTools<ItemContent> listTools = null;
	    	String msg = "/manage/data/sxtw/content/data_list_itemContent.jsp";
				try {
					//查询到具体的item
					Item item = itemService.getItembyId(Long.valueOf(itemID));
					modelMap.addAttribute("itemTem", item);
					//依据item查询对应的内容
				   listTools = itemContentService.getItemContentsByItem(item,Integer.valueOf(startIndex), Integer.valueOf(count));				
				   modelMap.addAttribute("pagingTool", listTools);
					
					//返回所有的item
					List<Item> itemList = itemService.getItems();
					modelMap.addAttribute("itemList", itemList);
					
					//返回所有的目录
				    List<Catalog> catalogList = catalogService.getCatalogs();
				    modelMap.addAttribute("catalogList", catalogList);
					//返回控制审核的字段
					modelMap.addAttribute("approveState", "1");
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	    	return msg;
	    }
	    /**
	     * 查询获得所有的数据
	     * @param modelMap
	     * @return
	     */
	    @RequestMapping(value="/manage/data/itemContent/queryall",method=RequestMethod.GET)
		public String getList(ModelMap modelMap){
			List<Item> list = new ArrayList<Item>();
			try {
			   list = itemService.getItems();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			modelMap.put("datalist", list);
			return "manage/data/data_list_item.jsp";
		}
	    
	    /**
	     * 检查实体是否重复
	     * @param modelMap
	     * @param item
	     * @return
	     */
	  
	    public String checkBMAndName(Item item) {
	    	List<Item> list = new ArrayList<Item>();
				 String msgString = "0";
				try {
					list = itemService.checkRepeat(item);
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
