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
			var url="${basePath}action/inheritor/list/CN/"+page;
			$("#pro_search").attr("action",url);
			$("#pro_search").submit();
		}
		//执行查询
		function searchlist(){
			pageRefresh(1);
		}
		//跳转新建页面
		function newpage(){
			window.parent.topage('传承人内容','inheritor/newpage');
		}
		//跳转编辑页面
		function edit(ids){
			window.parent.topage('传承人内容','inheritor/editpage/'+ids);
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
			var url="${basePath}action/inheritor/del/"+ids;
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
			if('${in.state}'!='')
				$('#state').combobox('select','${in.state}');
			if('${in.column.id}'!='')
				$('#column').combobox('select','${in.column.id}');
			if('${in.sex}'!='')
				$('#sex').combobox('select','${in.sex}');
			if('${in.naction.id}'!='')
				$('#naction').combobox('select','${in.naction.id}');
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
              
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;姓名:</td>
                <td>
				  <input class="easyui-textbox" style="width:100%" name="name" value="${in.name}">
                </td>
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;性别:</td>
                <td>
				 <select class="easyui-combobox" name="sex" id='sex' data-options="editable:false,panelHeight:'auto'">
                    <option value="" >--请选择--</option>
                    <option value="1" >男</option>
					<option value="2" >女</option>
                  </select>
				 
                </td>
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;民族:</td>
                <td>
				<select class="easyui-combobox" name="naction.id" id='naction' data-options="editable:false,panelHeight:'auto'">
                    <option value="0" >--请选择--</option>
					<c:forEach items="${n_list}" var="n_list" >
                    <option value="${n_list.id}" >${n_list.name}</option>
					</c:forEach>
                   
                  </select>
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
                <td>&nbsp;&nbsp;&nbsp;&nbsp;状态:</td>
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
				<th field="name" width="10%" align="center">姓名</th>
				<th field="no" width="9.9%" align="center">性别</th>
				<th field="naction" width="10%" align="center">民族</th>
				<th field="category" width="10%" align="center">所属类别</th>
				<th field="project" width="10%" align="center">所属项目</th>
				<th field="level" width="10%" align="center">批次级别</th>
				<th field="year" width="10%" align="center">批准年度</th>
				<th field="creatdate" width="10%" align="center">创建时间</th>
				<th field="state" width="10%" align="center">项目状态</th>
                <th field="caozuo" width="10%" align="center">
                 操作
                </th>
			  </tr>
	        </thead>
			<tbody>
			<c:forEach items="${pagingTools.dataSet}" var="in"  varStatus="status">
			<tr>
				<td>${in.id}</td>
				<td>${in.name}</td>
				<td>
				 <c:choose>  
					<c:when test="${in.sex eq '1'}">
						男
					</c:when>
					<c:when test="${in.sex eq '2'}">
						女
					</c:when>
				</c:choose>
				</td>
				<td>${in.naction.name}</td>
				<td>${in.column.name}</td>
				<td>${in.project.name}</td>
				<td>
				<c:if test="${in.batch !=''}">
				第${in.batch}批
				</c:if>
				${in.level.name} </td>
				<td>${in.year}年</td>
				<td><fmt:formatDate value='${in.creatdate}' pattern='yyyy-MM-dd'/></td>
				<td>
					 <c:choose>  
						<c:when test="${in.state eq '0'}">
						草稿
				  		</c:when>
						<c:when test="${in.state eq '1'}">
						待审
				  		</c:when>
						<c:when test="${in.state eq '2'}">
						审核中
				  		</c:when>
						<c:when test="${in.state eq '3'}">
						通过
				  		</c:when>
						<c:when test="${in.state eq '4'}">
						退回
				  		</c:when>
						<c:when test="${in.state eq '5'}">
						发布
				  		</c:when>
						<c:when test="${in.state eq '6'}">
						信息不全
				  		</c:when>
					</c:choose>
				</td>
				
				<td><a href="#" onclick="edit('${in.id}')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="del('${in.id}')">删除</a></td>
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