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
		</script>
		<script type="text/javascript">
		//确保输入框内容不为空

		//保存
			function save(){
				var url='${basePath}action/manage/membertype/save'
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
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px;" onclick="save()">保存</a>	   
  </div>       
  <div style="margin:10px 0">
  <form id="web-form" action="" method="post">
    <table>
      <tr>
        <td>角色名称</td>
        <td><input id="name"  name="name" class="easyui-textbox" data-options="required:true,validType:'length[0,10]',missingMessage:'角色名称不能为空',invalidMessage:'角色名称不能超出10字符'"  style="width:300px;height:30px;text-align:center;" /></td>    
	</tr>
		<tr>    
			<td>权限</td>
			<td>
			  <label><input id="role"  name="role" type="radio" value="0"  checked="checked"/>禁止浏览</label>
			  <label><input id="role"  name="role" type="radio" value="1"  />部分浏览</label>
			  <label><input id="role"  name="role" type="radio" value="2" />全部浏览</label>
			  <label><input id="role"  name="role" type="radio" value="3"  />管理员</label>
			</td>
		</tr>
    </table>
    </form>
  </div>      
</div>
</body>
</html>
