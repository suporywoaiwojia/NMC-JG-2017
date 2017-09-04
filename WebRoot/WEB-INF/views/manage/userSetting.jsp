<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>		     
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/backstage-v1.1.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}component/artdialog/skins/black.css" /> <script type="text/javascript" src="${basePath}js/jquery-1.8.3.min.js" ></script>
		<script type="text/javascript" src="${basePath}component/artdialog/jquery.artDialog.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/artDialog.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/plugins/iframeTools.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/artDialogDefaultConfig.js"></script>
		<script type="text/javascript">
			$(function(){
				$('.settingTool').dblclick(function(){
					if($('img', this).attr('title') != '清空'){
						var li = $('.modelSetting[setting="setting"]', parent.document)[0].parentElement;
						var url = $('img', this).attr('title');
						var image = $('img', this).attr('src');
						var id = $('img', this).attr('id');
						var name = $('.Titlebox', this).text();
						if($('img', li).length == 0){
							var imageHtml = '<img id="'+id+'" src="'+image+'" width="99" height="99" alt="'+url+'" />';
							$('.linkImagebox', li).html(imageHtml);
						}else{
							$('img', li).attr('alt', url);
							$('img', li).attr('id', id);
							$('img', li).attr('src', image);
						}
						$('.linkTitlebox', li).text(name);
					}else{
						var li = $('.modelSetting[setting="setting"]', parent.document)[0].parentElement;
						$('.linkImagebox', li).html('');
						$('.linkTitlebox', li).text('');
					}
					
					$('.modelSetting[setting="setting"]', parent.document).attr('setting', '');
					
					var ids = '';
					var lis = $('#linkImageBox > li[lang="settable"]', parent.document);
					for(var i=0; i<lis.length; i++){
						var image = $('img', lis[i]);
						if(image.length == 0){
							ids += ',';
						}else{
							ids += (','+image[0].id);
						}
					}
					ids = ids.substr(1);
					
					url = '${basePath}/action/manage/userpreferences/save/' + ids;
					$.get(url, function(success){
						if(success){
							//parent.dhxWins.window('desktop').close();
						
							window.parent.art.dialog.list['desktop'].close();
						}else{
							art.dialog({content: '偏好设置保存失败。', time: 2});
						}
					});
				});
			});
		</script>
	</head>	
	<body style="background: #24054b;">
		<c:forEach items="${functions}" var="function">
			<ul class="settingTool" title="双击完成偏好设置">
				<li class="imagebox">			
					<img id="${function.id}" src="${basePath}${function.icon}" width="99" height="99" title="${function.url}" />			
				</li>
				<li class="Titlebox">${function.resourceName}</li>
			</ul>
		</c:forEach>
		<ul class="settingTool" title="双击清空偏好设置">
			<li class="imagebox">			
				<img id="clear" src="${basePath}style/images-v1.1/desktopIcon/quxiao.png" width="99" height="99" title="清空" />			
			</li>
			<li class="Titlebox">置空</li>
		</ul>
	</body>
</html>