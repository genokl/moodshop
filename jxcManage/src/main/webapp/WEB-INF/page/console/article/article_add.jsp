<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title >创建文章</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/quick-layout-min.css">
<link rel="stylesheet" href="css/picture.css">
<link rel="stylesheet" type="text/css" href="js/uploadifive/uploadifive.css">

</head>
<body>

        <!-- 最外边框 -->
        <div style="margin: 20px auto; border: 1px solid blue; width: 300px; height: 500px;">

            <!-- 消息展示框 -->
            <div id="msg" style="width: 100%; height: 70%; border: 1px solid yellow;overflow: auto;"></div>

            <!-- 消息编辑框 -->
            <textarea id="tx" style="width: 100%; height: 20%;"></textarea>

            <!-- 消息发送按钮 -->
            <button id="TXBTN" style="width: 100%; height: 8%;">发送数据</button>

        </div>


	
	
	<!-- <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script> -->
	
	<!-- <input type="button" value="创建内容" id="createArticle" style="display: dispaly;"> -->
	<!-- <div class="fixed-bottom-box">
		<button type="button" id="createArticle" class="btn btn-primary pl20 pr20">创建内容</button>
	</div> -->
	<input type="hidden"  id="onUpload" onclick="$('#file_upload').uploadifive('upload')">
	
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/public.js?d=1"></script>
	<script type="text/javascript" src="js/uploadifive/jquery.uploadifive.min.js"></script>
	<script type="text/javascript" src="https://cdn.bootcss.com/sockjs-client/1.1.4/sockjs.min.js"></script>
	<script src="js/console/article/article_add.js?dat=<%=new Date().getTime()%>"></script>
	
	<!-- <script type="text/javascript" charset="utf-8" src="js/baiduUE/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="js/baiduUE/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="js/baiduUE/lang/zh-cn/zh-cn.js"></script> -->
    
</body>
</html>
