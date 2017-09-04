/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : CmsAttachment.java
 * 描述        : 查询内容附件
 * 作者        : lenovo
 * 日期        : 下午2:57:38
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       下午2:57:38  lenovo
 */
package com.menksoft.cms.freemarker.Cms;


import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * @author 呼和
 */
public class CmsServlet  implements TemplateDirectiveModel {
	private static final String PARAM_NAME_name  = "name";	//取值属性
	private static final String PARAM_NAME_scope  = "scope";	//范围

	@SuppressWarnings("all")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		if(params.get(PARAM_NAME_name)==null)
			throw new TemplateModelException("dbh_servlet"+"缺少参数"+PARAM_NAME_name);
		if(params.get(PARAM_NAME_scope)==null)
			throw new TemplateModelException("dbh_servlet"+"缺少参数"+PARAM_NAME_scope);
		if(params.get("var")==null)
			throw new TemplateModelException("dbh_servlet"+"缺少参数"+"var");
		try {
			String re="";
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();   
			if(request!=null){
				if((params.get(PARAM_NAME_scope).toString().equals("session")||
						params.get(PARAM_NAME_scope).toString().equals("Session"))&&request.getSession().getAttribute(params.get(PARAM_NAME_name).toString())!=null){
					Object	re1=request.getSession().getAttribute(params.get(PARAM_NAME_name).toString());
					env.setVariable(params.get("var").toString(), DEFAULT_WRAPPER.wrap(re1));
				}
				
				if((params.get(PARAM_NAME_scope).toString().equals("request")||
						params.get(PARAM_NAME_scope).toString().equals("Request"))&&request.getParameter(params.get(PARAM_NAME_name).toString())!=null){
					re=request.getParameter(params.get(PARAM_NAME_name).toString()).toString();
					env.setVariable(params.get("var").toString(), DEFAULT_WRAPPER.wrap(re));
				}
				if((params.get(PARAM_NAME_scope).toString().equals("servletcontext")||
						params.get(PARAM_NAME_scope).toString().equals("Servletcontext"))&&request.getServletContext().getAttribute(params.get(PARAM_NAME_name).toString())!=null){
					re=request.getServletContext().getAttribute(params.get(PARAM_NAME_name).toString()).toString();
					env.setVariable(params.get("var").toString(), DEFAULT_WRAPPER.wrap(re));
				}
			}
			
			body.render(env.getOut());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}

}
