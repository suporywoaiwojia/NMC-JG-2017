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
	    <link rel="shortcut icon" href="favicon.ico">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
        
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
        <script type="text/javascript">
		$val = $;
		</script>
		<script type="text/javascript" src="${basePath}js/validate.js"></script>
            
		<script type="text/javascript" src="${basePath}js/jquery-1.8.3.min.js"></script>
 		<script type="text/javascript" src="${basePath}js/xheditor-1.1.7/xheditor-1.1.7-zh-cn.min.js"></script>       
        <script type="text/javascript">	   
	
		//右下角弹出的的提示信息
		function showMessage(message){
				$.messager.show({
				title:'消息',
				msg:message,
				timeout:3000,
				showType:'slide'
			});
			}
		//确保输入框内容不为空
		function subCheckResult(contentId){
		//这里不能使用POST形式提交
		
		//获取到审核意见和结果
		var approveOpinion = $val("#approveOpinion").textbox('getValue');
		var state = $val('#state').combobox('getValue');
		
		var url="${basePath}action//check/manage/data/content/approve/"+contentId+"/"+approveOpinion+"/"+state;
		$.ajax({ url: url,  success: function(){
           alert("success");
        }});		
			}
		
		</script>
  </head>
 
<body>
<div class="easyui-panel"  style="width:100%;padding:20px 30px;">
  <div  style="padding:0 0 10px;" >
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:300px;" onclick="subCheckResult('${currentCon.id}')">保存</a>	   
  </div>       
  <div style="margin:10px 0">
    <form id="web-form" action="" method="post">
	<table id="top_table">
        <input type="hidden" id="id" name="id" value="${currentCon.id}"> 
		<tr>
        <td >文章名称:</td>
        <td>
        <input id="name"  name="name" readonly="readonly" value="${currentCon.name}" data-options="required:true,missingMessage:'站点名称不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px" />
        </td>        
        <td style="padding-left:40px;">作者姓名:</td>
        <td>
        <input id="author" name="author" value="${currentCon.author}" readonly="readonly" data-options="required:true,validType:'length[0,10]',missingMessage:'站点简称不能为空',invalidMessage:'站点简称长度不能超出20个字符'" class="easyui-textbox" style="width:300px;height:30px;text-align:center;" />
        </td>  
         <td style="padding-left:40px;">文章类型:</td>
        <td>
          <input id="dataType"  name="dataType" value="${currentCon.dataType}" readonly="readonly" class="easyui-textbox" data-options="required:true,missingMessage:'关键字不能为空'" style="text-align:center;width:300px;height:30px" />    </td>      
      </tr>
      <tr> 
	     
		<td>创建用户:</td>
        <td>
          <input value="${currentCon.createUser.userName}"  readonly="readonly" class="easyui-textbox"  style="text-align:center;width:300px;height:30px" />   
           <input type="hidden" id="createUser" name="createUser.id" value="${currentCon.createUser.id}"> 
       </td>
        <td style="padding-left:40px;">文章语言:</td>
        <td>
           <input value="${currentCon.language}"  name="language"  readonly="readonly" class="easyui-textbox"  style="text-align:center;width:300px;height:30px" />         
        </td>

        <td style="padding-left:40px;">所属栏目</td>
         <td>
           <input value="${currentCon.columnContents.name}"   readonly="readonly" class="easyui-textbox"  style="text-align:center;width:300px;height:30px" />  
            <input type="hidden" id="columnContents" name="columnContents.id" value="${currentCon.columnContents.id}">   
        </td>
      </tr>
	  <tr>
      <td >审核人员:</td>
        <td>
         <input value="${currentCon.approve.userName}"   readonly="readonly" class="easyui-textbox"  style="text-align:center;width:300px;height:30px" />  
          <input type="hidden" id="approve" name="approve.id" value="${currentCon.approve.id}">   
        </td>
      <td style="padding-left:40px;">审核结果:</td>
        <td>
         <select id="state" class="easyui-combobox" name="state" style="width:300px;height:30px; text-align:center;">
            <option value="0">无</option>
            <option value="2">待审</option>
            <option value="3">通过</option>
            <option value="4">退回</option>
          </select> 
        </td>
        </tr>
        <tr>
       <td >审核意见:</td>
		<td colspan="5"><input id="approveOpinion" name="approveOpinion" class="easyui-textbox" 
         data-options="multiline:true" style="width:1100px;height:100px" /></td>
		</tr>

    </table>
    <table>
    <div>
     ${richText}
    </div>
    </table>
    </form>
  </div>      
</div>
</body>
</html>
