<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
    	<title>栏目管理</title>
    
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
	 
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'west',split:true" style="width:300px;">
				<ul id="tree" class="easyui-tree" data-options="url:'${basePath}action/manage/column',method:'get',lines:true,cascadeCheck:false,animate:true"></ul>
	    			
			</div>
			<div data-options="region:'center'" >
				<iframe id="columnsFrame" src="" frameborder=0  allowtransparency="true" 
            			marginheight=0  marginwidth=0  scrolling="auto" 
            			style="overflow:auto;overflow-x:hidden;overflow-y:hidden;border: 0px;width:99%; height:99%" ></iframe>
			</div>
		</div>
	
	</body>
	<script  type="text/javascript">
	var url = '${basePath}action/manage/columns/list?id=1';
	$('#columnsFrame').attr('src', url);
	$('#tree').tree({
			onClick: function(node){
					url = '${basePath}action/manage/columns/list?id='+node.id;
					$('#columnsFrame').attr('src', url);
			}
	});
	</script>
</html>