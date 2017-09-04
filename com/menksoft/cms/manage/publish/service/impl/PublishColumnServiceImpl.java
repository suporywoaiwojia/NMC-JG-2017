/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : PublishColumnServiceImpl.java
 * 描述        : 栏目生成静态页
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
import com.menksoft.cms.manage.publish.service.PublishColumnService;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.service.ColumnsService;

/**
 * @author 呼和
 */
@Service("publishColumnService")
public class PublishColumnServiceImpl implements PublishColumnService {
	@javax.annotation.Resource
	private MakePageService MakePageService;
	@javax.annotation.Resource
	private ColumnsService columnsService;

	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.publish.service.impl.PublishColumnService#columnHtmlPage()
	 */
	@Override
	public String columnHtmlPage(String ids,int pageNum,String indexDynamics,String listDynamics,HttpServletResponse response) throws NumberFormatException, Throwable {
		String[] id=ids.split(",");
		String message="";
		for(int i=0;i<id.length;i++){
			Columns columns=columnsService.getColumnsById(Long.valueOf(id[i]));
			//CreatFolder.mkdir(columns.getPath());
			if(columns!=null){
				if(columns.getPageNum()==0){
					message=message+MakePageService.MakePage("column//"+columns.getListModel(), "index", "\\page\\",response)+"<br/>";
				}
			}
		}
		return message;
	}

	@Override
	public String columnHtmlPageAll(int pageNum, String indexDynamics,
			String listDynamics, HttpServletResponse response)
			throws NumberFormatException, Throwable {
		List<Columns> list=columnsService.getAllColumn();
		String message="";
		for(int i=0;i<list.size();i++){
			
			if(list.get(i)!=null){
				if(list.get(i).getPageNum()==0){
					if(indexDynamics.equals("1")){
					message=message+MakePageService.MakePage("column//"+list.get(i).getListModel(), "index", "\\page\\",response)+"<br/>";
					//wap生成静态二级主页
				}else{
					if(listDynamics.equals("1")){
//						PagingTools<Content> list_page = contentService.getContentByWebsite(pageNum, list.get(i).getPageNum(),list.get(i));
//						if(list.get(i).getListModel()!=null&&!list.get(i).getListModel().equals(""))
//							message=message+MakePageService.MakeListPage(pageNum,list.get(i),list_page,response)+"<br/>";
						}
					}
				}
			}
		}
		return message;
	}

}
