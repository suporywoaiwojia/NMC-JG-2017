/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : CreatFolder.java
 * 描述        : 创建文件夹
 * 作者        : 呼和
 * 日期        : 下午4:55:54
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       下午4:55:54  lenovo
 */
package com.menksoft.cms.manage.webContent.util;

import java.io.File;

import com.menksoft.util.Const;

/**
 * @author 呼和
 */
public class CreatFolder {
	public static void mkdir1(String mkdirName) {
		try {
			String uploadFilePath = Const.APP_ROOT + "page" + File.separator;
			File dirFile = new File(uploadFilePath + mkdirName);
			boolean bFile = dirFile.exists();

			if (bFile == true) {
				System.out.println("The folder exists.");
			} else {
				System.out
						.println("The folder do not exist,now trying to create a one...");
				bFile = dirFile.mkdir();
				if (bFile == true) {
					System.out.println("Create successfully!");
					System.out.println("创建文件夹");
				} else {
					System.out
							.println("Disable to make the folder,please check the disk is full or not.");
					System.out.println(" 文件夹创建失败，清确认磁盘没有写保护并且空件足够");
				}
			}
		} catch (Exception err) {
			System.err.println("ELS - Chart : 文件夹创建发生异常");
			err.printStackTrace();
		}
	}

}
