<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>无标题文档</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" /> 
		<meta http-equiv="cache-control" content="no-cache" /> 
		<meta http-equiv="expires" content="0" />
	
			<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
			<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
			<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
			<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
			<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		
		
		<script type="text/javascript">
			$(function(){
				var _MESSAGE = '${message}'; 
				var _listDynamics='${listDynamics}';
				if (_MESSAGE != '') {
					if (_MESSAGE.indexOf('成功') != -1) {
						art.dialog({content: _MESSAGE, lock: false, time: 2});
					} else {
						art.dialog({content: _MESSAGE, time: 2});
					}
				}

				if(_listDynamics==0){
					$("#makeHtml").attr("disabled","disabled");
				}
		
				var xml = '${xml}';
				var tree = new dhtmlXTreeObject('columnSelector', '100%', '100%',0);
		
 				tree.enableCheckBoxes(true);
		 		tree.enableThreeStateCheckboxes(false);
				tree.disableCheckbox(1,true);
				tree.disableCheckbox(1,true);
				tree.setSkin('dhx_skyblue');
				tree.setImagePath('${basePath}component/dhtmlx/tree/imgs/csh_dhx_skyblue/');
				tree.loadXMLString(xml);
				tree.openAllItems(1);
		
				tree.attachEvent("onClick", function(id){
			 		window.opener.document.getElementById("column.name").value=tree.getSelectedItemText();
			  		window.opener.document.getElementById("column.id").value=id;
			   		window.opener.document.getElementById("column.columnType").value=tree.getUserData(id,"columnType");
			  		window.close();
				});
				$("#makeHtml").click(function(){
					ids=tree.getAllChecked();
					$("#message").html('<div style="background:url(../../../../style/images-v1.1/loding.gif) no-repeat; width:658px; height:493px; margin:0 auto; text-align:center; font-size:16px" >正在生成，请稍后…… </div>');
					if(tree.getAllChecked().indexOf("1,")==0)
						ids=tree.getAllChecked().substring(2);
					var url = "${basePath}/action/publish/channelList/"+ids;
					$("#makeHtml").attr("disabled","disabled");
					$.ajax({type:"POST", url:url,dataType:"text", success:function(datas) {
						if(datas != ""){
						
							var msg=datas;
							$("#message").html(msg);
							$("#makeHtml").removeAttr("disabled");
						}
					}});
				});	
			});
		</script>
	</head>
	  
	<body>
	<!--	<form action="" method="get" id="publishColumn">
		    <div class="selectBox">
		    	<table width="100%" height="42" cellpadding="0" cellspacing="0" border="0" >
		    		<tr><td align="center" valign="middle"></td></tr>
				</table>
		    </div>
			<div style="width:250px; float:left;">
				<div id="columnSelector" style="font-size:32px; background-color: #d9d9d9;margin-top: 10px;"></div>
			</div>
			<div style="width:700px;margin:0 auto;" >
				<input type="button" value="生成静态页"  id="makeHtml"  class="publichButton"  style="margin-bottom:10px;"/>
				
				<div id="message"  style="min-height:500px;border-radius:8px;-webkit-border-radius:8px; -moz-border-radius:8px;background:#ffffff;width:700px;"></div>
			</div>
			
	  	</form> -->
        <div class="easyui-layout" data-options="fit:true" style="margin-top:20px;">
			<div data-options="region:'west',split:true" style="width:300px;">
				<ul id="tree" class="easyui-tree" data-options="url:'${basePath}action/manage/column',method:'get',lines:true,cascadeCheck:false,animate:true,checkbox:true"></ul>
	    			
			</div>
			<div data-options="region:'center'" >
				<iframe id="columnsFrame" src="" frameborder=0  allowtransparency="true" 
            			marginheight=0  marginwidth=0  scrolling="auto" 
            			style="overflow:auto;overflow-x:hidden;overflow-y:hidden;border: 0px;width:99%; height:99%" ></iframe>
			</div>
		</div>
	</body>
</html>