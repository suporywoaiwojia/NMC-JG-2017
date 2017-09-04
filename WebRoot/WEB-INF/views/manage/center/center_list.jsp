<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
    	<title>栏目管理</title>
    
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />    

			<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
			<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
			<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
			<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
			<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		
		
		
			
  	</head>
  
	<body>

		<div class="easyui-layout" data-options="fit:true" style="margin-top:20px;">

            
			<div id="right-panel" data-options="region:'center'" >
            <div style="padding:20px 0 0; margin-left:10px;">
		    <a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px" >新建</a>
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:100px" onclick="del()">删除</a>    
	       </div>
			<div style="margin:20px 0;"></div>
              <table id="table-datagrid" class="easyui-datagrid" title="" style="width:100%;height:600px"
		  	     data-options="singleSelect:false,method:'get'">
               <thead>
                    <tr>
                        <th field="ck" checkbox="true"></th>
                        <th data-options="field:'id',width:'15%',align:'center'">ID</th>
                        <th data-options="field:'title',width:'25%',align:'center'">名称</th>
                        <th data-options="field:'content',width:'35%',align:'center'">内容</th>
                        <th data-options="field:'edit',width:'25%',align:'center'">操作</th>
                    </tr>
                </thead>
        
                 <tbody>
                 
                 <c:forEach items="${pagingTools.dataSet}" var="centers">
                   <tr>
                          <td></td>
                        <td>${centers.id}</td>
                        <td>${centers.title}</td>
                        <td>${centers.content}</td>
                        <td><a href="#" onclick="edit('${centers.id}','${centers.title}')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="deldata('${centers.id}')">删除</a></td>
                        </tr>
                </c:forEach>
                </tbody>
             
             </table>  
          
			</div>
		</div>

	</body>
    </html>
	<script  type="text/javascript">

					//初始化页面，加载默认数据
				/*$('#table-datagrid').datagrid({
					url:'${basePath}action/center/list/1/20',
					columns:[[
					    {field:'ck',title:'',checkbox:'true'},
						{field:'id',title:'ID',width:'30%',align:'center'},
						{field:'title',title:'名称',width:'35%',align:'center'},
						{field:'edit',title:'操作',width:'35%',align:'center', formatter: function (value, row, index) {
								
								var Action = "<a href='javascript:void(0);' onclick='edit(" + row.id +",\""+row.name+ "\")'>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' onclick='deldata(" + row.id + ")'>删除</a>";
								return Action;
							}}
					]]
				});
				*/
			//跳转到编辑页面
           function edit(ids,name){
			   topPage(name,'${basePath}action/manage/columns/edit?id='+ids);
			}
			//新建页面方法
           function topPage(name,url){
			   $('#ea-tab').tabs('add',{
					title:name,
					href:url,
					closable:true
					
				});
			   }
		//依据node 的ID,假设其为父节点，有子目录，从服务器查询，如果不为空，则刷新表的数据面
			$(function(){
				
			//打开新建窗口
			$("#add").click(function(){
				var nodes = $('#tree').tree('getChecked');
				var url ='';
				if(nodes.length!=0){
			   	 url = '${basePath}action/manage/columns/add/'+nodes[0].id;
				}else{
				 url = '${basePath}action/manage/columns/add/1';
				}
				topPage("新建",url);
				//document.location.href = url;
			});
			})
		//删除
		function del(){
			$.messager.confirm('Confirm','数据删除不可恢复，确认删除吗？',function(r){
				if (r){
					//获取到选中的节点
					var checkbox=$('#table-datagrid').datagrid('getChecked');
					var ids;
					for( var a=0;a<checkbox.length;a++){
						//拼接ID
						if(a==0){
							ids=checkbox[a].id;
						}else{
							ids+=','+checkbox[a].id;
						}
					}
					alert(ids);
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
location.reload();
					});
				}
					
			}});
		}
	</script>
