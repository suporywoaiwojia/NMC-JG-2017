package com.menksoft.cms.manage.webContent.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.webContent.entity.Center;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.service.CenterService;
import com.menksoft.cms.manage.webContent.service.ColumnsService;

/**
 * @author Administrator
 *
 */
@Controller
public class CenterAction {
	@Resource
	private CenterService centerService;
	@Resource
	private ColumnsService columnsService;
    /**
     * @param modelMap
     * @param startIndex
     * @param parentId
     * @return
     */
    @RequestMapping(value="/center/list/{startIndex}/{parentId}",method=RequestMethod.GET)
	public String getCenters(ModelMap modelMap,@PathVariable("startIndex") Integer startIndex,
			@PathVariable("parentId") long parentId) {
		   PagingTools<Center> centers = null;
		   
		   try {
			   Columns columns = columnsService.getColumnsById(parentId);
			   
			centers = centerService.getList(columns, startIndex, PagingTools.PAGER_ECORD_DEFAULT);

			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		   modelMap.put("pagingTools", centers);
		   return "manage/center/center_list.jsp";
	}
}
