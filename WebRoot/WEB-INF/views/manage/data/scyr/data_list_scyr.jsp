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
					pageRefresh(pageNumber,'normal');
					$(this).pagination('loaded');
					
				}
			});
		})
		
		//翻页查询
		function pageRefresh(pageNumber,type){
			alert(type);
			//通用状态下查询到的数据的翻页
			if(type=='nromal'){
			  var url = '${basePath}action/manage/data/rapper/${column.id}/'+pageNumber+'/10';
			 
			  $("#userForm").attr("action",url);
			  $("#userForm").submit();
			}else if(type=='query'){//查询状态下的翻页
			 var url="${basePath}action/manage/data/rapper/query/"+pageNumber;
		     $("#pro_search").serialize();
			 $("#pro_search").attr("action",url);
			 $("#pro_search").submit();
			}
			pageNumber++;
		}
		//查询
		function searchlist(){
	     pageRefresh(0,'query');		
		}
			//删除一条数据
       function del(id){
             $.messager.confirm('Confirm','数据删除不可恢复，确认删除吗？',function(r){
				if (r){
	             $.ajax({
				   type:'GET',
				   url:"${basePath}action/manage/data/rapper/delete/"+id,
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
				   url:"${basePath}action/manage/data/rapper/delete/"+ids,
				   success:function result(){
					   showMessage("删除成功");
				   }
				   });
					
				}
			});
			
		}

        
		//跳转新建页面
		function newpage(columnId){
			
			window.parent.topage('新建资料','manage/data/rapper/addNew/'+columnId);
			
		}
		//打开编辑页面
			function edit(contentId,state){
				if(state!="2"){
			      window.parent.topage('编辑资料','manage/data/rapper/edite/'+contentId);
				}else{
				  $.messager.alert('消息','正在审核中，请耐心等待。','info');
				}
			}
        //打开审核页面
		   function loadApprovePage(contentId){
			 window.top.topage('编辑资料','check/manage/data/rapper/approve/'+ contentId);
			}

			//弹出的的提示信息,刷新页面
			function showMessage(message){
				$.messager.alert('消息',message,'info',function(){location.reload();});
			}
			function publish(contentId,state){
				//通过的状态下才能发布
				if(state=="3"){
					$.ajax({
				     type:'GET',
				     url:"${basePath}action/manage/data/rapper/publish/"+contentId,
				     success:function result(){
					  $.messager.alert('消息','发布成功。','info',function(){location.reload();});
				     }
				     });
				}else{
				$.messager.alert('消息','审核通过才能发布。','info');
				}
			}
	</script>
	
	</head>
	  
	<body>

		<div style="width:100%;height:700px;">
				
          <div style="margin:20px 0;">
		  <form id="pro_search" method="post" action="">
            <table>
		      <tr>
              
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;姓名:</td>
                <td>
				  <input class="easyui-textbox" style="width:100%" name="name" id="name">
                </td>
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;性别:</td>
                <td>
				  <input class="easyui-textbox" style="width:100%" name="sex" id="sex" >
                </td>
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

                <td>&nbsp;&nbsp;&nbsp;&nbsp;国籍:</td>
                <td>
	    		  <select id="country" class="easyui-combobox" name="country.id" style="width:300px;height:30px; text-align:center;">
                    
                    <c:forEach items="${countryList}" var="countryTem" >
                      
                         <option value="${countryTem.id}" <c:if test="${countryTem.name=='全部'}">selected="selected"</c:if> >${countryTem.name}</option>	
                      	
                    </c:forEach>
         		 </select>   
                </td>
               
                <td>
				  <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:100px; margin-left:20px;" onclick="searchlist()">查询</a>
                </td>
              </tr>
            </table>
                </form>
          </div>
          <div style="padding:20px 0 0;">
		     <a  href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:15%" onclick="newpage(${column.id})">新建</a> 
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:15%" onclick="delall()">删除</a>    
	      </div>
        
          <div style="margin:20px 0;"></div>
	     <table id="dg" class="easyui-datagrid" title="列表显示" style="width:100%;height:600px"
			data-options="singleSelect:false,method:'get'">
		<thead>
			<tr>
				<th field="ck" checkbox="true"></th>
				<th data-options="field:'id',width:'5%',align:'center'">ID</th>
				<th data-options="field:'createUser.userName',width:'10%',align:'center'">发布人</th>
				<th data-options="field:'name',width:'15%',align:'center'">姓名</th>
                <th data-options="field:'type',width:'10%',align:'center'">性别</th>
				<th data-options="field:'summary',width:'10%',align:'center'">简介</th>
                <th data-options="field:'country',width:'10%',align:'center'">国籍</th>
                <th data-options="field:'state',width:'10%',align:'center'">状态</th>
				<th data-options="field:'createDate',width:'10%',align:'center'">创建日期</th>
				<th data-options="field:'edit',width:'20%',align:'center'">操作</th>
			</tr>
		</thead>

		 <tbody>
		 <c:forEach items="${pagingTool.dataSet}" var="textConData">
			     <tr>
                  <td></td>
				<td>${textConData.id}</td>
				<td>${textConData.publishU.userName}</td>
                <td>${textConData.name}</td>
				<td>${textConData.sex}</td>
                <td>${textConData.summary}</td>
                <td>${textConData.country.name}</td>
                <td>
                <c:if test="${textConData.state=='1'}">草稿</c:if>
                <c:if test="${textConData.state=='2'}">待审</c:if>
                <c:if test="${textConData.state=='3'}">通过</c:if>
                <c:if test="${textConData.state=='4'}">退回</c:if>
                <c:if test="${textConData.state=='5'}">发布</c:if>
                
                </td>
                <td>${textConData.createDate}</td>
				<td>
                <c:if test="${approveState==1}"> <a href="#" onclick="loadApprovePage('${textConData.id}')">审核</a></c:if>
                <c:if test="${approveState==0}">
                <a href="#" onclick="edit('${textConData.id}','${textConData.state}')">编辑</a> 
                &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="del(${textConData.id})">删除</a>
                &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="publish('${textConData.id}','${textConData.state}')">发布</a>
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