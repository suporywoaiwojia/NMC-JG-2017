<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script language="javascript">
var attmodel='<div><ul class="newlongInput">'+
								'<li class="newBoxleftTitle">附件名称</li>'+
								'<li class="newBoxinput">'+
									'<input  id="attachmentfileName"  name="attachmentfileName" type="text"  value=""/>'+
					    			'<input type="hidden" id="attachmentfilePath" name="attachmentfilePath"  value="" />	'+	
									'<input type="hidden" name="attachmentsavePath" id="attachmentsavePath" value=""/>'+
					    		'</li>'+
							'</ul>'+
							
							'<ul>'+
								'<li class="newBoxleftTitle"></li>'+
					    		'<li >'+
								'	<input  type="hidden"  name="price" id="price" value=""  /> '+
					    		'<input type="button"  value=""  onclick="$(this).parent().parent().parent().remove()"  class="delButtonBG"/>'+
				    			'</li>'+
				    		'</ul>'+
					    
							'<ul class="newlongTextarea">'+
							'	<li class="newBoxleftTitle">描述</li>'+
							'	<li class="newBoxinput">'+
							'	<textarea  name="attachmentfileDescribe" id="attachmentfileDescribe" ></textarea>'+
							'	</li>'+
							'</ul><div>';
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
		
						var d='<ul>'+
								'<li class="newBoxleftTitle"></li>'+
					    		'<li >'+
								'	<input  type="hidden"  name="price" id="price" value=""  /> '+
					    		'<input type="button"  value=""  onclick="$(this).parent().parent().parent().remove()"  class="delButtonBG"/>'+
				    			'</li>'+
				    		'</ul>';
					var fileType='*.3gp; *.mp4; *.avi; *.flv; *.swf; *.m2t; *.wmv;';
					$('#att_upload_1').render({ 
						buttonClass: 'custom',
						savePath: 'website\\www\\contentAtt\\',
						httpPath: 'website/www/contentAtt/convert/',
						basePath: '${basePath}',
						componentPath: '${basePath}component/js-fileupload/client/',
						type: 'any',
						fileTypeExts :fileType,
					
						callback: function(fileElement, file, saveFile, httpFile){
						var index = file.lastIndexOf('.');
							var ext = file.substring(index, file.length);
							
							//if(ext!=".swf"&&ext!=".mp4")
							if(ext!=".swf")
								ext=".flv";
							
							$('input[name="attachmentfilePath"]:last').val(httpFile+ext);
							$('input[name="attachmentfileName"]:last').val(file);
							$('input[name="attachmentsavePath"]:last').val(saveFile);
							$('#inputFileFullName').val(saveFile);
							//与上传附件保存路径在同一路径下
							$('#outputFilePath').val('convert\\');
							//与上传附件保存路径在同一路径下
							$('#cutOutputFilePath').val('convert\\cut\\');
							
							$('#convertForm').submit();
							
						},
					
						removeCompleted: false,
						customElements: attmodel.replace(d,''),
						<c:if test="${approveFlag eq '1'}">
						fixed: {top: '80%',left: '90%'},
						</c:if>
						<c:if test="${approveFlag ne '1'}">
						fixed: {top: '70%', left: '90%'},
						</c:if>
						queueID: 'att_upload_1-queue'
					});
					
					
					
	});	
				
</script>
<!--封面图片 -->
		    	
				<table style="width:680px;border:0px solid #ccc;" cellpadding="0" cellspacing="0" border="0" id="type2">    	
			    	<tr>
			    		<td  rowspan="2"  style="padding-top:10px;width:170px;">
							<input type="file" id="upload_cover" name="upload_cover"  title="${content.coverPath}" /></td>
			    		
			    		<td>
							<ul class="newlongInput">
								<li class="newBoxleftTitle">封面图片</li>
								<li class="newBoxinput">
									<input type="text"  name="coverName" id="coverName" value="${content.coverName}" style="width:50%;" /> 
									<input type="hidden"  name="coverPath" id="coverPath" value="${content.coverPath}"/>
									
								</li>
								<li>								</li>
							</ul>						</td>
							
					</tr>
					<tr>
					 
						<td>
							<ul class="newlongTextarea" style="margin:0 auto;width:400px;" >
								<li class="newBoxleftTitle">图片描述</li>
								<li class="newBoxinput" style="width:400px;">
								<textarea  name="coverDescribe" id="coverDescribe" style="width:277px;">${content.coverDescribe}</textarea>
								</li>
							</ul>			    		</td>
							
			    	</tr>
		  		</table>
				<table style="width:100%;min-width:800px;margin:0 auto;border:0px solid #ccc;" align="center" cellpadding="0" cellspacing="0" border="0"id="type3" >
				<tr><td><input type="button"  value="选择已有附件"  id="coverHad"  class="publichButton"/></td></tr>
				<tr><td id="att" ></td></tr>
				<tr>
					
			    	<td >
						<c:forEach items="${contentFile}" var="contentFile"   > 
						<div>
							<ul class="newlongInput">
								<li class="newBoxleftTitle">附件名称</li>
								<li class="newBoxinput">
									<input  id="attachmentfileName"  name="attachmentfileName" type="text"  value="${contentFile.fileName}"/>
					    			<input type="hidden" id="attachmentfilePath" name="attachmentfilePath"  value="${contentFile.filePath}" />		
					    			<input type="hidden" name="attachmentsavePath" id="attachmentsavePath" value="${contentFile.savePath}"/>
					    		</li>
							</ul>
							
							
							
							<ul>
								<li class="newBoxleftTitle"></li>
					    		<li >
									<input  type="hidden"  name="price" id="price" value="${contentFile.price}"  /> 
					    		<input type="button"  value=""  onclick="$(this).parent().parent().parent().remove()"  class="delButtonBG"/>
				    			</li>
				    		</ul>
							
							<ul class="newlongTextarea">
								<li class="newBoxleftTitle">描述</li>
								<li class="newBoxinput">
								<textarea  name="attachmentfileDescribe" id="attachmentfileDescribe" >${contentFile.detailed}</textarea>
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
						
							
	    		  </td>
						
		    	  </tr>
					
		    	</table>
				