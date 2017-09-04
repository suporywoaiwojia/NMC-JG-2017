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
		
		<title>传承人信息</title>
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
		<link rel="stylesheet" type="text/css" href="${basePath}component/ssi-up/css/style.css">
		<link rel="stylesheet" href="${basePath}component/ssi-up/css/ssi-uploader.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/styles-left.css">
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		<script type="text/javascript">
			//日期格式化
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
			if('${in.batch}'!='')
				$('#batch').combobox('select','${in.batch}');
			if('${in.column.id}'!='')
				$('#column').combobox('select','${in.column.id}');
			if('${in.level.id}'!='')
				$('#level').combobox('select','${in.level.id}');
			if('${in.approve_id}'!='')
				$('#approve_id').combobox('select','${in.approve_id}');
			if('${in.sex}'!='')
				$('#sex').combobox('select','${in.sex}');
			if('${in.naction.id}'!='')
				$('#naction').combobox('select','${in.naction.id}');
			if('${in.project.id}'!='')
				$('#project').combobox('select','${in.project.id}');
		
		});
		</script>
	
	</head>
	  
	<body>
		 <div class="easyui-panel"   style="width:100%;padding:15px">
		  <div style="padding:0 0 10px;">
		  	
			 
		  </div>      
		  <div style="margin:10px 0">
		  <form method="post" id="inheritor">
		  	<input type="hidden" name="l_type" value="CN" />
		  	<input type="hidden" id="id" value="${in.id}" name="id"/>
			<input type="hidden" name="creatdate" value="<fmt:formatDate value='${in.creatdate}' pattern='yyyy-MM-dd'/>" />
			<input type="hidden" name="language" value="${in.language}" />
			<input type="hidden" name="parent_id" value="${in.id}" />
			<table class="data">
				
			  <tr>
			
				<td>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="name" id="name"  value="${in.name}"  readonly="true"></td>
				<td style="padding-left:40px;">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</td>
				<td>
					<select class="easyui-combobox" name="sex" id='sex' data-options="editable:false" style="width:80px"  readonly="true">
						<option value="1" >男</option>
						<option value="2" >女</option>
                  </select>
				</td>
				
			  </tr>
			   <tr>
			    <td>联系电话:</td>
			    <td><input class="easyui-textbox" style="width:300px;height:30px" name="tel" id="tel" value="${in.tel}"  readonly="true"></td>
			    <td style="padding-left:40px;">身份证号:</td>
			    <td><input class="easyui-textbox" style="width:300px;height:30px" name="carid" id="carid"  value="${in.carid}" readonly="true"></td>
		      </tr>
			  <tr>
			  	<td>民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族:</td>
				<td>
					<select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="naction.id" id="naction"  readonly="true">
						<c:forEach items="${n_list}" var="n_list" >
							<option value="${n_list.id}">${n_list.name}</option>
					   </c:forEach>
				 	 </select>
				</td>
				<td style="padding-left:40px;">出生年月:</td>
				<td>
				<input name="born_s" id="born_s" class="easyui-datebox" style="width:140px" readonly="true" value="${in.born_s}" />至
				<input name="born_e" id="born_e" class="easyui-datebox" style="width:140px"  readonly="true" value="${in.born_e}"/>
				</td>
			  </tr>
			  <tr>
			  	<td>所属类别:</td>
				<td>
					 <select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="column.id" id="column"  readonly="true">
						<c:forEach items="${c_list}" var="c_list" >
							<option value="${c_list.id}">${c_list.name}</option>
					   </c:forEach>
				 	 </select>
					  <!--附件获取类别编码 -->
					 <c:forEach items="${c_list}" var="c_list" >
						<input type="hidden" id="lb_${c_list.id}" value="${c_list.no}" data-pid='${c_list.parent.no}' />
					 </c:forEach>	
				</td>
				<td  style="padding-left:40px;">所属项目:</td>
				<td>
					 <select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="project.id" id="project" data-options="editable:false"  readonly="true">
						<c:forEach items="${p_list}" var="p_list" >
							<option value="${p_list.id}">${p_list.name}</option>
					   </c:forEach>
				 	 </select>
					   <!--附件项目类别编码 -->
					 <c:forEach items="${p_list}" var="p_list" >
						<input type="hidden" id="no_${p_list.id}" value="${p_list.no}" />
					</c:forEach>
				</td>
			  </tr>
			  <tr>
				<td>批准年度:</td>
				<td>
				<input class="easyui-numberspinner"    readonly="true" style="width:300px;height:30px;" id="year" name="year" value="${in.year}"></input>
				 
				</td>
				<td style="padding-left:40px;">级别批次:</td>
				<td>
					 &nbsp;第&nbsp;
				  <select class="easyui-combobox" style="width:80px;height:30px; text-align:center;" data-options="editable:false" name="batch" id="batch"  readonly="true">
				  	<option value="">无</option>
				  <c:forEach var="s"  begin="1" end="99" >
					<option value="${s}">${s}</option>
				 </c:forEach>
					
				  </select>
				  &nbsp;批&nbsp;
				  <select class="easyui-combobox" style="width:80px;height:30px; text-align:center;" name="level.id" id="level" data-options="editable:false"  readonly="true">
					<c:forEach items="${l_list}" var="l_list" >
						<option value="${l_list.id}">${l_list.name}</option>
					 </c:forEach>
				  </select>
				   <!--附件获取级别编码 -->
				  <c:forEach items="${l_list}" var="l_list" >
					<input type="hidden" id="jb_${l_list.id}" value="${l_list.bm}" />
				 </c:forEach>
				</td>
			  </tr>
			 
			</table>
			<table>
			  <tr>
				<td>人物简介:</td>
				<td><input class="easyui-textbox" data-options="required:false,validType:'length[0,500]',invalidMessage:'长度不能超出500个字符',multiline:true" style="width:700px;height:90px" name="summary" id="summary" value="${in.summary}"  readonly="true"></td>
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