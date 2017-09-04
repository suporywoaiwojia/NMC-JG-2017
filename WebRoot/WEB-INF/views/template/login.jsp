<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link type="text/css" rel="stylesheet" href="../../css/global.css" />
		<style type="text/css">
			input.submit {	    margin-right:30px;display:inline;cursor:pointer;display:block;	width:126px;height:68px;float:right;margin-top:-30px;border:0px;padding:0px;background:none;-webkit-border-radius: .3em;	-moz-border-radius: .3em;border-radius: .3em;}
			input.submit:hover {margin-right:30px;display:inline;cursor:pointer;display:block;	width:126px;height:68px;float:right;margin-top:-30px;border:0px;padding:0px;background:none;-webkit-border-radius: .3em;	-moz-border-radius: .3em;border-radius: .3em;}
	
			input.required {border:solid 1px #ccc;background:#fff;width:200px;height:24px;line-height:24px;-webkit-border-radius: .3em;-moz-border-radius: .3em;border-radius: .3em;font-family: Helvetica, Arial, sans-serif;color:#666;}
			input.required:focus {	border:solid 1px #ebc77e;background: #f8f3c7;width:200px;height:24px;line-height:24px;-webkit-transition:background .2s ease-in;-moz-transition:background .2s ease-in;  -o-transition:background .2s ease-in;  transition:background .2s ease-in;}
			label {display:block;	font-family: Helvetica, Arial, sans-serif;font-size:14px;font-weight:bold;color:#666;margin:10px 0 3px 0;width:295;text-align:left;}
			
			.remember {	float:left;	margin-left:50px;width:400px;display:inline;margin-top:5px;font-size:12px;}
			.remember input.checkbox {float:left;	margin:0 5px 0 0;padding-left:15px;width:auto;	background:none;border:none;}
			p {font-family: Helvetica, Arial, sans-serif;font-size:12px;color:#666;clear:both;}
			p a.tooltip {color:#0784b3;font-weight:bold;text-decoration:none;text-align:left;clear:both;float:left;}
			p a.tooltip:hover {color:#127296;}
			.closeButton{position:absolute;top:5px;right:7px;width:22px;height:21px;overflow:hidden;cursor:pointer;}


		</style>
		<script type="text/javascript" src="${basePath}js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${basePath}js/validate.js"></script>
		<script type="text/javascript">
			var COOKIE_PREFIX = 'www.anda9.net';
			$(function(){
				var member = '${member}';
				var errorMessage = '${errorMessage}';
				if(errorMessage && errorMessage != ''){
					var mail = '${loginid}';
					$('#message').html(errorMessage);
					$('#loginid').val(mail);
				}else if(member && member != ''){
					var p = window.parent.document;
					var mail = '${member.loginid}';
					var nickname = '${member.nickname}';
					var type = '${memberType.id}';
					var rememberMe = '${rememberMe}';
					
					if(rememberMe != '-1'){
						if(rememberMe == 'on'){
							var cookieValue = COOKIE_PREFIX + '|' + mail+'='+nickname+','+type;
							var days = 30;
							var cookies = document.cookie.split('; ');
							for(var i=0; i<cookies.length; i++){
								var prefixAndcookie = cookies[i].split('|');
								if(prefixAndcookie[0] == COOKIE_PREFIX && prefixAndcookie.length > 1){
									document.cookie = cookies[i] + '; expires=' + (new Date(-1)).toGMTString() + '; path=/';
								}
							}
							cookieValue += ',${pwd}';
							var now = new Date();
							now.setTime(now.getTime() + days * 24 * 60 * 60 * 1000);
							
							document.cookie = cookieValue + '; expires=' + now.toGMTString() + '; path=/';
						}
						
						$('.drop', p).slideUp();
						var name='${member.loginid}';
						if(name.length>6)
						name= name.substr(0,6)+".."
						$('#username', p).html('用户名：<a href="#" style="color:#FF6600">'+name+'</a>');
						$('.login', p).hide();
						$('.user', p).show();
						$('#headuser', p).hide();

						p.location.reload();
					//	$('#welcome', p).html('${member.loginid} 您好，欢迎了解蒙古族文化！');
										
					}else{
						var cookies = document.cookie.split('; ');
						for(var i=0; i<cookies.length; i++){
							var prefixAndcookie = cookies[i].split('|');
							if(prefixAndcookie[0] == COOKIE_PREFIX && prefixAndcookie.length > 1){
								var update = false;
								var keyAndVakue = prefixAndcookie[1].split('=');
								var values = keyAndVakue[1].split(',');
								if(values[0] != nickname || values[1] != type){
									var cookieValue = COOKIE_PREFIX + '|' + mail+'='+nickname+','+type+','+values[2];
									var now = new Date();
									now.setTime(now.getTime() + 30 * 24 * 60 * 60 * 1000);
									document.cookie = cookieValue + '; expires=' + now.toGMTString() + '; path=/';
								}
								
										var name='${member.loginid}';
										if(name.length>6)
										name= name.substr(0,6)+".."
										$('#username', p).html('用户名：<a href="#" style="color:#FF6600">'+name+'</a>');
										$('.login', p).hide();
										$('.user', p).show();
										$('#headuser', p).hide();
									//	$('#welcome', p).html('${member.loginid} 您好，欢迎了解蒙古族文化！');
										
										
							}
						}
					}
				}else{
					var cookies = document.cookie.split('; ');
					for(var i=0; i<cookies.length; i++){
						var prefixAndcookie = cookies[i].split('|');
						if(prefixAndcookie[0] == COOKIE_PREFIX && prefixAndcookie.length > 1){
							var keyAndVakue = prefixAndcookie[1].split('=');
							var values = keyAndVakue[1].split(',');
							if(values.length == 3){
								$('#loginid').val(keyAndVakue[0]);
								$('#password').val(values[2]);
								$('#loginForm').attr('action', '${basePath}action/rememberlogin');
								$('#loginForm').submit();
								
							}
						}
					}
				}
			});
			
			function login(){
				var email = $.trim($('#loginid').val());
				var password = $.trim($('#password').val());
				if(email == ''){
					$('#message').html('请输入用户名。')
					$('#loginid').focus();
					return false;
				}else if(password == ''){
					$('#message').html('请输入密码。')
					$('#password').focus();
					return false;
				}else if(!isEmail(email)){
					/*
					$('#message').html('请输入您注册时所使用的邮箱地址登录。')
					$('#mail').focus();
					return false;
					*/
				}
				
			}
			
			function colseLoginWindow(){
				$('#message').html('');
				$('#loginid').val('');
				$('#password').val('');
				var p = window.parent.document;
				$('.drop', p).slideUp();
			}
		</script>
  	</head>
  
  	<body>
  	  	<div class="closeButton" onclick="colseLoginWindow()"></div>
  		<form id="loginForm" action="${basePath}action/userlogin" method="post" onsubmit="return login()" >
		
          	<input type="text" id="loginid" name="loginid" class="required" maxlength="30" style="margin-top:72px;margin-left:85px;float:left;display:inline;"/>
    
          	<input type="password" id="password" name="password" class="required" maxlength="20" style="margin-top:12px;margin-left:85px;float:left;display:inline;"/>
          	<input type="submit" class="submit" value="          " />
          	<div id="message" style="position:absolute;bottom:2px;left:155px;width:200px;height:20px;line-height:20px;color:#f00;font-size:12px;"></div>
          	<div class="remember">
          		<label style="width:80px; float:left"><input id="rememberMe" name="rememberMe" type="checkbox" class="checkbox"/>记住我</label>
				<label style="float:right; width:100px"><a href="http://www.anda9.net/action/regedit"  target="_parent">[会员注册]</a></label>
          	</div>
          	<p><a href="#" class="tooltip"><!--  忘记密码--></a></p>
		</form>
  	</body>
</html>