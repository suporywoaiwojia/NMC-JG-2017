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
	<link type="text/css" rel="stylesheet" href="${basePath}component/artdialog/skins/black.css" /> <script type="text/javascript" src="${basePath}js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${basePath}component/artdialog/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}component/artdialog/artDialog.js"></script>
	<script type="text/javascript" src="${basePath}component/artdialog/plugins/iframeTools.js"></script>
	<script type="text/javascript" src="${basePath}js/validate.js"></script>
	
	
	<script type="text/javascript">
	$(function(){
	var _MESSAGE = '${message}'; 
				if (_MESSAGE != '') {
					if (_MESSAGE.indexOf('成功') != -1) {
						art.dialog({content: _MESSAGE, lock: false, time: 2});
					} else {
						art.dialog({content: _MESSAGE, time: 2});
					}
				}
		$("#save").click(function(){
				
				
				$("#Properties").attr("action","${basePath}/action/Pdf2swf/edit");
			$("#Properties").submit();
		});
		
	});
	</script>
</head>

<body>
	<form id="Properties" method="post" action="">
		 <%--按钮位置  START--%>
		<div class="selectBox">
			<table width="70%" height="42" cellpadding="0" cellspacing="0" border="0" >
				<tr>
					
					<td >
						<input type="button" name="save" id="save" class="saveButtonBG"   title="保存"/>
					</td>
					<td align="center" valign="middle">&nbsp;
						
					</td>
				</tr>
			</table>
		</div>
		 <%--按钮位置  END--%>
		

  	    <div class="newBox">
		   <table style="width:100%;min-width:800px;margin:0 auto;border:0px solid #ccc;" align="center" cellpadding="0" cellspacing="0" border="0">
		    	<tr>
				
		    		<td>
					<ul class="newlongInput">
	  	    			<li class="newBoxleftTitle">服务器环境</li>
	  	    			<li class="newBoxinput"><input type="radio" value="1" class="radio" name="environment" <c:if test="${environment eq 1 }">checked="checked"</c:if> />WINDOWS  &nbsp;&nbsp;<input type="radio" value="2" name="environment" class="radio"   <c:if test="${environment eq 2 }">checked="checked"</c:if>  />LINUX	</li>
						
	  	    		</ul>
					<ul class="newlongInput">
	  	    			<li class="newBoxleftTitle">openOffice路径</li>
	  	    			<li class="newBoxinput"><input type="text"   name="openOffice_home" value="${openOffice_home}"/></li>
	  	    		</ul>
					<ul class="newlongInput">
	  	    			<li class="newBoxleftTitle">swfTools路径</li>
	  	    			<li class="newBoxinput"><input type="text" class="newInput" style="width:95%;" name="swfTools_home"  value="${swfTools_home}"/></li>
	  	    		</ul>
					</td>
		    	</tr>
		    	
		    </table>
	    </div>
		
	</form>
</body>
</html>
