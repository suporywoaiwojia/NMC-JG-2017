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
  <div style="margin:10px 0">
    <form id="web_form" action="" method="post">
	<table align="center" >
   
		<tr>
        <td >资料名称:</td>
        <td><input id="name"  name="name" data-options="required:true,validType:'length[0,50]',missingMessage:'资料名称不能为空',invalidMessage:'作者长度不能超出50个字符'" class="easyui-textbox" style="text-align:center;width:300px;height:30px" /></td>        
        <td style="padding-left:40px;">作者姓名:</td>
        <td><input id="author" name="author" data-options="required:true,validType:'length[0,10]',missingMessage:'作者不能为空',invalidMessage:'作者长度不能超出20个字符'" class="easyui-textbox" style="width:300px;height:30px;text-align:center;" /></td>        
        <td style="padding-left:40px;">作者单位:</td>
        <td><input  id="address" name="address" data-options="required:true,missingMessage:'作者单位不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px"/></td>
      </tr>
      <tr> 
	    <td >资料类型:</td>
        <td>
           <select id="itemType" class="easyui-combobox" name="itemType.id" style="width:300px;height:30px; text-align:center;">
                    
            <c:forEach items="${typeList}" var="type" >
              <c:if test="${type.name!='全部'}">  
                 <option value="${type.id}" <c:if test="${type.name==column.name}">selected="selected"</c:if> >${type.name}</option>	
              </c:if>
            </c:forEach>
          </select>   
        </td>
		<td style="padding-left:40px;">资料国别:</td>
        <td>
           <select id="itemCountry" class="easyui-combobox" name="itemCountry.id" style="width:300px;height:30px; text-align:center;">
                    
            <c:forEach items="${countryList}" var="country" >
              <c:if test="${country.name!='全部'}">  
                 <option value="${country.id}" >${country.name}</option>	
              </c:if>
            </c:forEach>
          </select>   
       </td>
        <td style="padding-left:40px;">资料语言:</td>
        <td>
          <select id="language" class="easyui-combobox" name="itemLanguage.id" style="width:300px;height:30px; text-align:center;">
           <c:forEach items="${languageList}" var="language" >  
                    <c:if test="${publishHouse.name!='全部'}">     
                 <option value="${language.id}" >${language.name}</option>	
                  </c:if>
            </c:forEach>
          </select>        
          </td>
      </tr>
	  <tr>
        <td>出版机构:</td>
        <td>
          <select id="publishHouse" class="easyui-combobox" name="publishHouse.id" style="width:300px;height:30px; text-align:center;">
                    
            <c:forEach items="${pubHouseList}" var="publishHouse" >
              <c:if test="${publishHouse.name!='全部'}">  
                 <option value="${publishHouse.id}" >${publishHouse.name}</option>	
              </c:if>
            </c:forEach>
          </select>     
        </td>
	    <td style="height:30px; padding-left:40px;">出版版次:</td>
        <td>
           <input id="frequency"  name="frequency" class="easyui-textbox" data-options="required:true,missingMessage:'出版版次不能为空'" style="text-align:center;width:300px;height:30px" />       
        </td>
		<td style="height:30px; padding-left:40px;">出版时间:</td>
        <td>
          <input name="publishDate" id="born_s" class="easyui-datebox" style="width:250px;height:30px;text-align:center;" data-options="required:true,validType:'date',missingMessage:'出版时间不能为空',sharedCalendar:'#cc',formatter:myformatter,parser:myparser,editable:false"  />
		<div id="cc" class="easyui-calendar"></div>       
         </td>
      </tr>
      <tr>
	    <td >国际标准编号:</td>
        <td><input id="isbn" name="isbn" class="easyui-textbox" data-options="required:true,validType:'tel',missingMessage:'国际标准编号不能为空'" style="text-align:center;width:300px;height:30px" />
       </td>
       <td  style="padding-left:40px;">所属栏目</td>
         <td>
         <input  value="${column.name}" class="easyui-textbox" readonly="readonly" style="text-align:center;width:300px;height:30px" />
         <input id="parent" name="parent.id" type="hidden" value ="${column.id}" />
       </td>
      </tr>

      <tr></tr>
      <tr>
	    <td >资料摘要:</td>
		<td colspan="5"><input id="summary" name="summary" class="easyui-textbox" data-options="multiline:true" style="width:1100px;height:150px" /></td>
	  </tr> 
    </table>
    <!--上传后的返回的具体附件信息，附加到下面的容器中-->
    <div id="filedata"></div>
    </form>
    
     <div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left: 300px solid #ddd; border-right: 300px solid #ddd; text-align: center;">     
					附件信息
			</div>
		  <table align="center">
			  <tr>
				<td>封面附件</td>
					<td>
					<div  style="border:2px dashed #CCCCCC;min-height:200px; width:1100px;margging-left:40px;" id="file1">
					</div>
					</td>
				  </tr>
				  <tr>
				  <td style="width:50px"></td>
				  <td>
					<input type="file" multiple id="ssi-upload"/>
					</td>
				  </tr>
			</table>
			
			<!--上传结束 -->
  </div>  
  
		    
</div>
</body>
<script type="text/javascript">
		function save(){
			var url = "${basePath}action/manage/data/item/save";
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
		var savepath='${column.conFilesSavPath}';
		var basePath='${basePath}';
	//对上传图片的前缀命名
		var sequence;
		function getseq(){
			
				var seq2='item'; //
				var seq1='${column.id}';//具体某个资料名称 
		
				sequence=seq1+'_'+seq2;
				return sequence;
		}
	</script>
	<script src="${basePath}component/ssi-up/js/ssi-uploader.js"></script>
	<script src="${basePath}component/ssi-up/js/upload.js"></script>

</html>
