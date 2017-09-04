/**
 * 项目名称: 草原文化创意资源平台
 * 文件名     : SlideshowTypeAction.java
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
package com.menksoft.cms.manage.slideshow.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menksoft.cms.manage.slideshow.entity.SlideshowType;
import com.menksoft.cms.manage.slideshow.service.SlideshowTypeService;
import com.menksoft.util.Const;



/**
 * @author wangh
 * 幻灯片类型控制器类，负责幻灯片类型各类业务处理的控制转发。
 */
@Controller
public class SlideshowTypeAction {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private SlideshowTypeService slideshowTypeService;
	
	/**
	 * 跳转到幻灯片类型列表页面
	 * @param modelMap 保存幻灯片类型列表信息
	 * @return 幻灯片类型列表页面地址
	 */
	@RequestMapping(value="/manage/slideshow/type/list", method=RequestMethod.GET)
	public String forwardToSlideshowTypeListPage(ModelMap modelMap) {
		String forwardPage = "manage/slideshow/slideshowTypeList.jsp";
		try {
			List<SlideshowType> list = slideshowTypeService.getAllSlideshowType();
			modelMap.put("slideshowTypeList", list);
		} catch (Throwable e) {
			log.error("幻灯片类型列表"+Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
			modelMap.put(Const.MESSAGE_NAME, e);
			//TODO 错误处理，需要跳转到公共错误页面。
		}
		
		return forwardPage;
	}
	
	/**
	 * 跳转到幻灯片类型添加页面
	 * @return 幻灯片类型添加页面地址
	 */
	@RequestMapping(value="/manage/slideshow/type/add", method=RequestMethod.GET)
	public String forwardToSlideshowTypeAddPage() {
		return "manage/slideshow/slideshowTypeAdd.jsp";
	}
	
	/**
	 * 跳转到幻灯片类型编辑页面
	 * @param slideshowType 根据列表页面封装的幻灯片类型对象
	 * @param modelMap 保存幻灯片类型信息
	 * @return 幻灯片类型编辑页面地址
	 */
	@RequestMapping(value="/manage/slideshow/type/edit/{id}", method=RequestMethod.GET)
	public String forwardToSlideshowTypeEditPage(@PathVariable String id, ModelMap modelMap) {
		String forwardPage = "manage/slideshow/slideshowTypeEdit.jsp";
		try {
			Long typeId = new Long(id);
			SlideshowType slideshowType = null;
			slideshowType = slideshowTypeService.getSlideshowTypeById(typeId);
			modelMap.put("slideshowType", slideshowType);
		} catch (Throwable e) {
			log.error("幻灯片类型"+Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
			modelMap.put(Const.MESSAGE_NAME, e);
			//TODO 错误处理，需要跳转到公共错误页面。
		}
		
		return forwardPage;
	}
	
	/**
	 * 保存幻灯片类型
	 * @param slideshowType 幻灯片类型对象
	 * @param modelMap 保存幻灯片类型列表信息
	 * @return 幻灯片类型列表页面地址
	 */
	@RequestMapping(value="/manage/slideshow/type/save", method=RequestMethod.POST)
	public String addSlideshowType(SlideshowType slideshowType, ModelMap modelMap) {
		try {
			slideshowTypeService.save(slideshowType);
			modelMap.put(Const.MESSAGE_NAME, Const.MESSAGE_SUCCESS_SAVE);
		} catch (Throwable e) {
			log.error("幻灯片类型"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
			modelMap.put(Const.MESSAGE_NAME, e);
			return "manage/slideshow/slideshowTypeAdd.jsp";
		}
		return forwardToSlideshowTypeListPage(modelMap);
	}
	
	/**
	 * 更新幻灯片类型
	 * @param slideshowType 幻灯片类型对象
	 * @param modelMap 保存幻灯片类型列表信息
	 * @return 幻灯片类型列表页面地址
	 */
	@RequestMapping(value="/manage/slideshow/type/update", method=RequestMethod.POST)
	public String editSlideshowType(SlideshowType slideshowType, ModelMap modelMap) {
		try {
			slideshowTypeService.update(slideshowType);
			modelMap.put(Const.MESSAGE_NAME, Const.MESSAGE_SUCCESS_SAVE);
		} catch (Throwable e) {
			log.error("幻灯片类型"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
			modelMap.put(Const.MESSAGE_NAME, e);
			return "manage/slideshow/slideshowTypeEdit.jsp";
		}
		return forwardToSlideshowTypeListPage(modelMap);
	}
	
	/**
	 * 批量删除幻灯片类型
	 * @param ids 幻灯片类型Id的数组
	 */
	@RequestMapping(value="/manage/slideshow/type/delete/{ids}", method=RequestMethod.GET)
	public @ResponseBody void deleteSlideshowType(@PathVariable String ids) {
		Assert.hasText(ids, "删除幻灯片类型失败：给定的唯一标识无效。");
		
		String[] idArray = ids.split(",");
		try {
			slideshowTypeService.deleteBatch(idArray);
		} catch (Throwable e) {
			log.error("幻灯片类型"+Const.MESSAGE_FAIL_UNCHECKED_DELETE, e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 判断是否存在给定名称的幻灯片类型
	 * @param name 幻灯片名称
	 * @return 如果存在，返回true；否则返回false；执行过程中发生错误时，返回true。
	 */
	@RequestMapping(value="/manage/slideshow/type/isexist", method=RequestMethod.GET)
	public @ResponseBody boolean isExist(SlideshowType slideshowType) {
		return slideshowTypeService.isExist(slideshowType);
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request,  
	        ServletRequestDataBinder binder) throws Exception {  
	    binder.registerCustomEditor(String.class,"id",new CustomNumberEditor(Long.class,false));
	}  
}
