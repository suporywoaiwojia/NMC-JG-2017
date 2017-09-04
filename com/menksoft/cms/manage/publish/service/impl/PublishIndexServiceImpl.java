/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : PublishIndexServiceImpl.java
 * 描述        : 生成首页
 * 作者        : lenovo
 * 日期        : 下午4:25:46
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       下午4:25:46  lenovo
 */
package com.menksoft.cms.manage.publish.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.menksoft.cms.freemarker.FreeMarkerUtil;
import com.menksoft.cms.manage.publish.service.PublishIndexService;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.entity.behavior.ColumnsBehavior;
import com.menksoft.util.Const;

/**
 * @author 呼和
 */
@Service("publishIndexService")

public class PublishIndexServiceImpl implements PublishIndexService {
	@Resource
	private ColumnsBehavior columnsBehavior;
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.publish.service.PublishIndexService#deleteIndexPage()
	 */
	@Override
	public String deleteIndexPage() {
		String msg="删除失败";
		//文件路径
		String filePath=Const.APP_ROOT;
		
		//生成文件后缀名称
		String pageFormat=FreeMarkerUtil.getString("pageFormat");
		File file = new File(filePath+"index"+pageFormat);   
	    // 路径为文件且不为空则进行删除   
		System.out.println(file.getAbsolutePath());
	    if (file.isFile() && file.exists()) {   
	        file.delete();   
	        msg="删除成功";
	    }   
	    return msg;
	}

	@Override
	public List<Columns> getColumnsFindex() throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Columns.class);
		detachedCriteria.add(Restrictions.eq("pageNum", 0));
		return columnsBehavior.getColumnsByWebsite(detachedCriteria);
	}

}
