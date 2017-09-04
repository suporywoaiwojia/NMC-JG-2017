/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : WebsiteAction.java
 * 描述        : 会员管理
 * 作者        : 李彦佑
 * 日期        : 2012-11-14上午11:39:36
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1   2012-11-14  李彦佑
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

import com.menksoft.cms.manage.website.entity.Website;
import com.menksoft.cms.manage.website.service.WebsiteService;
import com.menksoft.util.Const;

/**
 * @author lyy
 *
 */
/**
 * @author Administrator
 *
 */
@Controller
public class WebsiteAction {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private WebsiteService websiteService;
	

	/**
	 * 有值的情况下的更新修改
	 * @param modelMap	
	 * @param memberTpye	保存对象
	 * @return	列表页
	 */
	@RequestMapping(value="/manage/website/update", method=RequestMethod.POST)
	public @ResponseBody String updateWebsite(ModelMap modelMap,Website website) {
		String msg="0";
		try{
			//保存操作
			websiteService.update(website);
			msg="1";
		}catch (Throwable e) {
			//报错失败
			log.error("站点信息"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);

			return "manage/errer.jsp";
		}
		return msg;
	}
	/**
	 * 站点页面的新建或查询
	 * @param modelMap	页面赋值
	 * @return
	 */
	@RequestMapping(value="/manage/website/editpage/{ids}", method=RequestMethod.GET)
	
	public String goToEditPage(ModelMap modelMap,@PathVariable("ids") long ids) {
		Website website=null;
		String webString = new String();
		try{
			//
			website=websiteService.getWebsiteById(ids);

			if(website!=null){
				webString = "manage/website/website_list.jsp";
			}else{
				webString = "manage/website/website_new.jsp";
			}
		}catch (Throwable e) {
			//报错失败，并跳转至错误页面
			log.error("链接信息"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			return "manage/errer.html";
		}
		modelMap.put("website", website);
		//System.out.println(webString+"++++++++++++++++");
		return webString;
	}
}
