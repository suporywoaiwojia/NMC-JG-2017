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
		
		<title>专项活动信息</title>
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
		</script>
	
	</head>
	  
	<body>
		 <div class="easyui-panel"   style="width:100%;padding:15px">
		  <div style="padding:0 0 10px;">
		  	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:10%;" onclick="save('1')">暂存</a>	
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:10%;" onclick="save('2')">保存</a>	
			 
		  </div>      
		  <div style="margin:10px 0">
		  <form method="post" id="activities">
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
			
				<td>活动名称:</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="name" id="name" data-options="required:true,validType:'length[0,50]',missingMessage:'活动名称不能为空',invalidMessage:'长度不能超出50个字符'"></td>
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
			    <td><input class="easyui-textbox" style="width:300px;height:30px" id="sponsor" name="sponsor" data-options="required:false,validType:'length[0,100]',invalidMessage:'长度不能超出100个字符'">
								</td>
			    <td style="padding-left:40px;">协/承办方:</td>
			    <td><input class="easyui-textbox" style="width:300px;height:30px" name="sponsor_other" id="sponsor_other" data-options="required:false,validType:'length[0,100]',invalidMessage:'长度不能超出100个字符'">				</td>
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
				<input class="easyui-numberspinner" value="0" data-options="required:true,min:0,max:999999"  style="width:300px;height:30px;" id="peoplenum" name="peoplenum" ></input>
				</td>
			  </tr>
			  <tr>
			  	<td>时间地点:</td>
					
				<td colspan="3">
				<input name="holedate_s" id="holedate_s" class="easyui-datebox" style="width:150px" data-options="sharedCalendar:'#cc',formatter:myformatter,parser:myparser,editable:false" />&nbsp;&nbsp;至&nbsp;&nbsp;
				<input name="holedate_e" id="holedate_e" class="easyui-datebox" style="width:150px" data-options="sharedCalendar:'#cc',formatter:myformatter,parser:myparser,editable:false" />
				
				&nbsp;&nbsp;在&nbsp;&nbsp;<input class="easyui-textbox" style="width:310px;height:30px" id="holdadd"  name="holdadd" data-options="required:false,validType:'length[0,100]',invalidMessage:'长度不能超出100个字符'"> &nbsp;&nbsp;举办
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
			<div id="filedata"></div>
			</form>
		  </div>
		 <div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left: 300px solid #ddd; border-right: 300px solid #ddd; text-align: center;">     
					附件信息
			</div>
		 <table>
			  <tr>
				<td>照片上传</td>
					<td>
					<div  style="border:2px dashed #CCCCCC;min-height:150px; width:700px" id="file1">
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
			<table>
			  <tr>
				<td>文本上传</td>
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
					<div  style="border:2px dashed #CCCCCC;min-height:150px; width:700px" id="file5">
					</div>
					</td>
				  </tr>
				  <tr>
				 <td style="width:50px"></td>
				  <td>
					<input type="file" multiple id="ssi-upload5"/>
					</td>
				  </tr>
			  </tr>
			</table>
		 </div>
		
	
	</body>
	<script type="text/javascript">
		function save(state){

			var url = "${basePath}action/act/save/"+state;
			$('#activities').form('submit', {
				url: url,
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid)
						return isValid;	// return false will stop the form submission
				},
				success: function(data){
					if(data!=0){
						$.messager.alert('消息','保存成功','info',function(){ window.location.href="${basePath}action/act/editpage/"+data;});
					}else{
						$.messager.alert('消息','保存失败','info');
					}
				}
			});
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
	<script type="text/javascript">
		function findInher(index){
			$('#inher_f').attr('src','${basePath}action/inheritor/search_act/CN/1/'+index);
			$('#inher').window('open');
		}
	</script>
	<div id="inher" class="easyui-window" title="传承人" closed="true" style="width:700px;height:500px;padding:5px;" minimizable="false" data-options="inline:true">
		<iframe width="100%" height="100%" frameborder="no" border="0" id="inher_f"></iframe>
	</div>
</html>