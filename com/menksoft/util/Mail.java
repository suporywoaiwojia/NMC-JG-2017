package com.menksoft.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import com.cloopen.rest.sdk.CCPRestSDK;

public class Mail {
	public static void pushMail(String sid,String mail)throws MessagingException, UnsupportedEncodingException{
		final Properties props = new Properties();
		 // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", getString("mail.smtp"));
        // 发件人的账号
        props.put("mail.user", getString("mail.username"));
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", getString("mail.password"));
//        System.out.println("====="+getString("mail.smtp")+"==="+getString("mail.username"));
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        
        String nick="";
        try {  
            nick=MimeUtility.encodeText("安达文化传媒");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"),nick);
        message.setFrom(form);
        // 设置收件人
        InternetAddress to = new InternetAddress(mail);
        message.setRecipient(RecipientType.TO, to);
     
       

        // 设置邮件标题
        message.setSubject("【草原文化数字化创意资源平台】密码重置");

        // 设置邮件的内容体
        message.setContent("您好：<p><p/>请通过以下链接重置密码:<p><p/><a href='"+Const.BASEPATH+"action/password/reset/"+sid+"'>"+Const.BASEPATH+"/action/password/reset/"+sid+"</a><p><p/>--<p><p/>草原文化数字化创意资源平台<p><p/><a href='http://www.anda9.net'>http://www.anda9.net</a>", "text/html;charset=UTF-8");

        // 发送邮件
        Transport.send(message);

	

		
	}
	
	public static String getString(String key) {
		String propertyFileName="config/spring/applicationMail" ;
	    ResourceBundle resourceBundle;
	    resourceBundle = ResourceBundle.getBundle(propertyFileName); 
        if (key == null || key.equals("") || key.equals("null")) { 
            return ""; 
        } 
        String result = ""; 
        try { 
            result = resourceBundle.getString(key); 
        } catch (MissingResourceException e) { 
            e.printStackTrace(); 
        } 
        return result; 
    }
	
	/**
	 * 发送短信
	 * @param tel
	 * @param type 1注册 2密码找回
	 * @return
	 */
	public static String SmsCodeGet(String tel,String type){
		String msg="";
		String code="";
		HashMap<String, Object> result = null;
		String Template="";//模版ID
		if(type.equals("1"))
			Template=getString("sms.TemplateId_register");
		else if(type.equals("2"))
			Template=getString("sms.TemplateId_find");
		String server=getString("sms.server");
		String port=getString("sms.port");
		String accountSid=getString("sms.accountSid");
		String accountToken=getString("sms.accountToken");
		String AppId=getString("sms.AppId");
		String time=getString("sms.time");//短信有效时间
		
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init(server, port);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(accountSid, accountToken);// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(AppId);// 初始化应用ID
		
		//6位随机数
		code=String.valueOf((int)((Math.random()*9+1)*100000));
		
		result = restAPI.sendTemplateSMS(tel,Template ,new String[]{code,time});

		System.out.println("SDKTestSendTemplateSMS result=" + result);
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
			msg=code;
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
			msg="";
		}
		return msg;
	}

}
