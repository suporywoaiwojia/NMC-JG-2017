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
			var url="${basePath}action/check/pub/list/CN/"+page;
			$("#pro_search").attr("action",url);
			$("#pro_search").submit();
		}
		//执行查询
		function searchlist(){
			pageRefresh(1);
		}
		//跳转编辑页面
		function check(ids){
			$('#check_win').attr('src','${basePath}action/check/pub/editpage/'+ids);
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
			if('${pub.state}'!='')
				$('#state').combobox('select','${pub.state}');
			if('${pub.project.id}'!='')
				$('#project').combobox('select','${pub.project.id}');
			if('${pub.type}'!='')
				$('#type').combobox('select','${pub.type}');
			if('${pub.publishing.id}'!='')
				$('#publishing').combobox('select','${pub.publishing.id}');
		
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
              
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;出版物名称:</td>
                <td>
				  <input class="easyui-textbox" style="width:100%" name="name" value="${pub.name}">
                </td>
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;作者:</td>
                <td>
				  <input class="easyui-textbox" style="width:100%" name="inheritor.name" id="inheritor" value="${pub.inheritor.name}">
                </td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;国际标准书号:</td>
                <td>
				  <input class="easyui-textbox" style="width:100%" name="isbn" id="isbn" value="${pub.isbn}">
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
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;出版机构:</td>
                <td>
				 <select class="easyui-combobox" name="publishing.id" id='publishing' data-options="editable:false,panelHeight:'auto'">
                    <option value="0" >--请选择--</option>
					<c:forEach items="${pub_list}" var="pub_list" >
                    <option value="${pub_list.id}" >${pub_list.name}</option>
					</c:forEach>
                  </select>
				 
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;出版物类别:</td>
                <td>
	    		  <select class="easyui-combobox" name="type" id='type' data-options="editable:false,panelHeight:'auto'">
                    <option value="" >--请选择--</option>
					
                    <option value="01" >专著</option>
					<option value="02" >论文</option>
                   	<option value="03" >曲集</option>
					<option value="04" >专辑</option>
					<option value="05" >报道</option>
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
			  	
				<th field="name" width="20%" align="center">出版物名称</th>
				<th field="type" width="10%" align="center">出版物类别</th>
				<th field="inher" width="10%" align="center">作者</th>
				<th field="isbn" width="15%" align="center">国际标准书号</th>
				<th field="publising" width="10%" align="center">出版机构</th>
				<th field="project" width="10%" align="center">所属项目</th>
				<th field="creatdate" width="10%" align="center">创建时间</th>
				<th field="state" width="9%" align="center">项目状态</th>
                <th field="caozuo" width="5%" align="center">
                 操作
                </th>
			  </tr>
	        </thead>
			<tbody>
			<c:forEach items="${pagingTools.dataSet}" var="pub"  varStatus="status">
			<tr>
			
				<td>${pub.name}</td>
				
				
				<td>
					<c:choose>  
						<c:when test="${pub.type eq '01'}">
						专著
				  		</c:when>
						<c:when test="${pub.type eq '02'}">
						论文
				  		</c:when>
						<c:when test="${pub.type eq '03'}">
						曲集
				  		</c:when>
						<c:when test="${pub.type eq '04'}">
						专辑
				  		</c:when>
						<c:when test="${pub.type eq '05'}">
						报道
				  		</c:when>
					</c:choose>
				</td>
				<td>
					${pub.inheritor.name} </td>
				<td>${pub.isbn} </td>
				<td>
				${pub.publishing.name} </td>
				<td>${pub.project.name}</td>
				<td><fmt:formatDate value='${pub.creatdate}' pattern='yyyy-MM-dd'/></td>
				<td>
					 <c:choose>  
						<c:when test="${pub.state eq '0'}">
						草稿
				  		</c:when>
						<c:when test="${pub.state eq '1'}">
						待审
				  		</c:when>
						<c:when test="${pub.state eq '2'}">
						审核中
				  		</c:when>
						<c:when test="${pub.state eq '3'}">
						通过
				  		</c:when>
						<c:when test="${pub.state eq '4'}">
						退回
				  		</c:when>
						<c:when test="${pub.state eq '5'}">
						发布
				  		</c:when>
					</c:choose>
				</td>
				
				<td><a href="#" onclick="check('${pub.id}')">审核</a></td>
			</tr>
			</c:forEach>
		</tbody>
	      </table>
			<div id="pp" class="easyui-pagination" style="background:#efefef;border:1px solid #ccc;"
				data-options="total:${pagingTools.count},pageNumber:${pagingTools.currentPage},pageSize:${pagingTools.pageRecord},displayMsg:'',layout:['sep','first','prev','manual','next','last','efresh']">
			</div>
        </div>
		
	</div>
	<div id="check" class="easyui-window" title="出版物信息审核" closed="true" style="width:1400px;height:700px;padding:5px;" minimizable="false" data-options="inline:true">
		<iframe id="check_win" width="100%" height="100%" frameborder="no" border="0"></iframe>
	</div>
	</body>
</html>