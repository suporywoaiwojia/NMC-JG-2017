<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
    	<title>评论</title>
    
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<link type="text/css" rel="stylesheet" href="${basePath}wap/styles/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}wap/styles/css/wapStyle.css" />
		
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
				
				$('.gobackButton').click(function(){
					document.frames.frameElement.ownerDocument.parentWindow.history.back();
				});
				
				//发表评论
				$('.discussButton').click(function(){
					if(comments == '1'){
						publish();
					}else if(comments == '2'){
						var url = '${basePath}action/islogin';
						$.get(url, function(login){
							if(login == 1){
								publish();
							}else{
								alert('弹出登录界面。');
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
								alert('弹出登录界面。');
							}
						});
					}
				});
				
				//取消回复
				$('input[name="cancel"]').live('click', function(){
					var ul = this.parentElement.parentElement;
					$(ul).hide();
				});
			});
			
			function gotoPage(startIndex){
				var url = '${basePath}action/content/${contentId}/comment/'+startIndex;
				$.get(url, function(comments){
					if(comments && comments.dataSet && comments.dataSet.length > 0){
						var length = comments.dataSet.length;
						totalPage = comments.totalPage;
						currentPage = comments.currentPage;
						var page = '';
						/*
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
						*/
						
						if(totalPage > 1 && (currentPage == 1 || currentPage < totalPage)){
							page += '<li class="linkPage" onclick="nextPage()">更多评论...</li>';
						}
						
						$('.pageNumber').html(page);
						
						for(var i=0; i<length; i++){
							var comment = comments.dataSet[i];
							var userText = comment.reply=='' 
								? comment.name + '：发表于 '+comment.dateTime+ '<br />' + comment.text 
								: comment.name + '：发表于 '+comment.dateTime+ '<br />' + comment.text + '<br /><br />' + comment.reply;
							var html = '<div style="width: 100%;"><form id="replyForm" method="post">' +
										'<ul class="discussBox"><li class="discussUser">' + 
					                	'<img src="' + comment.image + '" width="65" height="65"'+
					                	' style="border-radius:5px;-webkit-border-radius:5px;-moz-border-radius:5px;" />' +
					                   	'</li><li class="contentBox1"><span style="float:right;cursor:pointer;" onclick="showReply(this)">回复</span>' + userText +
					                	'</li></ul><ul style="width:100%;overflow:auto;height:80px;position:relative;display: none;">' +
					            		'<li style="position:absolute;left:100px;top:10px;width:65%;right:10px;">' +
					            		'<textarea name="reply" style="width:100%;height:40px;"></textarea>' +
					            		'<input type="hidden" name="replyId" value="'+ comment.id +'">' +
					            		'</li><li style="position:absolute;left:100px;top:55px;text-align:right;width:65%;right:10px;">' +
					            	    '<input type="button" name="reply" value="提交回复" />' +
					            	    '<input type="button" name="cancel" value="取消" />' +
					            		'</li></ul></form></div>';
								
							$('#commentBox').append(html);
						}
					}
					parent.document.getElementById('discussFrame').style.height = (document.body.scrollHeight+10) + 'px';
				});
			}
			
			function nextPage(){
				currentStartIndex = currentStartIndex + 5;
				gotoPage(currentStartIndex);
			}
			
			function showReply(div){
				var form = div.parentElement.parentElement.parentElement;
				$('ul:last', form).show();
				parent.document.getElementById('discussFrame').style.height = (document.body.scrollHeight+10) + 'px';
			}
			
			function publish(){
				var length = $.trim($('textarea[name="comment"]').val()).length;
				if(length > wordcount){
					alert('输入内容过长，请将输入内容控制在'+wordcount+'字以内。');
				}else if(length == 0){
					alert('请输入评论内容。');
				}else{
					var url = '${basePath}action/content/${contentId}/publish/wapcomment';
					$('#publishForm').submit();
				}
			}
			
			function reply(btn){
				var form = btn.parentElement.parentElement.parentElement;
				var textarea = $('textarea[name="reply"]', form);
				var length = $.trim(textarea.text()).length;
				if(length > wordcount){
					alert('输入内容过长，请将输入内容控制在'+wordcount+'字以内。');
				}else if(length == 0){
					alert('请输入评论内容。');
				}else{
					var url = '${basePath}action/content/${contentId}/reply/wapcomment';
					
					form.action = url;
					form.submit();
				}
			}
		</script>
	</head>
  	
  	<body>
  		<form id="publishForm" action="${basePath}action/content/${contentId}/publish/wapcomment" method="post">
  			<div id="commentBox" style="width: 100%;">
			</div>
			<div style="width:100%;height:40px;">
				<ul class="pageNumber"></ul>
			</div>
            <textarea name="comment" id="comment" style="width:99%;height:50px;clear:both;margin:5px 0;"></textarea>
			<div class="bottomButtonBox">
	        	<ul class="bottomButton">
	            	<li class="gobackButton"></li>
	                <li class="discussButton" ></li>
	            </ul>
	        </div>
	    </form>
	</body>
</html>