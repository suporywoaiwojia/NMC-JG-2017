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
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${basePath}js/validate.js"></script>
	    <script type="text/javascript" src="${basePath}component/js-fileupload/client/jquery.uploadify.js"></script>
		<script type="text/javascript">
	
		//右下角弹出的的提示信息
		function showMessage(message){
				$.messager.show({
				title:'消息',
				msg:message,
				timeout:3000,
				showType:'slide'
			});
			}
		//确保输入框内容不为空
		$(function(){
		$("#saveBtn").click(function (){
        //没有错误后，提交给后台
		var url="${basePath}action/website/update";
		$('#web-form').form('submit', {
					url: url,
					success: function(data){
						if(data=="1"){
							$.messager.alert('消息','保存成功','info');
						}else{
							$.messager.alert('消息','保存失败','info');
						}
					}
				});
		});
		});

		//拓展的验证格式
		$.extend($.fn.validatebox.defaults.rules, {
            idcard: {// 验证身份证
                validator: function (value) {
                    return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
                },
                message: '身份证号码格式不正确'
            },
            minLength: {
                validator: function (value, param) {
                    return value.length >= param[0];
                },
                message: '请输入至少（2）个字符.'
            },
            length: { validator: function (value, param) {
                var len = $.trim(value).length;
                return len >= param[0] && len <= param[1];
            },
                message: "输入内容长度必须介于{0}和{1}之间."
            },
            phone: {// 验证电话号码
                validator: function (value) {
                    return /^((\d2,3)|(\d{3}\-))?(0\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
                },
                message: '格式不正确,请使用下面格式:020-88888888'
            },
            mobile: {// 验证手机号码
                validator: function (value) {
                    return /^(1)\d{10}$/i.test(value);
                },
                message: '手机号码格式不正确'
            },
			tel:{
			    validator:function(value){
				var phone = /^((\d2,3)|(\d{3}\-))?(0\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
				var mobile = /^(1)\d{10}$/i.test(value);
                return (phone||mobile);
				},
				message:'电话联系格式不正确'
			},
            intOrFloat: {// 验证整数或小数
                validator: function (value) {
                    return /^\d+(\.\d+)?$/i.test(value);
                },
                message: '请输入数字，并确保格式正确'
            },
            currency: {// 验证货币
                validator: function (value) {
                    return /^\d+(\.\d+)?$/i.test(value);
                },
                message: '货币格式不正确'
            },
            qq: {// 验证QQ,从10000开始
                validator: function (value) {
                    return /^[1-9]\d{4,9}$/i.test(value);
                },
                message: 'QQ号码格式不正确'
            },
            integer: {// 验证整数 可正负数
                validator: function (value) {
                    //return /^[+]?[1-9]+\d*$/i.test(value);

                    return /^([+]?[0-9])|([-]?[0-9])+\d*$/i.test(value);
                },
                message: '请输入整数'
            },
            age: {// 验证年龄
                validator: function (value) {
                    return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value);
                },
                message: '年龄必须是0到120之间的整数'
            },

            chinese: {// 验证中文
                validator: function (value) {
                    return /^[\Α-\￥]+$/i.test(value);
                },
                message: '请输入中文'
            },
            english: {// 验证英语
                validator: function (value) {
                    return /^[A-Za-z]+$/i.test(value);
                },
                message: '请输入英文'
            },
            unnormal: {// 验证是否包含空格和非法字符
                validator: function (value) {
                    return /.+/i.test(value);
                },
                message: '输入值不能为空和包含其他非法字符'
            },
            username: {// 验证用户名
                validator: function (value) {
                    return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
                },
                message: '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
            },
            faxno: {// 验证传真
                validator: function (value) {
                    //            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value);
                    return /^((\d2,3)|(\d{3}\-))?(0\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
                },
                message: '传真号码不正确'
            },
            zip: {// 验证邮政编码
                validator: function (value) {
                    return /^[1-9]\d{5}$/i.test(value);
                },
                message: '邮政编码格式不正确'
            },
            ip: {// 验证IP地址
                validator: function (value) {
                    return /d+.d+.d+.d+/i.test(value);
                },
                message: 'IP地址格式不正确'
            },
            name: {// 验证姓名，可以是中文或英文
                validator: function (value) {
                    return /^[\Α-\￥]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
                },
                message: '请输入姓名'
            },
            date: {// 验证姓名，可以是中文或英文
                validator: function (value) {
                    //格式yyyy-MM-dd或yyyy-M-d
                    return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value);
                },
                message: '清输入合适的日期格式'
            },
            msn: {
                validator: function (value) {
                    return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
                },
                message: '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
            },
            same: {
                validator: function (value, param) {
                    if ($(param[0]).val() != "" && value != "") {
                        return $(param[0]).val() == value;
                    } else {
                        return true;
                    }
                },
                message: '两次输入的密码不一致！'
            }
        });

		$(function(){
		var files_img = $('input[name="upload_file"]');
			files_img.each(function(){
				var file_img = $(this);
					file_img.render({
						
						buttonClass: 'custom',
						savePath: 'website/www/contentImg/links/',
						httpPath: 'website/www/contentImg/links/',
						basePath: '${basePath}',
						componentPath: '${basePath}component/js-fileupload/client/',
						type: 'image',
						callback: function(fileElement, file, saveFile, httpFile){
							var index = file.lastIndexOf('.');
							var ext = file.substring(index, file.length);
							$('#filePath').val(httpFile + ext);
						}
					});
				file_img.preview(file_img.attr('title'));
				});
	});
		</script>
  </head>
 
<body>
<div class="easyui-panel" title="项目信息" style="width:100%;padding:20px 30px;">
  <div  style="padding:0 0 10px;" >
    <a id="saveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:300px;">保存</a>	   
  </div>       
  <div style="margin:10px 0">
    <form id="web-form" action="" method="post">
	<table>
		<tr>
        <td >网站名称:</td>
        <td><input id="name"  name="name" data-options="required:true,missingMessage:'站点名称不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px" /></td>        
        <td style="padding-left:40px;">网站简称:</td>
        <td><input id="abbreviation" name="abbreviation" data-options="required:true,validType:'length[0,10]',missingMessage:'站点简称不能为空',invalidMessage:'站点简称长度不能超出20个字符'" class="easyui-textbox" style="width:300px;height:30px;text-align:center;" /></td>        
        <td style="padding-left:40px;">网站域名:</td>
        <td><input  id="domainName" name="domainName" data-options="required:true,missingMessage:'站点域名不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px"/></td>
      </tr>
      <tr> 
		 <td>系统语言:</td>
        <td>
          <select id="language" class="easyui-combobox" name="language" style="width:300px;height:30px; text-align:center;">
            <option value="中">中</option>
            <option value="蒙">蒙</option>
            <option value="英">英</option>
            <option value="日">日</option>
            <option value="韩">韩</option>
          </select>        </td>
	      <td style="padding-left:40px;">关键字:</td>
        <td>
          <input id="webKey"  name="webKey" class="easyui-textbox" data-options="required:true,missingMessage:'关键字不能为空'" style="text-align:center;width:300px;height:30px" />        </td>
		<td style="padding-left:40px;">水印内容:</td>
        <td>
          <input id="watermark"  name="watermark" class="easyui-textbox" data-options="required:true,missingMessage:'水印内容不能为空'" style="text-align:center;width:300px;height:30px" />        </td>
      </tr>
	  <tr>
        <td>会员审核:</td>
        <td>
          <label><input id="member-y" name="member" type="radio" value="是" />是</label>
          <label><input id="member-n" name="member" type="radio" value="否" />否</label>        </td>
	    <td style="height:30px; padding-left:40px;">首页静态:</td>
        <td>
          <label><input id="indexDynamics" name="indexDynamics" type="radio" value="是" />是</label>
          <label><input id="indexDynamics" name="indexDynamics" type="radio" value="否" />否</label>        </td>
		<td style="height:30px; padding-left:40px;">列表静态:</td>
        <td>
          <label><input id="member-y" name="listDynamics" type="radio" value="是" />是</label>
          <label><input id="member-n" name="listDynamics" type="radio" value="否" />否</label>        </td>
      </tr>
      <tr>
	   <td >联系电话:</td>
        <td><input id="tel" name="tel" class="easyui-textbox" data-options="required:true,validType:'tel',missingMessage:'联系电话不能为空',invalidMessage:'电话格式为020-3333333或者13811111111'" style="text-align:center;width:300px;height:30px" /></td>
        <td style="padding-left:40px;">联系邮箱:</td>
        <td><input id="mail" name="mail" class="easyui-textbox" data-options="required:true,validType:'email',missingMessage:'联系邮箱不能为空',invalidMessage:'邮箱格式不正确'" style="text-align:center;width:300px;height:30px" /></td>

       <td></td>
	   <td></td>
        </tr>
      <tr>
	   <td >企业名称:</td>
        <td><input id="businessName" name="businessName" data-options="required:true,missingMessage:'企业名称不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px" /></td>
	  <td style="padding-left:40px;" >企业地址:</td>
        <td><input id="businessAdd" name="businessAdd" data-options="required:true,missingMessage:'企业地址不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px" /></td>
       
		<td style="padding-left:40px;">邮政编码:</td>
        <td><input id ="postCode" name ="postCode" data-options="required:true,missingMessage:'邮编不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px" /></td>
      </tr>
      <tr> 
	      
        <td  style="height:30px; ">备案编号:</td>
        <td><input id="ipc" name="ipc" data-options="required:true,missingMessage:'备案编号不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px" /></td>
        <td style="padding-left:40px;">版权信息:</td>
        <td><input  id="copyRight" name="copyRight" data-options="required:true,missingMessage:'版权信息不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px" /></td>
		<td></td>
		<td></td>
      </tr>


      <tr>
	  	<td>站点描述:</td>
		<td colspan="5"><input id="detailed" name="detailed" class="easyui-textbox" data-options="multiline:true" style="width:1100px;height:150px" /></td>
		</tr>
    </table>
    </form>
  </div>      
</div>
</body>
</html>
