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
		
		<title>码表信息</title>
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		<script type="text/javascript">
		//列表页面跳转
		function pageRefresh(page){
			var url="${basePath}action/project/list/CN/"+page;
			$("#pro_search").attr("action",url);
			$("#pro_search").submit();
		}
		//执行查询
		function searchlist(){
			pageRefresh(1);
		}
		//跳转新建页面
		function newpage(){
			window.parent.topage('项目内容','project/newpage');
		}
		//跳转编辑页面
		function edit(ids){
			window.parent.topage('项目内容','project/editpage/'+ids);
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
					deldata(ids)
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
			var url="${basePath}action/project/del/"+ids;
			$.ajax({type:"GET", url:url,dataType:"text", success:function(datas) {
				if(datas=='0'){
					$.messager.alert('消息','删除失败！','info');
				}else if(datas=='1'){
					$.messager.alert('消息','删除成功！','info',function(){searchlist();});
				}
					
			}});
		}
		$(function(){
			//翻页操作
			$('#pp').pagination({
				onSelectPage:function(pageNumber, pageSize){
					$(this).pagination('loading');
					//alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
					pageRefresh(pageNumber);
					$(this).pagination('loaded');
					
				}
			});
			//查询下拉项数据回显
			if('${pro.state}'!='')
				$('#state').combobox('select','${pro.state}');
			if('${pro.column.id}'!='')
				$('#column').combobox('select','${pro.column.id}');
			
			
		})
		</script>
	
	</head>
	  
	<body>
		<div style="width:100%;height:700px;">
		
          <div style="padding:20px 0 0;">
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px" onclick="newpage()">新建</a>
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:100px" onclick="delall()">删除</a>
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px">发布</a>	    
	      </div>
          <div style="margin:20px 0;">
		  <form id="pro_search" method="get" action="">
            <table>
		      <tr>
              
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;项目名称:</td>
                <td>
				  <input class="easyui-textbox" style="width:100%" name="name" value="${pro.name}">
                </td>
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;项目编号:</td>
                <td>
				  <input class="easyui-textbox" style="width:100%" name="no" value="${pro.no}">
                </td>
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;批准年度:</td>
                <td>
				  <input class="easyui-textbox" style="width:100%" name="year" value="${pro.year}">
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;所属类别:</td>
                <td>
	    		  <select class="easyui-combobox" name="column.id" id='column' data-options="editable:false,panelHeight:'auto'">
                    <option value="0" >--请选择--</option>
					<c:forEach items="${c_list}" var="c_list" >
                    <option value="${c_list.id}" >${c_list.name}</option>
					</c:forEach>
                   
                  </select>
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;项目状态:</td>
                <td>
	    		  <select class="easyui-combobox" name="state" data-options="editable:false,panelHeight:'auto'" id="state">
                    <option value="" >--请选择--</option>
                    <option value="0" >草稿</option>
                    <option value="1">待审</option>
                    <option value="2" >审核中</option>
					<option value="3" >通过</option>
					<option value="4" >退回</option>
					<option value="5">发布</option>
                  </select>
                </td>
               
               
               
                <td>
				  <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:100px; margin-left:20px;" onclick="searchlist()">查询</a>
                </td>
              </tr>
            </table>
                </form>
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
				<th field="name" width="20%" align="center">项目名称</th>
				<th field="no" width="10%" align="center">项目编号</th>
				<th field="category" width="10%" align="center">所属类别</th>
				<th field="level" width="10%" align="center">批次级别</th>
				<th field="year" width="9.9%" align="center">批准年度</th>
				<th field="creatdate" width="10%" align="center">创建时间</th>
				<th field="state" width="10%" align="center">项目状态</th>
				
                <th field="caozuo" width="20%" align="center">
                 操作
                </th>
			  </tr>
	        </thead>
			<tbody>
			<c:forEach items="${pagingTools.dataSet}" var="pro"  varStatus="status">
			<tr>
				<td>${pro.id}</td>
				<td>${pro.name}</td>
				<td>${pro.no}</td>
				<td>${pro.column.name}</td>
				<td>
				<c:if test="${pro.batch !=''}">
				第${pro.batch}批
				</c:if>
				${pro.level.name} </td>
				<td>${pro.year}年</td>
				<td><fmt:formatDate value='${pro.creatdate}' pattern='yyyy-MM-dd'/></td>
				<td>
					 <c:choose>  
						<c:when test="${pro.state eq '0'}">
						草稿
				  		</c:when>
						<c:when test="${pro.state eq '1'}">
						待审
				  		</c:when>
						<c:when test="${pro.state eq '2'}">
						审核中
				  		</c:when>
						<c:when test="${pro.state eq '3'}">
						通过
				  		</c:when>
						<c:when test="${pro.state eq '4'}">
						退回
				  		</c:when>
						<c:when test="${pro.state eq '5'}">
						发布
				  		</c:when>
					</c:choose>
				</td>
				
				<td><a href="#" onclick="edit('${pro.id}')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="del('${pro.id}')">删除</a></td>
			</tr>
			</c:forEach>
		</tbody>
	      </table>
			<div id="pp" class="easyui-pagination" style="background:#efefef;border:1px solid #ccc;"
				data-options="total:${pagingTools.count},pageNumber:${pagingTools.currentPage},pageSize:${pagingTools.pageRecord},displayMsg:'',layout:['sep','first','prev','manual','next','last','efresh']">
			</div>
        </div>
		
	</div>
	
	</body>
</html>