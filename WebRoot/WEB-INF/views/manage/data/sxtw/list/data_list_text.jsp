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
			  var url = '${basePath}action/manage/data/item/${column.id}/'+pageNumber+'/10';
		
			  $("#userForm").attr("action",url);
			  $("#userForm").submit();
			}else if(type=='query'){//查询状态下的翻页
			 var url="${basePath}action/manage/data/item/query/"+pageNumber;
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
				   url:"${basePath}action/manage/data/item/delete/"+id,
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
				   url:"${basePath}action/manage/data/item/delete/"+ids,
				   success:function result(){
					   showMessage("删除成功");
				   }
				   });
					
				}
			});
			
		}

        
		//跳转新建页面
		function newpage(){
		
			window.parent.topage('新建资料','manage/data/item/addNew/'+'${column.id}');
			
		}
	  //open approve page
	   function loadApprovePage(itemId){
			 window.top.topage('审核','check/manage/data/item/edite/'+ itemId);
			}
		//打开编辑页面
		function edit(id){
				
			window.parent.topage('编辑资料','/manage/data/item/edite/'+id);
			}
       //打开目录管理页面
       function loadCatalogPage(itemId){
			window.parent.topage('资料目录','/manage/data/catalog/'+itemId+'/0/10');
		}
		//进入到目录列表
       function loadCatalogApprovePage(itemId){
		  window.parent.topage('目录审核','check/manage/data/catalog/'+itemId+'/0/10'); 
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
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;作者:</td>
                <td>
				  <input class="easyui-textbox" style="width:100%" name="author" id="author" >
                </td>
				

                <td>&nbsp;&nbsp;&nbsp;&nbsp;资料类型:</td>
                <td>
	    		  <select id="itemType" class="easyui-combobox" name="itemType.id" style="width:300px;height:30px; text-align:center;">
                    
                    <c:forEach items="${typeList}" var="type" >
                      
                         <option value="${type.id}" <c:if test="${type.name=='全部'}">selected="selected"</c:if> >${type.name}</option>	
                      	
                    </c:forEach>
         		 </select>   
                </td>
                 <td>&nbsp;&nbsp;&nbsp;&nbsp;语言类型:</td>
                <td>
	    		  <select id="itemLanguage" class="easyui-combobox" name="itemLanguage.id" style="width:300px;height:30px; text-align:center;">
                    
                    <c:forEach items="${languageList}" var="language" >
                      
                         <option value="${language.id}" <c:if test="${language.name=='全部'}">selected="selected"</c:if> >${language.name}</option>
                      	
                    </c:forEach>
         		 </select>   
                </td>
                </tr>
                <tr>
               <td>&nbsp;&nbsp;&nbsp;&nbsp;所属栏目:</td>
                <td>
	    		  <select  class="easyui-combobox" name="parent.id" id="parent" style="width:300px;height:30px;text-align:center;" data-options="editable:false,panelHeight:'auto'" >
                      
                     <c:forEach items="${columnList}" var="col" >
                        <option value="${col.id}" <c:if test="${column.id==col.id}" > selected="selected"</c:if> >${col.name}</option>		
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
		     <a  href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:15%" onclick="newpage()">新建</a> 
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:15%" onclick="delall()">删除</a>    
	      </div>
        
          <div style="margin:20px 0;"></div>
	     <table id="dg" class="easyui-datagrid" title="列表显示" style="width:100%;height:600px"
			data-options="singleSelect:false,method:'get'">
		<thead>
			<tr>
				<th field="ck" checkbox="true"></th>
				<th data-options="field:'id',width:'5%',align:'center'">ID</th>
				<th data-options="field:'author',width:'10%',align:'center'">作者</th>
				<th data-options="field:'name',width:'10%',align:'center'">名称</th>
                  <th data-options="field:'itemType',width:'10%',align:'center'">类型</th>
				<th data-options="field:'itemCountry',width:'10%',align:'center'">国别</th>
                <th data-options="field:'itemLanguage',width:'10%',align:'center'">语言</th>
				<th data-options="field:'address',width:'15%',align:'center'">作者单位</th>
				<th data-options="field:'edit',width:'30%',align:'center'">操作</th>
			</tr>
		</thead>

		 <tbody>
		 <c:forEach items="${pagingTool.dataSet}" var="textConData">
			     <tr>
                  <td></td>
				<td>${textConData.id}</td>
				<td>${textConData.author}</td>
                <td>${textConData.name}</td>
		    	<td>${textConData.itemType.name}</td>
                <td>${textConData.itemCountry.name}</td>
				<td>${textConData.itemLanguage.name}</td>
                <td>${textConData.address}</td>
				<td>
                
                
                <c:if test="${approveState=='0'}">
                <a href="#" onclick="loadCatalogPage(${textConData.id})">目录管理</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="#" onclick="edit(${textConData.id})">编辑</a>
                &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="del(${textConData.id})">删除</a>
                </c:if>
                 <c:if test="${approveState=='1'}">
                 <a href="#" onclick="loadCatalogApprovePage(${textConData.id})">目录审核管理</a>&nbsp;&nbsp;&nbsp;&nbsp;
                 <a href="#" onclick="loadApprovePage(${textConData.id})">审核</a>
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