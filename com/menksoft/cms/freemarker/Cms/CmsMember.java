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
import com.menksoft.cms.manage.member.entity.Member;
import com.menksoft.cms.manage.member.service.MemberService;
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
public class CmsMember implements TemplateDirectiveModel {
	private static final String PARAM_MEMBER_ID = "id";
	/* (non-Javadoc)
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.Environment, java.util.Map, freemarker.template.TemplateModel[], freemarker.template.TemplateDirectiveBody)
	 */
	@Resource
	MemberService memberService;
	@SuppressWarnings("all")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Writer out = env.getOut();// 固定写法

		String id=DirectiveUtils.getString(PARAM_MEMBER_ID, params);
		if(params.get("id")==null)
			throw new TemplateModelException("dbh_member"+"缺少参数"+PARAM_MEMBER_ID);
		Member member=getMember(id);
		//名称
//		 env.setVariable("loginid", DEFAULT_WRAPPER.wrap(member.getLoginid()));
		if(member!=null){
			env.setVariable(params.get("var").toString(), DEFAULT_WRAPPER.wrap(member));
			String basePath=Const.BASEPATH+"action/";
			env.setVariable("memberUrl",DEFAULT_WRAPPER.wrap(basePath+"member"));
			env.setVariable("payUrl",DEFAULT_WRAPPER.wrap(basePath+"member/1/1"));
			env.setVariable("viewUrl",DEFAULT_WRAPPER.wrap(basePath+"member/2/1"));
			env.setVariable("favoriteUrl",DEFAULT_WRAPPER.wrap(basePath+"member/3/1"));
			env.setVariable("consumeUrl",DEFAULT_WRAPPER.wrap(basePath+"member/4/1"));
			
		}

		body.render(env.getOut());
	}
	/**
	 * 获取会员信息
	 * @return
	 */
	public Member getMember(String id){
		Member member=null;
		 try {
			 member=memberService.getMemberByWebsite(id);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		 return member;
	}
	
}
