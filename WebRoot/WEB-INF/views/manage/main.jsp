<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
 HttpSession s = request.getSession(); 
   	s.setAttribute("language","CN");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" /> 
    	<meta http-equiv="cache-control" content="no-cache" /> 
		<meta http-equiv="expires" content="0" />
		
		<title>内蒙古江格尔音像图文数据库</title>
		
		<link rel="stylesheet" href="${basePath}ui1.0/css/styles.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
	
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
	<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
	</head>
	
	<body>
				
		<div id="header">
		  <div class="headerNav">
			<a class="logo"></a>
			<ul class="nav">
			  
			  <li><a href="${basePath}action/manage/main/mn" title="蒙文版">蒙文</a></li>
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
				
				if('${list.resourceName}'!='内容管理'||'${list.resourceName}'!='审核管理'){
					var url = "${basePath}action/manage/leftsubmain/"+${list.id};
					$.ajax({type:"GET", url:url,dataType:"json", success:function(datas) {
						for(var a=0;a<datas.length;a++){
							$('#mk_${list.id}').append("<p onclick='topage(\""+datas[a].resourceName+"\",\""+datas[a].url+"\")'>"+datas[a].resourceName+"</p>");	
						}
					}});
				} 
				if('${list.resourceName}'=='内容管理'){
					$('#mk_${list.id}').append('<ul id="tree1" class="easyui-tree" data-options="method:\'get\',lines:true,cascadeCheck:false,animate:true"></ul>');
				}
				if('${list.resourceName}'=='审核管理'){
					$('#mk_${list.id}').append('<ul id="tree2" class="easyui-tree" data-options="method:\'get\',lines:true,cascadeCheck:false,animate:true"></ul>');
					
				}
			</script>
			
		</div>
		
		</c:forEach>
		
		
	</div>
		</div>
		
		<div id="container">
		<!--<iframe id="right" src="${basePath}action/manage/right.jsp" width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe> -->
		<div class="easyui-tabs" style="width:100%;height:750px;" id="tabs">
			
			 
		</div>
		</div>
		<div id="footer">Copyright © 2010 <a href="#" target="dialog">蒙科立</a> 京ICP备15053290号-2</div>
	</body>
		<script type="text/javascript">
		$('#tree1').tree({data:${Treedata}});
		$('#tree2').tree({data:${Treedata}});
		$('#tree1').tree({
			onClick: function(node){
				if(node.action!='')
					topage(node.text,node.action);
			}
		});
		$('#tree2').tree({
			onClick: function(node){
				if(node.action!='')
					topage(node.text,'check/'+node.action);
			}
		});
		
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
	function viewatt(type,src){
		//$('#attview').window('close');
		
		var content="";
		if(type=='1'){
			content='<div align="center"><img src="${basePath}'+src+'" style="width:800px"></div> ';
		}else if(type=='2'){
			content='<embed src="${basePath}'+src+'" height="100%" width="1200px" allowFullScreen="true"></embed>';
		}else if(type=='3'){
			var exc=src.substring(src.lastIndexOf('.')+1);
			content='<video src="${basePath}'+src+'" controls="controls"  height="100%" width="1200px"></video>';
			if(exc=='mp3'){
			content='<audio  src="${basePath}'+src+'" controls="controls"  height="100%" width="1200px"></audio >';
			}
			
		}
		$('#attview').dialog({
			title: ' ',
			
			height: 700,
			closed: false,
			cache: false,
			zIndex:9999,
			content:content,
			modal: false
		});
	}
	function updatatab(title,url){
			var tab = $('#tabs').tabs('getTab',title);  // get selected panel
		$('#tabs').tabs('update', {
			tab: tab,
			options: {
				title: title,
				content:'<iframe id="right" src="${basePath}action/'+url+ '" width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>',
			}
		});
	}
</script>
<div id="attview" width="1200px"></div>
</html>