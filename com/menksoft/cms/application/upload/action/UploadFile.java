package com.menksoft.cms.application.upload.action;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.menksoft.cms.manage.website.entity.Website;
import com.menksoft.cms.manage.website.service.WebsiteService;
import com.menksoft.util.Watermark;

/**
 * @author Administrator
 * ajax上传附件
 */
@Controller
public class UploadFile {
	 @Resource
	 private WebsiteService websiteService;

	protected Logger log = Logger.getLogger(this.getClass());
	@RequestMapping(value = "/file_upload", method = RequestMethod.POST)
	@ResponseBody public void upload(@RequestParam(value = "file", required = false) MultipartFile file
			,HttpServletRequest request,
			HttpServletResponse response)throws Throwable {
		 String uploadFileName=file.getOriginalFilename();
		
		 String newFileLongName=buildNewFileName(request,uploadFileName);//文件名称
		 String saveFileFullName=buildSaveFileFullName(request,newFileLongName);;
		 String httpFileFullName=buildHttpFileFullName(request,newFileLongName);
	    if (uploadFileName != null && !uploadFileName.equals("")
				&& writeFile(file,saveFileFullName)) {
			response.getOutputStream()
					.write((saveFileFullName + "," + httpFileFullName + "," + newFileLongName)
							.getBytes("UTF-8"));
			//图片增加水印
			Website w=new Website();
			w=websiteService.getWebsiteById(1);
			if(w.getWatermark()!=null&&!w.getWatermark().equals("")&&httpFileFullName.indexOf("img")>0)
				Watermark.pressText(saveFileFullName.replace(newFileLongName, ""), newFileLongName,
						w.getWatermark(), "黑体", Font.BOLD|Font.ITALIC, 40,  Color.gray, 1, 1, 0.3f);
		} else {
			response.getOutputStream().write("0".getBytes());
		}
	}



	/**
	 * 根据上传文件名称构造系统唯一文件名
	 * 
	 * @param originalFileName
	 *            上传文件名称
	 */
	protected String buildNewFileName(HttpServletRequest request,String originalFileName) {
		String newFileLongName="";
		if (originalFileName != null && !originalFileName.equals("")) {
			String ext = "";
			String sequence=request.getParameter("sequence") == null ? ""
					: request.getParameter("sequence");
			int index = originalFileName.lastIndexOf(".");
			if (index != -1) {
				ext = originalFileName.substring(index);
			}
			long timestamp = new Date().getTime();
			String filename=originalFileName.substring(0,index);
			
			if(sequence==null||sequence==""){
				newFileLongName = filename+"_"+timestamp  + ext;
			}else{
				newFileLongName = sequence+"_"+filename+"_"+timestamp  + ext;
			}
			
		}
		return newFileLongName;
	}

	/**
	 * 构造上传文件的磁盘存储完整文件名
	 * 
	 * @param request
	 */
	protected String buildSaveFileFullName(HttpServletRequest request,String newFileLongName) {
		String saveFileFullName="";
		String savePathMode = request.getParameter("savePathMode") == null ? ""
				: request.getParameter("savePathMode");
		System.out.println( savePathMode+"___"+request.getParameter("savePath"));
		String savePath = request.getParameter("savePath") == null ? ""
				: request.getParameter("savePath");
		savePath = (savePath.length() > 0 && (savePath.startsWith("/") || savePath
				.startsWith(File.separator))) ? savePath.substring(1)
				: savePath;

		savePath = (savePath.length() > 0 && (savePath.endsWith("/") || savePath
				.endsWith(File.separator))) ? savePath : savePath
				+ File.separator;

		savePath = savePathMode.equals("") || savePathMode.equals("relative") ? request.getSession().getServletContext().getRealPath("/")
				 + savePath
				: savePath;
		File saveFilePath = new File(savePath);
		if (!saveFilePath.exists()) {
			saveFilePath.mkdirs();
		}

		saveFileFullName = savePath +newFileLongName;
		//System.out.println(saveFileFullName+"===========");
		return saveFileFullName;
	}

	/**
	 * 构造上传文件的网络访问完整文件名
	 * 
	 * @param request
	 */
	protected String  buildHttpFileFullName(HttpServletRequest request,String newFileLongName) {
		String httpFileFullName="";
		String httpPathMode = request.getParameter("httpPathMode") == null ? ""
				: request.getParameter("httpPathMode");
		String httpPath = request.getParameter("httpPath") == null ? ""
				: request.getParameter("httpPath");
		
		httpPath = (httpPath.length() > 0 && (httpPath.startsWith("/") || httpPath
				.startsWith(File.separator))) ? httpPath.substring(1)
				: httpPath;

		httpPath = (httpPath.length() > 0 && (httpPath.endsWith("/") || httpPath
				.endsWith(File.separator))) ? httpPath : httpPath
				+ File.separator;

		httpPath = httpPathMode.equals("") || httpPathMode.equals("relative") ? httpPath
				: httpPath;
		httpFileFullName = httpPath + newFileLongName;
		System.out.println( httpFileFullName+"___"+request.getParameter("httpPath"));
		return httpFileFullName;
	}

	protected boolean writeFile( MultipartFile file,String saveFileFullName) {
		boolean success = false;

		int size = 2048;
		InputStream is = null;
		OutputStream os = null;

		try {
			is = new BufferedInputStream(file.getInputStream(), size);
			os = new BufferedOutputStream(
					new FileOutputStream(saveFileFullName), size);

			int length = 0;
			byte[] b = new byte[size];
			while ((length = is.read(b, 0, size)) != -1) {
				os.write(b, 0, length);
			}
			success = true;
		} catch (Exception e) {
			log.error("保存上传文件失败。", e);
		} finally {
			closeInputStream(is);
			closeOutputStream(os);
		}

		return success;
	}

	private void closeInputStream(InputStream inputStream) {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void closeOutputStream(OutputStream outputStream) {
		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
