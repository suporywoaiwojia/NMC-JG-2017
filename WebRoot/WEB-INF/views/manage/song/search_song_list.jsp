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
			var url="${basePath}action/song/search/CN/"+page;
			$("#pro_search").attr("action",url);
			$("#pro_search").submit();
		}
		//执行查询
		function searchlist(){
			pageRefresh(1);
		}
		//跳转新建页面
		function newpage(){
			window.parent.topage('新建曲目','song/newpage');
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
		<div style="width:100%;height:550px;">
		
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
				
				   <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px;margin-left:20px;" onclick="addnew()">添加</a>
				    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="width:100px;margin-left:20px;" onclick="subsong()">确定</a>
                </td>
              </tr>
            </table>
                </form>
          </div>
          <div style="margin:20px 0;"></div>	
	      <table id="dg" title="查询结果" style="width:100%;height:500px"   class="easyui-datagrid"  data-options="
				rownumbers:true,
				singleSelect:true,
				autoRowHeight:false,
				pagination:false">
	        <thead>
			  <tr>
			  	<th field="ck" checkbox="true"></th>
				<th field="name" width="20%" align="center">曲目名称</th>
				<th field="type" width="20%" align="center">曲目类别</th>
				<th field="style" width="20%" align="center">所属风格</th>
				<th field="peple" width="9.9%" align="center">演唱/奏者</th>
				<th field="project" width="20%" align="center">所属项目</th>
				<th field="creatdate" width="10%" align="center">创建时间</th>
				
              
			  </tr>
	        </thead>
			<tbody>
			<c:forEach items="${pagingTools.dataSet}" var="song"  varStatus="status">
			<tr>
				<td>${song.id}</td>
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
				<c:choose>  
						<c:when test="${song.type eq '1'}">
							${song.songStyle.name}
				  		</c:when>
						<c:when test="${song.type eq '2'}">
							${song.mtStyle.name}
				  		</c:when>
						<c:when test="${song.type eq '3'}">
							${song.hmStyle.name}
				  		</c:when>
					
					</c:choose>
				</td>
				<td>
				${song.inheritor.name} </td>
				<td>${song.project.name}</td>
				<td><fmt:formatDate value='${song.creatdate}' pattern='yyyy-MM-dd'/></td>
				
			
			</tr>
			</c:forEach>
		</tbody>
	      </table>
			<div id="pp" class="easyui-pagination" style="background:#efefef;border:1px solid #ccc;"
				data-options="total:${pagingTools.count},pageNumber:${pagingTools.currentPage},pageSize:${pagingTools.pageRecord},displayMsg:'',layout:['sep','first','prev','manual','next','last','efresh']">
			</div>
        </div>
		
	</div>
	
	</div>
	</body>
	<script type="text/javascript">
	function addnew(){
		window.parent.parent.topage('新建曲目','song/newpage');
	}
	function subsong(){
		var checkbox=$('#dg').datagrid('getChecked');
		var ids;
		for( var a=0;a<checkbox.length;a++){
				
				ids=checkbox[a].ck;
			
				window.parent.getsongfile(ids);
				$(window.parent.$('#song_search').window('close'));
		}
	}
	</script>
</html>