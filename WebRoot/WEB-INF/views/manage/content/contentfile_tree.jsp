<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>无标题文档</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" /> 
		<meta http-equiv="cache-control" content="no-cache" /> 
		<meta http-equiv="expires" content="0" />
		
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/backstage.css" />		
		<link type="text/css" rel="stylesheet" href="${basePath}component/artdialog/skins/black.css" /> <link type="text/css" rel="stylesheet" href="${basePath}component/dhtmlx/menu/skins/dhtmlxmenu_dhx_skyblue.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}component/dhtmlx/tree/dhtmlxtree.css" />
	
		<script type="text/javascript" src="${basePath}js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${basePath}js/validate.js"></script>
		<script type="text/javascript" src="${basePath}js/const.js"></script>
		<script type="text/javascript" src="${basePath}component/dhtmlx/dhtmlxcommon.js"></script>
		<script type="text/javascript" src="${basePath}component/dhtmlx/tree/dhtmlxtree.js"></script>
		<script type="text/javascript" src="${basePath}component/dhtmlx/menu/dhtmlxmenu.js"></script>
		<script type="text/javascript" src="${basePath}component/dhtmlx/menu/ext/dhtmlxmenu_ext.js"></script>
		<script type="text/javascript">
			$(function(){
				var enableHeight=window.parent.document.documentElement.clientHeight-210+'px';
				$('#pageTable').height(enableHeight);
				$('#columnsFrame').height(enableHeight);
				
				
				var flag = '${flag}';
				var xml = '${xml}';
				var tree = new dhtmlXTreeObject('columnSelector', '100%', '100%',0);
				tree.setSkin('dhx_skyblue');
				tree.setImagePath('${basePath}component/dhtmlx/tree/imgs/csh_dhx_skyblue/');
				tree.loadXMLString(xml);
				tree.openAllItems(1);
		
				tree.attachEvent("onSelect", function(id){
					//flag = 1 内容管理 2 内容审核  5 回收站管理
					<c:choose>
						<c:when test="${flag eq '1'}">	
							if(tree.getUserData(id,"columnType")=='5'){
								$("#columnsFrame").attr("src","${basePath}action/content/page/"+id) ;
							}else{
								$("#columnsFrame").attr("src","${basePath}action/contentFile/list/0/"+id
									+"/"+encodeURI(encodeURI(tree.getSelectedItemText()))+"/"+tree.getUserData(id,"columnType")) ;
							}			
							
							
								//alert(tree.hasChildren(id));
          				 	// $(window.frames["columnsFrame"].document).find("#new").attr("display","none")
       					///$(window.frames["columnsFrame"].document).find('input[name="new"]').css("display","none");
						//alert($(window.frames["columnsFrame"].document).find('input[name="new"]').attr("title"));
						
						</c:when>
				
						<c:when test="${flag eq '2'}">			
							$("#columnsFrame").attr("src","${basePath}action/contentcheck/list/0/"+id
								+"/"+encodeURI(encodeURI(tree.getSelectedItemText()))+"/"+tree.getUserData(id,"columnType")) ;
						</c:when>
				
						
	
						<c:when test="${flag eq '5'}">				
							$("#columnsFrame").attr("src","${basePath}action/recycle/list/0/"+id+"/"
								+encodeURI(encodeURI(tree.getSelectedItemText()))+"/"+tree.getUserData(id,"columnType")) ;
						</c:when>
				
						
					</c:choose>
				});
				
				tree.selectItem('1',true);
				
				if(flag == '1'){
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
						var f = $("#rightFrame",parent.document.body)[0].contentDocument;
						var selectedItemId = tree.getSelectedItemId();
						$("#contentForm",f).attr("action","${basePath}/action/content/newpage/"+selectedItemId);
						$("#contentForm",f).submit();
					});
				}
			});
			
		</script>
	</head>

	<body>
		
		 <table cellspacing="0" cellpadding="0" border="0" width="100%"  id="pageTable">
	    	<tr>
	    		<td width="150" valign="top" >
	    			<div id="columnSelector" style="font-size:32px; background-color: #d9d9d9;margin-top: 10px;"></div>
					<div id="menu"></div>
	    		</td>
	    		<td>
	    			<iframe id="columnsFrame" src="" frameborder=0  allowtransparency="true" 
            			marginheight=0  marginwidth=0  scrolling="auto" 
            			style="overflow:auto;overflow-x:hidden;overflow-y:auto;border: 0px;width:100%;"></iframe>
	    		</td>
	    	</tr>
	    </table>
	</body>

</html>