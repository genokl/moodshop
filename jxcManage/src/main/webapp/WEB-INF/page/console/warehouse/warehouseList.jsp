<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" href="css/bootstrap.min.css">
<base href="<%=basePath%>">
<title>库存列表</title>
</head>
<body>

	<div id="ad1">文章创建时间：
		<input id="pageNo" disabled="disabled" value="0">
		<input id="pageSize" disabled="disabled" value="1">
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<!-- <script src="js/public.js?d=1"></script> -->
	<script src="js/console/warehouse/warehouseList.js?dat=<%=new Date().getTime()%>"></script>
</body>
</html>