<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script language="javascript">
	var attmodel='<ul class="newlongInput">'+
								'<li class="newBoxleftTitle">附件名称</li>'+
								'<li class="newBoxinput">'+
									'<input  id="attachmentfileName"  name="attachmentfileName" type="text"  value=""/>'+
					    			'<input type="hidden" id="attachmentfilePath" name="attachmentfilePath"  value="" />	'+	
									'<input type="hidden" name="attachmentsavePath" id="attachmentsavePath" value=""/>'+
					    		'</li>'+
							'</ul>'+
							
							
							'<ul  class="newShortBoxInput" >'+
							'	<li class="newBoxleftTitle">价格</li>'+
					    	'	<li  class="newShortBoxinput">'+
							'		<input  type="text"  name="attachmentPrice" id="attachmentPrice" value=""  />'+
					    	
				    		'	</li>'+
				    		'</ul>'+ 
							'<ul >'+
					    	'	<li>'+
					    	'	<input type="button"  value=""  class="delButtonBG"  onclick="$(this).parent().parent().parent().parent().remove()" />'+
				    		'	</li>'+
				    		'</ul> '+
							
							'</label>'+
							'<ul class="newlongTextarea">'+
							'	<li class="newBoxleftTitle">描述</li>'+
							'	<li class="newBoxinput">'+
							'	<textarea  name="attachmentfileDescribe" id="attachmentfileDescribe" ></textarea>'+
							'	</li>'+
							'</ul>';
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
	
			var d='<input type="button"  value=""  class="delButtonBG"  onclick="$(this).parent().parent().parent().parent().remove()" />'	;
					var fileType="";
					$('#att_upload_1').render({ 
						buttonClass: 'custom',
						savePath: 'website\\www\\contentAtt\\',
						httpPath: 'website/www/contentAtt/',
						basePath: '${basePath}',
						componentPath: '${basePath}component/js-fileupload/client/',
						type: 'any',
						fileTypeExts :fileType,
					
						callback: function(fileElement, file, saveFile, httpFile){
						var ext =/\.[^\.]+/.exec(file);
							$('input[name="attachmentfilePath"]:last').val(httpFile+ext);
						
							$('input[name="attachmentfileName"]:last').val(file);
							$('input[name="attachmentsavePath"]:last').val(saveFile);
							$('#inputFileFullName').val(saveFile);
							$('#outputFilePath').val('website\\www\\contentAtt\\convert\\');
							
						},
					
						removeCompleted: false,
						customElements:  attmodel.replace(d,''),
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
				<table style="width:100%;min-width:800px;margin:0 auto;border:0px solid #ccc;" align="center" cellpadding="0" cellspacing="0" border="0"id="type3" >
				<tr><td><input type="button"  value="选择已有附件"  id="coverHad"  class="publichButton"/></td></tr>
				<tr><td id="att" ></td></tr>
				<tr>
			    	<td>
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
							
							
							<label >
							<ul  class="newShortBoxInput" >
								<li class="newBoxleftTitle">价格</li>
					    		<li  class="newShortBoxinput">
									<input  type="text"  name="attachmentPrice" id="attachmentPrice" value="${contentFile.price}"  />
					    	
				    			</li>
				    		</ul> 
							<ul >
					    		<li>
					    		<input type="button"  value=""  class="delButtonBG"  onclick="$(this).parent().parent().parent().parent().remove()" />
				    			</li>
				    		</ul> 
							</label> 
							<ul class="newlongTextarea">
								<li class="newBoxleftTitle">描述</li>
								<li class="newBoxinput">
								<textarea  name="attachmentfileDescribe" id="attachmentfileDescribe" >${contentFile.detailed}</textarea>
								</li>
							</ul>	
							</div>
				    		</c:forEach>
							<script  type="text/javascript">
							function downLoad_Check(c){
							
								if($(c).attr('checked')==undefined){
									$(c).next().val("0");
								}else{
									$(c).next().val("1");
								}
							}
							</script>
								<li style="float:left;width:100px;">
								<input type="file" id="att_upload_1" name="file_upload" />
					    		</li>
								<li style="float:left;padding-top:8px;">
									<div id="att_upload_1-queue" style="margin: 35px 0;width: 100%;height: auto;"></div>
								</li>
						
							
			    		</td>
						
			    	</tr>
					
		    	</table>