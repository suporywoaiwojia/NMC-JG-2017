/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : TemplateUtil.java
 * 描述          : 
 * 作者          : 王弘
 * 日期          : 2012-11-19
 * 公司          : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *     1     2012-11-19       王弘               新建文件
 */
package com.menksoft.cms.manage.webContent.util;

import java.io.File;

import com.menksoft.util.Const;

/**
 * @author wangh 栏目模板和内容模板的工具类。
 */
public class TemplateUtil {
	private static final String COLUMN_TEMPLATE_PATH = Const.APP_ROOT
			+ "WEB-INF/views/template/column/";
	private static final String CONTENT_TEMPLATE_PATH = Const.APP_ROOT
			+ "WEB-INF/views/template/content/";
	private static final String WAP_COLUMN_TEMPLATE_PATH = Const.APP_ROOT
			+ "WEB-INF/views/template/wap_column/";
	private static final String WAP_CONTENT_TEMPLATE_PATH = Const.APP_ROOT
			+ "WEB-INF/views/template/wap_content/";

	public static String[] getColumnTemplateFilesName() {
		File columnTemplateFiles = new File(COLUMN_TEMPLATE_PATH);
		if (!columnTemplateFiles.exists()) {
			columnTemplateFiles.mkdirs();
		}

		return columnTemplateFiles.list();
	}

	public static String[] getContentTemplateFilesName() {
		File contentTemplateFiles = new File(CONTENT_TEMPLATE_PATH);
		if (!contentTemplateFiles.exists()) {
			contentTemplateFiles.mkdirs();
		}

		return contentTemplateFiles.list();
	}

	public static String[] getWapColumnTemplateFilesName() {
		File columnTemplateFiles = new File(WAP_COLUMN_TEMPLATE_PATH);
		if (!columnTemplateFiles.exists()) {
			columnTemplateFiles.mkdirs();
		}

		return columnTemplateFiles.list();
	}

	public static String[] getWapContentTemplateFilesName() {
		File contentTemplateFiles = new File(WAP_CONTENT_TEMPLATE_PATH);
		if (!contentTemplateFiles.exists()) {
			contentTemplateFiles.mkdirs();
		}

		return contentTemplateFiles.list();
	}
}
