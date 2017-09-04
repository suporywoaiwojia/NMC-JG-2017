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
		
		<title>曲目信息</title>
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/styles-left.css">
		<link rel="stylesheet" type="text/css" href="${basePath}component/ssi-up/css/style.css">
		<link rel="stylesheet" href="${basePath}component/ssi-up/css/ssi-uploader.css"/>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		<script type="text/javascript">
			$(function(){
				$('#type').combobox({
					onSelect: function(record){
						if(record.value==1){
							$('#sstyle').show();
							$('#hstyle').hide();
							$('#mstyle').hide();
						}else if(record.value==2){
							$('#sstyle').hide();
							$('#hstyle').hide();
							$('#mstyle').show();
						}else if(record.value==3){
							$('#sstyle').hide();
							$('#hstyle').show();
							$('#mstyle').hide();
						}
							
					}
				});
				if('${pro.approve_id}'!='')
					$('#approve_id').combobox('select','${pro.approve_id}');
				if('${song.type}'!='')
					$('#type').combobox('select','${song.type}');
				if('${song.project.id}'!='')
					$('#project').combobox('select','${song.project.id}');
				if('${song.songStyle.id}'!='')
					$('#songStyle').combobox('select','${song.songStyle.id}');
				if('${song.hmStyle.id}'!='')
					$('#hmStyle').combobox('select','${song.hmStyle.id}');
				if('${song.mtStyle.id}'!='')
					$('#mtStyle').combobox('select','${song.mtStyle.id}');
			
			});
			
		</script>
	
	</head>
	  
	<body>
		<div class="easyui-panel"   style="width:100%;padding:15px">
		  <div style="padding:0 0 10px;">
		  	
			 
		  </div>      
		  <div style="margin:10px 0">
		  <form method="post" id="song">
		 	 <input type="hidden" id="id" value="${song.id}" name="id"/>
			<input type="hidden" name="l_type" value="CN" />
			<input type="hidden" name="creatdate" value="<fmt:formatDate value='${song.creatdate}' pattern='yyyy-MM-dd'/>" />
			<input type="hidden" name="language" value="${song.language}" />
			<input type="hidden" name="parent_id" value="${song.id}" />
			<input type="hidden" name="inheritor.id" id="inheritor" value="${song.inheritor.id}" />
			<table class="data">
				
			  <tr>
			
				<td>曲目名称:</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="name" id="name" value="${song.name}" readonly="true"></td>
				<td style="padding-left:40px;">曲目类别:</td>
				<td>
					<select class="easyui-combobox" name="type" id='type' data-options="editable:false" style="width:300px;height:30px; text-align:left;" readonly="true">
						<option value="1" >长调</option>
						<option value="2" >马头琴</option>
						<option value="3" >呼麦</option>
                  </select>				</td>
			  </tr>
			  <tr>
			    <td>演唱/奏者:</td>
			    <td><input class="easyui-textbox" style="width:300px;height:30px" readonly="readonly" id="zz" name="zz" value="${song.inheritor.name}" ><input type="hidden" name="inheritor.id" id="inheritor" value="${song.inheritor.id}"/>
					
				</td>
			    <td style="padding-left:40px;">所属风格:</td>
			    <td>
					<div id="sstyle">
					<select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="songStyle.id" id="songStyle" data-options="editable:false" readonly="true">
						<c:forEach items="${s_list}" var="s_list" >
							<option value="${s_list.id}">${s_list.name}</option>
					   </c:forEach>
				 	 </select>
					  <!--附件获取类别编码 -->
					 <c:forEach items="${s_list}" var="s_list" >
							<input type="hidden" id="s_${s_list.id}" value="${s_list.bm}" />
					 </c:forEach>
				  </div>
					 <div id="mstyle" style="display:none">
					 <select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="mtStyle.id" id="mtStyle" data-options="editable:false" readonly="true">
						<c:forEach items="${m_list}" var="m_list" >
							<option value="${m_list.id}">${m_list.name}</option>
					   </c:forEach>
				 	 </select>
					  <!--附件获取类别编码 -->
					   <c:forEach items="${m_list}" var="m_list" >
							<input type="hidden" id="m_${m_list.id}" value="${m_list.bm}" />
					 </c:forEach></div>
					 <div id="hstyle" style="display:none">
					 <select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="hmStyle.id" id="hmStyle" data-options="editable:false" readonly="true">
						<c:forEach items="${h_list}" var="h_list" >
							<option value="${h_list.id}">${h_list.name}</option>
					   </c:forEach>
				 	 </select>
					  <c:forEach items="${h_list}" var="h_list" >
							<input type="hidden" id="h_${h_list.id}" value="${h_list.bm}" />
					 </c:forEach>
				  </div>
				</td>
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
			  	<td style="padding-left:40px;">伴&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;奏:</td>
				<td ><input class="easyui-textbox" style="width:110px;height:30px" name="instrument" id="instrument" value="${song.instrument}" readonly="true">乐器&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="easyui-textbox" style="width:110px;height:30px" name="accompanied" id="accompanied" value="${song.accompanied}" readonly="true">伴奏
				</td>
			  </tr>
			</table>
			<div id="legend">
			<c:if test="${empty  jj_list}">
				<table  style="margin-top:8px" >
				  <tr>
					<td >简介/传说:</td>
					<td><input class="easyui-textbox" readonly="true" style="width:700px;height:90px" name="jj"  value="" ></td>
					
				  </tr>
				 
				</table>
			</c:if>
			<c:forEach items="${jj_list}" var="jj"  varStatus="status">
					<c:choose> 
					<c:when test="${status.index ==0}">
			<table  style="margin-top:8px" >
				  <tr>
					<td  >简介/传说:</td>
					<td ><input class="easyui-textbox"  style="width:700px;height:90px" name="jj" value="${jj.word}" readonly="true"></td>
				
				  </tr>
			
			</table>
			 </c:when>
			<c:when test="${status.index >0}">
				<table id="jj_${status.index}"  style="margin-top:8px" >
				  <tr>
					<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td ><span class="textbox" style="width: 698px; height: 88px;"><textarea name="jj" id="jj" class="textbox-text " autocomplete="off" tabindex="" placeholder="" style="text-align: start; margin: 0px; height: 80px; width: 690px;" readonly="true">${jj.word}</textarea></span></td>
				  </tr>
				</table>
		   </c:when>
		  </c:choose>  
		 </c:forEach>
			</div>
			<div id="lyrics">
			<c:if test="${empty  gc_list}">
				<table  style="margin-top:8px" >
					  <tr>
						<td width="63"  >歌&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;词:</td>
						<td width="704" ><input class="easyui-textbox"readonly="true" style="width:700px;height:90px" name="gc" id="gc" value="" ></td>
						
					  </tr>
					 
					</table>
			</c:if>
			<c:forEach items="${gc_list}" var="gc"  varStatus="status">
					<c:choose> 
					<c:when test="${status.index ==0}">
						<table  style="margin-top:8px" >
						  <tr>
							<td>歌&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;词:</td>
							<td ><input class="easyui-textbox" readonly="true" style="width:700px;height:90px" name="gc" id="gc" value="${gc.word}" ></td>
						
						  </tr>
						  
						</table>
					</c:when>
		  			</c:choose>
					<c:choose> 
					<c:when test="${status.index>0}">
						<table id="gc_${status.index}"  style="margin-top:8px" >
						  <tr>
							<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td ><span class="textbox" style="width: 698px; height: 88px;"><textarea name="gc" id="gc" class="textbox-text " autocomplete="off" tabindex="" placeholder="" style="text-align: start; margin: 0px; height: 80px; width: 690px;" readonly="true">${gc.word}</textarea></span></td>
						  </tr>
						</table>
					</c:when>
		  			</c:choose>
				</c:forEach>
			</div>
				
			</form>
		  </div>
		  <div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left: 300px solid #ddd; border-right: 300px solid #ddd; text-align: center;">     
					附件信息
					</div>
		  <!--申报文本上传 -->
				 <table >
				  <tr>
					<td>曲谱信息</td>
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
				<!--曲谱 -->
				<table >
				  <tr>
					<td>录音录像</td>
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