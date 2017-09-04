<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	<script type="text/javascript" src="${basePath}component/artdialog/plugins/iframeTools.js"></script>
	<script type="text/javascript" src="${basePath}js/validate.js"></script>
	
	
	<script type="text/javascript">
	$(function(){
	var _MESSAGE = '${message}'; 
				if (_MESSAGE != '') {
					if (_MESSAGE.indexOf('成功') != -1) {
						art.dialog({content: _MESSAGE, lock: false, time: 2});
					} else {
						art.dialog({content: _MESSAGE, time: 2});
					}
				}
		$("#save").click(function(){
			if (isNull($("#name").val())) {
				art.dialog({content: "请输入类型名称", time: 2});
				return false;
			}
			if (nameSpecialCharacters($("#name").val())) {
				var message = "类型名称输入内容不符合规范，输入内容中不能包含`~!@#$^&*=|{}\':;\',\\[\\].<>《》/?~%！@#￥……&*——|{}【】‘；：”“\'。，、？";
				art.dialog({content: message, time: 2});
				return false;
			}
			if(isNull($("#price").val())){
				var message = "请输入金额";
				art.dialog({content: message, time: 2});
				return false;
			}
			if($("#type").val()==1&&$('#month').val()==""){
				var message = "请选择有效月份";
				art.dialog({content: message, time: 2});
				return false;
			}
			if(isNumber($("#price").val())==false){
				var message = "金额输入错误";
				art.dialog({content: message, time: 2});
				return false;
			}
			if($("#discount").val()!=""&&isNumber($("#discount").val())==false){
				var message = "折扣输入错误";
				art.dialog({content: message, time: 2});
				return false;
			}
				<c:if test="${memberType.id > 0}"> 
			
				$("#membertypeEdit").attr("action","${basePath}/action/membertype/update");
				</c:if>
			<c:if test="${memberType.id eq 0}"> 
			
				
				$("#membertypeEdit").attr("action","${basePath}/action/membertype/new");
			
		
			</c:if>
			<c:if test="${memberType.id eq null}"> 
			
				
				$("#membertypeEdit").attr("action","${basePath}/action/membertype/new");
			
		
			</c:if>
			$("#membertypeEdit").submit();
		});
		$("#back").click(function(){
				$("#membertypeEdit").attr("action","${basePath}/action/membertype/back");
				$("#membertypeEdit").submit();
		});
		
		$('#type').change(function(){ 
			if($('#type').val()=="1"){
		
				$('#month').removeAttr("disabled");
				$("#month").get(0).selectedIndex=1;
				
			}else{
				$("#month").get(0).selectedIndex=0;
				$('#month').attr("disabled","disabled");
			
			}
		});
		$('#discount').blur(function(){
			
				if($("#discount").val()!=""&&isNumber($("#discount").val())==false){
					var message = "折扣输入错误";
					art.dialog({content: message, time: 2});
					return false;
				}else{
				
				var price=Math.round($('#price').val()*$("#discount").val()*0.1);
				$("#disprice").val(price);
				}
			
		 });
		$('#price').blur(function(){
			$("#disprice").val($('#price').val());
			if($("#discount").val()!=""&&isNumber($("#discount").val())==true){
				var price=Math.round($('#price').val()*$("#discount").val()*0.1);
				$("#disprice").val(price);
			}
		 });
	});

	</script>
</head>

