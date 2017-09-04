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
\		<link rel="stylesheet" type="text/css" href="${basePath}component/ssi-up/css/style.css">
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

			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:10%;" onclick="save()">保存</a>	
			 
		  </div>      
		  <div style="margin:10px 0">
		  <form method="post" id="activities">
		  <input type="hidden" name="l_type" value="CN" />
		  <input type="hidden" name="parent_id" value="${act.id}" />
		  <input type="hidden" name="creatdate" value="<fmt:formatDate value='${act.creatdate}' pattern='yyyy-MM-dd'/>" />
			<input type="hidden" name="language" value="${act.language}" />
		<input type="hidden" id="id" value="${act.id}" name="id"/>
		<input type="hidden" name="state" value="2" id="state" />
			<table class="data">
				 <c:if test="${columns.approve =='1'}">
				<tr>
					<td>审核人员:</td>
					<td>
						<select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="approve_id" id="approve_id" data-options="editable:false,panelHeight:'auto'">
							<c:forEach items="${approveUser}" var="approveUser" >
							<option value="${approveUser.loginId}">${approveUser.userName}</option>
							 </c:forEach>
							 <option value="3">通过</option>
							 <option value="4">退回</option>
				  		</select>					</td>
					<td></td>
					<td></td>
				</tr>
				</c:if>
			  <tr>
			
				<td>活动名称:</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="name" id="name" value="${act.name}"  data-options="required:true,validType:'length[0,50]',missingMessage:'活动名称不能为空',invalidMessage:'长度不能超出50个字符'"></td>
				<td style="padding-left:40px;">活动类别:</td>
				<td>
					<select class="easyui-combobox" name="type" id='type' data-options="editable:false,panelHeight:'auto'" style="width:300px;height:30px; text-align:left;" >
						<option value="01" >演出</option>
					<option value="02" >比赛</option>
                   	<option value="03" >会议</option>
					<option value="04" >田野调查</option>
					<option value="05" >培训</option>
                  </select>				</td>
			  </tr>
			  <tr>
			    <td>主办方:</td>
			    <td><input class="easyui-textbox" style="width:300px;height:30px" id="sponsor" name="sponsor" value="${act.sponsor}"  data-options="required:false,validType:'length[0,100]',invalidMessage:'长度不能超出100个字符'">
								</td>
			    <td style="padding-left:40px;">协/承办方:</td>
			    <td><input class="easyui-textbox" style="width:300px;height:30px" name="sponsor_other" id="sponsor_other"  value="${act.sponsor_other}"  data-options="required:false,validType:'length[0,100]',invalidMessage:'长度不能超出100个字符'">				</td>
		      </tr>
			  <tr>
			  	<td>所属项目:</td>
					
				<td><select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="project.id" id="project" data-options="editable:false,panelHeight:'auto'">
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
				<input class="easyui-numberspinner" value="${act.peoplenum}" data-options="required:true,min:0,max:999999"  style="width:300px;height:30px;" id="peoplenum" name="peoplenum" ></input>
				</td>
			  </tr>
			  <tr>
			  	<td>时间地点:</td>
					
				<td colspan="3">
				<input name="holedate_s" id="holedate_s" class="easyui-datebox" style="width:150px" data-options="sharedCalendar:'#cc',formatter:myformatter,parser:myparser,editable:false" value="${act.holedate_s}"/>&nbsp;&nbsp;至&nbsp;&nbsp;
				<input name="holedate_e" id="holedate_e" class="easyui-datebox" style="width:150px" data-options="sharedCalendar:'#cc',formatter:myformatter,parser:myparser,editable:false" value="${act.holedate_e}"/>
				
				&nbsp;&nbsp;在&nbsp;&nbsp;<input class="easyui-textbox" style="width:310px;height:30px" id="holdadd"  name="holdadd" value="${act.holdadd}"  data-options="required:false,validType:'length[0,100]',invalidMessage:'长度不能超出100个字符'"> &nbsp;&nbsp;举办
				<div id="cc" class="easyui-calendar">
				</td>
				
		  	  </tr>
			   <tr>
			    <td>活动级别</td>
			    <td>
					<select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="level.id" id="level" data-options="editable:false,panelHeight:'auto'">
						<c:forEach items="${l_list}" var="l_list" >
							<option value="${l_list.id}">${l_list.name}</option>
					   </c:forEach>
				 	 </select>
				</td>
			    <td style="padding-left:40px;"></td>
			    <td></td>
		      </tr>
			</table>
			<div id="filedata">
					<c:forEach items="${cf_list_1}" var="list1"  varStatus="status">
						<div id='f1_data_${status.index}'>
							<input type="hidden" name="savename" value="${list1.saveName}"/>
							<input type="hidden" name="savepath" value="${list1.savePath}"/>
							<input type="hidden" name="httppath" value="${list1.httpPath}"/>
							<input type="hidden" name="filetype" value="01"/>
							<input type="hidden" name="view" value="${list1.views}" />
							<input type="hidden" name="filename" value="${list1.fileName}.${list1.fileExc}"/>
						</div>
					</c:forEach>
					<c:forEach items="${cf_list_2}" var="list2"  varStatus="status2">
						<div id='f2_data_${status2.index}'>
							<input type="hidden" name="savename" value="${list2.saveName}"/>
							<input type="hidden" name="savepath" value="${list2.savePath}"/>
							<input type="hidden" name="httppath" value="${list2.httpPath}"/>
							<input type="hidden" name="filetype" value="02"/>
							<input type="hidden" name="view" value="${list2.views}" />
							<input type="hidden" name="filename" value="${list2.fileName}.${list2.fileExc}"/>
						</div>
					</c:forEach>
					<c:forEach items="${cf_list_3}" var="list3"  varStatus="status3">
						<div id='f3_data_${status3.index}'>
							<input type="hidden" name="savename" value="${list3.saveName}"/>
							<input type="hidden" name="savepath" value="${list3.savePath}"/>
							<input type="hidden" name="httppath" value="${list3.httpPath}"/>
							<input type="hidden" name="filetype" value="03"/>
							<input type="hidden" name="view" value="${list3.views}" />
							<input type="hidden" name="filename" value="${list3.fileName}.${list3.fileExc}"/>
						</div>
					</c:forEach>
					</div>
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
								<tr><td><span class="ssi-statusLabel  success" data-status="">${list1.fileName}.${list1.fileExc}</span>
								</td></tr>
								<tr><td><span style=" margin-right: 6px">保密<input type="checkbox" name="baomi" value="1" onclick="setview(this,'f1_data_${status.index}')"  <c:if test="${list1.views =='0'}">checked="checked" </c:if>></span><!--<a href="#" onclick="delfile('file_${status.index}','f1_data_${status.index}')">删除</a> -->
								
								</td></tr>
								</tbody>			</table>
								
					
							</c:forEach>
							
						</div>
					</td>
					
				  </tr>
				  <!--<tr>
				  <td style="width:50px"></td>
				  <td>
					<input type="file" multiple id="ssi-upload"/>
					</td>
				  </tr> -->
				</table>
				<!--申报文本上传 -->
				 <table >
				  <tr>
					<td>申报文本</td>
					<td style="width:700px" >
						<div  style="border:2px dashed #CCCCCC;min-height:150px" id="file2">
					
							<c:forEach items="${cf_list_2}" var="list2"  varStatus="status">
								<table class="uptable" id="file2_${status.index}"><tbody><tr><td class="ssi-upImgTd">
									
									<div class="document" href="test.mov" filetype="${list2.fileExc}"><span class="fileCorner"></span></div>
								</td></tr>
								<tr><td><span class="ssi-statusLabel  success" data-status="">${list2.fileName}.${list2.fileExc}</span>
								</td></tr>
								<tr><td><span style=" margin-right: 6px">保密<input type="checkbox" name="baomi" value="1"   <c:if test="${list2.views =='0'}">checked="checked" </c:if>  onclick="setview(this,'f2_data_${status.index}')""></span><!--<a href="#" onclick="delfile('file2_${status.index}','f2_data_${status.index}')">删除</a> -->
								
							
								</td></tr>
								</tbody>			</table>
								
					
							</c:forEach>
						
						</div>
					</td>
					
				  </tr>
				  <!--<tr>
				  <td style="width:50px"></td>
				  <td>
					<input type="file" multiple id="ssi-upload2"/>
					</td>
				  </tr> -->
				</table>
				<!--申报片 -->
				<table >
				  <tr>
					<td>申&nbsp;&nbsp;报&nbsp;&nbsp;片</td>
					<td style="width:700px" >
						<div  style="border:2px dashed #CCCCCC;min-height:150px" id="file3">
							<c:forEach items="${cf_list_3}" var="list3"  varStatus="status">
								<table class="uptable" id="file3_${status.index}"><tbody><tr><td class="ssi-upImgTd">
									<div class="document" href="test.mov" filetype="${list3.fileExc}"><span class="fileCorner"></span></div>
								</td></tr>
								<tr><td><span class="ssi-statusLabel  success" data-status="">${list3.fileName}.${list3.fileExc}</span>
								</td></tr>
								<tr><td><span style=" margin-right: 6px">保密<input type="checkbox" name="baomi" value="1" onclick="setview(this,'f3_data_${status.index}')" <c:if test="${list3.views =='0'}">checked="checked" </c:if>></span><!--<a href="#" onclick="delfile('file3_${status.index}','f3_data_${status.index}')">删除</a> -->
								
								
								</td></tr>
								</tbody>			</table>
								
					
							</c:forEach>
						</div>
					</td>
					
				  </tr>
				 <!-- <tr>
				  <td style="width:50px"></td>
				  <td>
					<input type="file" multiple id="ssi-upload3"/>
					
					</td>
				  </tr> -->
				</table>
		 </div>
		
	</body>
	<script type="text/javascript">
		function save(state){

			var url = "${basePath}action/check/act/update/";
			var approve=$('#approve_id').combobox('getText');
			if(approve=='通过'){
				$('#approve_id').val('');
				$('#state').val('3');
			}else if(approve=='退回'){
				$('#approve_id').val('');
				$('#state').val('4');
			}
			//$("#project").submit();
			var isValid = $("#activities").form('validate');
			if(isValid)
				$.ajax({type:"POST", url:url,data:$('#activities').serialize(),dataType:"text", success:function(datas) {
				
						if(datas=='1'){
							$.messager.alert('提示','提交成功!',success());
						}else if(datas=='0'){
							$.messager.alert('提示','提交失败!');
						}
					}
				});
			}
		}
		function success(){
			$(window.parent.$('#check').window('close'));
			window.parent.searchlist();
		}
	</script>
	<script type="text/javascript">
	//upload传递变量
	
		var savepath='${columns.columnPath}';
		var basePath='${basePath}';
	
		var sequence;
		function getseq(){
				var xm=$('#project').combobox('getValue');	
				var no=$('#no_'+xm).val()			
				var seq1='ICH'; //数据字库编码
				var seq2='${columns.parent.no}';//项目分类
				var seq3=no//项目申报编码
				var seq4='05';//具体项目内容编码 01项目信息 02传承人 03曲目库 04 出版物 05专项活动
				var seq5=$('#type').combobox('getValue'); //级别
				sequence=seq1+'_'+seq2+'_'+seq3+'_'+seq4+'_'+seq5;
				return sequence;
		}
	</script>
	<script src="${basePath}component/ssi-up/js/ssi-uploader.js"></script>
	<script src="${basePath}component/ssi-up/js/upload.js"></script>
</html>