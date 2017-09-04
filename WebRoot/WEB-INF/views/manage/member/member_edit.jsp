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
		
		//确保输入框内容不为空
		$(function(){
			$('${member.role.id}'!='')
					$('#role').combobox('select','${member.role.id}');
	
        });
       function update(){
	   		var url='${basePath}action/member/update'
			$('#web-form').form('submit', {
				url: url,
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid)
						return isValid;	// return false will stop the form submission
				},
				success: function(data){
					if(data=="1"){
						$.messager.alert('消息','保存成功','info');
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
	    <input type="hidden" id="id" name="id" value="${member.id}">
        <td>登录ID</td>
        <td><input id="loginid"  name="loginid" value="${member.loginid}" data-options="required:true,validType:'length[0,20]',missingMessage:'登录账号不能为空',invalidMessage:'长度不能超出20个字符'" class="easyui-textbox" style="width:300px;height:30px;"/></td>        
       <td style="padding-left:40px;">会员昵称</td>
        <td><input  id="nickname" name="nickname" value="${member.nickname}" data-options="required:true,validType:'length[0,10]',missingMessage:'昵称不能为空',invalidMessage:'不能超过10个字符'" class="easyui-textbox" style="width:300px;height:30px;"/></td>
		<td style="padding-left:40px;">会员性别</td>
		<td>
          <label><input id="member-y" name="sex" type="radio" value="0" <c:if test="${member.sex=='0'}">checked="checked" </c:if>/>男</label>
          <label><input id="member-n" name="sex" type="radio" value="1" <c:if test="${member.sex=='1'}">checked="checked" </c:if>/>女</label>
        </td>
      </tr>
      <tr>
        <td >会员生日</td>
        <td>
		<input name="birthday" id="born_s" class="easyui-datebox" style="width:300px;height:30px;text-align:center;" data-options="required:true,validType:'date',missingMessage:'生日不能为空',sharedCalendar:'#cc',formatter:myformatter,parser:myparser,editable:false"  value="${member.birthday}" />
		<div id="cc" class="easyui-calendar"></div>
		</td>
        <td style="padding-left:40px;">注册邮箱</td>
        <td><input  id="mail" name="mail" value="${member.mail}" data-options="required:true,validType:'email',missingMessage:'注册邮箱不能为空',invalidMessage:'邮箱格式如acb@sina.com'" class="easyui-textbox" style="width:300px;height:30px;"/></td>
		<td style="padding-left:40px;">会员密码</td>
        <td><input id="password" name="password" value="${member.password}" type= "password" class="easyui-textbox" data-options="required:true,validType:'length[0,60]',missingMessage:'密码不能为空',invalidMessage:'密码不能超出30个汉字或60个字符'" style="width:300px;height:30px;"/></td>
      </tr>
   
      <tr>
        <td >联系电话</td>
        <td><input id="tel" name="tel" value="${member.tel}" data-options="required:true,missingMessage:'联系方式不能为空'," class="easyui-textbox" style="width:300px;height:30px;text-align:center;"/></td>

        <td style="padding-left:40px;">联系地址</td>
        <td><input id ="address" name ="address" value="${member.address}" class="easyui-textbox" style="width:300px;height:30px;text-align:center;"/></td>
		<td  style="padding-left:40px;">角色</td>
         <td>
          <select  class="easyui-combobox" name="role.id" id="role" style="width:300px;height:30px;" data-options="editable:false,panelHeight:'auto'">
		  		 <c:forEach items="${memberTypes}" var="memberType" >
					<option value="${memberType.id}">${memberType.name}</option>		
				</c:forEach>
          </select>

        </td>
		
      </tr>
      <tr>
        <td>有效日期</td>
        <td>
		<input name="eTime" id="born_s" class="easyui-datebox"  style="width:300px;height:30px;text-align:center;" data-options="required:true,validType:'date',missingMessage:'生日不能为空',sharedCalendar:'#cc',formatter:myformatter,parser:myparser,editable:false"  value="${member.eTime}" />
		<div id="cc" class="easyui-calendar"></div>
		</td>
         <td style="padding-left:40px;">会员类型</td>
        <td>
          <label><input id="member-y" name="type" type="radio" value="0" <c:if test="${member.type=='0'}">checked="checked" </c:if>/>个人</label>
          <label><input id="member-n" name="type" type="radio" value="1" <c:if test="${member.type=='1'}">checked="checked" </c:if>/>企业</label>
        </td>
		<td  style="height:30px;padding-left:40px;">会员状态</td>
        <td>
          <label><input id="state" name="state" type="radio" value="0" <c:if test="${member.state=='0'}">checked="checked" </c:if> />禁用</label>
          <label><input id="state" name="state" type="radio" value="1" <c:if test="${member.state=='1'}">checked="checked" </c:if>/>正常</label>
        </td>
       
	  </tr>
    </table>
    </form>
  </div>      
</div>
</body>
</html>
