/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : Util.java
 * 描述        : 
 * 作者        : lenovo
 * 日期        : 下午3:01:18
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       下午3:01:18  lenovo
 */
package com.menksoft.cms.freemarker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menksoft.cms.manage.website.entity.Website;
import com.menksoft.cms.manage.website.service.WebsiteService;
import com.menksoft.util.Const;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author 呼和
 */
/**
 * @author 呼和
 */
public class FreeMarkerUtil {
	/**
	 * 将params的值复制到variable中
	 * 
	 * @param env
	 * @param params
	 * @return 原Variable中的值
	 * @throws TemplateException
	 */
	public static Map<String, TemplateModel> addParamsToVariable(
			Environment env, Map<String, TemplateModel> params)
			throws TemplateException {
		Map<String, TemplateModel> origMap = new HashMap<String, TemplateModel>();
		if (params.size() <= 0) {
			return origMap;
		}
		Set<Map.Entry<String, TemplateModel>> entrySet = params.entrySet();
		String key;
		TemplateModel value;
		for (Map.Entry<String, TemplateModel> entry : entrySet) {
			key = entry.getKey();
			value = env.getVariable(key);
			if (value != null) {
				origMap.put(key, value);
			}
			env.setVariable(key, entry.getValue());
		}
		return origMap;
	}
	/**
	 * 将variable中的params值移除
	 * 
	 * @param env
	 * @param params
	 * @param origMap
	 * @throws TemplateException
	 */
	public static void removeParamsFromVariable(Environment env,
			Map<String, TemplateModel> params,
			Map<String, TemplateModel> origMap) throws TemplateException {
		if (params.size() <= 0) {
			return;
		}
		for (String key : params.keySet()) {
			env.setVariable(key, origMap.get(key));
		}
	}
	
	/**
	 * 获取站点信息
	 * @return
	 */
	public static Website getWebsite(){
		WebsiteService websiteService=(WebsiteService) ApplicationContextHolder.getApplicationContext().getBean("WebsiteService");
		Website website=null;
		try {
			 website=websiteService.getWebsiteById(1);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return website;
	}
	/**
	 * 获取freemarker配置文件内容
	 * @param key 对象名称
	 * @return
	 */
	public static String getString(String key) {
		String propertyFileName="config/spring/applicationContext" ;
	    ResourceBundle resourceBundle;
	    resourceBundle = ResourceBundle.getBundle(propertyFileName); 
        if (key == null || key.equals("") || key.equals("null")) { 
            return ""; 
        } 
        String result = ""; 
        try { 
            result = resourceBundle.getString(key); 
        } catch (MissingResourceException e) { 
            e.printStackTrace(); 
        } 
        return result; 
    }
	
	/**
	 * 获取项目路径
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void getBasepath(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Const.BASEPATH==null){
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			Const.BASEPATH = basePath;
			
			if(request.getServletContext().getAttribute("basePath") == null){
				request.getServletContext().setAttribute("basePath", basePath);
			}
		}
//		response.sendRedirect(basePath+"action/manage/main");
	}
}
