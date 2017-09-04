package com.menksoft.cms.manage.website.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.website.entity.Links;
import com.menksoft.cms.manage.website.entity.behavior.LinkBehavior;
import com.menksoft.cms.manage.website.service.LinksService;
import com.menksoft.util.Const;
import com.menksoft.util.ValidateUtil;

/**
 * 链接管理
 * @author Liyy
 *
 */
@Service("LinksService")
public class LinksServiceImpl implements LinksService{

	@Resource
	private LinkBehavior linkBehavior;
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public void save(Links link) throws Throwable {
		//必填项判断
		Assert.hasText(link.getName(), link.getName()+Const.MESSAGE_FAIL_SAVE_ISNULL);
		//唯一校验判断
		Assert.isTrue(checkRepeat(link,"insert"), link.getName()+Const.MESSAGE_FAIL_EXISTED);
		//特殊字符校验
		Assert.isTrue(!ValidateUtil.nameSpecialCharacters(link.getName()),Const.MESSAGE_FAIL_VALIDATE_NAMESPECIALCHARACTERS);
		try {
			linkBehavior.saveLink(link);
		} catch (Throwable e) {
			log.error("链接信息"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
			throw new RuntimeException("链接信息"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
		}
	}

	@Override
	public void update(Links link) throws Throwable {
		//必填项判断
		Assert.hasText(link.getName(), link.getName()+Const.MESSAGE_FAIL_SAVE_ISNULL);
		try {
			Links linkOld=linkBehavior.getLinkById(link.getId());
			linkOld.setName(link.getName());
			linkOld.setId(link.getId());
			linkOld.setDetailed(link.getDetailed());
			linkOld.setFilePath(link.getFilePath());
			linkOld.setLink(link.getLink());
			linkBehavior.updateLink(linkOld);
		} catch (Throwable e) {
			log.error("链接信息"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
			throw new RuntimeException("链接信息"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
		}
		
	}

	@Override
	public void deleteById(String id) throws Throwable {
		try {
			linkBehavior.deleteLinks(id);
		} catch (Throwable e) {
			log.error("链接信息"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED, e);
			throw new RuntimeException("链接信息"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED, e);
		}
	}

	@Override
	public Links getLinkById(long id) throws Throwable {
		Links link=null;
		try{
			link=linkBehavior.getLinkById(id);
			
		}catch(Throwable e){
			log.error("链接信息查询"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			throw new RuntimeException("链接"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
		}
		return link;
	}


	/**
	 * 保存查询重复数据
	 * @param name
	 * @return
	 */
	public boolean checkRepeat(Links link,String flag)  throws Throwable{
		List<Links> list=null;
		boolean isExist=true;
		
		if(flag.equals("insert"))
			list=linkBehavior.getLinksByName(link.getName());
		if(flag.equals("update"))
			list=linkBehavior.getLinkByNameAndId(link.getName(), link.getId());
		if(list.size()>0)
			isExist=false;			
		return isExist;
	}

	@Override
	public PagingTools<Links> getAllLinks(Integer startIndex,
			Integer count) throws Throwable {
		PagingTools<Links> pagingtools=null;
		try {
			pagingtools =linkBehavior.getAllLinks(startIndex,count);
		} catch (Throwable e) {
			log.error("链接信息"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			throw new RuntimeException("链接信息"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
		}
		return pagingtools;
	}

	@Override
	public List<Links> getAllLiks() throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(Links.class);
		return linkBehavior.getAllLinks(criteria);
	}

	@Override
	public boolean isExist(String name) {
		return false;
	}

}
