package com.menksoft.cms.application.upload.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

@WebServlet(name = "fileUpload", urlPatterns = { "/fileUpload" })
@MultipartConfig
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 5571856107056490674L;

	protected Logger log = Logger.getLogger(this.getClass());

	protected String newFileLongName;
	protected String newFileShortName;
	protected String saveFileFullName;
	protected String httpFileFullName;
	protected String uploadFileName;
	protected Part part;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		convertParameters(request);

		log.debug("uploadFileName:" + uploadFileName);
		log.debug("newFileLongName:" + newFileLongName);
		log.debug("saveFileFullName:" + saveFileFullName);
		log.debug("httpFileFullName:" + httpFileFullName);

		if (uploadFileName != null && !uploadFileName.equals("")
				&& writeFile(part)) {
			response.getOutputStream()
					.write((saveFileFullName + "," + httpFileFullName + "," + newFileLongName)
							.getBytes());
		} else {
			response.getOutputStream().write("0".getBytes());
		}
	}

	protected void convertParameters(HttpServletRequest request) {
		parseFileName(request);
		buildNewFileName(uploadFileName);
		buildSaveFileFullName(request);
		buildHttpFileFullName(request);
	}

	/**
	 * 从request中解析上传文件名称
	 * 
	 * @param request
	 */
	protected void parseFileName(HttpServletRequest request) {
		// 获取上传文件组件名称
		String fileElementName = request.getParameter("fileElementName");
		fileElementName = fileElementName == null ? "Filedata"
				: fileElementName;
		try {
			part = request.getPart(fileElementName);
			if (part != null) {
				String header = part.getHeader("content-disposition");
				uploadFileName = header.substring(
						header.lastIndexOf("filename=") + 10,
						header.length() - 1);
			}
		} catch (Exception e) {
			log.error("解析上传文件失败。", e);
		}
	}

	/**
	 * 根据上传文件名称构造系统唯一文件名
	 * 
	 * @param originalFileName
	 *            上传文件名称
	 */
	protected void buildNewFileName(String originalFileName) {
		if (originalFileName != null && !originalFileName.equals("")) {
			String ext = "";
			int index = originalFileName.lastIndexOf(".");
			if (index != -1) {
				ext = originalFileName.substring(index);
			}
			long timestamp = new Date().getTime();
			String uuid = UUID.randomUUID().toString().substring(24, 36);

			newFileLongName = timestamp + "_" + uuid + ext;
			newFileShortName = timestamp + "_" + uuid;
			
		}
	}

	/**
	 * 构造上传文件的磁盘存储完整文件名
	 * 
	 * @param request
	 */
	protected void buildSaveFileFullName(HttpServletRequest request) {
		String savePathMode = request.getParameter("savePathMode") == null ? ""
				: request.getParameter("savePathMode");
		String savePath = request.getParameter("savePath") == null ? ""
				: request.getParameter("savePath");

		savePath = (savePath.length() > 0 && (savePath.startsWith("/") || savePath
				.startsWith(File.separator))) ? savePath.substring(1)
				: savePath;

		savePath = (savePath.length() > 0 && (savePath.endsWith("/") || savePath
				.endsWith(File.separator))) ? savePath : savePath
				+ File.separator;

		savePath = savePathMode.equals("") || savePathMode.equals("relative") ? getServletContext()
				.getRealPath("/") + savePath
				: savePath;

		File saveFilePath = new File(savePath);
		if (!saveFilePath.exists()) {
			saveFilePath.mkdirs();
		}

		saveFileFullName = savePath + newFileLongName;
	}

	/**
	 * 构造上传文件的网络访问完整文件名
	 * 
	 * @param request
	 */
	protected void buildHttpFileFullName(HttpServletRequest request) {
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

		httpPath = httpPathMode.equals("") || httpPathMode.equals("relative") ? getServletContext()
				.getAttribute("basePath") + httpPath
				: httpPath;
		
		httpFileFullName = httpPath + newFileShortName;
	}

	protected boolean writeFile(Part part) {
		boolean success = false;

		int size = 2048;
		InputStream is = null;
		OutputStream os = null;

		try {
			is = new BufferedInputStream(part.getInputStream(), size);
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
