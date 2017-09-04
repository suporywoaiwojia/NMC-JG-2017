/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : SlideshowAction.java
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

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menksoft.cms.manage.slideshow.entity.Slideshow;
import com.menksoft.cms.manage.slideshow.entity.SlideshowFile;
import com.menksoft.cms.manage.slideshow.service.SlideshowService;
import com.menksoft.util.Const;
import com.menksoft.util.CopyFileTool;

/**
 * @author wangh
 * 幻灯片控制器类，负责幻灯片各类业务处理的控制转发。
 */
@Controller
public class SlideshowAction {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private SlideshowService slideshowService;


	/**
	 * 跳转到幻灯片列表页面
	 * @param modelMap 保存幻灯片列表信息
	 * @return 幻灯片列表页面地址
	 */
	@RequestMapping(value="/manage/slideshow/list", method=RequestMethod.GET)
	public String forwardToSlideshowListPage(ModelMap modelMap) {
		String forwardPage = "manage/slideshow/slideshowList.jsp";
		try {
			List<Slideshow> list = slideshowService.getAllSlidesshow();
			modelMap.put("slideshowList", list);
		} catch (Throwable e) {
			log.error("幻灯片列表"+Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
			modelMap.put(Const.MESSAGE_NAME, e);
			//TODO 错误处理，需要跳转到公共错误页面。
		}
		
		return forwardPage;
	}
	
	/**
	 * 跳转到幻灯片添加页面
	 * @return 幻灯片添加页面地址
	 */
	@RequestMapping(value="/manage/slideshow/add", method=RequestMethod.GET)
	public String forwardToSlideshowAddPage(ModelMap modelMap) {
		try {
		} catch (Throwable e) {
			log.error("幻灯片类型"+Const.MESSAGE_FAIL_UNCHECKED_QUERY);
			modelMap.put(Const.MESSAGE_NAME, e);
		}
		return "manage/slideshow/slideshowNew.jsp";
	}
	
	/**
	 * 跳转到幻灯片编辑页面
	 * @param slideshowType 根据列表页面封装的幻灯片对象
	 * @param modelMap 保存幻灯片信息
	 * @return 幻灯片编辑页面地址
	 */
	@RequestMapping(value="/manage/slideshow/edit/{id}", method=RequestMethod.GET)
	public String forwardToSlideshowEditPage(@PathVariable("id")String id, ModelMap modelMap) {
		String forwardPage = "manage/slideshow/slideshowEdit.jsp";
		try {
			Long slideshowId = new Long(id);
			Slideshow slideshow = null;
			slideshow = slideshowService.getSlideshowById(slideshowId);
			modelMap.put("slideshow", slideshow);
		} catch (Throwable e) {
			log.error("幻灯片信息"+Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
			modelMap.put(Const.MESSAGE_NAME, e);
			//TODO 错误处理，需要跳转到公共错误页面。
		}
		
		return forwardPage;
	}
	
	/**
	 * 保存幻灯片
	 * @param slideshow 幻灯片对象
	 * @param modelMap 保存幻灯片列表信息
	 * @return 幻灯片列表页面地址
	 */
	@RequestMapping(value="/manage/slideshow/save", method=RequestMethod.POST)
	public @ResponseBody long addSlideshow(HttpServletRequest request, ModelMap modelMap) {
		Slideshow slideshow =null;
		long msg=0;
		try {
			slideshow = buildSlideshow(request);
			slideshowService.saveSlideshow(slideshow);
			msg=slideshow.getId();
		} catch (Throwable e) {
			log.error("幻灯片信息"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
		}
		return msg;
	}
	
	/**
	 * 更新幻灯片类型
	 * @param slideshowType 幻灯片类型对象
	 * @param modelMap 保存幻灯片类型列表信息
	 * @return 幻灯片类型列表页面地址
	 */
	@RequestMapping(value="/manage/slideshow/update", method=RequestMethod.POST)
	public @ResponseBody String editSlideshow(HttpServletRequest request, ModelMap modelMap) {
		Slideshow slideshow =null;
		String msg="0";
		try {
			 slideshow = buildSlideshow(request);
			 slideshowService.updateSlideshow(slideshow);
			 msg="1";
		} catch (Throwable e) {
			log.error("幻灯片信息"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
		}
		return msg;
	}
	
	/**
	 * 批量删除幻灯片
	 * @param ids 幻灯片Id的数组
	 */
	@RequestMapping(value="/manage/slideshow/delete/{ids}", method=RequestMethod.GET)
	public @ResponseBody String deleteSlideshow(@PathVariable String ids) {
		//Assert.hasText(ids, "幻灯片信息"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED);
		String msg="0";
		//String[] idArray = ids.split(",");
		try {
			slideshowService.deleteBatch(ids);
			msg="1";
		} catch (Throwable e) {
			log.error("幻灯片信息"+Const.MESSAGE_FAIL_UNCHECKED_DELETE, e);
			//throw new RuntimeException(e);
		return msg;
		}
	    return msg;
	}
	
	protected Slideshow buildSlideshow(HttpServletRequest request) {
		
	//	System.out.println("---------------------------");
		//获取幻灯片名称
		long slideshowId = ServletRequestUtils.getLongParameter(request, "id", 0);
		//获取幻灯片名称
		String slideshowName = ServletRequestUtils.getStringParameter(request, "name", "");
		//获取幻灯片图片文件名称
		String[] filesName = ServletRequestUtils.getStringParameters(request, "filesName");
		//获取幻灯片图片文件路径
		String[] filesUploadPath = ServletRequestUtils.getStringParameters(request, "filesUploadPath");
		//获取幻灯片图片文件路径
		String[] filesHttpPath = ServletRequestUtils.getStringParameters(request, "filesHttpPath");
		//获取幻灯片图片高度
		int[] heights = null; 
		try {
			heights = ServletRequestUtils.getIntParameters(request, "heights");
			if(heights.length == 0) {
				heights = new int[filesName.length];
			}
		} catch (Throwable e) {
			heights = new int[filesName.length];
			//throw new RuntimeException("图片高度"+Const.MESSAGE_FAIL_VALIDATE_POSITIVE_INTEGER);
		}
		//获取幻灯片图片宽度
		int[] widths = null;
		try {
			widths = ServletRequestUtils.getIntParameters(request, "widths");
			if(widths.length == 0) {
				widths = new int[filesName.length];
			}
		} catch (Throwable e) {
			widths = new int[filesName.length];
			//throw new RuntimeException("图片宽度"+Const.MESSAGE_FAIL_VALIDATE_POSITIVE_INTEGER);
		}
		//获取幻灯片图片链接地址
		String[] links = ServletRequestUtils.getStringParameters(request, "links");
		//获取幻灯片图片描述
		String[] describes = ServletRequestUtils.getStringParameters(request, "describes");
		//System.out.println(slideshowName+"----------"+"============================"+ServletRequestUtils.getStringParameters(request, "filesHttpPath"));
		
		List<SlideshowFile> slideshowFiles = new ArrayList<SlideshowFile>(); 
		int length = filesName.length;
		for(int i=0; i<length; i++) {
			//对保存的数据，复制到最终的目录中
			String filePathTemp = filesUploadPath[i];
			String filePath =filePathTemp.replaceFirst("temp", "slideshow");
			//变换路径
			File fileSour = new File(filePathTemp);
			File fileDes = new File(filePath);
			CopyFileTool.fileChannelCopy(fileSour,fileDes);
			
			String httpPath = filesHttpPath[i];
			String fileName = filesName[i];
			int height = heights[i];
			int width = widths[i];
			String link = links[i];
			String detailed = describes[i];
			
			if(filePath != null && !filePath.equals("")) {
				SlideshowFile slideshowFile = new SlideshowFile();
				slideshowFile.setFilepath(filePath);
				
				slideshowFile.setHttppath(httpPath);
				slideshowFile.setFilename(fileName);
				slideshowFile.setHeight(height);
				slideshowFile.setWidth(width);
				slideshowFile.setLink(link);
				slideshowFile.setDetailed(detailed);
				
				slideshowFiles.add(slideshowFile);
			}
		}
		
		Slideshow slideshow = new Slideshow();
		slideshow.setId(slideshowId);
		slideshow.setName(slideshowName);
		slideshow.setCreatdate(new Date());
		slideshow.setSlideshowFiles(slideshowFiles);
		return slideshow;
	}
}
