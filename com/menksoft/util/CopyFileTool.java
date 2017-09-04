package com.menksoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopyFileTool {
	
	  /**
	    * 使用文件通道的方式复制文件
	    * 
	    * @param s
	    *            源文件
	    * @param t
	    *            复制到的新文件
	    */

	    public static String fileChannelCopy(File sourceFile, File desinationFile) {
	    	
            String msg = "0";
	        FileInputStream fi = null;

	        FileOutputStream fo = null;

	        FileChannel in = null;

	        FileChannel out = null;

	        try {

	            fi = new FileInputStream(sourceFile);

                if(!desinationFile.exists()){
                	desinationFile.getParentFile().mkdir();
                	desinationFile.createNewFile(); 
                	
                }
                
	            fo = new FileOutputStream(desinationFile);

	            in = fi.getChannel();//得到对应的文件通道

	            out = fo.getChannel();//得到对应的文件通道

	            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
                 msg="1";
	        } catch (IOException e) {

	            e.printStackTrace();

	        } finally {

	            try {

	                fi.close();

	                in.close();

	                fo.close();

	                out.close();

	            } catch (IOException e) {

	                e.printStackTrace();

	            }

	        }
          return msg;
	    }
}
