<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
   		<title>草原文化创意资源平台-草原映像</title>
   		<link type="text/css" rel="stylesheet" href="${basePath}css/global.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}css/portal.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}css/discussNew.css" />
		
		
		<script type="text/javascript" src="${basePath}js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			var currentStartIndex = 0;
			var totalPage = 0;
			var currentPage = 0;
			var pageNumber = 5;
			var wordcount = 150;
			$(function(){
				var comments = '${comments}';
				if(comments != '3'){
					gotoPage(currentStartIndex);
				}else{
					$('.discussBox').hide();
				}
				
				//发表评论
				$('#publish').click(function(){
					if(comments == '1'){
						publish();
					}else if(comments == '2'){
						var url = '${basePath}action/islogin';
						$.get(url, function(login){
							if(login == 1){
								publish();
							}else{
								parent.document.getElementById('link').click();
							}
						});
					}
				});
				
				//回复评论
				$('input[name="reply"]').live('click', function(){
					var btn = this;
					if(comments == '1'){
						reply(btn);
					}else if(comments == '2'){
						var url = '${basePath}action/islogin';
						$.get(url, function(login){
							if(login == 1){
								reply(btn);
							}else{
								parent.document.getElementById('link').click();
							}
						});
					}
				});
				
				//取消回复
				$('input[name="cancel"]').live('click', function(){
					var ul = this.parentElement.parentElement.parentElement.parentElement;
					$('.replyUser', ul).hide();
				});
			});
			
			function gotoPage(startIndex){
				var url = '${basePath}action/content/${contentId}/comment/'+startIndex;
				$.get(url, function(comments){
					if(comments && comments.dataSet && comments.dataSet.length > 0){
						$('.userDiscuss').remove();
						var length = comments.dataSet.length;
						totalPage = comments.totalPage;
						currentPage = comments.currentPage;
						var page = '';
						var offset = pageNumber % 4;
						if(totalPage > pageNumber){
							if(currentPage < pageNumber){
								for(var i=1; i<pageNumber; i++){
									page += '<li class="numberPage" onclick="goPage('+i+')">'+i+'</li>';
								}
								page += '<li class="numberPage">...</li><li class="numberPage" onclick="goPage('+totalPage+')">'+totalPage+'</li>';
							}else if(currentPage <= (totalPage-offset)){
								page += '<li class="numberPage" onclick="goPage(1)">1</li><li class="numberPage">...</li>';
								var leftOffset = offset;
								var rightOffset = offset;
								if(currentPage+offset == totalPage){
									leftOffset = offset + 1;
								}
								for(var i=(currentPage-leftOffset); i<(currentPage+rightOffset); i++){
									page += '<li class="numberPage" onclick="goPage('+i+')">'+i+'</li>';
								}
								if(currentPage+offset < totalPage-1){
									page += '<li class="numberPage">...</li>';
								}
								page += '<li class="numberPage" onclick="goPage('+totalPage+')">'+totalPage+'</li>';
							}else if(currentPage == totalPage){
								page += '<li class="numberPage" onclick="goPage(1)">1</li><li class="numberPage">...</li>';
								for(var i=(totalPage-(pageNumber-1) + 1); i<=totalPage; i++){
									page += '<li class="numberPage" onclick="goPage('+i+')">'+i+'</li>';
								}
							}
						}else if(totalPage > 1){
							for(var i=1; i<=totalPage; i++){
								page += '<li class="numberPage" onclick="goPage('+i+')">'+i+'</li>';
							}
						}
						
						if(totalPage > 1){
							if(currentPage == 1){
								page += '<li class="linkPage">上一页</li><li class="linkPage" onclick="nextPage()">下一页</li>';
							}else if(currentPage == totalPage){
								page += '<li class="linkPage" onclick="prevPage()">上一页</li><li class="linkPage">下一页</li>';
							}else{
								page += '<li class="linkPage" onclick="prevPage()">上一页</li><li class="linkPage" onclick="nextPage()">下一页</li>';
							}
						}
						
						$('.pageNumber').html(page);
						
						for(var i=0; i<length; i++){
							var comment = comments.dataSet[i];
							var userText = comment.reply=='' 
								? comment.name + '：发表于 '+comment.dateTime+ '<br />' + comment.text 
								: comment.name + '：发表于 '+comment.dateTime+ '<br />' + comment.text + '<br /><br />' + comment.reply;
							var html = '<ul class="userDiscuss">' + 
								'<li class="userImage"><img src="'+ comment.image +'" /></li>' + 
								'<li class="userTime">'+ 
								'<div style="top:10px;right:15px;position: absolute;color:#1071e5;cursor:pointer;" onclick="showReply(this)">回复</div>' +
								'</li>' +
								'<li class="userText">' + userText + '</li>' + 
								'<li class="replyUser" style="display: none;">' +
								'<form id="replyForm" method="post">' +
								'<textarea name="reply" cols="" rows="" style="width:500px;height:50px;line-height:18px;background:#ebebeb;line-height:18px;border-radius:5px;-webkit-border-radius:5px;-moz-border-radius:5px;"></textarea>' +
								'<input type="hidden" name="replyId" value="'+ comment.id +'" />' +
								'<div style="width:600px;padding:5px 0;text-align:right;">请输入'+wordcount+'字以内的回复' +
								'<input name="reply" type="button" value="提交" style="border:1px solid #f2e40c;width:100px;height:30px;color:#f2e40c"/>' +
								'<input name="cancel" type="button" value="取消" style="border:1px solid #f2e40c;width:100px;height:30px;color:#f2e40c"/>' +
								'</div></form></li>' +
								'</ul>';
							$('.discussMiddle').append(html);
						}
					}
					parent.document.getElementById('comment').style.height = (document.body.scrollHeight+1) + 'px';
					top.scroll(0,160);
				});
			}
			
			function goPage(page){
				currentStartIndex = (page-1) * 5;
				gotoPage(currentStartIndex);
			}
			
			function nextPage(){
				currentStartIndex = currentStartIndex + 5;
				gotoPage(currentStartIndex);
			}
			
			function prevPage(){
				currentStartIndex = currentStartIndex - 5;
				if(currentStartIndex < 0){
					currentStartIndex = 0;
				}
				gotoPage(currentStartIndex);
			}
			
			function firstPage(){
				currentStartIndex = 0;
				gotoPage(currentStartIndex);
			}
			
			function lastPage(){
				if(totalPage > 1){
					currentStartIndex = (totalPage - 1) * 5;
				}else{
					currentStartIndex = 0;
				}
				
				gotoPage(currentStartIndex);
			}
			
			function showReply(div){
				var ul = div.parentElement.parentElement;
				var li = ul.parentElement;
				
				$('.replyUser', li).hide();
				$('.replyUser', ul).show();
				parent.document.getElementById('comment').style.height = (document.body.scrollHeight+1) + 'px';
			}
			
			function publish(){
				var length = $.trim($('textarea[name="comment"]').val()).length;
				if(length > wordcount){
					alert('输入内容过长，请将输入内容控制在'+wordcount+'字以内。');
				}else if(length == 0){
					alert('请输入评论内容。');
				}else{
					var url = '${basePath}action/content/${contentId}/publish/comment';
					$('#publishForm').submit();
				}
			}
			
			function reply(btn){
				var form = btn.parentElement.parentElement;
				var textarea = $('textarea[name="reply"]', form);
				var length = $.trim(textarea.text()).length;
				if(length > wordcount){
					alert('输入内容过长，请将输入内容控制在'+wordcount+'字以内。');
				}else if(length == 0){
					alert('请输入评论内容。');
				}else{
					var url = '${basePath}action/content/${contentId}/reply/comment';
					
					form.action = url;
					form.submit();
				}
			}
		</script>
    </head>
  
	<body>
		<form id="publishForm" action="${basePath}action/content/${contentId}/publish/discusscomment" method="post">
			<div class="advertDiscuss">
				<ul class="discussBox">
					<li class="discussTop">
						<img src="${basePath}images/discussTop.png" width="600" height="85"/>
					</li>
					<li class="discussMiddle">
						<ul class="discussInputBox">				
							<li>
								<textarea name="comment" id="discussContentInfoBox"></textarea>						
								<div style="width:590px;padding:5px 0;text-align:right;font-size:16px;color:#aaa;">
									请输入150字以内的回复
									<input id="publish" type="button" value="" style="border:0px;width:83px;height:31px;background:url(${basePath}images/protalPicture-1.png) -5px -1088px no-repeat;"/>
								</div>						
							</li>
						</ul>
						<ul class="userDiscuss">
							<li class="userImage"><img src="" /></li> 
							<li class="userTime"> 
							<div style="top:10px;right:15px;position: absolute;color:#1071e5;cursor:pointer;" onclick="showReply(this)">回复</div></li>
							<li class="userText">44444</li> 
							<li class="replyUser" style="display:none;">
							<form id="replyForm" method="post">
							<textarea name="reply" cols="" rows="" style="width:560px;height:50px;line-height:18px;background:#ebebeb;line-height:18px;border-radius:5px;-webkit-border-radius:5px;-moz-border-radius:5px;"></textarea>
							<input type="hidden" name="replyId" value="" />
							<div style="width:560px;padding:5px 0;text-align:right;">请输入150字以内的回复
							<input name="reply" type="button" value=" 提 交 " style="border:1px solid #f2e40c;width:80px;height:30px;background:#f2e40c;color:#666;font-size:16px;font-family:'微软雅黑';border-radius:5px;-webkit-border-radius:5px;-moz-border-radius:5px;"/>
							<input name="cancel" type="button" value=" 取 消 " style="border:1px solid #f2e40c;width:80px;height:30px;background:#f2e40c;color:#666;font-size:16px;font-family:'微软雅黑';border-radius:5px;-webkit-border-radius:5px;-moz-border-radius:5px;"/>
							</div></form></li>
						</ul>
					</li>
					<li class="discussBottom"></li>				
				</ul>
				<div style="clear:both;float:left;width:400px;height:40px;">
					<ul class="pageNumber"></ul>
				</div>
			</div>
		</form>
	</body>
</html>