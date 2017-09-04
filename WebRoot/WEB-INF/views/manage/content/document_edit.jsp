<script language="javascript">
	$(function(){
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
							var index = file.lastIndexOf('.');
							var ext = file.substring(index, file.length);
							$('#docDownPath').val(httpFile+ext);
							$('#documentName').val(file);
							$('#docSavePath').val(saveFile);
							
							var url = "${basePath}action/doc/getswf/"+saveFile.substring(saveFile.lastIndexOf("/")+1, saveFile.length)+ext;
							
								$.ajax({type:"GET", url:url,dataType:"text", success:function(datas) {
									
								}});
						
						},
						
						onDialogClose: function(){
							if($('#docDownPath').val() != ''){
								$('#docFile').html("");
								$('#doc_upload_1-queue').find('.uploadify-queue-item-any:not(:last)').remove();
							}
						},

						removeCompleted: false,
						
						
						fixed: {top: '70%', left: '70%'},
						
						queueID: 'doc_upload_1-queue'
					});
	});
</script>
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
					<li class="newBoxinput"><input  id="documentName"  name="documentName" type="text"  value="${content.documentName}"/><input type="hidden" id="docDownPath" name="docDownPath"  value="${content.docDownPath}" />	
						<input type="hidden" id="docSavePath" name="docSavePath"  value="${content.docSavePath}" />	
					</li>
				</ul>
				<ul class="newlongTextarea">
					<li class="newBoxleftTitle">剧本描述</li>
					<li class="newBoxinput"><textarea  name="documentDescribe" id="documentDescribe" >${content.documentDescribe}</textarea></li>
				</ul>
				</c:if>
					</div>
				
			</ul>
		</td>
		
	</tr>
	<tr><td><input type="button"  value="选择已有附件"  id="coverHad"  class="publichButton"/></td></tr>		
</table>