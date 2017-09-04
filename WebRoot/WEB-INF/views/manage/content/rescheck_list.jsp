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
		<script type="text/javascript"  src="${basePath}js/index-v1.1/checkboxStyle.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/jquery.artDialog.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/artDialog.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/plugins/iframeTools.js"></script>
		<script type="text/javascript" src="${basePath}js/validate.js"></script>
		<script type="text/javascript"  src="${basePath}js/tableColor.js" ></script>
		<script type="text/javascript" src="${basePath}component/artdialog/artDialogDefaultConfig.js"></script>
		<script type="text/javascript">
	
		$(function(){
			var _MESSAGE = '${message}'; 
				if (_MESSAGE != '') {
					if (_MESSAGE.indexOf('成功') != -1) {
						art.dialog({content: _MESSAGE, lock: false, time: 2});
					} else {
						art.dialog({content: _MESSAGE, time: 2});
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
				
					
					$("#contentForm").attr("action","${basePath}/action/rescheck/list/0/");
					$("#contentForm").submit();
				});
;
				$("#openTree").click(function(){
					
					window.open("${basePath}/action/rescheck/columnTree");
					
				});
			
			
			
		});
			/**
			*分页查询方法
			*/
			function gotoPage(currentPage,totalPage){
				var columnId=document.getElementById("column.id").value;
				var columnName=document.getElementById("column.name").value;
				var columnType=document.getElementById("column.columnType").value;
					
				$("#contentForm").attr("action","${basePath}/action/rescheck/list/"+currentPage);
		
				$("#contentForm").submit();
			}
			//编辑
			function edit_content(ids){
			
				$("#contentForm").attr("action","${basePath}/action/rescheck/docheck/"+ids);
				$("#contentForm").submit();
			}

			function del_content(ids){
				var columnId=document.getElementById("column.id").value;
				var columnName=document.getElementById("column.name").value;
				var columnType=document.getElementById("column.columnType").value;
				art.dialog.confirm("您确定要删除吗？",function (){
					$("#contentForm").attr("action","${basePath}/action/rescheck/deleteContent/"+ids+"/"+$("#currentPage").text()+"/"+columnId+"/"+encodeURI(encodeURI(columnName))+"/"+columnType);
					$("#contentForm").submit();
				});
			}
	</script>
	</head>
	  
	<body>
		<form action="" method="get" id="contentForm">
	    <div class="selectBox">
	    	<table width="100%" height="42" cellpadding="0" cellspacing="0" border="0" >
	    		<tr>
	    			<td align="center" valign="middle">
	    			<input type="hidden" name="column.name" id="column.name" value="${content.column.name}" />
						<input type="hidden" name="columb.id" id="column.id" value="${content.column.id}" />
						<input type="hidden" name="columb.columnType" id="column.columnType" value="${content.column.columnType}" />
					
						<ul>
	    					<li class="inputButtonBox">
	    						<div class="inputTitle">标题</div>
	    						<input type="text" name="title" id="title"   class="inputText"  value="${content.title}"/>
	    					</li>
							<li class="otherButtonBox">
								<input type="checkbox" name="putTop" id="putTop" value="1"   class="styled"  <c:if test="${content.putTop =='1'}">checked="checked" </c:if> /><label>置顶</label>
								<input type="checkbox" name="recommend" id="recommend" value="1"  class="styled"  <c:if test="${content.recommend =='1'}">checked="checked" </c:if>/><label>推荐</label>
							</li>
							
							<li class="otherButtonBox">
								内容状态：<select name="state" id="state">
										<option value="" >--请选择--</option>
										
										<option value="2" <c:if test="${content.state =='2'}">selected="selected" </c:if>>待审</option>
										<option value="3" <c:if test="${content.state =='3'}">selected="selected" </c:if>>初审</option>
										
										</select>
							</li>
							<li class="otherButtonBox">
								发布状态：<select name="oublishState" id="oublishState">
										<option value="">--请选择--</option>
										<option value="0" <c:if test="${content.oublishState =='0'}">selected="selected" </c:if>>未发布</option>
										<option value="1" <c:if test="${content.oublishState =='1'}">selected="selected" </c:if>>已发布</option>
										
										</select>
							</li>
						
							
							
							<li class="buttonBox"><input type="button" id="query"  class="selectButtonBG" value=""/></li>
						</ul>											
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
	    			<td   align="center" valign="middle" width="80">
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
				 	<td align="center" valign="middle" width="25" >
					<input type="checkbox" id="checkbox" name="checkbox" class="styled" value="${content.id}"/>
					</td>
	    			<td align="center" valign="middle" >
	    				${content.column.name}
	    			</td>
	    			<td  align="center" valign="middle">
	    				<font color="${content.titleColor}">${content.title}</font>
	    			</td>
					<td  align="center" valign="middle">
	    				${content.putTop}
	    			</td>
					<td  align="center" valign="middle">
	    				<c:if test="${content.recommend eq '1'}">是</c:if>
	    			</td>
					<td  align="center" valign="middle">
	    				
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
						<c:when test="${content.state eq '3'}">初审
				  		</c:when>
						<c:when test="${content.state eq '4'}">
						终审
				  		</c:when>
					</c:choose>
	    			</td>
					<td  align="center" valign="middle">
					
					 <c:choose>  
					 	
						<c:when test="${content.oublishState eq '0'}">
						未发布
				  		</c:when>
						<c:when test="${content.oublishState eq '1'}">
						已发布
				  		</c:when>
					</c:choose>
	    				<c:if test="${empty content.oublishState}">
						禁止发布
						</c:if>
	    			</td>
	    			<td align="center" valign="middle">
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
					<input type="button" name="gotoButton"  id="gotoButton"  class="goPageBG" value="" title="跳转"/>			
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
		
	    </form>
	    <script type="text/javascript">
			tableTrColor("tablechange","#d6d6d6","#dfdfdf","#FFFFFF");
		</script>	    
	</body>
</html>
