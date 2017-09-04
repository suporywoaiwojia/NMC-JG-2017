<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
    	<title>评论</title>
    
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		
		<link type="text/css" rel="stylesheet" href="${basePath}css/portal.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}css/global.css" />
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
				var url = '${basePath}action/content/${contentId}/comment/'+pageNumber+'/'+startIndex;
				
				$.get(url, function(comments){
					if(comments && comments.dataSet && comments.dataSet.length > 0){
						$('.userDiscuss').remove();
						var length = comments.dataSet.length;
						totalPage = comments.totalPage;
						currentPage = comments.currentPage;
						var page = '';
						var offset = pageNumber % 4;
						var style="";
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
									style="";
									if(i==currentPage)
										style='style=" background-color:#CCCCCC"';
									page += '<li class="numberPage " '+style+' onclick="goPage('+i+')">'+i+'</li>';
								}
								if(currentPage+offset < totalPage-1){
									page += '<li class="numberPage">...</li>';
								}
								page += '<li class="numberPage" onclick="goPage('+totalPage+')">'+totalPage+'</li>';
							}else if(currentPage == totalPage){
								page += '<li class="numberPage" onclick="goPage(1)">1</li><li class="numberPage">...</li>';
								for(var i=(totalPage-(pageNumber-1) + 1); i<=totalPage; i++){
								style="";
								if(i==currentPage)
										style='style=" background-color:#CCCCCC"';
									page += '<li class="numberPage" '+style+ '  onclick="goPage('+i+')">'+i+'</li>';
								}
							}
						}else if(totalPage > 1){
						
							for(var i=1; i<=totalPage; i++){
							style="";
								if(i==currentPage)
										style='style=" background-color:#CCCCCC"';
										
								page += '<li class="numberPage" '+style+ '   onclick="goPage('+i+')">'+i+'</li>';
							}
						}
						
						if(totalPage > 1){
							if(currentPage == 1){
								page += '<li class="linkPage" style=" color:#CCCCCC">Previous</li><li class="linkPage" onclick="nextPage()">Next</li>';
							}else if(currentPage == totalPage){
								page += '<li class="linkPage" onclick="prevPage()">Previous</li><li class="linkPage" style=" color:#CCCCCC">Next</li>';
							}else{
								page += '<li class="linkPage" onclick="prevPage()">Previous</li><li class="linkPage" onclick="nextPage()">Next</li>';
							}
						}
						
						$('.pageNumber').html(page);
						
						for(var i=0; i<length; i++){
						
							var comment = comments.dataSet[i];
							var userText = comment.reply=='' 
							
								?('<span style="color:#492bff;" >'+ comment.name+'</span>'+ '：published In '+comment.dateTime+ '<br />' + comment.text )
								: ('<span style="color:#492bff;" >'+comment.name+'</span>' + '：published In  '+comment.dateTime+ '<br />' + comment.text + '<br /><br />' + comment.reply);
							var html = '<ul class="userDiscuss">' + 
								'<li class="userImage"><img src="'+ comment.image +'" style="height: 68px;width: 68px;" /></li>' + 
								
								'<li class="userText">' + userText + '</li>' + 
								'<li class="userTime">'+ 
								'<div style="top:0px;right:15px;position: absolute;color:#492bff;font-weight:bold;cursor:pointer;" onclick="showReply(this)">Reply</div>' +
								'</li>' +
								'<li class="replyUser" style="display: none;">' +
								'<form id="replyForm" method="post">' +
								'<textarea name="reply" cols="" rows="" style="width:585px;height:50px;line-height:18px;border-radius:5px; -webkit-border-radius:5px; -moz-border-radius:5px;background:#c9f8f8;overflow:auto;overflow-x:hidden;overflow-Y:auto;"></textarea>' +
								'<input type="hidden" name="replyId" value="'+ comment.id +'">' +
								'<div style="width:600px;padding:5px 0;text-align:right;">Please enter '+wordcount+' texts' +
								'<input name="reply" type="button" value="Submit" style="cursor:pointer;border:0px solid #65b74c;background:none;width:50px;height:30px;color:#492bff;font-weight:bold;"/>&nbsp;' +
								'<input name="cancel" type="button" value="Cancel" style="cursor:pointer;border:0px solid #65b74c;background:none;width:50px;height:30px;color:#492bff;font-weight:bold;"/>' +
								'</div></form></li>' +
								'</ul>';
							$('.discussMiddle').append(html);
						}
					}
					parent.document.getElementById('comment').style.height = (document.body.scrollHeight+61) + 'px';
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
				
			}
			
			function publish(){
			//alert($('textarea[name="comment"]')[0].parentElement.outerHTML);
				var length = $.trim($('textarea[name="comment"]').val()).length;
				if(length > wordcount){
					alert('Input content is too long,Please control the input content within '+wordcount+' words。');
				}else if(length == 0){
					alert('Please enter comments');
				}else{
					var url = '${basePath}action/content/${contentId}/publish/comment';
					$('#publishForm').submit();
				}
				
			}
			
			function reply(btn){
				var form = btn.parentElement.parentElement;
				var textarea = $('textarea[name="reply"]', form);
				var length = $.trim($('textarea[name="reply"]', form).val()).length;
				
				if(length > wordcount){
					alert('Input content is too long,Please control the input content within'+wordcount+'words。');
				}else if(length == 0){
					alert('Please enter comments');
				}else{
					var url = '${basePath}action/content/${contentId}/reply/comment';
					
					form.action = url;
					form.submit();
				}
				
			}
		</script>
	</head>
  	
  	<body>
  		<div class="pageBox" style="width:744px">
	
	  		<form id="publishForm" action="${basePath}action/content/${contentId}/publish/comment" method="post">
		    	<div class="advertDiscuss">
					<div class="commenttop"></div>
					
				    <ul class="discussBox">
				    	<!--  li class="discussTop"></li   -->
				    	<li class="discussMiddle">
							<ul class="discussInputBox">
								<li>
									<!--<textarea name="comment" id="discussContentInfoBox"  style="margin-top:40px;margin-left:17px;width:100%;height:105px;line-height:18px;border-radius:5px; -webkit-border-radius:5px; -moz-border-radius:5px;overflow:auto;overflow-x:hidden;overflow-Y:auto;"></textarea> -->
									
									<textarea name="comment" id="discussContentInfoBox"  style="width:740px;height:105px;line-height:18px;"></textarea>
									<div style="width:740px;padding:5px 0;text-align:left;position:relative;">
										Please enter 150 texts
										<!--<input id="publish" type="button" value="    " style="border:0px solid #65b74c;background:none;width:50px;height:50px;cursor:pointer;color:#65b74c;font-weight:bold;position:absolute;top:-60px;right:7px;"/> -->
										<input id="publish" type="button" value="Submit"  style="float:right; width:80px; height:30px"/>
									</div>
									
								</li>
							</ul>
						</li>
				    	<!--  li class="discussBottom"></li    -->
					</ul>
					<div style="clear:both;width:740px;height:40px;margin-top:10px;">
						<ul class="pageNumber"></ul>
					</div>
			    </div>
		    </form>
	    </div>
	</body>
</html>