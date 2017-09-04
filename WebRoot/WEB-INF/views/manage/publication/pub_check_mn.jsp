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
		<link rel="stylesheet" href="${basePath}component/ssi-up/css/ssi-uploader_tm.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0_tm/css/styles-left.css">
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0_tm/js/jquery.easyui.min.js"></script>
		<script type="text/javascript">
			$(function(){
				
				if('${pub.approve_id}'!='')
					$('#approve_id').combobox('select','${pub.approve_id}-${pub.approve_name}');
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
		<input type="hidden" name="state" value="2" id="state" />
		<input type="hidden" id="ch_id" value="${pub.ch_id}" name="ch_id"/>
			<table class="data">
				 <c:if test="${columns.approve =='1'}">
				<tr>
				  <td> ᠬᠢᠨᠠᠨ ᠰᠢᠯᠭᠠᠭᠴᠢ:</td>
					<td>
						<select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="approve_id" id="approve_id" data-options="editable:false,panelHeight:'auto'">
							<c:forEach items="${approveUser}" var="approveUser" >
							<option value="${approveUser.loginId}-${approveUser.userName}">${approveUser.userName}</option>
							 </c:forEach>
							  <option value="3">ᠨᠡᠪᠲᠡᠷᠡᠬᠦ</option>
							 <option value="4">ᠪᠤᠴᠠᠭᠠᠬᠤ</option>
				  		</select>					</td>
					<td></td>
					<td></td>
				</tr>
				</c:if>
			  <tr>
			    <td> ᠨᠡᠷ᠎ᠡ:</td>
			
				<td><input class="easyui-textbox" style="width:300px;height:30px" name="name" id="name"  value="${pub.name}"  data-options="required:true,validType:'length[0,50]',missingMessage:'名称不能为空',invalidMessage:'长度不能超出50个字符'"></td>
				<td style="padding-left:20px;"> ᠠᠩᠭᠢᠯᠠᠯ:</td>
				<td>
					<select class="easyui-combobox" name="type" id='type' data-options="editable:false,panelHeight:'auto'" style="width:300px;height:30px; text-align:left;" >
						 <option value="01" > ᠲᠤᠰᠬᠠᠢ ᠪᠦᠲᠦᠭᠡᠯ</option>
						<option value="02" > ᠥᠭᠦᠯᠡᠯ</option>
						<option value="03" > ᠠᠶ᠎ᠠ ᠶᠢᠨ ᠲᠡᠭᠦᠪᠦᠷᠢ</option>
						<option value="04" > ᠴᠣᠮᠣᠭ</option>
						<option value="05" > ᠮᠡᠳᠡᠭᠡᠯᠡᠬᠦ</option>
                  </select>				</td>
			  </tr>
			  <tr>
			    <td> ᠵᠣᠬᠢᠶᠠᠭᠴᠢ:</td>
			    <td><input class="easyui-textbox" style="width:245px;height:30px" id="zz" name="zz" readonly="true" value="${pub.inheritor.name}" data-options="required:true,missingMessage:'作者不能为空'"><a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="findInher();" id="findInher">ᠯᠠᠪᠯᠠᠬᠤ</a>
					<input type="hidden" name="inheritor.id" id="inheritor" value="${pub.inheritor.id}"/>				</td>
			    <td style="padding-left:20px;"> ᠪᠤᠰᠤᠳ ᠵᠣᠬᠢᠶᠠᠭᠴᠢ:</td>
			    <td><input class="easyui-textbox" style="width:300px;height:30px" name="author_other" id="author_other" value="${pub.author_other}" data-options="required:false,validType:'length[0,50]',invalidMessage:'长度不能超出50个字符'">				</td>
		      </tr>
			  <tr>
			    <td> ᠬᠠᠷᠢᠶᠠᠯᠠᠬᠤ ᠲᠥᠰᠥᠯ:</td>
			  	<td><select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="project.id" id="project" data-options="editable:false,panelHeight:'auto'">
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
				<select class="easyui-combobox" style="width:300px;height:30px; text-align:left;" name="publishing.id" id="publishing" data-options="editable:false,panelHeight:'auto'">
						<c:forEach items="${pub_list}" var="pub_list" >
							<option value="${pub_list.id}">${pub_list.name}</option>
					   </c:forEach>
				 	 </select>				</td>
			  </tr>
			  <tr>
			    <td> ᠬᠡᠪᠯᠡᠭᠰᠡᠨ ᠤᠳᠠᠭ᠎ᠠ:</td>
			  	<td colspan="3">
				<input class="easyui-numberspinner"  data-options="required:true,min:1940,max:2050"  style="width:100px;height:30px;" id="pubyear" name="pubyear" value="${pub.pubyear}"></input>
				ᠣᠨ ᠤ
				<input class="easyui-numberspinner"  data-options="required:true,min:1,max:12"  style="width:100px;height:30px;" id="pubmonth" name="pubmonth"  value="${pub.pubmonth}">
				</input>
				ᠰᠠᠷ᠎ᠠ ᠳᠤ ᠬᠡᠪᠯᠡᠭᠰᠡᠨ&nbsp;
				<input class="easyui-numberspinner"   data-options="required:true,min:1940,max:2050"  style="width:100px;height:30px;" id="pubyear1" name="pubyear1"  value="${pub.pubyear1}"></input>
				ᠣᠨ ᠤ
				<input class="easyui-numberspinner"   data-options="required:true,min:1,max:12"  style="width:100px;height:30px;" id="pubmonth1" name="pubmonth1"  value="${pub.pubmonth1}">
				</input>
ᠰᠠᠷ᠎ᠠ  
<input class="easyui-numberspinner"   data-options="required:true,min:1,max:9999"  style="width:100px;height:30px;" id="frequency" name="frequency"  value="${pub.frequency}"></input>
ᠳᠤ ᠤᠳᠠᠭ᠎ᠠ ᠬᠡᠪᠯᠡᠭᠰᠡᠨ</td>
		  	  </tr>
			   <tr>
			     <td> ᠨᠣᠮ ᠤᠨ ᠪᠠᠷᠢᠮᠵᠢᠶ᠎ᠠ ᠳ᠋ᠤᠭᠠᠷ:</td>
			    <td><input class="easyui-textbox" style="width:300px;height:30px" id="isbn"  name="isbn" value="${pub.isbn}"  data-options="required:true,validType:'length[0,100]',missingMessage:'标准书号不能为空',invalidMessage:'长度不能超出100个字符'">					</td>
			    <td style="padding-left:20px;">ᠲᠦᠯᠬᠢᠭᠦᠷ ᠦᠭᠡ:</td>
			    <td><input class="easyui-textbox" style="width:300px;height:30px" name="keyword" id="keyword" value="${pub.keyword}" data-options="required:false,validType:'length[0,100]',invalidMessage:'长度不能超出100个字符'">				</td>
		      </tr>
			  <tr id="bdurl">
			     <td>ᠮᠡᠳᠡᠭᠡᠯᠡᠬᠦ:</td>
			     <td colspan="3"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:220px;" onclick="openweb()">open</a>	</td>
		      </tr>
			</table>
			<div id="filedata">
					<c:forEach items="${cf_list_4}" var="list4"  varStatus="status">
						<div id='f4_data_${status.index}'>
							<input type="hidden" name="savename" value="${list4.saveName}"/>
							<input type="hidden" name="savepath" value="${list4.savePath}"/>
							<input type="hidden" name="httppath" value="${list4.httpPath}"/>
							<input type="hidden" name="filetype" value="04"/>
							<input type="hidden" name="view" value="${list4.views}" />
							<input type="hidden" name="filename" value="${list4.fileName}.${list4.fileExc}"/>
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
		  <div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left: 300px solid #ddd; border-right: 300px solid #ddd; text-align: center;" class="mongol_div">     
					ᠳᠠᠭᠠᠯᠲᠠ ᠰᠤᠷᠠᠭ
					</div>
		 <!--照片上传 -->
				
				 <table  width="100%">
				  <tr>
					<td width="16%"  class="text_title"> ᠭᠠᠳᠠᠷ ᠤᠨ ᠵᠢᠷᠤᠭ</td>
					<td width="84%"  >
						<div  style="border:2px dashed #CCCCCC;min-height:150px" id="file4">
							<c:forEach items="${cf_list_4}" var="list4"  varStatus="status">
								<table class="uptable" id="file_${status.index}"><tbody><tr><td class="ssi-upImgTd">
									<a href="#" onclick="view('1','${list4.httpPath}')"><img class="imgToUpload" src="${basePath}${list4.httpPath}" style="width:140px; height:128px"></a>
								</td></tr>
								<tr><td><span class="ssi-statusLabel  success" data-status="">${list4.fileName}.${list4.fileExc}</span>
								</td></tr>
								<tr><td><span style=" margin-right: 6px">ᠨᠢᠭᠤᠴᠠᠯᠠᠬᠤ<input type="checkbox" name="baomi" value="1" onclick="setview(this,'f4_data_${status.index}')"  <c:if test="${list4.views =='0'}">checked="checked" </c:if>></span><!--<a href="#" onclick="delfile('file_${status.index}','f4_data_${status.index}')">删除</a> -->
								
								</td>
								</tr>
								</tbody>			</table>
								
					
							</c:forEach>
							
						</div>
					</td>
					
				  </tr>
				  <!--<tr>
				  <td style="width:50px"></td>
				  <td>
					<input type="file" multiple id="ssi-upload4"/>
					</td>
				  </tr> -->
				</table>
				<!--申报文本上传 -->
				 <table width="100%">
				  <tr>
					<td width="16%"  class="text_title"> ᠲᠸ᠋ᠺᠰᠲ ᠪᠠᠶᠢᠷᠢᠯᠠᠭᠤᠯᠬᠤ</td>
					<td width="84%" >
						<div  style="border:2px dashed #CCCCCC;min-height:150px" id="file2">
					
							<c:forEach items="${cf_list_2}" var="list2"  varStatus="status">
								<table class="uptable" id="file2_${status.index}"><tbody><tr><td class="ssi-upImgTd">
									
									<a href="#" onclick="view('2','${list2.httpPath}')"><div class="document" href="test.mov" filetype="${list2.fileExc}"><span class="fileCorner"></span></div></a>
								</td></tr>
								<tr><td><span class="ssi-statusLabel  success" data-status="">${list2.fileName}.${list2.fileExc}</span>
								</td></tr>
								<tr><td><span style=" margin-right: 6px">ᠨᠢᠭᠤᠴᠠᠯᠠᠬᠤ<input type="checkbox" name="baomi" value="1"   <c:if test="${list2.views =='0'}">checked="checked" </c:if>  onclick="setview(this,'f2_data_${status.index}')""></span><!--<a href="#" onclick="delfile('file2_${status.index}','f2_data_${status.index}')">删除</a> -->
								
							
								</td>
								</tr>
								</tbody>			</table>
								
					
							</c:forEach>
						
						</div>
					</td>
					
				  </tr>
				 <!-- <tr>
				  <td style="width:50px"></td>
				  <td>
					<input type="file" multiple id="ssi-upload2"/>
					</td>
				  </tr> -->
				</table>
				<!--申报片 -->
				<table width="100%">
				  <tr>
					<td width="16%"  class="text_title"> ᠳᠠᠭᠤ ᠳᠦᠷᠰᠦ ᠪᠢᠴᠢᠯᠭᠡ</td>
					<td width="84%"  >
						<div  style="border:2px dashed #CCCCCC;min-height:150px" id="file3">
							<c:forEach items="${cf_list_3}" var="list3"  varStatus="status">
								<table class="uptable" id="file3_${status.index}"><tbody><tr><td class="ssi-upImgTd">
									<a href="#" onclick="view('3','${list3.httpPath}')"><div class="document" href="test.mov" filetype="${list3.fileExc}"><span class="fileCorner"></span></div></a>
								</td></tr>
								<tr><td><span class="ssi-statusLabel  success" data-status="">${list3.fileName}.${list3.fileExc}</span>
								</td></tr>
								<tr><td><span style=" margin-right: 6px">ᠨᠢᠭᠤᠴᠠᠯᠠᠬᠤ<input type="checkbox" name="baomi" value="1" onclick="setview(this,'f3_data_${status.index}')" <c:if test="${list3.views =='0'}">checked="checked" </c:if>></span><!--<a href="#" onclick="delfile('file3_${status.index}','f3_data_${status.index}')">删除</a> -->
								
								
								</td>
								</tr>
								</tbody>			</table>
								
					
							</c:forEach>
						</div>
					</td>
					
				  </tr>
				  <!--<tr>
				  <td style="width:50px"></td>
				  <td>
					<input type="file" multiple id="ssi-upload3"/>
					
					</td>
				  </tr> -->
				</table>
				   <div style="padding:30px 0 10px;" class="mongol_div">
		  
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:220px" onclick="save()">ᠬᠠᠳᠠᠭᠠᠯᠠᠬᠤ</a>	
					 
				  </div>   
		 </div>
		 <form method="get" id="pub_1"></form>
	</body>
	<script type="text/javascript">
		function save(state){

			var url = "${basePath}action/check/pub/update/";
			var approve=$('#approve_id').combobox('getValue');
			if(approve=='3'){
				$('#approve_id').combobox('setValue','')
				$('#state').val("3");
			
			}else if(approve=='4'){
				$('#approve_id').combobox('setValue','')
				$('#state').val("4");
			}
			//$("#project").submit();
			var isValid = $("#publication").form('validate');
			if(isValid){
				$.ajax({type:"POST", url:url,data:$('#publication').serialize(),dataType:"text", success:function(datas) {
				
						if(datas=='1'){
							$.messager.alert('ᠰᠠᠨᠠᠭᠤᠯᠤᠮᠵᠢ','!',"info", function () {success();});
						}else if(datas=='0'){
							$.messager.alert('ᠰᠠᠨᠠᠭᠤᠯᠤᠮᠵᠢ','  !');
						}
					}
				});
			}
		}
		function success(){
			var url="${basePath}action/check/pub/list/MN/1";
			$("#pub_1").attr("action",url);
		
			$("#pub_1").submit();
		}
	</script>
	<script type="text/javascript">
		
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
				var seq1='ICH'; //数据字库编码
				var seq2='${columns.parent.no}';//项目分类
				var seq3=no//项目申报编码
				var seq4='04';//具体项目内容编码 01项目信息 02传承人 03曲目库 04 出版物 05专项活动
				var seq5=$('#type').combobox('getValue'); //级别
				sequence=seq1+'_'+seq2+'_'+seq3+'_'+seq4+'_'+seq5;
				return sequence;
		}
		$('#type').combobox({
		onSelect: function(record){
			if(record.value=='05'){
			
				$('#bdurl').show();	
				$('#inheritor').attr("disabled",true); 
				$('#publishing').combobox('disable');
				$('#findInher').splitbutton('disable');
				$('#zz').textbox({
					required: false
				});
				$('#isbn').textbox({
					required: false
				});
			}else{
				$('#bdurl').hide();
				$("#inheritor").attr('disabled',false); 
				if($('#publishing').attr('disabled')=='disabled'){
					$('#publishing').combobox('enable');
					$('#findInher').splitbutton('enable');
				}
				
				$('#zz').textbox({
					required: true
				});
				$('#isbn').textbox({
					required: true
				});
			}
		}
	});
	function openweb(){
			window.parent.openbd('${pub.bdurl}');
	}
	</script>
	<div id="inher" class="easyui-window" title=" " closed="true" style="width:700px;height:500px;top:30%;padding:5px;" minimizable="false" data-options="inline:true">
		<iframe src="${basePath}action/inheritor/search/MN/1" width="100%" height="100%" frameborder="no" border="0"></iframe>
	</div>

</html>