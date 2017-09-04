/**
 * 项目名称: 草原文化创意资源平台
 * 文件名    : IndexAction.java
 * 描述        : 
 * 作者        : lenovo
 * 日期        : 下午5:17:30
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       下午5:17:30  lenovo
 */
package com.menksoft.cms.website.index.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.menksoft.cms.freemarker.FreeMarkerUtil;
import com.menksoft.cms.freemarker.service.MakePageService;
import com.menksoft.cms.manage.webContent.entity.Columns;


/**
 * @author 
 */

@Controller
public class IndexAction {
    @Resource
	MakePageService makePageService;
	

	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexPage(ModelMap modelMap, HttpServletResponse response,
			HttpServletRequest request) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		try {
			
			String indexDynamics = FreeMarkerUtil.getWebsite()
					.getIndexDynamics();
			if (indexDynamics.equals("1")) {
				System.out.println("success+++++++++++++++++++++++++");
				return "../../index.html";
			}
			FreeMarkerUtil.getBasepath(request, response);
			makePageService.MakePage("index.html", "", "", response);
			System.out.println("000000000000++++++++++++");
		} catch (Throwable e) {
			e.printStackTrace();
			return "manage/errer.html";

		}
		return "";
	}

	@RequestMapping(value = "/{lg}/index", method = RequestMethod.GET)
	public String indexPage_lg(ModelMap modelMap, HttpServletResponse response,
			HttpServletRequest request, @PathVariable String lg) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		try {

			String indexDynamics = FreeMarkerUtil.getWebsite()
					.getIndexDynamics();
			if (indexDynamics.equals("1")) {
				return "../../index_"+lg.toUpperCase()+".html";
			}
			FreeMarkerUtil.getBasepath(request, response);
			makePageService.MakePage("index_"+lg.toUpperCase()+".html", "", "", response);
		} catch (Throwable e) {
			e.printStackTrace();
			return "manage/errer.html";

		}
		return "";
	}
	
	@RequestMapping(value = "/wap/index", method = RequestMethod.GET)
	public String indexPageWap(ModelMap modelMap, HttpServletResponse response,
			HttpServletRequest request) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String indexDynamics = FreeMarkerUtil.getWebsite().getIndexDynamics();
		if (indexDynamics.equals("1")) {
			return "../../wap_index.html";
		}

		try {
			FreeMarkerUtil.getBasepath(request, response);
			makePageService.MakePage("wap_index.html", "", "", response);
		} catch (Throwable e) {
			e.printStackTrace();
			return "manage/errer.html";
		}
		return "";
	}

	

}