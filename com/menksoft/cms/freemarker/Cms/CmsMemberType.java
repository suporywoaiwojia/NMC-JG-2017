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


import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.freemarker.DirectiveUtils;
import com.menksoft.cms.manage.member.entity.MemberType;
import com.menksoft.cms.manage.member.service.MemberTypeService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author 呼和
 */
public class CmsMemberType implements TemplateDirectiveModel {
	/* (non-Javadoc)
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.Environment, java.util.Map, freemarker.template.TemplateModel[], freemarker.template.TemplateDirectiveBody)
	 */
	@Resource
	MemberTypeService memberTypeService;
	@Override
	@SuppressWarnings("all")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
	
			
			try {
				if(params.get("id")==null){
					PagingTools<MemberType>list=memberTypeService.getAllMemberType(0, 10000);
					
					for(MemberType memberType:list.getDataSet()){
						env.setVariable(params.get("var").toString(), DEFAULT_WRAPPER.wrap(memberType));
						body.render(env.getOut());
					}
				}else{
					long id=DirectiveUtils.getInt("id", params);
					MemberType mt=memberTypeService.getMemberTypeById(id);
					env.setVariable(params.get("var").toString(), DEFAULT_WRAPPER.wrap(mt));
					body.render(env.getOut());
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
	}
}

