/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : MakePage.java
 * 描述        : 读取模版并生成静态页
 * 作者        : 呼和
 * 日期        : 2012/03/12
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       2012/03/12  呼和
 */
package com.menksoft.cms.freemarker.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.freemarker.FreeMarkerUtil;
import com.menksoft.cms.freemarker.service.MakePageService;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.entity.ContentFile;
import com.menksoft.cms.manage.website.service.WebsiteService;
import com.menksoft.util.Const;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author 呼和
 */
@Service("MakePageService")
public class MakePageServiceImpl implements MakePageService {

	
	private String webPage = Const.APP_ROOT;
	private String domainName="";
	private String pageFormat = "";// 生成页面后缀格式
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.menksoft.cms.freemarker.MakePageService#afterPropertiesSet()
	 */
	@javax.annotation.Resource
	WebsiteService websiteService;

	
	
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@Autowired
	private Configuration freeMarkerConfiguration;

	 //private FreeMarkerConfigurer freeMarkerConfigurer=null;
	public void setFreeMarkerConfigurer(
			FreeMarkerConfigurer freeMarkerConfigurer) throws Throwable {
		// 创建Configuration实例
		  this.freeMarkerConfigurer = freeMarkerConfigurer; 
		
		  freeMarkerConfigurer.getConfiguration().setObjectWrapper(new DefaultObjectWrapper());
		// 设置包装器，并将对象包装为数据模型
		
		this.pageFormat = FreeMarkerUtil.getString("pageFormat");
		this.domainName=websiteService.getWebsiteById(1).getDomainName();
	}
	public void setFreeMarkerConfiguration(Configuration freeMarkerConfiguration) {
		this.freeMarkerConfiguration = freeMarkerConfiguration;
	}
	
	public String getContent(String templateName, Map<String, Object> model) {
		try {
			Template t = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
			freeMarkerConfigurer.getConfiguration().setObjectWrapper(new DefaultObjectWrapper());
			return FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
		} catch (Exception ex) {
			ex.printStackTrace();
			//logger.equals(ex.getMessage());
			try {
				Template t = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
				freeMarkerConfigurer.getConfiguration().setObjectWrapper(new DefaultObjectWrapper());
				return FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
			} catch (Exception e) {
				e.printStackTrace();
			//	logger.equals(e.getMessage());
			}
		}

		return null;
	}
	public Template getTemplate(String templateName) {
		Template t=null;
		try {
			t = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
			freeMarkerConfigurer.getConfiguration().setObjectWrapper(new DefaultObjectWrapper());
			return t;
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}

		return t;
	}
	
	
	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.menksoft.cms.freemarker.MakePageService#buildHtml(freemarker.template
	 * .Configuration, java.lang.String, java.lang.String,
	 * org.springframework.ui.ModelMap, java.lang.String)
	 */
	@Override
	public String MakePage(String ModelPage, String htmlName, String pathName,
			HttpServletResponse response) throws IOException, TemplateException {
		// 页面变量传值
		ModelMap modelMap = new ModelMap();
		// 获取网站页面地址
//		
		String path = webPage + pathName + File.separator;
		// 生成页面格式
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate(ModelPage);
//		Template template = freeMarkerConfiguration.getTemplate(ModelPage);
		PrintWriter writer = null;
		// 查询站点信息 indexDynamic=1 为静态主页 0为动态主页
		String indexDynamics = FreeMarkerUtil.getWebsite().getIndexDynamics();
		if (indexDynamics.equals("1")) {
			writer = new PrintWriter(
					new OutputStreamWriter(new FileOutputStream(path + htmlName
							+ pageFormat), "UTF-8"));// 编码问题
		} else {
			writer = response.getWriter();
		}
		String basePath = Const.BASEPATH;
		modelMap.put("basePath", basePath);
		template.process(modelMap, writer);
//		writer.print(getContent(ModelPage,modelMap));
		writer.flush();
		writer.close();
		return "生成完毕：" + domainName +"/"+ htmlName + pageFormat;
	}





	

	

	
	@Override
	public String MakeListPageformodel(int pageNum,long memberid, String model,
			PagingTools<?> paglist, HttpServletResponse response,List<?> list,String flag)
			throws Throwable, IOException, TemplateException {
		String message = ""; // 生成信息
		Template template = getTemplate(model);
		PrintWriter writer = response.getWriter();
		ModelMap modelMap = new ModelMap();
		// 获取栏目对象

		// 文件存放路径


		String basePath = Const.BASEPATH;
		template.setCustomAttribute("memberid", memberid);
		
		template.setCustomAttribute("currentPage", pageNum);
		modelMap.put("currentPage", pageNum);
		
		modelMap.put("flag", flag);
		modelMap.put("memberid", memberid);
		//modelMap.put("memberid", memberid);
		modelMap.put("basePath", basePath);
		if(paglist!=null){
			template.setCustomAttribute("dbh_list", paglist.getDataSet());
			modelMap.put("totalPages", paglist.getTotalPage());
		}
		if(list!=null) {
			modelMap.put("dbh_list", list);
		
		}
		writer.print(getContent(model,modelMap));
		writer.flush();
		writer.close();
		return message;
	}
	@Override
	public String MakeAttFile(Columns column, List<ContentFile> list, String lg)
			throws Throwable, IOException, TemplateException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean exportPage(String ModelPage, String htmlName, String pathName,
			HttpServletResponse response,List<?> list1,List<?> list2,List<?> list3,long id)  {
		// 页面变量传值
				boolean result = true;  
				ModelMap modelMap = new ModelMap();
				// 获取网站页面地址
				String path = webPage + pathName + File.separator;
				// 生成页面格式
				Template template;
				try {
//					template = freeMarkerConfigurer.getConfiguration().getTemplate(ModelPage);
					template =getTemplate(ModelPage);
					PrintWriter writer = null;
					
						writer = new PrintWriter(
								new OutputStreamWriter(new FileOutputStream(path + htmlName
										+ pageFormat), "UTF-8"));// 编码问题
					modelMap.put("pid", id);
					modelMap.put("list1", list1);
					modelMap.put("list2", list2);
					modelMap.put("list3", list3);
					String basePath = Const.BASEPATH;
					modelMap.put("basePath", basePath);
					template.process(modelMap, writer);
					writer.flush();
					writer.close();
				} catch (IOException | TemplateException e) {
					result=false;
					e.printStackTrace();
				}
				
				return result;
	}
}
