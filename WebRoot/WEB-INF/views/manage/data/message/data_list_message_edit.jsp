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
		//右下角弹出的的提示信息
		function showMessage(message){
				$.messager.show({
				title:'消息',
				msg:message,
				timeout:3000,
				showType:'slide'
			});
			}
		

	
		</script>
  </head>
 
<body>
<div class="easyui-panel" title="项目信息" style="width:100%;padding:20px 30px;">
  <div  style="padding:0 0 10px;" >
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:300px;" onclick="save()">保存</a>	   
  </div>       
  <div style="margin:10px 0">
    <form id="web_form" action="" method="post">
	<table align="center" >
   <input type="hidden" id="message" name="message.id" value="${message.id}">  
		<tr>
        <td >标题:</td>
        <td><input id="title"  name="title" value="${rapper.name}" data-options="required:true,validType:'length[0,50]',missingMessage:'资料名称不能为空',invalidMessage:'作者长度不能超出50个字符'" class="easyui-textbox" style="text-align:center;width:300px;height:30px" /></td>        
        <td style="padding-left:40px;">姓名:</td>
        <td><input id="name" name="name" value="${message.name}" data-options="required:true,validType:'length[0,10]',missingMessage:'作者不能为空',invalidMessage:'作者长度不能超出20个字符'" class="easyui-textbox" style="width:300px;height:30px;text-align:center;" /></td>        
        <td style="padding-left:40px;">电话:</td>
        <td><input  id="phone" name="phone" value="${message.phone}" data-options="required:true,missingMessage:'作者单位不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px"/></td>
      </tr>
      <tr> 
		<td>邮箱:</td>
        <td>
		<input id="email" name="email"  value="${message.email}" class="easyui-textbox"  style="text-align:center;width:300px;height:30px" /> 
       </td>
       <td  style="padding-left:40px;">所属栏目</td>
         <td>
         <input  value="${column.name}" class="easyui-textbox" readonly="readonly" style="text-align:center;width:300px;height:30px" />
         <input type="hidden" id="column" name="column.id" value="${column.id}"/>
       </td>

      <c:if test="${column.approve=='1'}">
        <td style="padding-left:40px;">审核人员:</td>
        <td>
          <select id="approve" class="easyui-combobox" name="approve.id" style="width:300px;height:30px; text-align:center;">
            <c:forEach items="${approveList}" var="approve" >
                <option value="${approve.id}" <c:if test="${message.approve.id==approve.id}" >selected="selected"</c:if> >${approve.userName}</option>		
            </c:forEach>
          </select>       
        </td>
        </c:if>
        <c:if test="${column.approve=='0'}">
        <td style="padding-left:40px">审核开关:</td>
        <td>
        关闭
        </td>
        </c:if>
       </tr>
      <tr></tr>
      <tr>
	    <td >内容:</td>
		<td colspan="5"><input id="content" name="content" value="${message.content}" class="easyui-textbox" data-options="multiline:true" style="width:1100px;height:150px" /></td>
	  </tr> 
      <tr>
	    <td >审核意见:</td>
		<td colspan="5"><input id="opinion" name="opinion" value="${message.opinion}" class="easyui-textbox" data-options="multiline:true" style="width:1100px;height:150px" /></td>
	  </tr> 
    </table>
  
  </form>
  </div>
  
		    
</div>
</body>
<script type="text/javascript">
		function save(){
			var url = "${basePath}action/manage/data/rapper/update";
			var data = $("#web_form").serialize();
			$.post(url, data,
				   function(result){
					 if(result=='1'){
						 $.messager.alert('消息','保存成功','info');
					 }else if(result=='0'){
						 $.messager.alert('消息','保存失败','info');
				      }
				   }, "text");
				
		}
		
	</script>
	
</html>
