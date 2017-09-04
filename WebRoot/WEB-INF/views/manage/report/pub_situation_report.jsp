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
		b{font-style:normal;display:block;position:absolute;top:-45px;left:-45px;width:45px;} 
		em{font-style:normal;display:block;position:absolute;top:-25px;left:-115px;width:55x;} 


</style> 
	
	</head>	  
	<body>
		<div style="width:100%;height:600px;">
		
          <div style="margin:20px 0; text-align:center">
			<h1>${title}</h1>
          </div>
		  <div style="margin:20px 0; text-align:center">
			<a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" onclick="report(1)">出版物信息统计</a>
			 <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" onclick="report_pro()">出版物项目信息统计</a>
			 <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" onclick="report(2)">出版物出版情况统计</a>
          </div>
			<table > 
				
				<tr>
				  <th style="width:120px;"> 
					<div class="out"> 
					<b>出版社</b> 
				  <em>年份</em>					</div></th>
				
			  	<!--<c:forEach items="${year}" var="year"  >
					<th width="120px">${year}</th> 
				 </c:forEach>
				</tr> 
				<c:forEach items="${publish}" var="publish"  >
				<tr> 
			
					<td class="t1">${publish[1]}</td> 
					<c:forEach items="${count}" var="count"  >
						<c:if test="${publish[0] eq count[0]}">
						<td class="t1">${count[1]}</td> 
						</c:if>
					</c:forEach>	
			  </tr> 
				</c:forEach>	 -->
				<c:forEach items="${publish}" var="publish"  varStatus="p" >
					
					<th width="120px">${publish[1]}</th> 
				 </c:forEach>
				</tr> 
				<c:forEach items="${year}" var="year"  >
				<tr> 
				
					<td class="t1">${year}</td> 
					<c:forEach items="${count}" var="count"  >
						<c:if test="${year eq count[0]}">
						
						<td class="t1">${count[1]}</td> 
						</c:if>
						
					</c:forEach>	
			  </tr> 
				</c:forEach>
				<tr>
					<td>总计</td>
				
					<c:forEach items="${zj}" var="zj"  >	
					
					<td>${zj[1]}</td>
					</c:forEach>
				</tr>
		  </table> 
        </div>
		
	</div>
	<script type="text/javascript">
		function report(type){
			var url="${basePath}action/report/pub/"+type+"/CN";
			window.location.href=url;
				
		}
		function report_pro(){
			var url="${basePath}action/report/pub_pro/CN";
			window.location.href=url;
				
		}
	</script>
	</body>
</html>