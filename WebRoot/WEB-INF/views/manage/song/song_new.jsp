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
			});
			
		</script>
	
	</head>
	  
	<body>
		 <div class="easyui-panel"   style="width:100%;padding:15px">
		  <div style="padding:0 0 10px;">
		  	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:10%;" onclick="save('1')">暂存</a>	
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:10%;" onclick="save('2')">保存</a>	
			 
		  </div>      
		  <div style="margin:10px 0">
		  <form method="post" id="song">
		  <input type="hidden" name="l_type" value="CN" />
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
					<td></td>
					<td></td>
				</tr>
				</c:if>
			  <tr>
			
				<td>曲目名称:</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="name" id="name" data-options="required:true,validType:'length[0,50]',missingMessage:'曲目名称不能为空',invalidMessage:'长度不能超出50个字符'"></td>
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
			    <td><input class="easyui-textbox" style="width:245px;height:30px" id="zz" readonly="true" data-options="required:true,missingMessage:'演唱/奏者不能为空'"><a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="findInher();">查找</a>
					<input type="hidden" name="inheritor.id" id="inheritor"/>
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
					  <c:forEach items="${m_list}" var="m_list" >
							<input type="hidden" id="m_${m_list.id}" value="${m_list.bm}" />
					 </c:forEach>
					 </div>
					 <div id="hstyle" style="display:none">
					 <select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="hmstyle.id" id="hmStyle" data-options="editable:false,panelHeight:'auto'">
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
				<td ><input class="easyui-textbox" style="width:110px;height:30px" name="instrument" id="instrument" data-options="required:false,validType:'length[0,25]',invalidMessage:'长度不能超出25个字符'">乐器&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="easyui-textbox" style="width:110px;height:30px" name="accompanied" id="accompanied" data-options="required:false,validType:'length[0,25]',invalidMessage:'长度不能超出25个字符'">伴奏
				</td>
			  </tr>
			</table>
			<div id="legend">
			<table style="margin-top:8px" >
			  <tr>
				<td rowspan="2">简介/传说:</td>
				<td rowspan="2"><input class="easyui-textbox"  data-options="required:false,validType:'length[0,500]',invalidMessage:'长度不能超出500个字符',multiline:true" style="width:700px;height:90px" name="jj" id="jj" ></td>
				<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addHtml()">增加</a></td>
			  </tr>
			  <tr>
			    <td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="remove()">移除</a></td>
		      </tr>
			</table>
			</div>
			<div id="lyrics">
			<table  style="margin-top:8px">
			  <tr>
				<td rowspan="2">歌&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;词:</td>
				<td rowspan="2"><input class="easyui-textbox"  data-options="required:false,validType:'length[0,500]',invalidMessage:'长度不能超出500个字符',multiline:true" style="width:700px;height:90px" name="gc" id="gc" ></td>
				<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addlyrics()">增加</a></td>
			  </tr>
			  <tr>
			    <td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="removelyrics()">移除</a></td>
		      </tr>
			</table>
			</div>
			<div id="filedata"></div>
			</form>
		  </div>
		   <div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left: 300px solid #ddd; border-right: 300px solid #ddd; text-align: center;">附件信息</div>
		 
			<table>
			  <tr>
				<td>曲&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;谱</td>
				<td>
					<div  style="border:2px dashed #CCCCCC;min-height:150px; width:700px" id="file2">
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
			<table>
			  <tr>
				<td>录音录像</td>
				<td>
					<div  style="border:2px dashed #CCCCCC;min-height:150px; width:700px" id="file3">
					</div>
					</td>
				  </tr>
				  <tr>
				 <td style="width:50px"></td>
				  <td>
					<input type="file" multiple id="ssi-upload3"/>
					</td>
				  </tr>
			  </tr>
			</table>
			<!--上传结束 -->
		 </div>
	
	</body>
	<script type="text/javascript">
		function save(state){

			var url = "${basePath}action/song/save/"+state;
			$('#song').form('submit', {
				url: url,
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid)
						return isValid;	// return false will stop the form submission
				},
				success: function(data){
					if(data!=0){
						$.messager.alert('消息','保存成功','info',function(){ window.location.href="${basePath}action/song/editpage/"+data;});
					}else{
						$.messager.alert('消息','保存失败','info');
					}
				}
			});
		}
		var num=0;
		
		function addHtml(){
			num++;
			var html='<table id="html_'+num+'"  style="margin-top:8px">'+
				  '<tr>'+
					'<td style="width: 60px;"><input type="checkbox" name="cs" value="html_'+num+'"></td>'+
					'<td ><span class="textbox" style="width: 698px; height: 88px;"><textarea name="jj" id="jj" class="textbox-text" autocomplete="off" tabindex="" placeholder="" style="text-align: start; margin: 0px; height: 80px; width: 690px;" ></textarea></span></td>'+
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
			var html='<table id="html_'+num1+'"  style="margin-top:8px">'+
				  '<tr>'+
					'<td style="width: 60px;"><input type="checkbox" name="gcc" value="html_'+num1+'"></td>'+
					'<td ><span class="textbox" style="width: 698px; height: 88px;"><textarea name="gc" id="gc" class="textbox-text" autocomplete="off" tabindex="" placeholder="" style="text-align: start; margin: 0px; height: 80px; width: 690px;"  ></textarea></span></td>'+
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
				var seq1='ICH';; //数据字库编码
				var seq2='${columns.parent.no}';//项目分类
				var seq3=no//项目申报编码
				var seq4='03';//具体项目内容编码 01项目信息 02传承人 03曲目库 04 出版物 05专项活动
				var seq5=fg; //风格
				sequence=seq1+'_'+seq2+'_'+seq3+'_'+seq4+'_'+seq5;
				return sequence;
		}
	</script>
	<script src="${basePath}component/ssi-up/js/ssi-uploader.js"></script>
	<script src="${basePath}component/ssi-up/js/upload.js"></script>
	<div id="inher" class="easyui-window" title="传承人" closed="true" style="width:700px;height:500px;padding:5px;" minimizable="false" data-options="inline:true">
		<iframe src="${basePath}action/inheritor/search/CN/1" width="100%" height="100%" frameborder="no" border="0"></iframe>
	</div>
</html>