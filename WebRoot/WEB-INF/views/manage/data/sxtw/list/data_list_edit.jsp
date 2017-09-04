<%@page import="java.util.List"%>
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
	    <link rel="shortcut icon" href="favicon.ico">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
        <link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/styles-left.css">
		<link rel="stylesheet" type="text/css" href="${basePath}component/ssi-up/css/style.css">
		<link rel="stylesheet" href="${basePath}component/ssi-up/css/ssi-uploader.css"/>
        
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${basePath}js/validate.js"></script>

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
		//右下角弹出的的提示信息
		function showMessage(message){
				$.messager.show({
				title:'消息',
				msg:message,
				timeout:3000,
				showType:'slide'
			});
			}
		
		</script>
  </head>
 
<body>
<div class="easyui-panel" title="项目信息" style="width:100%;padding:20px 30px;">
  <div  style="padding:0 0 10px;" >
    <a  href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:300px;" onclick="save()">保存</a>	   
  </div>       
  <div style="margin:10px 0">
    <form id="web_form" action="" method="post">
	<table align="center">
   <input type="hidden" id="id" value="${item.id}"   name="id"/>
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
   <table align="center">
      <tr>
   <div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left: 300px solid #ddd; border-right: 300px solid #ddd; text-align: center;">     
					待审核信息
			</div>
   </tr>
		<tr>
        <td >资料名称:</td>
        <td>
        <input id="name"  name="name" value="${item.name}"   <c:if test="${approveState==1}" > readonly="readonly" </c:if>   data-options="required:true,missingMessage:'名称不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px" />
        </td>        
        <td style="padding-left:40px;">作者姓名:</td>
        <td>
        <input id="author" name="author" value="${item.author}"  <c:if test="${approveState=='1'}" > readonly="readonly" </c:if>   data-options="required:true,validType:'length[0,20]',missingMessage:'姓名不能为空',invalidMessage:'长度不能超出20个字符'" class="easyui-textbox" style="width:300px;height:30px;text-align:center;" /></td>        
        <td style="padding-left:40px;">作者单位:</td>
        <td>
        <input  id="address" name="address"  value="${item.address}"  <c:if test="${approveState=='1'}" > readonly="readonly" </c:if>   data-options="required:true,missingMessage:'单位不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px"/>
         </td>
      </tr>
      <tr> 
	    <td >资料类型:</td>
        <td>
           <!--编辑页面-->
             <c:if test="${approveState=='0'}" >  
                <select id="itemType" class="easyui-combobox" name="itemType.id" style="width:300px;height:30px; text-align:center;">
                    
                <c:forEach items="${typeList}" var="type" >
                  <c:if test="${type.id!=3}">  
                    <option value="${type.id}" <c:if test="${type.name==item.itemType.name}">selected="selected"</c:if> >${type.name}</option>	
                  </c:if>
                </c:forEach>
               </select>  
              </c:if> 
            <!--审核页面-->
              <c:if test="${approveState=='1'}" >
             <input    value="${item.itemType.name}"  readonly="readonly" 
                 data-options="required:true,missingMessage:'站点域名不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px"/> 
              </c:if>
 
        </td>
		<td style="padding-left:40px;">资料国别:</td>
        <td>
            <!--编辑页面-->
            <c:if test="${approveState==0}" > 
               <select id="itemCountry" class="easyui-combobox" name="itemCountry.id" style="width:300px;height:30px; text-align:center;">
                        
                <c:forEach items="${countryList}" var="country" >
                  <c:if test="${country.id!=2}">  
                     <option value="${country.id}" >${country.name}</option>	
                  </c:if>
                </c:forEach>
              </select>   
             </c:if>
             <!--审核页面-->
              <c:if test="${approveState==1}" >
             <input    value="${item.itemCountry.name}"  readonly="readonly" 
                 data-options="required:true,missingMessage:'站点域名不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px"/> 
              </c:if> 
       </td>
        <td style="padding-left:40px;">资料语言:</td>
        <td>
         <!--编辑页面-->
            <c:if test="${approveState==0}" > 
          <select id="language" class="easyui-combobox" name="itemLanguage.id" style="width:300px;height:30px; text-align:center;">
           <c:forEach items="${languageList}" var="language" >         
                 <option value="${language.id}" >${language.name}</option>	
            </c:forEach>
          </select>  
          </c:if>
        <!--审核页面-->
          <c:if test="${approveState==1}" >
         <input    value="${item.itemLanguage.name}"  readonly="readonly" 
             data-options="required:true,missingMessage:'站点域名不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px"/> 
          </c:if>     
          </td>
      </tr>
	  <tr>
        <td>出版机构:</td>
        <td> 
            <!--编辑页面-->
             <c:if test="${approveState==0}" > 
             <select id="pubHouse" class="easyui-combobox" name="pubHouse.id" style="width:300px;height:30px; text-align:center;">
                    
             <c:forEach items="${pubHouseList}" var="publishHouse" >
              <c:if test="${publishHouse.id!=1}">  
                 <option value="${publishHouse.id}" >${publishHouse.name}</option>	
              </c:if>
            </c:forEach>
          </select>  
          </c:if>
          <!--审核页面-->
              <c:if test="${approveState==1}" >
              <input    value="${item.pubHouse.name}"  readonly="readonly" 
                 data-options="required:true,missingMessage:'站点域名不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px"/> 
              </c:if>     
         </td>
	    <td style="height:30px; padding-left:40px;">出版版次:</td>
        <td>
           <input id="frequency"  name="frequency" value="${item.frequency}" class="easyui-textbox" 
            <c:if test="${approveState==1}" > readonly="readonly" </c:if> 
           data-options="required:true,missingMessage:'水印内容不能为空'" style="text-align:center;width:300px;height:30px" />      
        </td>
		<td style="height:30px; padding-left:40px;">出版时间:</td>
        <td>
          <input name="publishDate" id="born_s" value="${item.publishDate}" class="easyui-datebox" 
           <c:if test="${approveState==1}" > readonly="readonly" </c:if> 
          style="width:250px;height:30px;text-align:center;" data-options="required:true,validType:'date',missingMessage:'生日不能为空',sharedCalendar:'#cc',formatter:myformatter,parser:myparser,editable:false"  />
		<div id="cc" class="easyui-calendar"></div>       
         </td>
      </tr>
      <tr>
	   <td >国际标准编号:</td>
        <td>
        <input id="isbn" name="isbn" value="${item.isbn}" class="easyui-textbox" 
         <c:if test="${approveState==1}" > readonly="readonly" </c:if> 
        data-options="required:true,validType:'tel',missingMessage:'编号不能为空'" style="text-align:center;width:300px;height:30px" />
        </td>
       

       	<td  style="padding-left:40px;">选择栏目</td>
         <td>
          <!--编辑页面-->
          <c:if test="${approveState==0}" > 
          <select  class="easyui-combobox" name="parent.id" id="parent" style="width:300px;height:30px;" data-options="editable:false,panelHeight:'auto'">
		  		 <c:forEach items="${columnList}" var="column" >
					<option value="${column.id}" <c:if test="${item.parent.id==column.id}"> selected="selected"</c:if>>${column.name}</option>		
				</c:forEach>
          </select>
          </c:if>
           <!--审核页面-->
              <c:if test="${approveState==1}" >
             <input    value="${item.parent.name}"  readonly="readonly" 
                 data-options="required:true,missingMessage:'站点域名不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px"/> 
              </c:if> 
        </td>
	   <td></td>
        </tr>
      <tr>
	   <td >资料摘要:</td>
		<td colspan="5">
        <input id="summary" name="summary" value="${item.summary}" class="easyui-textbox" 
         <c:if test="${approveState==1}" > readonly="readonly" </c:if> 
        data-options="multiline:true" style="width:1100px;height:150px" />
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
    
     <div style=" padding: 0 20px 0; margin: 20px 0; line-height: 1px; border-left: 300px solid #ddd; border-right: 300px solid #ddd; text-align: center;">     
					附件信息
			</div>
		  <table align="center">
			  <tr>
				<td>封面附件</td>
					<td>
					<div  style="border:2px dashed #CCCCCC;min-height:200px; width:1100px;margging-left:40px;" id="file1">
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
				  </tr>
				  <tr>
				  <td style="width:50px"></td>
				  <td>
                  <c:if test="${approveState==0}">
					<input type="file" multiple id="ssi-upload"/>
                  </c:if>
					</td>
				  </tr>
			</table>
			
			<!--上传结束 -->
  </div>      
</div>
</body>
<script type="text/javascript">
		function save(){
			var url = "${basePath}action/manage/data/item/update";
			var data = $("#web_form").serialize();
			$.post(url, data,
				   function(result){
					 if(result=='1'){
						 $.messager.alert('消息','保存成功','info');
					 }else if(result=='0'){
						 $.messager.alert('消息','保存失败','info');
				      }
				   }, "text");
				
		}
	</script>
		<script type="text/javascript">
	//upload传递变量
		var savepath='${column.conFilesSavPath}';
		var basePath='${basePath}';
	//对上传图片的前缀命名
		var sequence;
		function getseq(){
			
				var seq2='item'; //
				var seq1='${column.id}';//具体某个资料名称 
		
				sequence=seq1+'_'+seq2;
				return sequence;
		}
	</script>
	<script src="${basePath}component/ssi-up/js/ssi-uploader.js"></script>
	<script src="${basePath}component/ssi-up/js/upload.js"></script>
</html>
