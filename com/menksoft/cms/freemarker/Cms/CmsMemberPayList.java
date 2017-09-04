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
import com.menksoft.cms.manage.member.entity.Member;
import com.menksoft.cms.manage.member.entity.MemberPayment;
import com.menksoft.cms.manage.member.service.MemberPaymentService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * @author 呼和
 */
public class CmsMemberPayList implements TemplateDirectiveModel {
	private static final String PARAM_num = "count";
	/* (non-Javadoc)
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.Environment, java.util.Map, freemarker.template.TemplateModel[], freemarker.template.TemplateDirectiveBody)
	 */
	@Resource
	MemberPaymentService memberPaymentService;
	@Override
	@SuppressWarnings("all")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
	
		if(params.get(PARAM_num)==null)
			throw new TemplateModelException("dbh_member_paylist"+"缺少参数"+PARAM_num);
			long id=(Long)env.getCustomAttribute("memberid");
			int datanum=DirectiveUtils.getInt(PARAM_num, params);
			int currentPage=0;
					if((Integer)env.getCustomAttribute("currentPage")!=null)
						currentPage=(Integer)env.getCustomAttribute("currentPage");
			try {
				Member member=new Member();
				member.setId(id);
				PagingTools<MemberPayment>list=memberPaymentService.getMemberPaymentForpage(member, currentPage-1, datanum);
				env.setVariable("showRecordNum",DEFAULT_WRAPPER.wrap(datanum));
				env.setVariable("c_Page",DEFAULT_WRAPPER.wrap(currentPage));
				env.setVariable("t_Pages",DEFAULT_WRAPPER.wrap(list.getTotalPage()));
				for(MemberPayment memberPayment:list.getDataSet()){
					env.setVariable(params.get("var").toString(), DEFAULT_WRAPPER.wrap(memberPayment));
					body.render(env.getOut());
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
	}
}

