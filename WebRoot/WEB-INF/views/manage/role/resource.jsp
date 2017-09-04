<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>

  	</head>
  
  	<body>
  		<ul id="tree" class="easyui-tree" data-options="lines:true,cascadeCheck:true,animate:true,checkbox:true"></ul>
  		<div align="center" style="margin-top: 10px;">
			 <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="width:100px" onclick="savePermission()" >确定</a>
			 <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:100px" onclick="cancel()" >取消</a>
			   
  		</div>
  	</body>
	<script type="text/javascript">
	$('#tree').tree({data:${Treedata}});
	function savePermission(){
				//var ids = tree.getAllCheckedBranches();
				var roleId = '${roleId}';
				//var d = parent.rightFrame?parent.rightFrame:parent;
				//d.savePermission(roleId, ids);
				var nodes = $('#tree').tree('getChecked', ['checked','indeterminate']);;
				var ids;
				for(var a=0;a<nodes.length;a++){
					if(a==0)
						ids=nodes[a].id;
					else
						ids=ids+','+nodes[a].id;
				}
				var d = parent.rightFrame?parent.rightFrame:parent;
			
				d.savePermission(roleId, ids);
			}
	function cancel(){
				//parent.dhxWins.window('roleResrouce').close();
				$(window.parent.$('#inher').window('close'));
			}
		//编辑 回显
		$(function(){
			var r='${s}'.split(',');
			if(r.length!=0){
				for(var i=0;i<r.length;i++){
					var node = $('#tree').tree('find',r[i]);
					$('#tree').tree('check', node.target);
				}
			}else{
				var node = $('#tree').tree('find', r);
				$('#tree').tree('check', node.target);
			}
		})
			
	
	</script>
</html>
