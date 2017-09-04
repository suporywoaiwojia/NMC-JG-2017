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
		
		<title>出版物信息</title>
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/styles-left.css">
		<link rel="stylesheet" type="text/css" href="${basePath}component/ssi-up/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}component/ssi-up/css/style.css">
		<link rel="stylesheet" href="${basePath}component/ssi-up/css/ssi-uploader.css"/>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		
	
	</head>
	  
	<body>
		<div class="easyui-layout" style="width:100%; height:719px" title="出版物信息采集表">
		<div  data-options="region:'center',split:true" title="出版物信息" style="width:60%">
		  <div style="padding:0 0 10px;">
		  	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:10%;" onclick="save('1')">暂存</a>	
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:10%;" onclick="save('2')">保存</a>	
		  </div>      
		  <div style="margin:10px 0">
		  <form method="post" id="publication">
		  <input type="hidden" name="l_type" value="CN" />
			<table class="data">
				 <c:if test="${columns.approve =='1'}">
				<tr>
					<td>审核人员:</td>
					<td>
						<select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="approve_id" id="approve_id" data-options="editable:false">
							<c:forEach items="${approveUser}" var="approveUser" >
							<option value="${approveUser.id}">${approveUser.userName}</option>
							 </c:forEach>
				  		</select>					</td>
					<td></td>
					<td></td>
				</tr>
				</c:if>
			  <tr>
			
				<td>名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:</td>
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="name" id="name" ></td>
				<td style="padding-left:40px;">类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</td>
				<td>
					<select class="easyui-combobox" name="type" id='type' data-options="editable:false" style="width:300px;height:30px; text-align:left;" >
						 <option value="01" >专著</option>
						<option value="02" >论文</option>
						<option value="03" >曲集</option>
						<option value="04" >专辑</option>
						<option value="05" >报道</option>
                  </select>				</td>
			  </tr>
			  <tr>
			    <td>作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者:</td>
			    <td><input class="easyui-textbox" style="width:245px;height:30px" id="zz" readonly="true"><a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="findInher();">查找</a>
					<input type="hidden" name="inheritor.id" id="inheritor"/>				</td>
			    <td style="padding-left:40px;">其他作者:</td>
			    <td><input class="easyui-textbox" style="width:300px;height:30px" name="author_other" id="author_other" >				</td>
		      </tr>
			  <tr>
			  	<td>所属项目:</td>
					
				<td><select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="project.id" id="project" data-options="editable:false">
						<c:forEach items="${p_list}" var="p_list" >
							<option value="${p_list.id}">${p_list.name}</option>
					   </c:forEach>
				 	 </select>
					  <!--附件项目类别编码 -->
					 <c:forEach items="${p_list}" var="p_list" >
						<input type="hidden" id="no_${p_list.id}" value="${p_list.no}" />
					</c:forEach>
					 </td>
			  	<td style="padding-left:40px;">出版机构:</td>
				<td >
				<select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="publishing.id" id="publishing" data-options="editable:false">
						<c:forEach items="${pub_list}" var="pub_list" >
							<option value="${pub_list.id}">${pub_list.name}</option>
					   </c:forEach>
				 	 </select>				</td>
			  </tr>
			  <tr>
			  	<td>出版版次:</td>
					
				<td colspan="3">
				<input class="easyui-numberspinner" value="1990" data-options="required:true,min:1940,max:2050"  style="width:100px;height:30px;" id="pubyear" name="pubyear" ></input>年
				<input class="easyui-numberspinner" value="1" data-options="required:true,min:1,max:12"  style="width:100px;height:30px;" id="pubmonth" name="pubmonth" ></input>月出版 &nbsp; &nbsp; &nbsp; &nbsp &nbsp; &nbsp &nbsp; &nbsp
				<input class="easyui-numberspinner" value="1990" data-options="required:true,min:1940,max:2050"  style="width:100px;height:30px;" id="pubyear1" name="pubyear1" ></input>年
				<input class="easyui-numberspinner" value="1" data-options="required:true,min:1,max:12"  style="width:100px;height:30px;" id="pubmonth1" name="pubmonth1" ></input>月
				第<input class="easyui-numberspinner" value="1" data-options="required:true,min:1,max:9999"  style="width:100px;height:30px;" id="frequency" name="frequency" ></input>次
				印刷
				</td>
		  	  </tr>
			   <tr>
			    <td>标准书号</td>
			    <td><input class="easyui-textbox" style="width:300px;height:30px" id="isbn"  name="isbn">
					</td>
			    <td style="padding-left:40px;">关键字:</td>
			    <td><input class="easyui-textbox" style="width:300px;height:30px" name="keyword" id="keyword" >				</td>
		      </tr>
			</table>
			<div id="filedata"></div>
			</form>
		  </div>
		  <div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left: 300px solid #ddd; border-right: 300px solid #ddd; text-align: center;">附件信息</div>
		  <table>
			  <tr>
				<td>封面图片</td>
					<td>
					<div  style="border:2px dashed #CCCCCC;min-height:150px; width:700px" id="file4">
					</div>
					</td>
				  </tr>
				  <tr>
				  <td style="width:50px"></td>
				  <td>
				 <input type="file" multiple id="ssi-upload4"/>
					
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
				<td>曲集上传</td>
				<td >
					<div  style="border:2px dashed #CCCCCC;min-height:150px; width:700px" id="file3">
					</div>
					</td>
				  </tr>
				  <tr>
				 <td style="width:50px"></td>
				  <td>
				  <input type="file" multiple id="ssi-upload3"/>
				<!--	<button class="button success">选择文件</button> -->
					</td>
				  </tr>
			  </tr>
			</table>
		 </div>
		 <div data-options="region:'east',split:true" title="相关链接" style="width:40%;">
		  <div style="padding:20px 0;">相关链接：</div> 
		  <div style="margin:0 0 10px;">
			<div style="width:80px; height:30px; text-align:right; line-height:30px; float:left;">传承人信息:</div>
			<div class="easyui-textbox" style="width:680px;height:30px; float:left;"></div>
		  </div> 
		  <div style="margin:0 0 10px;">
			<div style="width:80px; height:30px; text-align:right; line-height:30px; float:left;">曲目信息:</div>
			<div class="easyui-textbox" style="width:680px;height:30px; float:left;"></div>
		  </div>
		  <div style="margin:0 0 10px;">
			<div style="width:80px; height:30px; text-align:right; line-height:30px; float:left;">出版物信息:</div>
			<div class="easyui-textbox" style="width:680px;height:30px; float:left;"></div>
		  </div>
		  <div style="margin:0 0 10px;">
			<div style="width:80px; height:30px; text-align:right; line-height:30px; float:left;">专项活动信息:</div>
			<div class="easyui-textbox" style="width:680px;height:30px; float:left;"></div>
		  </div>
		 </div>     
		</div>
	
	</body>
	<script type="text/javascript">
		function save(state){

			var url = "${basePath}action/pub/save/"+state;
			$("#publication").attr("action",url);
			$("#publication").submit();
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
				var xm=$('#project').combobox('getValue');	
				var no=$('#no_'+xm).val();
				var seq1='${columns.parent.parent.no}'; //数据字库编码
				var seq2='${columns.parent.no}';//项目分类
				var seq3=no//项目申报编码
				var seq4='04';//具体项目内容编码 01项目信息 02传承人 03曲目库 04 出版物 05专项活动
				var seq5=$('#type').combobox('getValue'); //级别
				sequence=seq1+'_'+seq2+'_'+seq3+'_'+seq4+'_'+seq5;
				return sequence;
		}
	</script>
	<script src="${basePath}component/ssi-up/js/ssi-uploader.js"></script>
	<script src="${basePath}component/ssi-up/js/upload.js"></script>
	<script type="text/javascript">
	/*$(function(){
		$('.button success').click(function(){
			
		});
	})*/
	</script>
	<div id="inher" class="easyui-window" title="传承人" closed="true" style="width:700px;height:500px;padding:5px;" minimizable="false" data-options="inline:true">
		<iframe src="${basePath}action/inheritor/search/CN/1" width="100%" height="100%" frameborder="no" border="0"></iframe>
	</div>
</html>