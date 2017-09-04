/**
 * 项目名称: 草原文化创意资源平台
 * 文件名    : SensitivewordsAction.java
 * 描述        : 敏感词管理
 * 作者        : 景宏
 * 日期        : 上午11:02:42
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       上午11:02:42  景宏
 */
package com.menksoft.cms.manage.website.action;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.website.entity.Sensitivewords;
import com.menksoft.cms.manage.website.service.SensitivewordsService;
import com.menksoft.util.Const;


/**
 * 敏感词管理
 * @author 
 * 敏感词控制器类，负责广告各类业务处理的控制转发。
 */
@Controller
public class SensitivewordsAction {
	private Logger log = Logger.getLogger(this.getClass());
	@javax.annotation.Resource
	SensitivewordsService sensitivewordsService;
	
	/**
	 * 列表查询
	 * @return
	 */
	@RequestMapping(value="/manage/website/sensitivewords/list/{startIndex}", method=RequestMethod.GET)
	public String getAllSensitivewords(ModelMap modelMap,
			@PathVariable("startIndex") Integer startIndex, Sensitivewords sensitivewords) {		 
		PagingTools<Sensitivewords> pagingTools=null;
		
		try {
			pagingTools=sensitivewordsService.getAllSensitivewords(sensitivewords,startIndex, PagingTools.PAGER_ECORD_DEFAULT);
			
		} catch (Throwable e) {
			log.error("敏感词"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			return "manage/errer.html";
		}
		modelMap.put("pagingTools", pagingTools);
		
		return "manage/website/sensitivewords/sensitivewords_list.jsp";
	}
	
	/**
	 * 新建保存
	 * @param sensitivewords	保存对象
	 * @param modelMap	页面赋值对象
	 * @return	返回页面
	 */
	@RequestMapping(value="/manage/website/sensitivewords/new", method=RequestMethod.POST)
	@ResponseBody
	public String saveNewPage(Sensitivewords sensitivewords,ModelMap modelMap) {
		String msgString ="0";
		try{
			//保存操作
			sensitivewordsService.saveSensitivewords(sensitivewords);
			msgString="1";
		}catch (Throwable e) {
			//报错失败，并跳转至错误页面
			log.error("敏感词"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
			modelMap.put("message", e.getMessage());
			modelMap.put("sensitivewords", sensitivewords);
			//return "manage/website/sensitivewords/sensitivewords_new.jsp";
		}
		modelMap.put("message", Const.MESSAGE_SUCCESS_SAVE);
		System.out.println(msgString+"++++++++++++++++++++++++++++++++++");
		return msgString;
	}
	
	/**
	 * 新建页面跳转
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/manage/website/sensitivewords/newpage", method=RequestMethod.GET)
	public String goToNewPage(ModelMap modelMap) {
		return "manage/website/sensitivewords/sensitivewords_new.jsp";
	}
	
	/**
	 * 编辑跳转
	 * @param modelMap	页面赋值
	 * @return
	 */
	@RequestMapping(value="/manage/website/sensitivewords/editpage/{ids}", method=RequestMethod.GET)
	public String goToEditPage(ModelMap modelMap,@PathVariable("ids") long ids) {
		Sensitivewords sensitivewords=null;
		try{
			//保存操作
			sensitivewords=sensitivewordsService.getSensitivewordsById(ids);
		}catch (Throwable e) {
			//报错失败，并跳转至错误页面
			log.error("敏感词"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			//return "manage/errer.html";
		}
		modelMap.put("sensitivewords", sensitivewords);
		return "manage/website/sensitivewords/sensitivewords_new.jsp";
	}
	
	/**
	 * 编辑保存
	 * @param modelMap	
	 * @param sensitivewords	保存对象
	 * @return	列表页
	 */
	@RequestMapping(value="/manage/website/sensitivewords/update", method=RequestMethod.POST)
	@ResponseBody
	public String updateSensitivewords(ModelMap modelMap,Sensitivewords sensitivewords) {
		String msgString ="0";
		try{
			//保存操作
			sensitivewordsService.updateSensitivewords(sensitivewords);
			msgString="1";
		}catch (Throwable e) {
			//报错失败
			log.error("敏感词"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
			modelMap.put("message", e.getMessage());
			modelMap.put("sensitivewords", sensitivewords);
			//return "manage/website/sensitivewords/sensitivewords_new.jsp";
		}
		modelMap.put("message", Const.MESSAGE_SUCCESS_SAVE);
		modelMap.put("msg", msgString);
		
		return msgString;
	}
	
	/**
	 * 删除操作
	 * @param modelMap	
	 * @return	列表页
	 */
	@RequestMapping(value="/manage/website/sensitivewords/delet/{ids}", method=RequestMethod.GET)
	public String deleteSensitivewords(ModelMap modelMap,@PathVariable("ids") String ids) {
		try{			
			//删除操作
			sensitivewordsService.deleteSensitivewords(ids);
			modelMap.put("message", "删除成功");
		}catch (Throwable e) {
			//报错失败，并跳转至错误页面
			log.error("敏感词信息删除失败", e);
			modelMap.put("message", "删除失败,"+Const.MESSAGE_FAIL_PRIFX+e.getMessage());			
		}
		Sensitivewords sensitivewords = new Sensitivewords();
		return getAllSensitivewords(modelMap,0,sensitivewords);
	}
	@RequestMapping(value="/website/sensitivewords/back", method=RequestMethod.POST)
	public String goBackPage(ModelMap modelMap){
		Sensitivewords sensitivewords = new Sensitivewords();
		return getAllSensitivewords(modelMap,0,sensitivewords);
	}
}

