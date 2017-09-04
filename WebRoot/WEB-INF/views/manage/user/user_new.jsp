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
			
			function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
			}
			function myparser(s){
				if (!s) return new Date();
				var ss = (s.split('-'));
				var y = parseInt(ss[0],10);
				var m = parseInt(ss[1],10);
				var d = parseInt(ss[2],10);
				if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
					return new Date(y,m-1,d);
				} else {
					return new Date();
				}
			}
		function save(){
			var url="${basePath}action/manage/user/save";
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
  <div  style="padding:0 0 10px;" >
    <a id="saveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px;" onclick="save()">保存</a>	   
  </div>       
  <div style="margin:10px 0">
  <form id="web-form" action="" method="post">
    <table>
      <tr>
        <td>登录ID:</td>
        <td><input id="loginId"  name="loginId" class="easyui-textbox" data-options="required:true,validType:'length[0,50]',missingMessage:'账号不能为空',invalidMessage:'长度不能超出50个字符'"  style="width:300px;height:30px;" /></td>        
        
		<td  style="padding-left:40px;">会员性别</td>
		<td>
          <label><input id="member-y" name="sex" type="radio" value="1"  checked="checked" />男</label>
          <label><input id="member-n" name="sex" type="radio" value="2" />女</label>
        </td>
      </tr>
      <tr>
		<td>用户密码:</td>
        <td><input id="password" type="password" name="password" data-options="required:true,validType:'length[0,50]',missingMessage:'密码不能为空',invalidMessage:'长度不能超出50个字符'" class="easyui-textbox" style="width:300px;height:30px;" /></td>        
        <td style="padding-left:40px;">密码确认:</td>
        <td><input  id="repassword" type="password" name="repassword" validType='same["#password"]' data-options="required:true,missingMessage:'密码不能为空',invalidMessage:'前后密码不一致'" class="easyui-textbox" style="width:300px;height:30px;" /></td>
		</tr>
      <tr>
        <td>用户名称:</td>
        <td><input id="userName" name="userName"   class="easyui-textbox" data-options="required:true,validType:'length[0,50]',missingMessage:'用户名称不能为空',invalidMessage:'长度不能超过25个字符'" style="width:300px;height:30px;"/>
		</td>
        <td style="padding-left:40px;">用户生日:</td>
		<td><input name="birthday" id="born_s" class="easyui-datebox" style="width:300px;height:30px;" data-options="sharedCalendar:'#cc',formatter:myformatter,parser:myparser,editable:false" />
		<div id="cc" class="easyui-calendar"></div>
		</td>
      </tr>
      <tr>
        <td>联系电话:</td>
        <td><input id="mobile" name="mobile" class="easyui-textbox" data-options="required:false,validType:'length[0,20]',invalidMessage:'长度不能超过20个字符'" style="width:300px;height:30px;" /></td>
        <td style="padding-left:40px;">用户邮箱:</td>
        <td><input id="email" name="email" data-options="required:false,validType:'email',missingMessage:'邮箱不能为空',invalidMessage:'邮箱格式不正确,如abc@sina.com'" class="easyui-textbox" style="width:300px;height:30px;" /></td>
      </tr>
	 <td style="height:30px; ">审核权限:</td>
        <td>
		 <c:forEach items="${role}" var="role"  varStatus="status" >
		    <input type="checkbox"  name="roles[${status.index}].id" id="role" value="${role.id}"/> 
			<label>
			<input type="hidden" name="roles[${status.index}].roleName"  value="${role.roleName}" />${role.roleName}</label>
		</c:forEach>
        </td>
      
    </table>
    </form>
  </div>      
</div>

</body>
</html>
