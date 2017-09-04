<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head> 
	    
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" /> 
		<meta http-equiv="cache-control" content="no-cache" /> 
		<meta http-equiv="expires" content="0" />
	    <title>new</title>
		<link type="text/css" rel="stylesheet" href="${basePath}component/dhtmlx/tree/dhtmlxtree.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/backstage.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/backstage-v1.1.css" />	
		<link type="text/css" rel="stylesheet" href="${basePath}component/artdialog/skins/black.css" /> <link type="text/css" rel="stylesheet" href="${basePath}component/js-fileupload/client/css/uploadify.css" /> 
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
		
		<script type="text/javascript"   src="${basePath}js/jscolor.js"></script>
		<script type="text/javascript" src="${basePath}js/jquery-1.8.3.min.js"></script>
				<script type="text/javascript" src="${basePath}component/dhtmlx/dhtmlxcommon.js"></script>
		<script type="text/javascript" src="${basePath}component/dhtmlx/tree/dhtmlxtree.js"></script>
 		<script  type="text/javascript"  src="${basePath}js/xheditor-1.1.7/xheditor-1.1.7-zh-cn.min.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/jquery.artDialog.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/artDialog.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/plugins/iframeTools.js"></script>
		<script type="text/javascript" src="${basePath}js/validate.js"></script>
		<script type="text/javascript" src="${basePath}component/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${basePath}component/js-fileupload/client/jquery.uploadify.js"></script>
		<script type="text/javascript"  src="${basePath}js/index-v1.1/checkboxStyle.js"></script>
	
	<script type="text/javascript" src="${basePath}component/color/jquery.colorPicker.js"/></script>
		<!--调色板 -->
		<script type="text/javascript">
		  $(function() {  
		   $('#titleColor').colorPicker();
		    });
		</script>
		<script type="text/javascript" >
			$(document).ready(function(){
				//初始化xhEditor编辑器插件
				$('#content').xheditor({
				
					tools:'full',
					skin:'o2007silver',
					upMultiple:true,
					upImgUrl: "${basePath}UploadFileServlet",
					upImgExt: "jpg,jpeg,gif,bmp,png",
					onUpload:insertUpload,
					html5Upload:false
				});
				//xbhEditor编辑器图片上传回调函数
				function insertUpload(msg) {
					var _msg = msg.toString();
					var _picture_name = _msg.substring(_msg.lastIndexOf("/")+1);
					var _picture_path = Substring(_msg);
					var _str = "<input type='checkbox' name='_pictures' value='"+_picture_path+"' checked='checked' onclick='return false'/><label>"+_picture_name+"</label><br/>";
					$("#content").append(_msg);
					$("#uploadList").append(_str);
				}
				//处理服务器返回到回调函数的字符串内容,格式是JSON的数据格式.
				function Substring(s){
					return s.substring(s.substring(0,s.lastIndexOf("/")).lastIndexOf("/"),s.length);
				}
			});
		</script>
		<script type="text/javascript">
			$(function(){
			var files = $('input[name="upload_cover"]');
			files.each(function(){
				var file = $(this);
					file.render({
						
						buttonClass: 'custom',
						savePath: 'website/www/contentImg/',
						httpPath: 'website/www/contentImg/',
						basePath: '${basePath}',
						fileTypeExts : '*.gif; *.jpg; *.png; *.bmp; *.jpeg',
						componentPath: '${basePath}component/js-fileupload/client/',
						type: 'image',
						callback: function(fileElement, file, saveFile, httpFile){
							var index = file.lastIndexOf('.');
							var ext = file.substring(index, file.length);
							$('#coverName').val(file.substring(0,file.lastIndexOf('.')));
							$('#coverPath').val(httpFile + ext);
							
						}
					});
					
				file.preview(file.attr('title'));
				});
			//图片集
			var files_img = $('input[name="upload_file"]');
			files_img.each(function(){
				var file_img = $(this);
					file_img.render({
						
						buttonClass: 'custom',
						savePath: 'website/www/contentImg/',
						httpPath: 'website/www/contentImg/',
						basePath: '${basePath}',
						fileTypeExts : '*.gif; *.jpg; *.png; *.bmp; *.jpeg',
						componentPath: '${basePath}component/js-fileupload/client/',
						type: 'image',
						callback: function(fileElement, file, saveFile, httpFile){
							var row = fileElement.parentElement.parentElement;
							var index = file.lastIndexOf('.');
							var ext = file.substring(index, file.length);
							$('input[name="fileName"]', row).val(file.substring(0,file.lastIndexOf('.')));
							$('input[name="filePath"]', row).val(httpFile + ext);
							$('input[name="savePath"]', row).val(saveFile);
						}
					});
				file_img.preview(file_img.attr('title'));
				});
				if($("input[name='column.columnType']").val()=='3'||$("input[name='column.columnType']").val()=='4'||$("input[name='column.columnType']").val()=='7'){
				var attmodel='<ul class="newlongInput">'+
								'<li class="newBoxleftTitle">附件名称</li>'+
								'<li class="newBoxinput">'+
									'<input  id="attachmentfileName"  name="attachmentfileName" type="text"  value=""/>'+
					    			'<input type="hidden" id="attachmentfilePath" name="attachmentfilePath"  value="" />	'+	
									'<input type="hidden" name="attachmentsavePath" id="attachmentsavePath" value=""/>'+
					    		'</li>'+
							'</ul>'+
							'<ul class="newShortInput">'+
							'	<li class="newBoxleftTitle">可下载</li>'+
					    			
					    	'		<li class="newBoxinput">'+
					    	'		<input type="checkbox" name="checkbox" id="checkbox" onclick="downLoad_Check(this);"  class="styled"  /><input type="hidden" name="attachmentDownload"   id="attachmentDownload" value=""/>'+
							'	</li>'+
							'</ul>'+
							
							'<label id="price_label" style="visibility:hidden">'+
							'<ul class="newlongInput">'+
							'	<li class="newBoxleftTitle">价格</li>'+
					    	'	<li class="newBoxinput">'+
							'		<input type="text" name="price" id="price" value=""  />'+
					    	'		<!--<input type="button"  value="删除"  onclick="$(this).parent().parent().remove()"  class="buttonBG"/> -->'+
				    		'	</li>'+
				    		'</ul>'+
							'</label>'+
							'<ul class="newlongTextarea">'+
							'	<li class="newBoxleftTitle">描述</li>'+
							'	<li class="newBoxinput">'+
							'	<textarea  name="attachmentfileDescribe" id="attachmentfileDescribe" ></textarea>'+
							'	</li>'+
							'</ul>';
				if($("input[name='column.columnType']").val()=='7'){
					attmodel='<ul class="newlongInput">'+
							 '	<li class="newBoxleftTitle">附件名称</li>'+
								'<li class="newBoxinput">'+
								'<input  id="attachmentfileName"  name="attachmentfileName" type="text"  value=""/>'+
					    		'	<input type="hidden" id="attachmentfilePath" name="attachmentfilePath"  value="" />'+
								'	<input type="hidden" name="attachmentsavePath" id="attachmentsavePath" value=""/>'+
					    		'</li>'+
							'</ul>'+
							'<ul class="newShortInput">'+
								'<li class="newBoxleftTitle">可下载</li>'+
					    			
					    		'	<li class="newBoxinput">'+
					    		'	<input type="checkbox" name="checkbox" id="checkbox" onclick="downLoad_Check(this);"   /><input type="hidden" name="attachmentDownload"   id="attachmentDownload" value=""/>'+
								'</li>'+
							'</ul>'+
							
							'<ul class="newShortInput">'+
							'	<li class="newBoxleftTitle">主打</li>'+
					    	'		<li class="newBoxinput">'+
							'		<input type="checkbox" name="theme" id="theme" onclick="downLoad_Check(this);"    /><input type="hidden" name="attachmentTheme"   id="attachmentTheme" value=""/>'+
							'	</li>'+
							'</ul>'+
							
							'<label id="price_label" style="visibility:hidden">'+
							'<ul class="newlongInput">'+
							'	<li class="newBoxleftTitle">价格</li>'+
					    	'	<li class="newBoxinput">'+
							'		<input type="text" name="price" id="price" value=""  />'+
					    	'		<!--<input type="button"  value="删除"  onclick="$(this).parent().parent().remove()"  class="buttonBG"/> -->'+
				    		'	</li>'+
				    		'</ul>'+
							'</label>'+
							'<ul class="newlongTextarea">'+
							'	<li class="newBoxleftTitle">描述</li>'+
							'	<li class="newBoxinput">'+
							'	<textarea  name="attachmentfileDescribe" id="attachmentfileDescribe" ></textarea>'+
							'	</li>'+
							'</ul>';
				}
				var fileType="";
				<c:if test="${content.column.columnType eq 7}">
					fileType='*.wma; *.amr; *.mp3';
				</c:if>
				<c:if test="${content.column.columnType eq 4}">
					 fileType='*.wmv; *.asf; *.asx; *.rm; *.rmvb; *.mpg; *.mpeg; *.mpe; *.3gp; *.mov; *.mp4; *.m4v; *.avi; *.dat; *.mkv; *.flv; *.vob; *.m4a; *.f4v; *.ogg; *.dv; *.dif; *.ts; *.aac; *.ac3;  *.swf; *.wav; *.lavf; *.dvix; *.qt; *.divx; *.cpk; *.fli; *.flc; *.mod';
				</c:if>
				$('#att_upload_1').render({
					buttonClass: 'custom',
					savePath: 'website/upload_file/sildeshow/',
					httpPath: 'website/upload_file/sildeshow/',
					basePath: '${basePath}',
					componentPath: '${basePath}component/js-fileupload/client/',
					type: 'any',
					fileTypeExts :fileType, 
					callback: function(fileElement, file, saveFile, httpFile){
							
							$('input[name="attachmentfilePath"]:last').val(httpFile);
							$('input[name="attachmentfileName"]:last').val(file);
						},
					removeCompleted: false,
					customElements: attmodel,
					fixed: {top: '88%', left: '80%'},
					queueID: 'att_upload_1-queue'
					
				});
				}
				
				//文档
				
				if($("input[name='column.columnType']").val()=='6'){
				var docmodel='<ul class="newlongInput">'+
									'<li class="newBoxleftTitle">剧本名称</li>'+
									'<li class="newBoxinput"><input  id="documentName"  name="documentName" type="text"  value=""/><input type="hidden" id="docDownPath" name="docDownPath"  value="" />'+	
										'<input type="hidden" id="docSavePath" name="docSavePath"  value="" />	'+
									'</li>'+
								'</ul>'+
								'<ul class="newlongTextarea">'+
									'<li class="newBoxleftTitle">剧本描述</li>'+
									'<li class="newBoxinput"><textarea  name="documentDescribe" id="documentDescribe" ></textarea></li>'+
								'</ul>';
				
				
				
					$('#doc_upload_1').render({
						buttonClass: 'custom',
						
						savePath: 'website/www/contentDoc/',
						httpPath: 'website/www/contentDoc/',
						basePath: '${basePath}',
						componentPath: '${basePath}component/js-fileupload/client/',
						type: 'any',
						fileTypeExts : '*.doc; *.docx; *.xls; *.xlsx; *.ppt; *.pptx',
						callback: function(fileElement, file, saveFile, httpFile){	
						
								$('#docDownPath').val(httpFile);
								$('#documentName').val(file);
								$('#docSavePath').val(saveFile);
						},
						onDialogClose: function(){
							if($('#docDownPath').val() != ''){
								$('#docFile').html("");
								$('#doc_upload_1-queue').find('.uploadify-queue-item-any:not(:last)').remove();
							}
						},

						removeCompleted: false,
						customElements:docmodel,
						fixed: {top: '60%', left: '73%'},
						queueID: 'doc_upload_1-queue'
					});
				}
			
			});
		</script>
		<script type="text/javascript">
		function check(){
				//校验
				var flag=true;
				if(isNull($("#title").val())){
					art.dialog({content: "请输入标题", time: 2});
					flag= false;
				}
				if(isNull($("input[name='column.id']").val())){
					art.dialog({content: "请选择所属栏目", time: 2});
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
				if($("input[name='column.columnType']").val()!='${content.contentType}'){
					art.dialog({content: "您选择的所属栏目的类型与该文章内容不符，请重新选择", time: 2});
					flag= false;
				}
				
				return flag;
			}
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
					var flag= check();
					if(flag){
						$("#contentEdit").attr("action","${basePath}/action/rescheck/update");
						$("#contentEdit").submit();
					}
				});
				//tree
				
				$("#openTree").click(function(){
					//window.open("${basePath}/action/rescheck/columnTree");
					art.dialog.open('${basePath}/action/rescheck/columnTree/${content.contentType}', {title: '所属栏目',drag: false,
    resize: false});
					
				});
				$("#back").click(function(){
					$("#contentEdit").attr("action","${basePath}/action/rescheck/goback");
					$("#contentEdit").submit();
				});
			});
			$(function(){

		
			
	});
		</script>
		
		<script type="text/javascript">
		//标签
		function setTabLabel(name,cursel){	
		
			
			$('div[id^=con_label_]').css("display","none");
			document.getElementById("con_label_"+cursel).style.display="block";
			
			$('li[id^=tag_label]').attr("class","labelTypeTitle1"); 
			document.getElementById("tag_label"+cursel).className="labelTypeTitle";
		}
		//
		$(function(){
			
			
			//月排行
			
			
			$("#view").click(function(){
				var url = "${basePath}action/content/view/${content.id}";
				//alert(url);
				art.dialog.open(url, {title: '${content.title}',drag: false,
    resize: false});
	
				//window.open(url);
			});
		});
		</script>
	</head>
  
	<body>
	<form  name="contentEdit" id="contentEdit" method="post">
		<input type="hidden" name="oublishState" id="oublishState" value="0" />
		
			<input type="hidden" id="id" value="${content.id}" name="id"/>
			 <input type="hidden" name="contentType" id="contentType" value="${content.contentType}" />
		 <%--按钮位置  START--%>
		<div class="selectBox">
			<table width="100%" height="42" cellpadding="0" cellspacing="0" border="0" >
				<tr>
					<td >
					<!--<input type="button" name="view" id="view" class="buttonBG" value="查看内容"/> -->
					<input type="button" name="save" id="save" class="saveButtonBG" title="保存"/>
					<input type="button" name="buttonName" id="back" class="cancelButtonBG" title="返回"/>
					</td>
					
					<td align="center" valign="middle">&nbsp;
						
					</td>
				</tr>
			</table>
		</div>
		 <%--按钮位置  END--%>
		
		<div class="newBox">
		    <table style="width:100%;min-width:800px;margin:0 auto;border:0px solid #ccc;" align="center" cellpadding="0" cellspacing="0" border="0">
		    	<tr>
		    		<td>
					<ul class="newlongInput">
						<li class="newBoxleftTitle"><font color="#FF0000">*</font>	审核环节</li>
						<li class="newBoxinput">
						<select name="approvedUser.id" id="approvedUser" />
						<c:forEach items="${approveUser}" var="approveUser"   > 
		    				<option value="${approveUser.id}"
							 <c:if test="${approveUser.id eq content.approvedUser.id}">selected="selected"</c:if>
							
							>${approveUser.userName}</option>
		    				
						</c:forEach>
		    			</select>
		    			
						</li>
					</ul>
					<ul class="newlongInput">
						<li class="newBoxleftTitle">审核选项</li>
						<li class="newBoxinput">
						<select  name="opt" id="opt"/>
		    				<option value="2">审核</option>
		    				<option value="3">终审</option>
		    				<option value="4">退回</option>
		    				
		    			</select>
						</li>
					</ul>
					</td>
		    	</tr>
		    </table>
		</div>
		
  	    <div class="newBox">
		    <table style="width:100%;min-width:800px;margin:0 auto;border:0px solid #ccc;" align="center" cellpadding="0" cellspacing="0" border="0">
		    	<tr>
		    		<td>
					<ul class="newShortBoxInput" >
						<li class="newBoxleftTitle"><font color="#FF0000">*</font>标题</li>
						<li class="newShortBoxinput">
						<input type="text" name="title" id="title"  value="${content.title}" maxlength="150"/>
		    			
						</li>
					</ul>
					<ul class="newShortBoxInput" style="width:180px;">
						<li class="newBoxleftTitle">标题颜色</li>
						<li class="newShortBoxinput" style="width:60px">
						<input id="titleColor" type="text" name="titleColor" value="<c:if test='${content.titleColor eq ""}'>#000000</c:if><c:if test='${empty content.titleColor}'>#000000</c:if><c:if test='${content.titleColor ne ""}'>${content.titleColor}</c:if>" />
						</li>
					</ul>	
					<ul class="newShortBoxInput" style="position:relative;cursor:pointer" id='openTree'>
						<li class="newBoxleftTitle" style="color:#f00; cursor:pointer "  title="点击弹出">所属栏目</li>
						<li class="newShortBoxinput" style="cursor:pointer">
						<input type="text" name="column.name" id="column.name" value="${content.column.name}" style="cursor:pointer" readonly="readonly"  title="点击弹出"/>
		    		 
		    		  	<input type="hidden" name="column.id" id="column.id" value="${content.column.id}" />
						<input type="hidden" name="column.columnType" id="column.columnType" value="${content.contentType}" />
						</li>
						<!--<input name="button" type="button" id="openTree"  value="         "  style="width:70px;cursor:pointer;position:absolute;height:42px;line-height:42px;top:0px;left:0px;background:none;border:0px;"/> -->
					</ul>	
					 
					 <!--浏览权限：游客<input name="views" id="views" type="radio" value="1" />会员<input name="views" id="views" type="radio" value="2" /> 任何人<input name="views" id="views" type="radio" /> -->
					<ul class="newShortInput">
						<li class="newBoxleftTitle">置顶</li>
						<li class="newBoxinput">
						<select name="putTop" id="putTop" style="width:35px;background:none;height:32px;line-height:32px;margin-top:5px;border:0px;">
						<option value="">无</option>
						<option value="1" <c:if test="${content.putTop eq '1'}"> selected="selected"</c:if>>1</option>
						<option value="2" <c:if test="${content.putTop eq '2'}"> selected="selected"</c:if>>2</option>
						<option value="3" <c:if test="${content.putTop eq '3'}"> selected="selected"</c:if>>3</option>
						<option value="4" <c:if test="${content.putTop eq '4'}"> selected="selected"</c:if>>4</option>
						<option value="5" <c:if test="${content.putTop eq '5'}"> selected="selected"</c:if>>5</option>
						<option value="6" <c:if test="${content.putTop eq '6'}"> selected="selected"</c:if>>6</option>
						</select>
						</li>
					</ul>
					<ul class="newShortInput">
						<li class="newBoxleftTitle">推荐</li>
						<li class="newBoxinput">	
						<input type="checkbox" name="recommend" id="recommend"  value="1"  class="styled" <c:if test="${content.recommend eq '1'}"> checked="checked"</c:if>/>
						</li>
					</ul>
					<ul class="newlongTextarea">
						<li class="newBoxleftTitle">摘要</li>
						<li class="newBoxinput">
						<textarea name="summary" id="summary">${content.summary}</textarea>
						</li>
					</ul>	 
				<ul class="newShortBoxInput">
						<li class="newBoxleftTitle">作者</li>
						<li class="newShortBoxinput">		
						<input type="text" name="author" id="author"  value="${content.author}"  maxlength="50" /> 
						</li>
					</ul>
					<ul class="newShortBoxInput">
						<li class="newBoxleftTitle">来源</li>
						<li class="newShortBoxinput">
						<input type="text" name="source" id="source"  value="${content.source}"  maxlength="50" />
						</li>
					</ul>	
					<ul class="newShortBoxInput">
						<li class="newBoxleftTitle">发布日期</li>
						<li class="newShortBoxinput">
						<input type="text" id="publishDate" name="publishDate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd '})" style="width:300px;height:40px;line-height:40px;"  value="${content.publishDate}"/> 	
						</li>
					</ul>	
					
					
					</td>
		    	</tr>
		    	<tr >
			    		<td>
			    	
		
							<c:forEach items="${content.webtags}" var="tag"  varStatus="status"  > 
							<div class="labelButton" onmouseover="$(this).children().css('display','block'); "  onmouseout="$(this).children().css('display','none');">
							${tag.title}
							<input type="hidden" name="webtags[${status.index}].id" value="${tag.id}"/>
							<div id="closeButton" class="delLabelIcon" onclick="$('#tag_${tag.id}').attr('class','labelStyle');$(this).parent().remove();">X</div>
							</div>
							</c:forEach>
			    			<div class="labelClass" id="addtag" onclick="showAllLabel();">				</div>		    		</td>
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
		    	
			<!--封面图片 -->
			<table style="width:620px;border:0px solid #ccc;" cellpadding="0" cellspacing="0" border="0" id="type2">    	
			    	<tr>
			    		<td  rowspan="2"  style="padding-top:10px;width:170px;">
							<input type="file" id="upload_cover" name="upload_cover"  title="${content.coverPath}" />						
						</td>
			    		<td>
							<ul class="newlongInput">
								<li class="newBoxleftTitle">封面图片</li>
								<li class="newBoxinput">
									<input type="text"  name="coverName" id="coverName" value="${content.coverName}" style="width:98%;" /> 
									<input type="hidden"  name="coverPath" id="coverPath" value="${content.coverPath}"/>
								</li>
							</ul>
						</td>
					</tr>
					<tr>
						<td>
							<ul class="newlongTextarea" style="margin:0 auto;width:400px;" >
								<li class="newBoxleftTitle">图片描述</li>
								<li class="newBoxinput" style="width:400px;">
								<textarea  name="coverDescribe" id="coverDescribe" style="width:277px;">${content.coverDescribe}</textarea>
								</li>
							</ul>
			    		</td>
			    	</tr>
		  		</table>
				<!--word文本输入 -->
				<table style="width:100%;min-width:800px;margin:0 auto;border:0px solid #ccc;" align="center" cellpadding="0" cellspacing="0" border="0"id="type1">	   	
			    	<tr >
			    		<td >
						<textarea id="content" name="content" rows="15" style="width: 160%; border: 1px; display: block;">${Detailed}</textarea>
			    		</td>
			    	</tr>
		    	</table>
				<!--文档上传 -->
				<table style="width:100%;min-width:800px;margin:0 auto;border:0px solid #ccc;" align="center" cellpadding="0" cellspacing="0" border="0" id="type5" >		
			    	<tr>
			    		<td>
						<ul style="width:100%;min-width:800px;clear:both;border-bottom:0px solid #ccc;padding:10px 0;">
							<li style="float:left;width:100px;">
							<input type="file" id="doc_upload_1" name="doc_upload" />
							</li>
							<li style="float:left;padding-top:8px;">
									<div id="doc_upload_1-queue" style="margin: 35px 0;width: 100%;height: auto;"></div>
							</li>
							<div id="docFile">
							<c:if test="${content.documentPath ne null}">
							<ul class="newlongInput">
								<li class="newBoxleftTitle">剧本名称</li>
								<li class="newBoxinput"><input  id="documentName"  name="documentName" type="text"   value="${content.documentName}"/>
					    				<input type="hidden" id="docDownPath" name="docDownPath"  value="${content.docDownPath}" />	
										<input type="hidden" id="docSavePath" name="docSavePath"  value="${content.docSavePath}" />	
					    		</li>
							</ul>
				    		<ul class="newlongTextarea">
								<li class="newBoxleftTitle">剧本描述</li>		
								<li class="newBoxinput">
								<textarea  name="documentDescribe" id="documentDescribe">${content.documentDescribe}</textarea>
								</li>
							</ul>
							</c:if>
							</div>
						</ul>
			    		</td>
			    	</tr>
		    	</table>
				<!--图片集合 -->
				<table style="width:100%;min-width:800px;margin:0 auto;border:0px solid #ccc;" align="center" cellpadding="0" cellspacing="0" border="0"  id="type4">
			    	<tr>
			    	  
			    		<td>
			    			<input type="button" id="addRows" value="添加图片" class="publichButton"/><p></p>&nbsp;</td>
		    		</tr>
		    		<tr>
			    	
						
			    		<td id="imgList">
						<c:forEach items="${content.contentFile}" var="contentFile"  varStatus="status" >
						<ul style="float:left; width:750px;border:0px solid #ccc;" id="pic${status.index}" >
							<li  style="float:left">
								<input type="file" id="upload_file_${status.index}" name="upload_file"  title="${contentFile.filePath}" />
							</li>
							<li  style="float:right;width:580px;">
						
								<ul class="newlongInput">
									<li class="newBoxleftTitle">图片名称</li>
									<li class="newBoxinput">
										 <input type="text"   name="fileName" id="fileName" value="${contentFile.fileName}"/>
									</li>
								</ul> 
								<input type="button" value="删除" class="publichButton" id="imgdel" onclick="delpic('pic${status.index}');" />
								<input type="hidden"   name="filePath" id="filePath" value="${contentFile.filePath}"/>
								<input type="hidden"  name="savePath" id="savePath" value="${contentFile.savePath}"/>
							
								<ul class="newlongTextarea" style="margin:0 auto;width:400px;" >
									<li class="newBoxleftTitle">图片描述</li>
									<li class="newBoxinput" style="width:400px;">
											  <textarea width="100%" name="detailed" id="detailed"  >${contentFile.detailed}</textarea>
											</li>
													 
								</ul>
							</li>
						</ul>
						
                           </c:forEach>
							<c:if test="${empty content.contentFile}">
						<ul style="float:left; width:750px;border:0px solid #ccc;" id="pic" >
							
							<li  style="float:left"><input type="file" id="upload_file_0" name="upload_file"  title="" /></li>
							<li  style="float:right;width:580px;">
								<ul class="newlongInput">
									<li class="newBoxleftTitle">图片名称</li>
									<li class="newBoxinput">
									<input type="text"  name="fileName" id="fileName" value=""/>
									</li>
								</ul>
								  <input type="button" value="删除" class="publichButton" id="imgdel"  onclick="delpic('pic');"/>
								  <input type="hidden"   name="filePath" id="filePath" value=""/>
								  <input type="hidden"   name="savePath" id="savePath" value=""/>
							
								<ul class="newlongTextarea" style="flaot:left;width:400px;" >
									<li class="newBoxleftTitle">图片描述</li>
									<li class="newBoxinput" style="width:400px;">
									<textarea width="100%" name="detailed" id="detailed" ></textarea>
									</li>                 
								</ul>
							</li>
							
						</ul>
						</c:if>
						</td>
		    		</tr> 
					
		    	</table>
		    	<!--其他附件 -->
		    	<table style="width:100%;min-width:800px;margin:0 auto;border:0px solid #ccc;" align="center" cellpadding="0" cellspacing="0" border="0" id="type3" >	
			    	<tr >
			    		<td>
				    		<c:forEach items="${content.contentFile}" var="contentFile"   > 	
							<div >
							<ul class="newlongInput">
								<li class="newBoxleftTitle">附件名称</li>
								<li class="newBoxinput">
								<input  id="attachmentfileName"  name="attachmentfileName" type="text"  value="${contentFile.fileName}"/>
								<input type="hidden" id="attachmentfilePath" name="attachmentfilePath"  value="${contentFile.filePath}" />		
								</li>
							</ul>
							<ul class="newShortInput">
								<li class="newBoxleftTitle">可下载</li>
								<li class="newBoxinput">
					    		<input type="checkbox" name="checkbox" id="checkbox" onclick="if($(this).attr(\'checked\'))$(this).next().val(\'1\');" <c:if test="${contentFile.download eq '1'}"> class="styled" checked="checked"</c:if>  /><input type="hidden" name="attachmentDownload" id="attachmentDownload" value="${contentFile.download}"/>
								</li>
							</ul>
							<label id="price_label" style="visibility:hidden">
							<ul class="newlongInput">
								<li class="newBoxleftTitle">价格</li>
					    		<li class="newBoxinput">
					    		<input type="text" name="price" id="price" value="${contentFile.price}"  /></label>
					    			<!--<input type="button"  value="删除"  class="buttonBG" onclick="$(this).parent().parent().remove()" /> -->
				    			</li>
				    		</ul>
							</label>
							<ul class="newlongTextarea">
								<li class="newBoxleftTitle">描述</li>
								<li class="newBoxinput">	
								<textarea  name="attachmentfileDescribe" id="attachmentfileDescribe"  >${contentFile.detailed}</textarea>
								</li>
							</ul>
							</div>
				    		</c:forEach>
								<li style="float:left;width:100px;">
								<input type="file" id="att_upload_1" name="file_upload" />
					    		</li>
								<li style="float:left;padding-top:8px;">
									<div id="att_upload_1-queue" style="margin: 35px 0;width: 100%;height: auto;"></div>
								</li>
							</ul>
			    		</td>
			    	</tr>
		    	</table>
	    </div>
		<DIV id="tree" style="display:none"><div id="columnSelector" style="font-size:32px; background-color:#236bcd;margin-top: 10px;"></div></DIV>
