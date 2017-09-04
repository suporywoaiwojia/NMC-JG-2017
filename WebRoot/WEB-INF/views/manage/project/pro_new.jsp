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
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/styles-left.css">
	
		<link rel="stylesheet" type="text/css" href="${basePath}component/ssi-up/css/style.css">
		<link rel="stylesheet" href="${basePath}component/ssi-up/css/ssi-uploader.css"/>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>

		


	</head>
	  
	<body>

		 <div class="easyui-panel"   style="width:100%;padding:15px">
		   <div style="padding:0 0 10px;">
		  	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:10%;" onclick="save('1')">暂存</a>	
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:10%;" onclick="save('2')">保存</a>	
		  </div> 

		   
			<div style="margin:10px 0">
				<form method="post" id="project">
				<input type="hidden" name="l_type" value="CN" />
				<input  type="hidden" name="column_no" id="column_no" />
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
						<td></td>
						<td></td>
					</tr>
					</c:if>
				  <tr>
				
					<td>项目名称:</td>
					<td><input class="easyui-textbox" style="width:300px;height:30px" name="name" id="name" data-options="required:true,validType:'length[0,50]',missingMessage:'项目名称不能为空',invalidMessage:'长度不能超出50个字符'"></td>
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
					  <select class="easyui-combobox" style="width:90px;height:30px; text-align:center;" name="level.id" id="level" data-options="editable:false,panelHeight:'auto'">
						<c:forEach items="${l_list}" var="l_list" >
							<option value="${l_list.id}" bm='${l_list.bm}'>${l_list.name}</option>
						 </c:forEach>
					  </select>
					  <c:forEach items="${l_list}" var="l_list" >
							<input type="hidden" id="jb_${l_list.id}" value="${l_list.bm}" />
						 </c:forEach>
					  &nbsp;&nbsp;
					  <label><input name="leveltype" type="radio" id="leveltype" style="margin-left:20px;" value="1" checked="checked" />
					  独立</label>
					  <label><input name="leveltype" id="leveltype" type="radio" value="2" />扩充</label>
					</td>
				  </tr>
				  <tr>
					<td>批准年度:</td>
					<td>
					<input class="easyui-numberspinner" value="1990" data-options="required:true,min:1990,max:2050"  style="width:300px;height:30px;" id="year" name="year" ></input>
					 
					</td>
					<td style="padding-left:40px;">项目编号:</td>
					<td><input class="easyui-textbox" style="width:300px;height:30px" name="no" id="no" data-options="required:true,validType:'length[0,25]',missingMessage:'项目编号不能为空',invalidMessage:'长度不能超出25个字符'"></td>
				  </tr>
				  <tr>
					<td>所属类别:</td>
					<td>
						 <select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="column.id" id="column" data-options="editable:false,panelHeight:'auto'">
						<c:forEach items="${c_list}" var="c_list" >
							
							<option value="${c_list.id}">${c_list.name}</option>
						 </c:forEach>
					  </select>
					  <!--获取类别编码 -->
					  <c:forEach items="${c_list}" var="c_list" >
							<input type="hidden" id="lb_${c_list.id}" value="${c_list.no}" data-pid='${c_list.parent.no}' />
						 </c:forEach>
					</td>
					<td style="padding-left:40px;">保护单位:</td>
					<td><input class="easyui-textbox" style="width:300px;height:30px" name="employer" id="employer" data-options="required:false,validType:'length[0,100]',invalidMessage:'长度不能超出100个字符'"></td>
				  </tr>
				</table>
				<table>
				  <tr>
					<td>项目简介:</td>
					<td><input class="easyui-textbox" style="width:700px;height:90px" name="summary" id="summary" data-options="required:false,validType:'length[0,500]',invalidMessage:'长度不能超出500个字符',multiline:true"></td>
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
				<td>申报文本</td>
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
				<td>申&nbsp;&nbsp;报&nbsp;&nbsp;片</td>
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
			</div>
		
		 
	</body>
		<script>
	
	
	</script>
	<script type="text/javascript">
		function save(state){
			var url = "${basePath}action/project/save/"+state;
			var lb=$('#column').combobox('getValue');
			var lb_no=$('#lb_'+lb).val();//项目分类
			$('#column_no').val(lb_no)
			$('#project').form('submit', {
				url: url,
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid)
						return isValid;	// return false will stop the form submission
				},
				success: function(data){
					if(data!=0){
						$.messager.alert('消息','保存成功','info',function(){ window.location.href="${basePath}action/project/editpage/"+data;});
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
	</script>
	<script src="${basePath}component/ssi-up/js/ssi-uploader.js"></script>
	<script src="${basePath}component/ssi-up/js/upload.js"></script>

</html>