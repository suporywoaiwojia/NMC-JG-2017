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
			var url="${basePath}action/inheritor/search/CN/"+page;
			$("#pro_search").attr("action",url);
			$("#pro_search").submit();
		}
		//执行查询
		function searchlist(){
			pageRefresh(1);
		}
		//删除全部
		function subinher(){
					//获取被选中的复选框
			var checkbox=$('#dg').datagrid('getChecked');
			var ids;
			
			for( var a=0;a<checkbox.length;a++){
				$(window.parent.$("#inid_${fileindex}").val(checkbox[a].ck));
				$(window.parent.$("#inN_${fileindex}").val(checkbox[a].name))
			
				 $(window.parent.$("#inname_${fileindex}").html(checkbox[a].name));  
				$(window.parent.$('#inher').window('close'));
			}
		}
		function addnew(){
		
			window.parent.parent.topage('新建传承人','inheritor/newpage');
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
		})
		</script>
	
	</head>
	  
	<body>
		<div style="width:100%;">
		
          <div style="padding:20px 0 0;">  
	      </div>
          <div style="margin:20px 0;">
		  <form id="pro_search" method="get" action="">
            <table>
		      <tr>
              
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;姓名:</td>
                <td>
				  <input class="easyui-textbox" style="width:100%" name="name" id="search_name" value="${in.name}">
                </td>
                <td>
				  <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:100px; margin-left:20px;" onclick="searchlist()">查询</a>
				   <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px;margin-left:20px;" onclick="addnew()">添加</a>
				    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="width:100px;margin-left:20px;" onclick="subinher()">确定</a>
                </td>
              </tr>
            </table>
                </form>
          </div>
          <div style="margin:20px 0;"></div>	
	      <table id="dg" title="查询结果" style="width:100%;height:auto"   class="easyui-datagrid"  data-options="
				rownumbers:true,
				singleSelect:true,
				autoRowHeight:false,
				pagination:false">
	        <thead>
			  <tr>
			  	<th field="ck" checkbox="true"></th>
				<th field="name" width="20%" align="center">姓名</th>
				<th field="no" width="8%" align="center">性别</th>
				<th field="naction" width="10%" align="center">民族</th>
				<th field="category" width="20%" align="center">所属类别</th>
				<th field="project" width="15%" align="center">所属项目</th>
				<th field="level" width="15%" align="center">批次级别</th>
				<th field="year" width="10%" align="center">批准年度</th>
			  </tr>
	        </thead>
			<tbody>
			<c:forEach items="${pagingTools.dataSet}" var="in"  varStatus="status">
			<tr>
				<td>${in.id}</td>
				<td>${in.name}</td>
				<td><c:choose>  
					<c:when test="${in.sex eq '1'}">
						男
					</c:when>
					<c:when test="${in.sex eq '2'}">
						女
					</c:when>
				</c:choose></td>
				<td>${in.naction.name}</td>
				<td>${in.column.name}</td>
				<td>${in.project.name}</td>
				<td>
				<c:if test="${in.batch !=''}">
				第${in.batch}批
				</c:if>
				${in.level.name} </td>
				<td>${in.year}年</td>
			</tr>
			</c:forEach>
		</tbody>
	      </table>
			<div id="pp" class="easyui-pagination" style="background:#efefef;border:1px solid #ccc;"
				data-options="total:${pagingTools.count},pageNumber:${pagingTools.currentPage},pageSize:${pagingTools.pageRecord},displayMsg:'',layout:['sep','first','prev','manual','next','last','efresh']">
			</div>
        </div>
		
	</div>

	<script type="text/javascript">
		function save(){
		pageRefresh(1)
			var url = "${basePath}action//inheritor/search/save/";
			$.ajax({type:"POST", url:url,dataType:"text", data:$('#inher').serialize(),success:function(datas) {
				if(datas=="1"){
					
					$("#search_name").textbox("setValue", $('#save_name').val());
					
					$('#newinher').window('close');
					pageRefresh(1);
				}
					
			}});
		}
	</script>
	</body>
</html>