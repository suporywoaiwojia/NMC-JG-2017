<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
    	<title>评论</title>
    
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		
		<link type="text/css" rel="stylesheet" href="${basePath}css/global.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/backstage.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}css/portal.css" />
		
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
					$('.advertDiscuss').css('text-align', 'center');
					$('.advertDiscuss').css('vertical-align', 'middle');
					$('.advertDiscuss').html('该内容所属栏目已经禁用评论。');
				}
				
				$('#back').click(function(){
					history.back();
				});
			});
			
			function gotoPage(startIndex){
				var url = '${basePath}action/content/${contentId}/comment/10/'+startIndex;
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
								'<li class="userImage"><img src="'+ comment.image +'" style="height: 68px;width: 68px;" /></li>' + 
								'<li class="userTime">'+ 
								'<div style="top:10px;right:15px;position: absolute;color:#1071e5;cursor:pointer;" '+
								'onclick="delComment(\''+comment.id+'\')">删除</div>' +
								'</li>' +
								'<li class="userText">' + userText + '</li>' + 
								'</ul>';
							$('.discussMiddle').append(html);
						}
					} else if(startIndex == 0) {
						$('.advertDiscuss').css('text-align', 'center');
						$('.advertDiscuss').css('vertical-align', 'middle');
						$('.advertDiscuss').html('该内容暂时还没有评论。');
					}
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
			
			function delComment(commentId){
				var url = '${basePath}action/content/'+commentId+'/delete/comment';
				$.get(url, function(){
					gotoPage(0);
				});
			}
		</script>
	</head>
  	
  	<body>
  		<div class="selectBox">
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
	   			<tr>
	   				<td align="center" valign="middle" height="42"></td>
					<td width="65" align="center" valign="middle">
	   					<input type="button" name="back" id="back" class="buttonBG" value="返回"/>
	   				</td>
	   			</tr>
	   		</table>
		</div>
  		<div class="pageBox">
	    	<div class="advertDiscuss">
			    <ul class="discussBox">
			    	<li class="discussTop" style="height: 61px;line-height: 60px;">评论内容</li>
			    	<li class="discussMiddle"></li>
			    	<li class="discussBottom"></li>
				</ul>
				<div style="clear:both;float:left;width:740px;height:40px;">
					<ul class="pageNumber"></ul>
				</div>
		    </div>
	    </div>
	</body>
</html>