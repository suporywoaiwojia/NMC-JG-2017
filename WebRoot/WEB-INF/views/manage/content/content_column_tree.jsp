<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" /> 
		<meta http-equiv="cache-control" content="no-cache" /> 
		<meta http-equiv="expires" content="0" />
	<title>无标题文档</title>
	<link type="text/css" rel="stylesheet" href="${basePath}style/css/global.css" />
	<link type="text/css" rel="stylesheet" href="${basePath}style/css/backstage.css" />		
	<link type="text/css" rel="stylesheet" href="${basePath}component/artdialog/skins/black.css" /> <link type="text/css" rel="stylesheet" href="${basePath}component/dhtmlx/tree/dhtmlxtree.css" />
	
	<script type="text/javascript" src="${basePath}js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${basePath}js/validate.js"></script>
	<script type="text/javascript" src="${basePath}js/const.js"></script>
	<script type="text/javascript" src="${basePath}component/artdialog/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}component/artdialog/artDialog.js"></script>
	<script type="text/javascript" src="${basePath}component/artdialog/plugins/iframeTools.js"></script>
	<script type="text/javascript" src="${basePath}component/artdialog/artDialogDefaultConfig.js"></script>
	<script type="text/javascript" src="${basePath}component/dhtmlx/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${basePath}component/dhtmlx/tree/dhtmlxtree.js"></script>
	
	<script type="text/javascript">
	
	$(function(){

		
		var xml = '${xml}';
		var tree = new dhtmlXTreeObject('columnSelector', '100%', '100%',0);
		tree.setSkin('dhx_skyblue');
		tree.setImagePath('${basePath}component/dhtmlx/tree/imgs/csh_dhx_skyblue/');
		tree.loadXMLString(xml);
		tree.openAllItems(1);
		tree.attachEvent("onClick", function(id){
	
		window.parent.changeColumn(tree.getSelectedItemText(),id,tree.getUserData(id,"columnType"));
			/* parent.document.getElementsByName("column.name").value=tree.getSelectedItemText();
			  parent.document.getElementsByName("column.id").value=id;
			  parent.document.getElementsByName("column.columnType").value=tree.getUserData(id,"columnType");*/
			art.dialog.close()
		});
	});
	</script>
<title>无标题文档</title>
</head>

<body > 

<div id="columnSelector"  style="font-size:32px; background-color:#d9d9d9;margin-top: 10px;">

			    			</div>
</body>
</html>
