<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
	<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
	<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
	<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${basePath}component/dhtmlx/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${basePath}component/dhtmlx/tree/dhtmlxtree.js"></script>
	<script type="text/javascript" src="${basePath}component/dhtmlx/menu/dhtmlxmenu.js"></script>
	<script type="text/javascript" src="${basePath}component/dhtmlx/menu/ext/dhtmlxmenu_ext.js"></script>
	<script type="text/javascript">
		function getTree(flag){
			$(function(){
			
				var xml = '${xml}';
				var tree = new dhtmlXTreeObject('columnSelector', '100%', '100%',0);
				tree.setSkin('dhx_skyblue');
				tree.setImagePath('${basePath}component/dhtmlx/tree/imgs/csh_dhx_skyblue/');
				tree.loadXMLString(xml);
				tree.openAllItems(1);
		
				tree.attachEvent("onSelect", function(id){
					//flag = 1 内容管理 2 内容审核  5 回收站管理
					if(flag=='1'){
							if(tree.getUserData(id,"columnType")=='5'){
								$("#columnsFrame").attr("src","${basePath}action/content/page/"+id) ;
							}else{
								$("#columnsFrame").attr("src","${basePath}action/content/list/0/"+id
									+"/"+encodeURI(encodeURI(tree.getSelectedItemText()))+"/"+tree.getUserData(id,"columnType")) ;
							}		
					}
				
					if(flag=='2'){		
							$("#columnsFrame").attr("src","${basePath}action/contentcheck/list/0/"+id
								+"/"+encodeURI(encodeURI(tree.getSelectedItemText()))+"/"+tree.getUserData(id,"columnType")) ;
					}
				
					
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
	}
	function topage(url){
		/*var html='<div title="选项卡三" data-options="iconCls:\'icon-help\',closable:true" style="padding:10px">'+
			'<iframe id="right" src="${basePath}action/'+url+ '" width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>'+
		'</div>';*/
		var html='<div title="选项卡三" data-options="iconCls:\'icon-help\',closable:true" style="padding:10px">'+
			'1233ddd'+
		'</div>';
		//$("#tabs", parent.document).append(html);
		$("#tabs", parent.document).tabs('add',{
			title:'New Tab',
			content:'Tab Body',
			closable:true,
			tools:[{
				iconCls:'icon-mini-refresh',
				handler:function(){
					alert('refresh');
				}
			}]
		});
	}
		</script>
</head>
<body>
<div class="easyui-accordion" style="width:100%;height:750px;">

		<c:forEach items="${list}" var="list" varStatus="status">
		<div title="${list.resourceName}"  style="overflow:auto;padding:10px;" id="mk_${list.id}">
			<script type="text/javascript">
				/*if(${status.index}==0){
					$("#right", parent.document).attr("src",'${basePath}action/${list.url}');
				}*/
				if('${list.resourceName}'!='项目管理'||'${list.resourceName}'!='项目审核'){
				var url = "${basePath}action/manage/leftsubmain/"+${list.id};
				$.ajax({type:"GET", url:url,dataType:"json", success:function(datas) {
					for(var a=0;a<datas.length;a++){
					$('#mk_${list.id}').append("<p onclick='topage(\""+datas[a].url+"\")'>"+datas[a].resourceName+"</p>");
					
					}
				}});
				}
				if('${list.resourceName}'=='项目管理'){
					getTree(1);
					$('#mk_${list.id}').append('<div id="columnSelector" style="font-size:32px;margin-top: 10px;"></div><div id="menu"></div>');
					
				}
			</script>
			
		</div>
		</c:forEach>
	</div>
</body>
</html>