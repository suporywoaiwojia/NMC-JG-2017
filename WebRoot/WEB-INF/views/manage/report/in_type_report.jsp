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
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/styles-report-pie.css">
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${basePath}js/report-pie.js"></script>
	</head>	  
	<body>
		<div style="width:100%;height:600px;">
		
          <div style="margin:20px 0; text-align:center">
			<h1>${title}</h1>
          </div>
		  <div style="margin:20px 0; text-align:center">
			 <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" onclick="report_num()">传承人数量统计</a>
			 <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" onclick="report_pro()">传承人项目数量统计</a>
			 <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" onclick="report(2)">传承人性别统计</a>
			 <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" onclick="report(3)">传承人级别统计</a>
			 <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" onclick="report(4)">传承人民族统计</a>
			 <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" onclick="report_age()">传承人年龄统计</a>
          </div>
			<div id="container">

  <canvas id="chart" width="600" height="500"></canvas>

  <table id="chartData">

    <tr>
      <th>名称</th><th>数量</th>
     </tr>
	<c:forEach items="${list}" var="list"  >
    <tr>
      <td>${list[0]}</td>
      <td>${list[1]}</td>
    </tr>
	</c:forEach>
  </table>
</div>

        </div>
		
	</div>
	<script type="text/javascript">
		function report(type){
			var url;
			url="${basePath}action/report/inheritor/"+type+"/CN";
			window.location.href=url;
				
		}
		function report_num(){
			var url="${basePath}action/report/inheritor/CN";
			window.location.href=url;
		}
		function report_age(){
			var url="${basePath}action/report/inheritor_age/CN";
			window.location.href=url;
		}
		function report_pro(type){
			var url="${basePath}action/report/inheritor_pro/CN";
			window.location.href=url;
				
		}
	</script>
	</body>
</html>