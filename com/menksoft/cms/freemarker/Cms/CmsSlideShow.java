/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : CmsSlideShow.java
 * 描述        : 幻灯片转换
 * 作者        : 呼和
 * 日期        : 上午10:39:02
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       上午10:39:02  lenovo
 */
package com.menksoft.cms.freemarker.Cms;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.menksoft.cms.freemarker.DirectiveUtils;
import com.menksoft.cms.manage.slideshow.entity.SlideshowFile;
import com.menksoft.cms.manage.slideshow.service.SlideshowService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * @author 呼和
 */
public class CmsSlideShow  implements TemplateDirectiveModel{
	private static final String PARAM_NAME_slideshowId = "slideshowId";	//幻灯片ID
	@Resource
	SlideshowService slideshowService;
	@SuppressWarnings("all")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
		TemplateDirectiveBody body) throws TemplateException, IOException {

		long id=DirectiveUtils.getLong(PARAM_NAME_slideshowId, params);
		if(params.get(PARAM_NAME_slideshowId)==null)
			throw new TemplateModelException("dbh_slideshow"+"缺少参数"+PARAM_NAME_slideshowId);
		List<SlideshowFile> list;
		list=getList(id);
		for(SlideshowFile slideshowFile:list){
			env.setVariable(params.get("var").toString(), DEFAULT_WRAPPER.wrap(slideshowFile));
			body.render(env.getOut());
		}
			
	}
	/**
	 * 获取幻灯片
	 * @param id
	 * @return
	 */
	public List<SlideshowFile> getList(long id){
		 List<SlideshowFile> list=new ArrayList<SlideshowFile>();
		 try {
//			 SlideshowService slideshowService=(SlideshowService) ApplicationContextHolder.getApplicationContext().getBean("slideshowService");
			 list=slideshowService.getSlideshowFileByWebsite(id);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		 return list;
	}
}
