package com.menksoft.cms.manage.webContent.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import com.menksoft.cms.manage.data.service.CatalogService;
import com.menksoft.cms.manage.data.service.ItemContentService;
import com.menksoft.cms.manage.webContent.entity.ContentFile;
import com.menksoft.cms.manage.webContent.service.ContentFileService;

public class SetContentFile {
	
	/**
	 * 获取附件列表
	 * @param request
	 * @param id 关联内容ID
	 * @param type  1条目2目录 3内容 
	 * @return
	 */
	public static List<ContentFile> getFileList(HttpServletRequest request,long id,String type){
		List<ContentFile> ContentFileList=new ArrayList<ContentFile>();
		String[] filename=ServletRequestUtils.getStringParameters(request, "filename");
		String[] savename=ServletRequestUtils.getStringParameters(request, "savename");
		String[] savepath=ServletRequestUtils.getStringParameters(request, "savepath");
		String[] httppath=ServletRequestUtils.getStringParameters(request, "httppath");
		String[] filetype=ServletRequestUtils.getStringParameters(request, "filetype");
		String[] inheritorId=ServletRequestUtils.getStringParameters(request, "inheritorId");
		String[] inheritorName=ServletRequestUtils.getStringParameters(request, "inheritorName");
		String[] view=ServletRequestUtils.getStringParameters(request, "view");
		String cultural="";
		String kindType="";
		try {
			cultural=ServletRequestUtils.getStringParameter(request, "cultural");
			kindType=ServletRequestUtils.getStringParameter(request, "type");
		} catch (ServletRequestBindingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int a=0;a<filename.length;a++){
			ContentFile cf=new ContentFile();
			//需要根据传入的ID和类型参数，区分是目录，还是内容附件，依据ID查询到对应的数据，然后保存附件对象
			cf.setContentId(id);//与内容关联的ID，记录
			
			cf.setCreatDate(new Date());
			cf.setFileName(filename[a].substring(0,filename[a].lastIndexOf(".")));
			cf.setFileExc(filename[a].substring(filename[a].lastIndexOf(".")+1));
		
			cf.setSavePath(savepath[a]);
			cf.setSaveName(savename[a]);
			cf.setHttpPath(httppath[a]);

			cf.setType(type);

			cf.setViews(view[a]);
			if(savename[a].indexOf(".jpg")>0||savename[a].indexOf(".gif")>0||savename[a].indexOf(".png")>0){
				cf.setImgwaterPath(httppath[a].replace(savename[a], "W_"+savename[a]));
			}
				
			ContentFileList.add(cf);
		}
		return ContentFileList;
	}
}
