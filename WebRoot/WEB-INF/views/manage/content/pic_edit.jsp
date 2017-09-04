<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script language="javascript">
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
	});

</script>
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
				<table style="width:100%;min-width:800px;margin:0 auto;border:0px solid #ccc;" align="center" cellpadding="0" cellspacing="0" border="0"  id="type4">
			    	<tr id="1">
			    		<td id="333">
						<input type="button" id="addRows" value="添加图片" class="publichButton"/><p></p>&nbsp;
			    		</td>
					</tr>
		    		<tr>
					
			    		<td id="imgList" >
						
						
						<c:forEach items="${contentFile}" var="contentFile"  varStatus="status" >
						<ul style="float:left; width:750px;border:0px solid #ccc;" id='pic${status.index}' >
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
								
								<input type="button" value="删除" class="publichButton" id="imgdel"  onclick="delpic('pic${status.index}');" />								<input type="button" value="向下添加" class="publichButton" id="addimg"  onclick="addimgtemp('pic${status.index}');"/>
								<input type="hidden"   name="filePath" id="filePath" value="${contentFile.filePath}"/>
								<input type="hidden"  name="savePath" id="savePath" value="${contentFile.savePath}"/>
							
								<ul class="newlongTextarea" style="margin:0 auto;width:400px;" >
									
									<li class="newBoxinput" style="width:400px;">
											  <textarea width="100%" name="detailed" id="detailed"  >${contentFile.detailed}</textarea>
											</li>                 
								</ul>
								
								</br>&nbsp;&nbsp;&nbsp;&nbsp;序列：<input type="text"   name="signify" id="signify" value="${contentFile.signify}" style="width:50px"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
							</li>
						</ul>
						
                           </c:forEach>
						  
							<c:if test="${empty contentFile}">
							
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
									
									<li class="newBoxinput" style="width:400px;">
									<textarea width="100%" name="detailed" id="detailed" ></textarea>
									</li>                 
								</ul>
								</br>序列：<input type="text"   name="signify" id="signify" value="1" style="width:50px" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
							</li>
							
						</ul>
						</c:if>
						</td>
						
					</tr>
						
		    	</table>
			<script type="text/javascript">
		//行操作
		//var tmp=$("#imgList").html()
		var num=0;
		var num1=$('input[name="signify"]:last').val();
		
		
		
		var attmodel=$("#attModel").val();
		$(function(){//增加的一行方法1
			$("#addRows").click(function(){
				
			num++;
			
			var signify_num=$('input[name="signify"]').length+1;
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
							
							'		<li class="newBoxinput" style="width:400px;">'+
							'		<textarea width="100%" name="detailed" id="detailed" ></textarea>'+
							'		</li>                 '+
							'	</ul>'+
							'</br>序列：<input type="text"   name="signify" id="signify" value="'+signify_num+'" style="width:50px" onkeyup="this.value=this.value.replace(/\D/g,\'\')" onafterpaste="this.value=this.value.replace(/\D/g,\'\')"/>'+
							'</li>'+
						'</ul>';			
				
				$("#type4 tr").children().eq(1).append(picmodel);
				
				var file = $('input[name="upload_file"]:last');
				
				//file.attr('id', 'upload_file' + num);
				file.render();
				
				
			});
			
		}); 	
			function delpic(ids){
				document.getElementById(ids).innerHTML="";
				signify_no();
			}
			function addimgtemp(ids){
			num++;
			
			num1++;
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
							'<input type="button" value="向下添加" class="publichButton" id="addimg"  onclick="addimgtemp(\'pic_'+num+'\');"/>'+
							'	  <input type="hidden"   name="filePath" id="filePath" value=""/>'+
							'	  <input type="hidden"   name="savePath" id="savePath" value=""/>'+
							'	<ul class="newlongTextarea" style="flaot:left;width:400px;" >'+
							
							'		<li class="newBoxinput" style="width:400px;">'+
							'		<textarea width="100%" name="detailed" id="detailed" ></textarea>'+
							'		</li>                 '+
							'	</ul>'+
							'</br>序列：<input type="text"   name="signify" id="signify" value="'+num1+'" style="width:50px" onkeyup="this.value=this.value.replace(/\D/g,\'\')"onafterpaste="this.value=this.value.replace(/\D/g,\'\')"/>'+
							'</li>'+
						'</ul>';		
				$('#'+ids).append(picmodel);
				$('#upload_file'+num).render();
				signify_no();
			}
			function signify_no(){
				for(var i=0;i<$('input[name="signify"]').length;i++){
					$('input[name="signify"]:eq('+i+')').val(i+1);
				}
			}
		</script>