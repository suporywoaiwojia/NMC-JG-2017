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
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0_tm/css/easyui_tm.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0_tm/css/styles.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0_tm/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0_tm/css/demo.css">
		<link rel="stylesheet" type="text/css" href="${basePath}component/ssi-up/css/style.css">
		<link rel="stylesheet" href="${basePath}component/ssi-up/css/ssi-uploader.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0_tm/css/styles-left.css">
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0_tm/js/jquery.easyui.min.js"></script>
		<script type="text/javascript">
			$(function(){
				
				if('${pub.approve_id}'!='')
					$('#approve_id').combobox('select','${pub.approve_id}');
				if('${pub.type}'!='')
					$('#type').combobox('select','${pub.type}');
				if('${pub.publishing.id}'!='')
					$('#publishing').combobox('select','${pub.publishing.id}');
				if('${pub.project.id}'!='')
					$('#project').combobox('select','${pub.project.id}');
			})
		</script>
	
	</head>
	  
	<body>
		<div class="easyui-panel mongol_div"   style="width:100%;padding:15px" data-options="fit:true">
		    
		  <div style="margin:10px 0">
		  <form method="post" id="publication">
		  <input type="hidden" name="l_type" value="MN" />
		  <input type="hidden" name="parent_id" value="${pub.id}" />
		  <input type="hidden" name="creatdate" value="<fmt:formatDate value='${pub.creatdate}' pattern='yyyy-MM-dd'/>" />
		<input type="hidden" id="id" value="${pub.id}" name="id"/>
			<table class="data">
				
			  <tr>
			    <td> ᠨᠡᠷ᠎ᠡ:</td>
			
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="name" id="name"  value="${pub.name}" readonly="true"></td>
				<td style="padding-left:20px;"> ᠠᠩᠭᠢᠯᠠᠯ:</td>
				<td>
					<select class="easyui-combobox" name="type" id='type' data-options="editable:false" style="width:300px;height:30px; text-align:left;" readonly="true">
						 <option value="01" > ᠲᠤᠰᠬᠠᠢ ᠪᠦᠲᠦᠭᠡᠯ</option>
						<option value="02" > ᠥᠭᠦᠯᠡᠯ</option>
						<option value="03" > ᠠᠶ᠎ᠠ ᠶᠢᠨ ᠲᠡᠭᠦᠪᠦᠷᠢ</option>
						<option value="04" > ᠴᠣᠮᠣᠭ</option>
						<option value="05" > ᠮᠡᠳᠡᠭᠡᠯᠡᠬᠦ</option>
                  </select>				</td>
			  </tr>
			  <tr>
			    <td> ᠵᠣᠬᠢᠶᠠᠭᠴᠢ:</td>
			    <td><input class="easyui-textbox" style="width:300px;height:30px" id="zz" name="zz" readonly="true" value="${pub.inheritor.name}" > 
					<input type="hidden" name="inheritor.id" id="inheritor" value="${pub.inheritor.id}"/>				</td>
			    <td style="padding-left:20px;"> ᠪᠤᠰᠤᠳ ᠵᠣᠬᠢᠶᠠᠭᠴᠢ:</td>
			    <td><input class="easyui-textbox" style="width:300px;height:30px" name="author_other" id="author_other" value="${pub.author_other}" dreadonly="true" />				</td>
		      </tr>
			  <tr>
			    <td> ᠬᠠᠷᠢᠶᠠᠯᠠᠬᠤ ᠲᠥᠰᠥᠯ:</td>
			  	<td><select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="project.id" id="project" data-options="editable:false" readonly="true">
						<c:forEach items="${p_list}" var="p_list" >
							<option value="${p_list.id}">${p_list.name}</option>
					   </c:forEach>
				 	 </select>
					  <!--附件项目类别编码 -->
					 <c:forEach items="${p_list}" var="p_list" >
						<input type="hidden" id="no_${p_list.id}" value="${p_list.no}" />
					</c:forEach>					 </td>
			  	<td style="padding-left:20px;"> ᠬᠡᠪᠯᠡᠭᠰᠡᠨ ᠪᠠᠶᠢᠭᠤᠯᠤᠮᠵᠢ:</td>
			  	<td >
				<select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="publishing.id" id="publishing" data-options="editable:false" readonly="true">
						<c:forEach items="${pub_list}" var="pub_list" >
							<option value="${pub_list.id}">${pub_list.name}</option>
					   </c:forEach>
				 	 </select>				</td>
			  </tr>
			  <tr>
			    <td> ᠬᠡᠪᠯᠡᠭᠰᠡᠨ ᠤᠳᠠᠭ᠎ᠠ:</td>
			  	<td colspan="3">
				<input class="easyui-numberspinner" value="${pub.pubyear}" data-options="required:true,min:1940,max:2050"  style="width:100px;height:30px;" id="pubyear" name="pubyear" readonly="true"></input>
				ᠣᠨ ᠤ
