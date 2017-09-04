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
		<script type="text/javascript" src="${basePath}js/validate.js"></script>
        <script type="text/javascript">
        $val = $;
        </script>
        
		<script type="text/javascript" src="${basePath}js/jquery-1.8.3.min.js"></script>
 		<script type="text/javascript" src="${basePath}js/xheditor-1.1.7/xheditor-1.1.7-zh-cn.min.js"></script>
        
        <script type="text/javascript">
        $(function(){
		//取得上面表格的宽度，距离左边的边距，然后设置富文本编辑器的宽度和边距
		//初始化xhEditor编辑器插件
		var width = $("#top_table").width();
		//$("#summary").next(".textbox").width(width - 20);
		$("#textAreaContent").width(width);
		
				$('#textAreaContent').xheditor({
					tools:'Cut,Copy,Paste,Pastetext,Blocktag,Fontface,FontSize,Bold,Italic,Underline,Strikethrough,FontColor,BackColor,SelectAll,Removeformat,Align,List,Outdent,Indent,Link,Unlink,Anchor,Img,,Hr,Table,Fullscreen',
					
					skin:'o2007silver',
					
					upMultiple:true,
					upLinkUrl:"${basePath}UploadFileServlet",upLinkExt:"zip,rar,txt",
					upImgUrl: "${basePath}UploadFileServlet",
					upImgExt: "jpg,jpeg,gif,bmp,png",
				
					html5Upload:false
				});
				
				//xbhEditor编辑器图片上传回调函数
				function insertUpload(msg) {
					var _msg = msg.toString();
					var _picture_name = _msg.substring(_msg.lastIndexOf("/")+1);
					var _picture_path = Substring(_msg);
					var _str = "<input type='checkbox' name='_pictures' value='"+_picture_path+"' checked='checked' onclick='return false'/><label>"+_picture_name+"</label><br/>";                    
				
					$("#textAreaContent").append(_msg);
					$("#uploadList").append(_str);
				}
				//处理服务器返回到回调函数的字符串内容,格式是JSON的数据格式.
				function Substring(s){
					return s.substring(s.substring(0,s.lastIndexOf("/")).lastIndexOf("/"),s.length);
				}
			

				 
		});
      
	
	   
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
				$val.messager.show({
				title:'消息',
				msg:message,
				timeout:3000,
				showType:'slide'
			});
			}
		//确保输入框内容不为空
		$(function(){
			$("#saveBtn").click(function(){
				var data = $("#web-form").serialize();
				var url="${basePath}action/manage/data/content/update";
				$.post(url, data,
				   function(result){
					 if(result=='1'){
						 showMessage('保存成功');
					 }else if(result=='0'){
						 showMessage('保存失败');
				      }
				   }, "text");
				
				});
				
			});
		

	

		
		</script>
  </head>
 
