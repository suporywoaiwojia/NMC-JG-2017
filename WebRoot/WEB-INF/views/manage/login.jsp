<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="pragma" content="no-cache" /> 
    	<meta http-equiv="cache-control" content="no-cache" /> 
		<meta http-equiv="expires" content="0" />
        
        <title>用户登录</title>
        <link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/login.css">
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${basePath}js/jquery.cookie.js"></script>
  
    </head>

    <body >
	<div style="width:40%; margin:10% 0 0 10%; padding-top:80px; float:left;">
	  <img src="${basePath}ui1.0/images/login_title_chs.png" style="width:100%;">
	  <div style="width:100%; padding-top:80px; float:left; text-align:center; font-size:20px;">
	  <a href="#" style="color:#f36e1f; text-decoration:none; font-weight:bold;">简体中文</a>
	  <span style="color:#f36e1f; text-decoration:none; font-weight:bold;">&nbsp;|&nbsp;</span>
	  <a href="#" class="mongol_write" style="color:#f36e1f; text-decoration:none; font-size:16px;"></a>
	  </div>
	</div>
	<div style="width:350px; height:240px; background:#e7eff5; margin:10% 10% 0 0; padding:80px 30px; float:right; border:solid 15px #ffffff; border-radius:30px;">
	<form id="loginForm" method="post">
    	<table>
    <tr>
      <td style="width:40px; padding-bottom:20px;">账号:</td>
      <td style="padding-bottom:20px;"><input class="easyui-textbox" style="width:300px;height:40px;padding:12px" name="loginId" id="loginId" data-options="required:true,missingMessage:'请输入帐号',iconCls:'icon-man',iconWidth:38"></td>
    </tr>
    <tr>
      <td style="padding-bottom:20px;">密码:</td>
      <td style="padding-bottom:20px;"><input class="easyui-textbox" type="password" style="width:300px;height:40px;padding:12px" id="password" name="password"  data-options="required:true,missingMessage:'请输入密码',iconCls:'icon-lock',iconWidth:38"></td>
    </tr>
    <tr>
      <td style="padding-bottom:40px;"></td>
      <td style="padding-bottom:40px;"><input type="checkbox" id="remember">
    	<span>记住我</span></td>
    </tr>
  </table>
  </form>
  <div>
    <a href="#" style="width:350px; height:40px; text-align:center; border-radius:6px; font-size:14px; line-height:40px; color:#fff; background:#178fe5; display:block; text-decoration:none;" onclick="login()">
	  <span>登&nbsp;录</span>
	</a>
  </div>
</div>
    </body>
	<script type="text/javascript">
	$(function(){
		$("#loginId").focus();
		if($.trim('${param.error}') != ''){
			var exceptionMessage = '${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}';
			var message = '用户名或密码错误。';
			if(exceptionMessage == 'Maximum sessions of 1 for this principal exceeded'){
				message = '用户已在其他地点登录。';
			}
			$.messager.show({
				title:'消息',
				msg:message,
				timeout:3000,
				showType:'fade',
				width:300,
				height:200
			});
		}
		 if ($.cookie("nmgmgzfy") == "true") {
			$("#remember").attr("checked", true);
		
			 $("#loginId").textbox('setValue',$.cookie("loginId"));
			  $("#password").textbox('setValue',$.cookie("password"));
			
   		 }
	})
	function login(){
		$('#loginForm').attr('action', '${basePath}j_spring_security_check');
		var isValid = $("#loginForm").form('validate');
		if(isValid){
			
			if($('#remember').is(':checked')){
				var loginId = $("#loginId").val();
				var password = $("#password").val();
				
				$.cookie("nmgmgzfy", "true", { expires: 7 }); // 存储一个带7天期限的 cookie
				$.cookie("loginId", loginId, { expires: 7 }); // 存储一个带7天期限的 cookie
				$.cookie("password", password, { expires: 7 }); // 存储一个带7天期限的 cookie
				
			}else {
				$.cookie("nmgmgzfy", "false", { expires: -1 });        // 删除 cookie
				$.cookie("loginId", '', { expires: -1 });
				$.cookie("password", '', { expires: -1 });
				
			}
			$('#loginForm').submit();
		}
	}
	</script>
</html>