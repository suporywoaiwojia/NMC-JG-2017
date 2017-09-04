<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
    	<title>栏目管理</title>
    
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />    
<link type="text/css" rel="stylesheet" href="${basePath}style/css/global.css" />
			<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
			<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
			<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
			<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
			<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		
		<link type="text/css" rel="stylesheet" href="${basePath}component/dhtmlx/tree/dhtmlxtree.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}component/dhtmlx/menu/skins/dhtmlxmenu_dhx_skyblue.css" />
		<script type="text/javascript" src="${basePath}component/dhtmlx/dhtmlxcommon.js"></script>
		<script type="text/javascript" src="${basePath}component/dhtmlx/tree/dhtmlxtree.js"></script>
		<script type="text/javascript" src="${basePath}component/dhtmlx/menu/dhtmlxmenu.js"></script>
		<script type="text/javascript" src="${basePath}component/dhtmlx/menu/ext/dhtmlxmenu_ext.js"></script>
		
		<script type="text/javascript">
			//初始化组织机构树
			var tree;
			
			$(function(){
				var xml = '${columns}';
				tree = new dhtmlXTreeObject('columnsTree', '100%', '100%',0);
				tree.setSkin('dhx_skyblue');
				tree.setImagePath('${basePath}component/dhtmlx/tree/imgs/csh_dhx_skyblue/');
				tree.loadXMLString(xml);
				tree.openAllItems(1);
				
				tree.attachEvent("onSelect", function(id){
					var url;
					var children = tree.hasChildren(id);
					//if(children > 0){
						var parentPath = tree.getUserData('${column.id}', 'parentPath');
						url = '${basePath}action/manage/columns/list?id='+ id;
					/*} else {
						url = '${basePath}action/manage/columns/edit?id=' + id;
					}*/
					$('#columnsFrame').attr('src', url);
				});
				
				tree.selectItem('1',true);
				
				var menuXML = '<menu><item id="new" text="新建" /></menu>';
				var contextMenu = new dhtmlXMenuObject("menu");
				contextMenu.setSkin("dhx_skyblue");
				contextMenu.setIconsPath("${basePath}component/dhtmlx/menu/imgs/dhxmenu_dhx_skyblue/");
				contextMenu.setOpenMode("win");
				contextMenu.addNewChild(contextMenu.topId, 0, "new", "新建", false);
				contextMenu.renderAsContextMenu();
				
				tree.attachEvent("onRightClick", function(id, event){
					tree.selectItem(id);
					contextMenu.showContextMenu(event.clientX, event.clientY);
				});
				
				contextMenu.attachEvent("onClick", function(id, zoneId, casState){
					var parentId = tree.getSelectedItemId();
					var url = '${basePath}action/manage/columns/add/'+parentId;
					$('#columnsFrame').attr('src', url);
				});
				
			
			});
		</script>
  	</head>
  
	<body>
	 
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'west',split:true" style="width:300px;">
				<div id="columnsTree" style="float: left;margin-top: 5px;"></div>
	    			<div id="menu"></div>
			</div>
			<div data-options="region:'center'" >
				<iframe id="columnsFrame" src="" frameborder=0  allowtransparency="true" 
            			marginheight=0  marginwidth=0  scrolling="auto" 
            			style="overflow:auto;overflow-x:hidden;overflow-y:hidden;border: 0px;width:99%; height:99%" ></iframe>
			</div>
		</div>
	
	</body>
</html>