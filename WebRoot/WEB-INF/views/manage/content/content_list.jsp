<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" /> 
		<meta http-equiv="cache-control" content="no-cache" /> 
		<meta http-equiv="expires" content="0" />
		<title>无标题文档</title>
	
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/backstage-v1.1.css" />
		 <link type="text/css" rel="stylesheet" href="${basePath}component/artdialog/skins/black.css" /> <script type="text/javascript" src="${basePath}js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/jquery.artDialog.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/artDialog.js"></script>
		<script type="text/javascript"  src="${basePath}js/index-v1.1/checkboxStyle.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/plugins/iframeTools.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/artDialogDefaultConfig.js"></script>
		<script type="text/javascript" src="${basePath}js/validate.js"></script>
		
		
		<script type="text/javascript"  src="${basePath}js/tableColor.js" ></script>
		<script type="text/javascript">
		
		$(function(){
		
			var _MESSAGE = '${message}'; 
			
				if (_MESSAGE != '') {
					if (_MESSAGE.indexOf('成功') != -1) {
						art.dialog({content: _MESSAGE, lock: false, time: 2});
					} else {
						art.dialog({content: _MESSAGE, time: 10});
					}
				}
				//首页
				$("#first").click(function(){
					var currentPage=$("#currentPage").text();
					if(currentPage!=1){
						gotoPage(0,'${pagingTools.totalPage}');
					}
				});
				
				//下一页
				$("#back").click(function(){
					var currentPage=$("#currentPage").text();
					if(Number(currentPage)-1>0){
						gotoPage(Number(currentPage)-2,'${pagingTools.totalPage}');
					}
				});
				
				//上一页
				$("#next").click(function(){
					var currentPage=$("#currentPage").text();
					var totalPage=$("#totalPage").text();
					if(Number(currentPage)<Number(totalPage)){
						gotoPage(currentPage,'${pagingTools.totalPage}');
					}
				});
				
				//尾页
				$("#last").click(function(){
					var currentPage=$("#currentPage").text();
					var totalPage=$("#totalPage").text();
				
					gotoPage(totalPage-1,'${pagingTools.totalPage}');
				});
				
				/**
				*go按钮单击事件
				*/
				$("#gotoButton").click(function(){
					var currentPage=$("#toPage").val();
					if(validatePage(currentPage,'${pagingTools.totalPage}')){
						gotoPage(currentPage-1,'${pagingTools.totalPage}');
					}
				});		

				//查询
				$("#query").click(function(){
					var columnId=document.getElementById("column.id").value;
					var columnName=document.getElementById("column.name").value;
					var columnType=document.getElementById("column.columnType").value;
					
					$("#contentForm").attr("action","${basePath}/action/content/list/0/"+columnId+"/"+encodeURI(encodeURI(columnName))+"/"+columnType);
					$("#contentForm").submit();
				});
				$("#new").click(function(){
					var columnId=document.getElementById("column.id").value;
					
					$("#contentForm").attr("action","${basePath}/action/content/newpage/"+columnId);
					$("#contentForm").submit();
				});
				
			
			
			//批量删除
				$("#deleteAll").click(function(){
					var columnId=document.getElementById("column.id").value;
					var columnName=document.getElementById("column.name").value;
					var columnType=document.getElementById("column.columnType").value;
					var selectObj = $("input[name='checkbox']:checkbox:checked");
					if (selectObj.length == 0) {
						art.dialog({
							content: "请选择要删除的对象！",
							time: 2
						});
						
					}else{
						art.dialog.confirm("您确定要删除吗？",function (){
							var ids="";
							
								for (var i=0; i<selectObj.length; i++) {
									if(ids==""){
										ids=selectObj[i].value;
									}else{
										ids=ids+","+selectObj[i].value;
									}
								}
								
							$("#contentForm").attr("action","${basePath}/action/content/deleteContent/"+ids+"/"+$("#currentPage").text()+"/"+columnId+"/"+encodeURI(encodeURI(columnName))+"/"+columnType);
							//$("#contentTypeForm").attr("action","${basePath}/action/contenttype/delet/"+ids+"/0");
							$("#contentForm").submit();
						});
					}
				});
				//发布
				$("#publish").click(function(){
					var columnId=document.getElementById("column.id").value;
					var columnName=document.getElementById("column.name").value;
					var columnType=document.getElementById("column.columnType").value;
					var selectObj = $("input[name='checkbox']:checkbox:checked");
					var publishState="";
					if (selectObj.length == 0) {
						art.dialog({
							content: "请选择要发布的对象！",
							time: 2
						});
						
					}else{
						art.dialog.confirm("您确定要发布吗？",function (){
							var ids="";
							
								for (var i=0; i<selectObj.length; i++) {
									publishState=selectObj[i].attributes.getNamedItem("publishState").value;
									if(publishState==''){
										art.dialog({content:"您选中记录中存在禁止发布状态数据，发布失败", time: 2});
										return;
									}
										
									if(ids==""){
										ids=selectObj[i].value;
									}else{
										ids=ids+","+selectObj[i].value;
									}
								}
							
							
							//$.get(url, function(message){
							
								var columnId=document.getElementById("column.id").value;
								var columnName=document.getElementById("column.name").value;
								var columnType=document.getElementById("column.columnType").value;
								
								var pageNum=$("#currentPage").text()-1;
								var url = '${basePath}/action/content/publish/'+ids+'/'+columnId+'/'+pageNum;//$("#contentForm").attr("action","${basePath}/action/content/list/"+pageNum+"/"+columnId+"/"+encodeURI(encodeURI(columnName))+"/"+columnType);
								
								$("#contentForm").attr("action",url);
								$("#contentForm").submit();
								//alert(message);
								//art.dialog({content:message});
						//	});
							
						});
					}
				});
		});
			/**
			*分页查询方法
			*/
			function gotoPage(currentPage,totalPage){
				var columnId=document.getElementById("column.id").value;
				var columnName=document.getElementById("column.name").value;
				var columnType=document.getElementById("column.columnType").value;
					
				$("#contentForm").attr("action","${basePath}/action/content/list/"+currentPage+"/"+columnId+"/"+encodeURI(encodeURI(columnName))+"/"+columnType);
		
				$("#contentForm").submit();
			}
			//编辑
			function edit_content(ids){
				$("#contentForm").attr("action","${basePath}/action/content/editpage/"+ids);
				$("#contentForm").submit();
			}

			function del_content(ids){
					var columnId=document.getElementById("column.id").value;
					var columnName=document.getElementById("column.name").value;
					var columnType=document.getElementById("column.columnType").value;
				art.dialog.confirm("您确定要删除吗？",function (){
					$("#contentForm").attr("action","${basePath}/action/content/deleteContent/"+ids+"/"+$("#currentPage").text()+"/"+columnId+"/"+encodeURI(encodeURI(columnName))+"/"+columnType);
					$("#contentForm").submit();
				});
			}
			
			function edit_comment(contentId){
				window.location.href = '${basePath}action/manage/content/comment/'+contentId;
			}
	</script>
	</head>
	  
	<body topmargin="0" leftmargin="0" style="overflow:hidden;">
		<form action="" method="get" id="contentForm">
	
		
	    <div class="selectBox">
	    	<table width="100%" height="42" cellpadding="0" cellspacing="0" border="0" >
	    		<tr>
	    			<td width="111"  align="center" valign="middle">
					<c:if test="${content.column.id ne '1'}">
	    				<input type="button" name="new"  id="new"  class="newButtonBG" title="新建"/>
	    				<input type="button" name="deleteAll"  id="deleteAll"  class="delButtonBG" title="删除"/>
						
	    				<input type="button" name="publish"  id="publish"  class="publishButtonBG" title="发布"/>
					</c:if>
	    			</td>
	    			
	    			<td align="center" valign="middle">
	    				<input type="hidden" name="column.name" id="column.name" value="${content.column.name}" />
						<input type="hidden" name="columb.id" id="column.id" value="${content.column.id}" />
						<input type="hidden" name="columb.columnType" id="column.columnType" value="${content.column.columnType}" />
						
						<ul>
						<li class="inputButtonBox">
	    						<div class="inputTitle">标题</div>
						<input type="text" name="title" id="title" value="${content.title}" class="inputText"/>
						</li>
						<li class="otherButtonBox">
						<input type="checkbox" name="putTop" id="putTop" value="1"   class="styled" <c:if test="${content.putTop =='1'}">checked="checked" </c:if> /><label>置顶</label>
						<input type="checkbox" name="recommend" id="recommend" value="1"  class="styled" <c:if test="${content.recommend =='1'}">checked="checked" </c:if>/><label>推荐</label>
						</li>
						<li class="otherButtonBox">
						内容状态：<select name="state" id="state">
						<option value="" >--请选择--</option>
						<option value="1" <c:if test="${content.state =='1'}">selected="selected" </c:if>>草稿</option>
						<option value="2" <c:if test="${content.state =='2'}">selected="selected" </c:if>>待审</option>
						<option value="3" <c:if test="${content.state =='3'}">selected="selected" </c:if>>初审</option>
						<option value="4" <c:if test="${content.state =='4'}">selected="selected" </c:if>>终审</option>
						<option value="0" <c:if test="${content.state =='0'}">selected="selected" </c:if>>退回</option>
						</select>
						</li>
						<li class="otherButtonBox">
						发布状态：<select name="oublishState" id="oublishState">
						<option value="">--请选择--</option>
						<option value="0" <c:if test="${content.oublishState =='0'}">selected="selected" </c:if>>未发布</option>
						<option value="1" <c:if test="${content.oublishState =='1'}">selected="selected" </c:if>>已发布</option>
						
						</select>
						</li>
						<li class="otherButtonBox">
						语言类型：<select name="language" id="language">
									<option value="--"  >全部</option>
									<option value=""  <c:if test="${content.language ==''}">selected="selected" </c:if>>中文</option>
									<option value="MN" <c:if test="${content.language =='MN'}">selected="selected" </c:if>>蒙古语</option>
					    			<option value="EN" <c:if test="${content.language =='EN'}">selected="selected" </c:if>>英语</option>
					    			<option value="JPN" <c:if test="${content.language =='JPN'}">selected="selected" </c:if>>日语</option>
					    			<option value="KOR" <c:if test="${content.language =='KOR'}">selected="selected" </c:if>>韩语</option>
									<option value="DEZ" <c:if test="${content.language =='DEZ'}">selected="selected" </c:if>>德语</option>
									<option value="MCU" <c:if test="${content.language =='MCU'}">selected="selected" </c:if>>法语</option>
									<option value="OTH" <c:if test="${content.language =='OTH'}">selected="selected" </c:if>>其他</option>
						
						</select>
						</li>
						<li class="buttonBox"><input type="button" id="query"  class="selectButtonBG" title="查询"/></li></ul>
	    			</td>					
	    		</tr>
	    	</table>
	    </div>
	     <%--列表查询条件 END--%>
	    
	    <div class="listBox">
	    	<table width="100%" cellpadding="0" cellspacing="0" border="0"  id="tablechange">
	    		<tr class="titleTR">
					<td align="center" valign="middle" >
	    				&nbsp;&nbsp;
	    			</td>
					<td align="center" valign="middle" >
	    				ID
	    			</td>
	    			<td align="center" valign="middle" >
	    				所属栏目
	    			</td>
					<td align="center" valign="middle" >
	    				标题
	    			</td>
					<td align="center" valign="middle" >
	    				置顶
	    			</td>
	    			<td  align="center" valign="middle">
	    				推荐</td>
	    			<td  align="center" valign="middle">
	    				发布人</td>
					<td  align="center" valign="middle">
	    				发布时间</td>
					<td  align="center" valign="middle">
	    				内容状态</td>
					<td  align="center" valign="middle">
	    				发布状态</td>
	    			<td   align="center" valign="middle" width="120">
	    				操作
	    			</td>
	    		</tr>
				<c:forEach items="${pagingTools.dataSet}" var="content"  varStatus="status">
				 <c:choose>  
				<c:when test="${status.index % 2 == 0}">  
	    			<tr class="contentTR1">
				</c:when>  
       			<c:otherwise>  
          		  <tr  class="contentTR2">  
     		   </c:otherwise>  
				 </c:choose>  
				 	<td align="center" valign="middle"  width="25">
					<input type="checkbox" id="checkbox" name="checkbox" class="styled" value="${content.id}" publishState="${content.oublishState}" />
					
					</td>
					<td align="center" valign="middle" >
	    				${content.id}
	    			</td>
	    			<td align="center" valign="middle" >
	    				${content.column.name}
	    			</td>
	    			<td  align="center" valign="middle">
	    				<font color="${content.titleColor}">${content.title}</font>
	    			</td>
					<td  align="center" valign="middle">
	    				<c:if test="${content.putTop ne '9'}">${content.putTop}</c:if>
	    			</td>
					<td  align="center" valign="middle">
	    				<c:if test="${content.recommend eq '1'}">是</c:if>
	    			</td>
					<td  align="center" valign="middle">
	    				${content.user.userName}
						${content.member.loginid}
	    			</td>
					<td  align="center" valign="middle">
	    				
						<fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${content.publishDate}" />
	    			</td>
					<td  align="center" valign="middle">
	    				 <c:choose>  
					 	
						<c:when test="${content.state eq '0'}">
						退回
				  		</c:when>
						<c:when test="${content.state eq '1'}">
						草稿
				  		</c:when>
						<c:when test="${content.state eq '2'}">
						待审
				  		</c:when>
						<c:when test="${content.state eq '3'}">
						初审
				  		</c:when>
						<c:when test="${content.state eq '4'}">
						终审
				  		</c:when>
					</c:choose>
	    			</td>
					<td  align="center" valign="middle">
					
					 <c:choose>  
					 	
						<c:when test="${content.oublishState eq '0'}">
						<font color="#FF0000">未发布</font>
				  		</c:when>
						<c:when test="${content.oublishState eq '1'}">
						已发布
				  		</c:when>
						<c:when test="${empty content.oublishState }">
						<font color="#FF0000">禁止发布</font>
				  		</c:when>
					</c:choose>
	    				
	    			</td>
	    			<td   align="center" valign="middle">
					
						<a onclick="edit_comment('${content.id}')"><input type="button"  class="discussButtonBG" title="评论"/></a>
						<a onclick="edit_content('${content.id}')"><input type="button"   class="editButtonBG"  title="编辑" /></a>
	    				<a onclick="del_content('${content.id}')"><input type="button"  class="delButtonBG" title="删除"/></a>
						
	    			</td>
	    			
	    		</tr>
				</c:forEach>
	    		
	    	</table>
	    </div>
		   <%--分页信息START--%>
	    <div class="pageInfo">
			<ul class="pageInfolink">
				<li class="goNumber">
					<input type="button" name="gotoButton"  id="gotoButton"  class="goPageBG" title="跳转"/>			
				</li>
				<li class="inputNumber"><input type="text" class="pageInfoInputBox" id="toPage" /></li>
				
				<c:choose>
				<c:when test="${pagingTools.currentPage==pagingTools.totalPage || pagingTools.totalPage==0}">
				<li class="changePage" id="last">				
					<div id="lastpage"  class="lastPageBG"  title="尾页"/></li>
				</c:when>
				<c:otherwise>
					<li class="changePage" id="last">				
						<div  id="lastpage"  class="lastPageBG" title="尾页"/>
					</li>
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${pagingTools.currentPage<pagingTools.totalPage}">
				<li class="changePage" id="next"><div id="next"  class="nextPageBG" title="下一页"/></li>
				 </c:when>
				<c:otherwise>
				<li class="changePage" id="next"><div  id="next"  class="nextPageBG"  title="下一页"/></li>
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${pagingTools.currentPage eq 1}">
				<li class="changePage" id="back"><div id="back"  class="frontPageBG"  title="上一页"/></li>
				<li class="changePage" id="first"><div id="first"  class="firstPageBG" title="首页"/></li>
				 </c:when>
				<c:otherwise>
				<li class="changePage" id="back"><div id="back"  class="frontPageBG"   title="上一页"/></li>
				<li class="changePage" id="first"><div id="first"  class="firstPageBG" title="首页"/></li>
				 </c:otherwise>
				</c:choose>
				<li class="pageListInfo" style="display:none;">本页共<span id="count">${pagingTools.count }</span>条&nbsp;第<span id="currentPage">${pagingTools.currentPage}</span>页&nbsp;共<span id="totalPage">${pagingTools.totalPage }</span>页</li>
			</ul>
		</div>
	    
	  
	   <script type="text/javascript">
			tableTrColor("tablechange","#d6d6d6","#dfdfdf","#FFFFFF");
		</script>	 
		</form>   
	</body>
</html>
