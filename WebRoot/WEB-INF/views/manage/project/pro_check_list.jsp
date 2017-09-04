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
			var url="${basePath}action/check/project/list/CN/"+page;
			$("#pro_search").attr("action",url);
			$("#pro_search").submit();
		}
		//执行查询
		function searchlist(){
			pageRefresh(1);
		}
		//跳转编辑页面
		function check(ids){
			//window.parent.topage('编辑项目信息','check/project/editpage/'+ids);
				$('#check_win').attr('src','${basePath}action/check/project/editpage/'+ids);
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
						<c:when test="${pro.state eq '1'}">
						待审
				  		</c:when>
						<c:when test="${pro.state eq '2'}">
							审核中
				  		</c:when>
					</c:choose>
				</td>
				
				<td><a href="#" onclick="check('${pro.id}')">审核</a></td>
			</tr>
			</c:forEach>
		</tbody>
	      </table>
			<div id="pp" class="easyui-pagination" style="background:#efefef;border:1px solid #ccc;"
				data-options="total:${pagingTools.count},pageNumber:${pagingTools.currentPage},pageSize:${pagingTools.pageRecord},displayMsg:'',layout:['sep','first','prev','manual','next','last','efresh']">
			</div>
        </div>
		
	</div>
	<div id="check" class="easyui-window" title="项目信息审核" closed="true" style="width:1400px;height:700px;padding:5px;" minimizable="false" data-options="inline:true">
		<iframe id="check_win" width="100%" height="100%" frameborder="no" border="0"></iframe>
	</div>
	</body>
</html>