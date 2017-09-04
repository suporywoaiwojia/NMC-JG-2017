package com.menksoft.cms.timer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;


import org.apache.log4j.Logger;

import com.menksoft.cms.freemarker.FreeMarkerUtil;

/**
 * @author 呼和
 */
public class TimerPlan{
	private final Logger log = Logger.getLogger(getClass());
	
	
	public void getDayTop() {
		try {
			String path=FreeMarkerUtil.getString("url")+"dayTop/T";
			//生成日排行
			//getUrl(path);
			//同时自动生成页面
			//getUrl(FreeMarkerUtil.getString("url")+"build/allpage");
			//log.warn("获取内容日点击量成功。" +new Date());
		} catch (Throwable e) {
			 //log.warn("获取内容日点击量失败。" +new Date());
			e.printStackTrace();
		}
	}
	public void getWeekTop() {
		try {
			String path=FreeMarkerUtil.getString("url")+"weekTop/T";
			getUrl(path);
			log.warn("获取内容周点击量成功。" +new Date());
		} catch (Throwable e) {
			 log.warn("获取内容周点击量失败。" +new Date());
			e.printStackTrace();
		}
	}
	public void getMonthTop() {
		try {
			String path=FreeMarkerUtil.getString("url")+"monthTop/T";
			getUrl(path);
			log.warn("获取内容月点击量成功。" +new Date());
		} catch (Throwable e) {
			 log.warn("获取内容月点击量失败。" +new Date());
			e.printStackTrace();
		}
	}
	
	
	public void getUrl(String path) throws Throwable {
		URL url = new URL(path);//远程url
		  URLConnection URLconnection = url.openConnection();  
        HttpURLConnection httpConnection = (HttpURLConnection)URLconnection;  
        int responseCode = httpConnection.getResponseCode();  
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.err.println("成功");
            InputStream urlStream = httpConnection.getInputStream();  
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlStream));  
            String sCurrentLine = "";  
            String sTotalString = "";  
            while ((sCurrentLine = bufferedReader.readLine()) != null) {  
                sTotalString += sCurrentLine;  
            }  
            System.err.println(sTotalString);
               //假设该url页面输出为"OK"  
            log.warn("获取内容点击量成功。" +new Date());
           if (sTotalString.equals("OK")) {
           } else {
            }  
        }else{
      	  log.warn("获取内容点击量失败。" +new Date());
            System.err.println("失败");
         }
	}
}

