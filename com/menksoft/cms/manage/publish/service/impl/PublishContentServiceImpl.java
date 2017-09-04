/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : PublishContentServiceImpl.java
 * 描述        : 内容生成静态页
 * 作者        : 呼和
 * 日期        : 上午10:33:38
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       上午10:33:38  lenovo
 */
package com.menksoft.cms.manage.publish.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.menksoft.cms.freemarker.service.MakePageService;
import com.menksoft.cms.manage.publish.service.PublishContentService;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.service.ColumnsService;

/**
 * @author 呼和
 */
@Service("publishContentService")
public class PublishContentServiceImpl implements PublishContentService {
	@javax.annotation.Resource
	private MakePageService MakePageService;
	@javax.annotation.Resource
	private ColumnsService columnsService;


	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.publish.service.impl.PublishColumnService#columnHtmlPage()
	 */
	@Override
	public String contentHtmlPage(String ids,HttpServletResponse response) throws NumberFormatException, Throwable {
		String[] id=ids.split(",");
		String message="";
		for(int i=0;i<id.length;i++){
			Columns columns=columnsService.getColumnsById(Long.valueOf(id[i]));
			if(columns.getContentModel()!=null&&!columns.getContentModel().equals("")){
//				List<Content> list = new ArrayList<Content>();
//				list = contentService.getContentByWebsite(columns);
//				message=message+MakePageService.MakeContentPage(columns,list);
				
			}
		}
		
		return message;
	}

	@Override
	public String contentHtmlPageAll(HttpServletResponse response)
			throws NumberFormatException, Throwable {
		String message="";
		List<Columns> list=columnsService.getAllColumn();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getContentModel()!=null&&!list.get(i).getContentModel().equals("")){
//				List<Content> list_c = new ArrayList<Content>();
//				
//				list_c = contentService.getContentByWebsite(list.get(i));
//				
//					message=message+MakePageService.MakeContentPage(list.get(i),list_c);
				
			}
		}
		
		return message;
	}


}
