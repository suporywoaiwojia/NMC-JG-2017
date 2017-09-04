package com.menksoft.cms.manage.doc2swf;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

import javax.annotation.Resource;

import com.menksoft.cms.freemarker.FreeMarkerUtil;
import com.menksoft.cms.manage.website.entity.Website;
import com.menksoft.cms.manage.website.service.WebsiteService;


public class Doc2swf implements Callable{
	
	
	private static String exc=".swf";
	
	 private static Runtime runtime = Runtime.getRuntime();
	
	

	  private String infile; 

	  public Doc2swf(String infile) { 
              this.infile = infile; 
      } 
	@Override
	public Object call() throws Exception {
		String outFile=infile.substring(0, infile.lastIndexOf("."))+exc;
		try {
			
					
			Website website=FreeMarkerUtil.getWebsite();
				String flashPrinterPath=website.getSwfpath()+"\\p2fServer.exe";
				
				  String command = flashPrinterPath + " " + infile + " " 
			                + " " + outFile;  
				  Process process;
			process = runtime.exec(command);
				process.waitFor();
		        process.destroy();  
		        File outF = new File(outFile);  
		        if(outF.exists()){  
		           System.out.println("docToSwf success.........");  
		        }  
		} catch (IOException  e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}catch (Throwable e1) {
			e1.printStackTrace();
		}
		return outFile;
	}
}
