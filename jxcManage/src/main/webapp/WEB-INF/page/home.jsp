<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>创建活动</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link href="js/ace/css/font-awesome.min.css" rel="stylesheet" />
<link href="js/ace/css/ace-rtl.min.css" rel="stylesheet" />
<link href="js/ace/css/ace-skins.min.css" rel="stylesheet" />
<!-- <link href="js//sidebar-menu/sidebar-menu.css" rel="stylesheet"/> -->
<!-- <link rel="stylesheet" type="text/css" href="js/uploadifive/uploadifive.css"> -->
</head>
<body>


	<div class="sidebar" id="sidebar">
		<ul class="nav nav-list" id="menu"></ul>
		<div class="sidebar-collapse" id="sidebar-collapse">
			<i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
				data-icon2="icon-double-angle-right"></i>
		</div>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/public.js?d=1"></script>


	<script src="js/ace/ace-extra.min.js"></script>
	<script src="js/ace/ace.min.js"></script>

	<script src="js/ace/sidebar-menu/sidebar-menu.js"></script>
	<script src="js/ace/sidebar-menu/home.js"></script>
</body>
</html>
