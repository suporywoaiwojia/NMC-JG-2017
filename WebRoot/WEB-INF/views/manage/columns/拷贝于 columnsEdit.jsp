<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
    	<title>编辑栏目</title>
    
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />

		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/styles-left.css">
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}component/dhtmlx/dhtmlxcommon.js"></script>
		<script type="text/javascript" src="${basePath}component/dhtmlx/tree/dhtmlxtree.js"></script>
		<script type="text/javascript" src="${basePath}component/dhtmlx/menu/dhtmlxmenu.js"></script>
		<script type="text/javascript" src="${basePath}component/dhtmlx/menu/ext/dhtmlxmenu_ext.js"></script>
		
		<script type="text/javascript">
			$(function(){
				var xml = '${xml}';
				var tree = new dhtmlXTreeObject('columnSelector', '100%', '100%',0);
				tree.setSkin('dhx_skyblue');
				tree.setImagePath('${basePath}component/dhtmlx/tree/imgs/csh_dhx_skyblue/');
				
				tree.loadXMLString(xml);
				
				tree.attachEvent("onClick", function(id){
					var columnId = '${column.id}';
					if(columnId != id){
						$('#parentName').val(tree.getSelectedItemText());
						$('#parentId').val(tree.getSelectedItemId());
					}else{
						//art.dialog({content: '不能选择栏目自身。', time: 2});
					}
					hideColumnSelector();
				});
				
				
				
			});
			function showColumnSelector(){
		
				var div = document.getElementById('columnSelector');
				for(var i=0; i<300; i++){
					div.style.height = i+'px';
				}
			}
			
			function hideColumnSelector(){
				var div = document.getElementById('columnSelector');
				div.style.height = '0px';
			}
			$(function(){
				
				if('${column.views}'!='')
					$('#views').combobox('select','${column.views}');
				if('${column.listModel}'!='')
					$('#listModel').combobox('select','${column.listModel}');
				if('${column.contentModel}'!='')
					$('#contentModel').combobox('select','${column.contentModel}');
				
			})
			function update(){
			
				var url = "${basePath}action/manage/columns/update";
				
				$("#column").attr("action",url);
				var isValid = $("#column").form('validate');
				if(isValid)
					$("#column").submit();
	
			}
		</script>   
  	</head>
  
  	<body>
	 	<div class="easyui-panel"   style="width:100%;padding:15px; height:700px">
			<div style="padding:0 0 10px;">
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px;" onclick="update()">保存</a>	
		 	</div> 
			<form method="post" id="column">
			<input type="hidden" id="id" name="id" value="${column.id}" />
			<table  class="data">
			  <tr>
				<td>项目名称</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="name" id="name" value="${column.name}" data-options="required:true,validType:'length[0,50]',missingMessage:'项目名称不能为空',invalidMessage:'长度不能超出50个字符'"></td>
				<td>上级项目</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="parentName" id="parentName" value="${column.parent.name}"  ><!--<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="showColumnSelector();">查找</a>		    			 -->
	
				<input type="hidden" id="parentId" name="parentId" value="${column.parent.id}"/>
				</td>
			  </tr>
			   <tr>
				<td>项目编码</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="no" id="no" value="${column.no}" data-options="required:true,validType:'length[0,4]',missingMessage:'项目编码不能为空',invalidMessage:'长度不能超出4个字符'"></td>
				<td>数据表名</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="tablename" id="tablename" value="${column.tablename}" data-options="required:false,validType:'length[0,20]',invalidMessage:'长度不能超出20个字符'"></td>
			  </tr>
			  <tr>
				<td>项目排序</td>
				<td><input class="easyui-numberspinner" value="${column.columnOrder}" data-options="required:true,min:1,max:99"  style="width:300px;height:30px;" id="columnOrder" name="columnOrder" ></input></td>
				<td>审核开关</td>
				<td><input type="radio" name="approve" id="approve" value="1" <c:if test="${column.approve =='1'}">checked="checked" </c:if>>开启
				<input type="radio" name="approve" id="approve" value="0" <c:if test="${column.approve =='0'}">checked="checked" </c:if>>关闭
				</td>
			  </tr>
			  <tr>
				<td>项目状态</td>
				<td style="width:300px">
				<input  type="radio" name="state" id="state" value="1"  <c:if test="${column.state =='1'}">checked="checked" </c:if>>开启
				<input  type="radio" name="state" id="state" value="0"  <c:if test="${column.state =='0'}">checked="checked" </c:if>>关闭</td>
				<td>浏览权限</td>
				<td>
				  <select class="easyui-combobox" style="width:300px;height:30px; text-align:center;" data-options="editable:false" name="views" id="views">
						<option value="0">私有</option>
						<option value="1">部分公开</option>
						<option value="2">公开</option>
						<option value="3">订阅</option>
								
					</select>
				</td>
			  </tr>
			   <tr>
				<td>访问地址</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="actionPath" id="actionPath" value="${column.actionPath}" data-options="required:false,validType:'length[0,100]',invalidMessage:'长度不能超出100个字符'"></td>
				<td>附件地址</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="columnPath" id="columnPath" value="${column.columnPath}" data-options="required:false,validType:'length[0,100]',invalidMessage:'长度不能超出100个字符'"></td>
			  </tr>
			   <tr>
				<td>列表模版</td>
				<td><select class="easyui-combobox" style="width:300px;height:30px; text-align:center;" data-options="editable:false" name="listModel" id="listModel">
							<option value="" >--</option>
					<c:forEach items="${columnTemplateFiles}" var="fileName">
						<option value="${fileName}" >${fileName}</option>
					</c:forEach>		
								
					</select></td>
				<td>内容模版</td>
				<td><select class="easyui-combobox" style="width:300px;height:30px; text-align:center;" data-options="editable:false" name="contentModel" id="contentModel">
							<option value="" >--</option>
					<c:forEach items="${contentTemplateFiles}" var="fileName">
						<option value="${fileName}" >${fileName}</option>
					</c:forEach>
								
					</select></td>
			  </tr>
			</table>
			</form>
			<div id="columnSelector"  
			style="height: 0px; position: absolute; top: 55px; left: 800px; 
			overflow :auto; x-overflow : hidden; y-overflow : auto; 
			filter:progid:DXImageTransform.Microsoft.Alpha(opacity=90);"></div>
		</div>
		
  	</body>
</html>