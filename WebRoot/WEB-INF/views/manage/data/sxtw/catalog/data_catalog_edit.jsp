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
		
		<title>传承人信息</title>
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
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:10%;" onclick="save()">保存</a>	
			 
		  </div>      
		  <div style="margin:10px 0">
		  <form method="post" id="web_form">
			<input type="hidden" id="id" value="${catalog.id}" name="id"/>
			<table  align="center">
               <!--审核页面-->
             <c:if test="${approveState==1}">
                <tr>
                 <td>审核结果:</td>
                    <td>
                 <select id="state" class="easyui-combobox" name="state" style="width:300px;height:30px; text-align:center;">
                    <option value="0">无</option>
                    <option value="2">待审</option>
                    <option value="3">通过</option>
                    <option value="4">退回</option>
                  </select> 
                </td>
               </tr>
                <tr>
                 <td >审核意见:</td>
                <td colspan="5"><input id="approveOpinion" name="approveOpinion" class="easyui-textbox" 
                 data-options="multiline:true" style="width:1100px;height:100px" /></td>
                </tr>
            </c:if>
           <!--end-->
           </table>
           <table align="center" class="data">
                 <tr>
               <div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left: 300px solid #ddd; border-right: 300px solid #ddd; text-align: center;">     
                                待审核信息
                        </div>
               </tr>
			  <tr>
				<td>目录名称:</td>
				<td><input class="easyui-textbox" style="text-align:center;width:300px;height:30px" name="name" id="name" value="${catalog.name}" data-options="required:false,validType:'length[0,500]',invalidMessage:'长度不能超出500个字符',multiline:true"  <c:if test="${approveState==1}" > readonly="readonly" </c:if> />
                </td>
                <td style="padding-left:40px;">页码范围:</td>
				<td><input class="easyui-textbox" style="text-align:center;width:300px;height:30px" name="pageRange" id="pageRange" value="${catalog.name}" data-options="required:false,validType:'length[0,500]',invalidMessage:'长度不能超出500个字符',multiline:true"  <c:if test="${approveState==1}" > readonly="readonly" </c:if> />
                </td>
              <td style="padding-left:40px;">所属资料:</td>
				<td><input class="easyui-textbox" style="text-align:center;width:300px;height:30px" value="${item.name}"  readonly="readonly" data-options="required:false,validType:'length[0,500]',invalidMessage:'长度不能超出500个字符',multiline:true">
                 <input type="hidden" name="catalogItem.id"  id="catalogItem" value="${item.id}"/>
                </td>
              </tr>
			</table>
            <!--上传后的返回的具体附件信息，附加到下面的容器中-->
			<div id="filedata">
            <c:forEach items="${listContents}" var="contentFile"  varStatus="status">
						<div id='f1_data_${status.index}'>
							<input type="hidden" name="savename" value="${contentFile.saveName}"/>
							<input type="hidden" name="savepath" value="${contentFile.savePath}"/>
							<input type="hidden" name="httppath" value="${contentFile.httpPath}"/>
							<input type="hidden" name="filetype" value="01"/>
							<input type="hidden" name="view" value="${contentFile.views}" />

							<input type="hidden" name="filename" value="${contentFile.fileName}.${contentFile.fileExc}"/>
						</div>
			</c:forEach>
            
            </div>
			</form>
		  </div>
		  
		 <div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left: 300px solid #ddd; border-right: 300px solid #ddd; text-align: center;">     
					附件信息
			</div>
		  <table>
			  <tr>
				<td>扫描附件</td>
					<td>
                         <div  style="border:2px dashed #CCCCCC;min-height:700px; width:600px"" id="file1">
							<c:forEach items="${listContents}" var="contentFile"  varStatus="status">
								<table class="uptable" id="file_${status.index}"><tbody><tr><td class="ssi-upImgTd">
									<img class="imgToUpload" src="${basePath}${contentFile.httpPath}" style="width:140px; height:128px">
								</td></tr>
								<tr><td><span class="ssi-statusLabel  success" data-status="">${contentFile.fileName}.${contentFile.fileExc}</span>
								</td></tr>
								<tr><td>
                                <c:if test="${approveState==0}">
                                <span style=" margin-right: 6px">保密<input type="checkbox" name="baomi" value="1" onclick="setview(this,'f1_data_${status.index}')"  <c:if test="${contentFile.views =='0'}">checked="checked" </c:if>></span><a href="#" onclick="delfile('file_${status.index}','f1_data_${status.index}')">删除</a>
								</c:if>
								</td></tr>
								</tbody>			
                                </table>
								
					
							</c:forEach>
							
						</div>
					</td>
             <td>转换文本</td>
              <td>
					<div  style="border:2px dashed #CCCCCC;min-height:700px; width:600px" id="file2">
					</div>
			  </td>
				  </tr>
				  <tr>
				  <td style="width:50px"></td>
				  <td>
                   <c:if test="${approveState==0}">
					<input type="file" multiple id="ssi-upload"/>
                    </c:if>
					</td>
			       <td style="width:50px"></td>
				  <td>
                   <c:if test="${approveState==0}">
					<input type="file" multiple id="ssi-upload2"/>
                    </c:if>
					</td>
				  </tr>
			</table>
			
			<!--上传结束 -->
		</div>
	
	</body>
	<script type="text/javascript">
		function save(){
			var url = "${basePath}action/manage/data/catalog/save";
			
			$('#web_form').form('submit', {
				url: url,
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid)
						return isValid;	// return false will stop the form submission
				},
				success: function(data){
					if(data!=0){
						$.messager.alert('消息','保存成功','info');
					}else{
						$.messager.alert('消息','保存失败','info');
					}
				}
			});
		}
	</script>
		<script type="text/javascript">
	//upload传递变量
		var savepath='${columns.conFilesSavPath}';
		var basePath='${basePath}';
	//对上传图片的前缀命名
		var sequence;
		function getseq(){
			
				var seq2='catalog'; //
				var seq1='${item.name}';//具体某个资料名称 
		
				sequence=seq1+'_'+seq2;
				return sequence;
		}
	</script>
	<script src="${basePath}component/ssi-up/js/ssi-uploader.js"></script>
	<script src="${basePath}component/ssi-up/js/upload.js"></script>

</html>