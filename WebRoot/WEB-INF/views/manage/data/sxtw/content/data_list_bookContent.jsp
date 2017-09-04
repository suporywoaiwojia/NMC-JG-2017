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
		
		<title>文献信息</title>
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
					pageRefresh(pageNumber-1);
					$(this).pagination('loaded');
					
				}
			});
		})
				//列表页面跳转
		function pageRefresh(pageNumber){
			var url="${basePath}action/content/list/"+pageNumber;
			$("#userForm").attr("action",url);
			$("#userForm").submit();
		}
			//删除一条数据
       function del(id){
             $.messager.confirm('Confirm','数据删除不可恢复，确认删除吗？',function(r){
				if (r){
	             $.ajax({
				   type:'GET',
				   url:"${basePath}action/content/deleteMember/"+id+"/"+pageNum,
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
					$.ajax({
				   type:'GET',
				   url:"${basePath}action/content/deleteMember/"+ids+"/"+pageNum,
				   success:function result(){
					   showMessage("删除成功");
				   }
				   });
					
				}
			});
			
		}

        
		//跳转新建页面
		function newpage(columnId){
			window.parent.topage('新建资料','/manage/data/item/addNew/'+columnId);
			
		}
		//打开编辑页面
		function edit(id){
		
		 window.parent.topage('编辑资料','/manage/data/item/edite/'+id);
		}
        function loadBookContentPage(catalogId){
		  window.parent.topage('资料内容','/manage/data/bookContent/addNew/'+catalogId+'/0/10');
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
		     <a  href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:15%" onclick="newpage('${column.id}')">新建</a> 
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:15%" onclick="delall()">删除</a>    
	      </div>
        
          <div style="margin:20px 0;"></div>
	     <table id="dg" class="easyui-datagrid" title="列表显示" style="width:100%;height:600px"
			data-options="singleSelect:false,method:'get'">
		<thead>
			<tr>
				<th field="ck" checkbox="true"></th>
				<th data-options="field:'id',width:'5%',align:'center'">ID</th>
				<th data-options="field:'name',width:'15%',align:'center'">名称</th>
                <th data-options="field:'page',width:'10%',align:'center'">页码</th>
                <th data-options="field:'filePath',width:'40%',align:'center'">附件地址</th>
                <th data-options="field:'state',width:'15%',align:'center'">状态</th>
                <th data-options="field:'createDate',width:'10%',align:'center'">创建日期</th>
                <th data-options="field:'remask',width:'40%',align:'center'">备注</th>
				<th data-options="field:'edit',width:'30%',align:'center'">操作</th>
			</tr>
		</thead>

		 <tbody>
		 <c:forEach items="${bookContents.dataSet}" var="temCatalog">
			     <tr>
                  <td></td>
				<td>${temCatalog.id}</td>
				<td>${temCatalog.name}</td>
                <td>${temCatalog.page}</td>
                <td>${temCatalog.filePath}</td>
                <td>${temCatalog.state}</td>
                <td>${temCatalog.createDate}</td>
                <td>${temCatalog.remask}</td>
				<td>
                <a href="#" onclick="loadBookContentPage(${temCatalog.id})">内容管理</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="#" onclick="edit(${temCatalog.id})">编辑</a>
                &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="del(${temCatalog.id})">删除</a>
                </td>
				</tr>
		</c:forEach>
		 </tbody>
	</table>   
	<form action="" method="get" id="userForm">
	         <div id="pp" class="easyui-pagination" style="background:#efefef;border:1px solid #ccc;"
				data-options="total:${bookContents.count},pageNumber:${bookContents.currentPage},pageSize:${bookContents.pageRecord},displayMsg:'',layout:['sep','first','prev','manual','next','last','efresh']">
			</div>
        </div>
        </div>
	</form>
	</body>
</html>