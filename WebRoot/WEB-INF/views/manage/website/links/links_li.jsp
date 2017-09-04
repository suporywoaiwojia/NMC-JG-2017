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
         var pageNum=0;
		 			
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
			var url="${basePath}action/website/links/list/"+pageNumber;
			$("#userForm").attr("action",url);
			$("#userForm").submit();
		}
			//删除一条数据
             function del(ids){
             $.messager.confirm('Confirm','数据删除不可恢复，确认删除吗？',function(r){
				if (r){
	            var id = String(ids);
	             $.ajax({
				   type:'GET',
				   url:"${basePath}action/website/links/delete/"+id+"/"+pageNum,
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
					$.ajax({
				   type:'GET',
				   url:"${basePath}action/website/links/delete/"+ids+"/"+pageNum,
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

        
		//跳转新建页面
		function newpage(){
		
			$('#newpage').window('open');
			
		}
		//打开更新窗口，编码表	
		function edit(id){
			
			$('#editmt').attr('src','${basePath}action/website/links/editpage/'+id);
			$('#editpage').window('open');
		}

			//弹出的的提示信息,刷新页面
			function showMessage(message){
				$.messager.alert('消息',message,'info',function(){location.reload();});
			}
	</script>
	</head>
	  
	<body>
		
		<div style="width:100%;height:700px;">
		
          <div style="padding:20px 0 0;">
		    <a  href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px" onclick="newpage()">新建</a>
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:100px" onclick="delall()">删除</a>    
	      </div>
        
          <div style="margin:20px 0;"></div>	
	     <table id="dg" class="easyui-datagrid" title="列表显示" style="width:100%;height:600px"
			data-options="singleSelect:false,method:'get'">
		<thead>
			<tr>
				<th field="ck" checkbox="true"></th>
				<th data-options="field:'id',width:'9.9%',align:'center',hidden:true">ID</th>
				<th data-options="field:'name',width:'20%',align:'center'">链接名称</th>
				<th data-options="field:'link',width:'30%',align:'center'">链接地址</th>
				<th data-options="field:'detailed',width:'39%',align:'center'">链接描述</th>
				<th data-options="field:'edit',width:'10%',align:'center'">操作</th>
			</tr>
		</thead>

		 <tbody>
		 <c:forEach items="${pagingTools.dataSet}" var="link">
			     <tr>
                  <td></td>
				<td>${link.id}</td>
				<td>${link.name}</td>
				<td>${link.link}</td>
				<td>${link.detailed}</td>
				<td><a href="#" onclick="edit('${link.id}')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="del('${link.id}')">删除</a></td>
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
<div id="newpage" class="easyui-window" title="新建链接" closed="true" style="width:750px;height:355px;padding:5px;top:150px" minimizable="false" data-options="inline:true">
		<iframe src="${basePath}action/website/links/newpage" width="100%" height="100%" frameborder="no" border="0"></iframe>
</div>
<div id="editpage" class="easyui-window" title="编辑链接" closed="true" style="width:750px;height:355px;padding:5px;" minimizable="false" data-options="inline:true">
		<iframe src="" width="100%" height="100%" frameborder="no" border="0" id='editmt'></iframe>
</div>