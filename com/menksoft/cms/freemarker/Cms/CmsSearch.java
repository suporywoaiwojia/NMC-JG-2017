/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : CmsSearch.java
 * 描述        : 
 * 作者        : lenovo
 * 日期        : 下午4:28:12
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       下午4:28:12  lenovo
 */
package com.menksoft.cms.freemarker.Cms;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import com.menksoft.cms.freemarker.DirectiveUtils;
import com.menksoft.util.Const;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * @author 呼和
 */
public class CmsSearch implements TemplateDirectiveModel {
	private static final String PARAM_KEY = "key";
	/* (non-Javadoc)
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.Environment, java.util.Map, freemarker.template.TemplateModel[], freemarker.template.TemplateDirectiveBody)
	 */

	@Override
	@SuppressWarnings("all")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		if(params.get(PARAM_KEY)==null)
			throw new TemplateModelException("dbh_search"+"缺少参数"+PARAM_KEY);
			String key=DirectiveUtils.getString(PARAM_KEY, params);
			String basePath=Const.BASEPATH+"action/";
			env.setVariable("url",DEFAULT_WRAPPER.wrap(basePath+"search/"+key+"/1"));
			body.render(env.getOut());
	}
}

