<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" /> 
		<meta http-equiv="cache-control" content="no-cache" /> 
		<meta http-equiv="expires" content="0" />
	    <link rel="shortcut icon" href="favicon.ico">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
		<link type="text/css" rel="stylesheet" href="${basePath}component/js-fileupload/client/css/uploadify.css" /> 
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		 <script type="text/javascript" src="${basePath}component/js-fileupload/client/jquery.uploadify.js"></script>
		
		<script type="text/javascript">
	function save(){
		var url='${basePath}action/website/links/save'
		$('#web-form').form('submit', {
			url: url,
			onSubmit: function(){
				var isValid = $(this).form('validate');
				if (!isValid)
					return isValid;	// return false will stop the form submission
			},
			success: function(data){
				if(data=="1"){
					$.messager.alert('消息','保存成功','info',function(){window.parent.pageRefresh(1);});
				}else{
					$.messager.alert('消息','保存失败','info');
				}
			}
		});
	}
	$(function(){

					$('#upload_file').render({
						buttonClass: 'custom',
						savePath: 'website/www/contentImg/links/',
						httpPath: 'website/www/contentImg/links/',
						basePath: '${basePath}',
						componentPath: '${basePath}component/js-fileupload/client/',
						type: 'image',
						callback: function(fileElement, file, saveFile, httpFile){
							var index = file.lastIndexOf('.');
							var ext = file.substring(index, file.length);
							$('#filePath').val(httpFile + ext);
						}
					});
				//file_img.preview(file_img.attr('title'));
				
	});
		</script>
  </head>
 
<body>
 <div class="easyui-panel"   style="width:100%;padding:15px">
  <div  style="padding:0 0 10px;" >
    <a id="saveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px;" onclick="save()">保存</a>	   
  </div>       
  <div style="margin:10px 0">
  <form id="web-form" action="" method="post">
    <table >
      <tr>
	  	<td rowspan="3" style="padding-top:10px;padding-right:10px;">
			<input type="file" id="upload_file" name="upload_file" />
			<input type="hidden" id="filePath" name="filePath" />		</td>
        <td>链接名称:</td>
        <td><input id="name"  name="name" data-options="required:true,validType:'length[0,10]',missingMessage:'链接名称不能为空',invalidMessage:'长度不能超过10个字符'" class="easyui-textbox" style="width:300px;height:30px"/></td>        
               
      </tr>
	  <tr >
	    <td >链接地址</td>
	    <td><input id="link" name="link" data-options="required:true,validType:'length[0,100]',missingMessage:'链接地址不能为空',invalidMessage:'长度不能超过100个字符'" class="easyui-textbox" style="width:300px;height:30px"/></td>
		
	    </tr>
	  <tr >
	  	<td >链接描述</td>
		<td><input id="detailed" name="detailed" class="easyui-textbox"  data-options="multiline:true,required:false,validType:'length[0,150]',invalidMessage:'长度不能超过150个字符'" style="width:300px;height:100px"/></td>
        </tr>
    </table>
    </form>
  </div>      
</div>
</body>
</html>
