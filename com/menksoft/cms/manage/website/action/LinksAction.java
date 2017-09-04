/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : LinksAction.java
 * 描述        : 会员管理
 * 作者        : 李彦佑
 * 日期        : 2012-11-13下午2:13:18
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1   2012-11-13  李彦佑
 */
package com.menksoft.cms.manage.website.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.website.entity.Links;
import com.menksoft.cms.manage.website.service.LinksService;
import com.menksoft.util.Const;

/**
 * @author lyy
 *
 */
@Controller
public class LinksAction {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private LinksService linksService;
	/**
	 * 链接信息查询显示
	 * @param modelMap
	 * @param startIndex
	 * @return
	 */   
	@RequestMapping(value="/manage/website/links/list/{startIndex}", method=RequestMethod.GET)
	public String getAllLinks(ModelMap modelMap,
			@PathVariable("startIndex") Integer startIndex) {
		PagingTools<Links> pagingTools=null;
		try {
			pagingTools=linksService.getAllLinks(startIndex-1, PagingTools.PAGER_ECORD_DEFAULT);
		} catch (Throwable e) {
			log.error("链接"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			
			return "manage/errer.html";
		}
		modelMap.put("pagingTools", pagingTools);
		return "manage/website/links/links_li.jsp";
	}
	
	/**
	 * 新建页面跳转
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/manage/website/links/newpage", method=RequestMethod.GET)
	public String goToNewPage(ModelMap modelMap) {
		return "manage/website/links/links_new.jsp";
	}
	
	/**
	 * 新建保存
	 * @param memberTpye	保存对象
	 * @param modelMap	页面赋值对象
	 * @return	返回页面
	 */
	@RequestMapping(value="/manage/website/links/save", method=RequestMethod.POST)
	public @ResponseBody  String saveNewPage(Links link,ModelMap modelMap) {
		String msg="0";
		try{
			//保存操作
			linksService.save(link);
			msg="1";
		}catch (Throwable e) {
			//报错失败，并跳转至错误页面
			log.error("链接信息"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
			return "manage/errer.jsp";
		}
		return msg;
	}
	
	/**
	 * 编辑保存
	 * @param modelMap	
	 * @param memberTpye	保存对象
	 * @return	列表页
	 */
	@RequestMapping(value="/manage/website/links/update", method=RequestMethod.POST)
	public @ResponseBody String updateLinks(ModelMap modelMap,Links link) {
		String msg="0";
		try{
			//保存操作
			linksService.update(link);
			msg="1";
		}catch (Throwable e) {
			//报错失败
			log.error("链接信息"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
			
			return "manage/errer.jsp";
		}
		return msg;
	}
	/**
	 * 编辑跳转
	 * @param modelMap	页面赋值
	 * @return
	 */
	@RequestMapping(value="/manage/website/links/editpage/{ids}", method=RequestMethod.GET)
	public String goToEditPage(ModelMap modelMap,@PathVariable("ids") long ids) {
		Links link=null;
		try{
			//保存操作
			link=linksService.getLinkById(ids);
		}catch (Throwable e) {
			//报错失败，并跳转至错误页面
			log.error("链接信息"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			return "manage/errer.html";
		}
		modelMap.put("link", link);
		return "manage/website/links/links_edit.jsp";
	}
	/**
	 * 删除操作
	 * @param modelMap	
	 * @return	列表页
	 */
	@RequestMapping(value="/manage/website/links/delete/{id}/{pagnum}", method=RequestMethod.GET)
	public @ResponseBody String deleteLink(ModelMap modelMap,@PathVariable("id") String id,
			@PathVariable("pagnum") Integer pagnum) {
		String msgString="0";
		try{			
			//删除操作
			linksService.deleteById(id);
			modelMap.put("message", "删除成功");
			msgString="1";
		}catch (Throwable e) {
			//报错失败，并跳转至错误页面
			log.error("链接信息删除失败", e);
			modelMap.put("message", "删除失败,"+Const.MESSAGE_FAIL_PRIFX+e.getMessage());
			
		}
		return msgString;
	}
	
	@RequestMapping(value="/manage/website/links/back", method=RequestMethod.POST)
	public String goBackPage(ModelMap modelMap){
		return getAllLinks(modelMap, 0);
	}
}
