package com.menksoft.cms.manage.webContent.service.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.manage.data.entity.Catalog;
import com.menksoft.cms.manage.data.entity.Item;
import com.menksoft.cms.manage.data.entity.ItemContent;
import com.menksoft.cms.manage.data.service.CatalogService;
import com.menksoft.cms.manage.data.service.ContentService;
import com.menksoft.cms.manage.data.service.ItemContentService;
import com.menksoft.cms.manage.data.service.ItemService;
import com.menksoft.cms.manage.webContent.entity.ContentFile;
import com.menksoft.cms.manage.webContent.service.ContentFileService;
/**
 * @author Administrator
 *
 */
@Service("ContentFileService")
public class ContentFileServiceImpl implements ContentFileService {
	@Resource
	private HibernateTemplate hibernateTemplate;
	@Resource
	private CatalogService catalogService;
	@Resource
	private ItemContentService itemContentService;
	@Resource
	private ItemService itemService;
	/**
	 * @author Administrator
	 *通过传递的参数,type来区分是目录还是内容页，1为条目，2为目录，3为内容,然后存入关联
	 *查询到对应的内容，然后分别设置
	 */
	@Override
	public void save(List<ContentFile> list,long id,String type) throws Throwable {
		String sql="delete from r_tab_content_file where contentId="+id+" and  type='"+type+"'";
		hibernateTemplate.executeSql(sql, null);
		for(int a=0;a<list.size();a++){
			if(type.equals("1")){
			Item item = itemService.getItembyId(list.get(a).getContentId());
			list.get(a).setItemCFiles(item);
			}else if(type.equals("2")){
			Catalog catalog = catalogService.getCatalogbyId(list.get(a).getContentId());
			list.get(a).setCatalogContentFiles(catalog);
			}else if(type.equals("3")){
			ItemContent itemContent = itemContentService.getItemContentbyId(list.get(a).getContentId());	
			list.get(a).setItemContentFiles(itemContent);
			}
			
			hibernateTemplate.save(list.get(a));
		}
	}
	@Override
	public List<ContentFile> getList(String type, String filetype,long contentid)
			throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ContentFile.class);
		detachedCriteria.add(Restrictions.eq("type", type));
		if(!filetype.equals(""))
			detachedCriteria.add(Restrictions.eq("fileType", filetype));
		detachedCriteria.add(Restrictions.eq("contentId", contentid));
		return hibernateTemplate.getEntitiesByDetachedCriteria(detachedCriteria);
	}
	@Override
	public List<ContentFile> getListforFileName(String saveName, String type)
			throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ContentFile.class);
		detachedCriteria.add(Restrictions.eq("type", type));
		detachedCriteria.add(Restrictions.eq("saveName", saveName));
		return hibernateTemplate.getEntitiesByDetachedCriteria(detachedCriteria);
	}
	/**
	 * 
	 * @param 删除附件
	 * @param 
	 * @return
	 * @throws Throwable
	 */
	@Override
	public String deleteFiles(List<ContentFile> listContentFiles)
			throws Throwable {
		
		// TODO Auto-generated method stub
		//将取得的文件id拼接为字符串，只打开一次
		String idString = "";
		Integer count = listContentFiles.size();
		if (count>0) {
			for (int i = 0; i < count; i++) {
				if (i==count-1) {
					idString+=String.valueOf(listContentFiles.get(i).getId());
				}else{
				idString+=String.valueOf(listContentFiles.get(i).getId())+",";
				}
			}
			System.out.println(idString+"================================="+"contentfiles");
			hibernateTemplate.executeSql("delete from r_tab_content_file where id in("+idString+")", null);
		}
			
			//采用级联的设置，可以直接删除
		
		return "success";
	}

}
