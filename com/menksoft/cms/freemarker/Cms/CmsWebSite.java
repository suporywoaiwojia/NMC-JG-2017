/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : CmsWebSite.java
 * 描述        : 网站基本信息读取 dbh_website
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
import java.util.HashMap;
import java.util.Map;

import com.menksoft.cms.freemarker.DirectiveUtils;
import com.menksoft.cms.freemarker.FreeMarkerUtil;
import com.menksoft.cms.manage.website.entity.Website;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author 呼和
 */
public class CmsWebSite implements TemplateDirectiveModel {
	private static final String PARAM_NAME_WEBNAME = "webName";	//名称
	private static final String PARAM_NAME_ABBREVIATION = "abbreviation";//简称
	private static final String PARAM_NAME_DOMAINNAME = "domainName";//域名
	private static final String PARAM_NAME_TEL = "tel";//电话
	private static final String PARAM_NAME_MAIL = "mail";//邮箱
	private static final String PARAM_NAME_LOGO = "logo";//logo地址
	private static final String PARAM_NAME_BUSINESSNAME = "businessName";//名称
	private static final String PARAM_NAME_BUSINESSADD = "businessAdd";//地址
	private static final String PARAM_NAME_POSTCODE = "postCode";//邮编
	private static final String PARAM_NAME_IPC = "ipc";//备案号
	private static final String PARAM_NAME_COPYRIGHT = "copyRight";//版权
	private static final String PARAM_NAME_WEBKEY = "webKey";//关键字
	/* (non-Javadoc)
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.Environment, java.util.Map, freemarker.template.TemplateModel[], freemarker.template.TemplateDirectiveBody)
	 */
	@Override
	@SuppressWarnings("all")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		Website website=FreeMarkerUtil.getWebsite();
		paramWrap.put(PARAM_NAME_WEBNAME, DEFAULT_WRAPPER.wrap(website.getName()));
		paramWrap.put(PARAM_NAME_ABBREVIATION, DEFAULT_WRAPPER.wrap(website.getAbbreviation()));
		paramWrap.put(PARAM_NAME_DOMAINNAME, DEFAULT_WRAPPER.wrap(website.getDomainName()));
		paramWrap.put(PARAM_NAME_TEL, DEFAULT_WRAPPER.wrap(website.getTel()));
		paramWrap.put(PARAM_NAME_MAIL, DEFAULT_WRAPPER.wrap(website.getMail()));
		paramWrap.put(PARAM_NAME_LOGO, DEFAULT_WRAPPER.wrap(website.getLogoPath()));
		paramWrap.put(PARAM_NAME_BUSINESSNAME, DEFAULT_WRAPPER.wrap(website.getBusinessName()));
		paramWrap.put(PARAM_NAME_BUSINESSADD, DEFAULT_WRAPPER.wrap(website.getBusinessAdd()));
		paramWrap.put(PARAM_NAME_POSTCODE, DEFAULT_WRAPPER.wrap(website.getPostCode()));
		paramWrap.put(PARAM_NAME_IPC, DEFAULT_WRAPPER.wrap(website.getIpc()));
		paramWrap.put(PARAM_NAME_COPYRIGHT, DEFAULT_WRAPPER.wrap(website.getCopyRight()));
		paramWrap.put(PARAM_NAME_WEBKEY, DEFAULT_WRAPPER.wrap(website.getWebKey()));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}
}
