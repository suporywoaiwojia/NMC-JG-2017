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
		
		<title>出版物信息</title>
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/styles-left.css">
		<link rel="stylesheet" type="text/css" href="${basePath}component/ssi-up/css/style.css">
		<link rel="stylesheet" href="${basePath}component/ssi-up/css/ssi-uploader.css"/>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		<script type="text/javascript">
			
			function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
			}
			function myparser(s){
				if (!s) return new Date();
				var ss = (s.split('-'));
				var y = parseInt(ss[0],10);
				var m = parseInt(ss[1],10);
				var d = parseInt(ss[2],10);
				if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
					return new Date(y,m-1,d);
				} else {
					return new Date();
				}
			}
			$(function(){
				
				if('${act.approve_id}'!='')
					$('#approve_id').combobox('select','${act.approve_id}');
				if('${act.type}'!='')
					$('#type').combobox('select','${act.type}');
				if('${act.level.id}'!='')
					$('#level').combobox('select','${act.level.id}');
				if('${act.project.id}'!='')
					$('#project').combobox('select','${act.project.id}');
			})
		</script>
	
	</head>
	  
	<body>
		<div class="easyui-panel"   style="width:100%;padding:15px">
		  <div style="padding:0 0 10px;">

			 
		  </div>      
		  <div style="margin:10px 0">
		  <form method="post" id="activities">
		  <input type="hidden" name="l_type" value="CN" />
		  <input type="hidden" name="parent_id" value="${act.id}" />
		  <input type="hidden" name="creatdate" value="<fmt:formatDate value='${act.creatdate}' pattern='yyyy-MM-dd'/>" />
			<input type="hidden" name="language" value="${act.language}" />
		<input type="hidden" id="id" value="${act.id}" name="id"/>
			<table class="data">
				
			  <tr>
			
				<td>活动名称:</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="name" id="name" value="${act.name}" readonly="true"></td>
				<td style="padding-left:40px;">活动类别:</td>
				<td>
					<select class="easyui-combobox" name="type" id='type' data-options="editable:false" style="width:300px;height:30px; text-align:left;" readonly="true">
						<option value="01" >演出</option>
					<option value="02" >比赛</option>
                   	<option value="03" >会议</option>
					<option value="04" >田野调查</option>
					<option value="05" >培训</option>
                  </select>				</td>
			  </tr>
			  <tr>
			    <td>主办方:</td>
			    <td><input class="easyui-textbox" style="width:300px;height:30px" id="sponsor" name="sponsor" value="${act.sponsor}" readonly="true">
								</td>
			    <td style="padding-left:40px;">协/承办方:</td>
			    <td><input class="easyui-textbox" style="width:300px;height:30px" name="sponsor_other" id="sponsor_other"  value="${act.sponsor_other}" readonly="true">				</td>
		      </tr>
			  <tr>
			  	<td>所属项目:</td>
					
				<td><select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="project.id" id="project" data-options="editable:false" readonly="true">
						<c:forEach items="${p_list}" var="p_list" >
							<option value="${p_list.id}">${p_list.name}</option>
					   </c:forEach>
				 	 </select>
					  <!--附件项目类别编码 -->
					 <c:forEach items="${p_list}" var="p_list" >
						<input type="hidden" id="no_${p_list.id}" value="${p_list.no}" />
					</c:forEach>
					 </td>
			  	<td style="padding-left:40px;">参加人数:</td>
				<td >
				<input class="easyui-numberspinner" value="${act.peoplenum}" readonly="true"  style="width:300px;height:30px;" id="peoplenum" name="peoplenum" ></input>
				</td>
			  </tr>
			  <tr>
			  	<td>时间地点:</td>
					
				<td colspan="3">
				<input name="holedate_s" id="holedate_s" class="easyui-datebox" style="width:150px" readonly="true" value="${act.holedate_s}"/>&nbsp;&nbsp;至&nbsp;&nbsp;
				<input name="holedate_e" id="holedate_e" class="easyui-datebox" style="width:150px" readonly="true" value="${act.holedate_e}"/>
				
				&nbsp;&nbsp;在&nbsp;&nbsp;<input class="easyui-textbox" style="width:310px;height:30px" id="holdadd"  name="holdadd" value="${act.holdadd}"readonly="true"> &nbsp;&nbsp;举办
			
				</td>
				
		  	  </tr>
			   <tr>
			    <td>活动级别</td>
			    <td>
					<select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="level.id" id="level" data-options="editable:false" readonly="true">
						<c:forEach items="${l_list}" var="l_list" >
							<option value="${l_list.id}">${l_list.name}</option>
					   </c:forEach>
				 	 </select>
				</td>
			    <td style="padding-left:40px;"></td>
			    <td></td>
		      </tr>
			</table>
		
			</form>
		  </div>
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
					<td>文本上传</td>
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
					<td>录音录像</td>
					<td style="width:700px" >
						<div  style="border:2px dashed #CCCCCC;min-height:150px" id="file5">
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