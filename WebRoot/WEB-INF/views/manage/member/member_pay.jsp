<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" /> 
		<meta http-equiv="cache-control" content="no-cache" /> 
		<meta http-equiv="expires" content="0" />
		
		<title>会员类型管理</title>
	
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/backstage-v1.1.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}component/artdialog/skins/black.css" /> <script type="text/javascript" src="${basePath}js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${basePath}js/index-v1.1/checkboxStyle.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/jquery.artDialog.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/artDialog.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/plugins/iframeTools.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/artDialogDefaultConfig.js"></script>
		<script type="text/javascript" src="${basePath}js/validate.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#save").click(function(){
				
					var paytype=$('input:radio[name="paytype"]:checked');
					$("#price").val(paytype.attr("payprice"));
					$("#paymentName").val(paytype.attr("payname"));
					$("#paymentMonth").val(paytype.attr("paymonth"));
					
					var url="${basePath}/action/memberPayment/save/${memberId}";
					$.ajax({type:"POST", url:url,dataType: "html",data:$('#memberPay').serialize(), success:function(datas) {
						var win = art.dialog.top;
					 win.location.reload();
					
					}, error: function(data) {
                        alert("error:"+data.responseText);
                     }

					});
				});
				
			});
		</script>
	</head>
	  
	<body>
		<form method="post" id="memberPay" enctype="multipart/form-data"  name="memberPay">
	    	<input type="hidden" name="price" id="price" />
			<input type="hidden" name="paymentName" id="paymentName" />
			<input type="hidden" name="status" id="status"  value="1"/>
			<input type="hidden" name="paymentMonth" id="paymentMonth" />
			
	    		<table width="400" cellpadding="0" cellspacing="0" border="0">
	    			<tr>
		    			<td height="42" valign="middle">
		    				<input type="button" id="save" class="saveButtonBG" title="提交"/>
		    				<input type="button" id="back" class="cancelButtonBG" title="关闭"/>
		    			</td>
		    		</tr>
		    	</table>
	    	
	     	<%--列表查询条件 END--%>
	    
	    	<div class="listBox1">
	    		<table width="400" cellpadding="0" cellspacing="0" border="0" id="tablechange">
	    			<tr class="titleTR">
						<td align="center" valign="middle" width="30">&nbsp;</td>
	    				<td align="center" valign="middle">名称</td>
	    				<td align="center" valign="middle" width="120">金额</td>
	    				<td align="center" valign="middle" width="120">有效月数</td>
	    				
	    				
	    			</tr>
					<c:forEach items="${memberType}" var="membertype" varStatus="status">
          		  		<tr>  
				 			<td align="center" valign="middle">
								<input type="radio" name="paytype" class="radio" id="paytype" value="${membertype.id}"  payname="${membertype.name}" payprice="${membertype.price}" paymonth="${membertype.month}"/>						</td>
	    					<td align="left" valign="middle">${membertype.name}</td>
	    					<td align="center" valign="middle">${membertype.price}</td>
	    					<td align="center" valign="middle">${membertype.month}</td>
	    					
	    					
	    				</tr>
					</c:forEach>
	    		</table>
	    	</div>
		   	
		   	
		</form>
	</body>
</html>