<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
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
			<div data-options="region:'west',split:true" style="width:300px;">
				<ul id="tree" class="easyui-tree" data-options="url:'${basePath}action/manage/column',method:'get',lines:true,cascadeCheck:false,animate:true"></ul>
	    			
			</div>
            
			<div id="right-panel" data-options="region:'center'" >
            <div style="padding:20px 0 0; margin-left:10px;">
		    <a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px" >新建</a>
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:100px" onclick="del()">删除</a>    
	       </div>
			<div style="margin:20px 0;"></div>
            <div id="ea-tab"  class="easyui-tabs" style="width:100%;height:auto;">
            <div title="栏目管理" style="padding:20px;">
              <table id="table-datagrid" class="easyui-datagrid" title="" style="width:100%;height:600px"
		  	     data-options="singleSelect:false,method:'get'">
               <thead>
                    <tr>
                        <th field="ck" checkbox="true"></th>
                        <th data-options="field:'id',width:'30%',align:'center'">ID</th>
                        <th data-options="field:'name',width:'35%',align:'center'">名称</th>
                        <th data-options="field:'edit',width:'35%',align:'center'">操作</th>
                    </tr>
                </thead>
        
                 <tbody>
                 
                 <c:forEach items="${data}" var="item">
                   <tr>
                          <td>${item.id}</td>
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <td><a href="#" onclick="edit('${item.id}','${item.name}')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="deldata('${item.id}')">删除</a></td>
                        </tr>
                </c:forEach>
                </tbody>
             
             </table>  
             </div> 
            </div>
          
			</div>
		</div>

	</body>
    </html>
	<script  type="text/javascript">

					//初始化页面，加载默认数据
				$('#table-datagrid').datagrid({
					url:'${basePath}action/manage/columns/list/1',
					columns:[[
					    {field:'ck',title:'',checkbox:'true'},
						{field:'id',title:'ID',width:'30%',align:'center'},
						{field:'name',title:'名称',width:'35%',align:'center'},
						{field:'edit',title:'操作',width:'35%',align:'center', formatter: function (value, row, index) {
								
								var Action = "<a href='javascript:void(0);' onclick='edit(" + row.id +",\""+row.name+ "\")'>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' onclick='deldata(" + row.id + ")'>删除</a>";
								return Action;
							}}
					]]
				});
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
				$('#tree').tree({
				onClick: function(node){
					//依据node 的ID,假设其为父节点，有子目录，从服务器查询，如果不为空，则刷新表的数据
						var url = '${basePath}action/manage/columns/sublist/'+node.id;
						
						    $.ajax({  
							   type: "GET",  
							   url: url,
							   success: function(data){  
							   
							   if(data!=null&&data.length>0){
								   //刷新表数据
									$("#table-datagrid").datagrid("loadData", data);  
									
							   }else{
								  //没有子目录
								   }
							   }  
							});
					}
				});
				
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