</form>
		<script type="text/javascript">
			//新闻
			
				<c:if test="${content.contentType eq '1'}">
						 document.getElementById("type1").style.display="block";
						 document.getElementById("type2").style.display="none";
						 document.getElementById("type3").style.display="none";
						  document.getElementById("type5").style.display="none";
						   document.getElementById("type4").style.display="none";
				</c:if>
				//图片
				<c:if test="${content.contentType eq '2'}">
						
						 document.getElementById("type1").style.display="none";
						 document.getElementById("type3").style.display="none";
						 document.getElementById("type2").style.display="block";
						  document.getElementById("type4").style.display="block";
						   document.getElementById("type5").style.display="none";
				</c:if>
				//产品
				<c:if test="${content.contentType eq '3'}">
						
						 document.getElementById("type1").style.display="none";
						 document.getElementById("type3").style.display="block";
						 document.getElementById("type2").style.display="block";
						 document.getElementById("type4").style.display="none";
						  document.getElementById("price_label").style.visibility="visible";
						   document.getElementById("type5").style.display="none";
				</c:if>
				//视频
				<c:if test="${content.contentType eq '4'}">
							
						 document.getElementById("type1").style.display="none";
						 document.getElementById("type3").style.display="block";
						 document.getElementById("type2").style.display="block";
						 document.getElementById("type4").style.display="none";
						  document.getElementById("type5").style.display="none";
				</c:if>
				//音频
				<c:if test="${content.contentType eq '7'}">
							
						 document.getElementById("type1").style.display="none";
						 document.getElementById("type3").style.display="block";
						 document.getElementById("type2").style.display="block";
						 document.getElementById("type4").style.display="none";
						  document.getElementById("type5").style.display="none";
				</c:if>
				//文章
				<c:if test="${content.contentType eq '5'}">
					document.getElementById("type1").style.display="block";
					 document.getElementById("type2").style.display="none";
					 document.getElementById("type3").style.display="none";
					 document.getElementById("type4").style.display="none";
					 document.getElementById("type5").style.display="none";
					  document.getElementById("back").style.display="none";
				</c:if>	
				//文库
				
				<c:if test="${content.contentType eq '6'}">
						 document.getElementById("type1").style.display="none";
						 document.getElementById("type3").style.display="none";
						 document.getElementById("type2").style.display="none";
						 document.getElementById("type4").style.display="none";
						 document.getElementById("type5").style.display="block";			 
				</c:if>			
		
			
		</script>
		<script type="text/javascript">
		//行操作
		//var tmp=$("#imgList").html()
		var num=0;
		
		var attmodel=$("#attModel").val();
		$(function(){//增加的一行方法1
			$("#addRows").click(function(){	
			num++;
			var picmodel='<ul style="float:left; width:750px;border:0px solid #ccc;" id="pic_'+num+'" >'+
							'<li  style="float:left"><input type="file" id="upload_file'+num+'" name="upload_file" /></li>'+
							'<li  style="float:right;width:580px;">'+
							'	<ul class="newlongInput">'+
							'		<li class="newBoxleftTitle">图片名称</li>'+
							'		<li class="newBoxinput">'+
							'		<input type="text"  name="fileName" id="fileName" value=""/>'+
							'		</li>'+
							'	</ul>'+
							'	  <input type="button" value="删除" class="publichButton" id="imgdel" onclick="delpic(\'pic_'+num+'\')" />'+
							'	  <input type="hidden"   name="filePath" id="filePath" value=""/>'+
							'	  <input type="hidden"   name="savePath" id="savePath" value=""/>'+
							'	<ul class="newlongTextarea" style="flaot:left;width:400px;" >'+
							'		<li class="newBoxleftTitle">图片描述</li>'+
							'		<li class="newBoxinput" style="width:400px;">'+
							'		<textarea width="100%" name="detailed" id="detailed" ></textarea>'+
							'		</li>                 '+
							'	</ul>'+
							'</li>'+
						'</ul>';
				$("#type4 tr").children().eq(1).append(picmodel);
				var file = $('input[name="upload_file"]:last');
				
				file.render();
				
			});
			
		}); 	
		function delpic(ids){
				document.getElementById(ids).innerHTML="";
		}
		function showAllLabel(){
				document.getElementById("allLabel").style.display="block";
				//var a=Number($('#att_upload_1').css("top").replace("px",""))+Number($('#allLabel').css("height").replace("px",""));
				//$('#att_upload_1').css("top",a)
			}
			function uploadbutton(){
				//$('#att_upload_1').css("top","auto");
			}					
		</script>
	</body>
</html>
