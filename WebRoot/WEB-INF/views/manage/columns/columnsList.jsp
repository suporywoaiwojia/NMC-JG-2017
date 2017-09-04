<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
    	<title>栏目列表</title>
    
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
 		<script type="text/javascript">
			function edit(ids){
				//window.parent.parent.topage('编辑出版物','/manage/columns/edit?id=' + ids);
				var url = '${basePath}action/manage/columns/edit?id=' + ids;
				document.location.href = url;
			}
			function newpage(){

				var url = '${basePath}action/manage/columns/add/${columnId}';
				document.location.href = url;
			}
			//删除全部
		function delall(){
			$.messager.confirm('Confirm','数据删除不可恢复，确认删除吗？',function(r){
				if (r){
					//获取被选中的复选框
					var checkbox=$('#dg').datagrid('getChecked');
					var ids;
					for( var a=0;a<checkbox.length;a++){
						//拼接ID
						if(a==0)
							ids=checkbox[a].ck;
						else
							ids+=','+checkbox[a].ck;
					}
					deldata(ids);
				}
			});
			
		}
		//删除
		function del(ids){
			$.messager.confirm('Confirm','数据删除不可恢复，确认删除吗？',function(r){
				if (r){
					deldata(ids);
				}
			});
		}
		function deldata(ids){
			var url="${basePath}action/manage/columns/delete/"+ids;
			$.ajax({type:"GET", url:url,dataType:"text", success:function(datas) {
				if(datas=='0'){
					$.messager.alert('消息','删除失败！','info');
				}else if(datas=='1'){
					$.messager.alert('消息','删除成功！','info',function(){
						var url="${basePath}action/manage/columns/list/";
						$("#column").attr("action",url);
						$("#column").submit();
					});
				}
					
			}});
		}
		</script>
  	</head>
  
  	<body>
  		<div style="width:100%;">
		
          <div style="padding:20px 0 0;">
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px" onclick="newpage()">新建</a>
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:100px" onclick="delall()">删除</a>
    
	      </div>
          <div style="margin:20px 0;">
		 	<form id="column"></form>
          </div>
          <div style="margin:20px 0;"></div>	
	      <table id="dg" title="查询结果" style="width:100%;height:560px"   class="easyui-datagrid"  data-options="
				rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:false">
	        <thead>
			  <tr>
			  	<th field="ck" checkbox="true"></th>
				<th field="id" width="9.9%" align="center">ID</th>
				<th field="name" width="30%" align="center">栏目名称</th>
				<th field="order" width="20%" align="center">显示顺序</th>
				<th field="state" width="20%" align="center">栏目状态</th>
                <th field="caozuo" width="20%" align="center">
                 操作
                </th>
			  </tr>
	        </thead>
			<tbody>
			<c:forEach items="${columns}" var="column">
			<tr>
				<td>${column.id}</td>
				<td>${column.id}</td>
				<td>${column.name}</td>
				<td>${column.columnOrder}</td>
				<td>
					<c:choose>
						<c:when test="${column.state eq '1'}">开启</c:when>
						<c:otherwise>关闭</c:otherwise>
		    		</c:choose>
				</td>
				
				<td><a href="#" onclick="edit('${column.id}')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="del('${column.id}')">删除</a></td>
			</tr>
			</c:forEach>
		</tbody>
	      </table>
			
        </div>
		
	</div>
  	</body>
</html>