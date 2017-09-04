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

		function save(){
			var url="${basePath}action/website/update";
			
			$('#web-form').form('submit', {
					url: url,
					onSubmit: function(){
						var isValid = $(this).form('validate');
						if (!isValid)
							return isValid;	// return false will stop the form submission
					},
					success: function(data){
						if(data=="1"){
							$.messager.alert('消息','保存成功','info');
						}else{
							$.messager.alert('消息','保存失败','info');
						}
					}
				});
		}
		$(function(){
				
				if('${website.member}'!='')
					$('#member').combobox('select','${website.member}');
				if('${website.indexDynamics}'!='')
					$('#indexDynamics').combobox('select','${website.indexDynamics}');
				if('${website.listDynamics}'!='')
					$('#listDynamics').combobox('select','${website.listDynamics}');
				if('${website.language}'!=''){
					var language='${website.language}'.split(',');
					for(var a=0;a<language.length;a++){
						$('#language').combobox('select',language[a]);
					}
				}
			})
		</script>
  </head>
 
<body>
 <div class="easyui-panel"   style="width:100%;padding:15px">
  <div  style="padding:0 0 10px;" >
    <a id="saveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px;" onclick="save()">保存</a>	   
  </div>       
  <div style="margin:10px 0">
    <form id="web-form" action="" method="post">
	<table>
		<tr>
	    <input type="hidden" id="id" name="id" value="${website.id}"/>

        <td >网站名称:</td>
        <td><input id="name"  name="name" data-options="required:true,missingMessage:'站点名称不能为空',validType:'length[0,50]',invalidMessage:'长度不能超出50个字符'" class="easyui-textbox" style="width:300px;height:30px" value="${website.name}"/></td>        
        <td style="padding-left:40px;">网站简称:</td>
        <td><input id="abbreviation" name="abbreviation" data-options="required:false,validType:'length[0,50]',invalidMessage:'长度不能超出50个字符'" class="easyui-textbox" style="width:300px;height:30px;" value="${website.abbreviation}"/></td>        
        <td style="padding-left:40px;">网站域名:</td>
        <td><input  id="domainName" name="domainName" data-options="required:false,validType:'length[0,100]',invalidMessage:'长度不能超出100个字符'" class="easyui-textbox" style="width:300px;height:30px" value="${website.domainName}"></td>
      </tr>
      <tr>

	    <tr> 
		 <td>备案编号:</td>
        <td>
          <input id="ipc" name="ipc" data-options="required:true,missingMessage:'备案编号不能为空',validType:'length[0,100]',invalidMessage:'长度不能超出100个字符'" class="easyui-textbox" style="width:300px;height:30px" value="${website.ipc}"/>
        </td>
	      <td style="padding-left:40px;">关键字:</td>
        <td>
          <input id="webKey"  name="webKey" class="easyui-textbox" data-options="required:false,validType:'length[0,250]',invalidMessage:'长度不能超出250个字符'" style="width:300px;height:30px" value="${website.webKey}"/>
        </td>
		<td style="padding-left:40px;">水印内容:</td>
        <td>
          <input id="watermark"  name="watermark" class="easyui-textbox" data-options="required:false,validType:'length[0,250]',invalidMessage:'长度不能超出250个字符'" style="width:300px;height:30px" value="${website.watermark}"/>
        </td>
      </tr>
	
  
      <tr>
	   <td >联系电话:</td>
        <td><input id="tel" name="tel" class="easyui-textbox" data-options="required:false,validType:'length[0,20]',invalidMessage:'长度不能超出20个字符'" style="width:300px;height:30px" value="${website.tel}"/></td>
        <td style="padding-left:40px;">联系邮箱:</td>
        <td><input id="mail" name="mail" class="easyui-textbox" data-options="required:false,validType:'email',invalidMessage:'邮箱格式不正确'" style="width:300px;height:30px" value="${website.mail}"/></td>
<td style="padding-left:40px;">邮政编码:</td><td><input id ="postCode" name ="postCode" data-options="required:false,validType:'length[0,6]',invalidMessage:'长度不能超出6个字符'" class="easyui-textbox" style="width:300px;height:30px" value="${website.postCode}"/></td>
       
        </tr>
      <tr>
	   <td >企业名称:</td>
        <td><input id="businessName" name="businessName" data-options="required:false,validType:'length[0,50]',invalidMessage:'长度不能超出50个字符'" class="easyui-textbox" style="width:300px;height:30px" value="${website.businessName}"/></td>
	  <td style="padding-left:40px;" >企业地址:</td>
        <td><input id="businessAdd" name="businessAdd" data-options="required:false,validType:'length[0,150]',invalidMessage:'长度不能超出150个字符'" class="easyui-textbox" style="width:300px;height:30px" value="${website.businessAdd}"/></td>
       
		<td style="padding-left:40px;">语言种类:</td>
        <td><select class="easyui-combobox" name="language" id='language' data-options="editable:false,multiple:true,panelHeight:'auto'" style="width:300px;height:30px; text-align:left;" >
				<option value="CN" >中文</option>
				<option value="MN" >蒙语</option>
				<option value="EN" >英语</option>
           </select></td>
      </tr>
  <tr>
        <td>会员审核:</td>
        <td>
		  <select class="easyui-combobox" name="member" id='member' data-options="editable:false,panelHeight:45" style="width:300px;height:30px; text-align:left;" >
				<option value="0" >关闭</option>
				<option value="1" >开启</option>
				
           </select>	
        </td>
	    <td style="height:30px; padding-left:40px;">首页静态:</td>
        <td>
 
		  <select class="easyui-combobox" name="indexDynamics" id='indexDynamics' data-options="editable:false,panelHeight:45" style="width:300px;height:30px; text-align:left;" >
				<option value="0" >关闭</option>
				<option value="1" >开启</option>
				
           </select>
        </td>
		<td style="height:30px; padding-left:40px;">列表静态:</td>
        <td>
		  <select class="easyui-combobox" name="listDynamics" id='listDynamics' data-options="editable:false,panelHeight:45" style="width:300px;height:30px; text-align:left;" >
				<option value="0" >关闭</option>
				<option value="1" >开启</option>
				
           </select>
        </td>
		
      </tr>

      <tr>
        <td>站点描述:</td>
       <td colspan="6"><input id="detailed" name="detailed" class="easyui-textbox" data-options="multiline:true,validType:'length[0,150]',invalidMessage:'长度不能超出150个字符'" style="width:1100px;height:150px" value="${website.detailed}"/></td>
      </tr>
    </table>
    </form>
  </div>      
</div>
</body>
</html>
