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
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.menksoft.cms.application.dao.hibernate.OrderPattern;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.freemarker.DirectiveUtils;
import com.menksoft.cms.manage.webContent.entity.ContentFile;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * @author 呼和
 */
public class CmsAttachment  implements TemplateDirectiveModel {
	private static final String PARAM_NAME_count  = "count";	//显示数量
	private static final String PARAM_NAME_column  = "columnId";	//栏目ID
	private static final String PARAM_NAME_content  = "contentId";	//所属内容ID
	private static final String PARAM_NAME_theme  = "theme";	//所属内容ID
	private static final String PARAM_NAME_attid  = "attId";	//附件ID
//	@Resource
//	ContentFileService contentFileService;
//	@SuppressWarnings("all")
//	public void execute(Environment env, Map params, TemplateModel[] loopVars,
//			TemplateDirectiveBody body) throws TemplateException, IOException {
//		if(params.get(PARAM_NAME_attid)==null&&params.get(PARAM_NAME_count)==null)
//			throw new TemplateModelException("dbh_attachment"+"缺少参数"+PARAM_NAME_count);
//		if(params.get("var")==null)
//			throw new TemplateModelException("dbh_attachment"+"缺少参数"+"var");
//		try {
//			if(params.get(PARAM_NAME_attid)==null){
//			PagingTools<ContentFile>  list = getList(params);
//				for(ContentFile contentFile:list.getDataSet()){
//					env.setVariable(params.get("var").toString(), DEFAULT_WRAPPER.wrap(contentFile));
//					body.render(env.getOut());
//				}
//			}else{
//				env.setVariable(params.get("var").toString(), DEFAULT_WRAPPER.wrap(getAtt(params)));
//				body.render(env.getOut());
//			}
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		
//	}
//	@SuppressWarnings("all")
//	public PagingTools<ContentFile> getList(Map params) throws Throwable{
//		DetachedCriteria criteria = DetachedCriteria.forClass(ContentFile.class);
//		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
//		PagingTools<ContentFile> list1=null;
//		if(params.get(PARAM_NAME_column)!=null)
//			criteria.add(Restrictions.eq("columnId",DirectiveUtils.getString(PARAM_NAME_column, params)));
//		if(params.get(PARAM_NAME_content)!=null){
////			Content content =new Content();
////			content.setId(DirectiveUtils.getLong(PARAM_NAME_content, params));
////			criteria.add(Restrictions.eq("content",content));
//		}
//		if(params.get(PARAM_NAME_theme)!=null&&DirectiveUtils.getString(PARAM_NAME_theme, params).equals("1"))
//			criteria.add(Restrictions.eq("theme","1"));
//		orders.put("creatDate", OrderPattern.desc);
//		Integer num=DirectiveUtils.getInt(PARAM_NAME_count, params);
//		list1=contentFileService.getFile(criteria,num,orders);
//		return list1;
//	}
//	@SuppressWarnings("all")
//	public ContentFile  getAtt(Map params) throws Throwable{
//		DetachedCriteria criteria = DetachedCriteria.forClass(ContentFile.class);
//		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
//		long id=DirectiveUtils.getLong(PARAM_NAME_attid, params);
//		
//			criteria.add(Restrictions.eq("id",id));
//			ContentFile cf=contentFileService.getfileByid(id);
//		
//		return cf;
//	}
	@Override
	public void execute(Environment arg0, Map arg1, TemplateModel[] arg2,
			TemplateDirectiveBody arg3) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		
	}
}
