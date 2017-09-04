/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : CmsLinks.java
 * 描述        : 友情链接 dbh_links
 * 作者        : 呼和
 * 日期        : 上午9:30:24
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       上午9:30:24  lenovo
 */
package com.menksoft.cms.freemarker.Cms;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.menksoft.cms.manage.website.entity.Links;
import com.menksoft.cms.manage.website.service.LinksService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author 呼和
 */
public class CmsLinks implements TemplateDirectiveModel {

	/* (non-Javadoc)
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.Environment, java.util.Map, freemarker.template.TemplateModel[], freemarker.template.TemplateDirectiveBody)
	 */
	@Resource
	LinksService linksService;
	@Override
	@SuppressWarnings("all")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<Links> list=this.getList();
		for(Links links:list){
			env.setVariable(params.get("var").toString(), DEFAULT_WRAPPER.wrap(links));
			body.render(env.getOut());
		}

	}
	
	public List<Links> getList(){
//		LinksService linksService=(LinksService) ApplicationContextHolder.getApplicationContext().getBean("LinksService");
		List<Links> links=new ArrayList<Links>();
		try {
			links=linksService.getAllLiks();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return links;
	}

}
