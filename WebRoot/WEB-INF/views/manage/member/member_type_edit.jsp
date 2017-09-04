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

		<script type="text/javascript">
			function update(){
				var url='${basePath}action/membertype/update'
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
		</script>
  </head>
 
<body>
 <div class="easyui-panel"   style="width:100%;padding:15px">
  <div id="saveBtn" style="padding:0 0 10px;" >
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px;" onclick="update()">保存</a>	   
  </div>       
  <div style="margin:10px 0">
  <form id="web-form" action="" method="post">
    <table>
      <tr>
	  	<input type="hidden" name="id" id="id" value="${memberType.id}">
        <td>角色名称</td>
        <td><input id="name" value="${memberType.name}" name="name" class="easyui-textbox" data-options="required:true,validType:'length[0,10]',missingMessage:'角色名称不能为空',invalidMessage:'角色名称不能超过10字符'"  style="width:300px;height:30px;text-align:center;" /></td>        
        
	</tr>
	<tr>
		<td >权限</td>
        <td>
          <label><input id="role"  name="role" type="radio" value="0"    <c:if test="${memberType.role=='0'}">checked="checked" </c:if> />禁止浏览</label>
          <label><input id="role"  name="role" type="radio"  value="1" <c:if test="${memberType.role=='1'}">checked="checked" </c:if> />部分浏览</label>
		  <label><input id="role"  name="role" type="radio" value="2" <c:if test="${memberType.role=='2'}">checked="checked" </c:if> />全部浏览</label>
          <label><input id="role"  name="role" type="radio" value="3" <c:if test="${memberType.role=='3'}">checked="checked" </c:if>  />管理员</label>
        </td>
	</tr>
    </table>
    </form>
  </div>      
</div>
</body>
</html>
