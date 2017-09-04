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
				$('#save').click(function(){
				
					if(validate()){
						$('#columnEditForm').submit();
					}
				});
				
			});
			
			$(function(){
				
				if('${column.views}'!='')
					$('#views').combobox('select','${column.views}');
				
			})
			function save(){
			
				var url = "${basePath}action/manage/columns/save";
				
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
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px;" onclick="save()">保存</a>	
		 	</div> 
			<form method="post" id="column">
			
			<table  class="data">
			  <tr>
				<td>项目名称</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="name" id="name" value="" data-options="required:true,validType:'length[0,50]',missingMessage:'项目名称不能为空',invalidMessage:'长度不能超出50个字符'"></td>
				<td>上级项目</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="parentName" id="parentName" value="${parent.name}"  ><!--<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="showColumnSelector();">查找</a>		    			 -->
	
				<input type="hidden" id="parentId" name="parentId" value="${parent.id}"/>
				</td>
			  </tr>
			   <tr>
				<td>项目编码</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="no" id="no"  data-options="required:true,validType:'length[0,10]',missingMessage:'项目编码不能为空',invalidMessage:'长度不能超出10个字符'"></td>
				<td>附件地址</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="conFilesSavPath" id="conFilesSavPath"  value="" data-options="required:false,validType:'length[0,100]',invalidMessage:'长度不能超出100个字符'"></td>
			  </tr>
			  <tr>
				<td>项目排序</td>
				<td><input class="easyui-numberspinner" value="1" data-options="required:true,min:1,max:99"  style="width:300px;height:30px;" id="columnOrder" name="columnOrder" ></input></td>
				<td>审核开关</td>
				<td><input type="radio" name="approve" id="approve" value="1">开启
				<input type="radio" name="approve" id="approve" value="0" checked="checked">关闭
				</td>
			  </tr>
			  <tr>
				<td>项目状态</td>
				<td style="width:300px">
				<input  type="radio" name="state" id="state" value="1"  checked="checked" >开启
				<input  type="radio" name="state" id="state" value="0"  >关闭</td>
				<td>浏览权限</td>
				<td>
				  <select class="easyui-combobox" style="width:300px;height:30px;" data-options="editable:false,panelHeight:'auto'" name="views" id="views">
						<option value="0">私有</option>
						<option value="1">部分公开</option>
						<option value="2">公开</option>
						<option value="3">订阅</option>
								
					</select>
				</td>
			  </tr>
			   <tr>
				<td>访问地址</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="actionPath" id="actionPath" value="" data-options="required:false,validType:'length[0,100]',invalidMessage:'长度不能超出100个字符'"></td>
				<td>页面地址</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="columnPath" id="columnPath" value="" data-options="required:false,validType:'length[0,100]',invalidMessage:'长度不能超出100个字符'"></td>
			  </tr>
			   <tr>
				<td>列表模版</td>
				<td><select class="easyui-combobox" style="width:300px;height:30px;" data-options="editable:false,panelHeight:'auto'" name="listModel" id="listModel">
						<option value="" >--</option>
					<c:forEach items="${columnTemplateFiles}" var="fileName">
						<option value="${fileName}" >${fileName}</option>
					</c:forEach>			
					</select></td>
				<td>内容模版</td>
				<td><select class="easyui-combobox" style="width:300px;height:30px;" data-options="editable:false,panelHeight:'auto'" name="contentModel" id="contentModel">
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