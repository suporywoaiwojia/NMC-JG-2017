package com.menksoft.cms.manage.webContent.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menksoft.cms.manage.data.service.CheckRepeatService;
import com.menksoft.cms.manage.data.service.ItemService;
import com.menksoft.cms.manage.webContent.entity.Language;
import com.menksoft.cms.manage.webContent.service.ColumnsService;
import com.menksoft.cms.manage.webContent.service.LanguageService;



@Controller
public class LanguageAction {
	 @Resource
	 private ItemService itemService;
	 @Resource
	 private  CheckRepeatService crService;
	 
	 @Resource
	 private ColumnsService columnsService;
	 
	 @Resource
	 private LanguageService languageService;
	 
	 @InitBinder
	 protected void initBinder(WebDataBinder binder) {
	     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	 }
	    /**
	     * 
	     * 保存提交的数据，返回到列表页
	     * @param modelMap
	     * @param item ajax提交的json数据，转换为对象
	     * @return
	     */
	    @RequestMapping(value="/manage/webContent/language/save",method=RequestMethod.POST)
	   	public @ResponseBody String save(ModelMap modelMap,Language language){
	    	String msg = "0";
	    	boolean repeat = false;
	    	try {
	    		repeat = checkRepeatName(language).equals("1")? false:true;
	    		if(repeat){
    			languageService.save(language);
    			msg="1";
	    		}else{
	    			msg="2";
	    		}
	   		} catch (Throwable e) {
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
	    @RequestMapping(value="/manage/webContent/language/update",method=RequestMethod.POST)
	    @ResponseBody
	   	public String update(ModelMap modelMap,Language language){
	   		String msg="0";
	    	boolean repeat = false;
	    	try {
	    		repeat = checkRepeatName(language).equals("1")? false:true;
	    		if(repeat){
	    			languageService.update(language);
	    			msg="1";
	    		}else{
	    			msg="2";
	    		}
	   		    
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
	    @RequestMapping(value="/manage/webContent/language/delete/{id}",method=RequestMethod.GET)
	   	public @ResponseBody String delete(ModelMap modelMap,@PathVariable("id") String id){
	    	String msg= "0";
	   		try {
	  		  languageService.delete(id);
	  		  msg="1";
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
	    @RequestMapping(value="/manage/webContent/language/queryall",method=RequestMethod.GET)
		public String getList(ModelMap modelMap){
			List<Language> list = new ArrayList<Language>();
			try {
			   list = languageService.getLanguages();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			modelMap.put("datalist", list);
			return "manage/basicData/data_list_language.jsp";
		}
	    
	    /**
	     * 检查实体是否重复
	     * @param modelMap
	     * @param language
	     * @return
	     */
	  
	    public String checkRepeatName(Language language) {
	    	List<Language> list = new ArrayList<Language>();
				 String msgString = "0";
				try {
					list = languageService.checkRepeat(language);
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