<body>
<div class="easyui-panel"  style="width:100%;padding:20px 30px;">
  <div  style="padding:0 0 10px;" >
    <a id="saveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:300px;">保存</a>	   
  </div>       
  <div style="margin:10px 0">
    <form id="web-form" action="" method="post">
	<table id="top_table">
    <input type="hidden" id="id" name="id" value="${currentCon.id}" />
    <!--审核页面-->
     <c:if test="${approveState==1}">
        <tr>
         <td>审核结果:</td>
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
    </c:if>
   <!--end-->
   </table>
   <table>
   <tr>
   <div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left: 300px solid #ddd; border-right: 300px solid #ddd; text-align: center;">     
					内容信息
			</div>
   </tr>
     
		<tr>
        <td >文章名称:</td>
        <td>
        <input id="name"  name="name" value="${currentCon.name}"  <c:if test="${approveState=='1'}" > readonly="readonly" </c:if>  data-options="required:true,missingMessage:'名称不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px" />
        </td>        
        <td style="padding-left:40px;">作者姓名:</td>
        <td>
        <input id="author" name="author" value="${currentCon.author}"  <c:if test="${approveState=='1'}" > readonly="readonly" </c:if>data-options="required:true,validType:'length[0,20]',missingMessage:'姓名不能为空',invalidMessage:'长度不能超出20个字符'" class="easyui-textbox" style="width:300px;height:30px;text-align:center;" />
        </td>  
         <td style="padding-left:40px;">文章类型:</td>
        <td>
        <!--编辑页面-->
         <c:if test="${approveState=='0'}" > 
           <select id="type" class="easyui-combobox" name="type.id" style="width:300px;height:30px; text-align:center;">
            <c:forEach items="${typeList}" var="type" >
                <option value="${type.id}" <c:if test="${type.id==currentCon.type.id}">selected="selected"</c:if>>${type.name}</option>		
            </c:forEach>
          </select>
          </c:if>
          <!--审核页面-->
          <c:if test="${approveState=='1'}" >
          <input    value="${currentCon.type.name}"  readonly="readonly" 
             data-options="required:true,missingMessage:'站点域名不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px"/> 
          </c:if> 
       </td>      
      </tr>
      <tr> 
	     
		<td>创建用户:</td>
        <td>
          <input value="${currentCon.createUser.userName}"  readonly="readonly" class="easyui-textbox"  style="text-align:center;width:300px;height:30px" />   
           <!--编辑页面-->
           <c:if test="${approveState==0}" > 
          <input type="hidden" id="createUser" name="createUser.id" value="${currentCon.createUser.id}">   
          </c:if>
       </td>
        <td style="padding-left:40px;">文章语言:</td>
        <td>
         <!--编辑页面-->
             <c:if test="${approveState==0}" > 
          <select id="lan" class="easyui-combobox" name="lan.id" style="width:300px;height:30px; text-align:center;">
            <c:forEach items="${languageList}" var="language" >
                <option value="${language.id}" <c:if test="${language.id==currentCon.lan.id}">selected="selected"</c:if>>${language.name}</option>		
            </c:forEach>
          </select>  
          </c:if>
           <!--审核页面-->
          <c:if test="${approveState==1}" >
          <input    value="${currentCon.lan.name}"  readonly="readonly" 
             data-options="required:true,missingMessage:'站点域名不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px"/> 
          </c:if>      
        </td>
        <td style="padding-left:40px;">审核人员:</td>
        <td>
         <!--编辑页面-->
             <c:if test="${approveState==0}" > 
          <select id="approve" class="easyui-combobox" name="approve.id" style="width:300px;height:30px; text-align:center;">
            <c:forEach items="${approveList}" var="approve" >
                <option value="${approve.id}" <c:if test="${currentCon.approve.id==approve.id}" >selected="selected"</c:if> >${approve.userName}</option>		
            </c:forEach>
          </select> 
          </c:if>
           <!--审核页面-->
          <c:if test="${approveState==1}" >
          <input    value="${currentCon.approve.userName}"  readonly="readonly" 
             data-options="required:true,missingMessage:'站点域名不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px"/> 
          </c:if>         
        </td>
      </tr>
	  <tr>
       	<td >所属栏目</td>
         <td>
          <!--编辑页面-->
             <c:if test="${approveState==0}" > 
           <select  class="easyui-combobox" name="columnContents.id" id="columnContents" style="width:300px;height:30px;text-align:center;" data-options="editable:false,panelHeight:'auto'" >
             <c:forEach items="${columnList}" var="col" >
                <option value="${col.id}" <c:if test="${currentCon.columnContents.id==col.id}" >selected="selected"</c:if> >${col.name}</option>		
            </c:forEach>
          </select>   
           </c:if>
           <!--审核页面-->
          <c:if test="${approveState==1}" >
          <input    value="${currentCon.columnContents.name}"  readonly="readonly" 
             data-options="required:true,missingMessage:'站点域名不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px"/> 
          </c:if>      
        </td>
        </tr>

    </table>
    <table>
     <!--编辑页面-->
      <c:if test="${approveState==0}" > 
     <!-- 加载编辑器的容器 -->
       <textarea id="textAreaContent" name="textAreaContent" rows="15"  style="width: 800px; height:1000px; border: 1px; display: block;" wrap="physical">${richText}</textarea>
      </c:if>
      <c:if test="${approveState==1}" >
        <div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left: 300px solid #ddd; border-right: 300px solid #ddd; text-align: center;">     
					文章信息
			</div>
         <div>
         ${richText}
        </div>
      </c:if>
    </table>
    </form>
  </div>      
</div>
</body>
</html>
