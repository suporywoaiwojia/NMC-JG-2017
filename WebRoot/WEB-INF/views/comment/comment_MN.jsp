<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
    	<title>评论</title>
		<style type="text/css">
		  @font-face {
			font-family: Menksoft2007;
			font-style:  normal;
			font-weight: normal;
			src:url("${basePath}mongol/Menksoft2007.woff") format("woff");
			src:
			}
		</style>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<link type="text/css" href="${basePath}css/mn/styles.css" rel="stylesheet" />
		<script type="text/javascript" src="${basePath}js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			var currentStartIndex = 0;
			var totalPage = 0;
			var currentPage = 0;
			var pageNumber = 3;
			var wordcount = 150;
			$(function(){
				var comments = '${comments}';
				if(comments != '3'){
					gotoPage(currentStartIndex);
				}else{
					$('.comment').hide();
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
					
						var length = comments.dataSet.length;
						totalPage = comments.totalPage;
						currentPage = comments.currentPage;
						var page = '';
						var page_n=7; //最少显示7个页数栏目
						
						var style="";
						if(totalPage > page_n){
							if(currentPage<5){
								for(var i=1; i<page_n-1; i++){
									style='class="pages" onclick="goPage('+i+')"';
									if(i==currentPage)
											style='class="current"';
									page+=' <a '+style+'><span>'+i+'</span></a>';
								}
								page+='<a class="pages">...</a><a class="pages" onclick="goPage('+totalPage+')">'+totalPage+'</a>'
							}else if(totalPage-currentPage>4){
								page+='<a class="pages" onclick="goPage(1)">1</a><a class="pages">...</a>';
								for(var i=currentPage-1;i<=currentPage+page_n-6;i++){
									style='class="pages" onclick="goPage('+i+')"';
									if(i==currentPage)
											style='class="current"';
									page+=' <a '+style+'><span>'+i+'</span></a>';
								}
								page += '<a class="pages">...</a>';
								page += '<a class="pages" onclick="goPage('+totalPage+')">'+totalPage+'</a>';
							}else if(totalPage-currentPage<=4){
								page+='<a class="pages" onclick="goPage(1)">1</a><a class="pages">...</a>';
								for(var i=totalPage-(page_n-3);i<=totalPage;i++){
									style='class="pages" onclick="goPage('+i+')"';
									if(i==currentPage)
											style='class="current"';
									page+=' <a '+style+'><span>'+i+'</span></a>';
								}
							}
							
							
						
						}else if(totalPage >= 1){
							for(var i=1; i<=totalPage; i++){
								style='class="pages" onclick="goPage('+i+')"';
								if(i==currentPage)
									style='class="current"';
								page+=' <a '+style+'><span>'+i+'</span></a>';
							}
						}
						var page_p="";
						if(totalPage >= 1){
							if(currentPage == 1){
								page_p= ' <a class="page-up" href="#"><img src="${basePath}images/mn/page-up-a.png" /></a><span id="pn"></span><a class="page-dn" onclick="nextPage()" href="#"><img src="${basePath}images/mn/page-dn-b.png" /></a>';
							}else if(currentPage == totalPage){
								page_p= '<a class="page-up" onclick="prevPage()" href="#"><img src="${basePath}images/mn/page-up-b.png" /></a><span id="pn"></span><a class="page-dn" href="#"><img src="${basePath}images/mn/page-dn-a.png" /></a>';
							}else{
								page_p= '<a class="page-up" onclick="prevPage()" href="#"><img src="${basePath}images/mn/page-up-b.png" /></a><span id="pn"></span><a class="page-dn" onclick="nextPage()" href="#"><img src="${basePath}images/mn/page-dn-b.png" /></a>';
							}
						}
						
						$('.page').html(page_p);
						$('#pn').html(page);
						var html="";
						for(var i=0; i<length; i++){
						
							var comment = comments.dataSet[i];
							
							html += '<div class="comment-list"> <div class="comment-list-l">' + 
								' <div class="comment-list-face"><img src="'+ comment.image +'" style="height:50px;width: 50px;" /></div>' + 
								
								' <div class="comment-list-name"> <span class="mongol_write">' + comment.name + '</span></div> </div>' + 
								'<div class="comment-content"><span class="mongol_write">'+comment.dateTime+'</br>'+comment.text+'</span></div> </div><div class="fg-s"></div>';
							
						}
						$('#content').html(html);
					}
					parent.document.getElementById('comment').style.height = (document.body.scrollHeight) + 'px';
					top.scroll(0,160);
				});
			}
			
			function goPage(page){
				currentStartIndex = (page-1) * pageNumber;
				gotoPage(currentStartIndex);
			}
			
			function nextPage(){
				currentStartIndex = currentStartIndex + pageNumber;
				gotoPage(currentStartIndex);
			}
			
			function prevPage(){
				currentStartIndex = currentStartIndex - pageNumber;
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
					currentStartIndex = (totalPage - 1) * pageNumber;
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
				var length = $.trim($('textarea[name="reply"]', form).val()).length;
				
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
	<form id="publishForm" action="${basePath}action/content/MN/${contentId}/publish/comment" method="post">
		<div class="comment">
		  <div class="input-box-comment">
		
			<textarea name="comment" id="discussContentInfoBox"  class="input-box-comment-s" autocomplete="off" ></textarea>
			<input class="input-box-comment-submit" name="" type="image" src="${basePath}images/mn/submit.png" value=" " />
		  </div>
		  <span id="content"></span>
		  
		  <div class="comment-page">
			<div class="page">
			  			  
			</div>
		  </div>
		</div>
	</form>
	</body>
</html>