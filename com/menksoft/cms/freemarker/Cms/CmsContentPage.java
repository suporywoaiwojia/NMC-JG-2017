/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : CmsContentPage.java
 * 描述        : 网站栏目内容翻页列表静态页，动态内容生成
 * 作者        : 呼和
 * 日期        : 2012/03/14
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       2012/03/14  呼和
 */
package com.menksoft.cms.freemarker.Cms;


import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.menksoft.cms.freemarker.DirectiveUtils;
import com.menksoft.cms.freemarker.FreeMarkerUtil;
import com.menksoft.util.Const;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author 呼和
 */
public class CmsContentPage implements TemplateDirectiveModel {
	/* (non-Javadoc)
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.Environment, java.util.Map, freemarker.template.TemplateModel[], freemarker.template.TemplateDirectiveBody)
	 */
	

	@SuppressWarnings("all")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
		TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		
		
//		List<Content> list=new ArrayList();
//		list=(ArrayList)env.getCustomAttribute("dbh_list");
//		String listDynamics=(String)env.getCustomAttribute("listDynamics");
//		String path_1=env.getCustomAttribute("path").toString();
		String basePath=Const.BASEPATH;
//		if(list.size()!=0){
//			for(Content content:list){
//				//全文检索增加
////				path_1 = content.getColumn().getColumnPath();
//				//
//				env.setVariable(params.get("var").toString(), DEFAULT_WRAPPER.wrap(content));
//				try {
//					env.setVariable("hits", DEFAULT_WRAPPER.wrap(topService.getTotalHits(String.valueOf(content.getId()),String.valueOf(content.getColumn().getId()))));
//				} catch (NumberFormatException e) {
//					env.setVariable("hits", DEFAULT_WRAPPER.wrap("0"));
//				} catch (Throwable e) {
//					env.setVariable("hits", DEFAULT_WRAPPER.wrap("0"));
//				}
//				
//				env.setVariable("url",DEFAULT_WRAPPER.wrap(basePath+"page"+"/"+content.getId()+FreeMarkerUtil.getString("pageFormat")));
//				env.setVariable("wapurl",DEFAULT_WRAPPER.wrap(basePath+"page"+"/wap"+content.getId()+FreeMarkerUtil.getString("pageFormat")));
//				body.render(env.getOut());
//			}
//		}else{
//			Content content=new Content();
//			env.setVariable(params.get("var").toString(), DEFAULT_WRAPPER.wrap(content));
//			env.setVariable("url",DEFAULT_WRAPPER.wrap(""));
//			env.setVariable("hits", DEFAULT_WRAPPER.wrap("0"));
//			body.render(env.getOut());
//		}
		
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);

	}

	
}
