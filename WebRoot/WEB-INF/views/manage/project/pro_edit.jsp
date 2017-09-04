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
				$('#approve_id').combobox('select','${pro.approve_id}-${pro.approve_name}');
			
		});
		
		</script>

	
	</head>
	  
	<body>
		<div class="easyui-layout" style="width:100%; height:709px" title="项目信息采集表">
			 <div  data-options="region:'center',split:true" title="项目信息" style="width:60%;padding:15px">
				  <div style="padding:0 0 10px;">
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:10%;" onclick="update('1')">暂存</a>	
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:10%;" onclick="update('2')">保存</a>	
					 <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-exp'" style="width:10%;" onclick="exppdf('${pro.id}')">导出</a>	
				  </div> 
				   <form id="project" action=""  method="post">
				  <div style="margin:10px 0">
				 
						<input type="hidden" id="id" value="${pro.id}" name="id"/>
						<input type="hidden" name="l_type" value="CN" />
						<input type="hidden" name="creatdate" value="<fmt:formatDate value='${pro.creatdate}' pattern='yyyy-MM-dd'/>" />
						<input type="hidden" name="language" value="${pro.language}" />
						<input type="hidden" name="parent_id" value="${pro.id}" />
						<input type="hidden" name="column_no"  id="column_no"/>
					<table class="data">
					
						<c:if test="${columns.approve =='1'}">
						<tr>
							<td>审核人员:</td>
							<td>
								<select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="approve_id" id="approve_id" data-options="editable:false,panelHeight:'auto'">
									<c:forEach items="${approveUser}" var="approveUser" >
									<option value="${approveUser.loginId}-${approveUser.userName}">${approveUser.userName}</option>

									 </c:forEach>
								</select>
							</td>
							<td style="padding-left:40px;">&nbsp;</td>
							<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="open_log('${pro.id}','1')">查看日志</a></td>
						</tr>
						</c:if>
					  <tr>
						<td>项目名称:</td>
						<td><input class="easyui-textbox" style="width:300px;height:30px" name="name" id="name" value="${pro.name}" data-options="required:true,validType:'length[0,50]',missingMessage:'项目名称不能为空',invalidMessage:'长度不能超出50个字符'"></td>
						<td style="padding-left:40px;">级别批次:</td>
						<td>
						  &nbsp;第&nbsp;
						  <select class="easyui-combobox" style="width:80px;height:30px; text-align:center;" data-options="editable:false,panelHeight:'auto'" name="batch" id="batch">
							<option value="">无</option>
						  <c:forEach var="s"  begin="1" end="99" >
							<option value="${s}">${s}</option>
						 </c:forEach>
							
						  </select>
						  &nbsp;批&nbsp;
						  <select class="easyui-combobox" style="width:80px;height:30px; text-align:center;" name="level.id" id="level" data-options="editable:false,panelHeight:'auto'">
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
					<div id="filedata">
					<c:forEach items="${cf_list_1}" var="list1"  varStatus="status">
						<div id='f1_data_${status.index}'>
							<input type="hidden" name="savename" value="${list1.saveName}"/>
							<input type="hidden" name="savepath" value="${list1.savePath}"/>
							<input type="hidden" name="httppath" value="${list1.httpPath}"/>
							<input type="hidden" name="filetype" value="01"/>
							<input type="hidden" name="inheritorId" value="${list1.inheritorId}"/>
							<input type="hidden" name="inheritorName" value="${list1.inheritorName}"/>
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
							<input type="hidden" name="inheritorId" value="${list2.inheritorId}"/>
							<input type="hidden" name="inheritorName" value="${list2.inheritorName}"/>
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
							<input type="hidden" name="inheritorId" value="${list3.inheritorId}"/>
							<input type="hidden" name="inheritorName" value="${list3.inheritorName}"/>
							<input type="hidden" name="view" value="${list3.views}" />
							<input type="hidden" name="filename" value="${list3.fileName}.${list3.fileExc}"/>
						</div>
					</c:forEach>
					</div>
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
								<tr><td><span class="ssi-statusLabel  success" data-status="">${list1.fileName}.${list1.fileExc}</span>
								</td></tr>
								<tr><td><span style=" margin-right: 6px">保密<input type="checkbox" name="baomi" value="1" onclick="setview(this,'f1_data_${status.index}')"  <c:if test="${list1.views =='0'}">checked="checked" </c:if>></span><a href="#" onclick="delfile('file_${status.index}','f1_data_${status.index}')">删除</a>
								
								</td></tr>
								</tbody>			</table>
								
					
							</c:forEach>
							
						</div>
					</td>
					
				  </tr>
				  <tr>
				  <td style="width:50px"></td>
				  <td>
					<input type="file" multiple id="ssi-upload"/>
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
									
									<div class="document" href="test.mov" filetype="${list2.fileExc}"><span class="fileCorner"></span></div>
								</td></tr>
								<tr><td><span class="ssi-statusLabel  success" data-status="">${list2.fileName}.${list2.fileExc}</span>
								</td></tr>
								<tr><td><span style=" margin-right: 6px">保密<input type="checkbox" name="baomi" value="1"   <c:if test="${list2.views =='0'}">checked="checked" </c:if>  onclick="setview(this,'f2_data_${status.index}')""></span><a href="#" onclick="delfile('file2_${status.index}','f2_data_${status.index}')">删除</a>
								
							
								</td></tr>
								</tbody>			</table>
								
					
							</c:forEach>
						
						</div>
					</td>
					
				  </tr>
				  <tr>
				  <td style="width:50px"></td>
				  <td>
					<input type="file" multiple id="ssi-upload2"/>
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
									<div class="document" href="test.mov" filetype="${list3.fileExc}"><span class="fileCorner"></span></div>
								</td></tr>
								<tr><td><span class="ssi-statusLabel  success" data-status="">${list3.fileName}.${list3.fileExc}</span>
								</td></tr>
								<tr><td><span style=" margin-right: 6px">保密<input type="checkbox" name="baomi" value="1" onclick="setview(this,'f3_data_${status.index}')" <c:if test="${list3.views =='0'}">checked="checked" </c:if>></span><a href="#" onclick="delfile('file3_${status.index}','f3_data_${status.index}')">删除</a>
								
								
								</td></tr>
								</tbody>			</table>
								
					
							</c:forEach>
						</div>
					</td>
					
				  </tr>
				  <tr>
				  <td style="width:50px"></td>
				  <td>
					<input type="file" multiple id="ssi-upload3"/>
					
					</td>
				  </tr>
				</table>
			
			  </div>
		     <div data-options="region:'east',split:true" title="相关链接" style="width:40%;">
			
					<div style="margin:0 0 10px;">
					<div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left: 220px solid #ddd; border-right: 220px solid #ddd; text-align: center;">     
					传承人信息
					</div>
						<div  style="width:100%; min-height:100px float:left; line-height:30px;">
							<c:forEach items="${list_in}" var="list_in">
							<label style="margin-left:5px">
								<a href="#" class="easyui-linkbutton" onclick="open_in('${list_in.id}')">${list_in.name}</a>
							</label>
							</c:forEach>
						</div>
					</div> 
					  <div style="margin:0 0 10px;">
						<div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left: 220px solid #ddd; border-right: 220px solid #ddd; text-align: center;">     
					曲目信息
					</div>
						<div  style="width:100%; min-height:100px float:left; line-height:30px;">
							<c:forEach items="${list_song}" var="list_song">
							<label style="margin-left:5px">
							<a href="#" class="easyui-linkbutton" onclick="open_song('${list_song.id}')">${list_song.name}</a>
							</label>
							</c:forEach>
						</div>
					</div>
					  <div style="margin:0 0 10px;">
						<div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left:220px solid #ddd; border-right:220px solid #ddd; text-align: center;">     
					出版物信息
					</div>
						<div  style="width:100%; min-height:100px float:left; line-height:30px;">
							<c:forEach items="${list_pub}" var="list_pub">
							<label style="margin-left:5px">
							<a href="#" class="easyui-linkbutton" onclick="open_pub('${list_pub.id}')">${list_pub.name}</a>
							</label>
							</c:forEach>
						</div>
					  </div>
					  <div style="margin:0 0 10px;">
						<div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left:220px solid #ddd; border-right: 220px solid #ddd; text-align: center;">     
					专项活动信息
					</div>
						<div  style="width:100%; min-height:100px float:left; line-height:30px;">
							<c:forEach items="${list_act}" var="list_act">
							<label style="margin-left:5px">
							<a href="#" class="easyui-linkbutton" onclick="open_act('${list_act.id}')">${list_act.name}</a>
							</label>
							</c:forEach>
						</div>
					  </div> 
			 </div>    
		</div>
	
	</body>
	<script type="text/javascript">
		var basePath='${basePath}';
		function update(state){
			var url = "${basePath}action/project/update/"+state;
			var lb=$('#column').combobox('getValue');
			var lb_no=$('#lb_'+lb).val();//项目分类
			$('#column_no').val(lb_no);
			$('#project').form('submit', {
				url: url,
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid)
						return isValid;	// return false will stop the form submission
				},
				success: function(data){
					if(data=="1"){
						$.messager.alert('消息','保存成功','info',function(){location.reload();});
					}else{
						$.messager.alert('消息','保存失败','info');
					}
				}
			});
		}
		
		function exppdf(ids){
			var url="${basePath}action/project/exp/"+ids;
		
			var win = $.messager.progress({
				title:'waiting',
				msg:'生成数据',
				text:'正在生成请耐心等待……'
			});
			
		
			$.ajax({type:"GET", url:url,dataType:"text", success:function(datas) {
			
					if(datas==''){
						$.messager.progress('close');
						$.messager.alert('提示','导出失败!');
					}else{
						$.messager.progress('close');
						$('#download').attr('href','${basePath}'+datas);
						$('#dlg').dialog('open');
					}
				}
			});
		}
	</script>
	<div id="dlg" class="easyui-dialog" title="导出" closed="true" data-options="iconCls:'icon-exp'" style="width:260px;height:140px;padding:10px; line-height:80px;text-align:center">
		<a href="#" download="项目信息-${pro.name}.pdf" target="_blank" id="download">下载</a>
	</div>
		<script type="text/javascript">
	//upload传递变量
	
		var savepath='${columns.columnPath}';
		var basePath='${basePath}';
		
		var sequence;
		function getseq(){
				var lb=$('#column').combobox('getValue');
				var jb=$('#level').combobox('getValue');			
				var seq1='ICH'; //数据字库编码
				var seq2=$('#lb_'+lb).val();//项目分类
				var seq3=$('#no').val();//项目申报编码
				var seq4='01';//具体项目内容编码 01项目信息 02传承人 03曲目库 04 出版物 05专项活动
				var seq5=jb; //级别
				sequence=seq1+'_'+seq2+'_'+seq3+'_'+seq4+'_'+seq5;
				return sequence;
		}
		function open_in(id){
			window.parent.topage('传承人信息','inheritor/view/'+id+'/CN');
		}
		function open_song(id){
			window.parent.topage('曲目信息','song/view/'+id+'/CN');
		}
		function open_pub(id){
			window.parent.topage('出版物信息','pub/view/'+id+'/CN');
		}
		function open_act(id){
			window.parent.topage('专项活动信息','act/view/'+id+'/CN');
		}
	</script>
	<script src="${basePath}component/ssi-up/js/ssi-uploader.js"></script>
	
	<script src="${basePath}component/ssi-up/js/upload.js"></script>
	<script src="${basePath}js/app_log.js"></script>
	<div id="log" class="easyui-window" title="日志" closed="true" style="width:1000px;height:500px;padding:5px;" minimizable="false" data-options="inline:true">
	</div>
</html>