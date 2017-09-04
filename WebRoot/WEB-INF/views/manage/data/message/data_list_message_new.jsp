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
        <link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/styles-left.css">
		<link rel="stylesheet" type="text/css" href="${basePath}component/ssi-up/css/style.css">
		<link rel="stylesheet" href="${basePath}component/ssi-up/css/ssi-uploader.css"/>
	
        
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${basePath}js/validate.js"></script>
		<script type="text/javascript">
	function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
			}
			function myparser(s){
				if (!s) return new Date();
				var ss = (s.split('-'));
				var y = parseInt(ss[0],10);
				var m = parseInt(ss[1],10);
				var d = parseInt(ss[2],10);
				if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
					return new Date(y,m-1,d);
				} else {
					return new Date();
				}
			}
		//右下角弹出的的提示信息
		function showMessage(message){
				$.messager.show({
				title:'消息',
				msg:message,
				timeout:3000,
				showType:'slide'
			});
			}
		

		</script>
  </head>
 
<body>
<div class="easyui-panel" title="项目信息" style="width:100%;padding:20px 30px;">
  <div  style="padding:0 0 10px;" >
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:300px;" onclick="save()">保存</a>	   
  </div>      
   <div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left: 300px solid #ddd; border-right: 300px solid #ddd; text-align: center;">     
					附件信息
			</div>
		  <table>
			  <tr>
				<td>上传头像</td>
					<td>
					<div  style="border:2px dashed #CCCCCC;min-height:100px; width:200px" id="file1">
					</div>
					</td>>
				  </tr>
				  <tr>
				  <td style="width:50px"></td>
				  <td>
					<input type="file" multiple id="ssi-upload"/>
					</td>
				  </tr>
			</table>
			
			<!--上传结束 -->
		
  <div style="margin:10px 0">
    <form id="web_form" action="" method="post">
	<table align="center" >
   
		
        <tr>
        <td >姓名:</td>
        <td><input id="name"  name="name" data-options="required:true,validType:'length[0,50]',missingMessage:'资料名称不能为空',invalidMessage:'作者长度不能超出50个字符'" class="easyui-textbox" style="text-align:center;width:300px;height:30px" /></td>        
        <td style="padding-left:40px;">性别:</td>
        <td><input id="sex" name="sex" data-options="required:true,validType:'length[0,10]',missingMessage:'作者不能为空',invalidMessage:'作者长度不能超出20个字符'" class="easyui-textbox" style="width:300px;height:30px;text-align:center;" /></td>        
        <td style="padding-left:40px;">单位:</td>
        <td><input  id="address" name="address" data-options="required:true,missingMessage:'作者单位不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px"/></td>
      </tr>
      <tr> 
		<td>国籍:</td>
        <td>
           <select id="country" class="easyui-combobox" name="country.id" style="width:300px;height:30px; text-align:center;">
                    
            <c:forEach items="${countryList}" var="country" >
              <c:if test="${country.name!='全部'}">  
                 <option value="${country.id}" >${country.name}</option>	
              </c:if>
            </c:forEach>
          </select>   
       </td>
       <td  style="padding-left:40px;">所属栏目</td>
         <td>
         <input  value="${column.name}" class="easyui-textbox" readonly="readonly" style="text-align:center;width:300px;height:30px" />
         <input type="hidden" id="column" name="column.id" value="${column.id}"/>
       </td>
        <td  style="padding-left:40px;">电话</td>
         <td>
         <input   class="easyui-textbox" name="phone" id="phone" style="text-align:center;width:300px;height:30px" />
       
       </td>
       </tr>
       <tr>
       <td >邮箱</td>
         <td>
         <input  class="easyui-textbox" name="email" id="email" style="text-align:center;width:300px;height:30px" />
       </td>
       <td style="padding-left:40px;">创建用户:</td>
        <td>
          <input value="${loginUser.userName}"  readonly="readonly" class="easyui-textbox"  style="text-align:center;width:300px;height:30px" />   
          <input type="hidden" id="publishU" name="publishU.id" value="${loginUser.id}">   
       </td>

      <c:if test="${column.approve=='1'}">
        <td style="padding-left:40px;">审核人员:</td>
        <td>
          <select id="approve" class="easyui-combobox" name="approve.id" style="width:300px;height:30px; text-align:center;">
            <c:forEach items="${approveList}" var="approve" >
                <option value="${approve.id}" <c:if test="${loginUser.id==approve.id}" >selected="selected"</c:if> >${approve.userName}</option>		
            </c:forEach>
          </select>       
        </td>
        </c:if>
        <c:if test="${column.approve=='0'}">
        <td style="padding-left:40px">审核开关:</td>
        <td>
        关闭
        </td>
        </c:if>
       </tr>
      <tr></tr>
      <tr>
	    <td >简介:</td>
		<td colspan="5"><input id="summary" name="summary" class="easyui-textbox" data-options="multiline:true" style="width:1100px;height:150px" /></td>
	  </tr> 
    </table>
  <div id="filedata"></div>
  </form>
  </div>
  
		    
</div>
</body>
<script type="text/javascript">
		function save(){
			var url = "${basePath}action/manage/data/rapper/save";
			var data = $("#web_form").serialize();
			$.post(url, data,
				   function(result){
					 if(result=='1'){
						 $.messager.alert('消息','保存成功','info');
					 }else if(result=='0'){
						 $.messager.alert('消息','保存失败','info');
				      }
				   }, "text");
				
		}
		
	</script>
	<script type="text/javascript">
	//upload传递变量
		var savepath='${columns.conFilesSavPath}';
		var basePath='${basePath}';
	//对上传图片的前缀命名
		var sequence;
		function getseq(){
			
				var seq2='${catalog.id}'; //
				var seq1='${item.id}';//具体某个资料名称 
		
				sequence=seq1+'_'+seq2;
				return sequence;
		}
	</script>
	<script src="${basePath}component/ssi-up/js/ssi-uploader.js"></script>
	<script src="${basePath}component/ssi-up/js/upload.js"></script>
</html>
