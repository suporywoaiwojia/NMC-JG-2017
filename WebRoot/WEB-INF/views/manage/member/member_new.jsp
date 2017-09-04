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
		<link type="text/css" rel="stylesheet" href="${basePath}component/js-fileupload/client/css/uploadify.css" /> 
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
	    <script type="text/javascript" src="${basePath}component/js-fileupload/client/jquery.uploadify.js"></script>
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
		//确保输入框内容不为空
		$(function(){
		$("#saveBtn").click(function (){
		
        //没有错误后，提交给后台
		var url="${basePath}action/member/save";
		$("#web-form").attr("action",url);
		$("#web-form").submit();
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
       
	   //上传头像
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
  <div id="saveBtn" style="padding:0 0 10px;" >
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:300px;">保存</a>	   
  </div>       
  <div style="margin:10px 0">
  <form id="web-form" action="" method="post">
    <table>
      <tr>
	    
		<td rowspan="2" style="padding-top:10px;padding-right:10px;">
			<input type="file" id="upload_file" name="upload_file" "  />
			<input type="hidden" id="filePath" name="image" "/>
		</td>
        <td style="padding-left:40px;">登录ID</td>
        <td><input id="loginid"  name="loginid"  data-options="required:true,validType:'length[0,20]',missingMessage:'登录账号不能为空',invalidMessage:'登录ID不能超出10个汉字或20个字符'" class="easyui-textbox" style="width:250px;height:30px;text-align:center;"/></td>        
       <td style="padding-left:40px;">会员昵称</td>
        <td><input  id="nickname" name="nickname"  data-options="required:true,validType:'length[0,20]',missingMessage:'昵称不能为空',invalidMessage:'会员昵称不能超过10个汉字或20个字符'" class="easyui-textbox" style="width:250px;height:30px;text-align:center;"/></td>
		<td style="padding-left:40px;">会员性别</td>
        <td>
          <select id="sex" class="easyui-combobox" name="sex" style="width:250px;height:30px; ">
            <option value="0" <c:choose><c:when test="${member.sex eq '0'}">selected</c:when></c:choose>>保密</option>
            <option value="1" <c:choose><c:when test="${member.sex eq '1'}">selected</c:when></c:choose>>男</option>
            <option value="2" <c:choose><c:when test="${member.sex eq '2'}">selected</c:when></c:choose>>女</option>
          </select>
        </td>
      </tr>
      <tr>
        
        <td style="padding-left:40px;">会员生日</td>
        <td>
		<input name="birthday" id="born_s" class="easyui-datebox" style="width:250px;height:30px;text-align:center;" data-options="required:true,validType:'date',missingMessage:'生日不能为空',sharedCalendar:'#cc',formatter:myformatter,parser:myparser,editable:false"  />
		<div id="cc" class="easyui-calendar"></div>
		</td>
        <td style="padding-left:40px;">注册邮箱</td>
        <td><input  id="mail" name="mail"  data-options="required:true,validType:'email',missingMessage:'注册邮箱不能为空',invalidMessage:'邮箱格式如acb@sina.com'" class="easyui-textbox" style="width:250px;height:30px;text-align:center;"/></td>
		<td style="padding-left:40px;">会员密码</td>
        <td><input id="password" name="password"  type= "password" class="easyui-textbox" data-options="required:true,validType:'length[0,60]',missingMessage:'密码不能为空',invalidMessage:'密码不能超出30个汉字或60个字符'" style="width:250px;height:30px;text-align:center;"/></td>
      </tr>
    </table>
    <table style="margin-top:40px;margin-left:213px;">
      <tr>
        <td >联系电话</td>
        <td><input id="tel" name="tel"  data-options="required:true,missingMessage:'联系方式不能为空'," class="easyui-textbox" style="width:250px;height:30px;text-align:center;"/></td>

        <td style="padding-left:40px;">联系地址</td>
        <td><input id ="address" name ="address"  data-options="required:true,missingMessage:'联系方式不能为空'," class="easyui-textbox" style="width:250px;height:30px;text-align:center;"/></td>
		<td style="height:30px;padding-left:40px;">会员状态</td>
        <td>
          <label><input id="state" name="state" type="radio" value="0" <c:choose><c:when test="${member.state eq '0'}">checked</c:when></c:choose>/>正常</label>
          <label><input id="state" name="state" type="radio" value="1" <c:choose><c:when test="${member.state eq '1'}">checked</c:when></c:choose> />禁止</label>
        </td>
      </tr>
      <tr>
        <td>有效日期</td>
        <td>
		<input name="eTime" id="born_s" class="easyui-datebox"  style="width:250px;height:30px;text-align:center;" data-options="required:true,validType:'date',missingMessage:'生日不能为空',sharedCalendar:'#cc',formatter:myformatter,parser:myparser,editable:false"   />
		<div id="cc" class="easyui-calendar"></div>
		</td>
         <td style="padding-left:40px;">会员类型</td>
		 <td><select id="type" class="easyui-combobox" name="type" style="width:250px;height:30px; text-align:center;">
					<option value="0">个人</option>
					<option value="1">企业</option>
          </select>
		  </td>
        <td  style="padding-left:40px;">角色ID</td>
         <td>
          <select id="role" class="easyui-combobox" name="role.id" style="width:250px;height:30px; text-align:center;">
		  		 <c:forEach items="${memberTypes.dataSet}" var="memberType" >
					<option value="${memberType.id}" <c:choose><c:when test="${member.role.id eq memberType.id}">selected</c:when></c:choose>>${memberType.name}</option>		
				</c:forEach>
          </select>

        </td>
	  </tr>
    </table>
    </form>
  </div>      
</div>
</body>
</html>
