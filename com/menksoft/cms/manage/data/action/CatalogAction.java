package com.menksoft.cms.manage.data.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.data.entity.Catalog;
import com.menksoft.cms.manage.data.entity.Item;
import com.menksoft.cms.manage.data.entity.ItemContent;
import com.menksoft.cms.manage.data.service.CatalogService;
import com.menksoft.cms.manage.data.service.CheckRepeatService;
import com.menksoft.cms.manage.data.service.ItemContentService;
import com.menksoft.cms.manage.data.service.ItemService;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.entity.ContentFile;
import com.menksoft.cms.manage.webContent.service.ColumnsService;
import com.menksoft.cms.manage.webContent.service.ContentFileService;
import com.menksoft.cms.manage.webContent.util.SetContentFile;



@Controller
public class CatalogAction {
	 @Resource
	 private ItemService itemService;
	 @Resource
	 private  CheckRepeatService crService;
	 
	 @Resource
	 private ColumnsService columnsService;
	 
	 @Resource
	 private CatalogService catalogService;
	 
	 @Resource
	 private ContentFileService contentFileService;
	 
	 @Resource
	 private ItemContentService itemContentService;
	 
	 @InitBinder
	 protected void initBinder(WebDataBinder binder) {
	     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	 }
	    /**
	     * 依据item的ID，返回新建页面
	     * @param modelMap
	     * @param 
	     * @return
	     */
	 @RequestMapping(value="/manage/data/catalog/addNew/{itemId}",method=RequestMethod.GET)
	   	public String addNew(ModelMap modelMap,@PathVariable("itemId") String itemId){
	   		String msgString="/manage/data/sxtw/catalog/data_catalog_new.jsp";
	   		//目录需要属性
	   	  try {
	   		  //查询到当前所属的资料，返回资料对象
			Item item = itemService.getItembyId(Long.valueOf(itemId));
			//取得所属的栏目，将栏目存储附件的地址返回
			Columns columns = item.getParent();
			modelMap.addAttribute("item", item);
			modelMap.addAttribute("columns", columns);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   		return msgString;
	   		
	   	}
	    /**
	     *依据提交的条件，查询数据
	     * @param modelMap
	     * @param 
	     * @return
	     */
	  @RequestMapping(value="/manage/data/catalog/query/{startIndex}",method=RequestMethod.POST)
	   	public String queryList(ModelMap modelMap,@PathVariable("startIndex") Integer startIndex,
	   			Catalog catalog,HttpServletRequest request){
		
		  PagingTools<Catalog> listTools = null;
		try {
			//查询到所有符合条件的目录
			Integer count = 20;
			listTools=catalogService.getCatalogsByCatalog(catalog, Integer.valueOf(startIndex), count);
			modelMap.addAttribute("pagingTool", listTools);
			//查询到对应的item
			Item item = catalog.getCatalogItem();
			modelMap.addAttribute("itemTem", item);
			
			//返回所有的item
			List<Item> itemList = itemService.getItems();
			modelMap.addAttribute("itemList", itemList);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	            
		  return "/manage/data/sxtw/catalog/data_list_catalog.jsp";
	    }
	 
	    /**
	     * 
	     * 保存提交的数据，返回到列表页
	     * @param modelMap
	     * @param item ajax提交的json数据，转换为对象
	     * @return
	     */
	    @RequestMapping(value="/manage/data/catalog/save",method=RequestMethod.POST)
	   	public @ResponseBody String save(ModelMap modelMap,Catalog catalog,HttpServletRequest request){
	    	String msg = "0";
	    	List<ContentFile> cf_list=new ArrayList<ContentFile>();//附件
	    	try {
	    		//System.out.println(item.getPublishDate()+"+++++++++++++++");
    			catalogService.save(catalog);
    			//获取并保存附件，类型为目录，参数为2
    			cf_list=SetContentFile.getFileList(request,catalog.getId(),"2");
    			contentFileService.save(cf_list, catalog.getId(),"2");
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
	    @RequestMapping(value="/manage/data/catalog/edite/{catalogId}",method=RequestMethod.GET)
	    public String getItemById(ModelMap modelMap,@PathVariable("catalogId") String catalogId){
	    	String msg="/manage/data/sxtw/catalog/data_catalog_edit.jsp";
	    
	    	try {
	    		//依据ID查询到对应的目录对象
	    		Catalog catalog = catalogService.getCatalogbyId(Long.valueOf(catalogId));
	    		modelMap.addAttribute("catalog", catalog);
	    		//查询到对应的资料item
	    		Item item = catalog.getCatalogItem();
	    		modelMap.addAttribute("item", item);
	    		//查询到对应的所有附件内容
	    		List<ContentFile> listContents = catalog.getContentFiles();
	    		modelMap.addAttribute("listContents", listContents);
			    //对应的栏目
				Columns columns = item.getParent();
				modelMap.addAttribute("columns", columns);
				//返回控制内容或审核的字段
				modelMap.addAttribute("approveState", "0");
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  
	    	return msg;
	    	
	    }
	    /**
	     * 进入审核页面，依据数据ID跳转到编辑页面
	     * @param modelMap
	     * @param  item
	     * @return
	     */
	    @RequestMapping(value="/check/manage/data/catalog/edite/{catalogId}",method=RequestMethod.GET)
	    public String loadApproveById(ModelMap modelMap,@PathVariable("catalogId") String catalogId){
	    	String msg="/manage/data/sxtw/catalog/data_catalog_edit.jsp";
	    
	    	try {
	    		//依据ID查询到对应的目录对象
	    		Catalog catalog = catalogService.getCatalogbyId(Long.valueOf(catalogId));
	    		modelMap.addAttribute("catalog", catalog);
	    		//查询到对应的资料item
	    		Item item = catalog.getCatalogItem();
	    		modelMap.addAttribute("item", item);
	    		//查询到对应的所有附件内容
	    		List<ContentFile> listContents = catalog.getContentFiles();
	    		modelMap.addAttribute("listContents", listContents);
			    //对应的栏目
				Columns columns = item.getParent();
				modelMap.addAttribute("columns", columns);
				//返回控制内容或审核的字段
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
	    @RequestMapping(value="/manage/data/catalog/update",method=RequestMethod.POST)
	    @ResponseBody
	   	public String update(ModelMap modelMap,Catalog catalog,HttpServletRequest request){
	   		String msg="0";
	   		List<ContentFile> cf_list=new ArrayList<ContentFile>();//附件
	   		try {
	   		//  b=crService.getRepeatListForUpdate(Item.class,item.getBm(), item.getName(), "r_data_hmstyle",item.getId());
	    		
	    			catalogService.update(catalog);
	    			//获取并保存附件,类型选2，对应内容
	    			cf_list=SetContentFile.getFileList(request,catalog.getId(),"2");
	    			contentFileService.save(cf_list, catalog.getId(),"2");
	    			msg="1";
	   		    
	   		} catch (Throwable e) {
	   			e.printStackTrace();
	   		}
	   		return msg;
	   	}
		
	    /**
	     * 删除数据
	     * @param modelMap
	     * @param id,此时的id可以是一个也可以是一串类似"1,2,3"，这种字符串，用于批量删除
	     * @return
	     */
	    @RequestMapping(value="/manage/data/catalog/delete/{id}",method=RequestMethod.GET)
	   	public @ResponseBody String delete(ModelMap modelMap,@PathVariable("id") String id){
	    	String msg= "0";
	   		try {
	   	    //删除下级内容，最后删除实体,为批量做准备，需要循环遍历到所有的catalog
   			String[] list =  id.split(",");
			Integer length = list.length;
			if (length>0) {
				for (int i = 0; i < length; i++) {
					Catalog catalog = catalogService.getCatalogbyId(Long.valueOf(list[i]));	   		
			   		//删除关联的内容实体itemcontent
			   		List<ItemContent> itemContents = catalog.getCatalogContent();
			   		String idString ="";
			   		Integer count = itemContents.size();
					for (int j = 0; j < count; j++) {
						if (i==count-1) {
							idString+=itemContents.get(i).getId();
						}else{
							idString+=itemContents.get(i).getId()+",";
						}
					}
					//防止为空
					if (idString!=null&&idString!="") {
						 itemContentService.delete(idString);
					}
				 }
			}
        	 //删除实体
			if (id!=null&&id!="") {
   	  		  catalogService.delete(id);
			}
   	  		  msg="1";
		 
            
	   		} catch (Throwable e) {
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   		}
	   		return msg;
	   	}
	    /**
	     * 依据item的ID，查询获得所有的数据
	     * @param modelMap
	     * @return
	     */
	    @RequestMapping(value="/manage/data/catalog/{itemID}/{startIndex}/{count}",method=RequestMethod.GET)
	    public String getListDataByColumn(ModelMap modelMap,@PathVariable("itemID") String itemID,
	    		@PathVariable("startIndex") String startIndex,@PathVariable("count") String count){
	    	
	    	PagingTools<Catalog> listTools = null;
	    	String msg = "/manage/data/sxtw/catalog/data_list_catalog.jsp";
				try {
					Item item =itemService.getItembyId(Long.valueOf(itemID));
					listTools = catalogService.getCatalogsByItem(item,Integer.valueOf(startIndex), Integer.valueOf(count));
					modelMap.addAttribute("pagingTool", listTools);
					//将当前item返回
					modelMap.addAttribute("itemTem", item);
					//返回所有的item
					List<Item> itemList = itemService.getItems();
					modelMap.addAttribute("itemList", itemList);
					//返回控制内容或审核的字段
					modelMap.addAttribute("approveState", "0");
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	    	return msg;
	    }
	    /**
	     * 审核页面，依据item的ID，查询获得所有的数据
	     * @param modelMap
	     * @return
	     */
	    @RequestMapping(value="/check/manage/data/catalog/{itemID}/{startIndex}/{count}",method=RequestMethod.GET)
	    public String loadApproveByColumn(ModelMap modelMap,@PathVariable("itemID") String itemID,
	    		@PathVariable("startIndex") String startIndex,@PathVariable("count") String count){
	    	
	    	PagingTools<Catalog> listTools = null;
	    	String msg = "/manage/data/sxtw/catalog/data_list_catalog.jsp";
				try {
					Item item =itemService.getItembyId(Long.valueOf(itemID));
					listTools = catalogService.getCatalogsByItem(item,Integer.valueOf(startIndex), Integer.valueOf(count));
					modelMap.addAttribute("pagingTool", listTools);
					//将当前item返回
					modelMap.addAttribute("itemTem", item);
					//返回所有的item
					List<Item> itemList = itemService.getItems();
					modelMap.addAttribute("itemList", itemList);
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
	    @RequestMapping(value="/manage/data/catalog/queryall",method=RequestMethod.GET)
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
