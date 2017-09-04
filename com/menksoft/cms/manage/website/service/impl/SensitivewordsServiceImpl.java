/**
 * 项目名称: 草原文化创意资源平台
 * 文件名    : SensitivewordsServiceImpl.java
 * 描述        : 
 * 作者        : 景宏
 * 日期        : 上午10:47:05
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1     10:47:05   景宏
 *    2   2013-01-09   白蒙             增加敏感字查询功能
 */
package com.menksoft.cms.manage.website.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.website.entity.Sensitivewords;
import com.menksoft.cms.manage.website.entity.behavior.SensitivewordsBehavior;
import com.menksoft.cms.manage.website.service.SensitivewordsService;
import com.menksoft.util.Const;
import com.menksoft.util.ValidateUtil;


/**
 * 广告类型管理
 * @author 景宏
 * 敏感词的业务类，负责实现广告模块的各类业务操作。
 */
@Service("sensitivewordsService")
public class SensitivewordsServiceImpl implements SensitivewordsService  {
	private Logger log = Logger.getLogger(this.getClass());
	@javax.annotation.Resource
	private SensitivewordsBehavior sensitivewordsBehavior;
	
	/* (non-Javadoc)
	 * @see com.anda.cms.manage.website.sensitivewords.entity.service.impl.SensitivewordsService#getAllSensitivewords(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PagingTools<Sensitivewords> getAllSensitivewords(Sensitivewords sensitivewords, Integer startIndex, Integer count) throws 
	Throwable {
		PagingTools<Sensitivewords> pagingtools=null;
		DetachedCriteria criteria = DetachedCriteria.forClass(Sensitivewords.class);
		if(sensitivewords.getName()!=null&&!sensitivewords.getName().equals(""))
			criteria.add(Restrictions.ilike("name", sensitivewords.getName(),MatchMode.ANYWHERE));
		try {
			pagingtools =sensitivewordsBehavior.getAllSensitivewords(criteria,startIndex, count);
		} catch (Throwable e) {
			log.error("类敏感词"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			throw new RuntimeException("敏感词"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
		}
		return pagingtools;
	}

	/* (non-Javadoc)
	 * @see com.anda.cms.manage.website.sensitivewords.entity.service.SensitivewordsService#saveSensitivewords(com.anda.cms.manage.website.sensitivewords.entity.Sensitivewords)
	 */
	@Override
	public void saveSensitivewords(Sensitivewords sensitivewords)  throws Throwable{
		//必填项判断
		Assert.hasText(sensitivewords.getName(), sensitivewords.getName()+Const.MESSAGE_FAIL_SAVE_ISNULL);
		//唯一校验判断
		Assert.isTrue(checkRepeat(sensitivewords,"insert"), sensitivewords.getName()+Const.MESSAGE_FAIL_EXISTED);
		//特殊字符校验
		Assert.isTrue(!ValidateUtil.nameSpecialCharacters(sensitivewords.getName()),Const.MESSAGE_FAIL_VALIDATE_NAMESPECIALCHARACTERS);
		try {
			sensitivewordsBehavior.saveSensitivewords(sensitivewords);
		} catch (Throwable e) {
			log.error("敏感词"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
			throw new RuntimeException("敏感词"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
		}
	}
	
	/**
	 * 判断校验
	 * @param sensitivewords	判断对象
	 * @return
	 */
	public String checkValueSet(Sensitivewords sensitivewords)  throws Throwable{
		String msg="";	//错误信息
		if(ValidateUtil.nameSpecialCharacters(sensitivewords.getName()));
		return msg;
	}
	
	/**
	 * 保存查询重复数据
	 * @param name
	 * @return
	 */
	public boolean checkRepeat(Sensitivewords sensitivewords,String flag)  throws Throwable{
		List<Sensitivewords> list=null;
		boolean isExist=true;
		if(flag.equals("insert"))
			list=sensitivewordsBehavior.getSensitivewordsByName(sensitivewords.getName());
		if(flag.equals("update"))
			list=sensitivewordsBehavior.getSensitivewordsByNameAndId(sensitivewords.getName(),sensitivewords.getId());
		if(list.size()>0)
			isExist=false;			
		return isExist;
	}
	
	/**
	 * 根据ID查询
	 * @param id	查询ID数据
	 * @return
	 */
	@Override
	public Sensitivewords getSensitivewordsById(long id)  throws Throwable{
		Sensitivewords sensitivewords=null;
		try {
			sensitivewords=sensitivewordsBehavior.getSensitivewordsById(id);
		} catch (Throwable e) {
			log.error("敏感词"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			throw new RuntimeException("敏感词"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
		}
		return sensitivewords;
	}

	/**
	 * 编辑保存
	 * @param sensitivewords	保存对象
	 */
	@Override
	public void updateSensitivewords(Sensitivewords sensitivewords)  throws Throwable{
		//必填项判断
		Assert.hasText(sensitivewords.getName(), sensitivewords.getName()+Const.MESSAGE_FAIL_SAVE_ISNULL);
		//重复校验
		Assert.isTrue(checkRepeat(sensitivewords,"update"), sensitivewords.getName()+Const.MESSAGE_FAIL_EXISTED);		
		try {
			Sensitivewords sensitivewordsOld=sensitivewordsBehavior.getSensitivewordsById(sensitivewords.getId());
			sensitivewordsOld.setName(sensitivewords.getName());
			sensitivewordsOld.setId(sensitivewords.getId());
			sensitivewordsOld.setRepStr(sensitivewords.getRepStr());
			sensitivewordsBehavior.updateSensitivewords(sensitivewordsOld);
		} catch (Throwable e) {
			log.error("敏感词"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
			throw new RuntimeException("敏感词"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
		}
	}

	/**
	 * 根据ID删除数据
	 * @param id
	 */
	@Override
	public void deleteSensitivewords(String id)  throws Throwable{
		try {	
			sensitivewordsBehavior.deleteSensitivewords(id);
		} catch (Throwable e) {
			log.error("敏感词"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED, e);
			throw new RuntimeException("敏感词"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED, e);
		}
	}

	/* (non-Javadoc)
	 * @see com.anda.website.cms.manage.sensitivewords.service.SensitivewordsService#querySensitivewords()
	 */
	@Override
	public List<Sensitivewords> querySensitivewords() throws Throwable {
		List<Sensitivewords> sensitivewords=null;	
		try {
			sensitivewords= sensitivewordsBehavior.querySensitivewords();
		} catch (Throwable e) {
			log.error("敏感词"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			throw new RuntimeException("敏感词"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
		}
		return sensitivewords;
	}	
}