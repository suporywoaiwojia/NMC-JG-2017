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
public class ItemAction {
	 @Resource
	 private ItemService itemService;
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
	 private ItemContentService itemContentService;
	 @Resource
	 private CatalogService catalogService;
	 
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
	  @RequestMapping(value="/manage/data/item/addNew/{columnId}",method=RequestMethod.GET)
	   	public String addNew(ModelMap modelMap,@PathVariable("columnId") String columnId){
		  //查询所有的column，返回到页面提供选择
		System.out.println(columnId+"==============================");
		try {
			Columns column = columnsService.getColumnsById(Long.valueOf(columnId));
			modelMap.addAttribute("column", column);
		
			//查询到所有的数据类型，返回所有类型，选中与栏目对应的数据类型
		     List<DataType> typeList = dataTypeService.getDataTypes();
		     modelMap.addAttribute("typeList", typeList);
			  
			//查询到所有的语言，返回
			 List<Language> languageList = languageService.getLanguages();
			 modelMap.addAttribute("languageList", languageList);
			//查询到所有的栏目
			 List<Columns>  columnList = columnsService.getAllColumn();
			 modelMap.addAttribute("columnList", columnList);
			 //查询到所有国籍
			 List<Country> countryList = countryService.getCountrys();
			 modelMap.addAttribute("countryList", countryList);
			 //查询所有的出版社
			 List<PublishHouse> pubHouseList = publishHouseService.getPublishHouses();
			 modelMap.addAttribute("pubHouseList", pubHouseList);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	            
		  return "/manage/data/sxtw/list/data_list_new.jsp";
	    }
	 
	    /**
	     * 
	     * 保存提交的数据，返回到列表页
	     * @param modelMap
	     * @param item ajax提交的json数据，转换为对象
	     * @return
	     */
	    @RequestMapping(value="/manage/data/item/save",method=RequestMethod.POST)
	   	public @ResponseBody String save(ModelMap modelMap,Item item,HttpServletRequest request){
	    	String msg = "0";
	    	List<ContentFile> cf_list=new ArrayList<ContentFile>();//附件
	    	try {
    			itemService.save(item);
    			//获取并保存附件，类型为条目，参数为1
    			cf_list=SetContentFile.getFileList(request,item.getId(),"1");
    			contentFileService.save(cf_list, item.getId(),"1");
    			msg="1";
	    		
	   		} catch (Throwable e) {
	   			e.printStackTrace();
	   			
	   		}
	   		return msg;
	   	}
	    /**
	     * 依据数据ID跳转到审核页面
	     * @param modelMap
	     * @param  item
	     * @return
	     */
	    @RequestMapping(value="/check/manage/data/item/edite/{Id}",method=RequestMethod.GET)
	    public String loadApprovePage(ModelMap modelMap,@PathVariable("Id") String Id){
	    	String msg="/manage/data/sxtw/list/data_list_edit.jsp";
	    	
	    	try {
	    		//查找到当前item
	    	    Item item = itemService.getItembyId(Long.valueOf(Id));
	    	    modelMap.addAttribute("item", item);
	    	    //查询到当前item对应的附件
	    	    List<ContentFile> listContents = item.getContentFiles();
	    	    modelMap.addAttribute("listContents", listContents);
	    	    
	    	    //查找到对应的当前栏目
				Columns column = item.getParent();
				modelMap.addAttribute("column", column);
			
				//查询到所有的数据类型，返回
			     List<DataType> typeList = dataTypeService.getDataTypes();
			     modelMap.addAttribute("typeList", typeList);
				  
				//查询到所有的语言，返回
				 List<Language> languageList = languageService.getLanguages();
				 modelMap.addAttribute("languageList", languageList);
				//查询到所有的栏目
				 List<Columns>  columnList = columnsService.getAllColumn();
				 modelMap.addAttribute("columnList", columnList);
				 //查询到所有国籍
				 List<Country> countryList = countryService.getCountrys();
				 modelMap.addAttribute("countryList", countryList);
				 //查询所有的出版社
				 List<PublishHouse> pubHouseList = publishHouseService.getPublishHouses();
				 modelMap.addAttribute("pubHouseList", pubHouseList);
				 
				 //用于判断是内容页面还是审核
				 modelMap.addAttribute("approveState", "1");
			} catch (Throwable e) {
				// TODO Auto-generated catch block
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
	    @RequestMapping(value="/manage/data/item/edite/{Id}",method=RequestMethod.GET)
	    public String getItemById(ModelMap modelMap,@PathVariable("Id") String Id){
	    	String msg="/manage/data/sxtw/list/data_list_edit.jsp";
	    	
	    	try {
	    		//查找到当前item
	    	    Item item = itemService.getItembyId(Long.valueOf(Id));
	    	    modelMap.addAttribute("item", item);
	    	    //查询到当前item对应的附件
	    	    List<ContentFile> listContents = item.getContentFiles();
	    	    modelMap.addAttribute("listContents", listContents);
	    	    
	    	    //查找到对应的当前栏目
				Columns column = item.getParent();
				modelMap.addAttribute("column", column);
			
				//查询到所有的数据类型，返回
			     List<DataType> typeList = dataTypeService.getDataTypes();
			     modelMap.addAttribute("typeList", typeList);
				  
				//查询到所有的语言，返回
				 List<Language> languageList = languageService.getLanguages();
				 modelMap.addAttribute("languageList", languageList);
				//查询到所有的栏目
				 List<Columns>  columnList = columnsService.getAllColumn();
				 modelMap.addAttribute("columnList", columnList);
				 //查询到所有国籍
				 List<Country> countryList = countryService.getCountrys();
				 modelMap.addAttribute("countryList", countryList);
				 //查询所有的出版社
				 List<PublishHouse> pubHouseList = publishHouseService.getPublishHouses();
				 modelMap.addAttribute("pubHouseList", pubHouseList);
				 
				 //用于判断是内容页面还是审核
				 modelMap.addAttribute("approveState", "0");
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
	    @RequestMapping(value="/manage/data/item/update",method=RequestMethod.POST)
	    @ResponseBody
	   	public String update(ModelMap modelMap,Item item,HttpServletRequest request){
	   		String msg="0";
	   		
	   		List<ContentFile> cf_list=new ArrayList<ContentFile>();//附件
	   		try {
	   		//  b=crService.getRepeatListForUpdate(Item.class,item.getBm(), item.getName(), "r_data_hmstyle",item.getId());
	    		
	    			itemService.update(item);
	    			//获取并保存附件，类型为条目，参数为1
	    			cf_list=SetContentFile.getFileList(request,item.getId(),"1");
	    			contentFileService.save(cf_list, item.getId(),"1");
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
	    @RequestMapping(value="/manage/data/item/delete/{id}",method=RequestMethod.GET)
	   	public @ResponseBody String delete(ModelMap modelMap,@PathVariable("id") String id){
	    	String msg= "0";
	    	
	   		try {
	   		//先删除，itemcontent，然后catalog，然后item
	   			String[] list =  id.split(",");
				Integer length = list.length;
				if (length>0) {
					for (int i = 0; i < length; i++) {
						Item item = itemService.getItembyId(Long.valueOf(list[i]));	
						//删除与item关联的catalog
						List<Catalog> catalogs = item.getCatalog();
						Integer catalogCount = catalogs.size();
						String catalogIDString = "";
						if (catalogCount>0) {
							//查找到与catalog对应的itemcontent
							for (int j = 0; j < catalogCount; j++) {
								List<ItemContent> itemContents = catalogs.get(j).getCatalogContent();
								Integer itemCCount =  itemContents.size();
								String idString ="";
								if (itemCCount>0) {
									for (int k = 0; k < itemCCount; k++) {
										List<ContentFile> contentFiles = itemContents.get(k).getContentFiles();
										//删除与itemcontent 对应的contentfiles
										contentFileService.deleteFiles(contentFiles);
										if (k==itemCCount-1) {
											idString+=itemContents.get(k).getId();
										}else{
											idString+=itemContents.get(k).getId()+",";
										}
									}
									
								}
								//删除与catalog对应的itemcontent
								if (idString!=null&&idString!="") {
									itemContentService.delete(idString);
								}
								if (j==catalogCount-1) {
									catalogIDString+=catalogs.get(j).getId();
								}else{
									catalogIDString+=catalogs.get(j).getId()+",";
								}
							}
							
						}
						//删除与item关联的catalog
						if (catalogIDString!=null&&catalogIDString!="") {
						catalogService.delete(catalogIDString);
						}
					 }
				}
				//删除item
				if (id!=null&&id!=""){
	  		    itemService.delete(id);
				}
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
      @RequestMapping(value="/manage/data/item/query/{startIndex}",method=RequestMethod.POST)
	    
	    public String getListDataByConditions(ModelMap modelMap,
	    		@PathVariable("startIndex") Integer startIndex,
	    		Item item,HttpServletRequest request){
    	  PagingTools<Item> pagingTools = null;
    	  String pageString = "";
    	try {
    		//查询到所有符合条件的实体
    		Integer count = 20;
    		pagingTools = itemService.queryItems(item, Integer.valueOf(startIndex), count);
    		modelMap.addAttribute("pagingTool", pagingTools);
    		
			//用于判断是审核页面还是内容页面
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
			  Columns columns = columnsService.getColumnsById(item.getParent().getId());
			
			//判断是返回到哪个页面  
			  modelMap.addAttribute("column", columns);
			  String name = columns.getName();
			  if(name.equals("文本")){
				  pageString="manage/data/sxtw/list/data_list_text.jsp";
			  }else if(name.equals("视频")){
				  pageString="manage/data/sxtw/list/data_list_video.jsp";
			  }else if(name.equals("音频")){
				  pageString="manage/data/sxtw/list/data_list_audio.jsp";
			  }else if(name.equals("图像")){
				  pageString="manage/data/sxtw/list/data_list_video.jsp";
			  }
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
    	  return pageString;
    	  
      }
      /**
	     * 进入到审核列表页面
	     * @param modelMap
	     * @param  content
	     * @return
	     */
	    @RequestMapping(value="/check/manage/data/item/{columnID}/{startIndex}/{count}",method=RequestMethod.GET)
	    public String loadToApprovePage(ModelMap modelMap,@PathVariable("columnID") String columnID,
	    		@PathVariable("startIndex") String startIndex,@PathVariable("count") String count){
	    	PagingTools<Item> listTools = null;
	    	String path = "";
				try {
					//查询到数据库中存储的返回的页面的地址path
					Columns columns = columnsService.getColumnsById(Long.valueOf(columnID));
					
					path=columns.getColumnPath();
					//查询到对应页数的数据
					listTools = itemService.getItemsByColumn(columns, Integer.valueOf(startIndex), Integer.valueOf(count));
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
	     * 依据栏目的ID，查询获得所有的数据
	     * @param modelMap
	     * @return
	     */
	    @RequestMapping(value="/manage/data/item/{columnID}/{startIndex}/{count}",method=RequestMethod.GET)
	    public String getListDataByColumn(ModelMap modelMap,@PathVariable("columnID") String columnID,
	    		@PathVariable("startIndex") String startIndex,@PathVariable("count") String count){
	    	
	    	PagingTools<Item> listTools = null;
	    	String path = "";
				try {
					//查询到数据库中存储的返回的页面的地址path
					Columns columns = columnsService.getColumnsById(Long.valueOf(columnID));
					
					path=columns.getColumnPath();
					//查询到对应页数的数据
					listTools = itemService.getItemsByColumn(columns, Integer.valueOf(startIndex), Integer.valueOf(count));
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
	     * 查询获得所有的数据
	     * @param modelMap
	     * @return
	     */
	    @RequestMapping(value="/manage/data/item/queryall",method=RequestMethod.GET)
		public String getList(ModelMap modelMap){
			List<Item> list = new ArrayList<Item>();
			try {
			   list = itemService.getItems();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			modelMap.put("datalist", list);
			return "manage/data/sxtw/data_list_item.jsp";
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
