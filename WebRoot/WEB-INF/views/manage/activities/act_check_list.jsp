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
			var url="${basePath}action/check/act/list/CN/"+page;
			$("#pro_search").attr("action",url);
			$("#pro_search").submit();
		}
		//执行查询
		function searchlist(){
			pageRefresh(1);
		}
		
		//跳转编辑页面
		function edit(ids){
			$('#check_win').attr('src','${basePath}action/check/act/editpage/'+ids);
			$('#check').window('open');
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
			if('${act.state}'!='')
				$('#state').combobox('select','${act.state}');
			if('${act.project.id}'!='')
				$('#project').combobox('select','${act.project.id}');
			if('${act.type}'!='')
				$('#type').combobox('select','${act.type}');
			if('${act.level.id}'!='')
				$('#level').combobox('select','${act.level.id}');
		
		})
		</script>
	
	</head>
	  
	<body>
		<div style="width:100%;height:700px;">
		
          <div style="padding:20px 0 0;">
 
	      </div>
          <div style="margin:20px 0;">
		  <form id="pro_search" method="get" action="">
            <table>
		      <tr>
              
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;活动名称:</td>
                <td>
				  <input class="easyui-textbox" style="width:100%" name="name" value="${act.name}">
                </td>
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;主办方:</td>
                <td>
				  <input class="easyui-textbox" style="width:100%" name="sponsor" id="sponsor" value="${act.sponsor}">
                </td>
				
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;所属项目:</td>
                <td>
				 <select class="easyui-combobox" name="project.id" id='project' data-options="editable:false,panelHeight:'auto'">
                    <option value="0" >--请选择--</option>
					<c:forEach items="${p_list}" var="p_list" >
                    <option value="${p_list.id}" >${p_list.name}</option>
					</c:forEach>
                  </select>
				 
                </td>
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;活动级别:</td>
                <td>
				 <select class="easyui-combobox" name="level.id" id='level' data-options="editable:false,panelHeight:'auto'">
                    <option value="0" >--请选择--</option>
					<c:forEach items="${l_list}" var="l_list" >
                    <option value="${l_list.id}" >${l_list.name}</option>
					</c:forEach>
                  </select>
				 
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;活动类别:</td>
                <td>
	    		  <select class="easyui-combobox" name="type" id='type' data-options="editable:false,panelHeight:'auto'">
                    <option value="" >--请选择--</option>
					
                    <option value="01" >演出</option>
					<option value="02" >比赛</option>
                   	<option value="03" >会议</option>
					<option value="04" >田野调查</option>
					<option value="05" >培训</option>
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
			  
				<th field="name" width="20%" align="center">活动名称</th>
				<th field="type" width="10%" align="center">活动类别</th>
				<th field="zbf" width="20%" align="center">主办方</th>
				<th field="project" width="10%" align="center">所属项目</th>
			
				<th field="rs" width="5%" align="center">参加人数</th>
				<th field="jb" width="10%" align="center">活动级别</th>
				<th field="creatdate" width="10%" align="center">创建时间</th>
				<th field="state" width="9%" align="center">项目状态</th>
                <th field="caozuo" width="5%" align="center">
                 操作
                </th>
			  </tr>
	        </thead>
			<tbody>
			<c:forEach items="${pagingTools.dataSet}" var="act"  varStatus="status">
			<tr>
			
				<td>${act.name}</td>
				
				
				<td>
					<c:choose>  
						<c:when test="${act.type eq '01'}">
						演出
				  		</c:when>
						<c:when test="${act.type eq '02'}">
						比赛
				  		</c:when>
						<c:when test="${act.type eq '03'}">
						会议
				  		</c:when>
						<c:when test="${act.type eq '04'}">
						田野调查
				  		</c:when>
						<c:when test="${act.type eq '05'}">
						培训
				  		</c:when>
					</c:choose>
				</td>
				<td>
					${act.sponsor} </td>
				<td>${act.project.name} </td>
				
				<td>${act.peoplenum} </td>
				<td>${act.level.name} </td>
				<td><fmt:formatDate value='${act.creatdate}' pattern='yyyy-MM-dd'/></td>
				<td>
					 <c:choose>  
						<c:when test="${act.state eq '0'}">
						草稿
				  		</c:when>
						<c:when test="${act.state eq '1'}">
						待审
				  		</c:when>
						<c:when test="${act.state eq '2'}">
						审核中
				  		</c:when>
						<c:when test="${act.state eq '3'}">
						通过
				  		</c:when>
						<c:when test="${act.state eq '4'}">
						退回
				  		</c:when>
						<c:when test="${act.state eq '5'}">
						发布
				  		</c:when>
					</c:choose>
				</td>
				
				<td><a href="#" onclick="edit('${act.id}')">审核</a></td>
			</tr>
			</c:forEach>
		</tbody>
	      </table>
			<div id="pp" class="easyui-pagination" style="background:#efefef;border:1px solid #ccc;"
				data-options="total:${pagingTools.count},pageNumber:${pagingTools.currentPage},pageSize:${pagingTools.pageRecord},displayMsg:'',layout:['sep','first','prev','manual','next','last','efresh']">
			</div>
        </div>
		
	</div>
	<div id="check" class="easyui-window" title="专项活动信息审核" closed="true" style="width:1400px;height:700px;padding:5px;" minimizable="false" data-options="inline:true">
		<iframe id="check_win" width="100%" height="100%" frameborder="no" border="0"></iframe>
	</div>
	</body>
</html>