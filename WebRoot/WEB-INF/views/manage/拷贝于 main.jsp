<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" /> 
    	<meta http-equiv="cache-control" content="no-cache" /> 
		<meta http-equiv="expires" content="0" />
		
		<title>内蒙古蒙古族非物质文化遗产数据库</title>
		
		<link rel="stylesheet" href="${basePath}ui1.0/css/styles.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
	
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
	<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${basePath}component/dhtmlx/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${basePath}component/dhtmlx/tree/dhtmlxtree.js"></script>
	<script type="text/javascript" src="${basePath}component/dhtmlx/menu/dhtmlxmenu.js"></script>
	<script type="text/javascript" src="${basePath}component/dhtmlx/menu/ext/dhtmlxmenu_ext.js"></script>
	<script type="text/javascript">
		function getTree(){
			$(function(){
			
				var xml = '${xml}';
				var tree = new dhtmlXTreeObject('columnSelector', '100%', '100%',0);
				tree.setSkin('dhx_skyblue');
				tree.setImagePath('${basePath}component/dhtmlx/tree/imgs/csh_dhx_skyblue/');
				tree.loadXMLString(xml);
				tree.openAllItems(1);
				tree.selectItem('1',true);
					tree.attachEvent("onClick", function(id, zoneId, casState){
					
						var title=tree.getItemText(id);
					
						var url = "${basePath}action/manage/columns/path/"+id;
						$.ajax({type:"GET", url:url,dataType:"text", success:function(datas) {
							if(datas!='')
								topage(title,datas);
						}});
					});
			});
	}
		function getTree1(){
			$(function(){
			
				var xml = '${xml}';
				var tree = new dhtmlXTreeObject('columnSelector1', '100%', '100%',0);
				tree.setSkin('dhx_skyblue');
				tree.setImagePath('${basePath}component/dhtmlx/tree/imgs/csh_dhx_skyblue/');
				tree.loadXMLString(xml);
				tree.openAllItems(1);
		

				tree.selectItem('1',true);
					tree.attachEvent("onClick", function(id, zoneId, casState){
					
						var title=tree.getItemText(id);
					
						var url = "${basePath}action/manage/columns/path/"+id;
						$.ajax({type:"GET", url:url,dataType:"text", success:function(datas) {
							if(datas!='')
								topage(title,"/check"+datas);
						}});
					});
			});
	}
	function topage(title,url){
		$("#tabs").tabs('close',title);
		$("#tabs").tabs(
		'add',{
			title:title,
			content:'<iframe id="right" src="${basePath}action/'+url+ '" width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>',
			closable:true
			
		});
	}
	function closeTab(title){
		$("#tabs").tabs('close',title);
	}
	function logout(){
				document.location.href = '${basePath}j_spring_security_logout';
			}
		</script>
	</head>
	
	<body>
				
		<div id="header">
		  <div class="headerNav">
			<a class="logo"></a>
			<ul class="nav">
			  <li><a href="#" title="进入设置">中文</a></li>、
			  <li><a href="#" title="进入设置">蒙文</a></li>
			  <li><a href="#" title="退出登录" onclick="logout()">退出</a></li>
			</ul>
		  </div>
		</div>
		
		<div id="leftside">
		<!--<iframe src="${basePath}action/manage/left" width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe> -->
		<div class="easyui-accordion" style="width:100%;height:750px;">

		<c:forEach items="${list}" var="list" varStatus="index" >
		<div title="${list.resourceName}"  style="overflow:auto;padding:10px;" id="mk_${list.id}">
			<script type="text/javascript">
				/*if(${status.index}==0){
					$("#right", parent.document).attr("src",'${basePath}action/${list.url}');
				}*/
				if('${list.resourceName}'!='项目管理'||'${list.resourceName}'!='项目审核'){
					var url = "${basePath}action/manage/leftsubmain/"+${list.id};
					$.ajax({type:"GET", url:url,dataType:"json", success:function(datas) {
						for(var a=0;a<datas.length;a++){
							$('#mk_${list.id}').append("<p onclick='topage(\""+datas[a].resourceName+"\",\""+datas[a].url+"\")'>"+datas[a].resourceName+"</p>");
							
								
						}
					}});
				}
				if('${list.resourceName}'=='项目管理'){
					getTree();
					
					$('#mk_${list.id}').append('<div id="columnSelector" style="font-size:32px;margin-top: 10px;"></div><div id="menu"></div>');
					
				}
				if('${list.resourceName}'=='项目审核'){
					getTree1();
				
					$('#mk_${list.id}').append('<div id="columnSelector1" style="font-size:32px;margin-top: 10px;"></div><div id="menu"></div>');
					
				}
			</script>
			
		</div>
		
		</c:forEach>
		<div title="1234" style="overflow:auto;padding:10px;" id="tt">
			<script>
				
				$('#tt').tree({
	url:'${basePath}action/manage/column',
	method:"get",
	cascadeCheck:false,
	lines:true
});

			</script>
			<ul id="tt"></ul>
		</div>
	</div>
		</div>
		
		<div id="container">
		<!--<iframe id="right" src="${basePath}action/manage/right.jsp" width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe> -->
		<div class="easyui-tabs" style="width:100%;height:750px;" id="tabs">
			 <div title="选项卡一" style="padding:10px" >
			
			 </div>
			 
		</div>
		</div>
		
		<div id="footer">Copyright © 2010 <a href="#" target="dialog">蒙科立</a> 京ICP备15053290号-2</div>
	</body>
</html>