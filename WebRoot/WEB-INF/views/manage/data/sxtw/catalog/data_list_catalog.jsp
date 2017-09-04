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
		 var pageType="normal";			
		//翻页
		$(function(){
			//翻页操作
			$('#pp').pagination({
				onSelectPage:function(pageNumber, pageSize){
					$(this).pagination('loading');
				//	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
				    pageNum = pageNumber;
					pageRefresh(pageNumber,pageType);
					$(this).pagination('loaded');
					
				}
			});
		})
				//翻页查询
		function pageRefresh(pageNumber,type){
			//通用状态下查询到的数据的翻页
			if(type=='nromal'){
			  var url = '${basePath}action//'+pageNumber+'/10';
		
			  $("#userForm").attr("action",url);
			  $("#userForm").submit();
			}else if(type=='query'){//查询状态下的翻页
			 var url="${basePath}action/manage/data/catalog/query/"+pageNumber;
		     $("#pro_search").serialize();
			 $("#pro_search").attr("action",url);
			 $("#pro_search").submit();
			}
			
		}
		
		//查询
		function searchlist(){
		 pageType = "query";
	     pageRefresh(0,pageType);		
		}
			//删除一条数据
       function del(id){
             $.messager.confirm('Confirm','数据删除不可恢复，确认删除吗？',function(r){
				if (r){
	             $.ajax({
				   type:'GET',
				   url:"${basePath}action/manage/data/catalog/delete/"+id,
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
				   url:"${basePath}action/manage/data/catalog/delete/"+ids,
				   success:function result(){
					   showMessage("删除成功");
				   }
				   });
					
				}
			});
			
		}

        
		//跳转新建页面
		function newpage(itemId){
			window.parent.topage('新建资料','/manage/data/catalog/addNew/'+itemId);
			
		}
		//打开编辑页面
		function edit(catalogId){
		
		 window.parent.topage('编辑资料','/manage/data/catalog/edite/'+catalogId);
		}
		//打开资料内容列表页面
        function loadBookContentPage(catalogId){
		  window.parent.topage('资料内容','/manage/data/catalog/itemContent/'+catalogId+'/0/10');
		}
        //进入到内容审核列表
       function loadContentApprovePage(catalogId){
			window.parent.topage('内容审核管理','check/manage/data/catalog/itemContent/'+catalogId+'/0/10');
		}
		//进入到审核页
       function loadApprovePage(itemId){
		  window.parent.topage('审核','check/manage/data/catalog/edite/'+itemId); 
		   }

		//弹出的的提示信息,刷新页面
		function showMessage(message){
			$.messager.alert('消息',message,'info',function(){location.reload();});
		}
	</script>
	
	</head>
	  
	<body>

		<div style="width:100%;height:700px;">
		 <div style="margin:20px 0;">
		  <form id="pro_search" method="post" action="">
            <table>
		      <tr>
              
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;资料名称:</td>
                <td>
				  <input class="easyui-textbox" style="width:100%" name="name" id="name">
                </td>
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;目录页码:</td>
                <td>
				  <input class="easyui-textbox" style="width:100%" name="pageRange" id="pageRange" >
                </td>
               <td>&nbsp;&nbsp;&nbsp;&nbsp;所属资料:</td>
                <td>
	    		  <select  class="easyui-combobox" name="catalogItem.id" id="catalogItem" style="width:300px;height:30px;text-align:center;" data-options="editable:false,panelHeight:'auto'" >
                      
                     <c:forEach items="${itemList}" var="item" >
                        <option value="${item.id}" <c:if test="${itemTem.id==item.id}" > selected="selected"</c:if> >${item.name}</option>		
                    </c:forEach>
          </select>     
                </td>
                <td>			 
                </td>
               
                <td>
				  <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:100px; margin-left:20px;" onclick="searchlist()">查询</a>
                </td>
              </tr>
            </table>
                </form>
          </div>
          <div style="padding:20px 0 0;">
		     <a  href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:15%" onclick="newpage('${itemTem.id}')">新建</a> 
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
                <th data-options="field:'pageRange',width:'10%',align:'center'">页码</th>
                <th data-options="field:'filePath',width:'40%',align:'center'">附件地址</th>
				<th data-options="field:'edit',width:'30%',align:'center'">操作</th>
			</tr>
		</thead>

		 <tbody>
		 <c:forEach items="${pagingTool.dataSet}" var="temCatalog">
			     <tr>
                  <td></td>
				<td>${temCatalog.id}</td>
				<td>${temCatalog.name}</td>
                <td>${temCatalog.pageRange}</td>
                <td>${temCatalog.filePath}</td>
				<td>
                 <c:if test="${approveState=='0'}">
                <a href="#" onclick="loadBookContentPage(${temCatalog.id})">内容管理</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="#" onclick="edit(${temCatalog.id})">编辑</a>
                &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="del(${temCatalog.id})">删除</a>
                </c:if>
                 <c:if test="${approveState=='1'}">
                   <a href="#" onclick="loadContentApprovePage(${temCatalog.id})">内容审核管理</a>&nbsp;&nbsp;&nbsp;&nbsp;
                   <a href="#" onclick="loadApprovePage(${temCatalog.id})">审核</a>
                 </c:if>
                </td>
				</tr>
		</c:forEach>
		 </tbody>
	</table>   
	<form action="" method="get" id="userForm">
	         <div id="pp" class="easyui-pagination" style="background:#efefef;border:1px solid #ccc;"
				data-options="total:${pagingTool.count},pageNumber:${pagingTool.currentPage},pageSize:${pagingTool.pageRecord},displayMsg:'',layout:['sep','first','prev','manual','next','last','efresh']">
			</div>
        </div>
        </div>
	</form>
	</body>
</html>