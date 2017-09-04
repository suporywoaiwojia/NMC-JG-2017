/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : CmsMember.java
 * 描述        : 获取会员信息
 * 作者        : 呼和
 * 日期        : 下午2:40:22
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       下午2:40:22  lenovo
 */
package com.menksoft.cms.freemarker.Cms;


import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.annotation.Resource;

import com.menksoft.cms.freemarker.DirectiveUtils;
import com.menksoft.cms.manage.webContent.entity.Project;
import com.menksoft.cms.manage.webContent.service.ProjectService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * @author 呼和
 */
public class CmsProject_M implements TemplateDirectiveModel {
	private static final String PARAM_ID = "id";
	/* (non-Javadoc)
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.Environment, java.util.Map, freemarker.template.TemplateModel[], freemarker.template.TemplateDirectiveBody)
	 */
	@Resource
	ProjectService projectService;
	@SuppressWarnings("all")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Writer out = env.getOut();// 固定写法

		long id=DirectiveUtils.getLong(PARAM_ID, params);
		if(params.get("id")==null)
			throw new TemplateModelException("缺少参数"+PARAM_ID);
		Project p=getproject(id);
		//名称
		if(p!=null){
			env.setVariable(params.get("var").toString(), DEFAULT_WRAPPER.wrap(p));
		}

		body.render(env.getOut());
	}
	/**
	 * 获取会员信息
	 * @return
	 */
	public Project getproject(long id){
		Project p=null;
		 try {
			 p=projectService.getProjectByid(id);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		 return p;
	}
	
}
