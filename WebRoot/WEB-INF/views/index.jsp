<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	if(application.getAttribute("basePath") == null){
		application.setAttribute("basePath", basePath);
		com.menksoft.util.Const.BASEPATH = basePath;
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
		<title>草原文化创意资源平台-主页</title>
		<script type="text/javascript">
			window.history.go(-1);window.location.reload();
		</script>
	</head>
	<body>
		
	</body>
</html>