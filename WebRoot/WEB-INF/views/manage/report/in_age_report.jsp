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
		
		<title>码表信息</title>
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
	<style type="text/css"> 


		table{ border-collapse:collapse;border:1px #525152 solid;margin:0 auto;margin-top:20px;} 
		th,td{border:1px #525152 solid;text-align:center;font-size:12px;line-height:30px;} 
		
		
		/*模拟对角线*/ 
		.out{ 
		border-top:40px #D6D3D6 solid;/*上边框宽度等于表格第一行行高*/ 
		width:0px;/*让容器宽度为0*/ 
		height:0px;/*让容器高度为0*/ 
		border-left:120px #BDBABD solid;/*左边框宽度等于表格第一行第一格宽度*/ 
		position:relative;/*让里面的两个子容器绝对定位*/ 
		} 
		b{font-style:normal;display:block;position:absolute;top:-45px;left:-35px;width:35px;} 
		em{font-style:normal;display:block;position:absolute;top:-25px;left:-115px;width:55x;} 


</style> 
	
	</head>	  
	<body>
		<div style="width:100%;height:600px;">
		
          <div style="margin:20px 0; text-align:center">
			<h1>传承人年龄统计</h1>
          </div>
		  <div style="margin:20px 0; text-align:center">
			 <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px; " onclick="report_num()">传承人数据量统计</a>
			  <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" onclick="report_pro()">传承人项目数量统计</a>
			 <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" onclick="report(2)">传承人性别统计</a>
			 <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" onclick="report(3)">传承人级别统计</a>
			 <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" onclick="report(4)">传承人民族统计</a>
			 <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" >传承人年龄统计</a>
          </div>
			<table > 
				
				<tr>
				  <th style="width:120px;"> 
					<div class="out"> 
					<b>类别</b> 
				  <em>分类</em>					</div></th>
				
			  
					
					<c:forEach items="${list}" var="list"  >
					<th width="120px">${list[0]}</th> 
					</c:forEach>
					<th width="120px">总计</th> 
				</tr> 
					
				<tr> 
					<td class="t1">80岁以上</td> 
					<c:set value="0" var="bs" />  
					<c:forEach items="${list}" var="list"  >
						<c:set value="${bs + list[1]}" var="bs" />  
					<td>${list[1]}</td> 
					</c:forEach>
					<td class="t1"><div align="left">${bs} (
				      <fmt:formatNumber type="number" value="${(bs*10000/count)/100}" pattern="0.00" maxFractionDigits="2"/>%)</div></td> 
			  </tr> 
				<tr> 
					<td class="t1">70岁~79岁</td> 
					<c:set value="0" var="qs" />  
					<c:forEach items="${list_7}" var="list_7"  >
						<c:set value="${qs + list_7[1]}" var="qs" />  
					<td>${list_7[1]}</td> 
					</c:forEach>
					<td class="t1"><div align="left">${qs} (
				      <fmt:formatNumber type="number" value="${(qs*10000/count)/100}" pattern="0.00" maxFractionDigits="2"/>%)</div></td> 
			  </tr> 
			  <tr> 
					<td class="t1">60岁~69岁</td> 
					<c:set value="0" var="ls" />  
					<c:forEach items="${list_6}" var="list_6"  >
						<c:set value="${ls + list_6[1]}" var="ls" />  
					<td>${list_6[1]}</td> 
					</c:forEach>
					<td class="t1"><div align="left">${ls} (
				      <fmt:formatNumber type="number" value="${(ls*10000/count)/100}" pattern="0.00" maxFractionDigits="2"/>%)</div></td> 
			  </tr> 
			 <tr> 
					<td class="t1">50岁~59岁</td> 
					<c:set value="0" var="ws" />  
					<c:forEach items="${list_5}" var="list_5"  >
						<c:set value="${ws + list_5[1]}" var="ws" />  
					<td>${list_5[1]}</td> 
					</c:forEach>
					<td class="t1"><div align="left">${ws} (
				      <fmt:formatNumber type="number" value="${(ws*10000/count)/100}" pattern="0.00" maxFractionDigits="2"/>%)</div></td> 
			  </tr> 
			   <tr> 
					<td class="t1">40岁~49岁</td> 
					<c:set value="0" var="ss" />  
					<c:forEach items="${list_4}" var="list_4"  >
						<c:set value="${ss + list_4[1]}" var="ss" />  
					<td>${list_4[1]}</td> 
					</c:forEach>
					<td class="t1"><div align="left">${ss} (
				      <fmt:formatNumber type="number" value="${(ss*10000/count)/100}" pattern="0.00" maxFractionDigits="2"/>%)</div></td> 
			  </tr> 
				 <tr> 
					<td class="t1">30岁~39岁</td> 
					<c:set value="0" var="sss" />  
					<c:forEach items="${list_3}" var="list_3"  >
						<c:set value="${sss + list_3[1]}" var="sss" />  
					<td>${list_3[1]}</td> 
					</c:forEach>
					<td class="t1"><div align="left">${sss} (
				      <fmt:formatNumber type="number" value="${(sss*10000/count)/100}" pattern="0.00" maxFractionDigits="2"/>%)</div></td> 
			  </tr> 
			   <tr> 
					<td class="t1">30岁以下</td> 
					<c:set value="0" var="es" />  
					<c:forEach items="${list_2}" var="list_2"  >
						<c:set value="${es + list_2[1]}" var="es" />  
					<td>${list_2[1]}</td> 
					</c:forEach>
					<td class="t1"><div align="left">${es} (
				    <fmt:formatNumber type="number" value="${(es*10000/count)/100}" pattern="0.00" maxFractionDigits="2"/>%)</div></td> 
			  </tr> 
		  </table> 
        </div>
		
	</div>
	<script type="text/javascript">
		function report(type){
			var url="${basePath}action/report/inheritor/"+type+"/CN";
			window.location.href=url;
				
		}
		function report_num(){
			var url="${basePath}action/report/inheritor/CN";
			window.location.href=url;
		}
		function report_pro(type){
			var url="${basePath}action/report/inheritor_pro/CN";
			window.location.href=url;
				
		}
	</script>
	</body>
</html>