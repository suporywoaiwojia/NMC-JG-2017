/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : WebsiteServiceImpl.java
 * 描述        : 会员管理
 * 作者        : 李彦佑
 * 日期        : 2012-11-14上午11:21:23
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1   2012-11-14  李彦佑
 */
package com.menksoft.cms.manage.website.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.website.entity.Website;
import com.menksoft.cms.manage.website.entity.behavior.WebsiteBehavior;
import com.menksoft.cms.manage.website.service.WebsiteService;
import com.menksoft.util.Const;
import com.menksoft.util.ValidateUtil;

/**
 * @author lyy
 *
 */
@Service("WebsiteService")
public class WebsiteServiceImpl implements WebsiteService {

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.website.service.WebsiteService#getAllWebsites(java.lang.Integer, java.lang.Integer)
	 */
	@Resource
	private WebsiteBehavior websiteBehavior;

	
	private Logger log = Logger.getLogger(this.getClass());
	@Override
	public PagingTools<Website> getAllWebsites(Integer startIndex, Integer count)
			throws Throwable {
	PagingTools<Website> pagingtools=null;
	startIndex*=PagingTools.PAGER_ECORD_DEFAULT;
	try {
		pagingtools =websiteBehavior.getAllWebsites(startIndex, PagingTools.PAGER_ECORD_DEFAULT);
	} catch (Throwable e) {
		log.error("站点信息"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
		throw new RuntimeException("站点信息"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
	}
	return pagingtools;
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.website.service.WebsiteService#save(com.menksoft.cms.manage.website.entity.Website)
	 */
	@Override
	public void save(Website website) throws Throwable {
		//必填项判断
		Assert.hasText(website.getName(), website.getName()+Const.MESSAGE_FAIL_SAVE_ISNULL);
		//唯一校验判断
//		Assert.isTrue(checkRepeat(website,"insert"), website.getName()+Const.MESSAGE_FAIL_EXISTED);
		//特殊字符校验
		Assert.isTrue(!ValidateUtil.nameSpecialCharacters(website.getName()),Const.MESSAGE_FAIL_VALIDATE_NAMESPECIALCHARACTERS);
		try {
			websiteBehavior.saveWebsite(website);
		} catch (Throwable e) {
			log.error("站点信息"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
			throw new RuntimeException("站点信息"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
		}
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.website.service.WebsiteService#update(com.menksoft.cms.manage.website.entity.Website)
	 */
	@Override
	public void update(Website website) throws Throwable {
		//必填项判断
//				Assert.hasText(website.getName(), website.getName()+Const.MESSAGE_FAIL_SAVE_ISNULL);
//				try {
//					websiteBehavior.updateWebsite(website);
//				} catch (Throwable e) {
//					log.error("站点信息"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
//					throw new RuntimeException("站点信息"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
//				}
		websiteBehavior.updateWebsite(website);
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.website.service.WebsiteService#deleteById(java.lang.String)
	 */
	@Override
	public void deleteById(String id) throws Throwable {
		try {
			Map<String,Object>  deleteMap= new HashMap<String, Object>();
			String[] ids=id.split(",");
			for(int i=0;i<ids.length;i++){
				deleteMap.put("id", Long.valueOf(ids[i]));
			}
		
			websiteBehavior.deleteWebsites(deleteMap);
		} catch (Throwable e) {
			log.error("站点信息"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED, e);
			throw new RuntimeException("站点信息"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED, e);
		}
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.website.service.WebsiteService#getWebsiteById(long)
	 */
	@Override
	public Website getWebsiteById(long id) throws Throwable {
		Website website=null;
		try{
			website=websiteBehavior.getWebsiteById(id);
			
		}catch(Throwable e){
			log.error("站点信息查询"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			throw new RuntimeException("站点信息"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
		}
		return website;
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.website.service.WebsiteService#isExist(java.lang.String)
	 */
	@Override
	public boolean isExist(String name) {
		// TODO 未实现的方法
		return false;
	}
	
	public boolean checkRepeat(Website website,String flag)  throws Throwable{
		List<Website> list=null;
		boolean isExist=true;
		
		if(flag.equals("insert"))
			list=websiteBehavior.getWebsitesByName(website.getName());
		if(flag.equals("update"))
			list=websiteBehavior.getWebsiteByNameAndId(website.getName(),website.getId());
		if(list.size()>0)
			isExist=false;			
		return isExist;
	}

	
	/**
	 * @param path 附件路径
	 * @param list保留数据集合
	 */
	public void delatt(String path,List<?> list){
		// path=Const.APP_ROOT+"website/www/contentDoc/";
		File Att = getFile(path);
		
		List<String> attlist=new ArrayList<String>();//需要删除的封面集合
		Collections.addAll(attlist, Att.list());
		for(int a=0;a<attlist.size();a++){
			for(int b=0;b<list.size();b++){
					if(list.get(b).equals(attlist.get(a)))
						attlist.remove(a);
			}
		}
		for(int p=0;p<attlist.size();p++){
			File file= new File(path+attlist.get(p)); 
			 file.delete();
		}
	}
	public File getFile(String path){
		 File Att = new File(path);
			if (!Att.exists()) {
				Att.mkdirs();
			}
		return Att;
	}

}
