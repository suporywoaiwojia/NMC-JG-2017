package com.menksoft.cms.website.index.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import com.menksoft.cms.freemarker.FreeMarkerUtil;

public class Util {

	public  String videoPath(long id,String ipaddr){
		String path="";
		
		try {
			String url=FreeMarkerUtil.getString("url")+"vpath/"+id+"/"+ipaddr;
			path=getUrl(url);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	public static String getUrl(String path) throws Throwable {
		String vpath="";
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
            vpath=sTotalString;
        }else{
            System.err.println("失败");
         }
        return vpath;
	}
	
	/**
	 * 获取IP属性电信 联通 移动
	 * @param request
	 * @return
	 */
	public static String getIpaddr(HttpServletRequest request){
		  String ipAddress = request.getHeader("x-forwarded-for");  
          if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
              ipAddress = request.getHeader("Proxy-Client-IP");  
          }  
          if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
              ipAddress = request.getHeader("WL-Proxy-Client-IP");  
          }  
          if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
              ipAddress = request.getRemoteAddr();  
              if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                  //根据网卡取本机配置的IP  
                  InetAddress inet=null;  
                  try {  
                      inet = InetAddress.getLocalHost();  
                  } catch (UnknownHostException e) {  
                      e.printStackTrace();  
                  }  
                  ipAddress= inet.getHostAddress();  
              }  
          }  
          //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
          if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
              if(ipAddress.indexOf(",")>0){  
                  ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
              }  
          }  
//          return ipAddress;   
		
//		  String ip = request.getHeader("x-forwarded-for"); 
//	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
//	           ip = request.getHeader("Proxy-Client-IP"); 
//	       } 
//	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
//	           ip = request.getHeader("WL-Proxy-Client-IP"); 
//	       } 
//	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
//	           ip = request.getRemoteAddr(); 
//	       } 
	    try {
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	    return ipAddress;
	}
	
	public static String getIpaddr(String ip){
		String addr="";
		System.out.println("http://whois.pconline.com.cn/ip.jsp?ip="+ip);
		try {
			addr = Util.getUrl("http://whois.pconline.com.cn/ip.jsp?ip="+ip);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addr;
	}
}
