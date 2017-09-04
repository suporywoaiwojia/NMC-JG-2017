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
		<title>无标题文档</title>
	
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/backstage-v1.1.css" />
		 <link type="text/css" rel="stylesheet" href="${basePath}component/artdialog/skins/black.css" /> <script type="text/javascript" src="${basePath}js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/jquery.artDialog.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/artDialog.js"></script>
		<script type="text/javascript"  src="${basePath}js/index-v1.1/checkboxStyle.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/plugins/iframeTools.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/artDialogDefaultConfig.js"></script>
		<script type="text/javascript" src="${basePath}js/validate.js"></script>
		<script type="text/javascript" src="${basePath}component/js-fileupload/client/jquery.uploadify.js"></script>
		
		<script type="text/javascript"  src="${basePath}js/tableColor.js" ></script>
		<script type="text/javascript">
		
		$(function(){
		
			$("#back").click(function(){
				window.history.go(-1)
			});
				
			$("#select").click(function(){
				
				var attmodel=window.parent.parent.attmodel;
				
				   $("input[name='checkbox']:checkbox:checked").each(function(){
				   	<c:if test="${list ne null}">
				  	$("#att",window.parent.parent.document).append(attmodel);
					
					$('input[name="attachmentfilePath"]:last',window.parent.parent.document).val( $(this).attr('filePath'));
					$('input[name="attachmentfileName"]:last',window.parent.parent.document).val($(this).val());
					$('input[name="attachmentsavePath"]:last',window.parent.parent.document).val($(this).attr('savePath'))
					 </c:if>
					 <c:if test="${content ne null}">
					 $('input[name="documentName"]',window.parent.parent.document).val( $(this).val());
					$('input[name="docSavePath"]',window.parent.parent.document).val($(this).attr('savePath'));
					$('input[name="docDownPath"]',window.parent.parent.document).val($(this).attr('filePath'));
					
					 </c:if>
					window.parent.parent.closeW();
					
				});	
			});	
			
		});

			

	</script>
	</head>
	  
	<body>
		<form action="" method="get" id="contentForm">
	 <div class="selectBox">
	    	<table width="100%" height="42" cellpadding="0" cellspacing="0" border="0" >
	    		<tr>
	    			<td width="120"  align="center" valign="middle">
					<c:if test="${content.column.id ne '1'}">
	    				<input type="button" name="select"  id="select"  class="saveButtonBG" title="确定"/>
	    				<input type="button" name="back"  id="back"  class="cancelButtonBG" title="返回"/>
						
					</c:if>
	    			</td>
	    			
	    			
	    		</tr>
	    	</table>
	    </div>
		
	  
	     <%--列表查询条件 END--%>
	    
	    <div class="listBox">
	    	<table width="100%" cellpadding="0" cellspacing="0" border="0"  id="tablechange">
	    		<tr class="titleTR">
					<td align="center" valign="middle" ></td>
	    			<td align="center" valign="middle" >
	    				所属栏目
	    			</td>
					<td align="center" valign="middle" >
	    				标题
	    			</td>
					<td align="center" valign="middle" >
	    				附件名称
	    			</td>
	    			<td  align="center" valign="middle">
	    				价格</td>
	    			
	    		</tr>
				<c:if test="${list ne null}">
				<c:forEach items="${list}" var="contentF"  varStatus="status">
				 <c:choose>  
				<c:when test="${status.index % 2 == 0}">  
	    			<tr class="contentTR1" cover="${content.coverPath}" id="coverAdd">
				</c:when>  
       			<c:otherwise>  
          		  <tr  class="contentTR2" cover="${content.coverPath}" id="coverAdd">  
     		   </c:otherwise>  
				 </c:choose>  
				 	<td align="center" valign="middle" >
					<input type="checkbox" id="checkbox" name="checkbox" class="styled" value="${contentF.fileName}"  price="${contentF.price}" filepath="${contentF.filePath}" savepath="${contentF.savePath}"/>
					</td>
	    			<td align="center" valign="middle" >
	    				${contentF.content.column.name}
	    			</td>
	    			<td  align="center" valign="middle">
	    				<font color="${content.titleColor}">${contentF.content.title}</font>
	    			</td>
					<td  align="center" valign="middle">
	    				${contentF.fileName}
	    			</td>
					<td  align="center" valign="middle">
	    				${contentF.price}
	    			</td>
					
				
	    			
	    		</tr>
				</c:forEach>
	    		</c:if>
				<!--文档类型-->
				 <c:if test="${content ne null}">
				 <tr class="contentTR1" cover="${content.coverPath}" id="coverAdd">
				 <td align="center" valign="middle" >
					<input type="checkbox" id="checkbox" name="checkbox" class="styled" value="${content.documentName}"  filepath="${content.docDownPath}" savepath="${content.docSavePath}"/>
					</td>
	    			<td align="center" valign="middle" >
	    				${content.column.name}
	    			</td>
	    			<td  align="center" valign="middle">
	    				<font color="${content.titleColor}">${content.title}</font>
	    			</td>
					<td  align="center" valign="middle">
	    				${content.documentName}
	    			</td>
					<td  align="center" valign="middle">
	    				 
	    			</td>
				 </tr>
				 </c:if>
	    	</table>
	    </div>
		
	    
	  
	   <script type="text/javascript">
			tableTrColor("tablechange","#d6d6d6","#dfdfdf","#FFFFFF");
		</script>	 
		</form>   

	</body>
</html>
