<script language="javascript">
	$(document).ready(function(){
				//初始化xhEditor编辑器插件
				$('#content').xheditor({
					tools:'Cut,Copy,Paste,Pastetext,Blocktag,Fontface,FontSize,Bold,Italic,Underline,Strikethrough,FontColor,BackColor,SelectAll,Removeformat,Align,List,Outdent,Indent,Link,Unlink,Anchor,Img,Flash,Media,Hr,Table,Source,Preview,Print,Fullscreen',
					
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
					$("#content").append(_msg);
					$("#uploadList").append(_str);
				}
				//处理服务器返回到回调函数的字符串内容,格式是JSON的数据格式.
				function Substring(s){
					return s.substring(s.substring(0,s.lastIndexOf("/")).lastIndexOf("/"),s.length);
				}
			});

	$('#language').change(function(){ 
	var skin="o2007silver";
	
		if($('#language').val()=="MN"){
		skin="vista";
		}
		var sJSInit="$(\'#content\').xheditor({tools:\'Cut,Copy,Paste,Pastetext,Blocktag,Fontface,FontSize,Bold,Italic,Underline,Strikethrough,FontColor,BackColor,SelectAll,Removeformat,Align,List,Outdent,Indent,Link,Unlink,Anchor,Img,Flash,Media,Hr,Table,Source,Preview,Print,Fullscreen\',skin:\'"+skin+"\',upMultiple:true,upLinkUrl:\'${basePath}UploadFileServlet\',upLinkExt:\'zip,rar,txt\',upImgUrl: \'${basePath}UploadFileServlet\',upImgExt: \'jpg,jpeg,gif,bmp,png\',html5Upload:false});";
		$('#content').xheditor(false);

		
		$('link[id^=xheCSS]').remove();
		try{eval(sJSInit);}catch(e){};

		
	});
	
</script>
<table style="width:100%;min-width:800px; height:margin:0 auto;border:0px solid #ccc;" align="center" cellpadding="0" cellspacing="0" border="0"id="type1">	    	
	<tr>
		<td>
			<textarea id="content" name="content" rows="15" style="width: 800px; height:1000px; border: 1px; display: block;" wrap="physical">${Detailed}</textarea>
			    		
		</td>
	</tr>

</table>
<script language="javascript">
if("${content.language}"=="MN"){
		var sJSInit="$(\'#content\').xheditor({tools:\'Cut,Copy,Paste,Pastetext,Blocktag,Fontface,FontSize,Bold,Italic,Underline,Strikethrough,FontColor,BackColor,SelectAll,Removeformat,Align,List,Outdent,Indent,Link,Unlink,Anchor,Img,Flash,Media,Hr,Table,Source,Preview,Print,Fullscreen\',skin:\'vista\',upMultiple:true,upLinkUrl:\'${basePath}UploadFileServlet\',upLinkExt:\'zip,rar,txt\',upImgUrl: \'${basePath}UploadFileServlet\',upImgExt: \'jpg,jpeg,gif,bmp,png\',html5Upload:false});";
	$('#content').xheditor(false);
		$('link[id^=xheCSS]').remove();
		try{eval(sJSInit);}catch(e){};
		}
</script>