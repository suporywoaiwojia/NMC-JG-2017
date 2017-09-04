<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" /> 
		<meta http-equiv="cache-control" content="no-cache" /> 
		<meta http-equiv="expires" content="0" />
		
		<title>项目信息</title>
		
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
		<link rel="stylesheet" type="text/css" href="${basePath}component/ssi-up/css/style.css">
		<link rel="stylesheet" href="${basePath}component/ssi-up/css/ssi-uploader.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/styles-left.css">
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		<script type="text/javascript">
		$(function(){
			if('${pro.batch}'!='')
				$('#batch').combobox('select','${pro.batch}');
			if('${pro.column.id}'!='')
				$('#column').combobox('select','${pro.column.id}');
			if('${pro.level.id}'!='')
				$('#level').combobox('select','${pro.level.id}');
			if('${pro.approve_id}'!='')
				$('#approve_id').combobox('select','${pro.approve_id}');
			
		});
		
		</script>

	
	</head>
	  
	<body>
		<div class="easyui-panel"   style="width:100%;padding:15px">
				  <div style="padding:0 0 10px;">
				  </div> 
				   <form id="project" action=""  method="post">
				  <div style="margin:10px 0">
				 
						<input type="hidden" id="id" value="${pro.id}" name="id"/>
						<input type="hidden" name="l_type" value="CN" />
						<input type="hidden" name="creatdate" value="<fmt:formatDate value='${pro.creatdate}' pattern='yyyy-MM-dd'/>" />
						<input type="hidden" name="language" value="${pro.language}" />
						<input type="hidden" name="parent_id" value="${pro.id}" />
						
					<table class="data">
					
						<c:if test="${columns.approve =='1'}">
						<tr>
							<td>审核人员:</td>
							<td>
								<select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="approve_id" id="approve_id" data-options="editable:false">
									<c:forEach items="${approveUser}" var="approveUser" >
									<option value="${approveUser.loginId}">${approveUser.userName}</option>

									 </c:forEach>
								</select>
							</td>
							<td></td>
							<td></td>
						</tr>
						</c:if>
					  <tr>
						<td>项目名称:</td>
						<td><input class="easyui-textbox" style="width:300px;height:30px" name="name" id="name" value="${pro.name}" data-options="required:true,validType:'length[0,50]',missingMessage:'项目名称不能为空',invalidMessage:'长度不能超出50个字符'"></td>
						<td style="padding-left:40px;">级别批次:</td>
						<td>
						  &nbsp;第&nbsp;
						  <select class="easyui-combobox" style="width:80px;height:30px; text-align:center;" data-options="editable:false" name="batch" id="batch">
							<option value="">无</option>
						  <c:forEach var="s"  begin="1" end="99" >
							<option value="${s}">${s}</option>
						 </c:forEach>
							
						  </select>
						  &nbsp;批&nbsp;
						  <select class="easyui-combobox" style="width:80px;height:30px; text-align:center;" name="level.id" id="level" data-options="editable:false">
							<c:forEach items="${l_list}" var="l_list" >
								<option value="${l_list.id}">${l_list.name}</option>
							 </c:forEach>
						  </select>
						   <c:forEach items="${l_list}" var="l_list" >
							<input type="hidden" id="jb_${l_list.id}" value="${l_list.bm}" />
						 </c:forEach>
						  &nbsp;&nbsp;
						  <label><input name="leveltype" type="radio" id="leveltype" style="margin-left:20px;" value="1"  <c:if test="${pro.leveltype =='1'}">checked="checked" </c:if>/>
						  独立</label>
						  <label><input name="leveltype" id="leveltype" type="radio" value="2" <c:if test="${pro.leveltype =='2'}">checked="checked" </c:if>/>扩充</label>
						</td>
					  </tr>
					  <tr>
						<td>批准年度:</td>
						<td>
						<input class="easyui-numberspinner" value="${pro.year}" data-options="required:true,min:1990,max:2050"  style="width:300px;height:30px;" id="year" name="year" ></input>
						 
						</td>
						<td style="padding-left:40px;">项目编号:</td>
						<td><input class="easyui-textbox" style="width:300px;height:30px" name="no" id="no" value="${pro.no}"  data-options="required:true,validType:'length[0,25]',missingMessage:'项目编号不能为空',invalidMessage:'长度不能超出25个字符'"></td>
					  </tr>
					  <tr>
						<td>所属类别:</td>
						<td>
							 <select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="column.id" id="column" data-options="editable:false">
							<c:forEach items="${c_list}" var="c_list" >
								<option value="${c_list.id}">${c_list.name}</option>
							 </c:forEach>
						  </select>
						   <c:forEach items="${c_list}" var="c_list" >
							<input type="hidden" id="lb_${c_list.id}" value="${c_list.no}" data-pid='${c_list.parent.no}' />
						 </c:forEach>
						</td>
						<td style="padding-left:40px;">保护单位:</td>
						<td><input class="easyui-textbox" style="width:300px;height:30px" name="employer" id="employer" value="${pro.employer}" data-options="required:false,validType:'length[0,100]',invalidMessage:'长度不能超出100个字符'"></td>
					  </tr>
					</table>
					<table>
					  <tr>
						<td>项目简介:</td>
						<td><input class="easyui-textbox" data-options="required:false,validType:'length[0,500]',invalidMessage:'长度不能超出500个字符',multiline:true" style="width:700px;height:90px" name="summary" id="summary" value="${pro.summary}"></td>
					  </tr>
					</table>

				  </div>
				</form>
				  <div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left: 300px solid #ddd; border-right: 300px solid #ddd; text-align: center;">     
					附件信息
					</div>
				<!--照片上传 -->
				
				 <table >
				  <tr>
					<td>照片上传</td>
					<td style="width:700px" >
						<div  style="border:2px dashed #CCCCCC;min-height:150px" id="file1">
							<c:forEach items="${cf_list_1}" var="list1"  varStatus="status">
								<table class="uptable" id="file_${status.index}"><tbody><tr><td class="ssi-upImgTd">
									<img class="imgToUpload" src="${basePath}${list1.httpPath}" style="width:140px; height:128px">
								</td></tr>
								<tr><td><span class="ssi-statusLabel  success" data-status="">${list1.fileName}</span>
								</td></tr>
								
								</tbody>			</table>
								
					
							</c:forEach>
							
						</div>
					</td>
					
				  </tr>
				
				</table>
				<!--申报文本上传 -->
				 <table >
				  <tr>
					<td>申报文本</td>
					<td style="width:700px" >
						<div  style="border:2px dashed #CCCCCC;min-height:150px" id="file2">
					
							<c:forEach items="${cf_list_2}" var="list2"  varStatus="status">
								<table class="uptable" id="file2_${status.index}"><tbody><tr><td class="ssi-upImgTd">
									
									<div class="document" href="test.mov" filetype="${fn:substringAfter(list2.fileName,'.')}"><span class="fileCorner"></span></div>
								</td></tr>
								<tr><td><span class="ssi-statusLabel  success" data-status="">${list2.fileName}</span>
								</td></tr>
								
								</tbody>			</table>
								
					
							</c:forEach>
						
						</div>
					</td>
					
				  </tr>
				 
				</table>
				<!--申报片 -->
				<table >
				  <tr>
					<td>申&nbsp;&nbsp;报&nbsp;&nbsp;片</td>
					<td style="width:700px" >
						<div  style="border:2px dashed #CCCCCC;min-height:150px" id="file3">
							<c:forEach items="${cf_list_3}" var="list3"  varStatus="status">
								<table class="uptable" id="file3_${status.index}"><tbody><tr><td class="ssi-upImgTd">
									<div class="document" href="test.mov" filetype="${fn:substringAfter(list3.fileName,'.')}"><span class="fileCorner"></span></div>
								</td></tr>
								<tr><td><span class="ssi-statusLabel  success" data-status="">${list3.fileName}</span>
								</td></tr>
								
								</tbody>			</table>
								
					
							</c:forEach>
						</div>
					</td>
					
				  </tr>
				
				</table>
			
			  </div>
		   
	
	</body>

</html>