<input class="easyui-numberspinner"  data-options="required:true,min:1,max:12"  style="width:100px;height:30px;" id="pubmonth" name="pubmonth"  value="${pub.pubmonth}" readonly="true"></input>
</input>
ᠰᠠᠷ᠎ᠠ ᠳᠤ ᠬᠡᠪᠯᠡᠭᠰᠡᠨ
<input class="easyui-numberspinner"  data-options="required:true,min:1940,max:2050"  style="width:100px;height:30px;" id="pubyear1" name="pubyear1"  value="${pub.pubyear1}" readonly="true"></input>
ᠣᠨ ᠤ
<input class="easyui-numberspinner"  data-options="required:true,min:1,max:12"  style="width:100px;height:30px;" id="pubmonth1" name="pubmonth1"  value="${pub.pubmonth1}" readonly="true"></input>
ᠰᠠᠷ᠎ᠠ 
<input class="easyui-numberspinner" data-options="required:true,min:1,max:9999"  style="width:100px;height:30px;" id="frequency" name="frequency"  value="${pub.frequency}" readonly="true"></input>
ᠳᠤ ᠤᠳᠠᠭ᠎ᠠ ᠬᠡᠪᠯᠡᠭᠰᠡᠨ</td>
		  	  </tr>
			   <tr>
			     <td> ᠨᠣᠮ ᠤᠨ ᠪᠠᠷᠢᠮᠵᠢᠶ᠎ᠠ ᠳ᠋ᠤᠭᠠᠷ:</td>
			    <td><input class="easyui-textbox" style="width:300px;height:30px" id="isbn"  name="isbn" value="${pub.isbn}"  readonly="true">					</td>
			    <td style="padding-left:40px;"><span style="padding-left:20px;"> ᠲᠦᠯᠬᠢᠭᠦᠷ ᠦᠭᠡ:</span></td>
			    <td><input class="easyui-textbox" style="width:300px;height:30px" name="keyword" id="keyword" value="${pub.keyword}" readonly="true">				</td>
		      </tr>
			</table>
		
			</form>
		  </div>
		  <div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left: 300px solid #ddd; border-right: 300px solid #ddd; text-align: center;" class="mongol_div">     
					ᠳᠠᠭᠠᠯᠲᠠ ᠰᠤᠷᠠᠭ
					</div>
		 <!--照片上传 -->
				
				 <table  width="100%" >
				  <tr>
					<td width="16%"><span class="text_title"> ᠭᠠᠳᠠᠷ ᠤᠨ ᠵᠢᠷᠤᠭ</span></td>
					<td width="84%"  >
						<div  style="border:2px dashed #CCCCCC;min-height:150px" id="file4">
							<c:forEach items="${cf_list_4}" var="list4"  varStatus="status">
								<table class="uptable" id="file_${status.index}"><tbody><tr><td class="ssi-upImgTd">
									<img class="imgToUpload" src="${basePath}${list4.httpPath}" style="width:140px; height:128px">
								</td></tr>
								<tr><td><span class="ssi-statusLabel  success" data-status="">${list4.fileName}</span>
								</td></tr>
								
								</tbody>			</table>
								
					
							</c:forEach>
							
						</div>
					</td>
					
				  </tr>
				
				</table>
				<!--申报文本上传 -->
				 <table width="100%" >
				  <tr>
					<td width="16%"> ᠲᠸ᠋ᠺᠰᠲ ᠪᠠᠶᠢᠷᠢᠯᠠᠭᠤᠯᠬᠤ</td>
					<td width="84%" >
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
				<table width="100%" >
				  <tr>
					<td width="16%"> ᠳᠠᠭᠤ ᠳᠦᠷᠰᠦ ᠪᠢᠴᠢᠯᠭᠡ</td>
					<td width="84%"  >
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