<body>
	<form id="membertypeEdit" method="post" action="">
		 <%--按钮位置  START--%>
		<div class="selectBox">
			<table width="70%"  cellpadding="0" cellspacing="0" border="0" >
				<tr>
					
					<td width="80"  align="center" valign="middle">
						<input type="button" name="save" id="save" class="saveButtonBG" title="保存"/>

						<input type="button" name="back" id="back" class="cancelButtonBG" title="返回"/>
					</td>
					<td align="center" valign="middle">&nbsp;
						
					</td>
				</tr>
			</table>
		</div>
		 <%--按钮位置  END--%>
		
		
		<c:choose>  
		<c:when test="${memberType.id ne null}">  
		<input type="hidden" id="id" value="${memberType.id}" name="id"/>
		</c:when>
		</c:choose>

  	    <div class="newBox">
		   <table style="width:100%;min-width:800px;margin:0 auto;border:0px solid #ccc;" align="center" cellpadding="0" cellspacing="0" border="0">
		    	<tr>
		    		<td>
					<ul class="newlongInput">
	  	    				<li class="newBoxleftTitle"><font color="#FF0000">*</font>类型名称</li>
	  	    				<li class="newBoxinput">
							<input type="text" id="name" name="name" value="${memberType.name}" maxlength="20" />
							</li>
							
					</ul>
					<ul class="newlongInput">
						<li class="newBoxleftTitle">是否包月形式</li>
						<li class="newBoxinput">
						<select name="type" id="type"  class="newInput" >
							<option value='1' <c:choose>  
								<c:when test="${memberType.type=='1'}"> selected</c:when></c:choose>>是</option>
							<option value='2' <c:choose>  
								<c:when test="${memberType.type=='2'}"> selected</c:when></c:choose>>否</option>
								
						</select>
						</li>
						</ul>
					<ul class="newlongInput">
						<li class="newBoxleftTitle">有效月数</li>
						<li class="newBoxinput">
						<select name="month" id="month"  class="newInput" >
							<option value='' <c:choose>  
								<c:when test="${memberType.month=='0'}"> selected</c:when></c:choose>>无</option>
							<option value='1' <c:choose>  
								<c:when test="${memberType.month=='1'}"> selected</c:when></c:choose>>1</option>
							<option value='2' <c:choose>  
								<c:when test="${memberType.month=='2'}"> selected</c:when></c:choose>>2</option>
								<option value='3' <c:choose>  
								<c:when test="${memberType.month=='3'}"> selected</c:when></c:choose>>3</option>
								<option value='4' <c:choose>  
								<c:when test="${memberType.month=='4'}"> selected</c:when></c:choose>>4</option>
								<option value='5' <c:choose>  
								<c:when test="${memberType.month=='5'}"> selected</c:when></c:choose>>5</option>
								<option value='6' <c:choose>  
								<c:when test="${memberType.month=='6'}"> selected</c:when></c:choose>>6</option>
								<option value='7' <c:choose>  
								<c:when test="${memberType.month=='7'}"> selected</c:when></c:choose>>7</option>
								<option value='8' <c:choose>  
								<c:when test="${memberType.month=='8'}"> selected</c:when></c:choose>>8</option>
								<option value='9' <c:choose>  
								<c:when test="${memberType.month=='9'}"> selected</c:when></c:choose>>9</option>
								<option value='10' <c:choose>  
								<c:when test="${memberType.month=='10'}"> selected</c:when></c:choose>>10</option>
								<option value='11' <c:choose>  
								<c:when test="${memberType.month=='11'}"> selected</c:when></c:choose>>11</option>
								<option value='12' <c:choose>  
								<c:when test="${memberType.month=='12'}"> selected</c:when></c:choose>>12</option>
						</select>
						</li>
						</ul>
						<ul class="newlongInput">
	  	    				<li class="newBoxleftTitle"><font color="#FF0000">*</font>金额</li>
	  	    				<li class="newBoxinput">
							<input type="text" id="price" name="price" value="${memberType.price}" maxlength="20"  />
							</li>
							
					</ul>
					<ul class="newlongInput">
	  	    				<li class="newBoxleftTitle">折扣</li>
	  	    				<li class="newBoxinput">
							<input type="text" id="discount" name="discount" value="${memberType.discount}" maxlength="20"  />
							</li>
							
					</ul>
					<ul class="newlongInput">
	  	    				<li class="newBoxleftTitle">折扣价</li>
	  	    				<li class="newBoxinput">
							<input type="text" id="disprice" name="disprice" value="${memberType.disprice}" maxlength="20" readonly="readonly"/>
							</li>
							
					</ul>
					</td>
		    		
		    		
		    	</tr>
		    </table>
		    
	    </div>

		
		 <%--分页信息START--%>
	    <div class="pageInfo"></div>
		 <%--分页信息END--%>
		
	</form>
</body>
</html>
