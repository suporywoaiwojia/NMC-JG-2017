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
import com.menksoft.cms.manage.webContent.entity.Country;
import com.menksoft.cms.manage.webContent.service.ColumnsService;
import com.menksoft.cms.manage.webContent.service.CountryService;



@Controller
public class CountryAction {
	 @Resource
	 private ItemService itemService;
	 @Resource
	 private  CheckRepeatService crService;
	 
	 @Resource
	 private ColumnsService columnsService;
	 
	 @Resource
	 private CountryService countryService;
	 
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
	    @RequestMapping(value="/manage/webContent/country/save",method=RequestMethod.POST)
	   	public @ResponseBody String save(ModelMap modelMap,Country country){
	    	String msg = "0";
	    	boolean repeat = false;
	    	try {
	    		repeat = checkRepeatName(country).equals("1")? false:true;
	    		if(repeat){
    			countryService.save(country);
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
	    @RequestMapping(value="/manage/webContent/country/update",method=RequestMethod.POST)
	    @ResponseBody
	   	public String update(ModelMap modelMap,Country country){
	   		String msg="0";
	    	boolean repeat = false;
	    	try {
	    		repeat = checkRepeatName(country).equals("1")? false:true;
	    		if(repeat){
	    			countryService.update(country);
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
	    @RequestMapping(value="/manage/webContent/country/delete/{id}",method=RequestMethod.GET)
	   	public @ResponseBody String delete(ModelMap modelMap,@PathVariable("id") String id){
	    	String msg= "0";
	   		try {
	  		  countryService.delete(id);
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
	    @RequestMapping(value="/manage/webContent/country/queryall",method=RequestMethod.GET)
		public String getList(ModelMap modelMap){
			List<Country> list = new ArrayList<Country>();
			try {
			   list = countryService.getCountrys();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			modelMap.put("datalist", list);
			return "manage/basicData/data_list_country.jsp";
		}
	    
	    /**
	     * 检查实体是否重复
	     * @param modelMap
	     * @param country
	     * @return
	     */
	  
	    public String checkRepeatName(Country country) {
	    	List<Country> list = new ArrayList<Country>();
				 String msgString = "0";
				try {
					list = countryService.checkRepeat(country);
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
