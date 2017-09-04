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
		
		<title>码表信息</title>
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		<script type="text/javascript">
		
			//跳转新建页面
		function newpage(){
			window.parent.topage('新建幻灯','/manage/slideshow/add');
		}
		//跳转编辑页面
		function edit(id){
			window.parent.topage('编辑幻灯','/manage/slideshow/edit/'+id);
		}
			

			//弹出的的提示信息,刷新页面
			function showMessage(message){
				$.messager.alert('消息',message,'info',function(){location.reload();});
			}
			//删除编码数据
             function del(id){
             $.messager.confirm('Confirm','数据删除不可恢复，确认删除吗？',function(r){
				if (r){
	             $.ajax({
				   type:'GET',
				   url:"${basePath}action/manage/slideshow/delete/"+id,
				   success:function result(datas){
					  if(datas=='0'){
							showMessage("删除失败");
						 }else if(datas=='1'){
						    showMessage("删除成功");
					     }	
				   }
				   });
			    }
             });
             }
		
		//删除多个
		function delall(){
			$.messager.confirm('Confirm','数据删除不可恢复，确认删除吗？',function(r){
				if (r){
					//获取被选中的复选框
					var checkbox=$('#dg').datagrid('getChecked');
					var ids;
					for( var a=0;a<checkbox.length;a++){
						//拼接ID
						if(a==0)
							ids=checkbox[a].id;
						else 
							ids+=','+checkbox[a].id;
							
					}
					var url="${basePath}action/manage/slideshow/delete/"+ids;
					$.ajax({type:"GET", url:url,dataType:"text", success:function(datas) {
						if(datas=='0'){
							showMessage("删除失败");
						 }else if(datas=='1'){
						    showMessage("删除成功");
					     }	
					  }
					}); 
				}
			});
			
		}
	</script>
	</head>
	  
	<body>

		<div style="width:100%;height:700px;">
          <div style="padding:20px 0 0;">
		    <a  href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="newpage()" style="width:100px" >新建</a>
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:100px" onclick="delall()">删除</a>    
	      </div>
        
          <div style="margin:20px 0;"></div>	
	     <table id="dg" class="easyui-datagrid" title="列表显示" style="width:100%;height:600px" data-options="singleSelect:false,method:'get'">
		<thead>
			<tr>
				<th field="ck" checkbox="true"></th>
				<th data-options="field:'id',width:'9.9%'">ID</th>
				<th data-options="field:'name',width:'60%',">幻灯名称</th>
				<th data-options="field:'creatdate',width:'10%',align:'center'">创建日期</th>
				<th data-options="field:'edit',width:'20%',align:'center'">操作</th>
			</tr>
		</thead>

		 <tbody>
		 <c:forEach items="${slideshowList}" var="slideshow">
			     <tr>
                  <td></td>
				<td>${slideshow.id}</td>
				<td>${slideshow.name}</td>
				<td>${slideshow.creatdate}</td>
				<td><a href="#" onclick="edit('${slideshow.id}')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="del('${slideshow.id}')">删除</a></td>
				</tr>
		</c:forEach>
		 </tbody>
	</table>   
        
        </div>
	</body>
</html>