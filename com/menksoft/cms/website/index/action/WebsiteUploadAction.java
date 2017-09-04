package com.menksoft.cms.website.index.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.menksoft.cms.website.index.entity.FileMeta;
import com.menksoft.util.Const;


@Controller
public class WebsiteUploadAction {
	 LinkedList<FileMeta> files = new LinkedList<FileMeta>();
	 FileMeta fileMeta = null;
	 String newFileLongName="";
	 String newFileShortName="";
	 @RequestMapping(value="/web/upload", method = RequestMethod.POST)
	    public @ResponseBody  LinkedList<FileMeta> upload(MultipartHttpServletRequest request, HttpServletResponse response) {
			
	        //1. build an iterator
	         Iterator<String> itr =  request.getFileNames();
	         MultipartFile mpf = null;
	         //2. get each file
	         while(itr.hasNext()){
	 
	             //2.1 get next MultipartFile
	             mpf = request.getFile(itr.next()); 
	             //2.2 if files > 10 remove the first from the list
	             if(files.size() >= 2)
	                 files.pop();
	 
	             //2.3 create new fileMeta
	             fileMeta = new FileMeta();
	             buildNewFileName(mpf.getOriginalFilename());
	             fileMeta.setFileName(mpf.getOriginalFilename());
	             fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
	             fileMeta.setFileType(mpf.getContentType());
	             fileMeta.setFilePath(Const.BASEPATH+"/website/www/contentMember/image/"+newFileLongName);
	             try {
	                fileMeta.setBytes(mpf.getBytes());
	                
	                 // copy file to local disk (make sure the path "e.g. D:/temp/files" exists)            
	                 FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(Const.APP_ROOT+"/website/www/contentMember/image/"+newFileLongName));
	 
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	             //2.4 add to files
	             files.add(fileMeta);
	         }
	        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
	        return files;
	    }
	 
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

}
