<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" /> 
		<meta http-equiv="cache-control" content="no-cache" /> 
		<meta http-equiv="expires" content="0" />
		<title>敏感词管理</title>
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		<script type="text/javascript">	

			//删除一条数据
             function del(ids){
             $.messager.confirm('Confirm','数据删除不可恢复，确认删除吗？',function(r){
				if (r){
	            var id = String(ids);
	             $.ajax({
				   type:'GET',
				   url:"${basePath}action/website/sensitivewords/delet/"+id,
				   success:function result(){
					   showMessage("删除成功");
				   }
				   });
			    }
             });
             }
		
		//多条删除
		function delall(){
			$.messager.confirm('Confirm','数据删除不可恢复，确认删除吗？',function(r){
				if (r){
					//获取被选中的复选框
					var checkbox=$('#dg').datagrid('getChecked');
					var len=checkbox.length;
					var ids;
					for( var a=0;a<len;a++){
						//拼接ID
						if(a==0)
							ids=checkbox[a].id;
						else 
							ids+=','+checkbox[a].id;
							
					}
					$.ajax({
				   type:'GET',
				   url:"${basePath}action/website/sensitivewords/delet/"+ids,
				   success:function result(){
					   showMessage("删除成功");
				   }
				   });
					
				}
			});
			
		}

        
		 //打开新建窗口
			$(function(){
			$("#add").click(function(){
			$('#win').window('open');
			 $('#name').val('');
			 $('#repStr').val('');
			 $('#id').val('0');
			});
			})
		//打开更新窗口，编码表	
			function edit(id,name,repStr){
			$('#win').window('open');
            $('#name').val(name);
			$('#repStr').val(repStr);
			$('#id').val(id);
			}
       //提交功能
		function save(){
				var url;
				var id = $('#id').val();
				if(id=='0'){
					url ="${basePath}action/website/sensitivewords/new";
					postDataToServer(url,"save");
				}else{
					url ="${basePath}action/website/sensitivewords/update";
					postDataToServer(url,"update");
				}
				$('#id').val('0');	
				
			}
			//提交表单到服务器
			function postDataToServer(url,type){
			
				
				$.ajax({type:"POST", url:url,data:$('#data').serialize(),dataType:"text", success:function(datas) {
					
					if(datas=='1'){
						showMessage("保存成功");
					}else if(datas=='0'){
						unUpdataMessage("保存失败");
					}
			    }
				});

			}
			//弹出的的提示信息,刷新页面
			function showMessage(message){
				$.messager.alert('消息',message,'info',function(){location.reload();});
			}
			function unUpdataMessage(message){
			$.messager.alert('消息',message,'info',function(){});
			}
	</script>
	</head>
	  

	<body>
		<form action="" method="get" id="userForm">
		<div style="width:100%;height:700px;">
		
          <div style="padding:20px 0 0;">
		    <a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px">新建</a>
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:100px" onclick="delall()">删除</a>    
	      </div>
        
          <div style="margin:20px 0;"></div>	
	     <table id="dg" class="easyui-datagrid" title="列表显示" style="width:100%;height:600px"
			data-options="singleSelect:false,method:'get'">
		<thead>
			<tr>
				<th field="ck" checkbox="true"></th>
				<th data-options="field:'id',width:'1%',align:'center',hidden:true">ID</th>
				<th data-options="field:'name',width:'35%',align:'center'">名称</th>
                <th data-options="field:'repStr',width:'35%',align:'center'">替换内容</th>
				<th data-options="field:'edit',width:'30%',align:'center'">操作</th>
			</tr>
		</thead>

		 <tbody>
		 <c:forEach items="${pagingTools.dataSet}" var="sensitivewords">
			     <tr>
                  <td></td>
				<td>${sensitivewords.id}</td>
				<td>${sensitivewords.name}</td>
                <td>${sensitivewords.repStr}</td>
				<td><a href="#" onclick="edit('${sensitivewords.id}','${sensitivewords.name}','${sensitivewords.repStr}')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="del('${sensitivewords.id}')">删除</a></td>
				</tr>
		</c:forEach>
		 </tbody>
	</table>  
	</div> 
	</form>
	


		<div id="win" class="easyui-window" title="敏感词管理" style="width:400px;height:200px;" data-options="iconCls:'icon-save',modal:true,shadow:true,closed:true,collapsible:false,minimizable:false">
				
				<div class="easyui-layout" data-options="fit:true">
				<form id="data"  method="post">
				<div data-options="region:'center'" style="padding::20px 0 0 50px;">
					
					<table style="margin-top:20px;">
                    <tr>
                    <td>敏感名词</td>
                    <td><input id="name" name="name" class="easyui-validatebox" data-options="required:true,validType:'length[0,20]',missingMessage:'编码不能为空',invalidMessage:'长度不能超出20个字符'" style="width:300px;height:30px"></td>
                    </tr>
                    <tr>
                    <td>替换名词</td>
                    <td><input id="repStr" name="repStr" class="easyui-validatebox" data-options="required:true,validType:'length[0,25]',missingMessage:'名称不能为空',invalidMessage:'长度不能超出25个字符'" style="width:300px;height:30px"></td>
				</tr>
                    </table>
				<input type="hidden"  name="id" id="id" value="0" />
					
				</div>
				
				<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="save()" style="width:80px">保存</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#win').window('close')" style="width:80px">取消</a>
					
				</div>
				</form>	
			</div>
		</div>
	</body>
</html>
