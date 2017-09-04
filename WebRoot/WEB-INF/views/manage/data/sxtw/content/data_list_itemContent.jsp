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
			 var url="${basePath}action/manage/data/itemContent/query/"+pageNumber;
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
				   url:"${basePath}action/manage/data/itemContent/delete/"+id,
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
				   url:"${basePath}action/manage/data/itemContent/delete/"+ids,
				   success:function result(){
					   showMessage("删除成功");
				   }
				   });
					
				}
			});
			
		}

        
		//跳转新建页面
		function newpage(catalogId,itemId){
			var url = "";
			//依据服务器返回的两个数值，如果有目录，则依据目录创建内容；没有目录，有条目，怎依据条目创建内容
			if(catalogId!=null&&catalogId!=''&&catalogId!='undefined'){
			url='/manage/data/itemContent/addNew/'+catalogId+'/catalog';
			}else if(itemId!=null&&itemId!=''&&itemId!='undefined'){
			url='/manage/data/itemContent/addNew/'+itemId+'/item';	
			}
			window.parent.topage('新建内容',url);
		}
		//打开编辑页面
		function edit(itemContentId){
		 window.parent.topage('编辑内容','/manage/data/itemContent/edite/'+itemContentId);
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
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;资料页码:</td>
                <td>
				  <input class="easyui-textbox" style="width:100%" name="page" id="page" >
                </td>

                <td>&nbsp;&nbsp;&nbsp;&nbsp;所属目录:</td>
                <td>
	    		  <select id="contentsCatalog" class="easyui-combobox" name="contentsCatalog.id" style="width:300px;height:30px; text-align:center;">
                    
                    <c:forEach items="${catalogList}" var="catalog" >
                      
                      
                        <option value="${catalog.id}" <c:if test="${catalog.id==catalogTem.id}">selected="selected"</c:if> >${catalog.name}</option>	
                      
                       <c:if test="${catalogTem.id==null}">
                        <option value="">无</option>
                       </c:if>
                      	
                    </c:forEach>
         		 </select>   
                </td>
                </tr>
                <tr>
                 <td>&nbsp;&nbsp;&nbsp;&nbsp;审核状态:</td>
                <td>
				 <select class="easyui-combobox" name="state" id='state' data-options="editable:false,panelHeight:'auto'">
                    <option value="" >--请选择--</option>
					
                    <option value="1" >草稿</option>
					<option value="2" >待审</option>
                   	<option value="3" >通过</option>
                    <option value="4" >退回</option>
                   	<option value="5" >发布</option>
                  </select>
				 
                </td>
               <td>&nbsp;&nbsp;&nbsp;&nbsp;所属资料:</td>
                <td>
	    		  <select  class="easyui-combobox" name="contentsItem.id" id="contentsItem" style="width:300px;height:30px;text-align:center;" data-options="editable:false,panelHeight:'auto'" >
                      
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
		     <a  href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:15%" onclick="newpage('${catalogTem.id}','${itemTem.id}')">新建</a> 
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:15%" onclick="delall()">删除</a>    
	      </div>
        
          <div style="margin:20px 0;"></div>
	     <table id="dg" class="easyui-datagrid" title="列表显示" style="width:100%;height:600px"
			data-options="singleSelect:false,method:'get'">
		<thead>
			<tr>
				<th field="ck" checkbox="true"></th>
				<th data-options="field:'id',width:'5%',align:'center'">ID</th>
				<th data-options="field:'name',width:'10%',align:'center'">名称</th>
                <th data-options="field:'filePath',width:'20%',align:'center'">附件地址</th>
                <th data-options="field:'content',width:'10%',align:'center'">内容</th>
                <th data-options="field:'state',width:'10%',align:'center'">状态</th>
                <th data-options="field:'createDate',width:'10%',align:'center'">创建日期</th>
                <th data-options="field:'remask',width:'20%',align:'center'">备注</th>
				<th data-options="field:'edit',width:'15%',align:'center'">操作</th>
			</tr>
		</thead>

		 <tbody>
		 <c:forEach items="${pagingTool.dataSet}" var="temContent">
			     <tr>
                  <td></td>
				<td>${temContent.id}</td>
				<td>${temContent.name}</td>
                <td>${temContent.filePath}</td>
                <td>${temContent.content}</td>
                <td>${temContent.state}</td>
                <td>${temContent.createDate}</td>
                <td>${temContent.remask}</td>
				<td>
               
                <a href="#" onclick="edit('${temContent.id}')">编辑</a>
                &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="del('${temContent.id}')">删除</a>
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