/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : SlideshowServiceImpl.java
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

import com.menksoft.cms.manage.slideshow.entity.Slideshow;
import com.menksoft.cms.manage.slideshow.entity.SlideshowFile;
import com.menksoft.cms.manage.slideshow.entity.behavior.SlideshowBehavior;
import com.menksoft.cms.manage.slideshow.entity.behavior.SlideshowFileBehavior;
import com.menksoft.cms.manage.slideshow.service.SlideshowService;
import com.menksoft.util.Const;
import com.menksoft.util.ValidateUtil;

/**
 * @author wangh
 * 幻灯片的业务类，负责实现幻灯片模块的各类业务操作。
 */
@Service("slideshowService")
public class SlideshowServiceImpl implements SlideshowService {
	@Resource
	private SlideshowBehavior slideshowBehavior;
	@Resource
	private SlideshowFileBehavior slideshowFileBehavior;
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.service.impl.SlideshowService#saveSlideshow(com.menksoft.cms.manage.slideshow.entity.Slideshow)
	 */
	@Override
	public void saveSlideshow(Slideshow slideshow) throws Throwable {
		Assert.notNull(slideshow, Const.MESSAGE_FAIL_ILLEGAL_ARGUMENT);
		Assert.notNull(slideshow.getSlideshowFiles(), Const.MESSAGE_FAIL_ILLEGAL_ARGUMENT);
		Assert.isTrue(slideshow.getId() == 0, slideshow.getName()+Const.MESSAGE_FAIL_EXISTED);
//		Assert.isTrue(!ValidateUtil.nameSpecialCharacters(slideshow.getName()), "幻灯名称"+Const.MESSAGE_FAIL_VALIDATE_NAMESPECIALCHARACTERS);
		
		try {
			slideshow.setCreatdate(new Date());
			slideshowBehavior.saveSlideshow(slideshow);
			
			List<SlideshowFile> slideshowFiles = slideshow.getSlideshowFiles();
			for(SlideshowFile slideshowFile : slideshowFiles) {
				slideshowFile.setSlideshow(slideshow);
				slideshowFileBehavior.saveSlideshowFile(slideshowFile);
			}
		} catch(Throwable e) {
			throw new RuntimeException("幻灯片信息"+Const.MESSAGE_FAIL_UNCHECKED_SAVE);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.service.impl.SlideshowService#updateSlideshow(com.menksoft.cms.manage.slideshow.entity.Slideshow)
	 */
	@Override
	public void updateSlideshow(Slideshow slideshow) throws Throwable {
		Assert.notNull(slideshow, Const.MESSAGE_FAIL_ILLEGAL_ARGUMENT);
		Assert.notNull(slideshow.getSlideshowFiles(), Const.MESSAGE_FAIL_ILLEGAL_ARGUMENT);
		Assert.isTrue(slideshow.getId() > 0, slideshow.getName()+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED);
		Assert.isTrue(!ValidateUtil.nameSpecialCharacters(slideshow.getName()), "幻灯名称"+Const.MESSAGE_FAIL_VALIDATE_NAMESPECIALCHARACTERS);
		
		try {
			long id = slideshow.getId();
			Slideshow oldSlideshow = slideshowBehavior.getSlideshowById(id);
			oldSlideshow.setName(slideshow.getName());
		//	oldSlideshow.setType(slideshow.getType());
			slideshowBehavior.updateSlideshow(oldSlideshow);
			
			deleteSlideshowFilesBySlidesId(id);
			List<SlideshowFile> slideshowFiles = slideshow.getSlideshowFiles();
			for(SlideshowFile slideshowFile : slideshowFiles) {
				slideshowFile.setSlideshow(oldSlideshow);
				slideshowFileBehavior.saveSlideshowFile(slideshowFile);
			}
		} catch (Throwable e) {
			throw new RuntimeException("幻灯片信息"+Const.MESSAGE_FAIL_UNCHECKED_SAVE);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.service.impl.SlideshowService#deleteById(java.io.Serializable)
	 */
	@Override
	public void deleteById(Serializable id) throws Throwable {
		Assert.notNull(id, "幻灯片信息"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED);
		
		try {
			slideshowBehavior.deleteSlideshowById(id);
		} catch (Throwable e) {
			throw new RuntimeException("幻灯片信息"+Const.MESSAGE_FAIL_UNCHECKED_DELETE);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.service.impl.SlideshowService#deleteBatch(java.io.Serializable[])
	 */
	@Override
	public void deleteBatch(String ids) throws Throwable {
		//Assert.notEmpty(ids, "幻灯片信息"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED);
		
		try {
			//先删除含有外键的表，然后删除本表相关信息
			
		     String sqlFile = "delete from r_tab_slideshow_file where slideshow_id in("+ids+")";
		     String sql = "delete from r_tab_slideshow where id in("+ids+")";
//			Map<String, Object> properties = new HashMap<String, Object>();
//			StringBuffer hql = new StringBuffer("delete from R_TAB_SLIDESHOW where ");
//			int length = ids.length;
//			for(int i=0; i<length; i++) {
//				properties.put("id"+i, ids[i]);
//				hql.append("id = :id"+i);
//				hql.append(" or ");
//			}
//			
//			hql.delete(hql.length()-4, hql.length());
//			hibernateTemplate.executeSql("delete from r_data_cultural where id in("+id+")", null);
		   slideshowFileBehavior.updateBySql(sqlFile, null);
			slideshowBehavior.updateBySql(sql, null);
			
		} catch (Throwable e) {
			throw new RuntimeException("幻灯片信息"+Const.MESSAGE_FAIL_UNCHECKED_DELETE);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.service.impl.SlideshowService#getSlideshowById(java.io.Serializable)
	 */
	@Override
	public Slideshow getSlideshowById(Serializable id) throws Throwable {
		Assert.notNull(id, "幻灯片信息"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED);
		
		try {
			return slideshowBehavior.getSlideshowById(id);
		} catch (Throwable e) {
			throw new RuntimeException("幻灯片信息"+Const.MESSAGE_FAIL_UNCHECKED_QUERY);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.service.impl.SlideshowService#getAllSlidesshow()
	 */
	@Override
	public List<Slideshow> getAllSlidesshow() throws Throwable {
		try {
			return slideshowBehavior.getAllSlidesshow();
		} catch (Throwable e) {
			throw new RuntimeException("幻灯片信息"+Const.MESSAGE_FAIL_UNCHECKED_QUERY);
		}
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.service.SlideshowService#deleteSlideshowFilesBySlidesId(java.io.Serializable)
	 */
	@Override
	public void deleteSlideshowFilesBySlidesId(Serializable id) throws Throwable {
		String sql = "DELETE FROM R_TAB_SLIDESHOW_FILE WHERE SLIDESHOW_ID = :SLIDESHOW_ID";
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("SLIDESHOW_ID", id);
		
		slideshowBehavior.updateBySql(sql, properties);
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.slideshow.service.SlideshowService#getSlideshowFileByWebsite(long)
	 */
	@Override
	public List<SlideshowFile> getSlideshowFileByWebsite(long slidwshowId)
			throws Throwable {
		return slideshowFileBehavior.getSlideshowFileByWebsite(slidwshowId);
	}
}