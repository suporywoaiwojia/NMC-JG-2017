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
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${basePath}js/validate.js"></script>
		<script type="text/javascript" src="${basePath}component/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
	
		//右下角弹出的的提示信息
		function showMessage(message){
				$.messager.show({
				title:'消息',
				msg:message,
				timeout:3000,
				showType:'slide'
			});
			}
		//确保输入框内容不为空
		$(function(){
		//保存
				$("#saveBtn").click(function(){
					if (isNull($("#loginId").val())) {
						art.showMessage("请输入角色名称");
						return false;
					}else if($('#loginId').val().length < 4){
						showMessage("角色名称最少要4位");
						return false;
					}
					
					//没有错误后，提交给后台
					<c:choose>  
						<c:when test="${role.id ne null}"> 
							$("#web-form").attr("action","${basePath}action/manage/role/update/${role.id}");
						</c:when>
						<c:otherwise>  
							$("#web-form").attr("action","${basePath}action/manage/role/save");
						</c:otherwise>  
					</c:choose>
					$("#web-form").submit();
				});
		})
		</script>
  </head>
 
<body>
<div class="easyui-panel" title="项目信息" style="width:100%;padding:20px 30px;">
  <div id="saveBtn" style="padding:0 0 10px;" >
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:300px;">保存</a>	   
  </div>       
  <div style="margin:10px 0">
  <form id="web-form" action="" method="post">
    <table align="center">
      <tr>

        <td>名称:</td>
        <td><input id="roleName"  name="roleName" class="easyui-textbox" style="width:300px;height:30px;text-align:center;" value="${role.roleName}"/></td> 
 
      </tr>
      <tr>   
    </table>
    </form>
  </div>      
</div>
</body>
</html>
