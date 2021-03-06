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
		
		<title>会员管理</title>
	
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}style/css/backstage-v1.1.css" />
		<link type="text/css" rel="stylesheet" href="${basePath}component/artdialog/skins/black.css" /> <script type="text/javascript" src="${basePath}js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${basePath}js/index-v1.1/checkboxStyle.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/jquery.artDialog.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/artDialog.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/plugins/iframeTools.js"></script>
		<script type="text/javascript" src="${basePath}component/artdialog/artDialogDefaultConfig.js"></script>
		<script type="text/javascript" src="${basePath}component/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${basePath}js/validate.js"></script>
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
				
				$(".listBox > table tr :not(:first)").attr('class', 'contentTR2');
				$(".listBox > table tr :odd").attr('class', 'contentTR1');
				$(".listBox > table tr :not(:first)").live('mouseover', function(){
					var oldClass = $(this).attr('class');
					$(this).attr('oldClass',oldClass);
					$(this).attr('class','contentTR3');
				});
				
				$(".listBox > table tr :not(:first)").live('mouseout', function(){
					var oldClass = $(this).attr('oldClass');
					$(this).attr('class',oldClass);
				});
				
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
				
				//批量删除
				$("#deleteAll").click(function(){
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
							$("#memberForm").attr("action","${basePath}/action/member/deleteMember/"+ids+"/"+$("#currentPage").text());
							$("#memberForm").submit();
						});
					}
				});
				
				//查询
				$("#query").click(function(){
					$("#memberForm").attr("action","${basePath}/action/member/list/0");
					$("#memberForm").submit();
				});
			});
			
			//批量删除
			$("#deleteAll").click(function(){
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
						$("#memberForm").attr("action","${basePath}/action/member/deleteMember/"+ids+"/"+$("#currentPage").text());
						$("#memberForm").submit();
					});
				}
			});
		
			/**
			*分页查询方法
			*/
			function gotoPage(currentPage,totalPage){
				$("#memberForm").attr("action","");
				$("#memberForm").attr("action","${basePath}/action/member/list/"+currentPage);
				$("#memberForm").submit();
			}
			
			//编辑
			function edit_member(ids){
				$("#memberForm").attr("action","${basePath}/action/member/editpage/"+ids);
				$("#memberForm").submit();
			}
			
			function state_change(ids){
				$("#memberForm").attr("action","${basePath}/action/member/state/"+ids+"/"+$("#currentPage").text());
				$("#memberForm").submit();
			}
			
			function del_member(ids){
				art.dialog.confirm("您确定要删除吗？",function (){
					$("#memberForm").attr("action","${basePath}/action/member/deleteMember/"+ids+"/"+$("#currentPage").text());
					$("#memberForm").submit();
				});
			}
		</script>
	</head>
	  
	<body>
		<form method="get" id="memberForm">
	    	<div class="selectBox">
	    		<table width="100%" cellpadding="0" cellspacing="0" border="0">
	    			<tr>
		    			<td height="42" width="65"  align="center" valign="middle">
		    				<input type="button" name="deleteAll"  id="deleteAll" class="delButtonBG" title="删除"/>
						</td>
						<td>
							<ul>
	    						<li class="inputButtonBox">
	    							<div class="inputTitle">登录ID</div>
	    							<input type="text" class="inputText" name="loginid" id="loginid" value="${member.loginid}"/>
	    						</li>
	    						<li class="inputButtonBox">
	    							<div class="inputTitle">VIP截止日</div>
																		
									<input type="text" id="eTime" name="eTime" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd '})" style="width:159px;height:40px;line-height:40px;background-position:top right;"  value="<fmt:formatDate type='both' pattern='yyyy-MM-dd' value='${member.eTime}' />"/>
								</li>
								<li class="otherButtonBox">
									会员状态：
									  <select name="state">
										<option value=''>--选择--</option>
										<option value='0' 
											<c:choose>  
												<c:when test="${member.state eq '0'}">
													selected
												</c:when>
											</c:choose>>
											禁用
										</option>
										<option value='1' 
											<c:choose>  
												<c:when test="${member.state eq '1'}">
													selected
												</c:when>
											</c:choose>>
											正常
										</option>
									</select>
								</li>
								<li class="otherButtonBox">
									会员性质：
									  <select name="nature">
										<option value=''>--选择--</option>
										<option value='1' 
											<c:choose>  
												<c:when test="${member.nature eq '1'}">
													selected
												</c:when>
											</c:choose>>
											个人
										</option>
										<option value='2' 
											<c:choose>  
												<c:when test="${member.nature eq '2'}">
													selected
												</c:when>
											</c:choose>>
											企业
										</option>
                                        <option value='3' 
											<c:choose>  
												<c:when test="${member.nature eq '3'}">
													selected
												</c:when>
											</c:choose>>
											App
										</option>
									</select>
								</li>
								<li class="buttonBox">
									<input type="button" id="query" class="selectButtonBG"/>
								</li>
							</ul>
	    				</td>
	    			</tr>
	    		</table>
	    	</div>
	     	<%--列表查询条件 END--%>
	    
	    	<div class="listBox">
	    		<table width="100%" cellpadding="0" cellspacing="0" border="0" id="tablechange">
	    			<tr class="titleTR">
						<td align="center" valign="middle" width="30">&nbsp;</td>
	    				<td align="center" valign="middle">登陆ID</td>
						<td align="center" valign="middle">姓名</td>
						<td align="center" valign="middle">昵称</td>
						<td align="center" valign="middle">注册日期</td>
						<td align="center" valign="middle">VIP截止日</td>
						<td align="center" valign="middle">联系电话</td>
						<td align="center" valign="middle">会员性质</td>
	    				<td align="center" valign="middle">会员状态</td>
	    				<td align="center" valign="middle" width="120">操作</td>
	    			</tr>
					<c:forEach items="${pagingTools.dataSet}" var="member" varStatus="status">
          		  		<tr>  
				 			<td align="center" valign="middle">
								<input type="checkbox" id="checkbox" name="checkbox" value="${member.id}" class="styled"/>
							</td>
	    					<td align="center" valign="middle">${member.loginid}</td>
							<td align="center" valign="middle">${member.userName}</td>
							<td align="center" valign="middle">${member.nickname}</td>
	    					<td align="center" valign="middle">${member.creatdate}</td>
							<td align="center" valign="middle">${member.eTime}</td>
							<td align="center" valign="middle">${member.tel}</td>
							<td align="center" valign="middle">
					 			<c:choose>  
									<c:when test="${member.nature eq '1'}">个人</c:when>
									<c:when test="${member.nature eq '2'}">企业</c:when>
                                    <c:when test="${member.nature eq '3'}">App</c:when>
								</c:choose>
	    					</td>
							<td align="center" valign="middle">
					 			<c:choose>  
									<c:when test="${member.state eq '0'}">禁用</c:when>
									<c:when test="${member.state eq '1'}">正常</c:when>
								</c:choose>
	    					</td>
	    					<td align="center" valign="middle">
								<a onclick="state_change('${member.id}')">
									<input type="button" class="activationButtonBG" title="激活" />
								</a>
	    						<a onclick="edit_member('${member.id}')">
	    							<input type="button" class="editButtonBG" title="编辑" />
	    						</a>
	    						<a onclick="del_member('${member.id}')">
	    							<input type="button" class="delButtonBG" title="删除"/>
	    						</a>
	    					</td>
	    				</tr>
					</c:forEach>
	    		</table>
	    	</div>
		   
		   	<%--分页信息START--%>
	    	<div class="pageInfo">
				<ul class="pageInfolink">
					<li class="goNumber"><input type="button" name="gotoButton"  id="gotoButton"  class="goPageBG" title="跳转"/></li>
					<li class="inputNumber"><input type="text" class="pageInfoInputBox" id="toPage" /></li>
				
					<c:choose>
						<c:when test="${pagingTools.currentPage==pagingTools.totalPage || pagingTools.totalPage==0}">
							<li class="changePage" id="last"><div id="lastpage" class="lastPageBG" title="尾页"/></li>
				 		</c:when>
						<c:otherwise>
							<li class="changePage" id="last"><div id="lastpage" class="lastPageBG" title="尾页"/></li>
						</c:otherwise>
					</c:choose>
				
					<c:choose>
						<c:when test="${pagingTools.currentPage<pagingTools.totalPage}">
							<li class="changePage" id="next"><div id="next" class="nextPageBG" title="下一页"/></li>
				 		</c:when>
						<c:otherwise>
							<li class="changePage" id="next"><div id="next" class="nextPageBG" title="下一页"/></li>
						</c:otherwise>
					</c:choose>
				
					<c:choose>
						<c:when test="${pagingTools.currentPage eq 1}">
							<li class="changePage" id="back"><div id="back" class="frontPageBG" title="上一页"/></li>
							<li class="changePage" id="first"><div id="first" class="firstPageBG" title="首页"/></li>
				 		</c:when>
						<c:otherwise>
							<li class="changePage" id="back"><div id="back" class="frontPageBG" title="上一页"/></li>
							<li class="changePage" id="first"><div id="first" class="firstPageBG" title="首页"/></li>
				 		</c:otherwise>
					</c:choose>
					<li class="pageListInfo">
						本页共<span id="count">${pagingTools.count }</span>条&nbsp;
						第<span id="currentPage">${pagingTools.currentPage}</span>页&nbsp;
						共<span id="totalPage">${pagingTools.totalPage }</span>页
					</li>
				</ul>
			</div>
	    </form>
	</body>
</html>