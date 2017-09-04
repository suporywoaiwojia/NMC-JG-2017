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
  <script type="text/javascript" src="${basePath}js/jquery-2.2.3.min.js"></script>
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
<script type="text/jscript">
				$("#makeindex").click(function(){
					var url = "${basePath}/action/publish/index";
					//$("#message").html('<div style="background:url(../../../../style/images-v1.1/loding.gif) no-repeat; width:658px; height:493px; margin:0 auto; text-align:center; font-size:16px" >正在生成，请稍后…… </div>');
					//$("#makeindex").attr("disabled","disabled");
					$.ajax({type:"POST", url:url,dataType:"text", success:function(datas) {
						if(datas != ""){
						//	var msg=datas;
						//	$("#message").html(msg);
						//	$("#makeindex").removeAttr("disabled");
						alert(12);
						}
					}});

				});
</script>