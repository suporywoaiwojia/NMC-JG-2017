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
					$('#approve_id').combobox('select','${pro.approve_id}-${pro.userName}');
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
				$('#dlg').dialog('close');
			});
			
		</script>
	
	</head>
	  
	<body>
		<div class="easyui-layout" style="width:100%; height:719px" title="曲目信息采集表">
		<div  data-options="region:'center',split:true" title="曲目信息" style="width:60%;padding:15px">
		  <div style="padding:0 0 10px;">
		  	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:10%;" onclick="save('1')">暂存</a>	
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:10%;" onclick="save('2')">保存</a>	
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-exp'" style="width:10%;" onclick="exppdf('${song.id}')">导出</a>
			 
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
				 <c:if test="${columns.approve =='1'}">
				<tr>
					<td>审核人员:</td>
					<td>
						<select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="approve_id" id="approve_id" data-options="editable:false,panelHeight:'auto'">
							<c:forEach items="${approveUser}" var="approveUser" >
							<option value="${approveUser.loginId}-${approveUser.userName}">${approveUser.userName}</option>
							 </c:forEach>
				  		</select>					</td>
					<td style="padding-left:40px;">&nbsp;</td>
					<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="open_log('${song.id}','3')">查看日志</a></td>
				</tr>
				</c:if>
			  <tr>
			
				<td>曲目名称:</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="name" id="name" value="${song.name}" data-options="required:true,validType:'length[0,50]',missingMessage:'曲目名称不能为空',invalidMessage:'长度不能超出50个字符'"></td>
				<td style="padding-left:40px;">曲目类别:</td>
				<td>
					<select class="easyui-combobox" name="type" id='type' data-options="editable:false,panelHeight:'auto'" style="width:300px;height:30px; text-align:left;" >
						<option value="1" >长调</option>
						<option value="2" >马头琴</option>
						<option value="3" >呼麦</option>
                  </select>				</td>
			  </tr>
			  <tr>
			    <td>演唱/奏者:</td>
			    <td><input class="easyui-textbox" style="width:245px;height:30px" readonly="readonly" id="zz" name="zz" value="${song.inheritor.name}" data-options="required:true,missingMessage:'演唱/奏者不能为空'"><input type="hidden" name="inheritor.id" id="inheritor" value="${song.inheritor.id}"/><a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="findInher();">查找</a>
					
				</td>
			    <td style="padding-left:40px;">所属风格:</td>
			    <td>
					<div id="sstyle">
					<select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="songStyle.id" id="songStyle" data-options="editable:false,panelHeight:'auto'">
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
					 <select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="mtStyle.id" id="mtStyle" data-options="editable:false,panelHeight:'auto'">
						<c:forEach items="${m_list}" var="m_list" >
							<option value="${m_list.id}">${m_list.name}</option>
					   </c:forEach>
				 	 </select>
					  <!--附件获取类别编码 -->
					   <c:forEach items="${m_list}" var="m_list" >
							<input type="hidden" id="m_${m_list.id}" value="${m_list.bm}" />
					 </c:forEach></div>
					 <div id="hstyle" style="display:none">
					 <select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="hmStyle.id" id="hmStyle" data-options="editable:false,panelHeight:'auto'">
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
			  	<td style="padding-left:40px;">伴&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;奏:</td>
				<td ><input class="easyui-textbox" style="width:110px;height:30px" name="instrument" id="instrument" value="${song.instrument}" data-options="required:false,validType:'length[0,25]',invalidMessage:'长度不能超出25个字符'">乐器&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="easyui-textbox" style="width:110px;height:30px" name="accompanied" id="accompanied" value="${song.accompanied}" data-options="required:false,validType:'length[0,25]',invalidMessage:'长度不能超出25个字符'">伴奏
				</td>
			  </tr>
			</table>
			<div id="legend">
			<c:if test="${empty  jj_list}">
				<table  style="margin-top:8px" >
				  <tr>
					<td rowspan="2">简介/传说:</td>
					<td rowspan="2"><input class="easyui-textbox" data-options="required:false,validType:'length[0,500]',invalidMessage:'长度不能超出500个字符',multiline:true" style="width:700px;height:90px" name="jj"  value="" ></td>
					<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addHtml()">增加</a></td>
				  </tr>
				  <tr>
					<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="remove()">移除</a></td>
				  </tr>
				</table>
			</c:if>
			<c:forEach items="${jj_list}" var="jj"  varStatus="status">
					<c:choose> 
					<c:when test="${status.index ==0}">
			<table  style="margin-top:8px" >
				  <tr>
					<td rowspan="2">简介/传说:</td>
					<td rowspan="2"><textarea style="width:700px;height:90px" name="jj">${jj.word}</textarea></td>
					<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addHtml()">增加</a></td>
				  </tr>
				  <tr>
					<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="remove()">移除</a></td>
				  </tr>
			</table>
			 </c:when>
			<c:when test="${status.index >0}">
				<table id="jj_${status.index}"  style="margin-top:8px" >
				  <tr>
					<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="cs" value="jj_${status.index}"></td>
					<td ><span class="textbox" style="width: 698px; height: 88px;"><textarea name="jj" id="jj" class="textbox-text " autocomplete="off" tabindex="" placeholder="" style="text-align: start; margin: 0px; height: 80px; width: 690px;">${jj.word}</textarea></span></td>
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
						<td width="63" rowspan="2">歌&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;词:</td>
						<td width="704" rowspan="2"><input class="easyui-textbox" data-options="required:false,validType:'length[0,500]',invalidMessage:'长度不能超出500个字符',multiline:true" style="width:700px;height:90px" name="gc" id="gc" value="" ></td>
						<td ><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addlyrics()">增加</a></td>
					  </tr>
					  <tr>
						<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="removelyrics()">移除</a></td>
					  </tr>
					</table>
			</c:if>
			<c:forEach items="${gc_list}" var="gc"  varStatus="status">
					<c:choose> 
					<c:when test="${status.index ==0}">
						<table  style="margin-top:8px" >
						  <tr>
							<td rowspan="2">歌&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;词:</td>
							<td rowspan="2"><textarea style="width:700px;height:90px" name="gc">${gc.word}</textarea></td>
							<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addlyrics()">增加</a></td>
						  </tr>
						  <tr>
							<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="removelyrics()">移除</a></td>
						  </tr>
						</table>
					</c:when>
		  			</c:choose>
					<c:choose> 
					<c:when test="${status.index>0}">
						<table id="gc_${status.index}"  style="margin-top:8px" >
						  <tr>
							<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="gcc" value="gc_${status.index}"></td>
							<td ><span class="textbox" style="width: 698px; height: 88px;"><textarea name="gc" id="gc" class="textbox-text " autocomplete="off" tabindex="" placeholder="" style="text-align: start; margin: 0px; height: 80px; width: 690px;">${gc.word}</textarea></span></td>
						  </tr>
						</table>
					</c:when>
		  			</c:choose>
				</c:forEach>
			</div>
				<div id="filedata">
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
			</form>
		  </div>
		  <div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left: 300px solid #ddd; border-right: 300px solid #ddd; text-align: center;">     
					附件信息
					</div>
		  <!--申报文本上传 -->
				 <table >
				  <tr>
					<td>曲谱</td>
					<td style="width:700px" >
						<div  style="border:2px dashed #CCCCCC;min-height:150px" id="file2">
					
							<c:forEach items="${cf_list_2}" var="list2"  varStatus="status">
								<table class="uptable" id="file2_${status.index}"><tbody><tr><td class="ssi-upImgTd">
									
									<div class="document" href="test.mov" filetype="${list2.fileExc}"><span class="fileCorner"></span></div>
								</td></tr>
								<tr><td><span class="ssi-statusLabel  success" data-status="">${list2.fileName}</span>
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
				<!--曲谱 -->
				<table >
				  <tr>
					<td>录音录像</td>
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
				<div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left:220px solid #ddd; border-right:220px solid #ddd; text-align: center;">     
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
		
		function save(state){
			var url = "${basePath}action/song/update/"+state;
			$('#song').form('submit', {
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
		
		
		var num=0;
		function addHtml(){
			num++;
			var html='<table id="html_'+num+'">'+
				  '<tr>'+
					'<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="cs" value="html_'+num+'"></td>'+
					'<td ><span class="textbox" style="width: 698px; height: 88px;"><textarea name="jj"  class="textbox-text " autocomplete="off" tabindex="" placeholder="" style="text-align: start; margin: 0px; height: 80px; width: 690px;"></textarea></span></td>'+
				  '</tr>'+
				'</table>';
				$('#legend').append(html);
		}
		function remove(){
		 var obj= $("input[name='cs']");
			for(var a=0;a<obj.length;a++){
				 if(obj[a].checked){
				 	$("#"+obj[a].value).remove();
				 }
			}
		}
		var num1=1;
		function addlyrics(){
			num++;
			var html='<table id="html_'+num1+'">'+
				  '<tr>'+
					'<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="gcc" value="html_'+num1+'"></td>'+
					'<td ><span class="textbox" style="width: 698px; height: 88px;"><textarea name="gc" id="gc" class="textbox-text " autocomplete="off" tabindex="" placeholder="" style="text-align: start; margin: 0px; height: 80px; width: 690px;"></textarea></span></td>'+
				  '</tr>'+
				'</table>';
				$('#lyrics').append(html);
		}
		function removelyrics(){
		 var obj= $("input[name='gcc']");
			for(var a=0;a<obj.length;a++){
				 if(obj[a].checked){
				 	$("#"+obj[a].value).remove();
				 }
			}
		}
		function findInher(){
			$('#inher').window('open');
		}
	</script>
	<script type="text/javascript">
	//upload传递变量
		var savepath='${columns.columnPath}';
		var basePath='${basePath}';
		
		var sequence;
		function getseq(){
				if(!$("#sstyle").is(":hidden"))
					fg=$('#s_'+$('#songStyle').combobox('getValue')).val();
				if(!$("#mstyle").is(":hidden"))
					fg=$('#m_'+$('#mtStyle').combobox('getValue')).val();
				if(!$("#hstyle").is(":hidden"))
					fg=$('#h_'+$('#hmStyle').combobox('getValue')).val();
				var xm=$('#project').combobox('getValue');	
				var no=$('#no_'+xm).val();		
				var seq1='ICH'; //数据字库编码
				var seq2='${columns.parent.no}';//项目分类
				var seq3=no//项目申报编码
				var seq4='03';//具体项目内容编码 01项目信息 02传承人 03曲目库 04 出版物 05专项活动
				var seq5=fg; //风格
				sequence=seq1+'_'+seq2+'_'+seq3+'_'+seq4+'_'+seq5;
				return sequence;
		}
	</script>
	<script type="text/javascript">
		function exppdf(ids){
			var url="${basePath}action/song/exp/"+ids;
		
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
		<a href="#" download="曲目信息-${song.name}.pdf" target="_blank" id="download">下载</a>
	</div>
	<script src="${basePath}component/ssi-up/js/ssi-uploader.js"></script>
	<script src="${basePath}component/ssi-up/js/upload.js"></script>
	<div id="inher" class="easyui-window" title="传承人" closed="true" style="width:700px;height:500px;padding:5px;" minimizable="false" data-options="inline:true">
		<iframe src="${basePath}action/inheritor/search/CN/1" width="100%" height="100%" frameborder="no" border="0"></iframe>
	</div>
	<script type="text/javascript">
		function open_in(id){
			window.parent.topage('传承人信息','inheritor/view/'+id+'/CN');
		}
		function open_pub(id){
			window.parent.topage('出版物信息','pub/view/'+id+'/CN');
		}
		function open_act(id){
			window.parent.topage('专项活动信息','act/view/'+id+'/CN');
		}
	</script>
	<script src="${basePath}js/app_log.js"></script>
	<div id="log" class="easyui-window" title="日志" closed="true" style="width:1000px;height:500px;padding:5px;top:200px" minimizable="false" data-options="inline:true">
	</div>
</html>