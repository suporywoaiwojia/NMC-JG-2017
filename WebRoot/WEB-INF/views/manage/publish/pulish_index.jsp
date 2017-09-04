<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" /> 
		<meta http-equiv="cache-control" content="no-cache" /> 
		<meta http-equiv="expires" content="0" />
		<title>无标题文档</title>
	
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/backstage-v1.1.css" />
		 <link type="text/css" rel="stylesheet" href="${basePath}component/artdialog/skins/black.css" /> <script type="text/javascript" src="${basePath}js/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/jquery.artDialog.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/artDialog.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/plugins/iframeTools.js"></script>
		<script type="text/javascript" src="${basePath}js/validate.js"></script>
		
		
		<script type="text/javascript"  src="${basePath}js/tableColor.js" ></script>
		<script type="text/javascript">
		$(function(){
		
		
		var _MESSAGE = '${message}'; 
		var _indexDynamics='${indexDynamics}';
				if (_MESSAGE != '') {
					if (_MESSAGE.indexOf('成功') != -1) {
						art.dialog({content: _MESSAGE, lock: false, time: 2});
					} else {
						art.dialog({content: _MESSAGE, time: 2});
					}
				}
				
				if(_indexDynamics=='0'){
					
					$("#makeindex").attr("disabled","disabled");
					$("#deleteindex").attr("disabled","disabled");
				}
				$("#makeindex").click(function(){
					var url = "${basePath}/action/publish/index";
					//$("#message").html('<div style="background:url(../../../../style/images-v1.1/loding.gif) no-repeat; width:658px; height:493px; margin:0 auto; text-align:center; font-size:16px" >正在生成，请稍后…… </div>');
					//$("#makeindex").attr("disabled","disabled");
					$.ajax({type:"POST", url:url,dataType:"text", success:function(datas) {
						if(datas != ""){
							var msg=datas;
						//	$("#message").html(msg);
							$("#makeindex").removeAttr("disabled");
						}
					}});

				});
				$('#loding').hide();
				$("#makeall").click(function(){
					var url1 = "${basePath}/action/build/allpage";
					$("#message").html('<div style="background:url(../../../../style/images-v1.1/loding.gif) no-repeat; width:658px; height:493px; margin:0 auto; text-align:center; font-size:16px" >正在生成，请稍后…… </div>');
					$("#makeall").attr("disabled","disabled");
				
					
					$.ajax({type:"POST",async : true, url:url1,dataType:"text", success:function(datas) {
						if(datas != ""){
						//$('#loding').hide();
					
							var msg=datas;
							$("#message").html(msg);
							$("#makeall").removeAttr("disabled");
						}
					}});

				});
				$("#deleteindex").click(function(){
					
					$("#publishColumn").attr("action","${basePath}/action/publish/delete");
					$("#publishColumn").submit();
				});
			});
			
			


	</script>
	</head>
	  
	<body>
		<form action="" method="get" id="publishColumn">
	    <div class="selectBox">
	    	<table width="100%" height="42" cellpadding="0" cellspacing="0" border="0" >
	    		<tr>
	    			<td align="center" valign="middle">
					
	    				</td>
					<td align="center" valign="middle">
					<input type="button" id="makeindex" value="首页生成" class="publichButton"/>
					<input type="button" id="deleteindex" value="首页删除"  class="publichButton"/>
					<input type="button" id="makeall" value="整站生成"  class="publichButton"/>
	    			</td>
						</tr>
						</table>
	    </div>
		
		 <div class="listBox">
		 <div id="message" style="min-height:500px;border-radius:8px;-webkit-border-radius:8px; -moz-border-radius:8px;background: #FFFFFF;width:700px;margin:0 auto;">
		
			
		 </div>
		
		
		 </div>
	  </form>
	</body>
</html>
