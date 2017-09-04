<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" /> 
		<meta http-equiv="cache-control" content="no-cache" /> 
		<meta http-equiv="expires" content="0" />

	    <title>new</title>

		<link type="text/css" rel="stylesheet" href="${basePath}style/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/backstage.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/backstage-v1.1.css" />	
		<link type="text/css" rel="stylesheet" href="${basePath}component/artdialog/skins/black.css" /> 
		<link type="text/css" rel="stylesheet" href="${basePath}component/js-fileupload/client/css/uploadify.css" /> 
		<link rel="stylesheet" href="${basePath}component/color/colorPicker.css" type="text/css" />
		
		<style type="text/css">
		    ul.labelAllBox{width:100%;border-bottom:0px solid #c2c2c2;height:30px;}
			ul.labelAllBox li.labelTypeTitle{color:#f00;width:100px;height:28px;line-height:28px;text-align:center;float:left;background:#a29f9f;border:0px solid #c2c2c2;margin:2px 8px;color:#fff;border-radius:5px;-webkit-border-radius:5px; -moz-border-radius:5px;}
			ul.labelAllBox li.labelTypeTitle1{color:#000;width:100px;height:28px;line-height:28px;text-align:center;float:left;background:#fff;border:0px solid #c2c2c2;margin:2px 8px;color:#999;border-radius:5px;-webkit-border-radius:5px; -moz-border-radius:5px;}
			.labelShowBox{width:100%;clear:both;border-bottom:0px solid #c2c2c2;}
			.labelShowBox .labelInfo{width:100%;background:#fff;min-height:40px}
			.labelButton{margin:10px;float:left;height:39px;line-height:39px;padding:0 10px; cursor:pointer;float:left; font-family:'微软雅黑';width:70px;text-align:center;position:relative;color:#666;background:#d9d9d9;border-radius:8px;-webkit-border-radius:8px; -moz-border-radius:8px;border:2px dashed #c2c2c2;}
		    .delLabelIcon{cursor:pointer;width:10px;height:10px;color:#333;position:absolute;top:2px;right:5px;font-size:8px;}
		</style>
		
		<script type="text/javascript" src="${basePath}js/jscolor.js"></script>
		<script type="text/javascript" src="${basePath}js/jquery-1.8.3.min.js"></script>
 		<script type="text/javascript" src="${basePath}js/xheditor-1.1.7/xheditor-1.1.7-zh-cn.min.js"></script>
		<script type="text/javascript" src="${basePath}component/js-fileupload/client/jquery.uploadify.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/jquery.artDialog.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/artDialog.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/plugins/iframeTools.js"></script>
		<script type="text/javascript" src="${basePath}js/validate.js"></script>
		<script type="text/javascript" src="${basePath}component/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${basePath}js/index-v1.1/checkboxStyle.js"></script>
		<script type="text/javascript" src="${basePath}component/color/jquery.colorPicker.js"/></script>
		<!--调色板 -->
		<script type="text/javascript">
		  	$(function() {  
		   		$('#titleColor').colorPicker();
		   	});
		  	
			function check(){
				//校验
				var flag=true;
				if(isNull($("#title").val())){
					art.dialog({content: "请输入标题", time: 2});
					flag= false;
				}
				
				if (nameSpecialCharacters($("#title").val())) {
					var message = "标题输入内容不符合规范，输入内容中不能包含`~!@#$^&*=|{}\':;\',\\[\\].<>《》/?~%！@#￥……&*——|{}【】‘；：”“\'。，、？";
					art.dialog({content: message, time: 2});
					flag= false;
				}
				if (nameSpecialCharacters($("#summary").val())) {
					var message = "摘要输入内容不符合规范，输入内容中不能包含`~!@#$^&*=|{}\':;\',\\[\\].<>《》/?~%！@#￥……&*——|{}【】‘；：”“\'。，、？";
					art.dialog({content: message, time: 2});
					flag= false;
				}
				if (nameSpecialCharacters($("#author").val())) {
					var message = "作者输入内容不符合规范，输入内容中不能包含`~!@#$^&*=|{}\':;\',\\[\\].<>《》/?~%！@#￥……&*——|{}【】‘；：”“\'。，、？";
					art.dialog({content: message, time: 2});
					flag= false;
				}
				if (nameSpecialCharacters($("#source").val())) {
					var message = "来源输入内容不符合规范，输入内容中不能包含`~!@#$^&*=|{}\':;\',\\[\\].<>《》/?~%！@#￥……&*——|{}【】‘；：”“\'。，、？";
					art.dialog({content: message, time: 2});
					flag= false;
				}
				
				
				
				return flag;
			}
			
			$(function(){
				var _MESSAGE = "${message}"; 
				if (_MESSAGE != '') {
					if (_MESSAGE.indexOf('成功') != -1) {
						art.dialog({content: _MESSAGE, lock: false, time: 2});
					} else {
						art.dialog({content: _MESSAGE, time: 2});
					}
				}
			
				$("#save").click(function(){
					var flag= check();
					if(flag){
						<c:choose>  
							<c:when test="${approveFlag eq '1'}">  
								$("#state").val("2");
							</c:when>
							<c:otherwise> 
								$("#state").val("4");
							</c:otherwise>  
						</c:choose>
						<c:choose>  
							<c:when test="${content.id ne null && content.id != 0}">  
								$("#contentEdit").attr("action","${basePath}/action/content/update");
							</c:when>
							<c:otherwise> 
								$("#contentEdit").attr("action","${basePath}/action/content/save");
							</c:otherwise>  
						</c:choose>
						$("#contentEdit").submit();
					}
				});
				$("#draft").click(function(){
					var flag= check();
					
					if(flag){
						<c:choose>  
							<c:when test="${content.id ne null && content.id != 0}">  
								$("#contentEdit").attr("action","${basePath}/action/content/update");
							</c:when>
							<c:otherwise> 
								$("#contentEdit").attr("action","${basePath}/action/content/save");
							</c:otherwise>  
						</c:choose>
						$("#contentEdit").submit();
					}
				});
		<c:choose>  
		<c:when test="${content.id ne null && content.id != 0}">  
				$("#columnname").click(function(){
				//	window.open("${basePath}/action/content/columnTree");
					art.dialog.open('${basePath}/action/content/columnTree')
				});
			</c:when>
		</c:choose>		
				$("#back").click(function(){
					$("#contentEdit").attr("action","${basePath}/action/content/goback");
					$("#contentEdit").submit();
				});
			});
	
		
			function changeColumn(name,ids,type){
		
				$("#columnname").val(name);
				$("#columnid").val(ids);
				//document.getElementsByName(column.id).value=ids;
			
				$("#columnType").val(type);
				
			}
		
		//标签
		function setTabLabel(name,cursel){	
			$('div[id^=con_label_]').css("display","none");
			document.getElementById("con_label_"+cursel).style.display="block";
			
			$('li[id^=tag_label]').attr("class","labelTypeTitle1"); 
			document.getElementById("tag_label"+cursel).className="labelTypeTitle";
		}
		//
		$(function(){
			//日排行
			<c:if test="${content.id ne 0}"> 
			var url = "${basePath}action/top/day/hits/${content.column.id}/${content.id}";
			$.ajax({type:"GET", url:url,dataType:"text", success:function(datas) {
				if(${content.id}!=0){
					$('#dayTop').val(datas);
				}else{
					$('#dayTop').val("0");
				}
			}});
			//周排行
			var url = "${basePath}action/top/week/hits/${content.column.id}/${content.id}";
			$.ajax({type:"GET", url:url,dataType:"text", success:function(datas) {
				if(${content.id}!=0){
					$('#weekTop').val(datas);
				}else{
					$('#weekTop').val("0");
				}
			}});
			//月排行
			var url = "${basePath}action/top/month/hits/${content.column.id}/${content.id}";
			$.ajax({type:"GET", url:url,dataType:"text", success:function(datas) {
				if(${content.id}!=0){
					$('#monthTop').val(datas);
				}else{
					$('#monthTop').val("0");
				}
			}});
			var url = "${basePath}action/top/total/hits/${content.column.id}/${content.id}";
			$.ajax({type:"GET", url:url,dataType:"text", success:function(datas) {
				if(${content.id}!=0){
					$('#hitTop').val(datas);
				}else{
					$('#hitTop').val("0");
				}
			}});
			</c:if>
			$("#dayTop").click(function(){
				var day=$('#dayTop').val();
				
				art.dialog({
  					content: '日点击量：<input type="text" id="day" size=8 value="'+day+'"/> ',
				  	ok: function () {
    					if(!isInteger($('#day').val())){
							art.dialog({content: '日点击量请输入整数'});
							return false;
						}
						var url = "${basePath}action/top/addhits-N/D/${content.column.id}/${content.id}/"+$('#day').val();
						var num=$('#day').val();
						
						$.ajax({type:"POST", url:url,dataType:"text", success:function(datas) {
							if(datas){
								$('#dayTop').val(num);
								art.dialog({content:'修改成功'});
							}
						}});
   					},
   					cancelVal: '关闭',
					cancel: true 
				});
			});
			//周点击
			$("#weekTop").click(function(){
				var week=$('#weekTop').val();
				art.dialog({
  					content: '周点击量：<input type="text" id="week" size=8 value="'+week+'"/> ',
				  	ok: function () {
    					if(!isInteger($('#week').val())){
							art.dialog({content: '周点击量请输入整数'});
							return false;
						}
						var url = "${basePath}action/top/addhits-N/W/${content.column.id}/${content.id}/"+$('#week').val();
						var num=$('#week').val();
						$.ajax({type:"POST", url:url,dataType:"text", success:function(datas) {
							if(datas){
								$('#weekTop').val(num);
								art.dialog({content:'修改成功'});	
							}
						}});
   					},
   					cancelVal: '关闭',
					cancel: true 
				});
			});
			//月点击
			$("#monthTop").click(function(){
				var month=$('#monthTop').val();
				art.dialog({
  					content: '月点击量：<input type="text" id="month" size=8 value="'+month+'"/>',
				  	ok: function () {
						if(!isInteger($('#month').val())){
							art.dialog({content: '月点击量请输入整数'});
							return false;
						}
						var url = "${basePath}action/top/addhits-N/M/${content.column.id}/${content.id}/"+$('#month').val();
						var num=$('#month').val();
						$.ajax({type:"POST", url:url,dataType:"text", success:function(datas) {
							if(datas){
								$('#monthTop').val(num);
								art.dialog({content:'修改成功'});	
							}
						}});
   					},
   					cancelVal: '关闭',
					cancel: true 
				});
			});
			//总点击
			$("#hitTop").click(function(){
				var hits=$('#hitTop').val();
				art.dialog({
  					content: '总点击量：<input type="text" id="hits" size=8 value="'+hits+'"/>',
				  	ok: function () {
						if(!isInteger($('#hits').val())){
							art.dialog({content: '总点击量请输入整数'});
							return false;
						}
						var url = "${basePath}action/top/addhits-N/T/${content.column.id}/${content.id}/"+$('#hits').val();
						var num=$('#hits').val();
						$.ajax({type:"POST", url:url,dataType:"text", success:function(datas) {
							if(datas){
								$('#hitTop').val(num);
								art.dialog({content:'修改成功'});	
							}
					}});
   				},
   					cancelVal: '关闭',
					cancel: true 
				});
			});
			
			
			
			$("#publish").click(function(){
				var url = '${basePath}/action/content/publish/';
				$("#contentEdit").attr("action",url);
					$("#contentEdit").submit();
			});
			$("#coverHad").click(function(){
						art.dialog.open('${basePath}/action/tree/contentFile/1', { id: 'ATT',title: '附件选择', width: 1480, height: 700,zIndex:999999})
					});
			});
		function closeW(){
					art.dialog({id:'ATT'}).close() 
				}
		</script>
		
	</head>
  
	<body>
	<form  name="contentEdit" id="contentEdit" method="post">
		<input type="hidden" name="state" id="state" value="1"/>
		<input type="hidden" name="contentType" id="contentType" value="${content.contentType}" />
		<input type="hidden" name="oublishState" id="oublishState" <c:if test="${approveFlag eq ''}">value="0" </c:if> />
		<c:choose>  
		<c:when test="${content.id ne null && content.id != 0}">  
			<input type="hidden" id="id" value="${content.id}" name="id"/>
		</c:when>
		</c:choose>
		 <%--按钮位置  START--%>
		<div class="selectBox">
			<table width="80%" height="42" cellpadding="0" cellspacing="0" border="0" >
				<tr>
					
					<td  >
					
					<input type="button" name="draft" id="draft" class="saveTimeButtonBG" title="暂存"/>
					<input type="button" name="save" id="save" class="saveButtonBG" title="保存"/>
					<input type="button" name="buttonName" id="back" class="cancelButtonBG" title="返回"/>
					<c:choose> 
					<c:when test="${content.column.columnType eq '5'}">  
					<input type="button" name="publish"  id="publish"  class="publishButtonBG" title="发布" style="display:none"  <c:if test="${empty content.oublishState }"> disabled="disabled" </c:if> />
					</c:when>
					</c:choose>
					</td>
					
					<td align="center" valign="middle">&nbsp;
						
					</td>
				</tr>
			</table>
		</div>
		 <%--按钮位置  END--%>
		
  	    <div class="newBox">
		<c:if test="${approveFlag eq '1'}">
		
		    <table style="width:100%;min-width:800px;margin:0 auto;border:0px solid #ccc;" align="center" cellpadding="0" cellspacing="0" border="0">
		    	<tr>
		    		<td>
					<ul class="newlongInput">
						<li class="newBoxleftTitle"><font color="#FF0000">*</font>	审核环节</li>
						<li class="newBoxinput">
						<select name="approvedUser.id" id="approvedUser"/>
						<c:forEach items="${approveUser}" var="approveUser"   > 
		    				<option value="${approveUser.id}"
							 <c:if test="${approveUser.id eq content.approvedUser.id}">selected="selected"</c:if>
							
							>${approveUser.userName}</option>
		    				
						</c:forEach>
		    			</select>
		    			
						</li>
					</ul>
					
					</td>
		    	</tr>
		    </table>
		
		</c:if>
			<c:if test="${list ne null|| !empty list}">
		 <table style="width:100%;min-width:800px;margin:0 auto;border:0px solid #ccc;" align="center" cellpadding="0" cellspacing="0" border="0">
		    	<tr>
		    		<td>
					<ul class="newlongInput">
				    			<li class="newBoxleftTitle">语言</li>
		  	    				<li class="newBoxinput">
								
		  	    					<select id="language" name="language"  >
									<option value="" >中文</option>
					    				<c:forEach items="${list}" var="list"   > 
					    				<c:if test="${list.language eq 'MN'}"><option value="MN" <c:if test="${content.language eq 'MN'}">selected</c:if>>蒙古语</option></c:if>
					    				<c:if test="${list.language eq 'EN'}"><option value="EN" <c:if test="${content.language eq 'EN'}">selected</c:if>>英语</option></c:if>
					    				<c:if test="${list.language eq 'JPN'}"><option value="JPN" <c:if test="${content.language eq 'JPN'}">selected</c:if>>日语</option></c:if>
					    				<c:if test="${list.language eq 'KOR'}"><option value="KOR" <c:if test="${content.language eq 'KOR'}">selected</c:if>>韩语</option></c:if>
										<c:if test="${list.language eq 'DEZ'}"><option value="DEZ" <c:if test="${content.language eq 'DEZ'}">selected</c:if>>德语</option></c:if>
										<c:if test="${list.language eq 'MCU'}"><option value="MCU" <c:if test="${content.language eq 'MCU'}">selected</c:if>>法语</option></c:if>
										<c:if test="${list.language eq 'OTH'}"><option value="OTH" <c:if test="${content.language eq 'OTH'}">selected</c:if>>其他</option></c:if>
										</c:forEach>
					    			</select>
					    		</li>
				    		</ul>
					
					</td>
		    	</tr>
		    </table>
			</c:if>
		    <table style="width:100%;min-width:800px;margin:0 auto;border:0px solid #ccc;" align="center" cellpadding="0" cellspacing="0" border="0">
		    	<tr>
		    		<td>
				
						
					
					<ul class="newShortBoxInput" >
						<li class="newBoxleftTitle"><font color="#FF0000">*</font>标题</li>
						<li class="newShortBoxinput">
						<input type="text" name="title" id="title" value="${content.title}" maxlength="150" />
						</li>
					</ul>
					<ul class="newShortBoxInput" style="width:180px;">
						<li class="newBoxleftTitle">标题颜色</li>
						<li class="newShortBoxinput" style="width:60px;margin-top:13px;">
							 <input id="titleColor" type="text" name="titleColor" value="<c:if test='${content.titleColor eq ""}'>#000000</c:if><c:if test='${empty content.titleColor}'>#000000</c:if><c:if test='${content.titleColor ne ""}'>${content.titleColor}</c:if>" />
						</li>
					</ul>
					<ul class="newShortBoxInput" >
						<li class="newBoxleftTitle">所属栏目</li>
						<li class="newShortBoxinput">
						<input type="text" name="column.name" id="columnname" value="${content.column.name}" readonly="readonly" />
						
						<input type="hidden" name="column.id" id="columnid" value="${content.column.id}" />
						<input type="hidden" name="column.columnType" id="columnType" value="${content.column.columnType}" />
						</li>
					</ul>
					<ul class="newShortInput">
						<li class="newBoxleftTitle">置顶</li>
						<li class="newBoxinput">
							<select name="putTop" id="putTop" style="width:35px;background:none;height:32px;line-height:32px;margin-top:5px;border:0px;">
								<option value="9" <c:if test="${content.putTop eq '9'}">selected="selected"</c:if>>无</option>
								<option value="1" <c:if test="${content.putTop eq '1'}">  selected="selected"</c:if>>1</option>
								<option value="2" <c:if test="${content.putTop eq '2'}">  selected="selected"</c:if>>2</option>
								<option value="3" <c:if test="${content.putTop eq '3'}">  selected="selected"</c:if>>3</option>
								<option value="4" <c:if test="${content.putTop eq 4}">  selected="selected"</c:if>>4</option>
								<option value="5" <c:if test="${content.putTop eq '5'}"> selected="selected"</c:if>>5</option>
								<option value="6" <c:if test="${content.putTop eq '6'}"> selected="selected"</c:if>>6</option>
							</select>
						</li>
					</ul>
					<ul class="newShortInput">
						<li class="newBoxleftTitle">推荐</li>
						<li class="newBoxinput">
						<input type="checkbox" name="recommend" id="recommend"  value="1" class="styled"   <c:if test="${content.recommend eq '1'}"> checked="checked"</c:if> />
						</li>
					</ul>
					
					<ul class="newShortBoxInput">
						<li class="newBoxleftTitle">作者</li>
						<li class="newShortBoxinput">
						<input type="text" name="author" id="author" value="${content.author}" maxlength="50" />
						</li>
					</ul>
					<ul class="newShortBoxInput">
						<li class="newBoxleftTitle">来源</li>
						<li class="newShortBoxinput">
						<input type="text" name="source" id="source" class="newInput"   value="${content.source}"  maxlength="50" />
						</li>
					</ul>
					<ul class="newShortBoxInput">
						<li class="newBoxleftTitle">发布日期</li>
						<li class="newShortBoxinput">
						<input type="text" id="publishDate" name="publishDate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd '})" style="width:95px;height:40px;line-height:40px;background-position:top right;"  value="<fmt:formatDate type='both' pattern='yyyy-MM-dd' value='${content.publishDate}' />"/>
						</li>
					</ul>
					<ul class="newlongTextarea">
						<li class="newBoxleftTitle">摘要</li>
						<li class="newBoxinput">
						<textarea name="summary" id="summary" >${content.summary}</textarea>
						</li>
					</ul>
					 <c:if test="${content.id ne 0}"> 
						<ul class="newShortBoxInput">
							<li class="newBoxleftTitle">日点击量</li>
							<li class="newShortBoxinput">
							<input type="button" id="dayTop" title="点击修改" />
							</li>
						</ul>
						<ul class="newShortBoxInput">
							<li class="newBoxleftTitle">周点击量</li>
							<li class="newShortBoxinput">
							<input type="button" id="weekTop" title="点击修改" />
							</li>
						</ul>
						<ul class="newShortBoxInput">
							<li class="newBoxleftTitle">月点击量</li>
							<li class="newShortBoxinput">
							<input type="button" id="monthTop" title="点击修改" />
							</li>
						</ul>
						<ul class="newShortBoxInput">
							<li class="newBoxleftTitle">总点击量</li>
							<li class="newShortBoxinput">
							<input type="button" id="hitTop" title="点击修改" />
							</li>
						</ul>
					</c:if>
					</td>
		    		
		    	</tr>
		    	<!--标签 -->
		    	<tr>
			    		<td class="titleFormBox" valign="middle">
							<c:forEach items="${content.webtags}" var="tag"  varStatus="status"  > 
							<div class="labelButton" onmouseover="$(this).children().css('display','block'); "  onmouseout="$(this).children().css('display','none');">
							${tag.title}
							<input type="hidden" name="webtags[${status.index}].id" value="${tag.id}"/>
							<div id="closeButton" class="delLabelIcon" onclick="$('#tag_${tag.id}').attr('class','labelStyle');$(this).parent().remove();">X</div>
							</div>
							</c:forEach>
			    			<div class="labelClass" id="addtag" onclick="showAllLabel();"></div>
		    		</td>
		    	</tr>
    	  </table>	
    	 
    	  <div id="allLabel" class="allLabel">
			 
			 <ul class="labelAllBox">
			  <c:forEach items="${tagType}" var="tagType" varStatus="status" >
			  
		       <li class="<c:if test='${status.index eq 0}'>labelTypeTitle</c:if><c:if test="${status.index ne 0}">labelTypeTitle1</c:if>" id="tag_label${status.index+1}" onclick="setTabLabel('tag_label',${status.index+1});">${tagType.name}</li>
			   </c:forEach>
	   	  	 </ul>
			  <div class="labelShowBox">
			  <c:forEach items="${tagType}" var="tagType" varStatus="tt" >
	           <div  id="con_label_${tt.index+1}" class="labelInfo" style="<c:if test='${tt.index ne 0}'>display:none;</c:if>">
					<c:forEach items="${tagList}" var="tagList" varStatus="status" >
					<c:if test="${tagList.type.id eq tagType.id}">
					<ul class="labelStyle" id="tag_${tagList.id}" onclick="addTag(this,'${tagList.id}','${tagList.title}');">
						<li class="leftlabel"></li>
						<li class="middlelabel">${tagList.title}</li>
						<li class="rightlabel"></li>
					</ul>
					</c:if>
 					</c:forEach>
			   </div>
			    </c:forEach>
			</div>
				<script type="text/javascript">
				<c:forEach items="${tagList}" var="tagList" varStatus="status" >
					<c:forEach items="${content.webtags}" var="webtags" >
						<c:if test="${webtags.id eq tagList.id}">
							$('#tag_${tagList.id}').attr('class','labelStyle1');
						</c:if>
					</c:forEach>
				</c:forEach>
				
				//添加标签
				var index=${fn:length(content.webtags)};
				function addTag(obj,ids,tagname){
				if($(obj).attr('class')=="labelStyle"){
				//var a='<div class="labelButton">标签内容<div class="delLabelIcon">X</div></div>';
				var tag= "<div class=\"labelButton\" onmouseover=\"$(this).children().css('display','block'); \"  onmouseout=\"$(this).children().css('display','none');\" >"+
				"<input type=\"hidden\" name=\"webtags["+index+"].id\" value=\""+ids+"\"/>"+
				tagname+
				"<div id=\"closeButton\" class=\"delLabelIcon\" onclick=\"$('#tag_"+ids+"').attr('class','labelStyle');$(this).parent().remove();\">X</div>"+
		 " </div>";
					$("#addtag").before(tag);
					}
					$(obj).attr('class','labelStyle1');
					
					index=index+1;
				}
				</script>
				
   		        <div id="closeButton" class="closeButton" onclick="$(this).parent().css('display','none');uploadbutton();"></div>
   		    </div>
		    	
				<jsp:include page='${editpage}'/>
				
		    	
	    </div>
</form>

<form action="http://60.31.195.83:8888/media/action/media/convert" method="post" id="convertForm" target="result">
  	 <input type="hidden" id="inputFileFullName" name="inputFileFullName" />
  	 <input type="hidden" id="outputFilePath" name="outputFilePath" />
  	 <input type="hidden" id="cutOutputFilePath" name="cutOutputFilePath" />
  	 <input type="hidden" id="pay" name="pay" value="${content.column.pay}" />
	 <input type="hidden" id="project" name="project" value="资源网" />
</form>
<iframe id="result" name="result" src="http://60.31.195.83:8888/media/action/mediaconvert/result" 
  	  	      	  		style="width: 0px;height: 0px; border: 0;overflow:hidden;filter:progid:DXImageTransform.Microsoft.Alpha(0);"></iframe>
		<script type="text/javascript">
			//文章
		    if($("#columnType").val()=='5'){
				 document.getElementById("back").style.display="none";
				 document.getElementById("publish").style.display="block";
		    }	
			
			function showAllLabel(){
				document.getElementById("allLabel").style.display="block";
				//var a=Number($('#att_upload_1').css("top").replace("px",""))+Number($('#allLabel').css("height").replace("px",""));
				//var b=Number($('#doc_upload_1').css("top").replace("px",""))+Number($('#allLabel').css("height").replace("px",""));
				//$('#att_upload_1').css("top",a)
				//$('#doc_upload_1').css("top",b)
			}
			function uploadbutton(){
				//$('#att_upload_1').css("top","auto");
				//$('#doc_upload_1').css("top","auto");
			}				
		</script>
		
	</body>
</html>
