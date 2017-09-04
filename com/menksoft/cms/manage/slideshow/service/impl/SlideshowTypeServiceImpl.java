/**
 * 项目名称: 草原文化创意资源平台
 * 文件名     : SlideshowTypeServiceImpl.java
 * 描述          : 
 * 作者          : 王弘
 * 日期          : 2012/11/13
 * 公司          : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *     1     2012/11/13    王弘               新建文件
 */
package com.menksoft.cms.manage.slideshow.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.menksoft.cms.manage.slideshow.entity.SlideshowType;
import com.menksoft.cms.manage.slideshow.entity.behavior.SlideshowTypeBehavior;
import com.menksoft.cms.manage.slideshow.service.SlideshowTypeService;
import com.menksoft.util.Const;
import com.menksoft.util.ValidateUtil;



/**
 * @author wangh
 * 幻灯片类型的业务类，负责实现幻灯片类型模块的各类业务操作。
 */
@Service("slideshowTypeService")
public class SlideshowTypeServiceImpl implements SlideshowTypeService {
	@Resource
	private SlideshowTypeBehavior slideshowTypeBehavior;
	
	/* (non-Javadoc)
	 * @see com.anda.cms.manage.slideshow.entity.service.impl.SlideshowTypeService#save(com.anda.cms.manage.slideshow.entity.SlideshowType)
	 */
	@Override
	public void save(SlideshowType slideshowType) throws Throwable {
		Assert.notNull(slideshowType, Const.MESSAGE_FAIL_ILLEGAL_ARGUMENT);
		String name = slideshowType.getName();
		Assert.hasText(name, Const.MESSAGE_FAIL_SAVE_ISNULL + "类型名称。");
		Assert.isTrue(!ValidateUtil.nameSpecialCharacters(slideshowType.getName()),"类型名称"+Const.MESSAGE_FAIL_VALIDATE_NAMESPECIALCHARACTERS);
		Assert.isTrue(slideshowType.getId() == 0, slideshowType.getName() + Const.MESSAGE_FAIL_EXISTED);
		Assert.isTrue(!slideshowTypeBehavior.isExist(slideshowType), name + Const.MESSAGE_FAIL_EXISTED);
		
		slideshowType.setCreatdate(new Date());
		
		try {
			slideshowTypeBehavior.saveSlideshowType(slideshowType);
		} catch (Throwable e) {
			throw new RuntimeException("幻灯片类型"+Const.MESSAGE_FAIL_UNCHECKED_SAVE);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.anda.cms.manage.slideshow.entity.service.impl.SlideshowTypeService#update(com.anda.cms.manage.slideshow.entity.SlideshowType)
	 */
	@Override
	public void update(SlideshowType slideshowType) throws Throwable {
		Assert.notNull(slideshowType, Const.MESSAGE_FAIL_ILLEGAL_ARGUMENT);
		String name = slideshowType.getName();
		Assert.hasText(name, Const.MESSAGE_FAIL_SAVE_ISNULL + "类型名称。");
		Assert.isTrue(!ValidateUtil.nameSpecialCharacters(slideshowType.getName()),"类型名称"+Const.MESSAGE_FAIL_VALIDATE_NAMESPECIALCHARACTERS);
		Assert.isTrue(slideshowType.getId() > 0, slideshowType.getName()+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED);
		Assert.isTrue(!slideshowTypeBehavior.isExist(slideshowType), name + Const.MESSAGE_FAIL_EXISTED);
		
		try {
			SlideshowType oldSlideshowType = slideshowTypeBehavior.getSlideshowTypeById(slideshowType.getId());
			oldSlideshowType.setName(name);
			oldSlideshowType.setScript(slideshowType.getScript());
			slideshowTypeBehavior.updateSlideshowType(oldSlideshowType);
		} catch (Throwable e) {
			throw new RuntimeException("幻灯片类型"+Const.MESSAGE_FAIL_UNCHECKED_SAVE);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.anda.cms.manage.slideshow.entity.service.impl.SlideshowTypeService#deleteById(java.io.Serializable)
	 */
	@Override
	public void deleteById(Serializable id) throws Throwable {
		Assert.notNull(id, "幻灯片类型"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED);
		
		try {
			slideshowTypeBehavior.deleteSlideshowTypeById(id);
		} catch (Throwable e) {
			throw new RuntimeException("幻灯片类型"+Const.MESSAGE_FAIL_UNCHECKED_DELETE);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.anda.cms.manage.slideshow.entity.service.impl.SlideshowTypeService#deleteBatch(java.io.Serializable[])
	 */
	@Override
	public void deleteBatch(Serializable[] ids) throws Throwable {
		Assert.notEmpty(ids, "幻灯片类型"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED);
		
		try {
			Map<String, Object> properties = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer("delete from R_TAB_SLIDESHOW_TYPE where ");
			int length = ids.length;
			for(int i=0; i<length; i++) {
				properties.put("id"+i, ids[i]);
				hql.append("id = :id"+i);
				hql.append(" or ");
			}
			
			hql.delete(hql.length()-4, hql.length());
			
			slideshowTypeBehavior.updateBySql(hql.toString(), properties);
		} catch (Throwable e) {
			throw new RuntimeException("幻灯片类型"+Const.MESSAGE_FAIL_UNCHECKED_DELETE);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.anda.cms.manage.slideshow.entity.service.impl.SlideshowTypeService#getAllSlideshowType()
	 */
	@Override
	public List<SlideshowType> getAllSlideshowType() throws Throwable {
		try {
			return slideshowTypeBehavior.getAllSlideshowType();
		} catch (Throwable e) {
			throw new RuntimeException("幻灯片类型"+Const.MESSAGE_FAIL_UNCHECKED_QUERY);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.anda.cms.manage.slideshow.entity.service.impl.SlideshowTypeService#getSlideshowTypeById(Serializable)
	 */
	@Override
	public SlideshowType getSlideshowTypeById(Serializable id) throws Throwable {
		Assert.notNull(id, Const.MESSAGE_FAIL_ILLEGAL_ARGUMENT);
		try {
			return slideshowTypeBehavior.getSlideshowTypeById(id);
		} catch (Throwable e) {
			throw new RuntimeException("幻灯片类型"+Const.MESSAGE_FAIL_UNCHECKED_QUERY);
		}
	}
	
	/* (non-Javadoc)
//	 * @see com.anda.cms.manage.slideshow.entity.service.impl.SlideshowTypeService#isExist(String)
	 */
	@Override
	public boolean isExist(SlideshowType slideshowType) {
		return slideshowTypeBehavior.isExist(slideshowType);
	}
}