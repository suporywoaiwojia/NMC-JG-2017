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
			var url="${basePath}action/check/song/list/CN/"+page;
			$("#pro_search").attr("action",url);
			$("#pro_search").submit();
		}
		//执行查询
		function searchlist(){
			pageRefresh(1);
		}
		
		//跳转编辑页面
		function check(ids){
			
			$('#check_win').attr('src','${basePath}action/check/song/editpage/'+ids);
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
			if('${song.state}'!='')
				$('#state').combobox('select','${song.state}');
			if('${song.project.id}'!='')
				$('#project').combobox('select','${song.project.id}');
			if('${song.type}'!='')
				$('#type').combobox('select','${song.type}');
		
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
              
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;曲目名称:</td>
                <td>
				  <input class="easyui-textbox" style="width:100%" name="name" value="${song.name}">
                </td>
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;演唱/奏者:</td>
                <td>
				  <input class="easyui-textbox" style="width:100%" name="inheritor.name" id="inheritor" value="${song.inheritor.name}">
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
				
                <td>&nbsp;&nbsp;&nbsp;&nbsp;曲目类别:</td>
                <td>
	    		  <select class="easyui-combobox" name="type" id='type' data-options="editable:false,panelHeight:'auto'">
                    <option value="" >--请选择--</option>
					
                    <option value="1" >长调</option>
					<option value="2" >马头琴</option>
                   	<option value="3" >呼麦</option>
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
			  
				<th field="name" width="30%" align="center">曲目名称</th>
				<th field="type" width="10%" align="center">曲目类别</th>
				
				<th field="peple" width="10%" align="center">演唱/奏者</th>
				<th field="project" width="20%" align="center">所属项目</th>
				<th field="creatdate" width="10%" align="center">创建时间</th>
				<th field="state" width="10%" align="center">项目状态</th>
                <th field="caozuo" width="10%" align="center">
                 操作
                </th>
			  </tr>
	        </thead>
			<tbody>
			<c:forEach items="${pagingTools.dataSet}" var="song"  varStatus="status">
			<tr>
				
				<td>${song.name}</td>
				
				
				<td>
				<c:choose>  
						<c:when test="${song.type eq '1'}">
						长调
				  		</c:when>
						<c:when test="${song.type eq '2'}">
						马头琴
				  		</c:when>
						<c:when test="${song.type eq '3'}">
						呼麦
				  		</c:when>
					
					</c:choose>
				</td>
				
				<td>
				${song.inheritor.name} </td>
				<td>${song.project.name}</td>
				<td><fmt:formatDate value='${song.creatdate}' pattern='yyyy-MM-dd'/></td>
				<td>
					 <c:choose>  
						<c:when test="${song.state eq '0'}">
						草稿
				  		</c:when>
						<c:when test="${song.state eq '1'}">
						待审
				  		</c:when>
						<c:when test="${song.state eq '2'}">
						审核中
				  		</c:when>
						<c:when test="${song.state eq '3'}">
						通过
				  		</c:when>
						<c:when test="${song.state eq '4'}">
						退回
				  		</c:when>
						<c:when test="${song.state eq '5'}">
						发布
				  		</c:when>
					</c:choose>
				</td>
				
				<td><a href="#" onclick="check('${song.id}')">审核</a></td>
			</tr>
			</c:forEach>
		</tbody>
	      </table>
			<div id="pp" class="easyui-pagination" style="background:#efefef;border:1px solid #ccc;"
				data-options="total:${pagingTools.count},pageNumber:${pagingTools.currentPage},pageSize:${pagingTools.pageRecord},displayMsg:'',layout:['sep','first','prev','manual','next','last','efresh']">
			</div>
        </div>
		
	</div>
	<div id="check" class="easyui-window" title="曲目信息审核" closed="true" style="width:1400px;height:700px;padding:5px;" minimizable="false" data-options="inline:true">
		<iframe id="check_win" width="100%" height="100%" frameborder="no" border="0"></iframe>
	</div>
	</body>
</html>