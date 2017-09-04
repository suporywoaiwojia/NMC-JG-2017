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
		<title>用户管理</title>
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		<script type="text/javascript">
        var pageNum = 0;
			
			//跳转新建页面
		function newpage(){
			window.parent.topage('新建用户','/manage/user/newpage');
		}
		//跳转编辑页面
		function edit(id){
			window.parent.topage('编辑用户','/manage/user/editpage/'+id);
		//	$('#editmt').attr('src','${basePath}action/manage/user/editpage/'+id);
		//	$('#editpage').window('open');
		}

					
		//翻页
		$(function(){
			//翻页操作
			$('#pp').pagination({
				onSelectPage:function(pageNumber, pageSize){
					$(this).pagination('loading');
				//	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
				    pageNum = pageNumber;
					pageRefresh(pageNumber);
					$(this).pagination('loaded');
					
				}
			});
		})
		//列表页面跳转
		function pageRefresh(pageNumber){
			var url="${basePath}action/manage/user/list/"+pageNumber;
			$("#userForm").attr("action",url);
			$("#userForm").submit();
		}
			
			//右下角弹出的的提示信息
			function showMessage(message){
				$.messager.alert('消息',message,'info',function(){location.reload();});
			}
			//删除一条数据
             function del(id){
             $.messager.confirm('Confirm','数据删除不可恢复，确认删除吗？',function(r){
				if (r){
	             $.ajax({
				   type:'GET',
				   url:"${basePath}action/manage/user/deleteUser/"+id+"/"+pageNum,
				   success:function result(){
					   showMessage("删除成功");
				   }
				   });
			    }
             });
             }
		
		//多条删除
		function delall(){
			$.messager.confirm('Confirm','数据删除不可恢复，确认删除吗？',function(r){
				if (r){
					//获取被选中的复选框
					var checkbox=$('#dg').datagrid('getChecked');
					var len=checkbox.length;
					var ids;
					for( var a=0;a<len;a++){
						//拼接ID
						if(a==0)
							ids=checkbox[a].id;
						else 
							ids+=','+checkbox[a].id;
							
					}
					var url="${basePath}action/manage/user/deleteUser/"+ids+"/"+pageNum;
					$("#userForm").attr("action",url);
					$("#userForm").submit();
				}
			});
			
		}

	</script>
	</head>
	  
	<body>
		
		<div style="width:100%;height:700px;">
		
          <div style="padding:20px 0 0;">
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px" id ="add" onclick="newpage()">新建</a>
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:100px" onclick="delall()">删除</a>    
	      </div>
          <div style="margin:20px 0;"></div>	
	     <table id="dg" class="easyui-datagrid" title="列表显示" style="width:100%;height:600px"
			data-options="singleSelect:false,method:'get'">
		<thead>
			<tr>
				<th field="ck" checkbox="true"></th>
				<th data-options="field:'id',width:'9.9%',align:'center',hidden:true">ID</th>
				<th data-options="field:'bm',width:'29.9%'">登录账户</th>
				<th data-options="field:'name',width:'30%'">用户名称</th>
				<th data-options="field:'tel',width:'20%',align:'center'">联系电话</th>
				<th data-options="field:'edit',width:'20%',align:'center'">操作</th>
			</tr>
		</thead>

		 <tbody>
		 <c:forEach items="${pagingTools.dataSet}" var="user">
			     <tr>
                  <td></td>
				<td>${user.id}</td>
				<td>${user.loginId}</td>
				<td>${user.userName}</td>
				<td>${user.mobile}</td>
				
				<td><a href="#" onclick="edit('${user.id}')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="del('${user.id}')">删除</a></td>
				</tr>
		</c:forEach>
		 </tbody>
	</table> 
	<form action="" method="get" id="userForm">
         <div id="pp" class="easyui-pagination" style="background:#efefef;border:1px solid #ccc;"
				data-options="total:${pagingTools.count},pageNumber:${pagingTools.currentPage},pageSize:${pagingTools.pageRecord},displayMsg:'',layout:['sep','first','prev','manual','next','last','efresh']">
			</div>
       
      
	</form>
 </div>
	</body>
</html>
<div id="newpage" class="easyui-window" title="新建用户" closed="true" style="width:850px;height:425px;padding:5px;top:100px" minimizable="false" data-options="inline:true">
		<iframe src="${basePath}action/user/newpage" width="100%" height="100%" frameborder="no" border="0"></iframe>
</div>
<div id="editpage" class="easyui-window" title="编辑用户" closed="true" style="width:850px;height:425px;padding:5px;top:100px" minimizable="false" data-options="inline:true">
		<iframe src="" width="100%" height="100%" frameborder="no" border="0" id='editmt'></iframe>
</div>	