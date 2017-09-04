<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
    
    	<title>资源列表</title>
    
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
		<link type="text/css" rel="stylesheet" href="${basePath}component/dhtmlx/tree/dhtmlxtree.css" />
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${basePath}component/dhtmlx/dhtmlxcommon.js"></script>
		<script type="text/javascript" src="${basePath}component/dhtmlx/tree/dhtmlxtree.js"></script>
		<script type="text/javascript">
			var tree;
			$(function(){
				var xml = '${xml}';
				tree = new dhtmlXTreeObject('resourceTree', '100%', '100%',0);
				tree.setSkin('dhx_skyblue');
				tree.setImagePath('${basePath}component/dhtmlx/tree/imgs/csh_dhx_skyblue/');
				tree.enableCheckBoxes(true);
				tree.enableThreeStateCheckboxes(1);
				tree.loadXMLString(xml);
				tree.openAllItems(1);
			});
			
			function savePermission(){
				var ids = tree.getAllCheckedBranches();
				var roleId = '${roleId}';
				var d = parent.rightFrame?parent.rightFrame:parent;
				d.savePermission(roleId, ids);
			}
			
			function cancel(){
				//parent.dhxWins.window('roleResrouce').close();
				$(window.parent.$('#inher').window('close'));
			}
		</script>
  	</head>
  
  	<body>
  		<div id="resourceTree" ></div>
  		<div align="center" style="margin-top: 10px;">
			 <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="width:100px" onclick="savePermission()" >确定</a>
			 <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:100px" onclick="cancel()" >取消</a>
			   
  		</div>
  	</body>
</html>
