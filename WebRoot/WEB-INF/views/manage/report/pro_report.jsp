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
		border-top:92px #D6D3D6 solid;/*上边框宽度等于表格第一行行高*/ 
		width:0px;/*让容器宽度为0*/ 
		height:0px;/*让容器高度为0*/ 
		border-left:80px #BDBABD solid;/*左边框宽度等于表格第一行第一格宽度*/ 
		position:relative;/*让里面的两个子容器绝对定位*/ 
		} 
		b{font-style:normal;display:block;position:absolute;top:-70px;left:-40px;width:35px;} 
		em{font-style:normal;display:block;position:absolute;top:-35px;left:-70px;width:55x;} 


</style> 
	
	</head>
	  
	<body>
		<div style="width:100%;height:700px;">
		
          <div style="padding:20px 0 0;">
		 
	      </div>
          <div style="margin:20px 0; text-align:center">
			<h1>项目信息统计</h1>
          </div>
			<table width="800px"> 
				
				<tr>
				  <th width="81" rowspan="2" style="width:80px;"> 
					<div class="out"> 
					<b>类别</b> 
				  <em>级别</em>					</div></th>
				  <th width="75">民间文学</th>
				  <th colspan="4">传统表演</th>
				  <th colspan="3">手工技艺</th>
				  <th colspan="2">传统风俗</th>
			  </tr>
				<tr> 
					<th width="75">民间文学</th> 
					<th width="75">传统音乐</th> 
					<th width="75">传统舞蹈</th> 
					<th width="75">传统戏剧</th>
					<th width="75">传统曲艺</th>
					<th width="75">传统体育、游艺与杂技</th>
					<th width="75">传统美术</th>
					<th width="75">传统技艺</th>
					<th width="75">传统医药</th>
					<th width="75">民俗</th> 
				</tr> 
					<c:set value="0" var="wx" /> 
					<c:set value="0" var="yy" />  
					<c:set value="0" var="wd" />  
					<c:set value="0" var="xj" />  
					<c:set value="0" var="qy" />  
					<c:set value="0" var="zj" />  
					<c:set value="0" var="ms" />  
					<c:set value="0" var="yj" />  
					<c:set value="0" var="yao" />  
					<c:set value="0" var="min" />  
					<c:forEach items="${list}" var="list"  >
					  <c:set value="${wx + list[1]}" var="wx" />  
					  <c:set value="${yy + list[2]}" var="yy" />  
					  <c:set value="${wd + list[3]}" var="wd" />  
					  <c:set value="${xj + list[4]}" var="xj" />  
					  <c:set value="${qy + list[5]}" var="qy" />  
					  <c:set value="${zj + list[6]}" var="zj" />  
					  <c:set value="${ms + list[7]}" var="ms" />  
					  <c:set value="${yj + list[8]}" var="yj" />  
					  <c:set value="${yao + list[9]}" var="yao" />  
					  <c:set value="${min + list[10]}" var="min" />  
				<tr> 
					<td class="t1">${list[0]}</td> 
					<td>${list[1]}</td> 
					<td>${list[2]}</td> 
					<td>${list[3]}</td> 
					<td>${list[4]}</td>
					<td>${list[5]}</td>
					<td>${list[6]}</td>
					<td>${list[7]}</td>
					<td>${list[8]}</td>
					<td>${list[9]}</td>
					<td>${list[10]}</td> 
			  </tr> 
			</c:forEach>
				<tr> 
					<td rowspan="2" class="t1">合计(${wx+yy+wd+xj+qy+zj+ms+yj+yao+min})</td> 
					<td>${wx}</td> 
					<td>${yy}</td> 
					<td>${wd}</td> 
					<td>${xj}</td>
					<td>${qy}</td>
					<td>${zj}</td>
					<td>${ms}</td>
					<td>${yj}</td>
					<td>${yao}</td>
					<td>${min}</td> 
				</tr> 
				<tr> 
					<td>${wx}</td> 
					<td colspan="4">${yy+wd+xj+qy}</td> 
					<td colspan="3">${zj+ms+yj}</td>
					<td colspan="2">${yao+min}</td>
				</tr> 
		  </table> 

        </div>
		
	</div>
	<script type="text/javascript">

	</script>
	</body>
</html>